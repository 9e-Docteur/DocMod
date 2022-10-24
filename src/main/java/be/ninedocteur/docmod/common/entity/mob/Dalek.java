package be.ninedocteur.docmod.common.entity.mob;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.entity.projectile.AbstractRPGLaser;
import be.ninedocteur.docmod.common.init.DMBlocks;
import be.ninedocteur.docmod.common.init.DMItems;
import be.ninedocteur.docmod.common.item.gun.CyberGun;
import be.ninedocteur.docmod.common.item.gun.DalekGun;
import be.ninedocteur.docmod.common.item.laser.item.DalekLaserGunItem;
import be.ninedocteur.docmod.common.item.laser.item.DalekLaserItem;
import be.ninedocteur.docmod.common.sound.DMSound;
import be.ninedocteur.docmod.common.entity.projectile.AbstractDalekLaser;
import be.ninedocteur.docmod.utils.DMDamageSource;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Predicate;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Dalek extends Monster implements RangedAttackMob {

    private BlockPos LAST_POS = getOnPos();
    private Dalek DALEK_ENTITY = this;
    private final RangedBowAttackGoal bowGoal = new RangedBowAttackGoal(this, 0.25D, 20, 1.0F);
    private final MeleeAttackGoal meleeGoal = new MeleeAttackGoal(this, 0.2D, false);

    public Dalek(EntityType<? extends Monster> p_27403_, Level p_27404_) {
        super(p_27403_, p_27404_);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.addBehaviourGoals();
        //this.reassessWeaponGoal();
    }

    @Override
    public boolean shouldShowName() {
        if(DocMod.isRunningInDev){
            return true;
        };
        return false;
    }

    protected void addBehaviourGoals() {
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 0.3D));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, EnderMan.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, CybermanEntity.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, CyberBossEntity.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, CyberHumanEntity.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, CyberHumanEntitySecond.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, CyberHumanEntityThird.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, OldSteve.class, true));
        this.targetSelector.addGoal(9, new RangedAttackGoal(this, 0.25, 20, 5));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 120.0F).add(Attributes.FOLLOW_RANGE, 35.0D).add(Attributes.ATTACK_DAMAGE, 3.0D).add(Attributes.ARMOR, 2.0D).add(Attributes.MOVEMENT_SPEED, 1F);
    }

    protected boolean isSunSensitive() {
        return false;
    }

    public void aiStep() {
        if (this.isAlive()) {
            boolean flag = this.isSunSensitive() && this.isSunBurnTick();
            if (flag) {
                ItemStack itemstack = this.getItemBySlot(EquipmentSlot.HEAD);
                if (!itemstack.isEmpty()) {
                    if (itemstack.isDamageableItem()) {
                        itemstack.setDamageValue(itemstack.getDamageValue() + this.random.nextInt(2));
                        if (itemstack.getDamageValue() >= itemstack.getMaxDamage()) {
                            this.broadcastBreakEvent(EquipmentSlot.HEAD);
                            this.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
                        }
                    }

                    flag = false;
                }

                if (flag) {
                    this.setSecondsOnFire(8);
                }
            }
        }

        super.aiStep();
    }

    public float getSteeringSpeed() {
        return (float)this.getAttributeValue(Attributes.MOVEMENT_SPEED) * 0.225F;
    }


    public void reassessWeaponGoal() {
        if (this.level != null && !this.level.isClientSide) {
            this.goalSelector.removeGoal(this.meleeGoal);
            this.goalSelector.removeGoal(this.bowGoal);
            ItemStack itemstack = this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, (item) -> {
                return item instanceof CyberGun;
            }));
            if (itemstack.is(DMItems.DALEK_GUN.get())) {
                int i = 5;
                if (this.level.getDifficulty() != Difficulty.HARD) {
                    i = 5;
                }

                this.bowGoal.setMinAttackInterval(100);
                this.goalSelector.addGoal(1, this.bowGoal);
            } else {
                this.goalSelector.addGoal(1, this.meleeGoal);
            }
        }

    }

    public void performRangedAttack(LivingEntity pTarget, float pDistanceFactor) {
        ItemStack itemstack = this.getCyberLaserProjectile(this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, (item) -> {
            return item instanceof DalekGun;
        })));
        AbstractDalekLaser abstractarrow = this.getArrow(itemstack, pDistanceFactor);
        if (this.getMainHandItem().getItem() instanceof DalekGun) {
            abstractarrow = ((DalekGun)this.getMainHandItem().getItem()).customArrow(abstractarrow);
        }

        double d0 = pTarget.getX() - this.getX();
        double d1 = pTarget.getY(0.3333333333333333D) - abstractarrow.getY();
        double d2 = pTarget.getZ() - this.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        abstractarrow.shoot(d0, d1, d2, 2.5F, 0.0F);
        this.playSound(DMSound.DALEK_GUN.get(), 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(abstractarrow);
    }

    protected AbstractDalekLaser getArrow(ItemStack pArrowStack, float pDistanceFactor) {
        return this.getMobArrow(this, pArrowStack, pDistanceFactor);
    }

    public ItemStack getCyberLaserProjectile(ItemStack pShootable) {
        if (pShootable.getItem() instanceof DalekLaserGunItem) {
            Predicate<ItemStack> $$1 = ((DalekLaserGunItem)pShootable.getItem()).getSupportedHeldProjectiles();
            ItemStack $$2 = DalekLaserGunItem.getHeldProjectile(this, $$1);
            return $$2.isEmpty() ? new ItemStack(DMItems.DALEK_LASER.get()) : $$2;
        } else {
            return ItemStack.EMPTY;
        }
    }

    public static AbstractDalekLaser getMobArrow(LivingEntity pShooter, ItemStack pArrowStack, float pDistanceFactor) {
        DalekLaserItem arrowitem = (DalekLaserItem) (pArrowStack.getItem() instanceof DalekLaserItem ? pArrowStack.getItem() : DMItems.DALEK_LASER.get());
        AbstractDalekLaser abstractarrow = arrowitem.createArrow(pShooter.level, pArrowStack, pShooter);
        abstractarrow.setEnchantmentEffectsFromEntity(pShooter, pDistanceFactor);

        return abstractarrow;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return DMSound.DALEK_AMBIANT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return DMSound.CYBERMAN_HURT.get();
    }

    //DEPRECATED
   /*
    @Override
    public void die(DamageSource p_21014_) {
        if(p_21014_.isExplosion()){
            level.setBlockAndUpdate(getOnPos(), DMBlocks.DALEK_DAMAGED.get().defaultBlockState());
        }
    }

    */
/*
    @Override
    public boolean isDeadOrDying() {
        if (dead) {
            if (DamageSource.GENERIC.isExplosion()) {
                return level.setBlockAndUpdate(getOnPos(), DMBlocks.DALEK_DAMAGED.get().defaultBlockState());
            }
        }
        return dead;
    }

 */

    @Override
    public void tick() {
        super.tick();
        if(this.dead){
            if(this.getLastDamageSource().isExplosion()) {
                startTimer();
            }
        }
    }

    private void startTimer(){
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        final Runnable runnable = new Runnable() {
            int countdownStarter = 2;

            public void run() {

                System.out.println(countdownStarter);
                countdownStarter--;

                if (countdownStarter < 0) {
                    scheduler.shutdown();
                    level.setBlock(blockPosition(), DMBlocks.DALEK_DAMAGED.get().defaultBlockState(), 1);
                }
            }
        };

        runnable.run();
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
    }
}
