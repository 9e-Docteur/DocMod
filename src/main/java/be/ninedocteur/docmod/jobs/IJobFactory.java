package be.ninedocteur.docmod.jobs;

import be.ninedocteur.docmod.jobs.util.Constants;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.state.BlockState;

public interface IJobFactory {
    void giveXP(BlockState object, Integer xpToGive, Constants.Job fromJob, Integer fromLevel, Integer toLevel);
    void removeXP(BlockState object, Integer xpToRemove, Constants.Job fromJob, Integer fromLevel, Integer toLevel);
    void executeActionFromBlockstate(BlockState blockState);
}
