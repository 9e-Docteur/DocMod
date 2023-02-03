package fr.ninedocteur.docmod.common.block.entity;

import fr.ninedocteur.docmod.common.init.DMItems;
import fr.ninedocteur.docmod.common.init.DMTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class ZurbTeleporterEntity extends BlockEntity implements ImplementedInventory{

    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(1, ItemStack.EMPTY);

    public ZurbTeleporterEntity(BlockPos pos, BlockState state) {
        super(DMTileEntities.ZurbTeleporterTile , pos, state);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, items);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, items);
        super.writeNbt(nbt);
    }
    @Override
    public void setStack(int slot, ItemStack stack) {
        if (!(stack.getItem() == Items.COPPER_INGOT || stack.getItem() == DMItems.AMETHYST)){
            items.set(slot, stack);
        }
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    public void tick(){}
}
