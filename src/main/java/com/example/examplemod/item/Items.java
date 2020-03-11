package com.example.examplemod.item;

import com.elementtimes.elementcore.ElementCore;
import com.elementtimes.elementcore.api.annotation.ModItem;
import com.elementtimes.elementcore.api.annotation.part.Getter2;
import com.elementtimes.elementcore.api.annotation.tools.ModOreDict;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

/**
 * TODO BugList
 * Item 染色有问题
 * @author luqin2007
 */
public class Items {

    @ModItem(creativeTabKey = "blocks")
    @ModItem.Tooltip("测试物品")
    public static Item simpleItem1 = new Item();

    @ModItem(creativeTabKey = "blocks")
    @ModItem.ItemColor(0xFFAADFFF)
    @ModItem.RetainInCrafting
    @ModItem.Tooltip({"测试物品",
            "物品染色: 0xFFAADFFF",
            "合成后保留"})
    public static Item simpleItem2 = new Item();

    @ModItem(creativeTabKey = "blocks")
    @ModItem.Damageable(value = 10, noRepair = true)
    @ModItem.Tooltip({"测试物品", "有耐久, 不可修复"})
    public static Item simpleItem3 = new Item() {
        @Override
        public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
            playerIn.getHeldItem(handIn).damageItem(1, playerIn);
            return super.onItemRightClick(worldIn, playerIn, handIn);
        }
    };

    @ModItem(creativeTabKey = "blocks")
    @ModItem.ItemMeshDefinitionObj(value = @Getter2("com.example.examplemod.item.ItemMeshDefinition"),
            all = @Getter2(value = "com.example.examplemod.item.ItemMeshDefinition", name = "all4"))
    @ModItem.Tooltip("MeshDefinitionObj 测试")
    public static Item simpleItem4 = new Item();

    @ModItem(creativeTabKey = "blocks")
    @ModItem.ItemMeshDefinitionObj(value = @Getter2("com.example.examplemod.item.ItemMeshDefinition"),
            all = @Getter2(value = "com.example.examplemod.item.ItemMeshDefinition", name = "all5"))
    @ModItem.Tooltip("MeshDefinitionFunc 测试")
    public static Item simpleItem5 = new Item();

    @ModItem.Tooltip("子物品, 物品染色参考 EC 本体 debugger 物品")
    public static Item simpleItem6 = ElementCore.Items.debugger;

    @ModOreDict("apple")
    @ModItem.Tooltip("物品 @ModItem.Tooltip 测试")
    public static Item apple = net.minecraft.init.Items.APPLE;
}
