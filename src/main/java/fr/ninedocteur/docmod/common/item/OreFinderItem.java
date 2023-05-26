package fr.ninedocteur.docmod.common.item;

import fr.ninedocteur.docmod.common.init.DMBlocks;
import fr.ninedocteur.docmod.utils.DMTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

import java.util.HashMap;

public class OreFinderItem extends Item {
    public static HashMap<Block, Formatting> blockColorMap = new HashMap<>();

    public OreFinderItem(Settings properties) {
        super(properties);
        blockColorMap.put(Blocks.COAL_ORE, Formatting.BLACK);
        blockColorMap.put(Blocks.IRON_ORE, Formatting.GRAY);
        blockColorMap.put(Blocks.GOLD_ORE, Formatting.GOLD);
        blockColorMap.put(Blocks.REDSTONE_ORE, Formatting.RED);
        blockColorMap.put(Blocks.DIAMOND_BLOCK, Formatting.AQUA);
        blockColorMap.put(DMBlocks.ZINC_ORE, Formatting.DARK_AQUA);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(context.getWorld().isClient()) {
            BlockPos positionClicked = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            boolean foundBlock = false;

            for(int i = 0; i < positionClicked.getY(); i++){
                BlockState currentBlock = context.getWorld().getBlockState(new BlockPos(positionClicked.getX(), i, positionClicked.getY()));
                if(isTargetBlock(currentBlock)){
                    sendResult(currentBlock, new BlockPos(positionClicked.getX(), i, positionClicked.getY()), player);
                    foundBlock = true;
                }
            }

            if(!foundBlock) {
                player.sendMessage(Text.literal(Formatting.RED + "No Ores detected"));
            }
        }

        context.getStack().damage(1, Random.create(), (ServerPlayerEntity) context.getPlayer());

        return super.useOnBlock(context);
    }


    private boolean isTargetBlock(BlockState block){
        return block.isIn(DMTags.Blocks.ORE_FINDER_VALUABLES);
    }

    private void sendResult(BlockState state, BlockPos pos, PlayerEntity player){
        Formatting color = blockColorMap.get(state.getBlock());
        if(color == null){
            color = Formatting.OBFUSCATED;
        }

        player.sendMessage(
                Text.literal("Found " + color + state.getBlock().getTranslationKey() + " at " + pos)
        );
    }
}
