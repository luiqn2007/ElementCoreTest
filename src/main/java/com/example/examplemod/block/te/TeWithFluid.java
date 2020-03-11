package com.example.examplemod.block.te;

import com.elementtimes.elementcore.api.common.ECUtils;
import com.elementtimes.elementcore.api.template.capability.fluid.ITankHandler;
import com.elementtimes.elementcore.api.template.capability.fluid.TankHandler;
import com.elementtimes.elementcore.api.template.capability.fluid.TankHandlerVisitor;
import com.elementtimes.elementcore.api.template.capability.item.IItemHandler;
import com.elementtimes.elementcore.api.template.tileentity.SideHandlerType;
import com.elementtimes.elementcore.api.template.tileentity.interfaces.ITileFluidHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;

/**
 * 附带 CapabilityItemHandler 能力的 TileEntity
 * @author luqin2007
 */
public class TeWithFluid extends TileEntity implements ITileFluidHandler, ITickable {

    private ITankHandler mHandlerInput = new TankHandler(this::isFillValid, TankHandler.FALSE, 1, 1000);
    private ITankHandler mHandlerOutput = new TankHandler(TankHandler.FALSE, TankHandler.TRUE, 1, 1000);
    private ITankHandler mHandlerAll = new TankHandlerVisitor(mHandlerInput, mHandlerOutput);

    @Override
    public ITankHandler getTanks(SideHandlerType type) {
        switch (type) {
            case INPUT: return mHandlerInput;
            case OUTPUT: return mHandlerOutput;
            case ALL: return mHandlerAll;
            default: return TankHandler.EMPTY;
        }
    }

    @Override
    public SideHandlerType getTankType(@Nullable EnumFacing facing) {
        if (facing == null) {
            return SideHandlerType.ALL;
        } else if (facing == EnumFacing.DOWN) {
            return SideHandlerType.OUTPUT;
        }
        return SideHandlerType.INPUT;
    }

    @Override
    public boolean isFillValid(int slot, FluidStack fluidStack) {
        return fluidStack != null && fluidStack.getFluid() != FluidRegistry.LAVA;
    }

    // override
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        ITileFluidHandler.super.deserializeNBT(compound);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        ITileFluidHandler.super.serializeNBT();
        super.readFromNBT(compound);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return ITileFluidHandler.super.hasCapability(capability, facing) || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        T capItem = ITileFluidHandler.super.getCapability(capability, facing);
        return capItem != null ? capItem : super.getCapability(capability, facing);
    }

    @Override
    public void update() {
        if (!world.isRemote) {
            ITankHandler input = getTanks(SideHandlerType.INPUT);
            ITankHandler output = getTanks(SideHandlerType.OUTPUT);
            FluidStack drain = input.drainIgnoreCheck(0, input.getCapacity(0), false);
            int fill = output.fillIgnoreCheck(0, drain, true);
            input.drainIgnoreCheck(0, fill, true);
        }
    }
}
