package com.example.examplemod.block.te;

import com.elementtimes.elementcore.api.template.tileentity.interfaces.ITileTESR;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nonnull;
import java.util.HashMap;

public class TeWithTileTESR extends TileEntity implements ITileTESR {

    private HashMap<String, RenderObject> mRenderItems = new HashMap<>();
    private NBTTagCompound mProperties = new NBTTagCompound();

    @Override
    public HashMap<String, RenderObject> getRenderItems() {
        return mRenderItems;
    }

    @Nonnull
    @Override
    public NBTTagCompound getRenderProperties() {
        return mProperties;
    }

    @Override
    public void setRenderProperties(@Nonnull NBTTagCompound nbtTagCompound) {
        mProperties = nbtTagCompound;
    }

    @Override
    public void markRenderClient(BlockPos pos) {
        ITileTESR.super.markRenderClient(pos);
        markDirty();
    }

    @Override
    public boolean hasFastRenderer() {
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        ITileTESR.super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        ITileTESR.super.deserializeNBT(compound);
        return super.writeToNBT(compound);
    }
}
