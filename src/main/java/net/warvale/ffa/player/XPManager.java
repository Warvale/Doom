package net.warvale.ffa.player;

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
}
