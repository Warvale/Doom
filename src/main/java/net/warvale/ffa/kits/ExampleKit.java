package net.warvale.ffa.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.lang.reflect.Array;

public class ExampleKit implements Kit{
    private String name = "Example";
    public String getName(){return this.name;}
    public void giveKit(Player player){

        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
        helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        ItemStack[] armor = new ItemStack[4];
        armor[3] = helmet;
        player.getInventory().setArmorContents(armor);
        player.getInventory().setItem(0, new ItemStack(Material.STONE_SWORD));

    }
}
