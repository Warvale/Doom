package net.warvale.ffa.listeners;

import net.warvale.ffa.WarvaleFFA;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class LaunchpadListener implements Listener {
    WarvaleFFA plugin;

    public LaunchpadListener(WarvaleFFA plugin){
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onMove(PlayerMoveEvent e) {
        Block curr = e.getTo().getBlock();
        Block below = curr.getRelative(BlockFace.DOWN);
        if (below.getType().equals(Material.WOOL) && curr.getType().equals(Material.IRON_PLATE)) {
            e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(10));
            Vector velc = e.getPlayer().getVelocity();
            velc.setY(1.0F);
            e.getPlayer().setVelocity(velc);
        }
    }
}
