package com.github.dietcola.elevator;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Elevator extends JavaPlugin {
    private static Elevator instance;

    @Override
    public void onEnable() {
        instance = this;
        register();
        saveDefaultConfig();
        ConfigManager config = new ConfigManager();
    }
    private void register() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ElevatorListener(), this);
    }
    public static Elevator getInstance() {
        return instance;
    }
}
