package dev.shedux.egrs.util;

import dev.shedux.egrs.configuration.configurations.SettingsConfiguration;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Random;

public class LocationFinder {
    private final Random random;
    private final SafeLocationValidator validator;

    public LocationFinder(Random random) {
        this.random = random;
        this.validator = new SafeLocationValidator();
    }

    public Location findRandomSafeLocation(World world) {
        for (int attempt = 0; attempt < 20; attempt++) {
            int x = generateRandomX();
            int z = generateRandomZ();

            Location location = validator.findSafeYLevel(world, x, z);
            if (location != null) {
                return location;
            }
        }
        return null;
    }

    private int generateRandomX() {
        return SettingsConfiguration.ISLAND.CENTER_X +
                random.nextInt(SettingsConfiguration.ISLAND.SPAWN_RADIUS * 2) -
                SettingsConfiguration.ISLAND.SPAWN_RADIUS;
    }

    private int generateRandomZ() {
        return SettingsConfiguration.ISLAND.CENTER_Z +
                random.nextInt(SettingsConfiguration.ISLAND.SPAWN_RADIUS * 2) -
                SettingsConfiguration.ISLAND.SPAWN_RADIUS;
    }
}