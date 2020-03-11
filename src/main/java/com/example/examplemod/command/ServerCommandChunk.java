package com.example.examplemod.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ClassInheritanceMultiMap;
import net.minecraft.util.text.TextComponentString;

public class ServerCommandChunk extends CommandBase {
    @Override
    public String getName() {
        return "chunk";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "command.examplemod.server.all";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        EntityPlayerMP player = getCommandSenderAsPlayer(sender);
        for (ClassInheritanceMultiMap<Entity> list : player.world.getChunkFromChunkCoords(player.chunkCoordX, player.chunkCoordZ).getEntityLists()) {
            for (Entity entity : list) {
                player.sendMessage(new TextComponentString(entity.getName() + " at " + entity.getPosition()));
            }
        }
    }
}
