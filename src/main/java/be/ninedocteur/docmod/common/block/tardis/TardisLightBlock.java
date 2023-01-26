package be.ninedocteur.docmod.common.block.tardis;

import be.ninedocteur.docmod.utils.PlanetUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class TardisLightBlock extends Block {
    public static final BooleanProperty LIT = BooleanProperty.create("lit");
    public TardisLightBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        if(PlanetUtils.getDimension("tardis")){
            return state.getValue(LIT) ? 15 : 0;
        } else {
            return 15;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_49915_) {
        p_49915_.add(LIT);
    }

//    @Override
//    public void tick(BlockState p_222945_, ServerLevel p_222946_, BlockPos p_222947_, RandomSource p_222948_) {
//        TardisTileEntity tardisTileEntity = TardisTileEntity.getOrCreateTardis(p_222947_.getX() / 1000);
//        if(!p_222946_.isClientSide()) {
//            if (tardisTileEntity != null) {
//                if (PlanetUtils.getDimension("tardis")) {
//                    if (!tardisTileEntity.isOn()) {
//                        p_222946_.setBlock(p_222947_, p_222945_.cycle(LIT), 3);
//                    }
//                }
//            }
//        }
//        super.tick(p_222945_, p_222946_, p_222947_, p_222948_);
//    }
}
