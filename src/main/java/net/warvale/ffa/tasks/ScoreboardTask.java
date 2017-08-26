package net.warvale.ffa.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import net.warvale.ffa.WarvaleFFA;
import net.warvale.ffa.game.FFAMode;
import net.warvale.ffa.scoreboards.FFAScoreboard;

public class ScoreboardTask extends BukkitRunnable {

    private static ScoreboardTask instance;

    public static ScoreboardTask getInstance() {
        if (instance == null) {
            instance = new ScoreboardTask();
        }
        return instance;
    }

    @Override
    public void run() {

        for (Player online : Bukkit.getServer().getOnlinePlayers()) {
            FFAScoreboard.getInstance().newScoreboard(online);
        }

    }

}
