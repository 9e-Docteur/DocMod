package be.ninedocteur.docmod.jobs;

import be.ninedocteur.docmod.jobs.util.Constants;
import net.minecraft.world.level.block.state.BlockState;

public interface IJobFactory {
    void giveXP(Object object, Integer xpToGive, Constants.Job fromJob, Integer fromLevel, Integer toLevel);
    void removeXP(Object object, Integer xpToRemove, Constants.Job fromJob, Integer fromLevel, Integer toLevel);
    void executeActionFromBlockstate(BlockState blockState);
}
