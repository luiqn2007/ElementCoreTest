package com.example.examplemod.block.client;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

@SideOnly(Side.CLIENT)
public class BlockColor implements IBlockColor, IItemColor {

    private Random mRandom = new Random();

    @Override
    public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) {
        return mRandom.nextInt(0xFFFFFFFF);
    }

    @Override
    public int colorMultiplier(ItemStack stack, int tintIndex) {
        return mRandom.nextInt(0xFFFFFFFF);
    }
}
