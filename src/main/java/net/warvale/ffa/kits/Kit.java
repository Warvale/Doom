package net.warvale.ffa.kits;

import org.bukkit.entity.Player;

public interface Kit {

    String name = "Unnamed";
    int cost = 1;
    void giveKit(Player player);
    int getCost();
    String getName();


}

