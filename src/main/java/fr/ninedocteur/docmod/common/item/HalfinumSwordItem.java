package fr.ninedocteur.docmod.common.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class HalfinumSwordItem extends SwordItem {
    public HalfinumSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pTarget.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 200), pAttacker);
        pTarget.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 200), pAttacker);
        pTarget.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 200), pAttacker);
        return super.postHit(pStack, pTarget, pAttacker);
    }
}
