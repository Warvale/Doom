package net.warvale.ffa.listeners;

import net.warvale.ffa.WarvaleFFA;
import net.warvale.ffa.gui.guis.KitSelectorGUI;
import net.warvale.ffa.kits.Kit;
import net.warvale.ffa.kits.KitManager;
import net.warvale.ffa.player.FFAPlayer;
import net.warvale.ffa.player.PlayerManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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
            Player player = e.getPlayer();
            FFAPlayer ffaPlayer = PlayerManager.getInstance().getFFAPlayer(player.getUniqueId());
            Vector velc = e.getPlayer().getVelocity();
            velc.setY(1.0F);
            e.getPlayer().setVelocity(velc);
            Kit kit = KitSelectorGUI.getINSTANCE().getKits().get(ffaPlayer.getLastKit());
            player.getInventory().clear();
            kit.giveKit(player);
            KitManager.setUUID(player.getUniqueId(), kit);


            // Makes all items unbreakable.
            for (int i = 0; i <= 35; i++) {
                ItemStack wow = player.getInventory().getItem(i);
                if (wow == null) return;
                ItemMeta meta = wow.getItemMeta();
                meta.spigot().setUnbreakable(true);
                wow.setItemMeta(meta);
                player.getInventory().setItem(i,wow);
            }
            ItemStack[] armor = player.getInventory().getArmorContents().clone();
            for (int i = 0; i < armor.length; i++) {
                ItemStack dankmemes = armor[i];
                if (dankmemes.getType().equals(Material.AIR)) return;
                ItemMeta meta = dankmemes.getItemMeta();
                meta.spigot().setUnbreakable(true);
                dankmemes.setItemMeta(meta);
            }
            player.getInventory().setArmorContents(armor);

        }
    }
}
