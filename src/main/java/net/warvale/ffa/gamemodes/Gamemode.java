package net.warvale.ffa.gamemodes;

import org.bukkit.event.Listener;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import net.warvale.ffa.WarvaleFFA;
import net.warvale.ffa.kits.FFAKit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public abstract class Gamemode implements Listener {

    protected Random random = new Random();
    private static Map<String, Gamemode> gameModeMap = new HashMap<>();

    private String name;
    private String statsTable;
    private FFAKit kit;

    public Gamemode(String name, String statsTable, FFAKit kit) {
        this.name = name;
        this.statsTable = statsTable;
        this.kit = kit;
        Gamemode.gameModeMap.put(this.name, this);

        //register the game mode as a listener
        WarvaleFFA.registerListener(this);
    }

    /**
     * Do anything special for a death
     *
     * NOTE: Must be able to handle logger npcs.
     */
    public abstract void handleDeath(LivingEntity died);

    /**
     * Return name
     */
    public String getName() {
        return this.name;
    }

    public String getStatsTable() {
         return statsTable;
    }

    /**
     * Default kit
     */
    public FFAKit getKit() {
        return kit;
    }

    public static Gamemode getGamemode(String name) {
        if (!Gamemode.gameModeMap.containsKey(name)) {
            throw new RuntimeException("Invalid game mode requested " + name);
        }

        return Gamemode.gameModeMap.get(name);
    }

}
