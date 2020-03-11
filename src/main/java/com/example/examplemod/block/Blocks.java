package com.example.examplemod.block;

import com.elementtimes.elementcore.api.annotation.ModBlock;
import com.elementtimes.elementcore.api.annotation.enums.HarvestType;
import com.elementtimes.elementcore.api.annotation.part.*;
import com.elementtimes.elementcore.api.annotation.tools.*;
import com.elementtimes.elementcore.api.template.block.BaseClosableMachine;
import com.elementtimes.elementcore.api.template.block.BlockTileBase;
import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.te.*;
import com.example.examplemod.block.tebase.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * TODO BugList
 * color 系列，染色无效
 * ITileTESR, AnimTESR 有问题，无动画和渲染，可能是我用错了
 */
public class Blocks {

    // Block
    @ModBlock
    @ModBlock.Tooltip("@ModBlock 无参测试")
    public static Block simpleBlockNoTab = new Block(Material.ROCK);
    @ModBlock(creativeTabKey = "blocks")
    @ModBlock.Tooltip({"@ModBlock 测试",
            "带有 creativeTabKey 参数"})
    public static Block simpleBlock0 = new Block(Material.ROCK);
    @ModBlock(registerName = "sblock1", creativeTabKey = "blocks")
    @ModBlock.Tooltip({"@ModBlock 测试",
            "带有 registerName=sblock1, creativeTabKey 参数"})
    public static Block simpleBlock1 = new Block(Material.ROCK);
    @ModBlock(unlocalizedName = "sblock2", creativeTabKey = "blocks")
    @ModBlock.Tooltip({"@ModBlock 测试",
            "带有 unlocalizedName=sblock2, creativeTabKey 参数"})
    public static Block simpleBlock2 = new Block(Material.ROCK);

    // HarvestLevel
    // 需要铲子 等级 3 (Diamond)
    @ModBlock(creativeTabKey = "blocks")
    @ModBlock.HarvestLevel(toolClass = HarvestType.shovel, level = 3)
    @ModBlock.Tooltip({"@ModBlock.HarvestLevel 测试",
            "钻石等级铲子可以正常挖掘"})
    public static Block blockHarvestLevel = new Block(Material.ROCK);

    // 自然生成
    @ModBlock(creativeTabKey = "blocks")
    @ModBlock.WorldGen
    @ModBlock.Tooltip("@ModBlock.WorldGen 测试")
    public static Block blockGen = new Block(Material.ROCK);
    @ModBlock(creativeTabKey = "blocks")
    @ModBlock.WorldGenObj(@Getter(BlockGenerator.class))
    @ModBlock.Tooltip("@ModBlock.WorldGenObj 测试")
    public static Block blockGenObj = new Block(Material.ROCK);

    // StateMap && Tooltips
    @ModBlock(creativeTabKey = "blocks")
    @ModBlock.Tooltip({"@ModBlock.Tooltip, @ModBlock.StateMap 测试",
            "简单的 Tooltip, 只能增加不能修改"})
    @ModBlock.StateMap(suffix = "blockmap", name = @Getter2(value = "com.example.examplemod.block.BlockMap", name = "NAME"))
    public static Block blockMap = new BlockMap();
    @ModBlock(creativeTabKey = "blocks")
    @ModTooltip(@Method(value = Methods.class, name = "blockTooltips"))
    @ModBlock.StateMapper(@Getter2("com.example.examplemod.block.client.BlockStateMapper"))
    public static Block blockMapper = new BlockMap();

