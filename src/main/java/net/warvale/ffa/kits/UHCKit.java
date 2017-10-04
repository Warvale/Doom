package net.warvale.ffa.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class UHCKit implements Kit{
    private int cost = 0;
    public int getCost(){return this.cost;}
    private String name = "UHC";
    private Material icon = Material.GOLDEN_APPLE;
    public ItemStack getKillReward() {return new ItemStack(Material.GOLDEN_APPLE);
    }
    public Material getIcon() { return icon; }
    public String getName(){return this.name;}
    public void giveKit(Player player){
        ItemStack[] armor = new ItemStack[4];
        armor[0] = new ItemStack(Material.DIAMOND_BOOTS);
        armor[1] = new ItemStack(Material.IRON_LEGGINGS);
        armor[2] = new ItemStack(Material.IRON_CHESTPLATE);
        armor[3] = new ItemStack(Material.IRON_HELMET);
        player.getInventory().setArmorContents(armor);
        ItemStack sword = new ItemStack(Material.IRON_SWORD);
        player.getInventory().setItem(0, sword);
        player.getInventory().setItem(1, new ItemStack(Material.BOW));
        player.getInventory().setItem(7, new ItemStack(Material.ARROW, 4));
        player.getInventory().setItem(8, new ItemStack(Material.GOLDEN_APPLE, 2));
    }
}
