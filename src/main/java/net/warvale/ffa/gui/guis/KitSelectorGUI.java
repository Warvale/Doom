package net.warvale.ffa.gui.guis;

import net.warvale.ffa.gui.GUI;
import net.warvale.ffa.kits.*;
import net.warvale.ffa.player.FFAPlayer;
import net.warvale.ffa.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class KitSelectorGUI extends GUI implements Listener {

    public KitSelectorGUI() {
        super("KitSelectorGUI", "A inventory containing a list of kits to choose from!");


    }

    private final Map<String, Inventory> inventories = new HashMap<String, Inventory>();
    private Map<String, Kit> kits = new LinkedHashMap<>();
    private static KitSelectorGUI INSTANCE;

    public static KitSelectorGUI getINSTANCE() {
        return INSTANCE;
    }

    public Map<String, Kit> getKits() {
        return kits;
    }

    @Override
    public void onSetup() {
        INSTANCE = this;
        //Register kits here.

        kits.put("UHC", new UHCKit());
        kits.put("Archer", new ArcherKit());
        kits.put("Pot", new PotKit());
        kits.put("Scout", new ScoutKit());
        kits.put("MCSG", new MCSGKit());
        kits.put("Pyro", new PyroKit());
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
        FFAPlayer ffaPlayer = PlayerManager.getInstance().getFFAPlayer(player.getUniqueId());
        if (kits.size() > event.getSlot() && event.getClick().equals(ClickType.LEFT)) {
            event.getWhoClicked().closeInventory();

            Kit kit = kits.get(inv.getItem(event.getSlot()).getItemMeta().getDisplayName().substring(2));
            if (kit.getName() != "UHC" && !ffaPlayer.hasKit(kit.getName())) {
                // UHC kit is free.
                player.sendMessage(ChatColor.RED + "You don't have that kit! (Right-click to purchase it.)");
                return;
            }
            player.getInventory().clear();
            kit.giveKit(player);
            ffaPlayer.setLastKit(inv.getItem(event.getSlot()).getItemMeta().getDisplayName().substring(2));
            KitManager.setUUID(player.getUniqueId(), kit);


            // Makes all items unbreakable.
            for (int i = 0; i <= 35; i++) {
                ItemStack wow = player.getInventory().getItem(i);
                if (wow == null) return;
                ItemMeta meta = wow.getItemMeta();
                meta.spigot().setUnbreakable(true);
                wow.setItemMeta(meta);
                player.getInventory().setItem(i,wow);
            }
            ItemStack[] armor = player.getInventory().getArmorContents().clone();
            for (int i = 0; i < armor.length; i++) {
                ItemStack dankmemes = armor[i];
                if (dankmemes.getType().equals(Material.AIR)) return;
                ItemMeta meta = dankmemes.getItemMeta();
                meta.spigot().setUnbreakable(true);
                dankmemes.setItemMeta(meta);
            }
            player.getInventory().setArmorContents(armor);



        }


        if (kits.size() > event.getSlot() && event.getClick().equals(ClickType.RIGHT)) {
            event.getWhoClicked().closeInventory();

            Kit kit = kits.get(inv.getItem(event.getSlot()).getItemMeta().getDisplayName().substring(2));
            if (kit.getName().toUpperCase() == "UHC") {
                player.sendMessage(ChatColor.RED + "That kit is free.");
                return;
            }
            if (ffaPlayer.hasKit(kit.getName())) {
                player.sendMessage(ChatColor.RED+"You already have that Kit!");
                return;
            } else if (!ffaPlayer.hasKit(kit.getName())) {
                if (ffaPlayer.getEmbers() < kit.getCost()) {
                    player.sendMessage(ChatColor.RED+"You do not have enough "+ChatColor.GREEN+"Embers"+ChatColor.RED+" to buy this kit! "+ChatColor.GRAY+"(You need "+ String.valueOf(kit.getCost()-ffaPlayer.getEmbers())+" more Embers to buy this kit!");
                return;
                }
                // purchasing the kit here.
                ffaPlayer.addPurcashedKit(kit.getName());
                ffaPlayer.setEmbers(ffaPlayer.getEmbers() - kit.getCost());
                player.sendMessage(ChatColor.AQUA+"You have purchased the "+kit.getName().toUpperCase()+" kit!");
            }

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
            ItemStack kit = new ItemStack(entry.getValue().getIcon(), 1);
            ItemMeta kitMeta = kit.getItemMeta();

            kitMeta.setDisplayName("§a"+entry.getValue().getName());
            List<String> lores = new ArrayList<>();
            lores.add("§cCost: §a"+String.valueOf( entry.getValue().getCost() ));
            kitMeta.setLore(lores);
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
        p.getInventory().setHeldItemSlot(4);
    }

}
