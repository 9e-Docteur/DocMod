package be.ninedocteur.docmod.common.tileentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class DMWaterCapableBlockEntity extends DMBaseTileEntity{

	public DMWaterCapableBlockEntity(BlockEntityType<?> p_155228_, BlockPos p_155229_, BlockState p_155230_) {
		super(p_155228_, p_155229_, p_155230_);
		// TODO Auto-generated constructor stub
		this.getWaterEngine().setWaterEngineEnabled(true);
		this.getWaterEngine().setWaterCapable(true);
	}

}
