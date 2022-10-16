package be.ninedocteur.docmod.common.item.laser.item;

import be.ninedocteur.docmod.common.init.DMItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.function.Predicate;

public abstract class RPGGunItem extends Item {
    public RPGGunItem(Properties pProperties) {
        super(pProperties);
    }

    public static final Predicate<ItemStack> CYBERGUN_LASER_ONLY = (p_43017_) -> {
        return p_43017_.is(/*DMItems.RPG_LASER.get()*/ DMItems.DALEK_LASER.get());
    };
    public static final Predicate<ItemStack> ARROW_OR_FIREWORK;

    public Predicate<ItemStack> getSupportedHeldProjectiles() {
        return this.getAllSupportedProjectiles();
    }

    public abstract Predicate<ItemStack> getAllSupportedProjectiles();

    public static ItemStack getHeldProjectile(LivingEntity pShooter, Predicate<ItemStack> pIsAmmo) {
        if (pIsAmmo.test(pShooter.getItemInHand(InteractionHand.OFF_HAND))) {
            return pShooter.getItemInHand(InteractionHand.OFF_HAND);
        } else {
            return pIsAmmo.test(pShooter.getItemInHand(InteractionHand.MAIN_HAND)) ? pShooter.getItemInHand(InteractionHand.MAIN_HAND) : ItemStack.EMPTY;
        }
    }

    public int getEnchantmentValue() {
        return 1;
    }

    public abstract int getDefaultProjectileRange();

    static {
        ARROW_OR_FIREWORK = CYBERGUN_LASER_ONLY.or((p_43015_) -> {
            return p_43015_.is(Items.FIREWORK_ROCKET);
        });
    }
}
