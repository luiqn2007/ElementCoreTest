package com.example.examplemod.block;

import net.minecraft.item.ItemStack;

import java.util.List;

public class Methods {

    private static int burningTime = 300;

    public static void blockTooltips(ItemStack item, List<String> tooltips) {
        int meta = item.getMetadata();
        tooltips.add("@ModTooltip, @ModBlock.StateMapper 测试");
        if (meta == 0) {
            tooltips.add("BlockState: examplemod:blockmapper#m1");
        } else {
            tooltips.add("BlockState: examplemod:blockmapper#m2");
        }
    }

    public static int burningTime(ItemStack stack) {
        return burningTime++;
    }
}
