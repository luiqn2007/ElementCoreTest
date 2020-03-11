package com.example.examplemod.block.client;

import com.example.examplemod.block.BlockMap;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class BlockStateMapper implements IStateMapper {

    @Override
    public Map<IBlockState, ModelResourceLocation> putStateModelLocations(Block blockIn) {
        Map<IBlockState, ModelResourceLocation> resources = new HashMap<>();
        resources.put(blockIn.getDefaultState().withProperty(BlockMap.NAME, BlockMap.EnumName.MAP1), new ModelResourceLocation(blockIn.getRegistryName(), "m1"));
        resources.put(blockIn.getDefaultState().withProperty(BlockMap.NAME, BlockMap.EnumName.MAP2), new ModelResourceLocation(blockIn.getRegistryName(), "m2"));
        return resources;
    }
}
