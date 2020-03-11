package com.example.examplemod.block.tebase;

import com.elementtimes.elementcore.api.template.block.BlockTileBase;
import com.elementtimes.elementcore.api.template.capability.fluid.ITankHandler;
import com.elementtimes.elementcore.api.template.tileentity.SideHandlerType;
import com.example.examplemod.block.te.TeWithFluid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.*;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;
import net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper;

/**
 * TeWithItem 的方块
 * @author luqin2007
 */
public class TeFluidBlock extends BlockTileBase<TeWithFluid> {

    public TeFluidBlock() {
        super(TeWithFluid.class);
    }

    /**
     * 右键：使用原版 CapabilityItemHandler 操作
     * 空手获取，否则放入
     */
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            ItemStack item = playerIn.getHeldItem(hand);
            TeWithFluid te = (TeWithFluid) worldIn.getTileEntity(pos);
            ITankHandler tankOutput = te.getTanks(SideHandlerType.OUTPUT);
            ITankHandler tankInput = te.getTanks(SideHandlerType.INPUT);
            if (item.getItem() == Items.BUCKET) {
                FluidStack drain = tankOutput.drain(0, Fluid.BUCKET_VOLUME, false);
                if (drain != null && drain.amount == Fluid.BUCKET_VOLUME) {
                    tankOutput.drain(0, Fluid.BUCKET_VOLUME, true);
                    playerIn.setHeldItem(hand, FluidUtil.getFilledBucket(drain));
                }
            } else {
                boolean isDrain = false;
                IFluidHandlerItem fluidHandler = item.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
                if (fluidHandler == null) {
                    return false;
                }
                FluidStack drain = fluidHandler.drain(Fluid.BUCKET_VOLUME, false);
                if (drain != null) {
                    int fill = tankInput.fill(drain, false);
                    if (fill <= drain.amount && fill > 0) {
                        drain = fluidHandler.drain(fill, true);
                        tankInput.fill(drain, true);
                        isDrain = true;
                    }
                }
                if (!isDrain) {
                    drain = fluidHandler.drain(Fluid.BUCKET_VOLUME, false);
                    if (drain != null) {
                        int fill = tankOutput.fill(drain, false);
                        if (fill < drain.amount && fill > 0) {
                            drain = fluidHandler.drain(fill, true);
                            tankOutput.fill(drain, true);
                        }
                    }
                }
                playerIn.setHeldItem(hand, fluidHandler.getContainer());
            }
        }
        return true;
    }
}
