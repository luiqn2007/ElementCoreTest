package com.example.examplemod.network;

import com.example.examplemod.ExampleMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SimpleNetworkHandler implements IMessageHandler<SimpleNetworkMessage, SimpleNetworkMessage> {

    @Override
    public SimpleNetworkMessage onMessage(SimpleNetworkMessage message, MessageContext ctx) {
        EntityPlayer player;
        if (message instanceof SimpleNetworkMessage.Client) {
            net.minecraft.client.Minecraft.getMinecraft().player.sendChatMessage(message.message);
        } else {
            ctx.getServerHandler().player.sendMessage(new TextComponentString(message.message));
            ExampleMod.CONTAINER.elements.sendToPlayer(new SimpleNetworkMessage.Client("from client[event] to server[simple]"), ctx.getServerHandler().player);
        }
        return null;
    }
}
