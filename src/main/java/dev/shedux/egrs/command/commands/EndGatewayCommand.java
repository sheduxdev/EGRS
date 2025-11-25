package dev.shedux.egrs.command.commands;

import dev.shedux.egrs.configuration.ConfigurationManager;
import net.j4c0b3y.api.command.annotation.command.Command;
import net.j4c0b3y.api.command.annotation.command.Requires;
import net.j4c0b3y.api.command.annotation.parameter.classifier.Sender;
import net.j4c0b3y.api.command.annotation.registration.Register;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

@Register(name = "endgatewayrandomspawn", aliases = {"egrs"})
@Requires("egrs.command.admin")
@SuppressWarnings("unused")
public class EndGatewayCommand {

    @Command(name = "reload")
    public void reload(@Sender CommandSender sender) {
        long time = ConfigurationManager.reloadConfigurations();
        sender.sendMessage(colorize("&aConfiguration reloaded in &e" + time + "ms&a."));
    }

    private String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}