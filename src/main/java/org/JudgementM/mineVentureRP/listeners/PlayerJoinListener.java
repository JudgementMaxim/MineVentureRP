package org.JudgementM.mineVentureRP.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;
import org.JudgementM.mineVentureRP.SideBarDisplaySystem.SideBarDisplay;
public class PlayerJoinListener implements Listener{
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        String job = "Farmer";
        String town = "Neustadt";
        int coins = 100;

        SideBarDisplay.showSidebar(player, job, town, coins);
    }
}
