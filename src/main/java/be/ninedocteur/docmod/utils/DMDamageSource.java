package be.ninedocteur.docmod.utils;

import be.ninedocteur.docmod.common.entity.projectile.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nullable;

public class DMDamageSource extends DamageSource{

    public DMDamageSource(String p_19333_) {
        super(p_19333_);
    }

    public static DamageSource cyberLaser(AbstractLaser pArrow, @Nullable Entity pIndirectEntity) {
        return (new IndirectEntityDamageSource("cyber_laser", pArrow, pIndirectEntity)).setProjectile();
    }

    public static DamageSource dalekLaser(AbstractDalekLaser pArrow, @Nullable Entity pIndirectEntity) {
        return (new IndirectEntityDamageSource("dalek_laser", pArrow, pIndirectEntity)).setProjectile();
    }

    public static DamageSource rpgLaser(AbstractRPGLaser pArrow, @Nullable Entity pIndirectEntity) {
        return (new IndirectEntityDamageSource("rpg_laser", pArrow, pIndirectEntity)).setProjectile();
    }

    public static DamageSource magicLaser(AbstractWandLaser pArrow, @Nullable Entity pIndirectEntity) {
        return (new IndirectEntityDamageSource("magic_laser", pArrow, pIndirectEntity)).setProjectile();
    }

    public static DamageSource classicDalekLaser(AbstractClassicDalekLaser pArrow, @Nullable Entity pIndirectEntity) {
        return (new IndirectEntityDamageSource("classic_dalek_laser", pArrow, pIndirectEntity)).setProjectile();
    }

    public static DamageSource deadByTNT(LivingEntity entity){
        return DamageSource.explosion(entity);
    }
}
