package be.ninedocteur.docmod.common.block;

import javax.annotation.Nullable;

import be.ninedocteur.docmod.common.tileentity.GrassTile;
import be.ninedocteur.docmod.common.tileentity.LightSensorTile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class GrassBlock extends BaseEntityBlock{

	public GrassBlock(Properties p_49224_) {
		super(p_49224_);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
		// TODO Auto-generated method stub
		return new GrassTile(p_153215_, p_153216_);
	}
	
	@Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153212_, BlockState p_153213_, BlockEntityType<T> p_153214_) {
        return p_153212_.isClientSide ? null
                : (level0, pos, state0, blockEntity) -> ((GrassTile) blockEntity).tick();
    }

}
