package dev.shedux.egrs.util;

import dev.shedux.egrs.configuration.configurations.SettingsConfiguration;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public class SafeLocationValidator {

    public Location findSafeYLevel(World world, int x, int z) {
        for (int y = SettingsConfiguration.ISLAND.MAX_Y; y >= SettingsConfiguration.ISLAND.MIN_Y; y--) {
            if (isSafeLocation(world, x, y, z)) {
                return createCenteredLocation(world, x, y, z);
            }
        }
        return null;
    }

    private boolean isSafeLocation(World world, int x, int y, int z) {
        Block ground = world.getBlockAt(x, y, z);
        Block above1 = world.getBlockAt(x, y + 1, z);
        Block above2 = world.getBlockAt(x, y + 2, z);

        return ground.getType().isSolid()
                && above1.getType() == Material.AIR
                && above2.getType() == Material.AIR;
    }

    private Location createCenteredLocation(World world, int x, int y, int z) {
        return new Location(world, x + 0.5, y + 1, z + 0.5);
    }
}