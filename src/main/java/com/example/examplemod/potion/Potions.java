package com.example.examplemod.potion;

import com.elementtimes.elementcore.api.annotation.ModPotion;
import com.elementtimes.elementcore.api.annotation.enums.PotionBottleType;
import com.elementtimes.elementcore.api.annotation.part.PotionEffect;
import com.elementtimes.elementcore.api.annotation.part.PotionType;
import net.minecraft.potion.Potion;

public class Potions {

    @ModPotion(withType = @PotionType(effects = @PotionEffect(duration = 1000, amplifier = 1)))
    @ModPotion.Bottles(value = "blocks",
            types = {PotionBottleType.NORMAL, PotionBottleType.SPLASH, PotionBottleType.LINGERING})
    public static Potion testPotion = new TestPotion();
}
