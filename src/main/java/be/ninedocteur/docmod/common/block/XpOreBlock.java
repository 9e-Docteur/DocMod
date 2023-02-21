package be.ninedocteur.docmod.common.block;

import be.ninedocteur.docmod.jobs.JobFactory;
import be.ninedocteur.docmod.jobs.data.PlayerData;
import be.ninedocteur.docmod.jobs.util.Constants;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

public class XpOreBlock extends Block {
    public XpOreBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        ExperienceOrb experienceOrb = new ExperienceOrb(level, pos.getX(), pos.getY(), pos.getZ(), 40);
        level.addFreshEntity(experienceOrb);
        //PlayerData.getPlayerJobs(player).gainXP(Constants.Job.MINER, 25, (ServerPlayer) player);
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }
}
