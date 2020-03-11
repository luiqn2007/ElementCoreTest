package com.example.examplemod.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class BlockGenerator extends WorldGenerator {

    private IBlockState block = null;

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        if (block == null) {
            block = Blocks.blockGenObj.getDefaultState();
        }
        if (!worldIn.isRemote && worldIn.isBlockLoaded(position)) {
            int i = rand.nextInt(50);
            Chunk chunk = worldIn.getChunkFromBlockCoords(position);
            ChunkPos pos = chunk.getPos();
            if (i == 0) {
                for (int x = pos.getXStart() + i; x < pos.getXEnd() - i; x++) {
                    for (int z = pos.getZStart() + 5; z < pos.getZEnd() - 5; z++) {
                        for (int y = 0; y < worldIn.getActualHeight(); y++) {
                            setBlockAndNotifyAdequately(worldIn, new BlockPos(x, y, z), block);
                        }
                    }
                }
            } else if (i > 40) {
                for (int x = pos.getXStart() + 5; x < pos.getXEnd() - 5; x++) {
                    for (int z = pos.getZStart() + 5; z < pos.getZEnd() - 5; z++) {
                        for (int y = 0; y < i * 3; y++) {
                            setBlockAndNotifyAdequately(worldIn, new BlockPos(x, y, z), block);
                        }
                    }
                }
            } else {
                int x = position.getX() + 5, z = position.getZ() + 5;
                for (int y = 0; y < worldIn.getActualHeight(); y++) {
                    setBlockAndNotifyAdequately(worldIn, new BlockPos(x, y, z), block);
                }
            }
        }
        return true;
    }
}
