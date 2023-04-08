package be.ninedocteur.docmod.common.tileentity;

import be.ninedocteur.docmod.client.gui.menu.ComputerHarwareMenu;
import be.ninedocteur.docmod.common.computer.terminal.command.BaseCommand;
import be.ninedocteur.docmod.common.item.computer.parts.DiskType;
import be.ninedocteur.docmod.common.item.computer.parts.Disks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ComputerTileEntity /*extends RandomizableContainerBlockEntity /*BlockEntity*/ {
    // public int RAM_INSTALLED;

//    public List<String> TERMINAL_HISTORY = new ArrayList<>();
//    public long monitorPos;
//    public CompoundTag compoundTag = new CompoundTag();
//    public boolean isLinkedToMonitor;
//    private List<BaseCommand> installedApps = new ArrayList();
//    private HashMap<Disks, DiskType> INSTALLED_DISK = new HashMap<>();
//
//
////    public ComputerTileEntity(BlockPos p_155229_, BlockState p_155230_) {
////        super(DMTileEntity.COMPUTER_TILE_ENTITY.get(), p_155229_, p_155230_);
////    }
//
//    public ComputerTileEntity(BlockPos p_155229_, BlockState p_155230_) {
//        super(null, p_155229_, p_155230_);
//    }
//
//    public void installApp(BaseCommand app){
//        installedApps.add(app);
//    }
//
//    public boolean isAppInstalled(BaseCommand app){
//        return installedApps.contains(app);
//    }
//
//    public void setInstalledDisk(HashMap<Disks, DiskType> INSTALLED_DISK) {
//        this.INSTALLED_DISK = INSTALLED_DISK;
//    }
//
//    public HashMap<Disks, DiskType> getInstalledDisk() {
//        return INSTALLED_DISK;
//    }
//
//    /*
//    //WRITE
//    @Override
//    public CompoundTag serializeNBT() {
//        CompoundTag compoundTag = new CompoundTag();
//        compoundTag.putInt("RAM_INSTALLED", RAM_INSTALLED);
//
//        return super.serializeNBT();
//    }
//
//    //READ
//    @Override
//    public void deserializeNBT(CompoundTag nbt) {
//        this.RAM_INSTALLED = nbt.getInt("RAM_INSTALLED");
//        super.deserializeNBT(nbt);
//    }
//
//
//    @Nullable
//    @Override
//    public Packet<ClientGamePacketListener> getUpdatePacket() {
//        CompoundTag compoundTag = new CompoundTag();
//        compoundTag.putInt("RAM_INSTALLED", RAM_INSTALLED);
//
//
//        return getUpdatePacket();
//    }
//
//    @Override
//    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
//        CompoundTag compoundTag = new CompoundTag();
//        if(compoundTag.contains("RAM_INSTALLED")){
//            RAM_INSTALLED = compoundTag.getInt("RAM_INSTALLED");
//            this.getPersistentData().putInt("RAM_INSTALLED", RAM_INSTALLED);
//        }
//        super.onDataPacket(net, pkt);
//    }
//    */
//
//    public static final int CONTAINER_SIZE = 9;
//    private NonNullList<ItemStack> items = NonNullList.withSize(9, ItemStack.EMPTY);
///*
//    public ComputerTileEntity(BlockPos p_155493_, BlockState p_155494_) {
//        this(DMTileEntity.COMPUTER_TILE_ENTITY.get(), p_155493_, p_155494_);
//    }
//    */
//
//    public int getContainerSize() {
//        return 9;
//    }
//
//    public int getRandomSlot(RandomSource p_222762_) {
//        this.unpackLootTable((Player)null);
//        int i = -1;
//        int j = 1;
//
//        for(int k = 0; k < this.items.size(); ++k) {
//            if (!this.items.get(k).isEmpty() && p_222762_.nextInt(j++) == 0) {
//                i = k;
//            }
//        }
//
//        return i;
//    }
//    public int addItem(ItemStack p_59238_) {
//        for(int i = 0; i < this.items.size(); ++i) {
//            if (this.items.get(i).isEmpty()) {
//                this.setItem(i, p_59238_);
//                return i;
//            }
//        }
//
//        return -1;
//    }
//
//    protected Component getDefaultName() {
//        return Component.translatable("gui.docmod.computer_hardware");
//    }
//
//    public void load(CompoundTag p_155496_) {
//        super.load(p_155496_);
//        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
//        if (!this.tryLoadLootTable(p_155496_)) {
//            ContainerHelper.loadAllItems(p_155496_, this.items);
//        }
//
//    }
//
//    protected void saveAdditional(CompoundTag p_187498_) {
//        super.saveAdditional(p_187498_);
//        if (!this.trySaveLootTable(p_187498_)) {
//            ContainerHelper.saveAllItems(p_187498_, this.items);
//        }
//
//    }
//
//    public NonNullList<ItemStack> getItems() {
//        return this.items;
//    }
//
//    protected void setItems(NonNullList<ItemStack> p_59243_) {
//        this.items = p_59243_;
//    }
//
////    @OnlyIn(Dist.CLIENT)
////    protected AbstractContainerMenu createMenu(int p_59235_, Inventory p_59236_) {
////        return new ComputerHarwareMenu(p_59235_, p_59236_, this);
////    }
//
//    @Override
//    public CompoundTag serializeNBT() {
//        super.serializeNBT();
//        //compoundTag.putLong("monitorPos", monitorPos.asLong());
//        return compoundTag;
//    }
//
//    @Override
//    public void deserializeNBT(CompoundTag nbt) {
//        super.deserializeNBT(nbt);
//    }
}
