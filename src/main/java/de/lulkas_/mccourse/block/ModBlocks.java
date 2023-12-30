package de.lulkas_.mccourse.block;

import de.lulkas_.mccourse.MCCourseMod;
import de.lulkas_.mccourse.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MCCourseMod.MOD_ID);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Item.Properties blockItemProperties, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(toReturn, name, blockItemProperties);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(RegistryObject<T> block, String name, Item.Properties itemProperties) {
        RegistryObject<Item> toReturn = ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), itemProperties));
        return toReturn;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }


    public static final RegistryObject<Block> ALEXANDRITE_BLOCK = registerBlock("alexandrite_block",
            new Item.Properties(),
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK))
    );

    public static final RegistryObject<Block> ALEXANDRITE_ORE = registerBlock("alexandrite_ore",
            new Item.Properties(),
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE),
                    UniformInt.of(1, 5)
            )
    );

    public static final RegistryObject<Block> DEEPSLATE_ALEXANDRITE_ORE = registerBlock("deepslate_alexandrite_ore",
            new Item.Properties(),
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE),
                    UniformInt.of(1, 5)
            )
    );

    public static final RegistryObject<Block> END_STONE_ALEXANDRITE_ORE = registerBlock("end_stone_alexandrite_ore",
            new Item.Properties(),
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.SAND)
                    .requiresCorrectToolForDrops()
                    .strength(4.0F, 9.0F),
                    UniformInt.of(1, 5)
            )
    );

    public static final RegistryObject<Block> NETHER_ALEXANDRITE_ORE = registerBlock("nether_alexandrite_ore",
            new Item.Properties(),
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.NETHER)
                    .requiresCorrectToolForDrops()
                    .strength(4.0F, 3.0F)
                    .sound(SoundType.NETHER_ORE),
                    UniformInt.of(1, 5)
            )
    );

    public static final RegistryObject<Block> RAW_ALEXANDRITE_BLOCK = registerBlock("raw_alexandrite_block",
            new Item.Properties(),
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK))
    );

}
