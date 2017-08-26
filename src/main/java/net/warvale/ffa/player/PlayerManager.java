package net.warvale.ffa.player;


import net.warvale.ffa.WarvaleFFA;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager {

    private static PlayerManager instance;
    private Map<UUID, FFAPlayer> players = new HashMap<>();

    public static PlayerManager getInstance() {
        if (instance == null) {
            instance = new PlayerManager();
        }
        return instance;
    }

    public Map<UUID, FFAPlayer> getFFAPlayers() {
        return this.players;
    }

    public FFAPlayer getFFAPlayer(UUID playerUUID) {
        return this.players.get(playerUUID);
    }

    public boolean doesFFAPlayerExsists(UUID playerUUID) {
        return this.players.containsKey(playerUUID);
    }

    public void createFFAPlayer(UUID playerUUID) {
        this.players.put(playerUUID, new FFAPlayer(playerUUID));
    }

    public void removeFFAPlayer(UUID playerUUID) {
        if (this.players.containsKey(playerUUID)) {

            if (WarvaleFFA.get().getGame().isStatsEnabled()) {
                getFFAPlayer(playerUUID).saveData();
            }

            this.players.remove(playerUUID);
        }
    }

}
