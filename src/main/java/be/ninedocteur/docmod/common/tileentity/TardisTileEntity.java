package be.ninedocteur.docmod.common.tileentity;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.init.DMTileEntity;
import be.ninedocteur.docmod.common.network.DMPackets;
import be.ninedocteur.docmod.common.network.packets.TardisDematPacket;
import be.ninedocteur.docmod.common.network.packets.TardisRematPacket;
import be.ninedocteur.docmod.common.world.dimension.DMDimension;
import be.ninedocteur.docmod.utils.LevelUtils;
import be.ninedocteur.docmod.utils.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraftforge.network.NetworkDirection;

import java.util.*;

public class TardisTileEntity extends BlockEntity {

    private int id;
    public static HashMap<Integer, TardisTileEntity> tardisMap = new HashMap<>();

    public boolean isLocked = false;
    public boolean isDemating = false;
    public boolean isAlreadyDemat = false;
    public boolean isOn = true;
    public BlockPos targetPosition;
    public BlockPos currentPosition;
    public ResourceKey<Level> targetDimension;
    public ResourceKey<Level> currentDimension;
    public UUID ownerUUID;
    private boolean visible;

    public TardisTileEntity(BlockPos pos, BlockState state) {
        super(DMTileEntity.Tardis.get(), pos, state);
        Level mLevel = Minecraft.getInstance().level;
        ChunkAccess chunk = mLevel.getChunk(this.getCurrentLocation());
        if(mLevel instanceof ServerLevel serverLevel){
            serverLevel.setChunkForced(chunk.getPos().x, chunk.getPos().z, true);
        }
        mLevel.getChunkSource().updateChunkForced(chunk.getPos(), true);
    }

    public static TardisTileEntity getOrCreateTardis(BlockPos pos, BlockState state, int id){
        if(tardisMap.containsKey(id)){
            return tardisMap.get(id);
        }
        return new TardisTileEntity(pos, state);
    }

    public static TardisTileEntity getOrCreateTardis(int id){
        if(tardisMap.containsKey(id)){
            return tardisMap.get(id);
        }
        return null;
    }

    public static TardisTileEntity getOrCreateTardis(int id, UUID playerUUID){
        if(tardisMap.containsKey(id)){
            return tardisMap.get(id);
        }
        return null;
    }

    public int getId(){
        return id;
    }

    public void generateId(UUID ownerUUID){
        id = tardisMap.size() + 1;
        this.ownerUUID = ownerUUID;
        tardisMap.put(id, this);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("id", id);
        return super.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        id = nbt.getInt("id");
        super.deserializeNBT(nbt);
    }

    public UUID getOwnerUUID() {
        return ownerUUID;
    }

    public void demat(){
        DMPackets.INSTANCE.sendTo(new TardisDematPacket(getId(), getCurrentPosition(), getTargetPosition()), Minecraft.getInstance().player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
    }

    public void remat(){
        //setTargetPosition(new BlockPos(id * 1000, 0, id * 1000));
        if(getTargetPosition() == null){
            setTargetPosition(new BlockPos(16, 104, 10));
        }

        DMPackets.INSTANCE.sendTo(new TardisRematPacket(getId(), getOwnerUUID(), getTargetPosition()), Minecraft.getInstance().player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);

//        Level level1 = Minecraft.getInstance().level;
//        ChunkAccess chunk = level1.getChunk(this.getCurrentLocation());
//        if(level1 instanceof ServerLevel serverLevel){
//            serverLevel.setChunkForced(chunk.getPos().x, chunk.getPos().z, true);
//        }
//        level1.getChunkSource().updateChunkForced(chunk.getPos(), true);
//        DocMod.LOGGER.warn("JE SUIS EN TRAIN DE REMAT");
//        setTargetDimension(Level.OVERWORLD);
//
////        if(level instanceof ServerLevel serverLevel){
////            level.getServer().getLevel(getTargetDimension()).setBlockEntity(tardisTileEntity);
////        }
//        Level mLevel = Minecraft.getInstance().level;
//        ChunkAccess targetChunk = mLevel.getChunk(this.getTargetPosition());
//        if(mLevel instanceof ServerLevel serverLevel){
//            serverLevel.setChunkForced(targetChunk.getPos().x, targetChunk.getPos().z, true);
//           // serverLevel.getServer().getLevel(getTargetDimension()).setBlockEntity(tardisTileEntity);
//            setCurrentDimension(getTargetDimension());
//        }
//        level1.getChunkSource().updateChunkForced(chunk.getPos(), true);
//        //level.removeBlockEntity(getBlockPos());
//        isDemating = false;
//        //level.setBlockEntity(this);
//        //Minecraft.getInstance().level.setBlockEntity(this);
//        isAlreadyDemat = false;
    }

    public BlockPos getCurrentLocation(){
        return getBlockPos();
    }

    public void setOwnerUUID(UUID ownerUUID) {
        this.ownerUUID = ownerUUID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTargetDimension(ResourceKey<Level> targetDimension) {
        this.targetDimension = targetDimension;
    }

    public ResourceKey<Level> getTargetDimension() {
        return targetDimension;
    }

    public void setTargetPosition(BlockPos targetPosition) {
        this.targetPosition = targetPosition;
        this.visible = false;
    }

    public void setCurrentPosition(BlockPos currentPosition) {
        this.currentPosition = currentPosition;
    }

    public BlockPos getCurrentPosition() {
        return getBlockPos();
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public BlockPos getTargetPosition() {
        return targetPosition;
    }

    public ResourceKey<Level> getCurrentDimension() {
        return currentDimension;
    }

    public void setCurrentDimension(ResourceKey<Level> currentDimension) {
        this.currentDimension = currentDimension;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public String getTargetDimensionString(){
        if(getTargetDimension().location().getPath().equals("overworld")){
            return "Overworld";
        } else if(getTargetDimension().location().getPath().equals("the_end")){
            return "The End";
        } else if(getTargetDimension().location().getPath().equals("the_nether")){
            return "The Nether";
        } else if(getTargetDimension().location().getPath().equals("moon")){
            return "Moon";
        } else if(getTargetDimension().location().getPath().equals("space")){
            return "Space";
        }
        return getTargetDimension().location().getPath();
    }

    public boolean isOn() {
        return isOn;
    }

    public String getOnOffString(){
        if(isOn){
            Component.literal("OFF");
        } else {
            Component.literal("ON");
        }
        return "";
    }

    public String getOwnerName(){
        return PlayerUtils.getUserNameByUUID(getOwnerUUID());
    }
}
