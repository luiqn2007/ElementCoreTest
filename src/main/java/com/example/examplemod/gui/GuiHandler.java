package com.example.examplemod.gui;

import com.elementtimes.elementcore.api.annotation.ModGui;
import com.elementtimes.elementcore.api.template.gui.server.BaseContainer;
import com.elementtimes.elementcore.api.template.tileentity.interfaces.IGuiProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

@ModGui
public class GuiHandler implements IGuiHandler {

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
        if (te instanceof IGuiProvider) {
            return new BaseContainer(te, player);
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        Container container = (Container) getServerGuiElement(ID, player, world, x, y, z);
        if (container instanceof BaseContainer) {
            return new com.elementtimes.elementcore.api.template.gui.client.BaseGuiContainer((BaseContainer) container);
        }
        return null;
    }
}
