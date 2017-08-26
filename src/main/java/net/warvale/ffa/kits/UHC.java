package net.warvale.ffa.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import net.warvale.ffa.utils.ItemStackUtils;

public class UHC extends FFAKit {

    public UHC() {
        super("UHC");

        // Create default armor kit
        this.armorItems[3] = new ItemStack(Material.IRON_HELMET);
        this.armorItems[3].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);

        this.armorItems[2] = new ItemStack(Material.IRON_CHESTPLATE);
        this.armorItems[2].addEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);

        this.armorItems[1] = new ItemStack(Material.IRON_LEGGINGS);
        this.armorItems[1].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);

        this.armorItems[0] = new ItemStack(Material.IRON_BOOTS);
        this.armorItems[0].addEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);


        // Create default inventory kit
        this.inventoryItems[0] = new ItemStack(Material.IRON_SWORD);
        this.inventoryItems[0].addEnchantment(Enchantment.DAMAGE_ALL, 1);

        this.inventoryItems[1] = new ItemStack(Material.BOW);
        this.inventoryItems[1].addEnchantment(Enchantment.ARROW_DAMAGE, 1);
        this.inventoryItems[1].addEnchantment(Enchantment.ARROW_INFINITE, 1);

        this.inventoryItems[2] = new ItemStack(Material.COOKED_BEEF, 64);

        this.inventoryItems[3] = ItemStackUtils.createGoldenApple();
        this.inventoryItems[4] = ItemStackUtils.createGoldenApple();

        this.inventoryItems[9] = new ItemStack(Material.ARROW, 1);

    }

}
