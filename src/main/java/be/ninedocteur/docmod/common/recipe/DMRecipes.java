package be.ninedocteur.docmod.common.recipe;

import be.ninedocteur.docmod.DocMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DMRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, DocMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<InfusionTableRecipe>> INFUSION_RECIPE = SERIALIZERS.register("infusion_recipe", () -> InfusionTableRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus){
        SERIALIZERS.register(eventBus);
    }
}
