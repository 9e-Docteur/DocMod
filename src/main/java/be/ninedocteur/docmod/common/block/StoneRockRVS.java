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

public class StoneRockRVS extends HorizontalDirectionalBlock {
    public StoneRockRVS(Properties builder){
        super(builder);
    }

    private static final VoxelShape SHAPE_N =
            Stream.of(
                    Block.box(1, 0, 2, 6, 0.5, 5),
                    Block.box(11, 0, 0, 15, 0.8, 3),
                    Block.box(10, 0, 7, 15, 0.2, 10),
                    Block.box(10, 0, 4, 11, 1, 5),
                    Block.box(5, 0, 8, 7, 1, 10),
                    Block.box(2, 0, 12, 6, 0.6, 15),
                    Block.box(10, 0, 12, 15, 0.6, 15)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();


    private static final VoxelShape SHAPE_S =
            Stream.of(
                    Block.box(10, 0, 11, 15, 0.5, 14),
                    Block.box(1, 0, 13, 5, 0.8, 16),
                    Block.box(1, 0, 6, 6, 0.2, 9),
                    Block.box(5, 0, 11, 6, 1, 12),
                    Block.box(9, 0, 6, 11, 1, 8),
                    Block.box(10, 0, 1, 14, 0.6, 4),
                    Block.box(1, 0, 1, 6, 0.6, 4)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();


    private static final VoxelShape SHAPE_E =
            Stream.of(
                    Block.box(11, 0, 1, 14, 0.5, 6),
                    Block.box(13, 0, 11, 16, 0.8, 15),
                    Block.box(6, 0, 10, 9, 0.2, 15),
                    Block.box(11, 0, 10, 12, 1, 11),
                    Block.box(6, 0, 5, 8, 1, 7),
                    Block.box(1, 0, 2, 4, 0.6, 6),
                    Block.box(1, 0, 10, 4, 0.6, 15)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();


    private static final VoxelShape SHAPE_W =
            Stream.of(
                    Block.box(2, 0, 10, 5, 0.5, 15),
                    Block.box(0, 0, 1, 3, 0.8, 5),
                    Block.box(7, 0, 1, 10, 0.2, 6),
                    Block.box(4, 0, 5, 5, 1, 6),
                    Block.box(8, 0, 9, 10, 1, 11),
                    Block.box(12, 0, 10, 15, 0.6, 14),
                    Block.box(12, 0, 1, 15, 0.6, 6)
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
