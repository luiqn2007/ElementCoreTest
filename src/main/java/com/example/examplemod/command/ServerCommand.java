package com.example.examplemod.command;

import com.elementtimes.elementcore.api.annotation.ModCommand;
import net.minecraft.command.ICommandSender;
import net.minecraftforge.server.command.CommandTreeBase;

@ModCommand
public class ServerCommand extends CommandTreeBase {

    public ServerCommand() {
        addSubcommand(new ServerCommandAll());
        addSubcommand(new ServerCommandChunk());
        addSubcommand(new ServerCommandPlayer());
    }

    @Override
    public String getName() {
        return "exampleTest";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "command.examplemod.server";
    }
}
