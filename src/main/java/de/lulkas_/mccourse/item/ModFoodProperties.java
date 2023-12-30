package de.lulkas_.mccourse.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties KOHLRABI = new FoodProperties.Builder()
            .fast()
            .nutrition(3)
            .saturationMod(0.25f)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 6000, 10), 1f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 6000, 3), 1f)
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0), 1f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 6000, 4), 1f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 6000, 4), 1f)
            .effect(() -> new MobEffectInstance(MobEffects.WATER_BREATHING, 6000, 0), 1f)
            .build();
}
