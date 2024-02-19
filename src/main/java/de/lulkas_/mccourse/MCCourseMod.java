package de.lulkas_.mccourse;

import com.mojang.logging.LogUtils;
import de.lulkas_.mccourse.block.ModBlocks;
import de.lulkas_.mccourse.effect.ModEffects;
import de.lulkas_.mccourse.enchantment.ModEnchantments;
import de.lulkas_.mccourse.item.ModCreativeModeTabs;
import de.lulkas_.mccourse.item.ModItemProperties;
import de.lulkas_.mccourse.item.ModItems;
import de.lulkas_.mccourse.loot.ModLootModifiers;
import de.lulkas_.mccourse.painting.ModPaintings;
import de.lulkas_.mccourse.particle.ModParticles;
import de.lulkas_.mccourse.potion.BetterBrewingRecipe;
import de.lulkas_.mccourse.potion.ModPotions;
import de.lulkas_.mccourse.sound.ModSounds;
import de.lulkas_.mccourse.villager.ModVillagers;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MCCourseMod.MOD_ID)
public class MCCourseMod {

    public static final String MOD_ID = "mccourse";

    public static final Logger LOGGER = LogUtils.getLogger();

    public MCCourseMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEnchantments.register(modEventBus);
        ModSounds.register(modEventBus);
        ModLootModifiers.register(modEventBus);
        ModPaintings.register(modEventBus);
        ModEffects.register(modEventBus);
        ModPotions.register(modEventBus);
        ModVillagers.register(modEventBus);
        ModParticles.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ComposterBlock.COMPOSTABLES.put(ModItems.KOHLRABI.get(), 1f);
            ComposterBlock.COMPOSTABLES.put(ModItems.KOHLRABI_SEEDS.get(), 0.5f);

            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.SNAPDRAGON.getId(), ModBlocks.POTTED_SNAPDRAGON);

            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.AWKWARD, Items.SLIME_BALL, ModPotions.SLIMEY_POTION.get()));
        });
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() ->  {
                ModItemProperties.addCustomItemProperties();
            });
        }
    }
}
