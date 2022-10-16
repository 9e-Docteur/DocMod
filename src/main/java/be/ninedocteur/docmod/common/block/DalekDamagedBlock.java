package be.ninedocteur.docmod.common.block;

import be.ninedocteur.docmod.common.tileentity.DamagedDalekTileEntity;
import be.ninedocteur.docmod.common.tileentity.ToyotaRotorTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class DalekDamagedBlock extends BaseEntityBlock {
    public DalekDamagedBlock(Properties p_49224_) {
        super(p_49224_);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new DamagedDalekTileEntity(pPos, pState);
    }

    public BlockEntity create(BlockPos p_155268_, BlockState p_155269_) {
        return new DamagedDalekTileEntity(p_155268_, p_155269_);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.FACING);
    }

    @javax.annotation.Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(BlockStateProperties.FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public void animateTick(BlockState p_220827_, Level p_220828_, BlockPos p_220829_, RandomSource p_220830_) {
        float chance = 0.8f;
        float smokeChance = 1f;
        if (chance < p_220830_.nextFloat()) {
            p_220828_.addParticle(ParticleTypes.LAVA, p_220829_.getX() + p_220830_.nextDouble(),
                    p_220829_.getY() + 0.5D, p_220829_.getZ() + p_220830_.nextDouble(),
                    0d, 0.08d, 0d);
        }

        if (smokeChance < p_220830_.nextFloat()) {
            p_220828_.addParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, p_220829_.getX() + p_220830_.nextDouble(),
                    p_220829_.getY() + 0.5D, p_220829_.getZ() + p_220830_.nextDouble(),
                    0d, 0.08d, 0d);

            super.animateTick(p_220827_, p_220828_, p_220829_, p_220830_);
        }
    }
}
