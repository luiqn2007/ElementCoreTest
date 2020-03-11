package com.example.examplemod.item;

import com.example.examplemod.ExampleMod;
import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Collection;
import java.util.Random;

@SideOnly(Side.CLIENT)
public class ItemMeshDefinition implements net.minecraft.client.renderer.ItemMeshDefinition {

    @Override
    public ModelResourceLocation getModelLocation(ItemStack stack) {
        int sub = (int) (Minecraft.getMinecraft().world.getTotalWorldTime() % 3);
        String resourcePath = stack.getItem().getRegistryName().getResourcePath();
        return new ModelResourceLocation(new ResourceLocation(ExampleMod.MODID, resourcePath + sub), "inventory");
    }

    private static Random sRandom = new Random(System.currentTimeMillis());
    public static ModelResourceLocation model(ItemStack stack) {
        int sub = sRandom.nextInt(3);
        String resourcePath = stack.getItem().getRegistryName().getResourcePath();
        return new ModelResourceLocation(new ResourceLocation(ExampleMod.MODID, resourcePath + sub), "inventory");
    }

    public static ResourceLocation[] all4 = new ResourceLocation[] {
            new ModelResourceLocation(new ResourceLocation(ExampleMod.MODID, "simpleitem40"), "inventory"),
            new ModelResourceLocation(new ResourceLocation(ExampleMod.MODID, "simpleitem41"), "inventory"),
            new ModelResourceLocation(new ResourceLocation(ExampleMod.MODID, "simpleitem42"), "inventory")};

    public static Collection<ResourceLocation> all5 = Lists.newArrayList(
            new ModelResourceLocation(new ResourceLocation(ExampleMod.MODID, "simpleitem50"), "inventory"),
            new ModelResourceLocation(new ResourceLocation(ExampleMod.MODID, "simpleitem51"), "inventory"),
            new ModelResourceLocation(new ResourceLocation(ExampleMod.MODID, "simpleitem52"), "inventory"));
}
