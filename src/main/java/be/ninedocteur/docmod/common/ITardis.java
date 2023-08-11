package be.ninedocteur.docmod.common;

import be.ninedocteur.docmod.common.tileentity.TardisTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.INBTSerializable;

public interface ITardis extends INBTSerializable<CompoundTag> {

    Level getLevel();
    BlockPos getTargetPosition();
    BlockPos getCurrentPosition();
    ResourceKey<Level> getCurrentDimension();
    ResourceKey<Level> getTargetDimension();
    TardisTileEntity.State getCurrentState();

    void demat();
    void tick();
    void remat();
}
