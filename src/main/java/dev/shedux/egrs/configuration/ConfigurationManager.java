package dev.shedux.egrs.configuration;

import dev.shedux.egrs.EGRS;
import dev.shedux.egrs.configuration.configurations.SettingsConfiguration;
import dev.shedux.egrs.manager.Manager;
import net.j4c0b3y.api.config.ConfigHandler;
import net.j4c0b3y.api.config.StaticConfig;

import java.io.File;

@SuppressWarnings({"unused"})
public class ConfigurationManager extends Manager {

    private final EGRS plugin = EGRS.getINSTANCE();
    private static ConfigHandler configHandler;
    private SettingsConfiguration settings;

    @Override
    protected void onLoad() {
        File folder = plugin.getDataFolder();
        configHandler = new ConfigHandler(plugin.getLogger());

        this.settings = new SettingsConfiguration(folder, configHandler);
        this.settings.load();
    }

    @Override
    protected void onUnload() {
        settings.load();
    }

    public static long reloadConfigurations() {
        long start = System.currentTimeMillis();
        configHandler.getRegistered().forEach(StaticConfig::load);
        return System.currentTimeMillis() - start;
    }

    public static long saveConfigurations() {
        long start = System.currentTimeMillis();
        configHandler.getRegistered().forEach(StaticConfig::save);
        return System.currentTimeMillis() - start;
    }
}