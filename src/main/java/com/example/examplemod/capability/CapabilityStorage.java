package com.example.examplemod.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class CapabilityStorage implements Capability.IStorage<ICapabilityValue> {

    public static CapabilityStorage storage = new CapabilityStorage();

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<ICapabilityValue> capability, ICapabilityValue instance, EnumFacing side) {
        return new NBTTagInt(instance.getValue());
    }

    @Override
    public void readNBT(Capability<ICapabilityValue> capability, ICapabilityValue instance, EnumFacing side, NBTBase nbt) {
        instance.setValue(((NBTTagInt) nbt).getInt());
    }
}
