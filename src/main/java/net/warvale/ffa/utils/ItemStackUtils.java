package net.warvale.ffa.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemStackUtils {

    // Leather armor
    public static ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET);
    public static ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE);
    public static ItemStack LEATHER_LEGGINGS = new ItemStack(Material.LEATHER_LEGGINGS);
    public static ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS);

    // Chainmail armor
    public static ItemStack CHAINMAIL_HELMET = new ItemStack(Material.CHAINMAIL_HELMET);
    public static ItemStack CHAINMAIL_CHESTPLATE = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
    public static ItemStack CHAINMAIL_LEGGINGS = new ItemStack(Material.CHAINMAIL_LEGGINGS);
    public static ItemStack CHAINMAIL_BOOTS = new ItemStack(Material.CHAINMAIL_BOOTS);

    // Iron armor
    public static ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET);
    public static ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE);
    public static ItemStack IRON_LEGGINGS = new ItemStack(Material.IRON_LEGGINGS);
    public static ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS);

    // Gold armor
    public static ItemStack GOLD_HELMET = new ItemStack(Material.GOLD_HELMET);
    public static ItemStack GOLD_CHESTPLATE = new ItemStack(Material.GOLD_CHESTPLATE);
    public static ItemStack GOLD_LEGGINGS = new ItemStack(Material.GOLD_LEGGINGS);
    public static ItemStack GOLD_BOOTS = new ItemStack(Material.GOLD_BOOTS);

    // Diamond armor
    public static ItemStack DIAMOND_HELMET = new ItemStack(Material.DIAMOND_HELMET);
    public static ItemStack DIAMOND_CHESTPLATE = new ItemStack(Material.DIAMOND_CHESTPLATE);
    public static ItemStack DIAMOND_LEGGINGS = new ItemStack(Material.DIAMOND_LEGGINGS);
    public static ItemStack DIAMOND_BOOTS = new ItemStack(Material.DIAMOND_BOOTS);

    // Weapons
    public static ItemStack BOW = new ItemStack(Material.BOW);
    public static ItemStack ARROW = new ItemStack(Material.ARROW);
    public static ItemStack FISHING_ROD = new ItemStack(Material.FISHING_ROD);
    public static ItemStack WOOD_AXE = new ItemStack(Material.WOOD_AXE);
    public static ItemStack WOOD_SWORD = new ItemStack(Material.WOOD_SWORD);
    public static ItemStack STONE_AXE = new ItemStack(Material.STONE_AXE);
    public static ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD);
    public static ItemStack IRON_AXE = new ItemStack(Material.IRON_AXE);
    public static ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD);
    public static ItemStack GOLD_AXE = new ItemStack(Material.GOLD_AXE);
    public static ItemStack GOLD_SWORD = new ItemStack(Material.GOLD_SWORD);
    public static ItemStack DIAMOND_AXE = new ItemStack(Material.DIAMOND_AXE);
    public static ItemStack DIAMOND_SWORD = new ItemStack(Material.DIAMOND_SWORD);

    // Special consumables
    public static ItemStack ENDER_PEARL = new ItemStack(Material.ENDER_PEARL);
    public static ItemStack GOLDEN_APPLE = new ItemStack(Material.GOLDEN_APPLE);
    public static ItemStack GOD_APPLE = new ItemStack(Material.GOLDEN_APPLE, 1, (short) 1);
    public static ItemStack MILK_BUCKET = new ItemStack(Material.MILK_BUCKET);

    // Food
    public static ItemStack APPLE = new ItemStack(Material.APPLE);
    public static ItemStack BAKED_POTATO = new ItemStack(Material.BAKED_POTATO);
    public static ItemStack BREAD = new ItemStack(Material.BREAD);
    public static ItemStack COOKED_BEEF = new ItemStack(Material.COOKED_BEEF);
    public static ItemStack COOKED_CHICKEN = new ItemStack(Material.COOKED_CHICKEN);
    public static ItemStack COOKED_FISH = new ItemStack(Material.COOKED_FISH);
    public static ItemStack COOKIE = new ItemStack(Material.COOKIE);
    public static ItemStack GRILLED_PORK = new ItemStack(Material.GRILLED_PORK);
    public static ItemStack MELON = new ItemStack(Material.MELON);
    public static ItemStack PUMPKIN_PIE = new ItemStack(Material.PUMPKIN_PIE);
    public static ItemStack GOLDEN_CARROT = new ItemStack(Material.GOLDEN_CARROT);
    public static ItemStack MUSHROOM_SOUP = new ItemStack(Material.MUSHROOM_SOUP);

    // Misc
    public static ItemStack EMPTY_ITEM = new ItemStack(Material.AIR);

    // Drinkable potions
    public static ItemStack POTION;
    public static ItemStack REGENERATION_POTION;
    public static ItemStack SWIFTNESS_POTION;
    public static ItemStack FIRE_RESISTANCE_POTION;
    public static ItemStack POISON_POTION;
    public static ItemStack HEALING_POTION;
    public static ItemStack NIGHT_VISION_POTION;
    public static ItemStack WEAKNESS_POTION;
    public static ItemStack STRENGTH_POTION;
    public static ItemStack LEAPING_POTION;
    public static ItemStack SLOWNESS_POTION;
    public static ItemStack HARMING_POTION;
    public static ItemStack WATER_BREATHING_POTION;
    public static ItemStack INVISIBILITY_POTION;
    public static ItemStack REGENERATION_POTION_II;
    public static ItemStack SWIFTNESS_POTION_II;
    public static ItemStack POISON_POTION_II;
    public static ItemStack HEALING_POTION_II;
    public static ItemStack STRENGTH_POTION_II;
    public static ItemStack LEAPING_POTION_II;
    public static ItemStack HARMING_POTION_II;
    public static ItemStack REGENERATION_POTION_EXT;
    public static ItemStack SWIFTNESS_POTION_EXT;
    public static ItemStack FIRE_RESISTANCE_POTION_EXT;
    public static ItemStack POISON_POTION_EXT;
    public static ItemStack NIGHT_VISION_POTION_EXT;
    public static ItemStack WEAKNESS_POTION_EXT;
    public static ItemStack STRENGTH_POTION_EXT;
    public static ItemStack SLOWNESS_POTION_EXT;
    public static ItemStack LEAPING_POTION_EXT;
    public static ItemStack WATER_BREATHING_POTION_EXT;
    public static ItemStack INVISIBILITY_POTION_EXT;
    public static ItemStack REGENERATION_POTION_II_EXT;
    public static ItemStack SWIFTNESS_POTION_II_EXT;
    public static ItemStack POISON_POTION_II_EXT;
    public static ItemStack STRENGTH_POTION_II_EXT;

    // Reference http://minecraft.gamepedia.com/Potion#DataSource
    static {
        POTION = new ItemStack(Material.POTION);
        REGENERATION_POTION = new ItemStack(Material.POTION, 1, (short) 8193);
        SWIFTNESS_POTION = new ItemStack(Material.POTION, 1, (short) 8194);
        FIRE_RESISTANCE_POTION = new ItemStack(Material.POTION, 1, (short) 8195);
        POISON_POTION = new ItemStack(Material.POTION, 1, (short) 8196);
        HEALING_POTION = new ItemStack(Material.POTION, 1, (short) 8197);
        NIGHT_VISION_POTION = new ItemStack(Material.POTION, 1, (short) 8198);
        WEAKNESS_POTION = new ItemStack(Material.POTION, 1, (short) 8200);
        STRENGTH_POTION = new ItemStack(Material.POTION, 1, (short) 8201);
        LEAPING_POTION = new ItemStack(Material.POTION, 1, (short) 8202);
        SLOWNESS_POTION = new ItemStack(Material.POTION, 1, (short) 8203);
        HARMING_POTION = new ItemStack(Material.POTION, 1, (short) 8204);
        WATER_BREATHING_POTION = new ItemStack(Material.POTION, 1, (short) 8205);
        INVISIBILITY_POTION = new ItemStack(Material.POTION, 1, (short) 8206);
        REGENERATION_POTION_II = new ItemStack(Material.POTION, 1, (short) 8225);
        SWIFTNESS_POTION_II = new ItemStack(Material.POTION, 1, (short) 8226);
        POISON_POTION_II = new ItemStack(Material.POTION, 1, (short) 8228);
        HEALING_POTION_II = new ItemStack(Material.POTION, 1, (short) 8229);
        STRENGTH_POTION_II = new ItemStack(Material.POTION, 1, (short) 8233);
        LEAPING_POTION_II = new ItemStack(Material.POTION, 1, (short) 8235);
        HARMING_POTION_II = new ItemStack(Material.POTION, 1, (short) 8236);
        REGENERATION_POTION_EXT = new ItemStack(Material.POTION, 1, (short) 8257);
        SWIFTNESS_POTION_EXT = new ItemStack(Material.POTION, 1, (short) 8258);
        FIRE_RESISTANCE_POTION_EXT = new ItemStack(Material.POTION, 1, (short) 8259);
        POISON_POTION_EXT = new ItemStack(Material.POTION, 1, (short) 8260);
        NIGHT_VISION_POTION_EXT = new ItemStack(Material.POTION, 1, (short) 8262);
        WEAKNESS_POTION_EXT = new ItemStack(Material.POTION, 1, (short) 8264);
        STRENGTH_POTION_EXT = new ItemStack(Material.POTION, 1, (short) 8265);
        SLOWNESS_POTION_EXT = new ItemStack(Material.POTION, 1, (short) 8266);
        LEAPING_POTION_EXT = new ItemStack(Material.POTION, 1, (short) 8267);
        WATER_BREATHING_POTION_EXT = new ItemStack(Material.POTION, 1, (short) 8269);
        INVISIBILITY_POTION_EXT = new ItemStack(Material.POTION, 1, (short) 8270);
        REGENERATION_POTION_II_EXT = new ItemStack(Material.POTION, 1, (short) 8289);
        SWIFTNESS_POTION_II_EXT = new ItemStack(Material.POTION, 1, (short) 8290);
        POISON_POTION_II_EXT = new ItemStack(Material.POTION, 1, (short) 8292);
        STRENGTH_POTION_II_EXT = new ItemStack(Material.POTION, 1, (short) 8297);
    }

    // Splash potions
    public static ItemStack REGENERATION_SPLASH;
    public static ItemStack SWIFTNESS_SPLASH;
    public static ItemStack FIRE_RESISTANCE_SPLASH;
    public static ItemStack POISON_SPLASH;
    public static ItemStack HEALING_SPLASH;
    public static ItemStack NIGHT_VISION_SPLASH;
    public static ItemStack WEAKNESS_SPLASH;
    public static ItemStack STRENGTH_SPLASH;
    public static ItemStack SLOWNESS_SPLASH;
    public static ItemStack HARMING_SPLASH;
    public static ItemStack BREATHING_SPLASH;
    public static ItemStack INVISIBILITY_SPLASH;
    public static ItemStack REGENERATION_SPLASH_II;
    public static ItemStack SWIFTNESS_SPLASH_II;
    public static ItemStack POISON_SPLASH_II;
    public static ItemStack HEALING_SPLASH_II;
    public static ItemStack STRENGTH_SPLASH_II;
    public static ItemStack LEAPING_SPLASH_II;
    public static ItemStack HARMING_SPLASH_II;
    public static ItemStack REGENERATION_SPLASH_EXT;
    public static ItemStack SWIFTNESS_SPLASH_EXT;
    public static ItemStack FIRE_RESISTANCE_SPLASH_EXT;
    public static ItemStack POISON_SPLASH_EXT;
    public static ItemStack NIGHT_VISION_SPLASH_EXT;
    public static ItemStack WEAKNESS_SPLASH_EXT;
    public static ItemStack STRENGTH_SPLASH_EXT;
    public static ItemStack SLOWNESS_SPLASH_EXT;
    public static ItemStack LEAPING_SPLASH_EXT;
    public static ItemStack BREATHING_SPLASH_EXT;
    public static ItemStack INVISIBILITY_SPLASH_EXT;
    public static ItemStack REGENERATION_SPLASH_II_EXT;
    public static ItemStack POISON_SPLASH_II_EXT;
    public static ItemStack STRENGTH_SPLASH_II_EXT;

    // Reference http://minecraft.gamepedia.com/Potion#DataSource
    static {
        REGENERATION_SPLASH = new ItemStack(Material.POTION, 1, (short) 16385);
        SWIFTNESS_SPLASH = new ItemStack(Material.POTION, 1, (short) 16386);
        FIRE_RESISTANCE_SPLASH = new ItemStack(Material.POTION, 1, (short) 16387);
        POISON_SPLASH = new ItemStack(Material.POTION, 1, (short) 16388);
        HEALING_SPLASH = new ItemStack(Material.POTION, 1, (short) 16389);
        NIGHT_VISION_SPLASH = new ItemStack(Material.POTION, 1, (short) 16390);
        WEAKNESS_SPLASH = new ItemStack(Material.POTION, 1, (short) 16392);
        STRENGTH_SPLASH = new ItemStack(Material.POTION, 1, (short) 16393);
        SLOWNESS_SPLASH = new ItemStack(Material.POTION, 1, (short) 16394);
        HARMING_SPLASH = new ItemStack(Material.POTION, 1, (short) 16396);
        BREATHING_SPLASH = new ItemStack(Material.POTION, 1, (short) 16397);
        INVISIBILITY_SPLASH = new ItemStack(Material.POTION, 1, (short) 16398);
        REGENERATION_SPLASH_II = new ItemStack(Material.POTION, 1, (short) 16417);
        SWIFTNESS_SPLASH_II = new ItemStack(Material.POTION, 1, (short) 16418);
        POISON_SPLASH_II = new ItemStack(Material.POTION, 1, (short) 16420);
        HEALING_SPLASH_II = new ItemStack(Material.POTION, 1, (short) 16421);
        STRENGTH_SPLASH_II = new ItemStack(Material.POTION, 1, (short) 16425);
        LEAPING_SPLASH_II = new ItemStack(Material.POTION, 1, (short) 16427);
        HARMING_SPLASH_II = new ItemStack(Material.POTION, 1, (short) 16428);
        REGENERATION_SPLASH_EXT = new ItemStack(Material.POTION, 1, (short) 16449);
        SWIFTNESS_SPLASH_EXT = new ItemStack(Material.POTION, 1, (short) 16450);
        FIRE_RESISTANCE_SPLASH_EXT = new ItemStack(Material.POTION, 1, (short) 16451);
        POISON_SPLASH_EXT = new ItemStack(Material.POTION, 1, (short) 16452);
        NIGHT_VISION_SPLASH_EXT = new ItemStack(Material.POTION, 1, (short) 16454);
        WEAKNESS_SPLASH_EXT = new ItemStack(Material.POTION, 1, (short) 16456);
        STRENGTH_SPLASH_EXT = new ItemStack(Material.POTION, 1, (short) 16457);
        SLOWNESS_SPLASH_EXT = new ItemStack(Material.POTION, 1, (short) 16458);
        LEAPING_SPLASH_EXT = new ItemStack(Material.POTION, 1, (short) 16459);
        BREATHING_SPLASH_EXT = new ItemStack(Material.POTION, 1, (short) 16461);
        INVISIBILITY_SPLASH_EXT = new ItemStack(Material.POTION, 1, (short) 16481);
        REGENERATION_SPLASH_II_EXT = new ItemStack(Material.POTION, 1, (short) 16482);
        POISON_SPLASH_II_EXT = new ItemStack(Material.POTION, 1, (short) 16484);
        STRENGTH_SPLASH_II_EXT = new ItemStack(Material.POTION, 1, (short) 16489);
    }

    // Lingering potions
    public static ItemStack POISON_LINGERING;

    public static ItemStack[] LEATHER_ARMOR() {
        return new ItemStack[]{ItemStackUtils.LEATHER_HELMET, ItemStackUtils.LEATHER_CHESTPLATE,
                ItemStackUtils.LEATHER_LEGGINGS, ItemStackUtils.LEATHER_BOOTS};
    }

    public static ItemStack[] CHAINMAIL_ARMOR() {
        return new ItemStack[]{ItemStackUtils.CHAINMAIL_HELMET, ItemStackUtils.CHAINMAIL_CHESTPLATE,
                ItemStackUtils.CHAINMAIL_LEGGINGS, ItemStackUtils.CHAINMAIL_BOOTS};
    }

    public static ItemStack[] IRON_ARMOR() {
        return new ItemStack[]{ItemStackUtils.IRON_HELMET, ItemStackUtils.IRON_CHESTPLATE,
                ItemStackUtils.IRON_LEGGINGS, ItemStackUtils.IRON_BOOTS};
    }

    public static ItemStack[] GOLD_ARMOR() {
        return new ItemStack[]{ItemStackUtils.GOLD_HELMET, ItemStackUtils.GOLD_CHESTPLATE,
                ItemStackUtils.GOLD_LEGGINGS, ItemStackUtils.GOLD_BOOTS};
    }

    public static ItemStack[] DIAMOND_ARMOR() {
        return new ItemStack[]{ItemStackUtils.DIAMOND_HELMET, ItemStackUtils.DIAMOND_CHESTPLATE,
                ItemStackUtils.DIAMOND_LEGGINGS, ItemStackUtils.DIAMOND_BOOTS};
    }

    public static ItemStack[] ALL_ARMOR() {
        return new ItemStack[]{ItemStackUtils.LEATHER_HELMET, ItemStackUtils.LEATHER_CHESTPLATE,
                ItemStackUtils.LEATHER_LEGGINGS, ItemStackUtils.LEATHER_BOOTS,
                ItemStackUtils.CHAINMAIL_HELMET, ItemStackUtils.CHAINMAIL_CHESTPLATE,
                ItemStackUtils.CHAINMAIL_LEGGINGS, ItemStackUtils.CHAINMAIL_BOOTS,
                ItemStackUtils.IRON_HELMET, ItemStackUtils.IRON_CHESTPLATE,
                ItemStackUtils.IRON_LEGGINGS, ItemStackUtils.IRON_BOOTS,
                ItemStackUtils.GOLD_HELMET, ItemStackUtils.GOLD_CHESTPLATE,
                ItemStackUtils.GOLD_LEGGINGS, ItemStackUtils.GOLD_BOOTS,
                ItemStackUtils.DIAMOND_HELMET, ItemStackUtils.DIAMOND_CHESTPLATE,
                ItemStackUtils.DIAMOND_LEGGINGS, ItemStackUtils.DIAMOND_BOOTS};
    }

    public static ItemStack[] WOOD_WEAPONS() {
        return new ItemStack[]{ItemStackUtils.WOOD_AXE, ItemStackUtils.WOOD_SWORD};
    }

    public static ItemStack[] STONE_WEAPONS() {
        return new ItemStack[]{ItemStackUtils.STONE_AXE, ItemStackUtils.STONE_SWORD};
    }

    public static ItemStack[] IRON_WEAPONS() {
        return new ItemStack[]{ItemStackUtils.IRON_AXE, ItemStackUtils.IRON_SWORD};
    }

    public static ItemStack[] GOLD_WEAPONS() {
        return new ItemStack[]{ItemStackUtils.GOLD_AXE, ItemStackUtils.GOLD_SWORD};
    }

    public static ItemStack[] DIAMOND_WEAPONS() {
        return new ItemStack[]{ItemStackUtils.DIAMOND_AXE, ItemStackUtils.DIAMOND_SWORD};
    }

    public static ItemStack[] ALL_WEAPONS() {
        return new ItemStack[]{ItemStackUtils.BOW, ItemStackUtils.ARROW,
                ItemStackUtils.WOOD_AXE, ItemStackUtils.WOOD_SWORD,
                ItemStackUtils.STONE_AXE, ItemStackUtils.STONE_SWORD,
                ItemStackUtils.IRON_AXE, ItemStackUtils.IRON_SWORD,
                ItemStackUtils.GOLD_AXE, ItemStackUtils.GOLD_SWORD,
                ItemStackUtils.DIAMOND_AXE, ItemStackUtils.DIAMOND_SWORD,
                ItemStackUtils.FISHING_ROD};
    }

    public static ItemStack[] ALL_FOOD() {
        return new ItemStack[]{ItemStackUtils.APPLE, ItemStackUtils.BAKED_POTATO,
                ItemStackUtils.BREAD, ItemStackUtils.COOKED_BEEF,
                ItemStackUtils.COOKED_CHICKEN, ItemStackUtils.COOKED_FISH,
                ItemStackUtils.COOKIE, ItemStackUtils.GRILLED_PORK,
                ItemStackUtils.MELON, ItemStackUtils.PUMPKIN_PIE,
                ItemStackUtils.GOLDEN_CARROT, ItemStackUtils.MUSHROOM_SOUP};
    }

    /*
	 * Item creation
	 */
    public static ItemStack createItem(Material material, String displayName, String... lore) {
        List<String> loreList = new ArrayList<>();
        Collections.addAll(loreList, lore);
        return ItemStackUtils.createItem(material, 1, (short) 0, displayName, loreList);
    }

    public static ItemStack createItem(Material material, String displayName, List<String> lore) {
        return ItemStackUtils.createItem(material, 1, (short) 0, displayName, lore);
    }

    public static ItemStack createItem(Material material, int amount) {
        return ItemStackUtils.createItem(material, amount, null, null, null); // 2 null at end to avoid ambiguous method call, yolo
    }

    public static ItemStack createItem(Material material, short data) {
        return ItemStackUtils.createItem(material, data, null, null, null); // 2 null at end to avoid ambiguous method call, yolo
    }

    public static ItemStack createItem(Material material, int amount, String displayName, String... lore) {
        List<String> loreList = new ArrayList<>();
        Collections.addAll(loreList, lore);
        return ItemStackUtils.createItem(material, amount, (short) 0, displayName, loreList);
    }

    public static ItemStack createItem(Material material, int amount, String displayName, List<String> lore) {
        return ItemStackUtils.createItem(material, amount, (short) 0, displayName, lore);
    }

    public static ItemStack createItem(Material material, short data, String displayName, String... lore) {
        List<String> loreList = new ArrayList<>();
        Collections.addAll(loreList, lore);
        return ItemStackUtils.createItem(material, 1, data, displayName, loreList);
    }

    public static ItemStack createItem(Material material, short data, String displayName, List<String> lore) {
        return ItemStackUtils.createItem(material, 1, data, displayName, lore);
    }

    public static ItemStack createItem(Material material, int amount, short data, String displayName, String... lore) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, lore);
        return ItemStackUtils.createItem(material, amount, data, displayName, list);
    }

    public static ItemStack createItem(Material material, int amount, short data, String displayName, List<String> lore) {
        ItemStack item = new ItemStack(material, amount, data);
        ItemMeta itemMeta = item.getItemMeta();

        if (displayName != null) {
            itemMeta.setDisplayName(displayName);
        }

        if (lore != null) {
            itemMeta.setLore(lore);
        }

        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack setItemTitle(ItemStack item, String title) {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(title);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack setLore(ItemStack item, String... lore) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, lore);

        return ItemStackUtils.setLore(item, list);
    }

    public static ItemStack setLore(ItemStack item, List<String> lore) {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack createGoldenHead() {
        return ItemStackUtils.createGoldenHead(1);
    }

    public static ItemStack createGoldenHead(int amount) {
        ItemStack itemStack = new ItemStack(Material.GOLDEN_APPLE, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GOLD + "Golden Head");

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(ChatColor.DARK_PURPLE + "Some say consuming the head of a");
        arrayList.add(ChatColor.DARK_PURPLE + "fallen foe strengthens the blood");

        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack createGoldenApple() {
        return ItemStackUtils.createGoldenApple(1);
    }

    public static ItemStack createGoldenApple(int amount) {
        ItemStack itemStack = new ItemStack(Material.GOLDEN_APPLE, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(ChatColor.GREEN + "Golden Apple");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static boolean equals(ItemStack item, ItemStack other) {
        return item != null && other != null
                && item.getType() == other.getType()
                && item.hasItemMeta() && item.getItemMeta().hasDisplayName()
                && item.getItemMeta().getDisplayName().equals(other.getItemMeta().getDisplayName())
                && item.getItemMeta().getLore().equals(other.getItemMeta().getLore());
    }

    public static ItemStack addUnbreaking(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.spigot().setUnbreakable(true);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static void addUnbreakingToArmor(Player player) {
        // Add Unbreaking to armor
        for (ItemStack itemStack : player.getInventory().getArmorContents()) {
            if (itemStack == null || itemStack.getType() == Material.AIR) {
                continue;
            }

            ItemStackUtils.addUnbreaking(itemStack);
        }
    }

    public static void addUnbreakingToWeapons(Player player) {
        // Add Unbreaking to weapons
        for (ItemStack itemStack : player.getInventory().getContents()) {
            if (itemStack == null || itemStack.getType() == Material.AIR)
            {
                continue;
            }

            Material type = itemStack.getType();
            if (type == Material.WOOD_SWORD || type == Material.STONE_SWORD
                    || type == Material.GOLD_SWORD || type == Material.IRON_SWORD
                    || type == Material.DIAMOND_SWORD || type == Material.BOW) {
                ItemStackUtils.addUnbreaking(itemStack);
            }
        }
    }

    public static ItemStack removeUnbreaking(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.spigot().setUnbreakable(false);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static void removeUnbreakingFromArmor(Player player) {
        // Remove Unbreaking from armor
        for (ItemStack itemStack : player.getInventory().getArmorContents()) {
            if (itemStack == null || itemStack.getType() == Material.AIR)
            {
                continue;
            }

            ItemStackUtils.removeUnbreaking(itemStack);
        }
    }

    public static void removeUnbreakingFromWeapons(Player player) {
        // Remove Unbreaking from armor
        for (ItemStack itemStack : player.getInventory().getContents()) {
            if (itemStack == null || itemStack.getType() == Material.AIR)
            {
                continue;
            }

            Material type = itemStack.getType();
            if (type == Material.WOOD_SWORD || type == Material.STONE_SWORD
                    || type == Material.GOLD_SWORD || type == Material.IRON_SWORD
                    || type == Material.DIAMOND_SWORD || type == Material.BOW) {
                ItemStackUtils.removeUnbreaking(itemStack);
            }
        }
    }

    public static ItemStack[] clone(ItemStack[] items) {
        ItemStack[] ret = new ItemStack[items.length];
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                ret[i] = items[i].clone();
            }
        }
        return ret;
    }

}
