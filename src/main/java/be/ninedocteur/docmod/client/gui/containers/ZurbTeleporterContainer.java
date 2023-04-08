package be.ninedocteur.docmod.client.gui.containers;

import be.ninedocteur.docmod.common.tileentity.ZurbTeleporterTile;
import net.minecraft.core.BlockPos;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class ZurbTeleporterContainer /*extends AbstractContainerMenu*/ {
//    private static final int HOTBAR_SLOT_COUNT = 9;
//    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
//    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
//    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
//    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
//    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
//    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
//    private static final int TE_INVENTORY_SLOT_COUNT = 1;
//    private final Player player;
//    private final IItemHandler playerInv;
//
//    //Client
//    public ZurbTeleporterContainer(int pContainerId, Inventory playerInv){
//        this(pContainerId, new ItemStackHandler(TE_INVENTORY_SLOT_COUNT), BlockPos.ZERO, playerInv, playerInv.player, new SimpleContainerData(TE_INVENTORY_SLOT_COUNT));
//    }
//
//    //Server
//    public ZurbTeleporterContainer(int pContainerId, IItemHandler handler, BlockPos pos, Inventory playerInv, Player player, ContainerData data) {
//        super(DMContainers.ZURBTELEPORTERCONTAINER.get(), pContainerId);
//        this.player = player;
//        this.playerInv = new InvWrapper(playerInv);
//        layoutPlayerInventorySlots(8, 86);
//        addSlot(new SlotItemHandler(handler, 0, 176/2-8, 37));
//    }
//
//    @Override
//    public boolean stillValid(Player player) {
//        return true;
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
//    public static MenuConstructor getServerContainer(ZurbTeleporterTile be, BlockPos pos) {
//        return (id, playerInv, player) -> new ZurbTeleporterContainer(id, new ItemStackHandler(TE_INVENTORY_SLOT_COUNT), pos, playerInv, player, new SimpleContainerData(TE_INVENTORY_SLOT_COUNT));
//    }
//
//    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
//        addSlotBox(playerInv, 9, leftCol, topRow, 9, 18, 3, 18);
//
//        topRow += 58;
//        addSlotRange(playerInv, 0, leftCol, topRow, 9, 18);
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
//        sourceSlot.onTake(player, sourceStack);
//        return copyOfSourceStack;
//    }
}
