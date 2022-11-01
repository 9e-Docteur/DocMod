package be.ninedocteur.docmod.common.tileentity;

import be.ninedocteur.docmod.common.init.DMTileEntity;
import be.ninedocteur.docmod.common.world.dimension.DMDimension;
import be.ninedocteur.docmod.utils.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

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
}
