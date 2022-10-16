package be.ninedocteur.docmod.common.block;

import be.ninedocteur.docmod.client.gui.screens.DMComputerScreen;
import be.ninedocteur.docmod.common.tileentity.ComputerTileEntity;
import be.ninedocteur.docmod.common.tileentity.MonitorTileEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class MonitorBlock extends BaseEntityBlock {


    public MonitorBlock(Properties p_49224_) {
        super(p_49224_);
    }


    //    @Override
//    public InteractionResult use(BlockState p_60503_, Level p_60504_, BlockPos p_60505_, Player p_60506_, InteractionHand p_60507_, BlockHitResult p_60508_) {
//        BlockEntity blockentity = p_60504_.getBlockEntity(p_60505_);
//        if(Screen.hasShiftDown()){
//            //TURN ON/OFF THE PC
//        } else{
//            // final MenuProvider container = new SimpleMenuProvider(ComputerHarwareMenu, Component.literal("Computer Harware"));
//            //NetworkHooks.openScreen((ServerPlayer) p_60506_, container, p_60505_);
//            if(p_60504_.isClientSide && blockentity instanceof ComputerTileEntity computerTileEntity) {
//                Minecraft.getInstance().setScreen(new DMComputerScreen(computerTileEntity));
//            }
//        }
//        return super.use(p_60503_, p_60504_, p_60505_, p_60506_, p_60507_, p_60508_);
//    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new MonitorTileEntity(pPos, pState);
    }

    public BlockEntity create(BlockPos p_155268_, BlockState p_155269_) {
        return new MonitorTileEntity(p_155268_, p_155269_);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
        if(blockEntity instanceof MonitorTileEntity monitorTileEntity){
            BlockPos computerTileEntityPos = BlockPos.of(monitorTileEntity.compoundTag.getLong("computerPos"));
            BlockEntity computerTileEntity = pLevel.getBlockEntity(computerTileEntityPos);
            if(monitorTileEntity.isLinkedToComputer){
                Minecraft.getInstance().setScreen(new DMComputerScreen((ComputerTileEntity) computerTileEntity));
            } else {
                pPlayer.sendSystemMessage(Component.literal(ChatFormatting.RED + "This monitor is not linked to a computer."));
            }
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }
}
