package com.example.examplemod.entity;

import com.elementtimes.elementcore.api.annotation.ModEntity;
import com.elementtimes.elementcore.api.annotation.part.Method2;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

import javax.annotation.Nullable;

@ModEntity(id = "0", name = "test",
        eggColorPrimary = 0xFFAABBCC, eggColorSecondary = 0xFF001122,
        canSpawn = true, spawnWeight = 5, spawnMin = 3, spawnMax = 10,
        render = @Method2("com.example.examplemod.entity.TestEntityRenderer"))
public class TestEntity extends EntityLiving {

    public TestEntity(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(2, new EntityAILookIdle(this));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    }

    @Override
    public void fall(float distance, float damageMultiplier) { }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootTableList.ENTITIES_CHICKEN;
    }
}
