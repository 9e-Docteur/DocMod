package be.ninedocteur.docmod.common.item.computer;

import be.ninedocteur.docmod.common.block.ComputerBlock;
import be.ninedocteur.docmod.common.block.MonitorBlock;
import be.ninedocteur.docmod.common.init.DMBlocks;
import be.ninedocteur.docmod.common.tileentity.ComputerTileEntity;
import be.ninedocteur.docmod.common.tileentity.MonitorTileEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class HDMICable extends Item {
    public boolean isEnchanted;
    public boolean isMonitorSelected;
    private MonitorTileEntity monitorTileEntity;
    public HDMICable(Properties p_41383_) {
        super(p_41383_);
        isEnchanted = false;
        isMonitorSelected = false;
    }


    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        ItemStack itemInHand = context.getItemInHand();
        BlockPos blockPos = context.getClickedPos();
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        BlockState state = context.getLevel().getBlockState(blockPos);
        if(!level.isClientSide){
            if(state.getBlock() instanceof MonitorBlock monitorBlock){
                monitorTileEntity = (MonitorTileEntity) blockEntity;
                if(!monitorTileEntity.isLinkedToComputer)
                isEnchanted = true;
                isMonitorSelected = true;
            }
            if(state.getBlock() instanceof ComputerBlock){
                if(isMonitorSelected){
                    isEnchanted = false;
                    monitorTileEntity.compoundTag.putLong("monitorPos", monitorTileEntity.computerPos = blockPos.asLong());
                    monitorTileEntity.isLinkedToComputer = true;
                }
            }
            if(!isMonitorSelected){
                player.sendSystemMessage(Component.literal(ChatFormatting.RED + "You need to select the monitor first!"));
            }
        }
        return super.onItemUseFirst(stack, context);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        return super.use(p_41432_, p_41433_, p_41434_);
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return isEnchanted;
    }
}
