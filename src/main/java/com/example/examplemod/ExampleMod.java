package com.example.examplemod;

import com.elementtimes.elementcore.ElementCore;
import com.elementtimes.elementcore.api.common.ECModContainer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ExampleMod.MODID, name = ExampleMod.NAME, version = ExampleMod.VERSION, dependencies = "required-before:elementcore")
public class ExampleMod {
    public static final String MODID = "examplemod";
    public static final String NAME = "Example Mod";
    public static final String VERSION = "@version@";

    @Mod.Instance
    public static ExampleMod INSTANCE;

    public static ECModContainer CONTAINER;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        CONTAINER = ElementCore.builder()
                .enableDebugMessage()
                .useEventNetwork()
                .build(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) { }
}
