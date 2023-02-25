package be.ninedocteur.docmod.common.block;

import be.ninedocteur.docmod.common.tileentity.PandaTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;

public class PandaRebelBlock extends HorizontalDirectionalBlock implements EntityBlock {

    public PandaRebelBlock(Properties p_49224_) {
        super(p_49224_);
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new PandaTileEntity(pPos, pState);
    }

    public BlockEntity create(BlockPos p_155268_, BlockState p_155269_) {
        return new PandaTileEntity(p_155268_, p_155269_);
    }
}
