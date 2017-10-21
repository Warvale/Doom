package net.warvale.ffa.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;

public class AssassinKit implements Kit{
    private int cost = 15000;
    public int getCost(){return this.cost;}
    private String name = "Assassin";
    private Material icon = Material.DIAMOND_SWORD;
    public Material getIcon() { return icon; }
    public ArrayList<ItemStack> getKillRewards() {
        ArrayList<ItemStack> ret = new ArrayList<>();
        Potion splash = new Potion(PotionType.SPEED, 1);
        splash.setSplash(true);
        ItemStack potion = splash.toItemStack(1);
        ret.add(new ItemStack(Material.SNOW_BALL    ,4));
        ret.add(new ItemStack(Material.GOLDEN_APPLE,1));
        return ret;
    }

    public String getName(){return this.name;}
    public void giveKit(Player player){
        ItemStack[] armor = new ItemStack[4];
        armor[0] = new ItemStack(Material.CHAINMAIL_HELMET);
        for (ItemStack stack : armor) {
            stack.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        }
        player.getInventory().setArmorContents(armor);
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        sword.addEnchantment(Enchantment.DAMAGE_ALL, 2);
        player.getInventory().setItem(0, sword);


        Potion splash = new Potion(PotionType.SPEED, 1);
        splash.setSplash(true);
        ItemStack potion = splash.toItemStack(1);
        
        Potion splash2 = new Potion(PotionType.INVISIBILITY, 1);
        splash2.setSplash(true);
        ItemStack potion2 = splash2.toItemStack(1);
        
        player.getInventory().setItem(1, potion);
        player.getInventory().setItem(2, potion2);
        player.getInventory().setItem(3, new ItemStack(Material.GOLDEN_APPLE, 1));
    }
}
