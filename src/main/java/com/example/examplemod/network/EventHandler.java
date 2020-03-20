package com.example.examplemod.network;

import com.elementtimes.elementcore.api.annotation.ModEventNetwork;
import com.example.examplemod.ExampleMod;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.network.NetworkEvent;

@ModEventNetwork
public class EventHandler {

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onClient(NetworkEvent.ClientCustomPayloadEvent event) {
        ServerPlayerEntity player = event.getSource().get().getSender();
        PacketBuffer buffer = event.getPayload();
        player.sendMessage(new StringTextComponent("receive message at server"));
        player.sendMessage(new StringTextComponent("post message to client"));
        ExampleMod.CONTAINER.elements.postTo(buffer, player);
    }

    @SubscribeEvent
    public static void onServer(NetworkEvent.ServerCustomPayloadEvent event) {
        PlayerEntity player = net.minecraft.client.Minecraft.getInstance().player;
        player.sendMessage(new StringTextComponent("receive message at client"));
    }
}
