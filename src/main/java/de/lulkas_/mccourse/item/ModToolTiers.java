package de.lulkas_.mccourse.item;

import de.lulkas_.mccourse.MCCourseMod;
import de.lulkas_.mccourse.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {
    public static final Tier ALEXANDRITE = TierSortingRegistry.registerTier(
            new ForgeTier(5, 4000, 18f, 6f, 30, ModTags.Blocks.NEEDS_ALEXANDRITE_TOOL,
                    () -> Ingredient.of(ModItems.ALEXANDRITE.get())),
            new ResourceLocation(MCCourseMod.MOD_ID, "alexandrite"),
            List.of(Tiers.NETHERITE),
            List.of()
    );
}
