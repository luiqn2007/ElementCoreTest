package com.example.examplemod.block.te;

import com.elementtimes.elementcore.api.template.tileentity.interfaces.IMachineRecipe;
import com.elementtimes.elementcore.api.template.tileentity.recipe.IngredientPart;
import com.elementtimes.elementcore.api.template.tileentity.recipe.MachineRecipeCapture;
import com.elementtimes.elementcore.api.template.tileentity.recipe.MachineRecipeHandler;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TeWithRecipe extends TileEntity implements IMachineRecipe {

    private MachineRecipeHandler mRecipeHandler = new MachineRecipeHandler(1, 1, 0, 0);
    private IntSet mIgnore = new IntOpenHashSet();

    public TeWithRecipe() {
        mRecipeHandler.newRecipe().addCost(100)
                .addItemInput(IngredientPart.forItem("apple", 3))
                .addItemOutput(IngredientPart.forItem(Items.APPLE, 3)).endAdd();
    }

    @Override
    public MachineRecipeHandler getRecipes() {
        return mRecipeHandler;
    }

    @Nullable
    @Override
    public MachineRecipeCapture getWorkingRecipe() {
        return null;
    }

    @Nonnull
    @Override
    public IntSet getRecipeSlotIgnore() {
        return mIgnore;
    }

    @Override
    public void setWorkingRecipe(MachineRecipeCapture machineRecipeCapture) { }
}
