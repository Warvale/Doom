package net.warvale.ffa.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MCSGKit implements Kit{
    private int cost = 25000;
    public int getCost(){return this.cost;}
    private String name = "MCSG";
    private Material icon = Material.FISHING_ROD;
    public Material getIcon() { return icon; }
    public String getName(){return this.name;}
    public void giveKit(Player player){
        ItemStack[] armor = new ItemStack[4];
        armor[0] = new ItemStack(Material.GOLD_BOOTS);
        armor[1] = new ItemStack(Material.IRON_LEGGINGS);
        armor[2] = new ItemStack(Material.LEATHER_CHESTPLATE);
        armor[3] = new ItemStack(Material.CHAINMAIL_HELMET);
        player.getInventory().setArmorContents(armor);
        ItemStack sword = new ItemStack(Material.IRON_SWORD);
        sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
        player.getInventory().setItem(0, sword);
        player.getInventory().setItem(1, new ItemStack(Material.FISHING_ROD));
        player.getInventory().setItem(2, new ItemStack(Material.BOW));
        player.getInventory().setItem(7, new ItemStack(Material.ARROW, 16));
        player.getInventory().setItem(8, new ItemStack(Material.GOLDEN_APPLE));
    }
}
