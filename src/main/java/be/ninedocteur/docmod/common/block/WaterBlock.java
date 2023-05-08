package be.ninedocteur.docmod.common.block;

import be.ninedocteur.docmod.common.tileentity.DMWaterCapableBlockEntity;
import be.ninedocteur.docmod.common.tileentity.GutterTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.ticks.TickPriority;

public abstract class WaterBlock extends BaseEntityBlock {
	
	public WaterBlock(Properties p_49795_) {
		super(p_49795_);
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void tick(BlockState p_222945_, ServerLevel p_222946_, BlockPos p_222947_, RandomSource p_222948_) {
		// TODO Auto-generated method stub
		super.tick(p_222945_, p_222946_, p_222947_, p_222948_);
		p_222946_.scheduleTick(p_222947_, this, 1, TickPriority.EXTREMELY_HIGH);
		DMWaterCapableBlockEntity tile = (DMWaterCapableBlockEntity) p_222946_.getBlockEntity(p_222947_);
		tile.getWaterEngine().tick(p_222946_, p_222947_);
	}
	
	

}
