package be.ninedocteur.docmod.common.item.laser.item;

import be.ninedocteur.docmod.common.item.laser.entity.Laser;
import be.ninedocteur.docmod.common.entity.projectile.AbstractLaser;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class LaserItem extends Item {
    public LaserItem(Properties pProperties) {
        super(pProperties);
    }

    public AbstractLaser createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
        Laser arrow = new Laser(pLevel, pShooter);
        arrow.setEffectsFromItem(pStack);
        return arrow;
    }

    public boolean isInfinite(ItemStack stack, ItemStack bow, Player player) {
        int enchant = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, bow);
        return enchant <= 0 ? false : this.getClass() == LaserItem.class;
    }
}
