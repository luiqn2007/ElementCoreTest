package com.example.examplemod.block.tebase;

import com.elementtimes.elementcore.api.template.block.BlockTileBase;
import com.elementtimes.elementcore.api.template.capability.item.IItemHandler;
import com.elementtimes.elementcore.api.template.tileentity.SideHandlerType;
import com.example.examplemod.block.te.TeWithItem;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * TeWithItem 的方块
 * @author luqin2007
 */
public class TeItemBlock extends BlockTileBase<TeWithItem> {

    public TeItemBlock() {
        super(TeWithItem.class);
    }

    /**
     * 右键：使用原版 CapabilityItemHandler 操作
     * 空手获取，否则放入
     */
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            ItemStack item = playerIn.getHeldItem(hand);
            TeWithItem te = (TeWithItem) worldIn.getTileEntity(pos);
            // handler: ItemHandlerVisitor(ALL)
            IItemHandler handler;
            if (item.isEmpty()) {
                handler = te.getItemHandler(SideHandlerType.OUTPUT);
                playerIn.setHeldItem(hand, handler.extractItem(0, handler.getSlotLimit(0), false));
            } else {
                handler = te.getItemHandler(SideHandlerType.INPUT);
                playerIn.setHeldItem(hand, handler.insertItem(0, item, false));
            }
        }
        return true;
    }
}
