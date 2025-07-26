package org.JudgementM.mineVentureRP;

import org.bukkit.plugin.java.JavaPlugin;
import org.JudgementM.mineVentureRP.listeners.PlayerJoinListener;
import org.JudgementM.mineVentureRP.database.DatabaseManager;
import org.JudgementM.mineVentureRP.database.PlayerDataDAO;

import java.sql.SQLException;

public final class MineVentureRP extends JavaPlugin {

    private PlayerDataDAO playerDataDAO;

    @Override
    public void onEnable() {
        getLogger().info("MineVentureRP enabled!");

        try {
            DatabaseManager.connect();
            playerDataDAO = new PlayerDataDAO(DatabaseManager.getConnection());
            // Falls du JobDAO hast, hier auch createTable aufrufen
            // JobDAO.createTable(DatabaseManager.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(playerDataDAO), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("MineVentureRP disabled!");
        try {
            DatabaseManager.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
