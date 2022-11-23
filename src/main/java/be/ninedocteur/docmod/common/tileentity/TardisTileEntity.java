package be.ninedocteur.docmod.common.tileentity;

import be.ninedocteur.docmod.common.init.DMTileEntity;
import be.ninedocteur.docmod.utils.PlayerUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.TickingBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

import java.util.*;

public class TardisTileEntity extends BlockEntity {

    private int id;
    public static HashMap<Integer, TardisTileEntity> tardisMap = new HashMap<>();

    public boolean isLocked = false;
    public boolean isDemating = false;
    public boolean isAlreadyDemat = false;
    public boolean isOn = true;
    public BlockPos targetPosition;
    public ResourceKey<Level> targetDimension;
    public UUID ownerUUID;
    public float alpha = 1f;

    public TardisTileEntity(BlockPos pos, BlockState state) {
        super(DMTileEntity.Tardis.get(), pos, state);
        this.id = -1;
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

    public UUID getOwnerUUID() {
        return ownerUUID;
    }



    public void demat(){
        isDemating = true;
        //level.destroyBlock(getBlockPos(), false);
        level.removeBlockEntity(getBlockPos());
        isAlreadyDemat = true;
        isDemating = false;
        this.setChanged();
        if(!level.isClientSide){
            this.level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 2);
        }
    }

    public void remat(){
        //setTargetPosition(new BlockPos(id * 1000, 0, id * 1000));
        if(getTargetPosition() == null){
            setTargetPosition(new BlockPos(16, 104, 10));
        }
        setTargetDimension(Level.OVERWORLD);
        TardisTileEntity tardisTileEntity = DMTileEntity.Tardis.get().create(getTargetPosition(), this.getBlockState());
        tardisTileEntity.setId(id);
        tardisTileEntity.setOwnerUUID(ownerUUID);
        tardisTileEntity.setTargetPosition(targetPosition);
        tardisTileEntity.setTargetDimension(Level.OVERWORLD);
        tardisMap.put(tardisTileEntity.id, tardisTileEntity);
//        if(level instanceof ServerLevel serverLevel){
//            level.getServer().getLevel(getTargetDimension()).setBlockEntity(tardisTileEntity);
//        }
        level.setBlockEntity(tardisTileEntity);
        //level.removeBlockEntity(getBlockPos());
        isDemating = false;
        //level.setBlockEntity(this);
        //Minecraft.getInstance().level.setBlockEntity(this);
        isAlreadyDemat = false;
        this.setChanged();
        if(!level.isClientSide){
            this.level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 2);
        }
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
    }

    public BlockPos getTargetPosition() {
        return targetPosition;
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

    public void tick() {
        if (isDemating) {
            alpha -= 0.01F;
            if (alpha <= 0) {
                this.isDemating = false;
                this.level.setBlock(this.getBlockPos(), Blocks.AIR.defaultBlockState(), 1);
                this.level.setBlock(this.getBlockPos().below(), Blocks.AIR.defaultBlockState(), 1);
            }
        }
    }

//    @Override
//    public BlockPos getPos() {
//        return getBlockPos();
//    }
//
//    @Override
//    public void deserializeNBT(CompoundTag nbt) {
//        super.deserializeNBT(nbt);
//    }
//
//    @Override
//    public CompoundTag serializeNBT() {
//        return super.serializeNBT();
//    }
//
//    @Override
//    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
//        super.onDataPacket(net, pkt);
//    }
//
//    @Override
//    public void handleUpdateTag(CompoundTag tag) {
//        super.handleUpdateTag(tag);
//    }
//
//    @Override
//    public void onLoad() {
//        super.onLoad();
//    }
//
//    @Override
//    public AABB getRenderBoundingBox() {
//        return super.getRenderBoundingBox();
//    }
//
//    @Override
//    public void requestModelDataUpdate() {
//        super.requestModelDataUpdate();
//    }
//
//    @Override
//    public @NotNull ModelData getModelData() {
//        return super.getModelData();
//    }
//
//    @Override
//    public boolean hasCustomOutlineRendering(Player player) {
//        return super.hasCustomOutlineRendering(player);
//    }
//
//    @Override
//    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
//        return super.getCapability(cap);
//    }
//
//    @Override
//    public BlockEntityType<?> getType() {
//        return null;
//    }
}
