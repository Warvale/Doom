package net.warvale.ffa.tasks;

import net.warvale.ffa.player.FFAPlayer;
import net.warvale.ffa.player.PlayerManager;
import net.warvale.ffa.player.XPManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PlayerlistTask extends BukkitRunnable {

    private static PlayerlistTask instance;

    public static PlayerlistTask getInstance() {
        if (instance == null) {
            instance = new PlayerlistTask();
        }
        return instance;
    }

    @Override
    public void run() {

        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            FFAPlayer ffaPlayer = PlayerManager.getInstance().getFFAPlayer(player.getUniqueId());
            player.setPlayerListName(ChatColor.GRAY+"["+XPManager.getLevelColor(ffaPlayer.getLevel())+ffaPlayer.getLevel()+ChatColor.GRAY+"] " + ChatColor.translateAlternateColorCodes('&', PermissionsEx.getUser(player.getName()).getPrefix()) + player.getName());
            player.setDisplayName(ChatColor.GRAY+"["+ XPManager.getLevelColor(ffaPlayer.getLevel())+ffaPlayer.getLevel()+ChatColor.GRAY+"] " + ChatColor.translateAlternateColorCodes('&', PermissionsEx.getUser(player.getName()).getPrefix()) + player.getName());
        }

    }

}
