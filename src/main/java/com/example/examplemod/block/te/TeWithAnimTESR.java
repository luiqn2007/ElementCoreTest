package com.example.examplemod.block.te;

import com.example.examplemod.ExampleMod;
import com.google.common.collect.ImmutableMap;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.animation.TimeValues;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.model.animation.AnimationStateMachine;
import net.minecraftforge.common.model.animation.CapabilityAnimation;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TeWithAnimTESR extends TileEntity implements ITickable {
    private final static String ANIMATION_STATE_PLAY = "play";
    private final static String ANIMATION_STATE_STOP = "stop";
    private AnimationStateMachine mAnimationStateMachine;
    public boolean running = true;

    @Override
    public boolean hasFastRenderer() {
        return true;
    }

    @SideOnly(Side.CLIENT)
    private AnimationStateMachine getASM() {
        if (mAnimationStateMachine == null) {
            mAnimationStateMachine = (AnimationStateMachine) net.minecraftforge.client.model.ModelLoaderRegistry.loadASM(
                    new ResourceLocation(ExampleMod.MODID, "asms/block/teanimtesr.json"),
                    ImmutableMap.of("cycle_length", new TimeValues.ConstValue(5)));
        }
        return mAnimationStateMachine;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityAnimation.ANIMATION_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityAnimation.ANIMATION_CAPABILITY && FMLCommonHandler.instance().getSide() == Side.CLIENT) {
            return capability.cast((T) getASM());
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void update() {
        if (world.isRemote) {
            String state = getASM().currentState();
            if (running && ANIMATION_STATE_STOP.equals(state)) {
                getASM().transition(ANIMATION_STATE_PLAY);
            } else if (!running && ANIMATION_STATE_PLAY.equals(state)) {
                getASM().transition(ANIMATION_STATE_STOP);
            }
        }
    }
}
