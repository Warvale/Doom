package net.warvale.ffa.kits;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import net.warvale.ffa.utils.ItemStackUtils;

public class UHCKit {

    private static UHCKit instance;

    public static UHCKit getInstance() {
        if (instance == null) {
            instance = new UHCKit();
        }
        return instance;
    }

    public void giveKit(Player player) {
        //armor
        giveArmor(player);

        //make armor unbreakable
        ItemStackUtils.addUnbreakingToArmor(player);

        //gear
        giveGear(player);

        //make weapons unbreakable
        ItemStackUtils.addUnbreakingToWeapons(player);
    }

    private void giveArmor(Player player) {

        //helmet
        ItemStack helmet = new ItemStack(Material.IRON_HELMET, 1);
        helmet.setDurability((short) 0);
        ItemMeta helmetMeta = helmet.getItemMeta();

        helmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
        helmet.setItemMeta(helmetMeta);

        //chestplate
        ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE, 1);
        chestplate.setDurability((short) 0);
        ItemMeta chestplateMeta = chestplate.getItemMeta();

        chestplateMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 2, true);
        chestplate.setItemMeta(chestplateMeta);

        //leggings
        ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS, 1);
        leggings.setDurability((short) 0);
        ItemMeta leggingsMeta = leggings.getItemMeta();

        leggingsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
        leggings.setItemMeta(leggingsMeta);

        //boots
        ItemStack boots = new ItemStack(Material.IRON_BOOTS, 1);
        boots.setDurability((short) 0);
        ItemMeta bootsMeta = boots.getItemMeta();

        bootsMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 2, true);
        boots.setItemMeta(bootsMeta);

        player.getInventory().setHelmet(helmet);
        player.getInventory().setChestplate(chestplate);
        player.getInventory().setLeggings(leggings);
        player.getInventory().setBoots(boots);

    }

    private void giveGear(Player player) {
        //sword
        ItemStack sword = new ItemStack(Material.IRON_SWORD, 1);
        sword.setDurability((short) 0);
        ItemMeta swordMeta = sword.getItemMeta();

        swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        sword.setItemMeta(swordMeta);

        //bow
        ItemStack bow = new ItemStack(Material.BOW, 1);
        sword.setDurability((short) 0);
        ItemMeta bowMeta = bow.getItemMeta();

        bowMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        bow.setItemMeta(bowMeta);

        //steak
        ItemStack steak = new ItemStack(Material.COOKED_BEEF, 64);

        //ender pearl
        ItemStack pearl = new ItemStack(Material.ENDER_PEARL, 1);

        //arrows
        ItemStack arrow = new ItemStack(Material.ARROW, 1);

        //add items to players inventory
        player.getInventory().setItem(0, sword);
        player.getInventory().setItem(1, bow);
        player.getInventory().setItem(2, steak);
        player.getInventory().setItem(3, ItemStackUtils.createGoldenApple());
        player.getInventory().setItem(4, ItemStackUtils.createGoldenApple());
        player.getInventory().setItem(5, pearl);
        player.getInventory().setItem(9, arrow);

    }


}
