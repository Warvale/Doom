package net.warvale.ffa.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public interface Kit {

    String name = "Unnamed";
    Material icon = Material.WOOD_SWORD;
    int cost = 1;
    Material getIcon();
    ArrayList<ItemStack> getKillRewards();
    void giveKit(Player player);
    int getCost();
    String getName();


}

