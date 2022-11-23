package be.ninedocteur.docmod.common.tileentity;

import be.ninedocteur.docmod.common.init.DMTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ItemShowerTile extends BlockEntity {
    public String itemName;
    public ItemStack itemIn;

    public ItemShowerTile(BlockPos pos, BlockState state) {
        super(DMTileEntity.item_shower.get(), pos, state);
        CompoundTag compoundTag = new CompoundTag();
        itemIn = ItemStack.of((CompoundTag) compoundTag.get("item_in"));
        if(itemIn == null){
            itemIn = new ItemStack(Blocks.AIR);
        }
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putString("item_in", String.valueOf(itemIn.getItem().getName(itemIn)));
        return super.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        super.deserializeNBT(nbt);
    }
}
