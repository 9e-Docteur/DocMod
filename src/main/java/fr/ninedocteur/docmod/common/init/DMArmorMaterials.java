package fr.ninedocteur.docmod.common.init;

import fr.ninedocteur.docmod.DocMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

public enum DMArmorMaterials implements ArmorMaterial {
    ZINC("zinc", 80, new int[] { 2, 5, 6, 2 }, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0f, 0.0f, () -> {
        return Ingredient.ofItems(DMItems.ZINC_INGOT);

    }),

    HALFINUM("halfinum", 100, new int[] { 25, 20, 25, 35 }, 30,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON, 5.0f, 1.0f, () -> {
        return Ingredient.ofItems(DMItems.HALFINUM_INGOT);

    }),

    COPPER("copper", 60, new int[] { 2, 5, 6, 2 }, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0f, 0.0f, () -> {
        return Ingredient.ofItems(Items.COPPER_INGOT);
    }),

    CLOUD("cloud", 1, new int[] { 10, 10, 10, 10 }, 5,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0f, 1.0f, () -> {
        return Ingredient.ofItems(Items.COPPER_INGOT);
    }),

    MASK("mask", 1, new int[] { 10, 10, 10, 10 }, 5,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0f, 1.0f, () -> {
        return null;
    }),

    SPACE_SUIT("space_suit", 60, new int[] { 2, 5, 6, 2 }, 0,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2.0f, 1.0f, () -> {
        return null;
    }),;

    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final float knockbackResistance;
    private final Lazy<Ingredient> repairMaterial;

    DMArmorMaterials(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial) {
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairMaterial = new Lazy<>(repairMaterial);
    }
    @Override
    public int getDurability(ArmorItem.Type type) {
        return MAX_DAMAGE_ARRAY[type.getEquipmentSlot().getEntitySlotId()] * this.maxDamageFactor;
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return this.damageReductionAmountArray[type.getEquipmentSlot().getEntitySlotId()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.soundEvent;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }

    @Environment(EnvType.CLIENT)
    public String getName() {
        return DocMod.MOD_ID + ":" + this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
