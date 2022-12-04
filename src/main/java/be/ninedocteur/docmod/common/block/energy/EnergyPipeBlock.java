package be.ninedocteur.docmod.common.block.energy;

import be.ninedocteur.docmod.common.tileentity.EnergyPipeTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

import javax.annotation.Nullable;

public class EnergyPipeBlock extends BaseEntityBlock {
    public EnergyPipeBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return new EnergyPipeTileEntity(p_153215_, p_153216_);
    }
}
