package de.lulkas_.mccourse.event;

import de.lulkas_.mccourse.MCCourseMod;
import de.lulkas_.mccourse.particle.AlexandriteParticle;
import de.lulkas_.mccourse.particle.ModParticles;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MCCourseMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventClientBusEvents {
    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.ALEXANDRITE_PARTICLE.get(), AlexandriteParticle.Provider::new);
    }
}
