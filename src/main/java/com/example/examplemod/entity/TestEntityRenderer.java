package com.example.examplemod.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class TestEntityRenderer extends RenderLiving<TestEntity> {

    public TestEntityRenderer(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelTest(), .3f);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(TestEntity entity) {
        return new ResourceLocation("textures/entity/cow/cow.png");
    }
}
