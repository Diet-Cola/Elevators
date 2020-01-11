package com.github.dietcola.elevator;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.logging.Level;

public class ElevatorListener implements Listener {
    @EventHandler
    public void onPlayerJump(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (Utils.hasPlayerJumped(p) && Utils.isOnElevator(p)) {
            if (Utils.elevatorAbove(p)) {
                Elevator.getInstance().getLogger().log(Level.INFO, "Player: " + p.getUniqueId() + " used an Elevator up at: " + p.getLocation());
            }
        }
        if (p.isSneaking() && Utils.isOnElevator(p)) {
            if (Utils.elevatorBelow(p)) {
                Elevator.getInstance().getLogger().log(Level.INFO, "Player: " + p.getUniqueId() + " used an Elevator down at: " + p.getLocation());
            }
        }
    }
}
