package be.ninedocteur.docmod.client.gui.menu;

import be.ninedocteur.docmod.client.gui.containers.DMContainers;
import be.ninedocteur.docmod.client.gui.containers.ZurbTeleporterContainer;
import be.ninedocteur.docmod.common.init.DMBlocks;
import be.ninedocteur.docmod.common.tileentity.SafeChestTileEntity;
import be.ninedocteur.docmod.common.tileentity.ZurbTeleporterTile;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

@OnlyIn(Dist.CLIENT)
public class SafeChestMenu /*extends AbstractContainerMenu*/ {
//    public final ContainerData containerData;
//    private final ContainerLevelAccess containerAccess;
//    private static final int HOTBAR_SLOT_COUNT = 9;
//    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
//    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
//    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
//    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
//    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
//    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
//    private static final int TE_INVENTORY_SLOT_COUNT = 27;
//    private BlockPos pos;
//
//    public SafeChestMenu(int p_40188_, Inventory p_40189_) {
//        this(p_40188_, new ItemStackHandler(27), BlockPos.ZERO, p_40189_, new SimpleContainerData(27));
//    }
//
//    public SafeChestMenu(int pContainerId, IItemHandler handler, BlockPos pos, Inventory playerInv, ContainerData data) {
//        super(DMContainers.SAFE_CHEST_CONTAINER.get(), pContainerId);
//        this.pos = pos;
//        SafeChestTileEntity safeChestTileEntity = (SafeChestTileEntity) Minecraft.getInstance().level.getBlockEntity(pos);
//        safeChestTileEntity.setPos(pos);
//        containerAccess = ContainerLevelAccess.create(playerInv.player.level, pos);
//        containerData = data;
//        int index = 0;
//        layoutPlayerInventorySlots(new InvWrapper(playerInv), 8, 86);
//        for(int row = 0; row < 3; row++){ // CHEST
//            for(int colons = 0; colons < 9; colons++){
//                addSlot(new SlotItemHandler(handler, index, 8 + colons * 18,  row * 18));
//                index++;
//            }
//        }
//        addDataSlots(data);
//    }
//
//    public static MenuConstructor getServerContainer(SafeChestTileEntity te, BlockPos pos) {
//        return (id, playerInv, player) -> new SafeChestMenu(id, te.inventory, pos, playerInv, new SimpleContainerData(27));
//    }
//
//    private void layoutPlayerInventorySlots(IItemHandler playerInv, int leftCol, int topRow) {
//        addSlotBox(playerInv, 9, leftCol, topRow, 9, 18, 3, 18);
//
//        topRow += 58;
//        addSlotRange(playerInv, 0, leftCol, topRow, 9, 18);
//    }
//
//    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
//        for (int i = 0; i < amount; i++) {
//            addSlot(new SlotItemHandler(handler, index, x, y));
//            x += dx;
//            index++;
//        }
//
//        return index;
//    }
//
//    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
//        for (int j = 0; j < verAmount; j++) {
//            index = addSlotRange(handler, index, x, y, horAmount, dx);
//            y += dy;
//        }
//
//        return index;
//    }
//
//    @Override
//    public boolean stillValid(Player p_38874_) {
//        return stillValid(containerAccess, p_38874_, DMBlocks.SAFE_CHEST.get());
//    }
//
//    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
//    @Override
//    public ItemStack quickMoveStack(Player p_38941_, int index) {
//        Slot sourceSlot = slots.get(index);
//        if (sourceSlot == null || !sourceSlot.getItem().isEmpty()) return ItemStack.EMPTY;  //EMPTY_ITEM
//        ItemStack sourceStack = sourceSlot.getItem();
//        ItemStack copyOfSourceStack = sourceStack.copy();
//
//        // Check if the slot clicked is one of the vanilla container slots
//        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
//            // This is a vanilla container slot so merge the stack into the tile inventory
//            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
//                    + TE_INVENTORY_SLOT_COUNT, false)) {
//                return ItemStack.EMPTY;  // EMPTY_ITEM
//            }
//        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
//            // This is a TE slot so merge the stack into the players inventory
//            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
//                return ItemStack.EMPTY;
//            }
//        } else {
//            System.out.println("Invalid slotIndex:" + index);
//            return ItemStack.EMPTY;
//        }
//        // If stack size == 0 (the entire stack was moved) set slot contents to null
//        if (sourceStack.getCount() == 0) {
//            sourceSlot.set(ItemStack.EMPTY);
//        } else {
//            sourceSlot.setChanged();
//        }
//        sourceSlot.onTake(p_38941_, sourceStack);
//        return copyOfSourceStack;
//    }
//
//    public BlockPos getPos() {
//        return pos;
//    }
}