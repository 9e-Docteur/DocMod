package be.ninedocteur.docmod.common.tileentity;

import be.ninedocteur.docmod.common.init.DMTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class RedGlassTubeTile extends BlockEntity {

    public RedGlassTubeTile(BlockPos pos, BlockState state) {
        super(DMTileEntity.RedGlassTile.get(), pos, state);
    }
}
