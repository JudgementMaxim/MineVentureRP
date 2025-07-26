package org.JudgementM.mineVentureRP;

import org.bukkit.plugin.java.JavaPlugin;
import org.JudgementM.mineVentureRP.listeners.PlayerJoinListener;
import org.JudgementM.mineVentureRP.database.DatabaseManager;
import org.JudgementM.mineVentureRP.database.PlayerDataDAO;
import org.JudgementM.mineVentureRP.listeners.JobSelectionListener;

import java.sql.SQLException;

public final class MineVentureRP extends JavaPlugin {

    private PlayerDataDAO playerDataDAO;
    private static MineVentureRP instance;


    @Override
    public void onEnable() {
        getLogger().info("MineVentureRP enabled!");
        instance = this;
        getServer().getPluginManager().registerEvents(new JobSelectionListener(this), this);
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

    public static MineVentureRP getInstance() {
        return instance;
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
