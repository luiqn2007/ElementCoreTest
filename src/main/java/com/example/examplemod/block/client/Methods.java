package com.example.examplemod.block.client;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class Methods {

    public static int itemColor(ItemStack stack, int tintIndex) {
        return (int) Minecraft.getMinecraft().world.getTotalWorldTime() % 0xFFFFFFFF;
    }

    public static int blockColor(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) {
        return (pos == null ? (int) Minecraft.getMinecraft().world.getTotalWorldTime() : pos.getX() * pos.getY() * pos.getZ()) % 0xFFFFFFFF;
    }
}
