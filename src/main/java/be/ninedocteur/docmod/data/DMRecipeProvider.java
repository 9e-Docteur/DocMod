package be.ninedocteur.docmod.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

import static net.minecraft.world.item.Items.COAL;
import static net.minecraft.world.item.Items.STICK;
import static net.minecraft.world.item.crafting.RecipeSerializer.BLASTING_RECIPE;

public class DMRecipeProvider extends RecipeProvider implements IConditionBuilder{
    public DMRecipeProvider(PackOutput p_248933_) {
        super(p_248933_);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> p_251297_) {

    }

    private void createDoorRecipe(Consumer<FinishedRecipe> r, RecipeCategory category, ItemLike resultItem, ItemLike requireItem){
        ShapedRecipeBuilder.shaped(category, resultItem, 3).define('#', requireItem)
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_material", has(requireItem))
                .save(r);
    }

    private void createTrapDoorRecipe(Consumer<FinishedRecipe> r, RecipeCategory category, ItemLike resultItem, ItemLike requireItem){
        ShapedRecipeBuilder.shaped(category, resultItem, 6).define('#', requireItem)
                .pattern("###")
                .pattern("###")
                .pattern("   ")
                .unlockedBy("has_material", has(requireItem))
                .save(r);
    }

    private void createSignRecipe(Consumer<FinishedRecipe> r, RecipeCategory category, ItemLike resultItem, ItemLike requireItem){
        ShapedRecipeBuilder.shaped(category, resultItem, 6).define('#', requireItem).define('/', STICK)
                .pattern("###")
                .pattern("###")
                .pattern(" / ")
                .unlockedBy("has_material", has(requireItem))
                .save(r);
    }

    private void createPressurePlateRecipe(Consumer<FinishedRecipe> r, RecipeCategory category, ItemLike resultItem, ItemLike requireItem){
        ShapedRecipeBuilder.shaped(category, resultItem, 2).define('#', requireItem)
                .pattern("   ")
                .pattern("## ")
                .pattern("   ")
                .unlockedBy("has_material", has(requireItem))
                .save(r);
    }

    private void createSlabRecipe(Consumer<FinishedRecipe> r, RecipeCategory category, ItemLike resultItem, ItemLike requireItem){
        ShapedRecipeBuilder.shaped(category, resultItem, 6).define('#', requireItem)
                .pattern("   ")
                .pattern("###")
                .pattern("   ")
                .unlockedBy("has_material", has(requireItem))
                .save(r);
    }

    private void createButtonRecipe(Consumer<FinishedRecipe> r, RecipeCategory category, ItemLike resultItem, ItemLike requireItem){
        ShapelessRecipeBuilder.shapeless(category, resultItem).requires(requireItem)
                .unlockedBy("has_material", has(requireItem))
                .save(r);
    }

    private void createStairsRecipe(Consumer<FinishedRecipe> r, RecipeCategory category, ItemLike resultItem, ItemLike requireItem){
        ShapedRecipeBuilder.shaped(category, resultItem, 6).define('#', requireItem)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .unlockedBy("has_material", has(requireItem))
                .save(r);
    }

    private void createFenceRecipe(Consumer<FinishedRecipe> r, RecipeCategory category, ItemLike resultItem, ItemLike requireItem){
        ShapedRecipeBuilder.shaped(category, resultItem, 3).define('#', requireItem).define('/', STICK)
                .pattern("#/#")
                .pattern("#/#")
                .pattern("   ")
                .unlockedBy("has_material", has(requireItem))
                .save(r);
    }

    private void createFenceGateRecipe(Consumer<FinishedRecipe> r, RecipeCategory category, ItemLike resultItem, ItemLike requireItem){
        ShapedRecipeBuilder.shaped(category, resultItem, 3).define('#', requireItem).define('/', STICK)
                .pattern("/#/")
                .pattern("/#/")
                .pattern("   ")
                .unlockedBy("has_material", has(requireItem))
                .save(r);
    }

    private void createOreBlockRecipe(Consumer<FinishedRecipe> r, RecipeCategory category, ItemLike resultItem, ItemLike requireItem){
        ShapedRecipeBuilder.shaped(category, resultItem).define('#', requireItem)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_material", has(requireItem))
                .save(r);
    }

    private void createMaterialIngotRecipe(Consumer<FinishedRecipe> r, RecipeCategory category, ItemLike nuggetItem, ItemLike resultItem){
        ShapedRecipeBuilder.shaped(category, resultItem).define('#', nuggetItem)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_material", has(nuggetItem))
                .save(r);
    }

    private void createMaterialRecipe(Consumer<FinishedRecipe> r, RecipeCategory category, ItemLike resultItem, ItemLike requireItem){
        ShapelessRecipeBuilder.shapeless(category, resultItem, 9).requires(requireItem)
                .unlockedBy("has_material", has(requireItem))
                .save(r);
    }

    private void createCrossRecipe(Consumer<FinishedRecipe> r, RecipeCategory category, ItemLike resultItem, int count, ItemLike requireItem, ItemLike requireItem1, ItemLike requireItem2){
        ShapedRecipeBuilder.shaped(category, resultItem, count).define('#', requireItem).define('@', requireItem1).define('&', requireItem2)
                .pattern("#@#")
                .pattern("@&@")
                .pattern("#@#")
                .unlockedBy("has_material", has(requireItem))
                .save(r);
    }

    private void createCrossRecipe(Consumer<FinishedRecipe> r, RecipeCategory category, ItemLike resultItem, int count, ItemLike requireItem, ItemLike requireItem1, ItemLike requireItem2, ItemLike requireItem3){
        ShapedRecipeBuilder.shaped(category, resultItem, count).define('#', requireItem).define('@', requireItem1).define('&', requireItem2).define('%', requireItem3)
                .pattern("#%#")
                .pattern("@&@")
                .pattern("#%#")
                .unlockedBy("has_material", has(requireItem))
                .save(r);
    }

    private void createMixRecipe(Consumer<FinishedRecipe> r, RecipeCategory category, ItemLike resultItem, ItemLike requireItem, ItemLike requireItem1){
        ShapelessRecipeBuilder.shapeless(category, resultItem, 9).requires(requireItem).requires(requireItem1)
                .unlockedBy("has_material", has(requireItem))
                .save(r);
    }



    private void createCustomRecipe(Consumer<FinishedRecipe> r, RecipeCategory category, ItemLike resultItem, int count,
                                    ItemLike requireItem1, ItemLike requireItem2, ItemLike requireItem3,
                                    ItemLike requireItem4, ItemLike requireItem5, ItemLike requireItem6,
                                    ItemLike requireItem7, ItemLike requireItem8, ItemLike requireItem9){
        ShapedRecipeBuilder.shaped(category, resultItem, count)
                .define('1', requireItem1).define('2', requireItem2).define('3', requireItem3)
                .define('4', requireItem4).define('5', requireItem5).define('6', requireItem6)
                .define('7', requireItem7).define('8', requireItem8).define('9', requireItem9)
                .pattern("123")
                .pattern("456")
                .pattern("789")
                .unlockedBy("has_material", has(requireItem1))
                .save(r);
    }

    private void createSmelting(Consumer<FinishedRecipe> r, RecipeCategory category, ItemLike smeltItem, ItemLike resultItem, float time, int xp){
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(new ItemStack(smeltItem)), category, resultItem, time, xp);
    }

    private void createBlasting(Consumer<FinishedRecipe> r, RecipeCategory category, ItemLike smeltItem, ItemLike resultItem, float time, int xp){
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(new ItemStack(smeltItem)), category, resultItem, time, xp);
    }
}
