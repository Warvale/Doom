package net.warvale.ffa;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import net.warvale.ffa.commands.CommandHandler;
import net.warvale.ffa.config.ConfigManager;
import net.warvale.ffa.config.DatabaseCredentials;
import net.warvale.ffa.data.StorageBackend;
import net.warvale.ffa.game.Game;
import net.warvale.ffa.gui.GUIManager;
import net.warvale.ffa.listeners.*;
import net.warvale.ffa.message.MessageManager;
import net.warvale.ffa.scoreboards.FFAScoreboard;
import net.warvale.ffa.tasks.ScoreboardTask;
import java.util.logging.Level;

public class WarvaleFFA extends JavaPlugin {

    private static boolean shutdown = false;

    private static WarvaleFFA instance;
    private CommandHandler commandHandler;
    private Game game;
    private GUIManager gui;

    private static StorageBackend storageBackend;

    public void onEnable() {
        instance = this;

        ConfigManager.getInstance().setup();
        MessageManager.getInstance().setup();

        registerListeners(Bukkit.getPluginManager());

        //setup storage backend
        storageBackend = new StorageBackend(new DatabaseCredentials(
                ConfigManager.getConfig().getString("database.host"),
                ConfigManager.getConfig().getInt("database.port"),
                ConfigManager.getConfig().getString("database.user"),
                ConfigManager.getConfig().getString("database.pass"),
                ConfigManager.getConfig().getString("database.dbName")
                ));

        //load our game manager
        game = new Game();

        //setup the gui
        gui = new GUIManager(this);
        gui.registerGUIs();

        //register commands
        commandHandler = new CommandHandler(this);
        commandHandler.registerCommands(gui);

        ScoreboardTask.getInstance().runTaskTimer(this, 20, 20);

        try {
            game.getFFAWorld().setGameRuleValue("naturalRegeneration", "false");
            game.getFFAWorld().setGameRuleValue("doMobSpawning", "false");
            game.getFFAWorld().setGameRuleValue("doDaylightCycle", "false");
            game.getFFAWorld().setAutoSave(false);
        } catch (Exception ex) {
            getLogger().log(Level.SEVERE, "arena world does not exist or could not be found!");
        }

        /*if (getGame().isStatsEnabled()) {
            DatabaseUtils.loadTables();
        }*/
    }

    public void onDisable() {
        FFAScoreboard.getInstance().shutdown();
        getStorageBackend().closeConnections();
    }

    public static WarvaleFFA get() {
        return instance;
    }

    private void registerListeners(PluginManager pm) {

        pm.registerEvents(ChatListener.getInstance(), this);
        pm.registerEvents(new SessionListener(), this);
        pm.registerEvents(new DeathListener(), this);
        pm.registerEvents(new CommandListener(), this);
        pm.registerEvents(new WorldListener(this), this);

    }

    public Game getGame() {
        return game;
    }


    public void endSetup(String s) {
        getLogger().log(Level.SEVERE, s);
        if (!shutdown) {
            stop();
            shutdown = true;
        }
        throw new IllegalArgumentException("Disabling... " + s);
    }

    private void stop() {
        Bukkit.getServer().shutdown();
    }

    public GUIManager getGUI() {
        return gui;
    }

    public static StorageBackend getStorageBackend() {
        return storageBackend;
    }

    public static void registerListener(Listener l) {
        Bukkit.getPluginManager().registerEvents(l, instance);
    }

}
