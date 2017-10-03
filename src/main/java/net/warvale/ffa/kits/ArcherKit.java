package net.warvale.ffa.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class ArcherKit implements Kit{
    private int cost = 500;
    public int getCost(){return this.cost;}
    private String name = "Archer";
    private Material icon = Material.BOW    ;
    public Material getIcon() { return icon; }
    public String getName(){return this.name;}
    public void giveKit(Player player){
        ItemStack[] armor = new ItemStack[4];
        armor[0] = new ItemStack(Material.CHAINMAIL_BOOTS);
        armor[0].addEnchantment(Enchantment.PROTECTION_PROJECTILE, 3);
        armor[1] = new ItemStack(Material.CHAINMAIL_LEGGINGS);
        armor[1].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        armor[1].addEnchantment(Enchantment.DURABILITY, 1);
        armor[2] = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        armor[2].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        armor[3] = new ItemStack(Material.LEATHER_HELMET); //
        armor[3].addEnchantment(Enchantment.DURABILITY, 1);
        player.getInventory().setArmorContents(armor);
        player.getInventory().setItem(0, new ItemStack(Material.WOOD_SWORD));
        ItemStack bow = new ItemStack(Material.BOW);
        bow.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
        bow.addEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
        bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        player.getInventory().setItem(1, bow);
        Potion splash = new Potion(PotionType.SPEED, 1);
        splash.setSplash(true);

        ItemStack potion = splash.toItemStack(1);
        /*player.getInventory().setItem(2, potion);
        player.getInventory().setItem(3, potion);
        player.getInventory().setItem(4, potion);*/
        player.getInventory().setItem(8, new ItemStack(Material.ARROW));
    }
}
