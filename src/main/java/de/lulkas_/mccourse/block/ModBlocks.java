package de.lulkas_.mccourse.block;

import de.lulkas_.mccourse.MCCourseMod;
import de.lulkas_.mccourse.block.custom.AlexandriteLampBlock;
import de.lulkas_.mccourse.block.custom.KohlrabiCropBlock;
import de.lulkas_.mccourse.block.custom.SoundBlock;
import de.lulkas_.mccourse.item.ModItems;
import de.lulkas_.mccourse.sound.ModSounds;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static de.lulkas_.mccourse.block.custom.AlexandriteLampBlock.CLICKED;

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

    public static final RegistryObject<Block> SOUND_BLOCK = registerBlock("sound_block",
            new Item.Properties(),
            () -> new SoundBlock(BlockBehaviour.Properties.copy(Blocks.NOTE_BLOCK)
                    .strength(4f, 6f)
                    .requiresCorrectToolForDrops()
            )
    );

    public static final RegistryObject<Block> ALEXANDRITE_STAIRS = registerBlock("alexandrite_stairs",
            new Item.Properties(),
            () -> new StairBlock(() -> ModBlocks.ALEXANDRITE_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.GRANITE_STAIRS)
                            .strength(4f, 6f)
            )
    );

    public static final RegistryObject<Block> ALEXANDRITE_SLAB = registerBlock("alexandrite_slab",
            new Item.Properties(),
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.GRANITE_SLAB)
                    .strength(4f, 6f)
            )
    );

    public static final RegistryObject<Block> ALEXANDRITE_PRESSURE_PLATE = registerBlock("alexandrite_pressure_plate",
            new Item.Properties(),
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    BlockBehaviour.Properties.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE)
                            .strength(4f, 6f),
                    BlockSetType.IRON
            )
    );

    public static final RegistryObject<Block> ALEXANDRITE_BUTTON = registerBlock("alexandrite_button",
            new Item.Properties(),
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON)
                    .strength(4f, 6f),
                    BlockSetType.IRON,
                    15,
                    true
            )
    );

    public static final RegistryObject<Block> ALEXANDRITE_FENCE = registerBlock("alexandrite_fence",
            new Item.Properties(),
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)
                    .strength(4f, 6f)

            )
    );

    public static final RegistryObject<Block> ALEXANDRITE_FENCE_GATE = registerBlock("alexandrite_fence_gate",
            new Item.Properties(),
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE)
                    .strength(4f, 6f),
                    SoundEvents.CHERRY_WOOD_FENCE_GATE_OPEN,
                    SoundEvents.CHERRY_WOOD_FENCE_GATE_CLOSE
            )
    );

    public static final RegistryObject<Block> ALEXANDRITE_WALL = registerBlock("alexandrite_wall",
            new Item.Properties(),
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_WALL)
                    .strength(4f, 6f)
            )
    );

    public static final RegistryObject<Block> ALEXANDRITE_DOOR = registerBlock("alexandrite_door",
            new Item.Properties(),
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR)
                    .strength(4f, 6f),
                    BlockSetType.IRON
            )
    );

    public static final RegistryObject<Block> ALEXANDRITE_TRAPDOOR = registerBlock("alexandrite_trapdoor",
            new Item.Properties(),
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_WALL)
                    .strength(4f, 6f),
                    BlockSetType.IRON
            )
    );

    public static final RegistryObject<Block> ALEXANDRITE_LAMP = registerBlock("alexandrite_lamp",
            new Item.Properties(),
            () -> new AlexandriteLampBlock(BlockBehaviour.Properties.copy(Blocks.REDSTONE_LAMP)
                    .strength(4f, 6f)
                    .lightLevel(state -> state.getValue(CLICKED) ? 15 : 0)
                    .sound(ModSounds.ALEXANDRITE_LAMP_SOUNDS)
            )
    );

    public static final RegistryObject<Block> KOHLRABI_CROP = BLOCKS.register("kohlrabi_crop",
            () -> new KohlrabiCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)
                    .noCollission()
                    .noOcclusion()
            )
    );

    public static final RegistryObject<Block> SNAPDRAGON = registerBlock("snapdragon",
            new Item.Properties(),
            () -> new FlowerBlock(() -> MobEffects.HEALTH_BOOST, 200, BlockBehaviour.Properties.copy(Blocks.ALLIUM))
    );

    public static final RegistryObject<Block> POTTED_SNAPDRAGON = BLOCKS.register("potted_snapdragon",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), SNAPDRAGON, BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM)
                    .noLootTable())
    );
}
