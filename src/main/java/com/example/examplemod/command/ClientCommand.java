package com.example.examplemod.command;

import com.elementtimes.elementcore.api.annotation.ModCommand;
import com.example.examplemod.capability.TestCapability;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.IClientCommand;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

@ModCommand(client = true)
public class ClientCommand extends CommandBase implements IClientCommand {

    @Override
    public String getName() {
        return "exampleTestClient";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "command.examplemod.client=Test capability";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("aliseTest", "exampleTest2");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length > 1) {
            int i = parseInt(args[1]);
            getCommandSenderAsPlayer(sender).getCapability(TestCapability.CAPABILITY, null).setValue(i);
        }
        sender.sendMessage(new TextComponentString("value=" + getCommandSenderAsPlayer(sender).getCapability(TestCapability.CAPABILITY, null).getValue()));
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        return Arrays.asList("none", "any", "all");
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override
    public boolean allowUsageWithoutPrefix(ICommandSender sender, String message) {
        return false;
    }

    @Override
    public int compareTo(ICommand o) {
        return getName().compareTo(o.getName());
    }
}
