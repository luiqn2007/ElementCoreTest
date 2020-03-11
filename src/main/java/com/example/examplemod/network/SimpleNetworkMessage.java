package com.example.examplemod.network;

import com.elementtimes.elementcore.api.annotation.ModSimpleNetwork;
import com.elementtimes.elementcore.api.annotation.part.Getter;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;

public class SimpleNetworkMessage implements IMessage {

    public String message = "";

    public SimpleNetworkMessage() { }

    public SimpleNetworkMessage(String message) {
        this.message = message;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        message = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, message);
    }

    @ModSimpleNetwork(value = @Getter(SimpleNetworkHandler.class), side = Side.SERVER)
    public static class Server extends SimpleNetworkMessage {

        public Server() { }

        public Server(String message) {
            this.message = message;
        }
    }

    @ModSimpleNetwork(value = @Getter(SimpleNetworkHandler.class), side = Side.CLIENT)
    public static class Client extends SimpleNetworkMessage {

        public Client() { }

        public Client(String message) {
            this.message = message;
        }
    }
}
