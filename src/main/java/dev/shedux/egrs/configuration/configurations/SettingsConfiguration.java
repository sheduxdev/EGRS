package dev.shedux.egrs.configuration.configurations;

import net.j4c0b3y.api.config.ConfigHandler;
import net.j4c0b3y.api.config.StaticConfig;

import java.io.File;

public class SettingsConfiguration extends StaticConfig {
    public SettingsConfiguration(File file, ConfigHandler handler) {
        super(new File(file, "settings.yml"), handler);
    }

    public static class ISLAND {
        @Comment("Main island center X coordinate")
        public static int CENTER_X = 0;

        @Comment("Main island center Z coordinate")
        public static int CENTER_Z = 0;

        @Comment("Radius around center to spawn players")
        public static int SPAWN_RADIUS = 50;

        @Comment("Minimum Y level to search for safe spawn")
        public static int MIN_Y = 45;

        @Comment("Maximum Y level to search for safe spawn")
        public static int MAX_Y = 70;

        @Comment("Distance from center to consider as outer island")
        public static int OUTER_ISLAND_DISTANCE = 500;
    }

    public static class PLATFORM {
        @Comment("Obsidian platform X coordinate")
        public static int X = 100;

        @Comment("Obsidian platform Z coordinate")
        public static int Z = 0;

        @Comment("Detection radius around platform")
        public static int DETECTION_RADIUS = 10;

        @Comment("Minimum Y level for platform detection")
        public static int MIN_Y = 40;

        @Comment("Maximum Y level for platform detection")
        public static int MAX_Y = 55;
    }

    public static class MESSAGES {
        @Comment("Message sent when player teleports to main island")
        public static String WELCOME = "&aWelcome to main island!";

        @Comment("Message sent when safe location cannot be found")
        public static String NO_SAFE_LOCATION = "&cWe couldn't find a safe location!";
    }
}