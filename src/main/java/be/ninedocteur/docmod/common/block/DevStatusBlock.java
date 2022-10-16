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

public class DevStatusBlock extends HorizontalDirectionalBlock {
    public DevStatusBlock(Properties builder){
        super(builder);
    }

    private static final VoxelShape SHAPE_N =
            Stream.of(
                    Block.box(4.1, 0, 6, 11.899999999999999, 24, 10),
                    Block.box(1, 12, 6, 4, 24, 10),
                    Block.box(12, 12, 6, 15, 24, 10),
                    Block.box(4, 24, 4, 12, 32, 12)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();


    private static final VoxelShape SHAPE_S =
            Stream.of(
                    Block.box(3.5, 23, 3.5, 12.5, 32, 12.5),
                    Block.box(4, 12, 6, 12, 24, 10),
                    Block.box(3.75, 11.75, 5.75, 12.25, 24.25, 10.25),
                    Block.box(0, 12, 6, 4, 24, 10),
                    Block.box(-0.25, 11.75, 5.75, 4.25, 24.25, 10.25),
                    Block.box(4.1, 0, 6, 8.1, 12, 10),
                    Block.box(3.85, -0.25, 5.75, 8.35, 12.25, 10.25),
                    Block.box(7.9, 0, 6, 11.9, 12, 10),
                    Block.box(7.65, -0.25, 5.75, 12.15, 12.25, 10.25),
                    Block.box(11.75, 11.75, 5.75, 16.25, 24.25, 10.25),
                    Block.box(12, 12, 6, 16, 24, 10)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();


    private static final VoxelShape SHAPE_E =
            Stream.of(
            Block.box(3.5, 23, 3.5, 12.5, 32, 12.5),
        Block.box(6, 12, 4, 10, 24, 12),
        Block.box(5.75, 11.75, 3.75, 10.25, 24.25, 12.25),
        Block.box(6, 12, 12, 10, 24, 16),
        Block.box(5.75, 11.75, 11.75, 10.25, 24.25, 16.25),
        Block.box(6, 0, 7.9, 10, 12, 11.9),
        Block.box(5.75, -0.25, 7.65, 10.25, 12.25, 12.15),
        Block.box(6, 0, 4.1, 10, 12, 8.1),
        Block.box(5.75, -0.25, 3.8499999999999996, 10.25, 12.25, 8.35),
        Block.box(5.75, 11.75, -0.25, 10.25, 24.25, 4.25),
        Block.box(6, 12, 0, 10, 24, 4)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();


    private static final VoxelShape SHAPE_W =
            Stream.of(
            Block.box(3.5, 23, 3.5, 12.5, 32, 12.5),
Block.box(6, 12, 4, 10, 24, 12),
        Block.box(5.75, 11.75, 3.75, 10.25, 24.25, 12.25),
        Block.box(6, 12, 12, 10, 24, 16),
        Block.box(5.75, 11.75, 11.75, 10.25, 24.25, 16.25),
        Block.box(6, 0, 7.9, 10, 12, 11.9),
        Block.box(5.75, -0.25, 7.65, 10.25, 12.25, 12.15),
        Block.box(6, 0, 4.1, 10, 12, 8.1),
        Block.box(5.75, -0.25, 3.8499999999999996, 10.25, 12.25, 8.35),
        Block.box(5.75, 11.75, -0.25, 10.25, 24.25, 4.25),
        Block.box(6, 12, 0, 10, 24, 4)
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
