package net.warvale.ffa.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class MCSGKit implements Kit{
    private int cost = 5000;
    public int getCost(){return this.cost;}
    private String name = "MCSG";
    private Material icon = Material.FISHING_ROD;
    public ArrayList<ItemStack> getKillRewards() {
        ArrayList<ItemStack> ret = new ArrayList<>();
        ret.add(new ItemStack(Material.ARROW,3));
        ret.add(new ItemStack(Material.GOLDEN_APPLE,1));
        return ret;
    }
    public Material getIcon() { return icon; }
    public String getName(){return this.name;}
    public void giveKit(Player player){
        ItemStack[] armor = new ItemStack[4];
        armor[0] = new ItemStack(Material.GOLD_BOOTS);
        armor[1] = new ItemStack(Material.IRON_LEGGINGS);
        armor[2] = new ItemStack(Material.GOLD_CHESTPLATE);
        armor[3] = new ItemStack(Material.CHAINMAIL_HELMET);
        for (ItemStack stack : armor) {
            stack.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        }
        player.getInventory().setArmorContents(armor);
        ItemStack sword = new ItemStack(Material.STONE_SWORD);
        sword.addEnchantment(Enchantment.DAMAGE_ALL, 2);
        player.getInventory().setItem(0, sword);
        player.getInventory().setItem(1, new ItemStack(Material.FISHING_ROD));
        player.getInventory().setItem(2, new ItemStack(Material.BOW));
        player.getInventory().setItem(7, new ItemStack(Material.ARROW, 4));
        player.getInventory().setItem(8, new ItemStack(Material.GOLDEN_APPLE));
    }
}
