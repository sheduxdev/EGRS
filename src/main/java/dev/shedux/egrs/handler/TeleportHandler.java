package dev.shedux.egrs.handler;

import dev.shedux.egrs.configuration.configurations.SettingsConfiguration;
import dev.shedux.egrs.util.LocationFinder;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

public class TeleportHandler {
    private final PlayerTeleportEvent event;
    private final LocationFinder locationFinder;
    private final Player player;
    private final Location from;
    private final Location to;

    public TeleportHandler(PlayerTeleportEvent event, LocationFinder locationFinder) {
        this.event = event;
        this.locationFinder = locationFinder;
        this.player = event.getPlayer();
        this.from = event.getFrom();
        this.to = event.getTo();
    }

    public void process() {
        if (!isFromOuterIsland() || !isTargetingObsidianPlatform()) {
            return;
        }

        Location randomLocation = locationFinder.findRandomSafeLocation(to.getWorld());
        if (randomLocation != null) {
            event.setTo(randomLocation);
            player.sendMessage(colorize(SettingsConfiguration.MESSAGES.WELCOME));
        } else {
            player.sendMessage(colorize(SettingsConfiguration.MESSAGES.NO_SAFE_LOCATION));
        }
    }

    private boolean isFromOuterIsland() {
        double distance = Math.sqrt(Math.pow(from.getX(), 2) + Math.pow(from.getZ(), 2));
        return distance > SettingsConfiguration.ISLAND.OUTER_ISLAND_DISTANCE;
    }

    private boolean isTargetingObsidianPlatform() {
        if (to == null) return false;

        double distX = Math.abs(to.getX() - SettingsConfiguration.PLATFORM.X);
        double distZ = Math.abs(to.getZ() - SettingsConfiguration.PLATFORM.Z);

        return distX < SettingsConfiguration.PLATFORM.DETECTION_RADIUS
                && distZ < SettingsConfiguration.PLATFORM.DETECTION_RADIUS
                && to.getY() > SettingsConfiguration.PLATFORM.MIN_Y
                && to.getY() < SettingsConfiguration.PLATFORM.MAX_Y;
    }

    private String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}