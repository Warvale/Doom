package net.warvale.ffa.gui;

import java.util.ArrayList;
import java.util.List;

import net.warvale.ffa.gui.guis.KitSelectorGUI;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import com.google.common.collect.ImmutableList;
import net.warvale.ffa.WarvaleFFA;
import net.warvale.ffa.gui.guis.StatsGUI;


public class GUIManager {
    private final WarvaleFFA plugin;

    /**
     * GUI manager class constructor.
     *
     * @param plugin The main class.
     */
    public GUIManager(WarvaleFFA plugin) {
        this.plugin = plugin;
    }

    private final List<GUI> guis = new ArrayList<>();

    /**
     * Get a GUI by the given name.
     *
     * @param name The name to check with.
     * @return The GUI, null if not found.
     */
    public GUI getGUI(String name) {
        for (GUI gui : guis) {
            if (gui.getName().equalsIgnoreCase(name)) {
                return gui;
            }
        }

        return null;
    }

    /**
     * Get a GUI by the given name.
     *
     * @param clazz The class to check with.
     * @return The GUI, null if not found.
     */
    @SuppressWarnings("unchecked")
    public <T> T getGUI(Class<T> clazz) {
        for (GUI gui : guis) {
            if (gui.getClass().equals(clazz)) {
                return (T) gui;
            }
        }

        return null;
    }

    /**
     * Get a list of all GUI's.
     *
     * @return A list of GUI's.
     */
    public List<GUI> getGUIS() {
        return ImmutableList.copyOf(guis);
    }

    /**
     * Setup all the GUI inventories.
     */
    public void registerGUIs() {
        addGUI(new StatsGUI());
        addGUI(new KitSelectorGUI());
        plugin.getLogger().info("All inventories has been setup.");
    }

    /**
     * Add the given GUI to the storage, call it's setup method and register listeners if any.
     *
     * @param gui The GUI to use.
     */
    private void addGUI(GUI gui) {
        plugin.getLogger().info("Loading " + gui.getName().toLowerCase() + " gui...");
        
        guis.add(gui);
        gui.onSetup();

        if (gui instanceof Listener) {
            Bukkit.getPluginManager().registerEvents((Listener) gui, plugin);
        }
    }
}