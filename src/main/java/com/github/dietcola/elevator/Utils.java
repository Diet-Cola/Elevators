package com.github.dietcola.elevator;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class Utils {
    private static ConfigManager config;
    private static World w;

    public static boolean hasPlayerJumped(Player p) {
        Vector velocity = p.getVelocity();
        // Check if the player is moving "up"
        if (velocity.getY() > 0)
        {
            // Default jump velocity
            double jumpVelocity = (double) 0.42F; // Default jump velocity
            PotionEffect jumpPotion = p.getPotionEffect(PotionEffectType.JUMP);
            if (jumpPotion != null)
            {
                // If player has jump potion add it to jump velocity
                jumpVelocity += (double) ((float) jumpPotion.getAmplifier() + 1) * 0.1F;
            }
            // Check if player is not on ladder and if jump velocity calculated is equals to player Y velocity
            if (p.getLocation().getBlock().getType() != Material.LADDER && Double.compare(velocity.getY(), jumpVelocity) == 0)
            {
                return true;
            }
        }
        return false;
    }
    public static boolean isOnElevator(Player p) {
        Location loc = p.getLocation();
        Block b = loc.getBlock().getRelative(BlockFace.DOWN);
        if (b.getType() == config.getElevator()) {
            return true;
        }
        return false;
    }
    public static boolean elevatorAbove(Player p) {
        w = p.getWorld();
        //Scanning above the player for suitable elevator blocks
        for (int i = p.getLocation().getBlock().getY(); i<256; i++) {
            Block b = w.getBlockAt(p.getLocation().getBlockX(), i, p.getLocation().getBlockZ());
            Block b1 = w.getBlockAt(p.getLocation().getBlockX(), i+1, p.getLocation().getBlockZ());
            Block b2 = w.getBlockAt(p.getLocation().getBlockX(), i+2, p.getLocation().getBlockZ());
            if (b.getType() == config.getElevator() && !b1.getType().isSolid() && !b2.getType().isSolid()) {
                p.teleport(new Location(w, b.getLocation().getBlockX()+0.5, b1.getLocation().getBlockY(), b.getLocation().getBlockZ()+0.5));
                return true;
            }
        }
        return false;
    }
    public static boolean elevatorBelow(Player p) {
        w = p.getWorld();
        //Scanning below the player for suitable elevator blocks
        for (int i = p.getLocation().getBlock().getY()-2; i>0; i--) {
            Block b = w.getBlockAt(p.getLocation().getBlockX(), i, p.getLocation().getBlockZ());
            Block b1 = w.getBlockAt(p.getLocation().getBlockX(), i+1, p.getLocation().getBlockZ());
            Block b2 = w.getBlockAt(p.getLocation().getBlockX(), i+2, p.getLocation().getBlockZ());
            if (b.getType() == config.getElevator() && !b1.getType().isSolid() && !b2.getType().isSolid()) {
                p.teleport(new Location(w, b.getLocation().getBlockX()+0.5, b1.getLocation().getBlockY(), b.getLocation().getBlockZ()+0.5));
                return true;
            }
        }
        return false;
    }
}
