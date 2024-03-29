package de.lulkas_.mccourse.sound;

import de.lulkas_.mccourse.MCCourseMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MCCourseMod.MOD_ID);

    public static final RegistryObject<SoundEvent> METAL_DETECTOR_FOUND_ORE = registerSoundEvent("metal_detector_found_ore");

    public static final RegistryObject<SoundEvent> BAR_BRAWL = registerSoundEvent("bar_brawl");

    public static final RegistryObject<SoundEvent> ALEXANDRITE_LAMP_BREAK = registerSoundEvent("alexandrite_lamp_break");
    public static final RegistryObject<SoundEvent> ALEXANDRITE_LAMP_STEP = registerSoundEvent("alexandrite_lamp_step");
    public static final RegistryObject<SoundEvent> ALEXANDRITE_LAMP_FALL = registerSoundEvent("alexandrite_lamp_fall");
    public static final RegistryObject<SoundEvent> ALEXANDRITE_LAMP_PLACE = registerSoundEvent("alexandrite_lamp_place");
    public static final RegistryObject<SoundEvent> ALEXANDRITE_LAMP_HIT = registerSoundEvent("alexandrite_lamp_hit");

    public static final ForgeSoundType ALEXANDRITE_LAMP_SOUNDS = new ForgeSoundType(1f, 1f,
            ALEXANDRITE_LAMP_BREAK,
            ALEXANDRITE_LAMP_STEP,
            ALEXANDRITE_LAMP_PLACE,
            ALEXANDRITE_LAMP_HIT,
            ALEXANDRITE_LAMP_FALL
    );

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = new ResourceLocation(MCCourseMod.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
