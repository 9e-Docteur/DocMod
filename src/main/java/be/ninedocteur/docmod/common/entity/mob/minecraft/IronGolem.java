package be.ninedocteur.docmod.common.entity.mob.minecraft;

import be.ninedocteur.docmod.common.entity.mob.OldSteve;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.level.Level;

public class IronGolem extends net.minecraft.world.entity.animal.IronGolem {

    public IronGolem(EntityType<? extends net.minecraft.world.entity.animal.IronGolem> p_28834_, Level p_28835_) {
        super(p_28834_, p_28835_);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, OldSteve.class, 6.0F, 0.5D, 0.5D));
        super.registerGoals();
    }
}
