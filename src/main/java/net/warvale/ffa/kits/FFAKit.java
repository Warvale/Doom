package net.warvale.ffa.kits;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import net.warvale.ffa.WarvaleFFA;
import net.warvale.ffa.events.KitLoadEvent;

public class FFAKit {

    private String name;
    protected ItemStack[] inventoryItems = new ItemStack[36];
    protected ItemStack[] armorItems = new ItemStack[9];

    public FFAKit(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public ItemStack[] getInventoryItems(){
        return this.inventoryItems;
    }
    public ItemStack[] getArmorItems(){
        return this.armorItems;
    }

    public void loadKit(Player player) {

        try {
            player.getInventory().setContents(this.inventoryItems);
            player.getInventory().setArmorContents(this.armorItems);
            player.updateInventory();
        } catch (Exception ex) {

        }

        //call the KitLoadEvent
        Bukkit.getScheduler().runTaskAsynchronously(WarvaleFFA.get(), new Runnable() {
            @Override
            public void run() {
                Bukkit.getServer().getPluginManager().callEvent(new KitLoadEvent(player, WarvaleFFA.get().getGame().getGameMode().getKit()));
            }
        });
    }

}
