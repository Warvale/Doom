package net.warvale.ffa.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class PyroKit implements Kit {
    private int cost = 10000;
    public int getCost(){return this.cost;}
    private String name = "Pyro";
    private Material icon = Material.FIREBALL;
    public ArrayList<ItemStack> getKillRewards() {
        ArrayList<ItemStack> ret = new ArrayList<>();
        ret.add(new ItemStack(Material.ARROW,3));
        return ret;
    }
    public Material getIcon() { return icon; }
    public String getName(){return this.name;}
    public void giveKit(Player player){
        ItemStack[] armor = new ItemStack[4];
        armor[0] = new ItemStack(Material.DIAMOND_BOOTS);
        armor[0].addEnchantment(Enchantment.PROTECTION_PROJECTILE, 3);
        armor[1] = new ItemStack(Material.GOLD_LEGGINGS);
        armor[1].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        armor[1].addEnchantment(Enchantment.DURABILITY, 1);
        armor[2] = new ItemStack(Material.LEATHER_CHESTPLATE);
        armor[2].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        player.getInventory().setArmorContents(armor);
        
        ItemStack sword = new ItemStack(Material.STONE_SWORD);
        sword.addEnchantment(Enchantment.FIRE_ASPECT, 1);
        player.getInventory().setItem(0, sword);
        
        ItemStack bow = new ItemStack(Material.BOW);
        bow.addEnchantment(Enchantment.ARROW_FIRE, 1);
        player.getInventory().setItem(1, bow);

        player.getInventory().setItem(8, new ItemStack(Material.ARROW, 6));
    }
}
