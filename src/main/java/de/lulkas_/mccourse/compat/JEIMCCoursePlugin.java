package de.lulkas_.mccourse.compat;

import de.lulkas_.mccourse.MCCourseMod;
import de.lulkas_.mccourse.recipe.GemEmpoweringRecipe;
import de.lulkas_.mccourse.screen.GemEmpoweringStationScreen;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

@JeiPlugin
public class JEIMCCoursePlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(MCCourseMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new GemEmpoweringRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
        List<GemEmpoweringRecipe> empoweringRecipes = recipeManager.getAllRecipesFor(GemEmpoweringRecipe.Type.INSTANCE);
        registration.addRecipes(GemEmpoweringRecipeCategory.GEM_EMPOWERING_TYPE, empoweringRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(GemEmpoweringStationScreen.class, 60, 30, 20, 30, GemEmpoweringRecipeCategory.GEM_EMPOWERING_TYPE);
    }
}
