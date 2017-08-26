package net.warvale.ffa.gamemodes;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import net.warvale.ffa.events.KitLoadEvent;
import net.warvale.ffa.kits.UHC;
import net.warvale.ffa.utils.ItemStackUtils;

public class UHCGamemode extends Gamemode implements Listener {

    public UHCGamemode() {
        super("UHC", "uhcffa_stats", new UHC());
    }

    @EventHandler
    public void onKitLoad(KitLoadEvent event) {
        Player player = event.getPlayer();

        // Add an enderpearl to their inventory
        player.getInventory().addItem(new ItemStack(Material.ENDER_PEARL));

        //make  armor and weapons unbreakable
        ItemStackUtils.addUnbreakingToArmor(player);
        ItemStackUtils.addUnbreakingToWeapons(player);
    }

    @Override
    public void handleDeath(LivingEntity died) {
        // Drop a Golden Head since we disable crafting benches
        died.getWorld().dropItemNaturally(died.getLocation(), ItemStackUtils.createGoldenHead());
    }

}
