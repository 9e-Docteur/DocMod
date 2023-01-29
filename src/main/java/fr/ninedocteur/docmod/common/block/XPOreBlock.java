package fr.ninedocteur.docmod.common.block;

import net.minecraft.block.BlockState;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class XPOreBlock extends DMOreBlock{
    private final int experienceDropped;

    public XPOreBlock(boolean isDeepSlate, float hardness, float resistance, int xp) {
            super(isDeepSlate, hardness, resistance);
            this.experienceDropped = xp;
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        ExperienceOrbEntity experienceOrb = new ExperienceOrbEntity(world, pos.getX(), pos.getY(), pos.getZ(), experienceDropped);
        world.spawnEntity(experienceOrb);
        super.onBreak(world, pos, state, player);
    }
}
