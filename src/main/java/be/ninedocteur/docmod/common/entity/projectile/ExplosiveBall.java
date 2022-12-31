package be.ninedocteur.docmod.common.entity.projectile;

import be.ninedocteur.docmod.DMConfig;
import be.ninedocteur.docmod.common.init.DMItems;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class ExplosiveBall extends ThrowableItemProjectile {

    public ExplosiveBall(EntityType<? extends ExplosiveBall> p_37391_, Level p_37392_) {
        super(p_37391_, p_37392_);
    }

    public ExplosiveBall(Level p_37399_, LivingEntity p_37400_) {
        super(EntityType.SNOWBALL, p_37400_, p_37399_);
    }

    public ExplosiveBall(Level p_37394_, double p_37395_, double p_37396_, double p_37397_) {
        super(EntityType.SNOWBALL, p_37395_, p_37396_, p_37397_, p_37394_);
    }

    protected Item getDefaultItem() {
        return DMItems.EXPLOSIVE_BALL.get();
    }

    private ParticleOptions getParticle() {
        ItemStack $$0 = this.getItemRaw();
        return (ParticleOptions)($$0.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleOption(ParticleTypes.ITEM, $$0));
    }

    public void handleEntityEvent(byte p_37402_) {
        if (p_37402_ == 3) {
            ParticleOptions $$1 = this.getParticle();

            for(int $$2 = 0; $$2 < 8; ++$$2) {
                this.level.addParticle($$1, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            }
        }

    }

    protected void onHitEntity(EntityHitResult p_37404_) {
        super.onHitEntity(p_37404_);
        Entity $$1 = p_37404_.getEntity();
        int $$2 = $$1 instanceof Blaze ? 3 : 0;
        $$1.hurt(DamageSource.thrown(this, this.getOwner()), (float)$$2);
    }

    protected void onHit(HitResult p_37406_) {
        super.onHit(p_37406_);
        if (!this.level.isClientSide) {
            if(DMConfig.Server.GunExplosion.get()) {
                level.explode(this, this.getX(), this.getY(), this.getZ(), DMConfig.Server.GunExplosionSize.get(), true, Explosion.BlockInteraction.DESTROY);
            }
            this.level.broadcastEntityEvent(this, (byte)3);
            this.discard();
        }

    }

    @Override
    protected void onHitBlock(BlockHitResult p_37258_) {
        super.onHitBlock(p_37258_);
        if(DMConfig.Server.GunExplosion.get()) {
            if (!level.isClientSide) {
                level.explode(this, this.getX(), this.getY(), this.getZ(), DMConfig.Server.GunExplosionSize.get(), true, Explosion.BlockInteraction.DESTROY);
                this.discard();
            }
        }
    }
}
