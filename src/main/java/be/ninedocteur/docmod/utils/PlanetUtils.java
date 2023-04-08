package be.ninedocteur.docmod.utils;

import be.ninedocteur.docmod.common.block.tileentity.TardisEntity;
import be.ninedocteur.docmod.common.entity.mob.CybermanEntity;
import be.ninedocteur.docmod.common.entity.mob.Dalek;
import be.ninedocteur.docmod.common.entity.mob.SWDalek;
import be.ninedocteur.docmod.common.init.DMItems;
import be.ninedocteur.docmod.common.item.space.SpaceSuitHelmet;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class PlanetUtils {
    @SubscribeEvent
    public static void initMoon(LivingEvent.LivingTickEvent e){
        //GRAVITY
        if(getDimension(e, "moon")){
            if(e.getEntity() instanceof Player livingEntityEntity) {
                if(!livingEntityEntity.isCreative()) {
                    setGravity(livingEntityEntity, Gravity.MOON);
                   // if(!checkForSpaceSuit(livingEntityEntity) && !airPresent()) {
                      //  livingEntityEntity.hurt(DMDamageSource.SUFFOCATE_ON_A_UNOXYGENED_PLANET, 1.5F);
                    //}
                    setNoFallDamage(livingEntityEntity);
                }
            }
        }
    }

    @SubscribeEvent
    public static void initSpace(LivingEvent.LivingTickEvent e){
        //GRAVITY
        if(getDimension(e, "space")){
            if(e.getEntity() instanceof Player livingEntityEntity) {
                if(!livingEntityEntity.isCreative()) {
                    setGravity(livingEntityEntity, Gravity.SPACE);
                    //if(!checkForSpaceSuit(livingEntityEntity) && !airPresent()) {
                       // livingEntityEntity.hurt(DMDamageSource.SUFFOCATE_ON_A_UNOXYGENED_PLANET, 1.5F);
                    //}
                    setNoFallDamage(livingEntityEntity);
                }
            }
        }
    }

    public static boolean checkForSpaceSuit(Player player){
        return player.getInventory().getArmor(3).is(DMItems.SPACE_HELMET.get()) && player.getInventory().getArmor(2).is(DMItems.SPACE_CHESTPLATE.get()) && player.getInventory().getArmor(1).is(DMItems.SPACE_LEGGINGS.get()) && player.getInventory().getArmor(0).is(DMItems.SPACE_BOOTS.get());
    }

    public static boolean isHelmetPresent(Player player){
        return player.getInventory().getArmor(3).is(DMItems.SPACE_HELMET.get());
    }

//    public static boolean airPresent(){
//         return SpaceSuitHelmet.air <= 0;
//    }

    public static Vec3 setGravity(Player player, double gravity){
        Vec3 motion = player.getDeltaMovement();
        player.setDeltaMovement(motion.add(0, gravity, 0));
        return motion;
    }

    public static Vec3 setGravity(LivingEntity livingEntity, Gravity gravity){
        Vec3 motion = livingEntity.getDeltaMovement();
        if(gravity == Gravity.MOON){
            livingEntity.setDeltaMovement(motion.add(0, 0.05D, 0));
        }
        else if(gravity == Gravity.OVERWORLD) {

        }
        else if(gravity == Gravity.HARD){
            livingEntity.setDeltaMovement(motion.add(0D, -0.05D, 0D));
        }
        else if(gravity == Gravity.SPACE){

                if (livingEntity instanceof Dalek || livingEntity instanceof ArmorStand || livingEntity instanceof CybermanEntity || livingEntity instanceof Mob) {
                    livingEntity.setDeltaMovement(motion.add(0, 0.1D, 0));
                }

                if (livingEntity.isShiftKeyDown()) {
                    livingEntity.setDeltaMovement(motion.multiply(1.0D, 1D, 1.0D));
                }

        }
        return motion;
    }

    public static void setNoFallDamage(Player player){
        player.fallDistance = 0;
    }

    public static boolean getDimension(LivingEvent.LivingTickEvent event, String path){
        return event.getEntity().level.dimension().location().getPath().equals(path);
    }

    public static boolean getDimension(String path){
        return Minecraft.getInstance().level.dimension().location().getPath().equals(path);
    }
}
