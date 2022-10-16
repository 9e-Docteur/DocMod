package be.ninedocteur.docmod.common.tileentity;

import be.ninedocteur.docmod.common.init.DMTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class GreenGlassTubeTile extends BlockEntity {

    public GreenGlassTubeTile(BlockPos pos, BlockState state) {
        super(DMTileEntity.GreenGlassTile.get(), pos, state);
    }
}
