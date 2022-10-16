package be.ninedocteur.docmod.utils;

public class CyberUtils {


   /*

    public ItemStack getCyberLaserProjectile(ItemStack pShootable) {
        if (pShootable.getItem() instanceof LaserGunItem) {
            Predicate<ItemStack> $$1 = ((LaserGunItem)pShootable.getItem()).getSupportedHeldProjectiles();
            ItemStack $$2 = LaserGunItem.getHeldProjectile(this, $$1);
            return $$2.isEmpty() ? new ItemStack(DMItems.CYBER_LASER.get()) : $$2;
        } else {
            return ItemStack.EMPTY;
        }
    }

    public static AbstractLaser getMobArrow(LivingEntity pShooter, ItemStack pArrowStack, float pDistanceFactor) {
        LaserItem arrowitem = (LaserItem)(pArrowStack.getItem() instanceof LaserItem ? pArrowStack.getItem() : DMItems.CYBER_LASER.get());
        AbstractLaser abstractarrow = arrowitem.createArrow(pShooter.level, pArrowStack, pShooter);
        abstractarrow.setEnchantmentEffectsFromEntity(pShooter, pDistanceFactor);

        return abstractarrow;
    }

    */
}
