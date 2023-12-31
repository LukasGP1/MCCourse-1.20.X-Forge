package de.lulkas_.mccourse.datagen;

import de.lulkas_.mccourse.MCCourseMod;
import de.lulkas_.mccourse.block.ModBlocks;
import de.lulkas_.mccourse.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MCCourseMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.Blocks.METAL_DETECTOR_VALUABLES)
                .add(ModBlocks.ALEXANDRITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_ALEXANDRITE_ORE.get())
                .add(ModBlocks.END_STONE_ALEXANDRITE_ORE.get())
                .add(ModBlocks.NETHER_ALEXANDRITE_ORE.get())
                .addTag(Tags.Blocks.ORES);
    }

    @Override
    public String getName() {
        return "Block Tags";
    }
}
