package com.github.dietcola.elevator;

import org.bukkit.Material;

public class ConfigManager {
    private static Material elevator;

    public ConfigManager() {
        Elevator plugin = Elevator.getInstance();
        elevator = Material.matchMaterial(plugin.getConfig().getString("elevator"));
    }

    public static Material getElevator() {
        return elevator;
    }
}
