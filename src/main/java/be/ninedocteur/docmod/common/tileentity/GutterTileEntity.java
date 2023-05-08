package be.ninedocteur.docmod.common.tileentity;

import be.ninedocteur.docmod.common.init.DMTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class GutterTileEntity extends DMWaterCapableBlockEntity{

	public GutterTileEntity(BlockPos p_155229_, BlockState p_155230_) {
		super(DMTileEntity.GUTTER_TILE.get(), p_155229_, p_155230_);
		// TODO Auto-generated constructor stub
		this.getWaterEngine().setMaxWaterLevel(1);
		this.getWaterEngine().setCanPropagate(true);
	}

	@Override
	public void setName(DMNBTTile tile) {
		tile.setName("gutter_tile");
		
	}
	
	

}
