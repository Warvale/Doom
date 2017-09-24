package net.warvale.ffa.scoreboards;

import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import net.warvale.ffa.player.FFAPlayer;
import net.warvale.ffa.player.PlayerManager;

import java.util.Map;
import java.util.UUID;

public class FFAScoreboard {

    private static FFAScoreboard instance;

    public static FFAScoreboard getInstance() {
        if (instance == null) {
            instance = new FFAScoreboard();
        }
        return instance;
    }

    //scoreboard map
    private Map<UUID, Scoreboard> scoreboards = Maps.newHashMap();

    public Map<UUID, Scoreboard> getScoreboards() {
        return scoreboards;
    }

    public void addScoreboard(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("game", "dummy");

        objective.setDisplayName(ChatColor.DARK_GRAY + "» " + ChatColor.RED + ChatColor.BOLD + "Doom" + ChatColor.DARK_GRAY + " «" );
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Team players = scoreboard.registerNewTeam("Players");
        players.addEntry("§bPlayers:");
        players.setSuffix(" §7");

        Team kills = scoreboard.registerNewTeam("Level");
        kills.addEntry("§bLevel:");
        kills.setSuffix(" §7");

        Team deaths = scoreboard.registerNewTeam("Embers");
        deaths.addEntry("§bEmbers:");
        deaths.setSuffix(" §7");

        Team killStreak = scoreboard.registerNewTeam("KillStreak");
        killStreak.addEntry("§bKill Streak:");
        killStreak.setSuffix(" §7");

        Team totalKills = scoreboard.registerNewTeam("TotalKills");
        totalKills.addEntry("§bTotal Kills:");
        totalKills.setSuffix(" §7");

        Team totalDeaths = scoreboard.registerNewTeam("TotalDeaths");
        totalDeaths.addEntry("§bTotal Deaths:");
        totalDeaths.setSuffix(" §7");

        Team highestKS = scoreboard.registerNewTeam("HighestKS");
        highestKS.addEntry("§bKill St");
        highestKS.setSuffix("reak: §7");


        scoreboards.put(player.getUniqueId(), scoreboard);
    }

    public void removeScoreboard(Player player) {
        if (scoreboards.containsKey(player.getUniqueId())) {
            scoreboards.get(player.getUniqueId()).clearSlot(DisplaySlot.SIDEBAR);
            scoreboards.remove(player.getUniqueId());
        }
    }

    public void shutdown() {
        for (Scoreboard scoreboard : scoreboards.values()) {
            for (Objective objective : scoreboard.getObjectives()) {
                objective.unregister();
            }
            scoreboard.clearSlot(DisplaySlot.SIDEBAR);
        }
        scoreboards.clear();
    }

    public void newScoreboard(Player p) {

        //add the user to the scoreboard
        if (!getScoreboards().containsKey(p.getUniqueId())) {
            addScoreboard(p);
        }

        //update the scoreboard
        updatePlayers();
        updateLevel();
        updateEmbers();
        updateKillStreak();
        updateTotalKills();
        updateTotalDeaths();
        updateHighestKS();
        updateWebsite();


        p.setScoreboard(getScoreboards().get(p.getUniqueId()));
    }

    public void updatePlayers() {

        for (Player online : Bukkit.getServer().getOnlinePlayers()) {

            Objective objective = getScoreboards().get(online.getUniqueId()).getObjective("game");
            Team players = getScoreboards().get(online.getUniqueId()).getTeam("Players");
            if (objective != null && players != null) {

                players.setSuffix(" §7" + String.valueOf(Bukkit.getServer().getOnlinePlayers().size()));

                objective.getScore("§bPlayers:").setScore(13);

            }

        }

    }

    public void updateLevel() {

        for (Player online : Bukkit.getServer().getOnlinePlayers()) {

            FFAPlayer ffaPlayer = PlayerManager.getInstance().getFFAPlayer(online.getUniqueId());

            Objective objective = getScoreboards().get(online.getUniqueId()).getObjective("game");
            Team level = getScoreboards().get(online.getUniqueId()).getTeam("Level");
            if (objective != null && level != null) {
                objective.getScore("    ").setScore(12);

                level.setSuffix(" §7" + String.valueOf(ffaPlayer.getLevel()) + "§8/§7"+String.valueOf(ffaPlayer.getXPtoNextLevel()));

                objective.getScore("§bLevel:").setScore(11);

            }

        }

    }

