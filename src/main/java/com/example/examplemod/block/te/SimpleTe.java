package com.example.examplemod.block.te;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class SimpleTe extends TileEntity {

    public int i = 0;

    public int add() {
        i++;
        markDirty();
        return i;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("__i__", i);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        i = compound.getInteger("__i__");
        super.readFromNBT(compound);
    }
}
