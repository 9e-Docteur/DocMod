package be.ninedocteur.docmod.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

import static net.minecraft.world.item.Items.COAL;
import static net.minecraft.world.item.Items.STICK;
import static net.minecraft.world.item.crafting.RecipeSerializer.BLASTING_RECIPE;

public class DMRecipeProvider { //extends RecipeProvider implements IConditionBuilder{

//    public DMRecipeProvider(DataGenerator pGenerator) {
//        super(pGenerator);
//    }
//
//    @Override
//    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
//        //SET CRAFTS HERE!
//    }
//
//    private void createDoorRecipe(Consumer<FinishedRecipe> r, ItemLike resultItem, ItemLike requireItem){
//        ShapedRecipeBuilder.shaped(resultItem, 3).define('#', requireItem)
//                .pattern("##")
//                .pattern("##")
//                .pattern("##")
//                .unlockedBy("has_material", has(requireItem))
//                .save(r);
//    }
//
//    private void createTrapDoorRecipe(Consumer<FinishedRecipe> r, ItemLike resultItem, ItemLike requireItem){
//        ShapedRecipeBuilder.shaped(resultItem, 6).define('#', requireItem)
//                .pattern("###")
//                .pattern("###")
//                .pattern("   ")
//                .unlockedBy("has_material", has(requireItem))
//                .save(r);
//    }
//
//    private void createSignRecipe(Consumer<FinishedRecipe> r, ItemLike resultItem, ItemLike requireItem){
//        ShapedRecipeBuilder.shaped(resultItem, 6).define('#', requireItem).define('/', STICK)
//                .pattern("###")
//                .pattern("###")
//                .pattern(" / ")
//                .unlockedBy("has_material", has(requireItem))
//                .save(r);
//    }
//
//    private void createPressurePlateRecipe(Consumer<FinishedRecipe> r, ItemLike resultItem, ItemLike requireItem){
//        ShapedRecipeBuilder.shaped(resultItem, 2).define('#', requireItem)
//                .pattern("   ")
//                .pattern("## ")
//                .pattern("   ")
//                .unlockedBy("has_material", has(requireItem))
//                .save(r);
//    }
//
//    private void createSlabRecipe(Consumer<FinishedRecipe> r, ItemLike resultItem, ItemLike requireItem){
//        ShapedRecipeBuilder.shaped(resultItem, 6).define('#', requireItem)
//                .pattern("   ")
//                .pattern("###")
//                .pattern("   ")
//                .unlockedBy("has_material", has(requireItem))
//                .save(r);
//    }
//
//    private void createButtonRecipe(Consumer<FinishedRecipe> r, ItemLike resultItem, ItemLike requireItem){
//        ShapedRecipeBuilder.shaped(resultItem).define('#', requireItem)
//                .pattern("   ")
//                .pattern(" # ")
//                .pattern("   ")
//                .unlockedBy("has_material", has(requireItem))
//                .save(r);
//    }
//
//    private void createStairsRecipe(Consumer<FinishedRecipe> r, ItemLike resultItem, ItemLike requireItem){
//        ShapedRecipeBuilder.shaped(resultItem, 6).define('#', requireItem)
//                .pattern("#  ")
//                .pattern("## ")
//                .pattern("###")
//                .unlockedBy("has_material", has(requireItem))
//                .save(r);
//    }
//
//    private void createFenceRecipe(Consumer<FinishedRecipe> r, ItemLike resultItem, ItemLike requireItem){
//        ShapedRecipeBuilder.shaped(resultItem, 3).define('#', requireItem).define('/', Items.STICK)
//                .pattern("#/#")
//                .pattern("#/#")
//                .pattern("   ")
//                .unlockedBy("has_material", has(requireItem))
//                .save(r);
//    }
//
//    private void createFenceGateRecipe(Consumer<FinishedRecipe> r, ItemLike resultItem, ItemLike requireItem){
//        ShapedRecipeBuilder.shaped(resultItem, 3).define('#', requireItem).define('/', Items.STICK)
//                .pattern("/#/")
//                .pattern("/#/")
//                .pattern("   ")
//                .unlockedBy("has_material", has(requireItem))
//                .save(r);
//    }
//
//    private void createOreBlockRecipe(Consumer<FinishedRecipe> r, ItemLike resultItem, ItemLike requireItem){
//        ShapedRecipeBuilder.shaped(resultItem).define('#', requireItem)
//                .pattern("###")
//                .pattern("###")
//                .pattern("###")
//                .unlockedBy("has_material", has(requireItem))
//                .save(r);
//    }
//
//    private void createMaterialIngotRecipe(Consumer<FinishedRecipe> r, ItemLike nuggetItem, ItemLike resultItem){
//        ShapedRecipeBuilder.shaped(nuggetItem).define('#', resultItem)
//                .pattern("###")
//                .pattern("###")
//                .pattern("###")
//                .unlockedBy("has_material", has(resultItem))
//                .save(r);
//    }
//
//    private void createMaterialRecipe(Consumer<FinishedRecipe> r, ItemLike resultItem, ItemLike requireItem){
//        ShapedRecipeBuilder.shaped(resultItem, 9).define('#', requireItem)
//                .pattern("   ")
//                .pattern(" # ")
//                .pattern("   ")
//                .unlockedBy("has_material", has(requireItem))
//                .save(r);
//    }
//
//    private void createMixRecipe(Consumer<FinishedRecipe> r, ItemLike resultItem, ItemLike requireItem, ItemLike requireItem1){
//        ShapedRecipeBuilder.shaped(resultItem, 9).define('1', requireItem).define('2', requireItem1)
//                .pattern("   ")
//                .pattern(" 12")
//                .pattern("   ")
//                .unlockedBy("has_material", has(requireItem))
//                .save(r);
//    }
//
//    private void createCustomRecipe(Consumer<FinishedRecipe> r,
//                                    ItemLike requireItem1, ItemLike requireItem2, ItemLike requireItem3,
//                                    ItemLike requireItem4, ItemLike requireItem5, ItemLike requireItem6,
//                                    ItemLike requireItem7, ItemLike requireItem8, ItemLike requireItem9,
//                                    ItemLike resultItem){
//        ShapedRecipeBuilder.shaped(resultItem)
//                .define('1', requireItem1).define('2', requireItem2).define('3', requireItem3)
//                .define('4', requireItem4).define('5', requireItem5).define('6', requireItem6)
//                .define('7', requireItem7).define('8', requireItem8).define('9', requireItem9)
//                .pattern("123")
//                .pattern("456")
//                .pattern("789")
//                .unlockedBy("has_material", has(resultItem))
//                .save(r);
//    }
//
//    private void createSmelting(Consumer<FinishedRecipe> r, ItemLike smeltItem, ItemLike resultItem, float time, int xp, SimpleCookingSerializer<?> type){
//        SimpleCookingRecipeBuilder.cooking(Ingredient.of(new ItemStack(smeltItem)), resultItem, time, xp, type);
//    }
}
