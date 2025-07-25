package org.JudgementM.mineVentureRP;

import org.bukkit.plugin.java.JavaPlugin;
import org.JudgementM.mineVentureRP.test.TestSystem;
import org.JudgementM.mineVentureRP.listeners.PlayerJoinListener;
import org.JudgementM.mineVentureRP.database.DatabaseManager;
import org.JudgementM.mineVentureRP.database.JobDAO;

import java.sql.SQLException;

public final class MineVentureRP extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("MineVentureRP enabled!");
        try {
            DatabaseManager.connect();
            JobDAO.createTable();
        }catch (SQLException e){
            e.printStackTrace();
        }
        new TestSystem(this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("MineVentureRP disabled!");
    }


}
