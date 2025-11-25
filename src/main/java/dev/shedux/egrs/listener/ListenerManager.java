package dev.shedux.egrs.listener;

import dev.shedux.egrs.EGRS;
import dev.shedux.egrs.listener.listeners.GatewayListener;
import dev.shedux.egrs.manager.Manager;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class ListenerManager extends Manager {

    private final List<Listener> listeners = new ArrayList<>();

    @Override
    protected void onLoad() {
        registerListeners(new GatewayListener());
    }

    @Override
    protected void onUnload() {
        listeners.clear();
    }

    private void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, EGRS.getINSTANCE());
            this.listeners.add(listener);
        }
    }
}