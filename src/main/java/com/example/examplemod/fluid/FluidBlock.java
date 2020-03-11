package com.example.examplemod.fluid;

import com.example.examplemod.ExampleMod;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class FluidBlock extends BlockFluidClassic {

    private Entity entity;

    public FluidBlock() {
        super(Fluids.fluid2, Material.WATER);
        setRegistryName(ExampleMod.MODID, "fb0");
    }

    public FluidBlock(Fluid fluid) {
        super(fluid, Material.LAVA);
        setRegistryName(ExampleMod.MODID, "fb1");
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);
        if (!worldIn.isRemote && entityIn != entity) {
            entity = entityIn;
            entityIn.sendMessage(new TextComponentString(entityIn.getName() + " in " + getFluid().getName()));
        }
    }
}
