package org.JudgementM.mineVentureRP.listeners;

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.JudgementM.mineVentureRP.MineVentureRP;
import org.JudgementM.mineVentureRP.gui.JobSelectionGUI;
import org.JudgementM.mineVentureRP.jobs.JobType;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import java.util.logging.Logger;
import org.bukkit.Bukkit;

public class JobSelectionListener implements Listener {

    private final MineVentureRP plugin;

    public JobSelectionListener(MineVentureRP plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        plugin.getLogger().info("onClick gestartet");
        Player player = (Player) event.getWhoClicked();

        if(player.hasMetadata("OpenedMenu")) {
            plugin.getLogger().info("Player hat Metadata 'OpenedMenu'");
            String openTitle = PlainTextComponentSerializer.plainText().serialize(player.getOpenInventory().title());
            int page = player.getMetadata("OpenedMenu").get(0).asInt();  // Korrigiert von "JobMenuPage" zu "OpenedMenu"
            plugin.getLogger().info("Open Inventory Titel: " + openTitle + " | Erwartet enthält: Wähle deinen Job");

            if(!openTitle.startsWith("Wähle deinen Job")) {  // Robust gegen Farbcode-Unterschiede
                plugin.getLogger().info("Titel stimmt nicht überein, event ignoriert.");
                return;
            }

            event.setCancelled(true);
            plugin.getLogger().info("Event gecancelt.");

            if(event.getSlot() == 36 && page > 0){
                plugin.getLogger().info("Zurück Button gedrückt.");
                JobSelectionGUI.open(player, page - 1);
            } else if (event.getSlot() == 44){
                plugin.getLogger().info("Weiter Button gedrückt.");
                JobSelectionGUI.open(player, page + 1);
            } else {
                int index = page * 7 + getJobButtonIndexFromSlot(event.getSlot());
                JobType[] jobs = JobType.values();
                if (index < jobs.length) {
                    JobType selected = jobs[index];
                    plugin.getLogger().info("Job ausgewählt: " + selected.getDisplayName());
                    player.sendMessage("Du hast den Job " + selected.getDisplayName() + " gewählt!");
                    player.closeInventory();
                } else {
                    plugin.getLogger().info("Index außerhalb der Job-Liste: " + index);
                }
            }
        } else {
            plugin.getLogger().info("Player hat kein Metadata 'OpenedMenu'");
        }
    }

    private int getJobButtonIndexFromSlot(int slot) {
        return switch (slot) {
            case 10 -> 0;
            case 12 -> 1;
            case 14 -> 2;
            case 16 -> 3;
            case 29 -> 4;
            case 31 -> 5;
            case 33 -> 6;
            default -> -1;
        };
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();

        if(player.hasMetadata("OpenedMenu"))
            player.removeMetadata("OpenedMenu", MineVentureRP.getInstance());
    }
}
