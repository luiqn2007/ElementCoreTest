package com.example.examplemod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockMap extends Block {

    public static PropertyEnum<EnumName> NAME = PropertyEnum.create("name", EnumName.class);

    public BlockMap() {
        super(Material.ROCK);
        setDefaultState(getDefaultState().withProperty(NAME, EnumName.MAP1));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, NAME);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(NAME) == EnumName.MAP1 ? 0 : 1;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return super.getStateFromMeta(meta).withProperty(NAME, meta == 0 ? EnumName.MAP1 : EnumName.MAP2);
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(this, 1, getMetaFromState(state));
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        worldIn.setBlockState(pos, getStateFromMeta(stack.getMetadata()));
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        items.add(new ItemStack(this, 1, 0));
        items.add(new ItemStack(this, 1, 1));
    }

    public enum  EnumName implements IStringSerializable {
        MAP1("map1"), MAP2("map2");

        public final String name;

        EnumName(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    }
}
