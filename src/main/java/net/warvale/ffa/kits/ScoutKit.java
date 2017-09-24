package net.warvale.ffa.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ScoutKit implements Kit{
    private String name = "Scout";
    public String getName(){return this.name;}
    public void giveKit(Player player){
        ItemStack[] armor = new ItemStack[4];
        armor[0] = new ItemStack(Material.CHAIN_BOOTS);
        armor[1] = new ItemStack(Material.CHAIN_LEGGINGS);
        armor[2] = new ItemStack(Material.CHAIN_CHESTPLATE);
        armor[3] = new ItemStack(Material.CHAIN_HELMET);
        player.getInventory().setArmorContents(armor);
        ItemStack sword = new ItemStack(Material.STONE_SWORD);
        sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
        player.getInventory().setItem(0, sword);
        // player.getInventory().setItem(1, new ItemStack(Material.SPEED_SPLASH_POT)); TODO: add splash swift 1 for 8 min
        // player.getInventory().setItem(2, new ItemStack(Material.SPEED_SPLASH_POT)); TODO: add splash swift 1 for 8 min
        // player.getInventory().setItem(3, new ItemStack(Material.SPEED_SPLASH_POT)); TODO: add splash swift 1 for 8 min
        player.getInventory().setItem(8, new ItemStack(Material.GOLDEN_APPLE, 2));
    }
}
