package be.ninedocteur.docmod.common.init;

import be.ninedocteur.docmod.DocMod;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.EnumMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

public enum ModArmorMaterial implements ArmorMaterial {
    
    ZINC("zinc", 80, make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
    	map.put(ArmorItem.Type.BOOTS, 5);
    	map.put(ArmorItem.Type.LEGGINGS, 8);
    	map.put(ArmorItem.Type.CHESTPLATE, 10);
    	map.put(ArmorItem.Type.HELMET, 5);
     }), 25, SoundEvents.ARMOR_EQUIP_DIAMOND, 0.0F, 0.0F, () -> {
        return Ingredient.of(DMItems.ZINC_INGOT.get());
     }),
    
    HALFINUM("halfinum", 100, make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
    	map.put(ArmorItem.Type.BOOTS, 7);
    	map.put(ArmorItem.Type.LEGGINGS, 10);
    	map.put(ArmorItem.Type.CHESTPLATE, 12);
    	map.put(ArmorItem.Type.HELMET, 7);
     }), 25, SoundEvents.ARMOR_EQUIP_NETHERITE, 0.0F, 0.0F, () -> {
        return Ingredient.of(DMItems.HALFINUM_INGOT.get());
     }),
    
    COPPER("copper", 60, make(new EnumMap<>(ArmorItem.Type.class), (p_266650_) -> {
        p_266650_.put(ArmorItem.Type.BOOTS, 2);
        p_266650_.put(ArmorItem.Type.LEGGINGS, 4);
        p_266650_.put(ArmorItem.Type.CHESTPLATE, 6);
        p_266650_.put(ArmorItem.Type.HELMET, 4);
     }), 25, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
        return Ingredient.of(Items.COPPER_INGOT);
     }),

    CLOUD("cloud", 1, make(new EnumMap<>(ArmorItem.Type.class), (p_266650_) -> {
        p_266650_.put(ArmorItem.Type.BOOTS, 1);
        p_266650_.put(ArmorItem.Type.LEGGINGS, 1);
        p_266650_.put(ArmorItem.Type.CHESTPLATE, 1);
        p_266650_.put(ArmorItem.Type.HELMET, 1);
     }), 25, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
        return null;
     }),

    MASK("mask", 1, make(new EnumMap<>(ArmorItem.Type.class), (p_266650_) -> {
        p_266650_.put(ArmorItem.Type.BOOTS, 1);
        p_266650_.put(ArmorItem.Type.LEGGINGS, 1);
        p_266650_.put(ArmorItem.Type.CHESTPLATE, 1);
        p_266650_.put(ArmorItem.Type.HELMET, 1);
     }), 25, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
        return null;
     }),

    SPACE_SUIT("space_suit", 20, make(new EnumMap<>(ArmorItem.Type.class), (p_266650_) -> {
        p_266650_.put(ArmorItem.Type.BOOTS, 2);
        p_266650_.put(ArmorItem.Type.LEGGINGS, 4);
        p_266650_.put(ArmorItem.Type.CHESTPLATE, 3);
        p_266650_.put(ArmorItem.Type.HELMET, 2);
     }), 25, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
        return null;
     }),;

	public static final StringRepresentable.EnumCodec<ArmorMaterials> CODEC = StringRepresentable.fromEnum(ArmorMaterials::values);
	   private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266653_) -> {
	      p_266653_.put(ArmorItem.Type.BOOTS, 13);
	      p_266653_.put(ArmorItem.Type.LEGGINGS, 15);
	      p_266653_.put(ArmorItem.Type.CHESTPLATE, 16);
	      p_266653_.put(ArmorItem.Type.HELMET, 11);
	   });
	   private final String name;
	   private final int durabilityMultiplier;
	   private final EnumMap<ArmorItem.Type, Integer> protectionFunctionForType;
	   private final int enchantmentValue;
	   private final SoundEvent sound;
	   private final float toughness;
	   private final float knockbackResistance;
	   private final LazyLoadedValue<Ingredient> repairIngredient;

	   private ModArmorMaterial(String p_268171_, int p_268303_, EnumMap<ArmorItem.Type, Integer> p_267941_, int p_268086_, SoundEvent p_268145_, float p_268058_, float p_268180_, Supplier<Ingredient> p_268256_) {
	      this.name = p_268171_;
	      this.durabilityMultiplier = p_268303_;
	      this.protectionFunctionForType = p_267941_;
	      this.enchantmentValue = p_268086_;
	      this.sound = p_268145_;
	      this.toughness = p_268058_;
	      this.knockbackResistance = p_268180_;
	      this.repairIngredient = new LazyLoadedValue<>(p_268256_);
	   }

	   public int getDurabilityForType(ArmorItem.Type p_266745_) {
	      return HEALTH_FUNCTION_FOR_TYPE.get(p_266745_) * this.durabilityMultiplier;
	   }

	   public int getDefenseForType(ArmorItem.Type p_266752_) {
	      return this.protectionFunctionForType.get(p_266752_);
	   }

	   public int getEnchantmentValue() {
	      return this.enchantmentValue;
	   }

	   public SoundEvent getEquipSound() {
	      return this.sound;
	   }

	   public Ingredient getRepairIngredient() {
	      return this.repairIngredient.get();
	   }

	   public String getName() {
	      return this.name;
	   }

	   public float getToughness() {
	      return this.toughness;
	   }

	   public float getKnockbackResistance() {
	      return this.knockbackResistance;
	   }

	   public String getSerializedName() {
	      return this.name;
	   }
	   
	   private static <T> T make(T p_137470_, Consumer<T> p_137471_) {
		      p_137471_.accept(p_137470_);
		      return p_137470_;
	}
}

