package be.ninedocteur.docmod.common.entity.mob.ai.goal;

import be.ninedocteur.docmod.common.entity.mob.CyberBossEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class CyberBossGoal<T extends Monster & RangedAttackMob> extends Goal {

    private final T mob;
    @Nullable
    private LivingEntity target;
    private int attackTime = -1;
    private final double speedModifier;
    private int seeTime;
    private final int attackIntervalMin;
    private final int attackIntervalMax;
    private final float attackRadius;
    private final float attackRadiusSqr;
    private boolean strafingClockwise;
    private boolean strafingBackwards;
    private int strafingTime = -1;
    private CyberBossEntity cyberBossEntity;
    private int tick;


    public CyberBossGoal(T p_25768_, double p_25769_, int p_25770_, float p_25771_) {
        this(p_25768_, p_25769_, p_25770_, p_25770_, p_25771_);
    }

    public CyberBossGoal(T p_25773_, double p_25774_, int attackIntervalMin, int attackIntervalMax, float attackRadius) {
            this.mob = p_25773_;
            this.speedModifier = p_25774_;
            this.attackIntervalMin = attackIntervalMin;
            this.attackIntervalMax = attackIntervalMax;
            this.attackRadius = attackRadius;
            this.attackRadiusSqr = attackIntervalMin * attackIntervalMax;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

    public boolean canUse() {
        LivingEntity livingentity = this.mob.getTarget();
        if (livingentity != null && livingentity.isAlive()) {
            this.target = livingentity;
            return true;
        } else {
            return false;
        }
    }

    public boolean canContinueToUse() {
        return this.canUse() || !this.mob.getNavigation().isDone();
    }

    public void stop() {
        this.target = null;
        this.seeTime = 0;
        this.tick = 0;
        this.attackTime = -1;
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        boolean targetIsInVisual = this.mob.getSensing().hasLineOfSight(target);
        LivingEntity livingentity = this.mob.getTarget();
        if (livingentity != null) {
            double d0 = this.mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
            boolean flag = this.mob.getSensing().hasLineOfSight((Entity)livingentity);
            boolean flag1 = (this.seeTime > 0);
            if (flag != flag1)
                this.seeTime = 0;
            if (flag) {
                this.seeTime++;
            } else {
                this.seeTime--;
            }
            if (d0 <= this.attackRadiusSqr && this.seeTime >= 20) {
                this.mob.getNavigation().stop();
                this.strafingTime++;
            } else {
                this.mob.getNavigation().moveTo((Entity)livingentity, this.speedModifier);
                this.strafingTime = -1;
            }
            if (this.strafingTime >= 20) {
                if (this.mob.getRandom().nextFloat() < 0.3D)
                    this.strafingClockwise = !this.strafingClockwise;
                if (this.mob.getRandom().nextFloat() < 0.3D)
                    this.strafingBackwards = !this.strafingBackwards;
                this.strafingTime = 0;
            }
            if (this.strafingTime > -1) {
                if (d0 > (this.attackRadiusSqr * 0.75F)) {
                    this.strafingBackwards = false;
                } else if (d0 < (this.attackRadiusSqr * 0.25F)) {
                    this.strafingBackwards = true;
                }
                this.mob.getMoveControl().strafe(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);
                this.mob.lookAt((Entity)livingentity, 30.0F, 30.0F);
            } else {
                this.mob.getLookControl().setLookAt((Entity)livingentity, 30.0F, 30.0F);
            }
            if (targetIsInVisual) {
                ++this.tick;
               // if (this.tick >= getTicksBeforeLaser()) {
                    //CyberBossType cyberBossType = new CyberBossType();
                    //AbstractLaser.Weapons<CyberBossEntity> cyberWeapon = cyberBossType.getWeapon();
                    //if (cyberWeapon != null) {
                     //   if (cyberWeapon.useWeapon((CyberBossEntity) mob)) {
                       //     this.mob.playSound(DMSound.CYBERMAN_GUN.get(), 1.0F, 1.0F);
                         //   if (target instanceof Mob) {
                           //     Mob mob = (Mob)target;
                             //   mob.setAggressive(true);
                            //}
                       // }
                    //}
                  //  this.tick = 0;
                }
            }
        }
    }
/*
    public double getTicksBeforeLaser() {
        //CyberBossType cyberBossType = new CyberBossType();
        //double attacksPerSecond = cyberBossType.getAttacksPerSecond();
        //double ticksTillAttack = (1.0 / attacksPerSecond) * 20;
        //return ticksTillAttack;
    }

 */

//}
