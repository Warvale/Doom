package net.warvale.ffa.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ArcherKit implements Kit{
    private String name = "ArcherKit";
    public String getName(){return this.name;}
    public void giveKit(Player player){
        ItemStack[] armor = new ItemStack[4];
        armor[0] = new ItemStack(Material.CHAIN_BOOTS); // TODO: add projectile protection 3
        armor[1] = new ItemStack(Material.CHAIN_LEGGINGS); // TODO: add protection 1, unbreaking 1
        armor[2] = new ItemStack(Material.CHAIN_CHESTPLATE); // TODO: add protection 1
        armor[3] = new ItemStack(Material.LEATHER_HELMET); // TODO: add unbreaking 1
        player.getInventory().setArmorContents(armor);
        player.getInventory().setItem(0, new ItemStack(Material.WOODEN_SWORD));
        ItemStack bow = new ItemStack(Material.BOW);
        bow.addEnchantment(Enchantment.ARROW_DAMAGE, 2);
        bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        player.getInventory().setItem(1, bow);
        // player.getInventory().setItem(2, new ItemStack(Material.SPEED_SPLASH_POT)); TODO: add splash swift  1 for 8 min
        player.getInventory().setItem(8, new ItemStack(Material.ARROW));
    }
}
