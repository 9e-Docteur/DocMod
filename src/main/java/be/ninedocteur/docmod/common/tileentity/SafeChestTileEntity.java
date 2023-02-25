package be.ninedocteur.docmod.common.tileentity;

import be.ninedocteur.docmod.client.gui.menu.SafeChestMenu;
import be.ninedocteur.docmod.common.init.DMTileEntity;
import be.ninedocteur.docmod.utils.PlayerUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.ShulkerBoxMenu;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.checkerframework.checker.units.qual.C;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

public class SafeChestTileEntity extends InventoryTileEntity {
    private UUID owner;
    private List<UUID> authorisedPeople = new ArrayList();
    private BlockPos pos;

    public SafeChestTileEntity(BlockPos pos, BlockState state) {
        super(DMTileEntity.SAFECHEST.get(), pos, state, 27);
    }

    public void addAuthorisedPeople(UUID uuidOfTheAutorisedPerson){
        authorisedPeople.add(uuidOfTheAutorisedPerson);
    }

    public void removeAuthorisedPeople(UUID uuidOfTheAutorisedPerson){
        authorisedPeople.remove(uuidOfTheAutorisedPerson);
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public boolean isOwner(UUID uuid){
        if(uuid == owner){
            return true;
        } else {
            return false;
        }
    }

    public UUID getOwner() {
        return owner;
    }

    public List<UUID> getAuthorisedPeople() {
        return authorisedPeople;
    }

    public String getNameByUUID(UUID uuid){
        return PlayerUtils.getUserNameByUUID(uuid);
    }
    public UUID getUUIDByName(String name){
        return PlayerUtils.getUserUUID(name);
    }

    public boolean isPersonAllowedToUseChest(UUID uuid){
        if(authorisedPeople.contains(uuid)){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putUUID("owner", getOwner());
        saveAdditional(tag);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        owner = nbt.getUUID("owner");
        load(nbt);
        super.deserializeNBT(nbt);
    }


    public Component getDefaultName() {
        return Component.translatable("Safe Chest");
    }

    public void load(CompoundTag p_155678_) {
        super.load(p_155678_);
    }

    protected AbstractContainerMenu createMenu(int p_59660_, Inventory p_59661_) {
        return new SafeChestMenu(p_59660_, p_59661_);
    }

    public BlockPos getPos() {
        return pos;
    }

    public void setPos(BlockPos pos) {
        this.pos = pos;
    }
}
