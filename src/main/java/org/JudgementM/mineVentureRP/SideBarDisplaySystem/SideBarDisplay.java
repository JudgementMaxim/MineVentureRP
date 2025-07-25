package org.JudgementM.mineVentureRP.SideBarDisplaySystem;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class SideBarDisplay {

    public static void showSidebar(Player player, String job, String town, int coins){
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective objective = board.registerNewObjective("sidebar", "dummy", "§6§lMineVenture");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        // Platzhalter-Zeile für Abstand
        objective.getScore(" ").setScore(4);
        objective.getScore("§eJob: §f" + job).setScore(3);
        objective.getScore("§eTown: §f" + town).setScore(2);
        objective.getScore("§eCoins: §f" + coins).setScore(1);

        player.setScoreboard(board);
    }

}
