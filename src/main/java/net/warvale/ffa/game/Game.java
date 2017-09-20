package net.warvale.ffa.game;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import net.warvale.ffa.config.ConfigManager;
import net.warvale.ffa.message.MessageManager;
import net.warvale.ffa.message.PrefixType;
import net.warvale.ffa.utils.DatabaseUtils;

public class Game {

    private FFAMode ffaMode;
    private String worldName;
    private int maxPlayers;
    private boolean stats;

    public Game() {

        try {
            ffaMode = FFAMode.valueOf(ConfigManager.getConfig().getString("game.type"));
        } catch (Exception ex) {
            Bukkit.getLogger().warning("Setting the FFAMode to NONE as it can't find the saved one!");
            setFFAMode(FFAMode.NONE);
        }


        this.worldName = ConfigManager.getConfig().getString("settings.world", "arena");
        this.maxPlayers = ConfigManager.getConfig().getInt("settings.maxPlayers", 100);
        this.stats = ConfigManager.getConfig().getBoolean("settings.stats", true);


            DatabaseUtils.setTable("uhcffa_stats");

    }

    public FFAMode getFFAMode() {
        return ffaMode;
    }

    public void setFFAMode(FFAMode ffaMode) {
        this.ffaMode = ffaMode;

        ConfigManager.getConfig().set("game.type", ffaMode.toString());
        ConfigManager.getInstance().saveConfig();
    }

    public boolean isFFAMode(FFAMode ffaMode) {
        return getFFAMode().equals(ffaMode);
    }



    public String getWorldName() {
        return worldName;
    }

    public void setWorldName(String worldName) {
        this.worldName = worldName;

        ConfigManager.getConfig().set("settings.world", worldName);
        ConfigManager.getInstance().saveConfig();
    }

    public World getFFAWorld() {
        return Bukkit.getWorld(worldName);
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;

        ConfigManager.getConfig().set("settings.maxPlayers", maxPlayers);
        ConfigManager.getInstance().saveConfig();
    }

    public boolean isStatsEnabled() {
        return stats;
    }

    public void toggleStats() {
        if (isStatsEnabled()) {

            //disable stats
            setStats(false);

            MessageManager.broadcast(MessageManager.getPrefix(PrefixType.FFA) + "Stats are now disabled!");

        } else {

            //enable stats
           setStats(true);

            MessageManager.broadcast(MessageManager.getPrefix(PrefixType.FFA) + "Stats are now enabled!");
        }
    }

    public void setStats(boolean enable) {
        stats = enable;

        ConfigManager.getConfig().set("settings.stats", enable);
        ConfigManager.getInstance().saveConfig();
    }


    /**
     * Get the spawnpoint of the arena.
     *
     * @return The spawnpoint of the arena.
     */
    public Location getSpawn() {
        FileConfiguration config = ConfigManager.getConfig();

        World world = Bukkit.getWorld(config.getString("spawn.world", getWorldName()));

        if (world == null) {
            world = Bukkit.getWorlds().get(0);
        }

        double x = config.getDouble("spawn.x", 0);
        double y = config.getDouble("spawn.y", 51);
        double z = config.getDouble("spawn.z", 0);
        float yaw = (float) config.getDouble("spawn.yaw", 0);
        float pitch = (float) config.getDouble("spawn.pitch", 0);

        Location loc = new Location(world, x, y, z, yaw, pitch);
        return loc;
    }

}
