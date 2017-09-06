package net.warvale.ffa.listeners;

import net.warvale.ffa.WarvaleFFA;
import net.warvale.ffa.player.FFAPlayer;
import net.warvale.ffa.player.PlayerManager;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class WorldListener implements Listener {

    WarvaleFFA plugin;

    public WorldListener(WarvaleFFA plugin){
        this.plugin = plugin;
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event) {
        event.setCancelled(true);
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    private void onBlockPlace(BlockPlaceEvent event) {
        if(!event.getPlayer().getGameMode().equals(GameMode.CREATIVE)){
            Location loc = event.getBlockPlaced().getLocation();
            new BukkitRunnable() { //TODO: Do this based on kit

                @Override
                public void run() {
                    loc.getBlock().setType(Material.AIR);
                }

            }.runTaskLater(this.plugin, 200);
        }
        event.setCancelled(false/*For now*/); //TODO: Set cancelled based on kit
    }

    @EventHandler
    public void onWeather(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

}
