package be.ninedocteur.docmod.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class CoupeBlock extends HorizontalDirectionalBlock {
    public CoupeBlock(Properties builder){
        super(builder);
    }

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.box(12, 11, 7, 13, 12, 9),
            Block.box(5, 0, 5, 11, 2, 11),
            Block.box(6, 0.19999999999999996, 4.9, 10, 1.7, 5.1000000000000005),
            Block.box(6, 0, 6, 10, 5, 10),
            Block.box(5, 5, 6, 6, 6, 10),
            Block.box(6, 5, 10, 10, 6, 11),
            Block.box(6, 5, 5, 10, 6, 6),
            Block.box(5, 6, 4, 11, 14, 5),
            Block.box(5, 6, 11, 11, 14, 12),
            Block.box(4, 6, 5, 5, 14, 11),
            Block.box(11, 6, 5, 12, 14, 11),
            Block.box(10, 5, 6, 11, 6, 10),
            Block.box(5, 5, 5, 6, 6, 6),
            Block.box(5, 5, 10, 6, 6, 11),
            Block.box(10, 5, 10, 11, 6, 11),
            Block.box(10, 5, 5, 11, 6, 6),
            Block.box(5, 4, 7, 6, 5, 9),
            Block.box(4, 5, 7, 5, 6, 9),
            Block.box(3, 6, 7, 4, 7, 9),
            Block.box(2, 7, 7, 3, 8, 9),
            Block.box(1, 8, 7, 2, 9, 9),
            Block.box(1, 9, 7, 2, 10, 9),
            Block.box(1, 10, 7, 2, 11, 9),
            Block.box(2, 11, 7, 3, 12, 9),
            Block.box(3, 11, 7, 4, 12, 9),
            Block.box(13, 11, 7, 14, 12, 9),
            Block.box(14, 10, 7, 15, 11, 9),
            Block.box(14, 9, 7, 15, 10, 9),
            Block.box(14, 8, 7, 15, 9, 9),
            Block.box(13, 7, 7, 14, 8, 9),
            Block.box(12, 6, 7, 13, 7, 9),
            Block.box(11, 5, 7, 12, 6, 9),
            Block.box(10, 4, 7, 11, 5, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.box(5, 0, 5, 11, 2, 11),
            Block.box(4.9, 0.19999999999999996, 6, 5.1000000000000005, 1.7, 10),
            Block.box(6, 0, 6, 10, 5, 10),
            Block.box(6, 5, 10, 10, 6, 11),
            Block.box(10, 5, 6, 11, 6, 10),
            Block.box(5, 5, 6, 6, 6, 10),
            Block.box(4, 6, 5, 5, 14, 11),
            Block.box(11, 6, 5, 12, 14, 11),
            Block.box(5, 6, 11, 11, 14, 12),
            Block.box(5, 6, 4, 11, 14, 5),
            Block.box(6, 5, 5, 10, 6, 6),
            Block.box(5, 5, 10, 6, 6, 11),
            Block.box(10, 5, 10, 11, 6, 11),
            Block.box(10, 5, 5, 11, 6, 6),
            Block.box(5, 5, 5, 6, 6, 6),
            Block.box(7, 4, 10, 9, 5, 11),
            Block.box(7, 5, 11, 9, 6, 12),
            Block.box(7, 6, 12, 9, 7, 13),
            Block.box(7, 7, 13, 9, 8, 14),
            Block.box(7, 8, 14, 9, 9, 15),
            Block.box(7, 9, 14, 9, 10, 15),
            Block.box(7, 10, 14, 9, 11, 15),
            Block.box(7, 11, 13, 9, 12, 14),
            Block.box(7, 11, 12, 9, 12, 13),
            Block.box(7, 11, 2, 9, 12, 3),
            Block.box(7, 10, 1, 9, 11, 2),
            Block.box(7, 9, 1, 9, 10, 2),
            Block.box(7, 8, 1, 9, 9, 2),
            Block.box(7, 7, 2, 9, 8, 3),
            Block.box(7, 6, 3, 9, 7, 4),
            Block.box(7, 5, 4, 9, 6, 5),
            Block.box(7, 4, 5, 9, 5, 6),
            Block.box(7, 11, 3, 9, 12, 4)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.box(5, 0, 5, 11, 2, 11),
            Block.box(6, 0.19999999999999996, 10.899999999999999, 10, 1.7, 11.1),
            Block.box(6, 0, 6, 10, 5, 10),
            Block.box(10, 5, 6, 11, 6, 10),
            Block.box(6, 5, 5, 10, 6, 6),
            Block.box(6, 5, 10, 10, 6, 11),
            Block.box(5, 6, 11, 11, 14, 12),
            Block.box(5, 6, 4, 11, 14, 5),
            Block.box(11, 6, 5, 12, 14, 11),
            Block.box(4, 6, 5, 5, 14, 11),
            Block.box(5, 5, 6, 6, 6, 10),
            Block.box(10, 5, 10, 11, 6, 11),
            Block.box(10, 5, 5, 11, 6, 6),
            Block.box(5, 5, 5, 6, 6, 6),
            Block.box(5, 5, 10, 6, 6, 11),
            Block.box(10, 4, 7, 11, 5, 9),
            Block.box(11, 5, 7, 12, 6, 9),
            Block.box(12, 6, 7, 13, 7, 9),
            Block.box(13, 7, 7, 14, 8, 9),
            Block.box(14, 8, 7, 15, 9, 9),
            Block.box(14, 9, 7, 15, 10, 9),
            Block.box(14, 10, 7, 15, 11, 9),
            Block.box(13, 11, 7, 14, 12, 9),
            Block.box(12, 11, 7, 13, 12, 9),
            Block.box(2, 11, 7, 3, 12, 9),
            Block.box(1, 10, 7, 2, 11, 9),
            Block.box(1, 9, 7, 2, 10, 9),
            Block.box(1, 8, 7, 2, 9, 9),
            Block.box(2, 7, 7, 3, 8, 9),
            Block.box(3, 6, 7, 4, 7, 9),
            Block.box(4, 5, 7, 5, 6, 9),
            Block.box(5, 4, 7, 6, 5, 9),
            Block.box(3, 11, 7, 4, 12, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.box(5, 0, 5, 11, 2, 11),
            Block.box(10.899999999999999, 0.19999999999999996, 6, 11.1, 1.7, 10),
            Block.box(6, 0, 6, 10, 5, 10),
            Block.box(6, 5, 5, 10, 6, 6),
            Block.box(5, 5, 6, 6, 6, 10),
            Block.box(10, 5, 6, 11, 6, 10),
            Block.box(11, 6, 5, 12, 14, 11),
            Block.box(4, 6, 5, 5, 14, 11),
            Block.box(5, 6, 4, 11, 14, 5),
            Block.box(5, 6, 11, 11, 14, 12),
            Block.box(6, 5, 10, 10, 6, 11),
            Block.box(10, 5, 5, 11, 6, 6),
            Block.box(5, 5, 5, 6, 6, 6),
            Block.box(5, 5, 10, 6, 6, 11),
            Block.box(10, 5, 10, 11, 6, 11),
            Block.box(7, 4, 5, 9, 5, 6),
            Block.box(7, 5, 4, 9, 6, 5),
            Block.box(7, 6, 3, 9, 7, 4),
            Block.box(7, 7, 2, 9, 8, 3),
            Block.box(7, 8, 1, 9, 9, 2),
            Block.box(7, 9, 1, 9, 10, 2),
            Block.box(7, 10, 1, 9, 11, 2),
            Block.box(7, 11, 2, 9, 12, 3),
            Block.box(7, 11, 3, 9, 12, 4),
            Block.box(7, 11, 13, 9, 12, 14),
            Block.box(7, 10, 14, 9, 11, 15),
            Block.box(7, 9, 14, 9, 10, 15),
            Block.box(7, 8, 14, 9, 9, 15),
            Block.box(7, 7, 13, 9, 8, 14),
            Block.box(7, 6, 12, 9, 7, 13),
            Block.box(7, 5, 11, 9, 6, 12),
            Block.box(7, 4, 10, 9, 5, 11),
            Block.box(7, 11, 12, 9, 12, 13)
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
