package be.ninedocteur.docmod.common.init;


import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ModItemTier implements Tier {

    ZINC(8, 3060, 16.0F, 8.0F, 25, () -> {
        return Ingredient.of(DMItems.ZINC_INGOT.get());
    }),

    COPPER(5, 3000, 10.0F, 5.0F, 15, () -> {
        return Ingredient.of(Items.COPPER_INGOT);
    }),

    HALFINUM(15, 5000, 30.0F, 25.0F, 30, () -> {
        return Ingredient.of(DMItems.HALFINUM_INGOT.get());
    }),

    CLOUD(2, 500, 2.0F, 0.5F, 5, () -> {
        return Ingredient.of(DMItems.HALFINUM_INGOT.get());
    });


    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final LazyLoadedValue<Ingredient> repairMaterial;

    ModItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = new LazyLoadedValue<>(repairMaterial);
    }


    @Override
    public int getUses() {
        return maxUses;
    }

    @Override
    public float getSpeed() {
        return efficiency;
    }

    @Override
    public float getAttackDamageBonus() {
        return attackDamage;
    }

    @Override
    public int getLevel() {
        return harvestLevel;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairMaterial.get();
    }
}
