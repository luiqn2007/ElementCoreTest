package com.example.examplemod.tab;

import com.elementtimes.elementcore.api.annotation.ModTab;
import com.elementtimes.elementcore.api.annotation.part.Method;
import com.elementtimes.elementcore.api.annotation.tools.ModTabEditor;
import com.elementtimes.elementcore.api.template.tabs.CreativeTabDynamic;
import com.elementtimes.elementcore.api.template.tabs.CreativeTabStatic;
import com.example.examplemod.block.Blocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

/**
 * TODO BugList
 * CreativeTabStatic(blocks) 标签方块不显示图标，物品正常
 */
public class Tabs {

    @ModTab
    public static CreativeTabs blocks = new CreativeTabStatic("blocks", Items.BUCKET);

    @ModTab("items")
    public static CreativeTabs tab2 = new CreativeTabDynamic("tab2", 10, Blocks.simpleBlock0, Blocks.simpleBlock1, Blocks.simpleBlock2);

    @ModTab
    @ModTabEditor(@Method(value = Tabs.class, name = "appleEdit"))
    public static CreativeTabs apple = new CreativeTabStatic("oreTab", Items.APPLE);

    public static void appleEdit(NonNullList<ItemStack> items) {
        items.clear();
        items.add(new ItemStack(Items.REDSTONE));
        items.addAll(OreDictionary.getOres("apple"));
    }
}
