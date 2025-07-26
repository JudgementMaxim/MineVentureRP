package org.JudgementM.mineVentureRP.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;
import org.JudgementM.mineVentureRP.SideBarDisplaySystem.SideBarDisplay;
import org.JudgementM.mineVentureRP.database.PlayerDataDAO;
import org.JudgementM.mineVentureRP.gui.JobSelectionGUI;

import java.sql.SQLException;

public class PlayerJoinListener implements Listener {

    private final PlayerDataDAO dao;

    public PlayerJoinListener(PlayerDataDAO dao) {
        this.dao = dao;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        try {
            PlayerDataDAO.PlayerData data = dao.getPlayerData(player.getUniqueId());

            if (data != null) {
                SideBarDisplay.showSidebar(player, data.getJob(), data.getTown(), data.getCoins());
            } else {
                SideBarDisplay.showSidebar(player, "Kein Job", "Keine Stadt", 0);
                JobSelectionGUI.open(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            SideBarDisplay.showSidebar(player, "Fehler", "Fehler", 0);
        }
    }
}
