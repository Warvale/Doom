package net.warvale.ffa.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import net.warvale.ffa.kits.FFAKit;

public class KitLoadEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Player player;
    private FFAKit kit;

    public KitLoadEvent(Player player, FFAKit kit) {
        this.player = player;
        this.kit = kit;
    }

    public Player getPlayer() {
        return player;
    }

    public FFAKit getKit() {
        return kit;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
