package be.ninedocteur.docmod.common.block;

import be.ninedocteur.docmod.common.tileentity.EnderPadTile;
import be.ninedocteur.docmod.common.tileentity.HartnellTileEntity;
import be.ninedocteur.docmod.common.tileentity.ToyotaRotorTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class EnderPadBlock extends BaseEntityBlock {
    public EnderPadBlock(Properties p_49224_) {
        super(p_49224_);
    }
    
    @Override
    public void stepOn(Level p_152431_, BlockPos p_152432_, BlockState p_152433_, Entity p_152434_) {
    	EnderPadTile tile = (EnderPadTile) p_152431_.getBlockEntity(p_152432_);
    	if(p_152434_.isCrouching()) {
    		if(tile.isLinked()) {
    			p_152434_.teleportTo(tile.getLinkedPos().getX(), tile.getLinkedPos().getY(), tile.getLinkedPos().getZ());
    		} else {
    			p_152434_.sendSystemMessage(Component.literal("§cThis pad is not linked to a destination pos!"));
    		}
    	}
    	super.stepOn(p_152431_, p_152432_, p_152433_, p_152434_);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new EnderPadTile(pPos, pState);
    }

    public BlockEntity create(BlockPos p_155268_, BlockState p_155269_) {
        return new EnderPadTile(p_155268_, p_155269_);
    }
}
