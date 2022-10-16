package be.ninedocteur.docmod.common.block;

import be.ninedocteur.docmod.common.sound.DMSound;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class HandBrakeBlock extends Block {
    public HandBrakeBlock(Properties p_49795_) {
        super(p_49795_);
    }

    public static final BooleanProperty ON = BooleanProperty.create("on");
    public static boolean isOn = false;

    @Override
    public InteractionResult use(BlockState p_60503_, Level p_60504_, BlockPos p_60505_, Player p_60506_, InteractionHand p_60507_, BlockHitResult p_60508_) {
        if(!p_60504_.isClientSide() && p_60507_ == InteractionHand.MAIN_HAND){
            p_60504_.setBlock(p_60505_, p_60503_.cycle(ON), 3);
        }
        p_60504_.playSound(p_60506_, p_60505_, DMSound.LEVER_ON.get(), SoundSource.AMBIENT, 1f, 1f);
        return super.use(p_60503_, p_60504_, p_60505_, p_60506_, p_60507_, p_60508_);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_49915_) {
        p_49915_.add(ON);
    }
}
