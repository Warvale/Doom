package net.warvale.ffa.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ExampleKit implements Kit{
    public String name = "Example";
    public void giveKit(Player player){
        player.getInventory().getChestplate().setType(Material.IRON_CHESTPLATE);
        player.getInventory().setItem(0, new ItemStack(Material.STONE_SWORD));

    }
}
