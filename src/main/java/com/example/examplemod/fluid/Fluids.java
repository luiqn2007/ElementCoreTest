package com.example.examplemod.fluid;

import com.elementtimes.elementcore.api.annotation.ModFluid;
import com.elementtimes.elementcore.api.annotation.enums.FluidBlockType;
import com.elementtimes.elementcore.api.annotation.part.Getter;
import com.elementtimes.elementcore.api.annotation.part.Method;
import com.elementtimes.elementcore.api.annotation.tools.ModTooltip;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.List;

public class Fluids {

    @ModFluid
    @ModTooltip(@Method(value = Fluids.class, name = "tooltips0"))
    public static Fluid fluid0 = new Fluid("examplemod.fluid0",
            new ResourceLocation("examplemod", "fluid/f0_still"),
            new ResourceLocation("examplemod", "fluid/f0_fluid"), 0xFF3B3B3B);

    public static void tooltips0(ItemStack stack, List<String> tooltips) {
        tooltips.add("普通流体");
        tooltips.add("无法倒出");
    }

    @ModFluid(density = -10)
    @ModFluid.FluidBlock(type = FluidBlockType.ClassicLava)
    @ModTooltip(@Method(value = Fluids.class, name = "tooltips1"))
    public static Fluid fluid1 = new Fluid("examplemod.fluid1",
            new ResourceLocation("examplemod", "fluid/f1_still"),
            new ResourceLocation("examplemod", "fluid/f1_fluid"), 0xFF6E4A26);

    public static void tooltips1(ItemStack stack, List<String> tooltips) {
        tooltips.add("普通气体");
        tooltips.add("Lava 材质");
    }

    @ModFluid
    @ModFluid.FluidBlockObj(@Getter(FluidBlock.class))
    @ModTooltip(@Method(value = Fluids.class, name = "tooltips2"))
    public static Fluid fluid2 = new Fluid("examplemod.fluid2",
            new ResourceLocation("examplemod", "fluid/f2_still"),
            new ResourceLocation("examplemod", "fluid/f2_fluid"), 0xFF979750);

    public static void tooltips2(ItemStack stack, List<String> tooltips) {
        tooltips.add("普通流体");
        tooltips.add("自定义方块 WATER 材质");
    }

    @ModFluid
    @ModFluid.FluidBlockFunc(@Method(FluidBlock.class))
    @ModTooltip(@Method(value = Fluids.class, name = "tooltips3"))
    public static Fluid fluid3 = new Fluid("examplemod.fluid3",
            new ResourceLocation("examplemod", "fluid/f3_still"),
            new ResourceLocation("examplemod", "fluid/f3_fluid"), 0xFF969642);

    public static void tooltips3(ItemStack stack, List<String> tooltips) {
        tooltips.add("普通流体");
        tooltips.add("自定义方块 LAVA 材质");
    }
}
