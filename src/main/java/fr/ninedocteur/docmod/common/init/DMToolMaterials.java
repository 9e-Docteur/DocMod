package fr.ninedocteur.docmod.common.init;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

public enum DMToolMaterials implements ToolMaterial {
    ZINC(8, 3060, 16.0F, 8.0F, 25, () -> {
        return Ingredient.ofItems(DMItems.ZINC_INGOT);
    }),

    COPPER(5, 3000, 10.0F, 5.0F, 15, () -> {
        return Ingredient.ofItems(Items.COPPER_INGOT);
    }),

    HALFINUM(15, 5000, 30.0F, 25.0F, 30, () -> {
        return Ingredient.ofItems(DMItems.HALFINUM_INGOT);
    }),

    CLOUD(2, 500, 2.0F, 0.5F, 5, () -> {
        return Ingredient.ofItems(DMItems.HALFINUM_INGOT);
    });

    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Lazy<Ingredient> repairMaterial;

    DMToolMaterials(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = new Lazy(repairMaterial);
    }

    @Override
    public int getDurability() {
        return 0;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 0;
    }

    @Override
    public float getAttackDamage() {
        return 0;
    }

    @Override
    public int getMiningLevel() {
        return 0;
    }

    @Override
    public int getEnchantability() {
        return 0;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }
}
