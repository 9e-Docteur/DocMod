package be.ninedocteur.docmod.common.item.laser.item;

import be.ninedocteur.docmod.common.entity.projectile.AbstractWandLaser;
import be.ninedocteur.docmod.common.item.laser.entity.MagicLaser;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class MagicLaserItem extends Item {
    public MagicLaserItem(Properties pProperties) {
        super(pProperties);
    }

    public AbstractWandLaser createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
        MagicLaser arrow = new MagicLaser(pLevel, pShooter);
        arrow.setEffectsFromItem(pStack);
        return arrow;
    }



    public boolean isInfinite(ItemStack stack, ItemStack bow, Player player) {
        int enchant = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, bow);
        return enchant <= 0 ? false : this.getClass() == MagicLaserItem.class;
    }
}
