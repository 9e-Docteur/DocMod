package be.ninedocteur.docmod.common.tileentity.system;

import be.ninedocteur.docmod.common.tileentity.DMWaterCapableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.Level;

public class WaterEngine {
	private int waterLevel;
	private int maxWaterLevel;
	private int transferSpeed;
	private boolean waterEngineEnabled;
	private boolean waterCapable;
	private boolean canPropagate;
	
	public WaterEngine(int waterLevel, int maxWaterLevel, int transferSpeed) {
		this.waterLevel = waterLevel;
		this.maxWaterLevel = maxWaterLevel;
		this.transferSpeed = transferSpeed;
	}
	
	public WaterEngine() {

	}
	
	public int getMaxWaterLevel() {
		return maxWaterLevel;
	}
	
	public void setMaxWaterLevel(int maxWaterLevel) {
		this.maxWaterLevel = maxWaterLevel;
	}
	
	public int getWaterLevel() {
		return waterLevel;
	}
	
	public void setWaterLevel(int waterLevel) {
		this.waterLevel = waterLevel;
	}
	
	public int getTransferSpeed() {
		return transferSpeed;
	}
	
	public void setTransferSpeed(int transferSpeed) {
		this.transferSpeed = transferSpeed;
	}
	
	public void setWaterEngineEnabled(boolean waterEngineEnabled) {
		this.waterEngineEnabled = waterEngineEnabled;
	}
	
	public boolean isWaterEngineEnabled() {
		return waterEngineEnabled;
	}
	
	public boolean isWaterCapable() {
		return waterCapable;
	}
	
	public void setWaterCapable(boolean waterCapable) {
		this.waterCapable = waterCapable;
	}
	
	public boolean isCanPropagate() {
		return canPropagate;
	}
	
	public void setCanPropagate(boolean canPropagate) {
		this.canPropagate = canPropagate;
	}
	
	public void tick(Level level, BlockPos pos) {
		if(waterLevel > maxWaterLevel) {
			waterLevel = maxWaterLevel;
		}
		
		if(this.isWaterCapable() && canPropagate) {
			propagateFluid(level, pos);
		}
	}
	
	public void propagateFluid(Level level, BlockPos pos) {
		if (!level.isClientSide()) {
            BlockPos[] neighborPositions = {
                pos.north(), pos.south(), pos.east(), pos.west(), pos.above(), pos.below()
            };
            for (BlockPos neighborPos : neighborPositions) {
                BlockEntity neighborEntity = level.getBlockEntity(neighborPos);
                if (neighborEntity instanceof DMWaterCapableBlockEntity) {
                    ((DMWaterCapableBlockEntity) neighborEntity).getWaterEngine().setWaterLevel(this.waterLevel);
                }
            }
        }
	}
}
