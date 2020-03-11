package com.example.examplemod.block.tebase;

import com.elementtimes.elementcore.api.template.block.BlockTileBase;
import com.example.examplemod.block.te.TeWithEnergy;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class TeEnergyBlock extends BlockTileBase<TeWithEnergy> {

    public TeEnergyBlock() {
        super(TeWithEnergy.class);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            TeWithEnergy te = (TeWithEnergy) worldIn.getTileEntity(pos);
            int energyStored = te.getEnergyHandler().getEnergyStored();
            String color = te.reverse ? TextFormatting.RED.toString() : TextFormatting.BLUE.toString();
            playerIn.sendMessage(new TextComponentString(color + "Stored Energy: " + energyStored));
        }
        return true;
    }
}
