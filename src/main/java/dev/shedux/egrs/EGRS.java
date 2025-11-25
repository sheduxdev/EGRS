package dev.shedux.egrs;

import dev.shedux.egrs.command.CommandManager;
import dev.shedux.egrs.configuration.ConfigurationManager;
import dev.shedux.egrs.listener.ListenerManager;
import dev.shedux.egrs.manager.ManagerStorage;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class EGRS extends JavaPlugin {

    @Getter
    private static EGRS INSTANCE;

    @Getter
    private static ManagerStorage managerStorage;

    @Override
    public void onEnable() {
        INSTANCE = this;
        managerStorage = new ManagerStorage();

        managerStorage.registerAndLoad(new ConfigurationManager());
        managerStorage.registerAndLoad(new CommandManager());
        managerStorage.registerAndLoad(new ListenerManager());

        managerStorage.setAllWaiting();
        managerStorage.finishAll();

        ConfigurationManager.saveConfigurations();
        ConfigurationManager.reloadConfigurations();

        getLogger().info("EGRS enabled successfully!");
    }

    @Override
    public void onDisable() {
        if (managerStorage != null) {
            managerStorage.unloadAll();
        }
        getLogger().info("EGRS disabled!");
    }
}