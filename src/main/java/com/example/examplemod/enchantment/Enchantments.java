package com.example.examplemod.enchantment;

import com.elementtimes.elementcore.api.annotation.ModEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;

public class Enchantments {

    @ModEnchantment
    @ModEnchantment.Book
    public static Enchantment enchantmentTest = new Enchantment(Enchantment.Rarity.COMMON, EnumEnchantmentType.BREAKABLE, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND}) {
        @Override
        public int getMinLevel() {
            return 3;
        }

        @Override
        public int getMaxLevel() {
            return 5;
        }

        @Override
        public int calcModifierDamage(int level, DamageSource source) {
            return 3 * level;
        }
    };
}
