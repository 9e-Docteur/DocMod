package be.ninedocteur.docmod.common.tileentity;

import be.ninedocteur.docmod.common.entity.SittableEntity;
import be.ninedocteur.docmod.common.init.DMTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ChairTileEntity extends BlockEntity {

    public ChairTileEntity(BlockPos pos, BlockState state) {
        super(DMTileEntity.CHAIR_TILE_ENTITY.get(), pos, state);
    }
    public SittableEntity seat;
    public int ticks = 0;


    public SittableEntity getOrCreateSeat() {
        if (this.seat == null) {
            final var seat = new SittableEntity(this.level);
            seat.absMoveTo(this.worldPosition.getX() + 0.5D, this.worldPosition.getY(),
                    this.worldPosition.getZ() + 0.5D,
                    getBlockState().getValue(HorizontalDirectionalBlock.FACING).toYRot(), 0.0f);
            this.level.addFreshEntity(seat);
            this.seat = seat;
        }

        return this.seat;
    }
    @Override
    public void onLoad() {
        super.onLoad();
        getOrCreateSeat();
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        if (this.seat != null) {
            this.seat.kill();
        }
    }

    public void tick() {

        if (this.ticks % 5 == 0 && (this.seat == null || this.seat.isRemoved())) {
            if (this.seat != null) {
                this.seat.kill();
                this.seat = null;
            }
            getOrCreateSeat();
        }
        this.ticks++;
    }
}
