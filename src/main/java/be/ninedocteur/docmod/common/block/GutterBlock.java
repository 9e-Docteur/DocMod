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
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.ticks.TickPriority;

public class GutterBlock extends WaterBlock {
	int water;

	public GutterBlock(Properties p_49795_) {
		super(p_49795_);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
		// TODO Auto-generated method stub
		return new GutterTileEntity(p_153215_, p_153216_);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void tick(BlockState p_222945_, ServerLevel p_222946_, BlockPos p_222947_, RandomSource p_222948_) {
		// TODO Auto-generated method stub
		super.tick(p_222945_, p_222946_, p_222947_, p_222948_);
		p_222946_.scheduleTick(p_222947_, this, 1, TickPriority.EXTREMELY_HIGH);
		DMWaterCapableBlockEntity tile = (DMWaterCapableBlockEntity) p_222946_.getBlockEntity(p_222947_);
		if(p_222946_.isRaining()) {
			water++;
			tile.getWaterEngine().setWaterLevel(water);
		}
	}
	
	@Override
	public InteractionResult use(BlockState p_60503_, Level p_60504_, BlockPos p_60505_, Player p_60506_,
			InteractionHand p_60507_, BlockHitResult p_60508_) {
		// TODO Auto-generated method stub
		DMWaterCapableBlockEntity tile = (DMWaterCapableBlockEntity) p_60504_.getBlockEntity(p_60505_);
		System.out.println(tile.getWaterEngine().getWaterLevel());
		return super.use(p_60503_, p_60504_, p_60505_, p_60506_, p_60507_, p_60508_);
	}

}
