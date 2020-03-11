package com.example.examplemod.block.tebase;

import com.elementtimes.elementcore.api.template.block.BlockTileBase;
import com.elementtimes.elementcore.api.template.capability.fluid.ITankHandler;
import com.elementtimes.elementcore.api.template.tileentity.SideHandlerType;
import com.elementtimes.elementcore.api.template.tileentity.recipe.MachineRecipe;
import com.elementtimes.elementcore.api.template.tileentity.recipe.MachineRecipeCapture;
import com.elementtimes.elementcore.api.template.tileentity.recipe.MachineRecipeHandler;
import com.example.examplemod.block.te.TeWithFluid;
import com.example.examplemod.block.te.TeWithRecipe;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;

/**
 * TeWithItem 的方块
 * @author luqin2007
 */
public class TeRecipeBlock extends BlockTileBase<TeWithRecipe> {

    public TeRecipeBlock() {
        super(TeWithRecipe.class);
    }

    /**
     * 右键：使用原版 CapabilityItemHandler 操作
     * 空手获取，否则放入
     */
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            ItemStack heldItem = playerIn.getHeldItem(hand);
            if (heldItem.isEmpty()) {
                playerIn.sendMessage(new TextComponentString("No Item"));
                return false;
            }
            TeWithRecipe te = (TeWithRecipe) worldIn.getTileEntity(pos);
            MachineRecipeHandler recipes = te.getRecipes();
            NonNullList<ItemStack> itemList = NonNullList.withSize(1, heldItem);
            NonNullList<FluidStack> fluidList = NonNullList.create();
            if (recipes.acceptInput(itemList, fluidList)) {
                MachineRecipeCapture[] captures = recipes.matchInput(itemList, fluidList);
                playerIn.sendMessage(new TextComponentString("Recipes include item " + heldItem.getItem().getRegistryName() + ", match " + captures.length + " capture."));
            } else {
                playerIn.sendMessage(new TextComponentString("Recipes not include item " + heldItem.getItem().getRegistryName()));
            }
        }
        return true;
    }
}
