package net.warvale.ffa.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;

public class PotKit implements Kit{
    private int cost = 1000;
    public int getCost(){return this.cost;}
    private String name = "Pot";
    private Material icon = Material.POTION;
    public Material getIcon() { return icon; }
    public ArrayList<ItemStack> getKillRewards() {
        ArrayList<ItemStack> ret = new ArrayList<>();
        Potion splash = new Potion(PotionType.INSTANT_HEAL, 1);
        splash.setSplash(true);
        ItemStack potion = splash.toItemStack(1);
        ret.add(potion);
        return ret;
    }
    public String getName(){return this.name;}
    public void giveKit(Player player){
        ItemStack[] armor = new ItemStack[4];
        armor[0] = new ItemStack(Material.IRON_BOOTS);
        armor[1] = new ItemStack(Material.CHAINMAIL_LEGGINGS);
        armor[2] = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        armor[3] = new ItemStack(Material.IRON_HELMET);
        for (ItemStack stack : armor) {
            stack.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        }
        player.getInventory().setArmorContents(armor);
        player.getInventory().setItem(0, new ItemStack(Material.IRON_SWORD));

        Potion splash = new Potion(PotionType.INSTANT_HEAL, 1);
        splash.setSplash(true);
        ItemStack potion = splash.toItemStack(1);
        player.getInventory().setItem(1, potion);
        player.getInventory().setItem(2, potion);
        
        Potion splashx = new Potion(PotionType.FIRE_RESISTANCE, 1);
        splashx.setSplash(true);
        ItemStack potionx = splashx.toItemStack(1);
        player.getInventory().setItem(4, potionx);
        
        Potion splashy = new Potion(PotionType.REGEN, 1);
        splashy.setSplash(true);
        ItemStack potiony = splashy.toItemStack(1);
        player.getInventory().setItem(5, potiony);
        
        Potion splashz = new Potion(PotionType.INSTANT_DAMAGE, 1);
        splashz.setSplash(true);
        ItemStack potionz = splashz.toItemStack(1);
        player.getInventory().setItem(3, potionz);
    }
}
