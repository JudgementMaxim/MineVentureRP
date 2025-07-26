package org.JudgementM.mineVentureRP.gui;

import net.kyori.adventure.text.Component;
import org.JudgementM.mineVentureRP.MineVentureRP;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.List;

public class JobSelectionGUI implements Listener {

    private static final int JOBS_PER_PAGE = 7;
    private static final List<JobButton> jobButtons = new ArrayList<>();

    static {
        jobButtons.add(new JobButton("FARMER", Material.WHEAT));                   // Pflanzen anbauen & ernten
        jobButtons.add(new JobButton("SCHMIED", Material.IRON_INGOT));             // Werkzeuge schmieden
        jobButtons.add(new JobButton("RÜSTUNGSSCHMIED", Material.IRON_CHESTPLATE));// Rüstungen herstellen
        jobButtons.add(new JobButton("WAFFENSCHMIED", Material.IRON_SWORD));       // Waffen herstellen
        jobButtons.add(new JobButton("HÄNDLER", Material.EMERALD));               // Waren tauschen
        jobButtons.add(new JobButton("BERGBAUER", Material.IRON_PICKAXE));        // Erze abbauen
        jobButtons.add(new JobButton("JÄGER", Material.BOW));                     // Tiere & Mobs jagen
        jobButtons.add(new JobButton("KOCH", Material.COOKED_BEEF));              // Essen herstellen
        jobButtons.add(new JobButton("ALCHEMIST", Material.BREWING_STAND));       // Tränke brauen
        jobButtons.add(new JobButton("BAUER", Material.IRON_HOE));                // Felder bearbeiten
        jobButtons.add(new JobButton("ANGELER", Material.FISHING_ROD));           // Fischen
        jobButtons.add(new JobButton("HOLZFÄLLER", Material.OAK_LOG));            // Bäume fällen
        jobButtons.add(new JobButton("KRÄUTERKUNDLER", Material.FERN));           // Pflanzen & Kräuter sammeln
        jobButtons.add(new JobButton("SCOUT", Material.MAP));                     // Gebiete erkunden
        jobButtons.add(new JobButton("WÄCHTER", Material.SHIELD));                // Städte beschützen
        jobButtons.add(new JobButton("PIRAT", Material.TNT));                     // Plündern & Schiffe bauen
        jobButtons.add(new JobButton("HANDWERKER", Material.CRAFTING_TABLE));     // Allrounder für Basismaterialien
        jobButtons.add(new JobButton("ARCHITEKT", Material.BRICKS));              // Gebäude entwerfen
    }

    public static void open(Player player) {
        open(player, 0);
    }

    public static void open(Player player, int page) {
        Inventory inv = Bukkit.createInventory(null, 9 * 5, Component.text("Wähle deinen Job (Seite " + (page + 1) + ")"));

        int start = page * JOBS_PER_PAGE;
        int end = Math.min(start + JOBS_PER_PAGE, jobButtons.size());

        // Hauptjob-Buttons (Slot 20, 21, 22, 24, 25, 26 als Standardposition)
        int[] slots = {10, 12, 14, 16, 29, 31,33};
        for (int i = start; i < end; i++) {
            JobButton button = jobButtons.get(i);
            ItemStack item = new ItemStack(button.material());
            ItemMeta meta = item.getItemMeta();
            meta.displayName(Component.text(button.name()));
            item.setItemMeta(meta);
            inv.setItem(slots[i - start], item);
        }

        // Navigation
        if (page > 0) {
            ItemStack prev = new ItemStack(Material.ARROW);
            ItemMeta meta = prev.getItemMeta();
            meta.displayName(Component.text("§c← Zurück"));
            prev.setItemMeta(meta);
            inv.setItem(36, prev);
        }

        if (end < jobButtons.size()) {
            ItemStack next = new ItemStack(Material.ARROW);
            ItemMeta meta = next.getItemMeta();
            meta.displayName(Component.text("§aWeiter →"));
            next.setItemMeta(meta);
            inv.setItem(44, next);
        }

        player.openInventory(inv);
        player.setMetadata("OpenedMenu", new FixedMetadataValue(MineVentureRP.getInstance(), page));
    }

    private record JobButton(String name, Material material) {}
}
