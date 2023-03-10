package be.ninedocteur.docmod.common.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class SmithItem extends Item {

    public SmithItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResult useOn(UseOnContext p_41427_) {
        BlockPos pos = p_41427_.getClickedPos();
        BlockState state = p_41427_.getLevel().getBlockState(pos);
        Level level = p_41427_.getLevel();
        ItemStack itemStack = p_41427_.getItemInHand();
        Player player = p_41427_.getPlayer();
        if(state.getBlock() == Blocks.DAMAGED_ANVIL){
            level.removeBlock(pos, false);
            level.setBlock(pos, Blocks.CHIPPED_ANVIL.defaultBlockState(), 1);
            this.setDamage(this.getDefaultInstance(), 1);
        }
        if(state.getBlock() == Blocks.CHIPPED_ANVIL){
            level.removeBlock(pos, false);
            level.setBlock(pos, Blocks.ANVIL.defaultBlockState(), 1);
            itemStack.hurtAndBreak(1, player, (player1 -> {
                player1.broadcastBreakEvent(player.getUsedItemHand());
            }));
        }
        return super.useOn(p_41427_);
    }
}
