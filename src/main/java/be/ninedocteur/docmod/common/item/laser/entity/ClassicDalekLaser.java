package be.ninedocteur.docmod.common.item.laser.entity;

import be.ninedocteur.docmod.common.entity.DMEntityType;
import be.ninedocteur.docmod.common.entity.projectile.AbstractClassicDalekLaser;
import be.ninedocteur.docmod.common.init.DMItems;
import com.google.common.collect.Sets;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class ClassicDalekLaser extends AbstractClassicDalekLaser {

    private static final int EXPOSED_POTION_DECAY_TIME = 600;
    private static final int NO_EFFECT_COLOR = -1;
    private static final EntityDataAccessor<Integer> ID_EFFECT_COLOR;
    private static final byte EVENT_POTION_PUFF = 0;
    private Potion potion;
    private final Set<MobEffectInstance> effects;
    private boolean fixedColor;

    public ClassicDalekLaser(EntityType<? extends ClassicDalekLaser> p_36858_, Level p_36859_) {
        super(p_36858_, p_36859_);
        this.potion = Potions.EMPTY;
        this.effects = Sets.newHashSet();
    }

    public ClassicDalekLaser(Level p_36861_, double p_36862_, double p_36863_, double p_36864_) {
        super(DMEntityType.CLASIC_DALEK_LASER.get(), p_36862_, p_36863_, p_36864_, p_36861_);
        this.potion = Potions.EMPTY;
        this.effects = Sets.newHashSet();
    }

    public ClassicDalekLaser(Level p_36866_, LivingEntity p_36867_) {
        super(DMEntityType.CLASIC_DALEK_LASER.get(), p_36867_, p_36866_);
        this.potion = Potions.EMPTY;
        this.effects = Sets.newHashSet();
    }

    public void setEffectsFromItem(ItemStack pStack) {
        if (pStack.is(Items.TIPPED_ARROW)) {
            this.potion = PotionUtils.getPotion(pStack);
            Collection<MobEffectInstance> $$1 = PotionUtils.getCustomEffects(pStack);
            if (!$$1.isEmpty()) {
                Iterator var3 = $$1.iterator();

                while(var3.hasNext()) {
                    MobEffectInstance $$2 = (MobEffectInstance)var3.next();
                    this.effects.add(new MobEffectInstance($$2));
                }
            }

            int $$3 = getCustomColor(pStack);
            if ($$3 == -1) {
                this.updateColor();
            } else {
                this.setFixedColor($$3);
            }
        } else if (pStack.is(DMItems.CYBER_LASER.get())) {
            this.potion = Potions.EMPTY;
            this.effects.clear();
            this.entityData.set(ID_EFFECT_COLOR, -1);
        }

    }

    public static int getCustomColor(ItemStack p_36885_) {
        CompoundTag $$1 = p_36885_.getTag();
        return $$1 != null && $$1.contains("CustomPotionColor", 99) ? $$1.getInt("CustomPotionColor") : -1;
    }

    private void updateColor() {
        this.fixedColor = false;
        if (this.potion == Potions.EMPTY && this.effects.isEmpty()) {
            this.entityData.set(ID_EFFECT_COLOR, -1);
        } else {
            this.entityData.set(ID_EFFECT_COLOR, PotionUtils.getColor(PotionUtils.getAllEffects(this.potion, this.effects)));
        }

    }

    public void addEffect(MobEffectInstance pEffect) {
        this.effects.add(pEffect);
        this.getEntityData().set(ID_EFFECT_COLOR, PotionUtils.getColor(PotionUtils.getAllEffects(this.potion, this.effects)));
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ID_EFFECT_COLOR, -1);
    }

    public void tick() {
        super.tick();
        if (this.level.isClientSide) {
            if (this.inGround) {
                if (this.inGroundTime % 5 == 0) {
                    this.makeParticle(1);
                }
            } else {
                this.makeParticle(2);
            }
        } else if (this.inGround && this.inGroundTime != 0 && !this.effects.isEmpty() && this.inGroundTime >= 600) {
            this.level.broadcastEntityEvent(this, (byte)0);
            this.potion = Potions.EMPTY;
            this.effects.clear();
            this.entityData.set(ID_EFFECT_COLOR, -1);
        }

    }

    private void makeParticle(int pParticleCount) {
        int $$1 = this.getColor();
        if ($$1 != -1 && pParticleCount > 0) {
            double $$2 = (double)($$1 >> 16 & 255) / 255.0D;
            double $$3 = (double)($$1 >> 8 & 255) / 255.0D;
            double $$4 = (double)($$1 >> 0 & 255) / 255.0D;

            for(int $$5 = 0; $$5 < pParticleCount; ++$$5) {
                this.level.addParticle(ParticleTypes.ENTITY_EFFECT, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), $$2, $$3, $$4);
            }

        }
    }

    public int getColor() {
        return (Integer)this.entityData.get(ID_EFFECT_COLOR);
    }

    private void setFixedColor(int p_36883_) {
        this.fixedColor = true;
        this.entityData.set(ID_EFFECT_COLOR, p_36883_);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        

        if (this.fixedColor) {
            pCompound.putInt("Color", this.getColor());
        }

        if (!this.effects.isEmpty()) {
            ListTag $$1 = new ListTag();
            Iterator var3 = this.effects.iterator();

            while(var3.hasNext()) {
                MobEffectInstance $$2 = (MobEffectInstance)var3.next();
                $$1.add($$2.save(new CompoundTag()));
            }

            pCompound.put("CustomPotionEffects", $$1);
        }

    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (pCompound.contains("Potion", 8)) {
            this.potion = PotionUtils.getPotion(pCompound);
        }

        Iterator var2 = PotionUtils.getCustomEffects(pCompound).iterator();

        while(var2.hasNext()) {
            MobEffectInstance $$1 = (MobEffectInstance)var2.next();
            this.addEffect($$1);
        }

        if (pCompound.contains("Color", 99)) {
            this.setFixedColor(pCompound.getInt("Color"));
        } else {
            this.updateColor();
        }

    }

    protected void doPostHurtEffects(LivingEntity pLiving) {
        super.doPostHurtEffects(pLiving);
        Entity $$1 = this.getEffectSource();
        Iterator var3 = this.potion.getEffects().iterator();

        MobEffectInstance $$3;
        while(var3.hasNext()) {
            $$3 = (MobEffectInstance)var3.next();
            pLiving.addEffect(new MobEffectInstance($$3.getEffect(), Math.max($$3.getDuration() / 8, 1), $$3.getAmplifier(), $$3.isAmbient(), $$3.isVisible()), $$1);
        }

        if (!this.effects.isEmpty()) {
            var3 = this.effects.iterator();

            while(var3.hasNext()) {
                $$3 = (MobEffectInstance)var3.next();
                pLiving.addEffect($$3, $$1);
            }
        }

    }

    protected ItemStack getPickupItem() {
        if (this.effects.isEmpty() && this.potion == Potions.EMPTY) {
            return new ItemStack(DMItems.CYBER_LASER.get());
        } else {
            ItemStack $$0 = new ItemStack(Items.TIPPED_ARROW);
            PotionUtils.setPotion($$0, this.potion);
            PotionUtils.setCustomEffects($$0, this.effects);
            if (this.fixedColor) {
                $$0.getOrCreateTag().putInt("CustomPotionColor", this.getColor());
            }

            return $$0;
        }
    }

    public void handleEntityEvent(byte pId) {
        if (pId == 0) {
            int $$1 = this.getColor();
            if ($$1 != -1) {
                double $$2 = (double)($$1 >> 16 & 255) / 255.0D;
                double $$3 = (double)($$1 >> 8 & 255) / 255.0D;
                double $$4 = (double)($$1 >> 0 & 255) / 255.0D;

                for(int $$5 = 0; $$5 < 20; ++$$5) {
                    this.level.addParticle(ParticleTypes.ENTITY_EFFECT, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), $$2, $$3, $$4);
                }
            }
        } else {
            super.handleEntityEvent(pId);
        }

    }

    static {
        ID_EFFECT_COLOR = SynchedEntityData.defineId(ClassicDalekLaser.class, EntityDataSerializers.INT);
    }
}
