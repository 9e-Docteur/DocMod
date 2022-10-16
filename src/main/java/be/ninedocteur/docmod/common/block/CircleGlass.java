package be.ninedocteur.docmod.common.block;


import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class CircleGlass extends HorizontalDirectionalBlock {
    public CircleGlass(Properties builder){
        super(builder);
    }

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.box(4, 0, 0, 12, 16, 1),
            Block.box(3, 0, 1, 4, 16, 2),
            Block.box(2, 0, 2, 3, 16, 3),
            Block.box(1, 0, 3, 2, 16, 4),
            Block.box(1, 0, 12, 2, 16, 13),
            Block.box(2, 0, 13, 3, 16, 14),
            Block.box(3, 0, 14, 4, 16, 15),
            Block.box(14, 0, 3, 15, 16, 4),
            Block.box(13, 0, 2, 14, 16, 3),
            Block.box(12, 0, 1, 13, 16, 2),
            Block.box(0, 0, 4, 1, 16, 12),
            Block.box(4, 0, 15, 12, 16, 16),
            Block.box(12, 0, 14, 13, 16, 15),
            Block.box(13, 0, 13, 14, 16, 14),
            Block.box(14, 0, 12, 15, 16, 13),
            Block.box(15, 0, 4, 16, 16, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.box(4, 0, 0, 12, 16, 1),
            Block.box(3, 0, 1, 4, 16, 2),
            Block.box(2, 0, 2, 3, 16, 3),
            Block.box(1, 0, 3, 2, 16, 4),
            Block.box(1, 0, 12, 2, 16, 13),
            Block.box(2, 0, 13, 3, 16, 14),
            Block.box(3, 0, 14, 4, 16, 15),
            Block.box(14, 0, 3, 15, 16, 4),
            Block.box(13, 0, 2, 14, 16, 3),
            Block.box(12, 0, 1, 13, 16, 2),
            Block.box(0, 0, 4, 1, 16, 12),
            Block.box(4, 0, 15, 12, 16, 16),
            Block.box(12, 0, 14, 13, 16, 15),
            Block.box(13, 0, 13, 14, 16, 14),
            Block.box(14, 0, 12, 15, 16, 13),
            Block.box(15, 0, 4, 16, 16, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.box(4, 0, 0, 12, 16, 1),
            Block.box(3, 0, 1, 4, 16, 2),
            Block.box(2, 0, 2, 3, 16, 3),
            Block.box(1, 0, 3, 2, 16, 4),
            Block.box(1, 0, 12, 2, 16, 13),
            Block.box(2, 0, 13, 3, 16, 14),
            Block.box(3, 0, 14, 4, 16, 15),
            Block.box(14, 0, 3, 15, 16, 4),
            Block.box(13, 0, 2, 14, 16, 3),
            Block.box(12, 0, 1, 13, 16, 2),
            Block.box(0, 0, 4, 1, 16, 12),
            Block.box(4, 0, 15, 12, 16, 16),
            Block.box(12, 0, 14, 13, 16, 15),
            Block.box(13, 0, 13, 14, 16, 14),
            Block.box(14, 0, 12, 15, 16, 13),
            Block.box(15, 0, 4, 16, 16, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SHAPE_E = Stream.of(
            Block.box(4, 0, 0, 12, 16, 1),
            Block.box(3, 0, 1, 4, 16, 2),
            Block.box(2, 0, 2, 3, 16, 3),
            Block.box(1, 0, 3, 2, 16, 4),
            Block.box(1, 0, 12, 2, 16, 13),
            Block.box(2, 0, 13, 3, 16, 14),
            Block.box(3, 0, 14, 4, 16, 15),
            Block.box(14, 0, 3, 15, 16, 4),
            Block.box(13, 0, 2, 14, 16, 3),
            Block.box(12, 0, 1, 13, 16, 2),
            Block.box(0, 0, 4, 1, 16, 12),
            Block.box(4, 0, 15, 12, 16, 16),
            Block.box(12, 0, 14, 13, 16, 15),
            Block.box(13, 0, 13, 14, 16, 14),
            Block.box(14, 0, 12, 15, 16, 13),
            Block.box(15, 0, 4, 16, 16, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        switch (p_60555_.getValue(FACING)) {
            case SOUTH:
                return SHAPE_S;
            case WEST:
                return SHAPE_W;
            case EAST:
                return SHAPE_E;
            default:
                return SHAPE_N;
        }
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
