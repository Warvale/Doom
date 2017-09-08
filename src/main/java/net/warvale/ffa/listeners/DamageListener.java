package net.warvale.ffa.listeners;

import net.warvale.ffa.WarvaleFFA;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {
    WarvaleFFA plugin;

    public DamageListener(WarvaleFFA plugin){
        this.plugin = plugin;
    }
    public void onDamage(EntityDamageEvent e) {
        if (!(e.getEntityType().equals(EntityType.PLAYER))) return;
        if (e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) e.setCancelled(true);
    }
}
