package net.warvale.ffa.kits;

import org.bukkit.entity.Player;
import net.warvale.ffa.WarvaleFFA;

import java.util.ArrayList;
import java.util.List;

public class KitManager {

    private static KitManager instance;

    public static KitManager get() {
        if (instance == null) {
            instance = new KitManager();
        }
        return instance;
    }

    private List<FFAKit> kits = new ArrayList<>();

    public void loadKits() {
        kits.add(new UHC());
    }

    public FFAKit getKit(String name) {
        for (FFAKit kit : kits) {
            if (kit.getName().equals(name)) {
                return kit;
            }
        }
        return null;
    }

    public void giveKit(Player player) {

        switch (WarvaleFFA.get().getGame().getFFAMode()) {
            case UHC:
                getKit("UHC").loadKit(player);
        }

    }

}
