package net.warvale.ffa.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Kit {

    String name = "Unnamed";
    Material icon = Material.WOOD_SWORD;
    int cost = 1;
    Material getIcon();
    ItemStack getKillReward();
    void giveKit(Player player);
    int getCost();
    String getName();


}

