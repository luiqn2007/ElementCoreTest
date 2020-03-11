package com.example.examplemod.block;

import com.example.examplemod.block.te.SimpleTe;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class SimpleTeBlock extends Block implements ITileEntityProvider {

    public SimpleTeBlock() {
        super(Material.ROCK);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new SimpleTe();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
        if (!worldIn.isRemote) {
            SimpleTe te = (SimpleTe) worldIn.getTileEntity(pos);
            int i = te.add();
            TextFormatting[] values = TextFormatting.values();
            String format = values[i % values.length].toString();
            playerIn.sendMessage(new TextComponentString(format + "te at " + pos + " i=" + i));
        }
        return true;
    }
}
