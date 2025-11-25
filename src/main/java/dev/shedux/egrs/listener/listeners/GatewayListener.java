package dev.shedux.egrs.listener.listeners;

import dev.shedux.egrs.handler.TeleportHandler;
import dev.shedux.egrs.util.LocationFinder;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.Random;

public class GatewayListener implements Listener {

    private final Random random = new Random();
    private final LocationFinder locationFinder = new LocationFinder(random);

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        if (!isValidGatewayTeleport(event)) {
            return;
        }

        TeleportHandler handler = new TeleportHandler(event, locationFinder);
        handler.process();
    }

    private boolean isValidGatewayTeleport(PlayerTeleportEvent event) {
        return event.getCause() == PlayerTeleportEvent.TeleportCause.END_GATEWAY
                && event.getFrom().getWorld().getEnvironment() == World.Environment.THE_END;
    }
}