    public void updateEmbers() {

        for (Player online : Bukkit.getServer().getOnlinePlayers()) {

            FFAPlayer ffaPlayer = PlayerManager.getInstance().getFFAPlayer(online.getUniqueId());

            Objective objective = getScoreboards().get(online.getUniqueId()).getObjective("game");
            Team embers = getScoreboards().get(online.getUniqueId()).getTeam("Embers");
            if (objective != null && embers != null) {

                embers.setSuffix(" §7" + String.valueOf(ffaPlayer.getEmbers()));

                objective.getScore("§bEmbers:").setScore(10);

            }

        }

    }

    public void updateKillStreak() {

        for (Player online : Bukkit.getServer().getOnlinePlayers()) {

            FFAPlayer ffaPlayer = PlayerManager.getInstance().getFFAPlayer(online.getUniqueId());

            Objective objective = getScoreboards().get(online.getUniqueId()).getObjective("game");
            Team killStreak = getScoreboards().get(online.getUniqueId()).getTeam("KillStreak");
            if (objective != null && killStreak != null) {

                killStreak.setSuffix(" §7" + String.valueOf(ffaPlayer.getKillStreak()));

                objective.getScore("§bKill Streak:").setScore(9);

            }

        }

    }

    public void updateTotalKills() {

        for (Player online : Bukkit.getServer().getOnlinePlayers()) {

            FFAPlayer ffaPlayer = PlayerManager.getInstance().getFFAPlayer(online.getUniqueId());

            Objective objective = getScoreboards().get(online.getUniqueId()).getObjective("game");
            Team totalKills = getScoreboards().get(online.getUniqueId()).getTeam("TotalKills");
            if (objective != null && totalKills != null) {
                objective.getScore("   ").setScore(8);


                totalKills.setSuffix(" §7" + String.valueOf(ffaPlayer.getTotalKills()));

                objective.getScore("§bTotal Kills:").setScore(7);

            }

        }

    }

    public void updateTotalDeaths() {

        for (Player online : Bukkit.getServer().getOnlinePlayers()) {

            FFAPlayer ffaPlayer = PlayerManager.getInstance().getFFAPlayer(online.getUniqueId());

            Objective objective = getScoreboards().get(online.getUniqueId()).getObjective("game");
            Team totalDeaths = getScoreboards().get(online.getUniqueId()).getTeam("TotalDeaths");
            if (objective != null && totalDeaths != null) {

                totalDeaths.setSuffix(" §7" + String.valueOf(ffaPlayer.getTotalDeaths()));

                objective.getScore("§bTotal Deaths:").setScore(6);

            }

        }

    }

    public void updateHighestKS() {

        for (Player online : Bukkit.getServer().getOnlinePlayers()) {

            FFAPlayer ffaPlayer = PlayerManager.getInstance().getFFAPlayer(online.getUniqueId());

            Objective objective = getScoreboards().get(online.getUniqueId()).getObjective("game");
            Team highestKS = getScoreboards().get(online.getUniqueId()).getTeam("HighestKS");
            if (objective != null && highestKS != null) {
                objective.getScore("  ").setScore(5);
                objective.getScore("§bHighest").setScore(4);

                highestKS.setSuffix("reak: §7" + String.valueOf(ffaPlayer.getHighestKillStreak()));

                objective.getScore("§bKill St").setScore(3);

            }

        }

    }


    public void updateWebsite() {

        for (Player online : Bukkit.getServer().getOnlinePlayers()) {

            Objective objective = getScoreboards().get(online.getUniqueId()).getObjective("game");
            if (objective != null) {
                objective.getScore(" ").setScore(2);

                objective.getScore("§bwarvale.net").setScore(1);

            }

        }

    }


}
