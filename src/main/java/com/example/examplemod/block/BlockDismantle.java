package com.example.examplemod.block;

import com.elementtimes.elementcore.api.template.block.interfaces.IDismantleBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockDismantle extends SimpleTeBlock implements IDismantleBlock {

    @Override
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
        super.onBlockClicked(worldIn, pos, playerIn);
        if (!worldIn.isRemote) {
            ItemStack mainhand = playerIn.getHeldItemMainhand();
            if (!mainhand.isEmpty() && mainhand.getItem() == Items.APPLE) {
                dismantleBlock(worldIn, pos);
            }
        }
    }
}
