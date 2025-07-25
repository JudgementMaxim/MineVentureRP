package org.JudgementM.mineVentureRP;

import org.bukkit.plugin.java.JavaPlugin;
import org.JudgementM.mineVentureRP.test.TestSystem;

public final class MineVentureRP extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("MineVentureRP enabled!");
        new TestSystem(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("MineVentureRP disabled!");
    }
}
