package be.ninedocteur.docmod.common.tileentity;

import be.ninedocteur.docmod.common.init.DMTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class MonitorTileEntity extends BlockEntity {
    public CompoundTag compoundTag = new CompoundTag();
    public long computerPos;
    public boolean isLinkedToComputer;

    public MonitorTileEntity(BlockPos pos, BlockState state) {
        super(DMTileEntity.MONITOR_TILE_ENTITY.get(), pos, state);
    }

    @Override
    public CompoundTag serializeNBT() {
        return super.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        super.deserializeNBT(nbt);

    }
}
