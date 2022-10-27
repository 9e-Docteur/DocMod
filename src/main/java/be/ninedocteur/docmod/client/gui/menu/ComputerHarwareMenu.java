package be.ninedocteur.docmod.client.gui.menu;

import be.ninedocteur.docmod.common.init.DMItems;
import be.ninedocteur.docmod.common.init.DMMenu;
import be.ninedocteur.docmod.common.item.computer.parts.*;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ComputerHarwareMenu extends AbstractContainerMenu {
    private static final int SLOT_COUNT = 9;
    private static final int INV_SLOT_START = 9;
    private static final int INV_SLOT_END = 36;
    private static final int USE_ROW_SLOT_START = 36;
    private static final int USE_ROW_SLOT_END = 45;
    private final Container container;
    static ComputerHarwareMenu instance;

    public ComputerHarwareMenu(int p_39433_, Inventory p_39434_) {
        this(p_39433_, p_39434_, new SimpleContainer(9));
        instance = this;
    }

    public ComputerHarwareMenu(int p_39436_, Inventory p_39437_, Container p_39438_) {
        super(DMMenu.COMPUTER.get(), p_39436_); //DMMenu.COMPUTER.get() = POINT TO THE GUI TEXTURE OF THE COMPUTER
        checkContainerSize(p_39438_, 9); //UPDATE THIS WHEN REMOVE/ADDING SLOTS
        this.container = p_39438_;
        p_39438_.startOpen(p_39437_.player);

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                this.addSlot(new Slot(p_39438_, j + i * 3, 62 + j * 18, 17 + i * 18));
            }
        }

        for(int k = 0; k < 3; ++k) {
            for(int i1 = 0; i1 < 9; ++i1) {
                this.addSlot(new Slot(p_39437_, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
            }
        }

        for(int l = 0; l < 9; ++l) {
            this.addSlot(new Slot(p_39437_, l, 8 + l * 18, 142));
        }

//        getSlot(0).mayPlace(new ItemStack(DMItems.RAM.get()));
//        getSlot(1).mayPlace(new ItemStack(DMItems.CPU.get()));
//        getSlot(2).mayPlace(new ItemStack(DMItems.MOTHERBOARD.get()));
//        getSlot(3).mayPlace(new ItemStack(DMItems.GPU.get()));
//        getSlot(4).mayPlace(new ItemStack(DMItems.DISK.get()));
//        getSlot(5).mayPlace(new ItemStack(DMItems.DISK.get()));
//        getSlot(6).mayPlace(new ItemStack(DMItems.VENTIRAD.get()));
        //getSlot(7).mayPlace(new ItemStack(DMItems.RAM.get()));
        //getSlot(8).mayPlace(new ItemStack(DMItems.RAM.get()));

    }

    public boolean stillValid(Player p_39440_) {
        return this.container.stillValid(p_39440_);
    }

    public ItemStack quickMoveStack(Player p_39444_, int p_39445_) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(p_39445_);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (p_39445_ < 9) {
                if (!this.moveItemStackTo(itemstack1, 9, 45, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, 9, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(p_39444_, itemstack1);
        }

        return itemstack;
    }

    public void removed(Player p_39442_) {
        super.removed(p_39442_);
        this.container.stopOpen(p_39442_);
    }

    public static ComputerHarwareMenu getInstance() {
        return instance;
    }
}
