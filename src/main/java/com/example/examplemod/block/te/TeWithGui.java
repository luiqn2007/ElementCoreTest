package com.example.examplemod.block.te;

import com.elementtimes.elementcore.api.template.tileentity.interfaces.IGuiProvider;
import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.Blocks;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class TeWithGui extends TileEntity implements IGuiProvider {

    private List<EntityPlayerMP> mOpenedPlayers = new ArrayList<>();
    private ResourceLocation mBackground = new ResourceLocation(ExampleMod.MODID, "textures/gui/guibg");
    private GuiSize mGuiSize = new GuiSize().withSize(640, 710, 0, 0).withNoInteractLimit().withNoInventory().withTitleY(10);

    @Override
    public ResourceLocation getBackground() {
        return mBackground;
    }

    @Override
    public GuiSize getSize() {
        return mGuiSize;
    }

    @Override
    public String getTitle() {
        return Blocks.teGui.getLocalizedName();
    }

    @Override
    public List<EntityPlayerMP> getOpenedPlayers() {
        return mOpenedPlayers;
    }

    @Override
    public int getGuiId() {
        return 0;
    }

    @Override
    public float getProcess() {
        return 0;
    }
}
