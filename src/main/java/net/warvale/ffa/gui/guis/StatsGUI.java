package net.warvale.ffa.gui.guis;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import net.warvale.ffa.gui.GUI;
import net.warvale.ffa.player.FFAPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatsGUI extends GUI implements Listener {

    public StatsGUI() {
        super("Stats", "A inventory containing stats for a user.");
    }

    private final Map<String, Inventory> inventories = new HashMap<String, Inventory>();

    @Override
    public void onSetup() {}

    @EventHandler
    public void on(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) {
            return;
        }

        Inventory inv = event.getInventory();

        if (!inv.getTitle().endsWith("'s Stats")) {
            return;
        }

        event.setCancelled(true);
    }

    /**
     * Get the stats inventory for the given player.
     *
     * @param ffaPlayer The stats owner.
     * @return The inventory.
     */
    public Inventory get(FFAPlayer ffaPlayer) {
        String name = ffaPlayer.getName();

        if (!inventories.containsKey(name)) {
            inventories.put(name, Bukkit.createInventory(ffaPlayer.getPlayer(), 45, "§4" + name + "'s Stats"));
        }

        update(ffaPlayer);

        return inventories.get(name);
    }

    /**
     * Update the stats inventory for the given user.
     *
     * @param ffaPlayer The inventory owner.
     */
    public void update(FFAPlayer ffaPlayer) {
        Inventory inv = inventories.get(ffaPlayer.getName());
        List<String> lore = new ArrayList<>();

        glassify(inv);

        //Total Kills
        ItemStack kills = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta killsMeta = kills.getItemMeta();

        killsMeta.setDisplayName("§8» §7Kills:§a " + ffaPlayer.getTotalKills() + " §8«");

        kills.setItemMeta(killsMeta);
        inv.setItem(0, kills);

        //KDR
        ItemStack kdr = new ItemStack(Material.DIAMOND_HOE, 1);
        ItemMeta kdrMeta = kdr.getItemMeta();

        kdrMeta.setDisplayName("§8» §7KDR:§a " + ffaPlayer.getKd() + " §8«");

        kdr.setItemMeta(kdrMeta);
        inv.setItem(1, kdr);

        //Total Deaths
        ItemStack deaths = new ItemStack(Material.TNT, 1);
        ItemMeta dethsMeta = deaths.getItemMeta();

        dethsMeta.setDisplayName("§8» §7Deaths:§a " + ffaPlayer.getTotalDeaths() + " §8«");

        deaths.setItemMeta(dethsMeta);
        inv.setItem(2, deaths);

        //Embers
        ItemStack embers = new ItemStack(Material.EMERALD, 1);
        ItemMeta embersMeta = embers.getItemMeta();

        embersMeta.setDisplayName("§8» §7Embers:§a " + ffaPlayer.getEmbers() + " §8«");

        embers.setItemMeta(embersMeta);
        inv.setItem(3, embers);

    }

}
