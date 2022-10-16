package be.ninedocteur.docmod.common.item;

import be.ninedocteur.docmod.common.init.DMBlocks;
import be.ninedocteur.docmod.utils.DMTags;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class OreFinderItem extends Item {

    public static HashMap<Block, ChatFormatting> blockColorMap = new HashMap<>();

    public OreFinderItem(Properties pProperties) {
        super(pProperties);
        blockColorMap.put(Blocks.COAL_ORE, ChatFormatting.BLACK);
        blockColorMap.put(Blocks.IRON_ORE, ChatFormatting.GRAY);
        blockColorMap.put(Blocks.GOLD_ORE, ChatFormatting.GOLD);
        blockColorMap.put(Blocks.REDSTONE_ORE, ChatFormatting.RED);
        blockColorMap.put(Blocks.DIAMOND_BLOCK, ChatFormatting.AQUA);
        blockColorMap.put(DMBlocks.ZINC_ORE.get(), ChatFormatting.DARK_AQUA);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(pContext.getLevel().isClientSide()) {
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;

            for(int i = 0; i < positionClicked.getY(); i++){
                BlockState currentBlock = pContext.getLevel().getBlockState(new BlockPos(positionClicked.getX(), i, positionClicked.getY()));
                if(isTargetBlock(currentBlock)){
                    sendResult(currentBlock, new BlockPos(positionClicked.getX(), i, positionClicked.getY()), player);
                    foundBlock = true;
                }
            }

            if(!foundBlock) {
                player.sendSystemMessage(Component.literal(ChatFormatting.RED + "No Ores detected"));
            }
        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                (player) -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return super.useOn(pContext);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(Component.literal(ChatFormatting.GOLD + "Find ore with this item!"));
    }

    private boolean isTargetBlock(BlockState block){
        return block.is(DMTags.Blocks.ORE_FINDER_VALUABLES);
    }

    private void sendResult(BlockState state, BlockPos pos, Player player){
        ChatFormatting color = blockColorMap.get(state.getBlock());
        if(color == null){
            color = ChatFormatting.OBFUSCATED;
        }

        player.sendSystemMessage(
                Component.literal(
                        "Found " + color + state.getBlock().getDescriptionId() + " at " + pos
                )
        );
    }
}
