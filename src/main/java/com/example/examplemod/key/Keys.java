package com.example.examplemod.key;

import com.elementtimes.elementcore.api.annotation.ModKey;
import com.elementtimes.elementcore.api.annotation.part.Method2;
import com.example.examplemod.ExampleMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class Keys {

    @ModKey(@Method2(value = "com.example.examplemod.key.Keys", name = "onTestNet"))
    public static KeyBinding keyT = new KeyBinding("key.test.net", Keyboard.KEY_T, "ElementCoreTest");

    @ModKey(@Method2(value = "com.example.examplemod.key.Keys", name = "onTestKey"))
    public static KeyBinding keyM = new KeyBinding("key.test.key", Keyboard.KEY_M, "ElementCoreTest");

    public static void onTestNet(InputEvent.KeyInputEvent event, KeyBinding key) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        player.sendChatMessage("Key and Network Test Net");
        ExampleMod.CONTAINER.elements.postToServer(buf-> ByteBufUtils.writeUTF8String(buf, "form key to server[event]"));
    }

    public static void onTestKey(InputEvent.KeyInputEvent event, KeyBinding key) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        player.sendChatMessage("Key and Network Test Key");
    }
}
