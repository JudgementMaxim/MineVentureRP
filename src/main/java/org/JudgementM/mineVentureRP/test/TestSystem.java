package org.JudgementM.mineVentureRP.test;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.Plugin;


public class TestSystem implements Listener{
    private final Plugin plugin;
    public TestSystem(Plugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
        Bukkit.getLogger().info("[MineVentureRP] TestSystem loaded.");
    }

}
