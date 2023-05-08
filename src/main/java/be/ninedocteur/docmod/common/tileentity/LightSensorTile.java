package be.ninedocteur.docmod.common.tileentity;

import java.util.List;

import be.ninedocteur.docmod.common.block.LightSensorBlock;
import be.ninedocteur.docmod.common.init.DMTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.AABB;

public class LightSensorTile extends BlockEntity{
	public boolean shoudShowLight;
	private Direction dir;
	
	public LightSensorTile(BlockPos p_155229_, BlockState p_155230_) {
		super(DMTileEntity.LIGHT_SENSOR.get(), p_155229_, p_155230_);
		// TODO Auto-generated constructor stub
	}

	public boolean isShoudShowLight() {
		return shoudShowLight;
	}
	
	public void setShoudShowLight(boolean shoudShowLight) {
		this.shoudShowLight = shoudShowLight;
	}
	
	public void tick(Direction direction) {
		if(!this.getLevel().isClientSide) {
			BlockPos pos = this.getBlockPos();
			DirectionProperty property = BlockStateProperties.HORIZONTAL_FACING;
			BlockPos zonePos;
			
			
		
	        //
			AABB detectionBoxNorth = new AABB(pos.offset(0, 1, 0), pos.offset(1, 1, 5)); // 5 blocks to the north of the block
			AABB detectionBoxNorthWest = new AABB(pos.offset(-4, 1, 0), pos.offset(1, 1, 5)); // 5 blocks to the north-west of the block
			AABB detectionBoxNorthEast = new AABB(pos.offset(0, 1, -4), pos.offset(5, 1, 1)); // 5 blocks to the north-east of the block
	        
			List<LivingEntity> entitiesNorth = level.getEntitiesOfClass(LivingEntity.class, detectionBoxNorth);
			List<LivingEntity> entitiesNorthWest = level.getEntitiesOfClass(LivingEntity.class, detectionBoxNorthWest);
			List<LivingEntity> entitiesNorthEast = level.getEntitiesOfClass(LivingEntity.class, detectionBoxNorthEast);

			
			boolean shouldBeOn = !entitiesNorth.isEmpty() || !entitiesNorthEast.isEmpty() || !entitiesNorthWest.isEmpty();

			if (this.getBlockState().getValue(LightSensorBlock.LIT) != shouldBeOn)
				level.setBlockAndUpdate(pos, this.getBlockState().setValue(LightSensorBlock.LIT, shouldBeOn));

		}
	}
	
	public enum EnumDirection {
	    NORTH,
	    EAST,
	    SOUTH,
	    WEST
	}
}
