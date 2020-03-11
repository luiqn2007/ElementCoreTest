package com.example.examplemod.block.te;

import com.elementtimes.elementcore.api.template.capability.EnergyHandler;
import com.elementtimes.elementcore.api.template.tileentity.SideHandlerType;
import com.elementtimes.elementcore.api.template.tileentity.interfaces.ITileEnergyHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class TeWithEnergy extends TileEntity implements ITileEnergyHandler, ITickable {

    private EnergyHandler mHandler = new EnergyHandler(10000, 100, 100);
    public boolean reverse = false;

    @Override
    public EnergyHandler getEnergyHandler() {
        return mHandler;
    }

    @Override
    public SideHandlerType getEnergyType(EnumFacing enumFacing) {
        return SideHandlerType.ALL;
    }

    @Override
    public void update() {
        int energyStored = mHandler.getEnergyStored();
        if (energyStored == 0) {
            reverse = false;
        } else if (energyStored >= mHandler.getMaxEnergyStored()) {
            reverse = true;
        }
        if (reverse) {
            mHandler.extractEnergy(100, false);
        } else {
            mHandler.receiveEnergy(100, false);
        }
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return ITileEnergyHandler.super.hasCapability(capability, facing) || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        T cap = ITileEnergyHandler.super.getCapability(capability, facing);
        return cap != null ? cap : super.getCapability(capability, facing);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        ITileEnergyHandler.super.deserializeNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        ITileEnergyHandler.super.writeToNBT(compound);
        return super.writeToNBT(compound);
    }
}
