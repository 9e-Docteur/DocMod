package be.ninedocteur.docmod.common.item.laser.item;

import be.ninedocteur.docmod.common.entity.projectile.AbstractClassicDalekLaser;
import be.ninedocteur.docmod.common.item.laser.entity.ClassicDalekLaser;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class ClassicDalekLaserItem extends Item {
    public ClassicDalekLaserItem(Properties pProperties) {
        super(pProperties);
    }

    public AbstractClassicDalekLaser createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
        ClassicDalekLaser arrow = new ClassicDalekLaser(pLevel, pShooter);
        arrow.setEffectsFromItem(pStack);
        return arrow;
    }



    public boolean isInfinite(ItemStack stack, ItemStack bow, Player player) {
        int enchant = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, bow);
        return enchant <= 0 ? false : this.getClass() == ClassicDalekLaserItem.class;
    }
}
