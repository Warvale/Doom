package net.warvale.ffa.listeners;


import net.warvale.ffa.gui.guis.KitSelectorGUI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;
import net.warvale.ffa.WarvaleFFA;
import net.warvale.ffa.game.FFAMode;
import net.warvale.ffa.message.MessageManager;
import net.warvale.ffa.message.PrefixType;
import net.warvale.ffa.player.PlayerManager;
import net.warvale.ffa.scoreboards.FFAScoreboard;
import org.bukkit.potion.PotionEffect;

public class SessionListener implements Listener {

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

        FFAScoreboard.getInstance().addScoreboard(player);
        FFAScoreboard.getInstance().newScoreboard(player);

        player.teleport(WarvaleFFA.get().getGame().getSpawn());
        KitSelectorGUI.giveKitSelectorItem(player);
        player.setHealth(20);
        for (PotionEffect effect : player.getActivePotionEffects())  // loop thru all active potion effects
            player.removePotionEffect(effect.getType()); // remove potion effects

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

}
