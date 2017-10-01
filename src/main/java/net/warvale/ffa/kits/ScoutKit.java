package net.warvale.ffa.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class ScoutKit implements Kit{
    private int cost = 10000;
    public int getCost(){return this.cost;}
    private String name = "Scout";
    private Material icon = Material.STRING;
    public Material getIcon() { return icon; }
    public String getName(){return this.name;}
    public void giveKit(Player player){
        ItemStack[] armor = new ItemStack[4];
        armor[0] = new ItemStack(Material.CHAINMAIL_BOOTS);
        armor[1] = new ItemStack(Material.CHAINMAIL_LEGGINGS);
        armor[2] = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        armor[3] = new ItemStack(Material.CHAINMAIL_HELMET);
        for (ItemStack stack : armor) {
            stack.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        }
        player.getInventory().setArmorContents(armor);
        ItemStack sword = new ItemStack(Material.STONE_SWORD);
        sword.addEnchantment(Enchantment.DAMAGE_ALL, 2);
        player.getInventory().setItem(0, sword);


        Potion splash = new Potion(PotionType.SPEED, 1);
        splash.setSplash(true);
        ItemStack potion = splash.toItemStack(1);
        player.getInventory().setItem(1, potion);
        player.getInventory().setItem(2, potion);
        player.getInventory().setItem(3, potion);
        player.getInventory().setItem(7, new ItemStack(Material.ENDER_PEARL, 1));
        player.getInventory().setItem(8, new ItemStack(Material.GOLDEN_APPLE, 2));
    }
}
