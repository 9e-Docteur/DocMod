package be.ninedocteur.docmod.integrations.jei;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.recipe.InfusionTableRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JEIDocModPlugin implements IModPlugin {

    public static RecipeType<InfusionTableRecipe> INFUSION_TYPE =
            new RecipeType<>(InfusionTableRecipeCategory.UID, InfusionTableRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(DocMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new
                InfusionTableRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();

        List<InfusionTableRecipe> recipesInfusing = rm.getAllRecipesFor(InfusionTableRecipe.Type.INSTANCE);
        registration.addRecipes(INFUSION_TYPE, recipesInfusing);
    }
}
