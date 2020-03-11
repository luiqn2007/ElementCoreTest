package com.example.examplemod.capability;

import com.elementtimes.elementcore.api.template.tileentity.BaseTileEntity;
import com.example.examplemod.ExampleMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CapabilityBinder {

    @SubscribeEvent
    public static void onCapabilityBindEntity(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof EntityPlayer) {
            event.addCapability(new ResourceLocation(ExampleMod.MODID, "cap"), new CapProvider());
            System.out.println("bind cap to player");
        }
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.Clone event) {
        EntityPlayer newPlayer = event.getEntityPlayer();
        EntityPlayer oldPlayer = event.getOriginal();
        if (oldPlayer.hasCapability(TestCapability.CAPABILITY, null)) {
            int value = oldPlayer.getCapability(TestCapability.CAPABILITY, null).getValue();
            newPlayer.getCapability(TestCapability.CAPABILITY, null).setValue(value);
        }
    }

    @SubscribeEvent
    public static void onCapabilityBindItem(AttachCapabilitiesEvent<Item> event) {
        if (event.getObject() == Items.APPLE) {
            event.addCapability(new ResourceLocation(ExampleMod.MODID, "cap"), new CapProvider());
            System.out.println("bind cap to " + event.getObject());
        }
    }

    @SubscribeEvent
    public static void onCapabilityBindBlock(AttachCapabilitiesEvent<TileEntity> event) {
        if (event.getObject() instanceof BaseTileEntity) {
            event.addCapability(new ResourceLocation(ExampleMod.MODID, "cap"), new CapProvider());
            System.out.println("bind cap to " + event.getObject());
        }
    }

    public static class CapProvider implements ICapabilityProvider {

        private ICapabilityValue mValue = new ICapabilityValue.DefaultImplement();

        @Override
        public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
            return capability == TestCapability.CAPABILITY;
        }

        @Nullable
        @Override
        public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
            if (capability == TestCapability.CAPABILITY) {
                return TestCapability.CAPABILITY.cast(mValue);
            }
            return null;
        }
    }
}
