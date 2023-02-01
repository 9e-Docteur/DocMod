package fr.ninedocteur.docmod.common.block.entity;

import fr.ninedocteur.docmod.common.init.DMTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class GlassTubeEntity extends BlockEntity {
    public GlassTubeEntity(BlockPos pos, BlockState state) {
        super(DMTileEntities.GlassTile, pos, state);
    }
}
