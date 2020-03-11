package com.example.examplemod.recipe;

import com.elementtimes.elementcore.api.annotation.ModRecipe;
import com.example.examplemod.ExampleMod;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.function.Supplier;

public class Recipes {

    private static final ResourceLocation test = new ResourceLocation(ExampleMod.MODID, "test");

    @ModRecipe
    public static IRecipe recipe0 = new ShapelessOreRecipe(test, Items.APPLE, "apple");

    @ModRecipe
    public static Supplier<IRecipe> recipe1 = () -> new ShapelessOreRecipe(test, Items.IRON_AXE, com.example.examplemod.item.Items.simpleItem2);

    @ModRecipe
    public static Object[] recipe3 = new Object[] {new ShapelessOreRecipe(test, Items.ARROW, Items.NETHER_STAR)};

    @ModRecipe(width = 2, height = 1)
    public static Object[] recipe4 = new Object[] {
            new ItemStack(Items.REDSTONE),
            new ItemStack(Items.PUMPKIN_SEEDS), new ItemStack(Items.ARROW)
    };

    @ModRecipe.RecipeMethod
    public static IRecipe recipe5() {
        ShapelessOreRecipe recipe = new ShapelessOreRecipe(test, Blocks.LAVA, Items.LAVA_BUCKET);
        recipe.setRegistryName(new ResourceLocation(ExampleMod.MODID, "recipe5"));
        return recipe;
    }
}
