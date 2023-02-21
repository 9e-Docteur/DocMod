package be.ninedocteur.docmod.common.block;

import be.ninedocteur.docmod.jobs.JobFactory;
import be.ninedocteur.docmod.jobs.util.Constants;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;

public class ElectronicVineBlock extends Block {

    public ElectronicVineBlock(Properties p_49795_) {
        super(p_49795_);
    }

    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        pEntity.makeStuckInBlock(pState, new Vec3(0.25D, 0.05F, 0.25D));
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        JobFactory.giveXP(1, Constants.Job.MAGICIAN, 0, 2);
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }
}
