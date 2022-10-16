package be.ninedocteur.docmod.common.init;

import be.ninedocteur.docmod.DocMod;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum ModArmorMaterial implements ArmorMaterial {

    ZINC("zinc", 80, new int[] { 2, 5, 6, 2 }, 12,
    SoundEvents.ARMOR_EQUIP_IRON, 1.0f, 0.0f, () -> {
        return Ingredient.of(DMItems.ZINC_INGOT.get());

    }),

    HALFINUM("halfinum", 100, new int[] { 25, 20, 25, 35 }, 30,
            SoundEvents.ARMOR_EQUIP_IRON, 5.0f, 1.0f, () -> {
        return Ingredient.of(DMItems.HALFINUM_INGOT.get());

    }),

    COPPER("copper", 60, new int[] { 2, 5, 6, 2 }, 12,
    SoundEvents.ARMOR_EQUIP_IRON, 1.0f, 0.0f, () -> {
        return Ingredient.of(Items.COPPER_INGOT);
    }),

    CLOUD("cloud", 1, new int[] { 10, 10, 10, 10 }, 5,
    SoundEvents.ARMOR_EQUIP_IRON, 2.0f, 1.0f, () -> {
        return Ingredient.of(Items.COPPER_INGOT);
    }),

    MASK("mask", 1, new int[] { 10, 10, 10, 10 }, 5,
    SoundEvents.ARMOR_EQUIP_IRON, 2.0f, 1.0f, () -> {
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
    private final LazyLoadedValue<Ingredient> repairMaterial;

    ModArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability,
                     SoundEvent soundEvent, float toughness, float knockbackResistance,
                     Supplier<Ingredient> repairMaterial) {
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairMaterial = new LazyLoadedValue<>(repairMaterial);
    }


    @Override
    public int getDurabilityForSlot(EquipmentSlot p_40410_) {
        return MAX_DAMAGE_ARRAY[p_40410_.getIndex()] * this.maxDamageFactor;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot p_40411_) {
        return this.damageReductionAmountArray[p_40411_.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
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

    @OnlyIn(Dist.CLIENT)
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

