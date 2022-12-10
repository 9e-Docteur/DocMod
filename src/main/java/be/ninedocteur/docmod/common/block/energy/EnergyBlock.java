package be.ninedocteur.docmod.common.block.energy;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public abstract class EnergyBlock extends BaseEntityBlock {
    public EnergyBlock(Properties p_49795_) {
        super(p_49795_);
    }
}
