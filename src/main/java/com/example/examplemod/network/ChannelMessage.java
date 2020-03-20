package com.example.examplemod.network;

import com.elementtimes.elementcore.api.annotation.ModSimpleNetwork;
import com.elementtimes.elementcore.api.annotation.part.Method;
import com.example.examplemod.ExampleMod;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import java.util.function.Supplier;

@ModSimpleNetwork(encoder = @Method(value = ChannelMessage.class, name = "encoder"),
        decoder = @Method(value = ChannelMessage.class, name = "decoder"),
        handler = @Method(value = ChannelMessage.class, name = "handler"))
public class ChannelMessage {

    public String message;

    public ChannelMessage(String msg) {
        message = msg;
    }

    public ChannelMessage() {
        message = "";
    }

    public static void encoder(ChannelMessage msg, PacketBuffer buffer) {
        buffer.writeString(msg.message);
    }

    public static ChannelMessage decoder(PacketBuffer msg) {
        String message = msg.readString();
        return new ChannelMessage(message);
    }

    public static void handler(ChannelMessage msg, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        if (context.getDirection().getReceptionSide() == LogicalSide.CLIENT) {
            PlayerEntity player = net.minecraft.client.Minecraft.getInstance().player;
            player.sendMessage(new StringTextComponent("receive message at client"));
            player.sendMessage(new StringTextComponent("post message to server"));
            PacketBuffer buffer = new PacketBuffer(Unpooled.buffer());
            buffer.writeString(msg.message);
            ExampleMod.CONTAINER.elements.postToServer(buffer);
        } else if (context.getDirection().getReceptionSide() == LogicalSide.SERVER) {
            ServerPlayerEntity player = context.getSender();
            player.sendMessage(new StringTextComponent("receive message at server"));
            player.sendMessage(new StringTextComponent("send message to client"));
            ExampleMod.CONTAINER.elements.sendTo(msg, player);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void test() {
        net.minecraft.client.Minecraft.getInstance().player.sendChatMessage("send message to server");
        ExampleMod.CONTAINER.elements.sendToServer(new ChannelMessage(""));
    }
}
