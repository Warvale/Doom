package net.warvale.ffa.listeners;


import net.warvale.ffa.gui.GUIManager;
import net.warvale.ffa.gui.guis.KitSelectorGUI;
import net.warvale.ffa.player.FFAPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerListPingEvent;
import net.warvale.ffa.WarvaleFFA;
import net.warvale.ffa.game.FFAMode;
import net.warvale.ffa.message.MessageManager;
import net.warvale.ffa.message.PrefixType;
import net.warvale.ffa.player.PlayerManager;
import net.warvale.ffa.scoreboards.FFAScoreboard;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class SessionListener implements Listener {
    WarvaleFFA plugin;
    public SessionListener(WarvaleFFA plugin){
        this.plugin = plugin;
    }
    @EventHandler (priority = EventPriority.HIGH)
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);

        Player player = event.getPlayer();

        //make sure player is in survival and clear inventory
        player.setGameMode(GameMode.SURVIVAL);
        player.getInventory().clear();

        //make sure the player exists
        if (!PlayerManager.getInstance().doesFFAPlayerExsists(player.getUniqueId())) {
            PlayerManager.getInstance().createFFAPlayer(player.getUniqueId());
        }
        FFAPlayer ffaPlayer = PlayerManager.getInstance().getFFAPlayer(player.getUniqueId());
        FFAScoreboard.getInstance().addScoreboard(player);
        FFAScoreboard.getInstance().newScoreboard(player);

        player.teleport(WarvaleFFA.get().getGame().getSpawn());
        KitSelectorGUI.giveKitSelectorItem(player);
        player.setHealth(20);
        for (PotionEffect effect : player.getActivePotionEffects())  // loop thru all active potion effects
            player.removePotionEffect(effect.getType()); // remove potion effects
        player.setFoodLevel(20);
        player.setLevel(ffaPlayer.getLevel());

    }

    @EventHandler (priority = EventPriority.HIGH)
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);

        Player player = event.getPlayer();

        player.getInventory().clear();
        PlayerManager.getInstance().removeFFAPlayer(player.getUniqueId());
        FFAScoreboard.getInstance().removeScoreboard(player);
    }

    @EventHandler (priority = EventPriority.HIGH)
    public void onKick(PlayerKickEvent event) {

        Player player = event.getPlayer();

        player.getInventory().clear();
        PlayerManager.getInstance().removeFFAPlayer(player.getUniqueId());
        FFAScoreboard.getInstance().removeScoreboard(player);
    }

    @EventHandler (priority = EventPriority.HIGH)
    public void onLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();

        if (Bukkit.getServer().getOnlinePlayers().size() >= WarvaleFFA.get().getGame().getMaxPlayers()) {
            player.kickPlayer(MessageManager.getPrefix(PrefixType.FFA) + "The server is currently full, if you wish to join full servers,\n" +
                    "you can buy a donor rank at http://store.warvale.net");
        }
    }

    @EventHandler
    public void onPing(ServerListPingEvent event) {

//        event.setMotd(MessageManager.getPrefix(PrefixType.FFA) + "§7UHC FFA now in public beta");
        event.setMaxPlayers(WarvaleFFA.get().getGame().getMaxPlayers());
    }
    @EventHandler
    public void onSoup(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR))) return; // Stop doing anything if they arent right clicking.
        if (!(Material.MUSHROOM_SOUP.equals(event.getItem().getType()))) return;
        if ((player.getHealth()+2.5) > 20) player.setHealth(20); else player.setHealth(player.getHealth()+2.5);
        player.getInventory().remove(new ItemStack(Material.MUSHROOM_SOUP,1));
    }
    @EventHandler
    public void kitSelectorInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR))) return; // Stop doing anything if they arent right clicking.
        if (!("§bKit Selector".equals(event.getItem().getItemMeta().getDisplayName()))) return; // is this the right item?
        // go ahead and open the gui
        KitSelectorGUI inv = plugin.getGUI().getGUI(KitSelectorGUI.class);

        player.openInventory(inv.get(player));
    }

}
