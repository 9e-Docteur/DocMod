package be.ninedocteur.docmod.utils;

import be.ninedocteur.docmod.common.init.DMItems;
import be.ninedocteur.docmod.common.item.laser.item.DalekLaserGunItem;
import be.ninedocteur.docmod.common.item.laser.item.LaserGunItem;
import be.ninedocteur.docmod.common.item.laser.item.MagicLaserGunItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;

public class GunUtils {
    public static ItemStack getCyberLaserProjectile(ItemStack pShootable, Player player) {
        if (!(pShootable.getItem() instanceof LaserGunItem)) {
            return ItemStack.EMPTY;
        } else {
            Predicate<ItemStack> predicate = ((LaserGunItem)pShootable.getItem()).getSupportedHeldProjectiles();
            ItemStack itemstack = LaserGunItem.getHeldProjectile(player, predicate);
            if (!itemstack.isEmpty()) {
                return itemstack;
            } else {
                predicate = ((LaserGunItem)pShootable.getItem()).getAllSupportedProjectiles();

                for(int i = 0; i < player.getInventory().getContainerSize(); ++i) {
                    ItemStack itemstack1 = player.getInventory().getItem(i);
                    if (predicate.test(itemstack1)) {
                        return itemstack1;
                    }
                }

                return player.getAbilities().instabuild ? new ItemStack(DMItems.CYBER_LASER.get()) : ItemStack.EMPTY;
            }
        }
    }

    public static ItemStack getDalekLaserProjectile(ItemStack pShootable, Player player) {
        if (!(pShootable.getItem() instanceof DalekLaserGunItem)) {
            return ItemStack.EMPTY;
        } else {
            Predicate<ItemStack> predicate = ((DalekLaserGunItem)pShootable.getItem()).getSupportedHeldProjectiles();
            ItemStack itemstack = DalekLaserGunItem.getHeldProjectile(player, predicate);
            if (!itemstack.isEmpty()) {
                return itemstack;
            } else {
                predicate = ((DalekLaserGunItem)pShootable.getItem()).getAllSupportedProjectiles();

                for(int i = 0; i < player.getInventory().getContainerSize(); ++i) {
                    ItemStack itemstack1 = player.getInventory().getItem(i);
                    if (predicate.test(itemstack1)) {
                        return itemstack1;
                    }
                }

                return player.getAbilities().instabuild ? new ItemStack(DMItems.DALEK_LASER.get()) : ItemStack.EMPTY;
            }
        }
    }

    public static ItemStack getMagicLaserProjectile(ItemStack pShootable, Player player) {
        if (!(pShootable.getItem() instanceof MagicLaserGunItem)) {
            return ItemStack.EMPTY;
        } else {
            Predicate<ItemStack> predicate = ((MagicLaserGunItem)pShootable.getItem()).getSupportedHeldProjectiles();
            ItemStack itemstack = MagicLaserGunItem.getHeldProjectile(player, predicate);
            if (!itemstack.isEmpty()) {
                return itemstack;
            } else {
                predicate = ((MagicLaserGunItem)pShootable.getItem()).getAllSupportedProjectiles();

                for(int i = 0; i < player.getInventory().getContainerSize(); ++i) {
                    ItemStack itemstack1 = player.getInventory().getItem(i);
                    if (predicate.test(itemstack1)) {
                        return itemstack1;
                    }
                }

                return player.getAbilities().instabuild ? new ItemStack(DMItems.MAGIC_LASER.get()) : ItemStack.EMPTY;
            }
        }
    }
}