    // color
    @ModBlock(creativeTabKey = "blocks")
    @ModBlock.BlockColor(value = 0xFF0097A7, item = 0xFFFFEB3B)
    @ModBlock.Tooltip({"@ModBlock.BlockColor 测试",
            "物品染色: #0097A7",
            "方块染色: #FFEB3B"})
    public static Block colorBlock0 = new BlockGlass(Material.GLASS, true);
    @ModBlock(creativeTabKey = "blocks")
    @ModColor(block = @Method2(value = "com.example.examplemod.block.client.Methods", name = "blockColor"), item = @Method2(value = "com.example.examplemod.block.client.Methods", name = "itemColor"))
    @ModBlock.Tooltip({"@ModColor 测试",
            "物品染色: 当前世界时间 % 0xFFFFFFFF",
            "方块染色: 当前方块位置 (x*y*z) % 0xFFFFFFFF"})
    public static Block colorBlock1 = new BlockGlass(Material.GLASS, true);
    @ModBlock(creativeTabKey = "blocks")
    @ModColorObj(block = @Getter2("com.example.examplemod.block.client.BlockColor"), item = @Getter2("com.example.examplemod.block.client.BlockColor"))
    @ModBlock.Tooltip({"@ModBlock.ModColorObj 测试",
            "物品染色: 随机数",
            "方块染色: 随机数"})
    public static Block colorBlock2 = new BlockGlass(Material.GLASS, true);
    @ModBlock(creativeTabKey = "blocks")
    @ModBlock.BlockColor(0xFF0097A7)
    @ModColor(item = @Method2(value = "com.example.examplemod.block.client.Methods", name = "itemColor"))
    @ModBlock.Tooltip({"@ModBlock.BlockColor & @ModColor 测试",
            "物品染色: 当前世界时间 % 0xFFFFFFFF 由 @ModColor 提供",
            "方块染色: #0097A7 由 @ModBlock.BlockColor 提供"})
    public static Block colorBlockMix0 = new BlockGlass(Material.GLASS, true);
    @ModBlock(creativeTabKey = "blocks")
    @ModColor(item = @Method2(value = "com.example.examplemod.block.client.Methods", name = "itemColor"))
    @ModColorObj(block = @Getter2("com.example.examplemod.block.client.BlockColor"))
    @ModBlock.Tooltip({"@ModColor & @ModColorObj 测试",
            "物品染色: 当前世界时间 % 0xFFFFFFFF 由 @ModColor 提供",
            "方块染色: 随机数 由 @ModColorObj 提供"})
    public static Block colorBlockMix1 = new BlockGlass(Material.GLASS, true);
    @ModBlock(creativeTabKey = "blocks")
    @ModBlock.BlockColor(item = 0xFFFFEB3B)
    @ModColorObj(block = @Getter2("com.example.examplemod.block.client.BlockColor"))
    @ModBlock.Tooltip({"@ModBlock.BlockColor & @ModColorObj 测试",
            "物品染色: #FFEB3B 由 @ModBlock.BlockColor 提供",
            "方块染色: 随机数 由 @ModColorObj 提供"})
    public static Block colorBlockMix2 = new BlockGlass(Material.GLASS, true);

    // BurnTime
    @ModBurnTime(value = -1, sub = @BurnTime(method = @Method(value = Methods.class, name = "burningTime")))
    @ModBlock.Tooltip({"@ModBurnTime 测试",
            "泥土燃烧时间: 初始 300, 每次访问增加 1"})
    public static Block dirt = net.minecraft.init.Blocks.DIRT;

    // OreDictionary
    @ModBlock
    @ModOreDict("apple")
    @ModBlock.Tooltip({"@ModOreDict 测试",
            "该方块拥有矿辞 apple"})
    public static Block appleBlock = new BlockGlass(Material.WOOD, true);

