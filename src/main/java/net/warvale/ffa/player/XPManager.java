package net.warvale.ffa.player;

import org.bukkit.ChatColor;

public class XPManager {
    private static int[] levelupxp = new int[12];



    public static int[] getLevelupxp() {
        levelupxp[0] = 50;
        levelupxp[1] = 150;
        levelupxp[2] = 300;
        levelupxp[3] = 750;
        levelupxp[4] = 1500;
        levelupxp[5] = 4000;
        levelupxp[6] = 10000;
        levelupxp[7] = 25000;
        levelupxp[8] = 75000;
        levelupxp[9] = 150000;
        levelupxp[10] = 250000;
        levelupxp[11] = 500000;
    return levelupxp;
    }
    public static ChatColor getLevelColor(int level){
        if (level==1||level==2) return ChatColor.GRAY;
        else if (level==3||level==4) return ChatColor.GREEN;
        else if (level==5||level==6) return ChatColor.YELLOW;
        else if (level==7||level==8) return ChatColor.GOLD;
        else if (level==9||level==11||level==10) return ChatColor.RED;
        else if (level==12) return ChatColor.DARK_RED;
        else return ChatColor.BLACK;
    }
}
