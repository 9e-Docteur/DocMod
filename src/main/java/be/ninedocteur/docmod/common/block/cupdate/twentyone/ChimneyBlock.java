package be.ninedocteur.docmod.common.block.cupdate.twentyone;

import be.ninedocteur.docmod.common.block.cupdate.ChristmasHorizontalDirectionalBlock;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

import javax.annotation.Nullable;

public class ChimneyBlock extends ChristmasHorizontalDirectionalBlock {

    public ChimneyBlock(Properties builder) {
        super(builder);
        this.setAddedYear(2021);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
}
