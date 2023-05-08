package be.ninedocteur.docmod.common.entity.mob;

import be.ninedocteur.docmod.DMConfig;
import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.init.DMBlocks;
import be.ninedocteur.docmod.common.init.DMItems;
import be.ninedocteur.docmod.common.item.gun.CyberGun;
import be.ninedocteur.docmod.common.item.laser.item.LaserGunItem;
import be.ninedocteur.docmod.common.item.laser.item.LaserItem;
import be.ninedocteur.docmod.common.sound.DMSound;
import be.ninedocteur.docmod.common.entity.projectile.AbstractLaser;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class CybermanEntity extends Monster implements RangedAttackMob {
    private Block spawnBlock;

    private final RangedBowAttackGoal bowGoal = new RangedBowAttackGoal(this, 0.25D, 20, 1.0F);
    private final MeleeAttackGoal meleeGoal = new MeleeAttackGoal(this, 0.2D, false);
    public boolean isAttacking;

    public CybermanEntity(EntityType<? extends Monster> p_27403_, Level p_27404_) {
        super(p_27403_, p_27404_);
        this.setHealth(this.getMaxHealth());
    }
    @Override
    public boolean shouldShowName() {
        if(DocMod.isRunningInDev){
            return true;
        };
        return false;
    }


    @Override
    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
        this.playSound(DMSound.CYBERMAN_STEP.get(), 0.20F, 1.0F);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(9, new NearestAttackableTargetGoal<>(this, Zurbitris.class, true));
        this.addBehaviourGoals();
    }

    protected void addBehaviourGoals() {
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.25, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 0.25D));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, OldSteve.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, EnderMan.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(9, new NearestAttackableTargetGoal<>(this, Zurbitris.class, true));
        this.targetSelector.addGoal(9, new NearestAttackableTargetGoal<>(this, Dalek.class, true));
        this.targetSelector.addGoal(9, new NearestAttackableTargetGoal<>(this, SWDalek.class, true));
        this.targetSelector.addGoal(9, new RangedAttackGoal(this, 0.25, 20, 5));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 250.0D).add(Attributes.FOLLOW_RANGE, 35.0D).add(Attributes.ATTACK_DAMAGE, 10.0D).add(Attributes.ARMOR, 2.0D).add(Attributes.MOVEMENT_SPEED, 1F);
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

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
    }

    public void startSeenByPlayer(ServerPlayer pPlayer) {
        super.startSeenByPlayer(pPlayer);
    }

    public void stopSeenByPlayer(ServerPlayer pPlayer) {
        super.stopSeenByPlayer(pPlayer);
    }


    public void setCustomName(@Nullable Component pName) {
        super.setCustomName(pName);
    }

    @org.jetbrains.annotations.Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return DMSound.CYBERMAN_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return DMSound.CYBERMAN_HURT.get();
    }

    public float getSteeringSpeed() {
        return (float)this.getAttributeValue(Attributes.MOVEMENT_SPEED) * 0.225F;
    }




    public void performRangedAttack(LivingEntity pTarget, float pDistanceFactor) {
        ItemStack itemstack = this.getCyberLaserProjectile(this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, (item) -> {
            return item instanceof CyberGun;
        })));
        AbstractLaser abstractarrow = this.getArrow(itemstack, pDistanceFactor);
        if (this.getMainHandItem().getItem() instanceof CyberGun) {
            abstractarrow = ((CyberGun)this.getMainHandItem().getItem()).customArrow(abstractarrow);
        }

        double d0 = pTarget.getX() - this.getX();
        double d1 = pTarget.getY(0.3333333333333333D) - abstractarrow.getY();
        double d2 = pTarget.getZ() - this.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        abstractarrow.shoot(d0, d1, d2, 2.5F, 0.0F);
        this.playSound(DMSound.CYBERMAN_GUN.get(), 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(abstractarrow);
    }

    protected AbstractLaser getArrow(ItemStack pArrowStack, float pDistanceFactor) {
        return this.getMobArrow(this, pArrowStack, pDistanceFactor);
    }

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


    public boolean canFireProjectileWeapon(ProjectileWeaponItem p_32144_) {
        return p_32144_ == DMItems.CYBER_GUN.get();
    }

    public void setSpawnBlock(Block spawnBlock) {
        this.spawnBlock = spawnBlock;
    }

    public Block getSpawnBlock() {
        return spawnBlock;
    }

    public static boolean canSpawn(EntityType<CybermanEntity> entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return checkMobSpawnRules(entityType, level, spawnType, pos, random) && pos.getY() > 100  ||  checkMobSpawnRules(entityType, level, spawnType, pos, random) && pos.getY() > 100 && level.getBlockState(pos).is(DMBlocks.STEEL_BLOCK.get());
    }
    
    @Override
    public void tick() {
    	if(this.getTarget() != null) {
    		isAttacking = true;
    	} else {
    		isAttacking = false;
    	}
    	super.tick();
    }
}
