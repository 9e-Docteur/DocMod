package be.ninedocteur.docmod.common.item.gun;

import be.ninedocteur.docmod.common.entity.DMEntityType;
import be.ninedocteur.docmod.common.init.DMItems;
import be.ninedocteur.docmod.common.item.laser.item.RPGItem;
import be.ninedocteur.docmod.common.item.laser.item.RPGGunItem;
import be.ninedocteur.docmod.common.sound.DMSound;
import be.ninedocteur.docmod.common.entity.projectile.AbstractRPGLaser;
import be.ninedocteur.docmod.utils.DMMath;
import be.ninedocteur.docmod.utils.TeamUtils;
import be.ninedocteur.docmod.utils.GunUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.User;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.function.Predicate;

public class RPGGun extends RPGGunItem {
    public RPGGun(Properties pProperties) {
        super(pProperties);
    }

    public static final int MAX_DRAW_DURATION = 20;
    public static final int DEFAULT_RANGE = 15;

    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        if (pEntityLiving instanceof Player) {
            Player player = (Player)pEntityLiving;
            boolean flag = player.getAbilities().instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, pStack) > 0;
            ItemStack itemstack = GunUtils.getDalekLaserProjectile(pStack, player);
            int i = this.getUseDuration(pStack) - pTimeLeft;
            i = ForgeEventFactory.onArrowLoose(pStack, pLevel, player, i, !itemstack.isEmpty() || flag);
            if (i < 0) {
                return;
            }

            if (!itemstack.isEmpty() || flag) {
                if (itemstack.isEmpty()) {
                    itemstack = new ItemStack(DMItems.RPG_LASER.get());
                }

                float f = getPowerForTime(i);
                if (!((double)f < 0.1D)) {
                    boolean flag1 = player.getAbilities().instabuild || itemstack.getItem() instanceof RPGItem && ((RPGItem)itemstack.getItem()).isInfinite(itemstack, pStack, player);
                    if (!pLevel.isClientSide) {
                        RPGItem arrowitem = (RPGItem)(itemstack.getItem() instanceof RPGItem ? itemstack.getItem() : DMItems.RPG_LASER.get());
                        AbstractRPGLaser abstractarrow = arrowitem.createArrow(pLevel, itemstack, player);
                        abstractarrow = this.customArrow(abstractarrow);
                        abstractarrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, f * 3.0F, 1.0F);
                        if (f == 1.0F) {
                            abstractarrow.setCritArrow(true);
                        }

                        int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, pStack);
                        if (j > 0) {
                            abstractarrow.setBaseDamage(abstractarrow.getBaseDamage() + (double)j * 0.5D + 0.5D);
                        }

                        int k = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, pStack);
                        if (k > 0) {
                            abstractarrow.setKnockback(k);
                        }

                        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, pStack) > 0) {
                            abstractarrow.setSecondsOnFire(100);
                        }

                        pStack.hurtAndBreak(1, player, (p_40665_) -> {
                            p_40665_.broadcastBreakEvent(player.getUsedItemHand());
                        });
                        if (flag1 || player.getAbilities().instabuild && (itemstack.is(Items.SPECTRAL_ARROW) || itemstack.is(Items.TIPPED_ARROW))) {
                            abstractarrow.pickup = AbstractRPGLaser.Pickup.CREATIVE_ONLY;
                        }

                        pLevel.addFreshEntity(abstractarrow);
                    }
                    if(pEntityLiving.equals(DMEntityType.SWDALEK.get())){
                        pLevel.playSound((Player)null, player.getX(), player.getY(), player.getZ(), DMSound.SPECIAL_WEAPON.get(), SoundSource.PLAYERS, 1.0F, 1.0F / (pLevel.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    } else {
                        pLevel.playSound((Player)null, player.getX(), player.getY(), player.getZ(), DMSound.RPG.get(), SoundSource.PLAYERS, 1.0F, 1.0F / (pLevel.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    }
                    if (!flag1 && !player.getAbilities().instabuild) {
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            player.getInventory().removeItem(itemstack);
                        }
                    }

                    player.awardStat(Stats.ITEM_USED.get(this));

                    User user = Minecraft.getInstance().getUser();
                    TeamUtils.TeamMember teamMember = TeamUtils.getTeamMembers().get(user.getUuid());
                    if(teamMember != null){
                        player.getCooldowns().addCooldown(this, DMMath.convertSecondsToTicks(1));
                    }else {
                        player.getCooldowns().addCooldown(this, DMMath.convertSecondsToTicks(1));
                    }

                }
            }
        }

    }

    public static float getPowerForTime(int pCharge) {
        float f = (float)pCharge / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return 1.2f;
    }

    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }

    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        boolean flag = !GunUtils.getDalekLaserProjectile(itemstack, pPlayer).isEmpty();
        InteractionResultHolder<ItemStack> ret = ForgeEventFactory.onArrowNock(itemstack, pLevel, pPlayer, pHand, flag);
        if (ret != null) {
            return ret;
        } else if (!pPlayer.getAbilities().instabuild && !flag) {
            return InteractionResultHolder.fail(itemstack);
        } else {
            pPlayer.startUsingItem(pHand);
            return InteractionResultHolder.consume(itemstack);
        }
    }

    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return CYBERGUN_LASER_ONLY;
    }

    public AbstractRPGLaser customArrow(AbstractRPGLaser arrow) {
        return arrow;
    }

    public int getDefaultProjectileRange() {
        return 100;
    }
}
