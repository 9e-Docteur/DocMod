package be.ninedocteur.docmod.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

import be.ninedocteur.docmod.common.tileentity.LightSensorTile;
import be.ninedocteur.docmod.common.tileentity.SolarPanelTile;

import java.util.stream.Stream;

public class LightSensorBlock extends BaseEntityBlock{
	public Direction state;
	public static final BooleanProperty LIT = BlockStateProperties.LIT;
    

    public LightSensorBlock(Properties p_49224_) {
		super(p_49224_);
		// TODO Auto-generated constructor stub
	}

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
		state = context.getHorizontalDirection().getOpposite();
		return this.defaultBlockState();
    }
    
    @Override
    public void onPlace(BlockState p_60566_, Level p_60567_, BlockPos p_60568_, BlockState p_60569_, boolean p_60570_) {
    	// TODO Auto-generated method stub
    	super.onPlace(p_60566_, p_60567_, p_60568_, p_60569_, p_60570_);
    	
    }
    
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_55673_) {
        p_55673_.add(LIT);
     }
    
  
	@Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153212_, BlockState p_153213_, BlockEntityType<T> p_153214_) {
        return p_153212_.isClientSide ? null
                : (level0, pos, state0, blockEntity) -> ((LightSensorTile) blockEntity).tick(state);
    }

	@Override
	public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
		// TODO Auto-generated method stub
		return new LightSensorTile(p_153215_, p_153216_);
	}
}
