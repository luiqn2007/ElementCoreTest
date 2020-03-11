package com.example.examplemod.capability;

import com.elementtimes.elementcore.api.annotation.ModCapability;
import com.elementtimes.elementcore.api.annotation.part.Getter;
import com.elementtimes.elementcore.api.annotation.part.Method;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

@ModCapability(type = ICapabilityValue.class,
        typeFactory = @Method(ICapabilityValue.DefaultImplement.class),
        storage = @Getter(value = CapabilityStorage.class, name = "storage"))
public class TestCapability {

    @CapabilityInject(ICapabilityValue.class)
    public static Capability<ICapabilityValue> CAPABILITY;

    @CapabilityInject(ICapabilityValue.class)
    public static void onRegister(Capability<ICapabilityValue> cap) {
        System.out.println("Capability ICapabilityValue is registered");
    }
}