    // TileEntity
    @ModBlock(creativeTabKey = "blocks")
    @ModBlock.TileEntity(SimpleTe.class)
    @ModBlock.Tooltip({"@ModBlock.TileEntity 测试",
            "附带一个 TileEntity, 内存一个 int 值 i",
            "每次右键点击，i 自增 1，通过消息告知玩家"})
    public static Block te = new SimpleTeBlock();
    @ModBlock(creativeTabKey = "blocks")
    @ModBlock.Tooltip({"BlockTileBase 测试", "附带 SimpleTe", "右键显示 i 值"})
    public static Block teBase = new SimpleTeBase();
    @ModBlock(creativeTabKey = "blocks")
    @ModBlock.Tooltip({"IDismantleBlock 测试", "右键显示 i",
            "手持苹果左键可以获取带有 TE 的 NBT 的物品"})
    public static Block teDismantle = new BlockDismantle();
    @ModBlock(creativeTabKey = "blocks")
    @ModBlock.TileEntity(BaseTe.class)
    @ModBlock.Tooltip({"BaseTileEntity 测试",
            "附带一个 GUI, 3 个物品槽, 2 个流体槽, 100 FE 能量缓存, 1 个配方",
            "物品槽包括 2 个流体输入槽, 2 个流体输出槽",
            "每 tick 获得 20 FE",
            "配方: 5 苹果, 1000mb 熔岩, 100 FE => 1 苹果方块, 1000mb 水"})
    public static Block teMachine = new BaseClosableMachine<>(BaseTe.class, ExampleMod.INSTANCE);
    @ModBlock(creativeTabKey = "blocks")
    @ModBlock.TileEntity(TeWithGui.class)
    @ModBlock.Tooltip({"IGuiProvider 测试", "附带一个 GUI"})
    public static Block teGui = new BlockTileBase<>(TeWithGui.class, ExampleMod.INSTANCE);
    @ModBlock(creativeTabKey = "blocks")
    @ModBlock.TileEntity(TeWithItem.class)
    @ModBlock.Tooltip({"ITileItemHandler 测试",
            "附带一个输入槽, 一个输出槽",
            "每 tick 会将输入槽的物品移动到输出槽",
            "空手右键, 获取输出槽物品; 否则, 存入物品",
            "不能存入苹果"})
    public static Block teItem = new TeItemBlock();
    @ModBlock(creativeTabKey = "blocks")
    @ModBlock.TileEntity(TeWithFluid.class)
    @ModBlock.Tooltip({"ITileFluidHandler 测试",
            "附带一个输入槽, 一个输出槽",
            "每 tick 会将输入槽的流体移动到输出槽",
            "空桶右键取出流体, 其他容器则尝试 drain 和 fill",
            "不能存入熔岩"})
    public static Block teFluid = new TeFluidBlock();
    @ModBlock(creativeTabKey = "blocks")
    @ModBlock.TileEntity(TeWithEnergy.class)
    @ModBlock.Tooltip("")
    public static Block teEnergy = new TeEnergyBlock();
    @ModBlock(creativeTabKey = "blocks")
    @ModBlock.TileEntity(TeWithLifecycle.class)
    @ModBlock.Tooltip({"IMachineTickable, BaseClosableMachine 测试",
            "主要测试 IMachineLifecycle",
            "使用 IMachineLifecycle 控制机器运行",
            "等待 5s，运行 5s，在运行的第 1s 暂停 5s"})
    public static Block teLifecycle = new BaseClosableMachine<>(TeWithLifecycle.class);
    @ModBlock(creativeTabKey = "blocks")
    @ModBlock.TileEntity(TeWithRecipe.class)
    @ModBlock.Tooltip({"IMachineRecipe 测试",
            "存在一个配方, 输入 3 个矿辞 apple 的物品, 输出 3 个苹果",
            "空手右键发送 No Item",
            "否则, 提示 Recipes include item 或 Recipes not include item"})
    public static Block teRecipe = new TeRecipeBlock();
    @ModBlock(creativeTabKey = "blocks")
    @ModBlock.TileEntity(TeWithTileTESR.class)
    @ModBlock.Tooltip({"ITileTESR 测试",
            "携带物品右键可在方块中显示物品"})
    public static Block teTileTESR = new TeTileTESRBlock();
    @ModBlock(creativeTabKey = "blocks")
    @ModBlock.TileEntity(TeWithTESR.class)
    @ModBlock.TESR("com.example.examplemod.block.client.SimpleTESR")
    @ModBlock.Tooltip({"@ModBlock.TESR 测试",
            "绘制一个矩形"})
    public static Block teTESR = new BlockTileBase<>(TeWithTESR.class);
    @ModBlock(creativeTabKey = "blocks")
    @ModBlock.TileEntity(TeWithAnimTESR.class)
    @ModBlock.AnimTESR
    @ModBlock.Tooltip({"@ModBlock.AnimTESR 测试",
            "直接用的 ET压缩机 的动画"})
    public static Block teAnimTESR = new BlockTileBase<TeWithAnimTESR>(TeWithAnimTESR.class) {
        @Override
        public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
            super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
            if (worldIn.isRemote) {
                TeWithAnimTESR te = (TeWithAnimTESR) worldIn.getTileEntity(pos);
                te.running = !te.running;
            }
            return true;
        }
    };
}
