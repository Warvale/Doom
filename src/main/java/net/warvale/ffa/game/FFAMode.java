package net.warvale.ffa.game;

public enum FFAMode {

    NONE("none"),
    BUILDUHC("builduhc_ffa_stats"),
    SOUP("soup_ffa_stats"),
    UHC("uhc_ffa_stats"),
    SG("sg_ffa_stats"),
    NODEBUFF("nodebuff_ffa_stats");

    private String table;

    FFAMode(String table) {
        this.table = table;
    }

    public String getTable() {
        return this.table;
    }
}
