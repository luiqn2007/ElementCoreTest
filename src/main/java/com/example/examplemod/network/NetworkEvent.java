package com.example.examplemod.network;

import com.elementtimes.elementcore.api.annotation.ModEventNetwork;
import com.example.examplemod.ExampleMod;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@ModEventNetwork
public class NetworkEvent {

    @SubscribeEvent
    public static void onServer(FMLNetworkEvent.ServerCustomPacketEvent event) {
        ByteBuf buf = event.getPacket().payload();
        EntityPlayerMP player = ((NetHandlerPlayServer) event.getHandler()).player;
        player.sendMessage(new TextComponentString(ByteBufUtils.readUTF8String(buf)));
        ExampleMod.CONTAINER.elements.postToPlayer(b -> ByteBufUtils.writeUTF8String(b, "from server[event] to client[event]"), player);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onClient(FMLNetworkEvent.ClientCustomPacketEvent event) {
        ByteBuf buf = event.getPacket().payload();
        net.minecraft.client.entity.EntityPlayerSP player = net.minecraft.client.Minecraft.getMinecraft().player;
        player.sendMessage(new TextComponentString(ByteBufUtils.readUTF8String(buf)));
        ExampleMod.CONTAINER.elements.sendToServer(new SimpleNetworkMessage.Server("from client[event] to server[simple]"));
    }
}
