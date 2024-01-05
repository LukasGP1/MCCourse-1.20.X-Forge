package de.lulkas_.mccourse.item;

import de.lulkas_.mccourse.MCCourseMod;
import de.lulkas_.mccourse.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MCCourseMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> COURSE_TAB = CREATIVE_MODE_TABS.register("course_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.ALEXANDRITE.get()))
                    .title(Component.translatable("creativetab.course_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.ALEXANDRITE.get());
                        pOutput.accept(ModItems.RAW_ALEXANDRITE.get());
                        pOutput.accept(ModItems.METAL_DETECTOR.get());
                        pOutput.accept(ModItems.KOHLRABI.get());
                        pOutput.accept(ModItems.PEAT_BRICK.get());
                        pOutput.accept(ModItems.ALEXANDRITE_SWORD.get());
                        pOutput.accept(ModItems.ALEXANDRITE_PICKAXE.get());
                        pOutput.accept(ModItems.ALEXANDRITE_HOE.get());
                        pOutput.accept(ModItems.ALEXANDRITE_AXE.get());
                        pOutput.accept(ModItems.ALEXANDRITE_SHOVEL.get());
                        pOutput.accept(ModItems.ALEXANDRITE_PAXEL.get());
                        pOutput.accept(ModItems.ALEXANDRITE_HAMMER.get());
                        pOutput.accept(ModItems.ALEXANDRITE_HELMET.get());
                        pOutput.accept(ModItems.ALEXANDRITE_CHESTPLATE.get());
                        pOutput.accept(ModItems.ALEXANDRITE_LEGGINGS.get());
                        pOutput.accept(ModItems.ALEXANDRITE_BOOTS.get());
                        pOutput.accept(ModItems.ALEXANDRITE_HORSE_ARMOR.get());

                        pOutput.accept(ModBlocks.ALEXANDRITE_BLOCK.get());
                        pOutput.accept(ModBlocks.RAW_ALEXANDRITE_BLOCK.get());
                        pOutput.accept(ModBlocks.ALEXANDRITE_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_ALEXANDRITE_ORE.get());
                        pOutput.accept(ModBlocks.NETHER_ALEXANDRITE_ORE.get());
                        pOutput.accept(ModBlocks.END_STONE_ALEXANDRITE_ORE.get());
                        pOutput.accept(ModBlocks.SOUND_BLOCK.get());
                        pOutput.accept(ModBlocks.ALEXANDRITE_SLAB.get());
                        pOutput.accept(ModBlocks.ALEXANDRITE_STAIRS.get());
                        pOutput.accept(ModBlocks.ALEXANDRITE_PRESSURE_PLATE.get());
                        pOutput.accept(ModBlocks.ALEXANDRITE_BUTTON.get());
                        pOutput.accept(ModBlocks.ALEXANDRITE_FENCE.get());
                        pOutput.accept(ModBlocks.ALEXANDRITE_FENCE_GATE.get());
                        pOutput.accept(ModBlocks.ALEXANDRITE_WALL.get());
                        pOutput.accept(ModBlocks.ALEXANDRITE_DOOR.get());
                        pOutput.accept(ModBlocks.ALEXANDRITE_TRAPDOOR.get());
                        pOutput.accept(ModBlocks.ALEXANDRITE_LAMP.get());
                    })
                    .build()
    );

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
