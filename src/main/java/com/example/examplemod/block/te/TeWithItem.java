package com.example.examplemod.block.te;

import com.elementtimes.elementcore.api.common.ECUtils;
import com.elementtimes.elementcore.api.template.capability.item.IItemHandler;
import com.elementtimes.elementcore.api.template.capability.item.ItemHandler;
import com.elementtimes.elementcore.api.template.capability.item.ItemHandlerVisitor;
import com.elementtimes.elementcore.api.template.tileentity.SideHandlerType;
import com.elementtimes.elementcore.api.template.tileentity.interfaces.ITileItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * 附带 CapabilityItemHandler 能力的 TileEntity
 * @author luqin2007
 */
public class TeWithItem extends TileEntity implements ITileItemHandler, ITickable {

    private IItemHandler mHandlerInput = new ItemHandler(1, this::isInputValid);
    private IItemHandler mHandlerOutput = new ItemHandler(1, this::isInputValid);
    private IItemHandler mHandlerAll = new ItemHandlerVisitor(mHandlerInput, mHandlerOutput);

    @Nonnull
    @Override
    public IItemHandler getItemHandler(@Nonnull SideHandlerType type) {
        switch (type) {
            case INPUT: return mHandlerInput;
            case OUTPUT: return mHandlerOutput;
            case ALL: return mHandlerAll;
            default: return ItemHandler.EMPTY;
        }
    }

    @Nonnull
    @Override
    public SideHandlerType getItemType(@Nullable EnumFacing facing) {
        if (facing == null) {
            return SideHandlerType.ALL;
        } else if (facing == EnumFacing.DOWN) {
            return SideHandlerType.OUTPUT;
        }
        return SideHandlerType.INPUT;
    }

    @Override
    public boolean isInputValid(int i, ItemStack itemStack) {
        return itemStack.getItem() != Items.APPLE;
    }

    // override
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        ITileItemHandler.super.deserializeNBT(compound);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        ITileItemHandler.super.serializeNBT();
        super.readFromNBT(compound);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return ITileItemHandler.super.hasCapability(capability, facing) || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        T capItem = ITileItemHandler.super.getCapability(capability, facing);
        return capItem != null ? capItem : super.getCapability(capability, facing);
    }

    @Override
    public void update() {
        if (!world.isRemote) {
            IItemHandler extractFrom = getItemHandler(SideHandlerType.INPUT);
            IItemHandler insertTo = getItemHandler(SideHandlerType.OUTPUT);
            for (int i = 0; i < extractFrom.getSlots(); i++) {
                int slotLimit = extractFrom.getSlotLimit(i);
                ItemStack itemExtract = extractFrom.extractItem(i, slotLimit, true);
                ItemStack itemInsert = ECUtils.item.insertItemIgnoreCheck(insertTo, itemExtract, false);
                if (!itemInsert.isEmpty()) {
                    extractFrom.extractItem(i, itemInsert.getCount(), false);
                }
            }
        }
    }
}
