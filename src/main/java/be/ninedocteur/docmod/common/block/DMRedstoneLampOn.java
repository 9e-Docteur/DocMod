package be.ninedocteur.docmod.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class DMRedstoneLampOn extends Block {
    public DMRedstoneLampOn(Properties p_49795_) {
        super(p_49795_);
    }
    
    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
    	// TODO Auto-generated method stub
    	return 15;
    }
}
