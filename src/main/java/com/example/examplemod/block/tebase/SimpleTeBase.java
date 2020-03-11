package com.example.examplemod.block.tebase;

import com.elementtimes.elementcore.api.template.block.BlockTileBase;
import com.example.examplemod.block.te.SimpleTe;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class SimpleTeBase extends BlockTileBase<SimpleTe> {

    public SimpleTeBase() {
        super(SimpleTe.class);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        SimpleTe te = (SimpleTe) worldIn.getTileEntity(pos);
        if (!worldIn.isRemote) {
            playerIn.sendMessage(new TextComponentString("count: " + (te.add())));
        }
        return true;
    }
}
