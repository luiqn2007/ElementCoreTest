package com.example.examplemod.block.tebase;

import com.elementtimes.elementcore.api.common.ECUtils;
import com.elementtimes.elementcore.api.template.tileentity.interfaces.ITileTESR;
import com.example.examplemod.block.te.TeWithTileTESR;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class TeTileTESRBlock extends BlockGlass implements ITileEntityProvider {
    public TeTileTESRBlock() {
        super(Material.GLASS, true);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TeWithTileTESR();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
        if (!worldIn.isRemote) {
            TeWithTileTESR te = (TeWithTileTESR) worldIn.getTileEntity(pos);
            ItemStack stack = playerIn.getHeldItem(hand);
            if (stack.isEmpty()) {
                te.getRenderItems().forEach((s, renderObject) -> {
                    if (renderObject.isRender()) {
                        te.setRender(s, false, pos);
                    }
                });
            } else {
                String key = stack.getItem().getRegistryName().toString() + "@" + stack.getMetadata();
                ITileTESR.RenderObject renderObject = ECUtils.collection.computeIfAbsent(te.getRenderItems(), key, () -> {
                    ITileTESR.RenderObject obj = new ITileTESR.RenderObject(stack.copy());
                    obj.translate(.5, .375, .5).scale(3, 3, 3);
                    te.registerRender(key, obj);
                    te.markRenderClient(pos);
                    return obj;
                });
                te.setRender(key, true, pos);
            }
        }
        return true;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
}
