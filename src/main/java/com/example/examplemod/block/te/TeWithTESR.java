package com.example.examplemod.block.te;

import net.minecraft.tileentity.TileEntity;

public class TeWithTESR extends TileEntity {

    @Override
    public boolean hasFastRenderer() {
        return true;
    }
}
