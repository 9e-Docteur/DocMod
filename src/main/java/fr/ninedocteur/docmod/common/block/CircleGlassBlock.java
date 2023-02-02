package fr.ninedocteur.docmod.common.block;

import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

import java.util.stream.Stream;

public class CircleGlassBlock extends HorizontalFacingBlock {
    public CircleGlassBlock(Settings settings) {
        super(settings);
    }

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.createCuboidShape(4, 0, 0, 12, 16, 1),
            Block.createCuboidShape(3, 0, 1, 4, 16, 2),
            Block.createCuboidShape(2, 0, 2, 3, 16, 3),
            Block.createCuboidShape(1, 0, 3, 2, 16, 4),
            Block.createCuboidShape(1, 0, 12, 2, 16, 13),
            Block.createCuboidShape(2, 0, 13, 3, 16, 14),
            Block.createCuboidShape(3, 0, 14, 4, 16, 15),
            Block.createCuboidShape(14, 0, 3, 15, 16, 4),
            Block.createCuboidShape(13, 0, 2, 14, 16, 3),
            Block.createCuboidShape(12, 0, 1, 13, 16, 2),
            Block.createCuboidShape(0, 0, 4, 1, 16, 12),
            Block.createCuboidShape(4, 0, 15, 12, 16, 16),
            Block.createCuboidShape(12, 0, 14, 13, 16, 15),
            Block.createCuboidShape(13, 0, 13, 14, 16, 14),
            Block.createCuboidShape(14, 0, 12, 15, 16, 13),
            Block.createCuboidShape(15, 0, 4, 16, 16, 12)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.createCuboidShape(4, 0, 0, 12, 16, 1),
            Block.createCuboidShape(3, 0, 1, 4, 16, 2),
            Block.createCuboidShape(2, 0, 2, 3, 16, 3),
            Block.createCuboidShape(1, 0, 3, 2, 16, 4),
            Block.createCuboidShape(1, 0, 12, 2, 16, 13),
            Block.createCuboidShape(2, 0, 13, 3, 16, 14),
            Block.createCuboidShape(3, 0, 14, 4, 16, 15),
            Block.createCuboidShape(14, 0, 3, 15, 16, 4),
            Block.createCuboidShape(13, 0, 2, 14, 16, 3),
            Block.createCuboidShape(12, 0, 1, 13, 16, 2),
            Block.createCuboidShape(0, 0, 4, 1, 16, 12),
            Block.createCuboidShape(4, 0, 15, 12, 16, 16),
            Block.createCuboidShape(12, 0, 14, 13, 16, 15),
            Block.createCuboidShape(13, 0, 13, 14, 16, 14),
            Block.createCuboidShape(14, 0, 12, 15, 16, 13),
            Block.createCuboidShape(15, 0, 4, 16, 16, 12)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.createCuboidShape(4, 0, 0, 12, 16, 1),
            Block.createCuboidShape(3, 0, 1, 4, 16, 2),
            Block.createCuboidShape(2, 0, 2, 3, 16, 3),
            Block.createCuboidShape(1, 0, 3, 2, 16, 4),
            Block.createCuboidShape(1, 0, 12, 2, 16, 13),
            Block.createCuboidShape(2, 0, 13, 3, 16, 14),
            Block.createCuboidShape(3, 0, 14, 4, 16, 15),
            Block.createCuboidShape(14, 0, 3, 15, 16, 4),
            Block.createCuboidShape(13, 0, 2, 14, 16, 3),
            Block.createCuboidShape(12, 0, 1, 13, 16, 2),
            Block.createCuboidShape(0, 0, 4, 1, 16, 12),
            Block.createCuboidShape(4, 0, 15, 12, 16, 16),
            Block.createCuboidShape(12, 0, 14, 13, 16, 15),
            Block.createCuboidShape(13, 0, 13, 14, 16, 14),
            Block.createCuboidShape(14, 0, 12, 15, 16, 13),
            Block.createCuboidShape(15, 0, 4, 16, 16, 12)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
    
    private static final VoxelShape SHAPE_E = Stream.of(
            Block.createCuboidShape(4, 0, 0, 12, 16, 1),
            Block.createCuboidShape(3, 0, 1, 4, 16, 2),
            Block.createCuboidShape(2, 0, 2, 3, 16, 3),
            Block.createCuboidShape(1, 0, 3, 2, 16, 4),
            Block.createCuboidShape(1, 0, 12, 2, 16, 13),
            Block.createCuboidShape(2, 0, 13, 3, 16, 14),
            Block.createCuboidShape(3, 0, 14, 4, 16, 15),
            Block.createCuboidShape(14, 0, 3, 15, 16, 4),
            Block.createCuboidShape(13, 0, 2, 14, 16, 3),
            Block.createCuboidShape(12, 0, 1, 13, 16, 2),
            Block.createCuboidShape(0, 0, 4, 1, 16, 12),
            Block.createCuboidShape(4, 0, 15, 12, 16, 16),
            Block.createCuboidShape(12, 0, 14, 13, 16, 15),
            Block.createCuboidShape(13, 0, 13, 14, 16, 14),
            Block.createCuboidShape(14, 0, 12, 15, 16, 13),
            Block.createCuboidShape(15, 0, 4, 16, 16, 12)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (state.get(FACING)) {
            case NORTH:
            default:
                return SHAPE_N;
            case WEST:
                return SHAPE_W;
            case SOUTH:
                return SHAPE_S;
            case EAST:
                return SHAPE_E;
        }
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }
}
