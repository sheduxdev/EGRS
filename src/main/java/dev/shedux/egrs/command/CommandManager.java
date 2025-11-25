package dev.shedux.egrs.command;

import dev.shedux.egrs.EGRS;
import dev.shedux.egrs.command.commands.EndGatewayCommand;
import dev.shedux.egrs.manager.Manager;
import lombok.Getter;
import net.j4c0b3y.api.command.bukkit.BukkitCommandHandler;

@Getter
public class CommandManager extends Manager {

    private BukkitCommandHandler bukkitCommandHandler;

    @Override
    protected void onLoad() {
        this.bukkitCommandHandler = new BukkitCommandHandler(EGRS.getINSTANCE());
        bukkitCommandHandler.register(new EndGatewayCommand());
    }

    @Override
    protected void onUnload() {
    }
}