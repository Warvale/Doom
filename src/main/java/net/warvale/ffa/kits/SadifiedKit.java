package net.warvale.ffa.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SadifiedKit implements Kit{
    private String name = "Sadified";
    public String getName(){return this.name;}
    public void giveKit(Player player){

        PotionEffect pot=new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000,0,true, false); // example potion
        player.addPotionEffect(pot);
        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
        helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        ItemStack[] armor = new ItemStack[4];
        armor[2] = new ItemStack(Material.IRON_CHESTPLATE);
        player.getInventory().setArmorContents(armor);
        ItemStack enchbow = new ItemStack(Material.BOW);
        enchbow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        enchbow.addEnchantment(Enchantment.ARROW_DAMAGE, 2);
        player.getInventory().setItem(0, enchbow);
        player.getInventory().setItem(1, new ItemStack(Material.ARROW));

    }
}
