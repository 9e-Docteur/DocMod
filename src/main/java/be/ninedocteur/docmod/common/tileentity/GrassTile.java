package be.ninedocteur.docmod.common.tileentity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class GrassTile extends DMBaseTileEntity{
	public int stage;
	public int tick;

	public GrassTile(BlockPos p_155229_, BlockState p_155230_) {
		super(be.ninedocteur.docmod.common.init.DMTileEntity.GRASS_TILE.get(), p_155229_, p_155230_);
		// TODO Auto-generated constructor stub
		this.getLifeEngine().setHealthSystemEnabled(true);
		this.getLifeEngine().setHealth(0);
		this.getLifeEngine().setTick(false);
	}
	
	@Override
	protected void saveAdditional(CompoundTag p_187471_) {
		// TODO Auto-generated method stub
		super.saveAdditional(p_187471_);
		p_187471_.putInt("health", this.getLifeEngine().getHealth());
	}
	
	@Override
	public void load(CompoundTag p_155245_) {
		// TODO Auto-generated method stub
		super.load(p_155245_);
		int health = this.getLifeEngine().getHealth();
		if(p_155245_.contains("health")) {
			health = p_155245_.getInt("health");
		} else {
			health = 0;
		}
	}
	
	public int getStage() {
		return stage;
	}
	
	public void setStage(int stage) {
		this.stage = stage;
	}
	
	public void tick() {
		int health = this.getLifeEngine().getHealth();
		tick++;
		if(tick == 120) {
			health++;
			tick = 0;
			this.getLifeEngine().setHealth(health);
		}
		if(health == 0) {
			stage = 1;
			this.setStage(stage);
			this.getLifeEngine().setHealth(health);
		}
		if(health == 1) {
			stage = 2;
			this.setStage(stage);
			this.getLifeEngine().setHealth(health);
		}
		if(health == 2) {
			stage = 3;
			this.setStage(stage);
			this.getLifeEngine().setHealth(health);
		}
		if(health == 3) {
			stage = 4;
			this.setStage(stage);
			this.getLifeEngine().setHealth(health);
		}
		if(health == 4) {
			stage = 5;
			this.setStage(stage);
			this.getLifeEngine().setHealth(health);
		}
		if(stage > 5) {
			stage = 5;
			this.setStage(stage);
		}
	}

	@Override
	public void setName(DMNBTTile tile) {
		tile.setName("grass_tile");
		
	}
}
