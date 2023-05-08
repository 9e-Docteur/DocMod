package be.ninedocteur.docmod.common.block;

import be.ninedocteur.docmod.common.tileentity.HartnellTileEntity;
import be.ninedocteur.docmod.common.tileentity.ImageTileEntity;
import be.ninedocteur.docmod.common.tileentity.ToyotaRotorTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
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

public class ImageLoaderBlock extends BaseEntityBlock {
    public ImageLoaderBlock(Properties p_49224_) {
        super(p_49224_);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new ImageTileEntity(pPos, pState);
    }

    public BlockEntity create(BlockPos p_155268_, BlockState p_155269_) {
        return new ImageTileEntity(p_155268_, p_155269_);
    }
}
