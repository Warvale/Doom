package net.warvale.ffa.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public interface Kit {

    String name = "Unnamed";
    Material icon = Material.WOOD_SWORD;
    int cost = 1;
    Material getIcon();
    void giveKit(Player player);
    int getCost();
    String getName();


}

