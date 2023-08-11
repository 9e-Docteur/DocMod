package be.ninedocteur.docmod.common.tileentity;

import be.ninedocteur.docmod.common.DMCapabilities;
import be.ninedocteur.docmod.common.ITardis;
import be.ninedocteur.docmod.common.init.DMTileEntity;
import be.ninedocteur.docmod.common.world.tardis.TardisWorldManager;
import be.ninedocteur.docmod.utils.PlayerUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.*;

public class TardisTileEntity extends BlockEntity implements ITardis {

    private int id;
    public static HashMap<Integer, TardisTileEntity> tardisMap = new HashMap<>();

    public State state = State.STANDING;
    public boolean isLocked = false;
    public boolean isOn = true;
    public BlockPos targetPosition;
    public BlockPos currentPosition;
    public ResourceKey<Level> targetDimension;
    public ResourceKey<Level> currentDimension;
    public UUID ownerUUID;
    private UUID tardisID;

    public TardisTileEntity(BlockPos pos, BlockState state) {
        super(DMTileEntity.Tardis.get(), pos, state);
        TardisWorldManager.addTardis(this);
        tardisID = null;
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
        this.tardisID = UUID.randomUUID();
        tardisMap.put(id, this);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putUUID("owner", ownerUUID);
        tag.putUUID("tardisId", tardisID);
        tag.putString("state", state.toString());
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        super.deserializeNBT(nbt);
        ownerUUID = nbt.getUUID("owner");
        tardisID = nbt.getUUID("tardisId");
        state = State.valueOf(nbt.getString("state"));
    }

    public UUID getOwnerUUID() {
        return ownerUUID;
    }

    @Override
    public void demat(){
        level.getCapability(DMCapabilities.TARDIS).ifPresent((cap) -> {
            setState(State.DEMATTING);
            if(cap.getTargetPosition() == null){
                setTargetPosition(getCurrentPosition());
            }
            if(cap.getCurrentDimension() == null) {
                setTargetDimension(getCurrentDimension());
            }
            setState(State.FLY);
            cap.getLevel().removeBlock(getBlockPos(), false);
        });
    }

    @Override
    public void tick() {

    }

    @Override
    public void remat(){
        level.getCapability(DMCapabilities.TARDIS).ifPresent((cap) -> {
            setState(State.REMATTING);
            if(cap.getTargetPosition() == null){
                setTargetPosition(getCurrentPosition());
            }
            if(cap.getCurrentDimension() == null) {
                setTargetDimension(getCurrentDimension());
            }
            cap.getLevel().setBlock(getTargetPosition(), getBlockState(), 1);
        });
    }

    public BlockPos getCurrentLocation(){
        return getBlockPos();
    }

    public UUID getTardisID() {
        return tardisID;
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

    @Override
    public ResourceKey<Level> getTargetDimension() {
        return targetDimension;
    }



    @Override
    public State getCurrentState() {
        return state;
    }

    public void setTargetPosition(BlockPos targetPosition) {
        this.targetPosition = targetPosition;
    }

    public void setCurrentPosition(BlockPos currentPosition) {
        this.currentPosition = currentPosition;
    }

    @Override
    public BlockPos getCurrentPosition() {
        return getBlockPos();
    }

    @Override
    public BlockPos getTargetPosition() {
        return targetPosition;
    }

    @Override
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

    public void setState(State state){
        this.state = state;
    }

    public enum State {
        DEMATTING,
        REMATTING,
        FLY,
        STANDING
    }
}
