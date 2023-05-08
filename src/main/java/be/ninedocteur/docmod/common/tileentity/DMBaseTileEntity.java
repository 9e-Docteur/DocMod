package be.ninedocteur.docmod.common.tileentity;

import be.ninedocteur.docmod.common.tileentity.system.LifeEngine;
import be.ninedocteur.docmod.common.tileentity.system.WaterEngine;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class DMBaseTileEntity extends DMNBTTile{
	private LifeEngine lifeEngine = new LifeEngine();
	private WaterEngine waterEngine = new WaterEngine();

	public DMBaseTileEntity(BlockEntityType<?> p_155228_, BlockPos p_155229_, BlockState p_155230_) {
		super(p_155228_, p_155229_, p_155230_);
		// TODO Auto-generated constructor stub
	}
	
	public LifeEngine getLifeEngine() {
		return lifeEngine;
	}
	
	public WaterEngine getWaterEngine() {
		return waterEngine;
	}

}