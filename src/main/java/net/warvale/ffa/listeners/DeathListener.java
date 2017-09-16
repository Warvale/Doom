package net.warvale.ffa.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import net.warvale.ffa.WarvaleFFA;
import net.warvale.ffa.game.FFAMode;
import net.warvale.ffa.kits.UHCKit;
import net.warvale.ffa.message.MessageManager;
import net.warvale.ffa.player.FFAPlayer;
import net.warvale.ffa.player.PlayerManager;
import net.warvale.ffa.utils.ItemStackUtils;

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
        ffaPlayer.addDeath();
        ffaPlayer.addTotalDeath();
        FFAPlayer ffadead = PlayerManager.getInstance().getFFAPlayer(killer.getUniqueId());
        ffadead.setEmbers(ffadead.getEmbers()+20);

        //only reset kill streak if it is greater than 5
        if (ffaPlayer.getKillStreak() > 5) {
            MessageManager.broadcast(ChatColor.AQUA + killer.getName() + ChatColor.GRAY + " has ended " + ChatColor.AQUA +
                    player.getName() + ChatColor.GRAY + "'s killstreak of " + ChatColor.RED + ffaPlayer.getKillStreak() + ChatColor.GRAY + "!");

            if (ffaPlayer.getKillStreak() > ffaPlayer.getHighestKillStreak()) {
                ffaPlayer.setHighestKillStreak(ffaPlayer.getKillStreak());
            }

            ffaPlayer.resetKillStreak();
        }

        //killer stats
        killer.getInventory().addItem(ItemStackUtils.createGoldenHead());

        //update killer stats
        FFAPlayer ffaPlayerKiller = PlayerManager.getInstance().getFFAPlayer(killer.getUniqueId());

        ffaPlayerKiller.addKill();
        ffaPlayerKiller.addKillStreak();
        ffaPlayerKiller.addTotalKill();

        if (ffaPlayerKiller.getKillStreak() > ffaPlayerKiller.getHighestKillStreak()) {
            ffaPlayerKiller.setHighestKillStreak(ffaPlayerKiller.getKillStreak());
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

    }

    @EventHandler (priority = EventPriority.HIGH)
    public void onRespawn(PlayerRespawnEvent event) {

        Player player = event.getPlayer();

        if (!WarvaleFFA.get().getGame().isFFAMode(FFAMode.NONE)) {
            WarvaleFFA.get().getGame().getGameMode().getKit().loadKit(player);
        }

        event.setRespawnLocation(WarvaleFFA.get().getGame().getSpawn());
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

}
