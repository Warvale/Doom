package net.warvale.ffa.gui.guis;

import com.comphenix.protocol.PacketType;
import net.warvale.ffa.gui.GUI;
import net.warvale.ffa.gui.GUIManager;
import net.warvale.ffa.kits.ExampleKit;
import net.warvale.ffa.kits.Kit;
import net.warvale.ffa.player.FFAPlayer;
import net.warvale.ffa.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class KitSelectorGUI extends GUI implements Listener {

    public KitSelectorGUI() {
        super("KitSelectorGUI", "A inventory containing a list of kits to choose from!");


    }

    private final Map<String, Inventory> inventories = new HashMap<String, Inventory>();
    private Map<String, Kit> kits = new HashMap();
    @Override
    public void onSetup() {
        //Register kits here.

        kits.put("Example", new ExampleKit());
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
        if (kits.size() >= event.getSlot()) {
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
