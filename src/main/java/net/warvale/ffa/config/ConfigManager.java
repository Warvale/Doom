package net.warvale.ffa.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import net.warvale.ffa.WarvaleFFA;
import net.warvale.ffa.utils.FileUtils;

import java.io.File;

public class ConfigManager {

    private static ConfigManager instance;

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    //config
    private static FileConfiguration config;
    private File configFile;

    public void setup() {

        //copy default config file
        WarvaleFFA.get().getLogger().info("Loading configs");

        try {

            if (!WarvaleFFA.get().getDataFolder().exists()) {
                WarvaleFFA.get().getDataFolder().mkdir();
            }

            configFile = new File(WarvaleFFA.get().getDataFolder(), "config.yml");
            if (!configFile.exists()) {
                //copy default config file and defaults
                FileUtils.loadFile("config.yml");
            }

            reloadConfig();

        } catch (Exception ex) {
            ex.printStackTrace();
            WarvaleFFA.get().getLogger().severe("Could not load configs");
        }

    }

    public static FileConfiguration getConfig() {
        return config;
    }


    public void reloadConfig() {
        try {

            config = new YamlConfiguration();
            config.load(configFile);

        } catch (Exception ex) {
            WarvaleFFA.get().getLogger().severe("Could not reload config: " + configFile.getName());
        }
    }

    public void saveConfig() {
        try {
            config.save(configFile);
        } catch (Exception ex) {
            WarvaleFFA.get().getLogger().severe("Could not save config: " + configFile.getName());
        }
    }

}
