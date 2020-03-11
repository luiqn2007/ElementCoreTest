package com.example.examplemod.block.client;

import com.example.examplemod.block.te.TeWithTESR;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraftforge.client.model.animation.FastTESR;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

@SideOnly(Side.CLIENT)
public class SimpleTESR extends FastTESR<TeWithTESR> {

    public Random mRandom = new Random(System.currentTimeMillis());

    @Override
    public void renderTileEntityFast(TeWithTESR te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {
        buffer.color(.3f, .3f, .3f, 1f);
        buffer.pos(-10, +10, 0).endVertex();
        buffer.pos(+10, +10, 0).endVertex();
        buffer.pos(+10, -10, 0).endVertex();
        buffer.pos(-10, -10, 0).endVertex();
    }
}
