package net.warvale.ffa.gui.guis;

import net.warvale.ffa.gui.GUI;
import net.warvale.ffa.kits.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KitSelectorGUI extends GUI implements Listener {

    public KitSelectorGUI() {
        super("KitSelectorGUI", "A inventory containing a list of kits to choose from!");


    }

    private final Map<String, Inventory> inventories = new HashMap<String, Inventory>();
    private Map<String, Kit> kits = new HashMap();
    private static int[] levelupxp = new int[12];

    public static int[] getLevelupxp() {
        return levelupxp;
    }

    @Override
    public void onSetup() {
        levelupxp[0] = 50;
        levelupxp[1] = 150;
        levelupxp[2] = 300;
        levelupxp[3] = 750;
        levelupxp[4] = 1500;
        levelupxp[5] = 4000;
        levelupxp[6] = 10000;
        levelupxp[7] = 25000;
        levelupxp[8] = 75000;
        levelupxp[9] = 150000;
        levelupxp[10] = 250000;
        levelupxp[11] = 500000;
        //Register kits here.

        kits.put("UHC", new UHCKit());
        kits.put("MCSG", new MCSGKit());
        kits.put("Scout", new ScoutKit());
        kits.put("Archer", new ArcherKit());
    }



    @EventHandler
    public void on(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) {
            return;
        }

        Inventory inv = event.getInventory();

        if (!inv.getTitle().endsWith("Kit Selector")) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        if (kits.size() > event.getSlot()) {
            event.getWhoClicked().closeInventory();

            Kit kit = kits.get(inv.getItem(event.getSlot()).getItemMeta().getDisplayName().substring(2));
            player.getInventory().clear();
            kit.giveKit(player);

        }


        event.setCancelled(true);
    }

    /**
     * Get the stats inventory for the given player.
     * @return The inventory.
     */
    public Inventory get(Player p) {
        String name = p.getName();

        if (!inventories.containsKey(name)) {
            inventories.put(name, Bukkit.createInventory(p.getPlayer(), 45, "§8Kit Selector"));
        }

        update(p);

        return inventories.get(name);
    }

    /**
     * Update the stats inventory for the given user.
     *
     */
    public void update(Player p) {
        Inventory inv = inventories.get(p.getName());
        List<String> lore = new ArrayList<>();

        glassify(inv);

        int counter = 0;
        for (Map.Entry<String, Kit> entry : kits.entrySet())
        {
            ItemStack kit = new ItemStack(Material.DIAMOND_SWORD, 1);
            ItemMeta kitMeta = kit.getItemMeta();

            kitMeta.setDisplayName("§a"+entry.getValue().getName());

            kit.setItemMeta(kitMeta);
            inv.setItem(counter, kit);
            counter++;
        }

    }
    public static void giveKitSelectorItem(Player p) {
        ItemStack guiopener = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta meta = guiopener.getItemMeta();
        meta.setDisplayName("§bKit Selector");
        guiopener.setItemMeta(meta);
        p.getInventory().setItem(4,guiopener);
    }

}
