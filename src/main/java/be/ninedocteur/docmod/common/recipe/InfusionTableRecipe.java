package be.ninedocteur.docmod.common.recipe;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.utils.IOUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sun.jna.platform.win32.WinNT;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;

public class InfusionTableRecipe/* implements Recipe<SimpleContainer> */{
//    private final ResourceLocation id;
//    private final ItemStack output;
//    private final NonNullList<Ingredient> recipeItems;
//
//    public InfusionTableRecipe(ResourceLocation id, ItemStack output,
//                                    NonNullList<Ingredient> recipeItems) {
//        this.id = id;
//        this.output = output;
//        this.recipeItems = recipeItems;
//    }
//
//    @Override
//    public boolean matches(SimpleContainer pContainer, Level pLevel) {
//        if(pLevel.isClientSide()) {
//            return false;
//        }
//
//        return recipeItems.get(0).test(pContainer.getItem(1));
//    }
///*
//    public FluidStack getFluid() {
//        return fluidStack;
//    }
//
// */
//
//    @Override
//    public NonNullList<Ingredient> getIngredients() {
//        return recipeItems;
//    }
//
//    @Override
//    public ItemStack assemble(SimpleContainer pContainer) {
//        return output;
//    }
//
//    @Override
//    public boolean canCraftInDimensions(int pWidth, int pHeight) {
//        return true;
//    }
//
//    @Override
//    public ItemStack getResultItem() {
//        return output.copy();
//    }
//
//    @Override
//    public ResourceLocation getId() {
//        return id;
//    }
//
//    @Override
//    public RecipeSerializer<?> getSerializer() {
//        return Serializer.INSTANCE;
//    }
//
//    @Override
//    public RecipeType<?> getType() {
//        return Type.INSTANCE;
//    }
//
//    public static class Type implements RecipeType<InfusionTableRecipe> {
//        private Type() { }
//        public static final Type INSTANCE = new Type();
//        public static final String ID = "infusion_recipe";
//    }
//
//
//    public static class Serializer implements RecipeSerializer<InfusionTableRecipe> {
//        public static final Serializer INSTANCE = new Serializer();
//        public static final ResourceLocation ID =
//                new ResourceLocation(DocMod.MOD_ID, "infusion_recipe");
//
//        @Override
//        public InfusionTableRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
//            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));
//
//            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
//            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);
//            //FluidStack fluid = IOUtils.readFluid(pSerializedRecipe.get("fluid").getAsJsonObject());
//
//            for (int i = 0; i < inputs.size(); i++) {
//                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
//            }
//
//            return new InfusionTableRecipe(pRecipeId, output, inputs);
//        }
//
//        @Override
//        public @Nullable InfusionTableRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
//            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);
//            FluidStack fluid = buf.readFluidStack();
//
//            for (int i = 0; i < inputs.size(); i++) {
//                inputs.set(i, Ingredient.fromNetwork(buf));
//            }
//
//            ItemStack output = buf.readItem();
//            return new InfusionTableRecipe(id, output, inputs);
//        }
//
//        @Override
//        public void toNetwork(FriendlyByteBuf buf, InfusionTableRecipe recipe) {
//            buf.writeInt(recipe.getIngredients().size());
//            //buf.writeFluidStack(recipe.fluidStack);
//
//            for (Ingredient ing : recipe.getIngredients()) {
//                ing.toNetwork(buf);
//            }
//            buf.writeItemStack(recipe.getResultItem(), false);
//        }
//    }
//
//
//	@Override
//	public ItemStack assemble(SimpleContainer p_44001_, RegistryAccess p_267165_) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public ItemStack getResultItem(RegistryAccess p_267052_) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
