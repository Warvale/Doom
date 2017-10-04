package net.warvale.ffa.kits;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class KitManager {
    private static Map<UUID, Kit> playerKit = new HashMap<>();
    public static Kit getUUID(UUID ux) {
       return playerKit.get(ux);
    }
    public static void setUUID(UUID ud, Kit value) {playerKit.put(ud, value);}
}
