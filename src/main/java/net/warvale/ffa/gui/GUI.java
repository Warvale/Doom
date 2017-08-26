package net.warvale.ffa.gui;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public abstract class GUI {
    private String description;
    private String name;

    /**
     * Inventory GUI class constructor
     *
     * @param name The name of the feature.
     * @param description A short description of the feature.
     */
    public GUI(String name, String description) {
        this.description = description;
        this.name = name;
    }

    /**
     * Get the name of the GUI
     *
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get a description of the GUI.
     *
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Called when the inventory has been setup.
     */
    public abstract void onSetup();

    /**
     * Fill the given inventory with class on every slot with no item.
     *
     * @param inv The inventory to fill.
     * @param blackFirst Wether to start with the black glass or not.
     */
    protected void glassify(Inventory inv, boolean blackFirst) {
        if (inv == null) {
            return;
        }

        ItemStack black = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        ItemMeta blackMeta = black.getItemMeta();
        blackMeta.setDisplayName("§0:>"); // hidden easter egg :>
        black.setItemMeta(blackMeta);

        ItemStack gray = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
        ItemMeta grayMeta = black.getItemMeta();
        grayMeta.setDisplayName("§0:>"); // hidden easter egg :>
        gray.setItemMeta(grayMeta);

        boolean bool = blackFirst;

        for (int i = 0; i < inv.getSize(); i++) {
            bool = !bool;

            ItemStack item = inv.getItem(i);

            if (item != null && inv.getItem(i).getType() != Material.AIR) {
                continue;
            }

            if (bool) {
                inv.setItem(i, gray);
            } else {
                inv.setItem(i, black);
            }
        }
    }

    /**
     * Fill the given inventory with class on every slot with no item.
     *
     * @param inv The inventory to fill.
     */
    protected void glassify(Inventory inv) {
        glassify(inv, true);
    }

    /**
     * Check if the given slot is on one of the sides of the inventory.
     *
     * @param slot The slot.
     * @return True if it is, false otherwise.
     */
    protected boolean isSide(int slot) {
        switch (slot) {
        case 0:
        case 8:
        case 9:
        case 17:
        case 18:
        case 26:
        case 27:
        case 35:
        case 36:
            return true;
        default:
            return false;
        }
    }
}