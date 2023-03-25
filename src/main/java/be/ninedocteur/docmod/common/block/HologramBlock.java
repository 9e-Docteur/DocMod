package be.ninedocteur.docmod.common.block;

import be.ninedocteur.docmod.client.gui.screens.DMHologramBlock;
import be.ninedocteur.docmod.common.tileentity.HologramTileEntity;
import net.minecraft.client.Minecraft;
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
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class HologramBlock extends BaseEntityBlock {
    public HologramBlock(Properties p_49224_) {
        super(p_49224_);
    }

    @Override
    public InteractionResult use(BlockState p_60503_, Level p_60504_, BlockPos p_60505_, Player p_60506_, InteractionHand p_60507_, BlockHitResult p_60508_) {
        HologramTileEntity hologramTileEntity = (HologramTileEntity) p_60504_.getBlockEntity(p_60505_);
        Minecraft.getInstance().setScreen(new DMHologramBlock(hologramTileEntity));
        return super.use(p_60503_, p_60504_, p_60505_, p_60506_, p_60507_, p_60508_);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return new HologramTileEntity(p_153215_, p_153216_);
    }
}
