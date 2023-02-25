package be.ninedocteur.docmod.common.block;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.gui.containers.DMContainers;
import be.ninedocteur.docmod.client.gui.menu.SafeChestMenu;
import be.ninedocteur.docmod.common.tileentity.SafeChestTileEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class SafeChestBlock extends Block implements EntityBlock {

    public SafeChestBlock(Properties p_49224_) {
        super(p_49224_);
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new SafeChestTileEntity(pPos, pState);
    }

    public BlockEntity create(BlockPos p_155268_, BlockState p_155269_) {
        return new SafeChestTileEntity(p_155268_, p_155269_);
    }

    @Override
    public void onPlace(BlockState p_60566_, Level p_60567_, BlockPos p_60568_, BlockState p_60569_, boolean p_60570_) {
        super.onPlace(p_60566_, p_60567_, p_60568_, p_60569_, p_60570_);
        SafeChestTileEntity safeChestTileEntity = (SafeChestTileEntity) p_60567_.getBlockEntity(p_60568_);
        Player player = Minecraft.getInstance().player;
        safeChestTileEntity.setOwner(player.getUUID());
        DocMod.LOGGER.info("New Safe Chest placed at " + p_60568_ + " with owner " + safeChestTileEntity.getOwner());
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        SafeChestTileEntity safeChestTileEntity = (SafeChestTileEntity) level.getBlockEntity(pos);
        if(!level.isClientSide){
            if(safeChestTileEntity.isOwner(player.getUUID()) || safeChestTileEntity.isPersonAllowedToUseChest(player.getUUID())){
            final MenuProvider container = new SimpleMenuProvider(SafeChestMenu.getServerContainer(safeChestTileEntity, pos), safeChestTileEntity.getDefaultName());
            NetworkHooks.openScreen((ServerPlayer) player, container, pos);
            } else {
                player.sendSystemMessage(Component.literal(ChatFormatting.RED + "You can't open this safe chest! You are not allowed! This chest belong to " + ChatFormatting.YELLOW + safeChestTileEntity.getNameByUUID(safeChestTileEntity.getOwner())));
            }
        }
        return InteractionResult.SUCCESS;
    }
}
