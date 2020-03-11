package com.example.examplemod.block.te;

import com.elementtimes.elementcore.api.template.tileentity.BaseTileEntity;
import com.elementtimes.elementcore.api.template.tileentity.SideHandlerType;
import com.elementtimes.elementcore.api.template.tileentity.interfaces.IMachineLifecycle;
import com.elementtimes.elementcore.api.template.tileentity.lifecycle.FluidMachineLifecycle;
import com.elementtimes.elementcore.api.template.tileentity.recipe.IngredientPart;
import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class BaseTe extends BaseTileEntity {

    public BaseTe() {
        super(100, 3, 3, 1, 1000, 1, 1000);
        addLifeCycle(new FluidMachineLifecycle(this));
        addLifeCycle(new IMachineLifecycle() {
            @Override
            public void onTickStart() {
                getEnergyHandler().receiveEnergy(20, false);
            }
        });
        getRecipes().newRecipe()
                .addCost(100)
                .addItemInput(IngredientPart.forItem(Items.APPLE, 5))
                .addFluidInput(IngredientPart.forFluid(FluidRegistry.LAVA, 1000))
                .addItemOutput(IngredientPart.forItem(Blocks.appleBlock, 1))
                .addFluidOutput(IngredientPart.forFluid(FluidRegistry.WATER, 1000))
                .endAdd();
    }

    @Override
    public ResourceLocation getBackground() {
        return new ResourceLocation(ExampleMod.MODID, "textures/gui/basete.png");
    }

    @Override
    public GuiSize getSize() {
        return GUI_SIZE_176_156_74.withNoInteractLimit().withTitleY(50)
                .withEnergy(0, 0, 156, 173, 156, 3).withProcess(0, 5, 156, 173, 156, 3);
    }

    @Nonnull
    @Override
    public FluidSlotInfo[] getFluids() {
        return new FluidSlotInfo[] {
                FluidSlotInfo.createInput(0, 10, 10),
                FluidSlotInfo.createOutputHorizontal(0, 30, 10)
        };
    }

    @Nonnull
    @Override
    public Slot[] getSlots() {
        return new Slot[] {
                new SlotItemHandler(getItemHandler(SideHandlerType.INPUT), 0, 30, 60),
                new SlotItemHandler(getItemHandler(SideHandlerType.INPUT), 1, 30, 90),
                new SlotItemHandler(getItemHandler(SideHandlerType.INPUT), 2, 30, 120),
                new SlotItemHandler(getItemHandler(SideHandlerType.OUTPUT), 0, 60, 60),
                new SlotItemHandler(getItemHandler(SideHandlerType.OUTPUT), 1, 60, 90),
                new SlotItemHandler(getItemHandler(SideHandlerType.OUTPUT), 2, 60, 120)
        };
    }

    @Override
    public String getTitle() {
        return Blocks.teMachine.getLocalizedName();
    }

    @Override
    public int getGuiId() {
        return 0;
    }

    @Override
    public int getEnergyTick() {
        return 10;
    }
}
