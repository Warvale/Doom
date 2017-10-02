package net.warvale.ffa.listeners;

import net.warvale.ffa.gui.GUIManager;
import net.warvale.ffa.gui.guis.KitSelectorGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import net.warvale.ffa.WarvaleFFA;
import net.warvale.ffa.message.MessageManager;
import net.warvale.ffa.player.FFAPlayer;
import net.warvale.ffa.player.PlayerManager;
import net.warvale.ffa.utils.ItemStackUtils;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DeathListener implements Listener {

    @EventHandler (priority = EventPriority.HIGH)
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity().getPlayer();
        Player killer = event.getEntity().getKiller();


        if (player == null || killer == null) {
            return;
        }


        //clear items of the player
        player.getInventory().clear();

        //update player stats
        FFAPlayer ffaPlayer = PlayerManager.getInstance().getFFAPlayer(player.getUniqueId());
            player.spigot().respawn(); // insta respawn
        player.getInventory().setArmorContents(new ItemStack[4]);
        ffaPlayer.addDeath();
        ffaPlayer.addTotalDeath();
        FFAPlayer ffakiller = PlayerManager.getInstance().getFFAPlayer(killer.getUniqueId());
        ffakiller.setEmbers(ffakiller.getEmbers()+20);
        killer.sendMessage(ChatColor.GOLD + "+20 Embers");


        //only reset kill streak if it is greater than 5
        if (ffaPlayer.getKillStreak() > 5) {
            MessageManager.broadcast(ChatColor.AQUA + killer.getName() + ChatColor.GRAY + " has ended " + ChatColor.AQUA +
                    player.getName() + ChatColor.GRAY + "'s killstreak of " + ChatColor.RED + ffaPlayer.getKillStreak() + ChatColor.GRAY + "!");

            if (ffaPlayer.getKillStreak() > ffaPlayer.getHighestKillStreak()) {
                ffaPlayer.setHighestKillStreak(ffaPlayer.getKillStreak());
            }

            ffaPlayer.resetKillStreak();
        }

        // leveling / xp system
        int levelbeforechanges = ffakiller.getLevel();
        int amountadding = (ffakiller.getKillStreak()+1) * 5;
        ffakiller.setXp(ffakiller.getXp()+amountadding);
        killer.sendMessage(ChatColor.AQUA+"+"+amountadding+" XP");
        killer.setLevel(ffakiller.getLevel()); // Update MC level.
        if (levelbeforechanges != ffakiller.getLevel()) {
            Bukkit.getServer().broadcastMessage(ChatColor.GREEN + killer.getName()+ChatColor.AQUA+" just leveled up to level "+String.valueOf(ffakiller.getLevel())+" by killing "+ ChatColor.GREEN+player.getName()+ChatColor.AQUA+"!");
        }


        //update killer stats


        ffakiller.addKill();
        ffakiller.addKillStreak();
        ffakiller.addTotalKill();

        if (ffakiller.getKillStreak() > ffakiller.getHighestKillStreak()) {
            ffakiller.setHighestKillStreak(ffakiller.getKillStreak());
        }

        if (player.getLastDamageCause() != null) {

            EntityDamageEvent ede = player.getLastDamageCause();
            EntityDamageEvent.DamageCause dc = ede.getCause();

            if ((ede instanceof EntityDamageByEntityEvent)) {
                EntityDamageByEntityEvent edbee = (EntityDamageByEntityEvent) ede;
                Entity agresor = edbee.getDamager();

                if ((agresor instanceof Player)) {

                    event.setDeathMessage(ChatColor.RED + player.getName() + ChatColor.GRAY + " was slain by " + ChatColor.RED + killer.getName());
                    return;

                }

                if (dc == EntityDamageEvent.DamageCause.PROJECTILE) {
                    Projectile pro = (Projectile) agresor;
                    Entity shooter = (pro.getShooter() instanceof LivingEntity) ? (Entity)pro.getShooter() : null;

                    if (shooter == null) {
                        event.setDeathMessage(null);
                    } else {

                        if ((shooter instanceof Player)) {

                            if (pro.getType().equals(EntityType.ARROW)) {
                                event.setDeathMessage(ChatColor.RED + player.getName() + ChatColor.GRAY + " was shot by " + ChatColor.RED + killer.getName());
                            }

                        } else {
                            event.setDeathMessage(null);
                        }

                    }

                }

            } else {
                event.setDeathMessage(null);
            }

        }
        PotionEffect regenlol = new PotionEffect(PotionEffectType.REGENERATION,5,2);
        killer.addPotionEffect(regenlol, true);
    }

    @EventHandler (priority = EventPriority.HIGH)
    public void onRespawn(PlayerRespawnEvent event) {

        Player player = event.getPlayer();


        event.setRespawnLocation(WarvaleFFA.get().getGame().getSpawn());
        KitSelectorGUI.giveKitSelectorItem(player);
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onHungerChange(FoodLevelChangeEvent e  ) {e.setFoodLevel(20);}

}
