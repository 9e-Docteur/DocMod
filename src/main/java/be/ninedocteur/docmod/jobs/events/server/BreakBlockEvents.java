package be.ninedocteur.docmod.jobs.events.server;


import be.ninedocteur.docmod.jobs.data.GainXPUtil;
import be.ninedocteur.docmod.jobs.data.JobsInfo;
import be.ninedocteur.docmod.jobs.data.PlayerData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.HashMap;
import java.util.Map;

@EventBusSubscriber
public class BreakBlockEvents {

    /**
     * Fired when a block is broken by a player : checks if the player can gain any xp and gives it to the player.
     * @param event the Break Block Event
     */
    @SubscribeEvent
    public void onBreakOreOrCrop(BlockEvent.BreakEvent event) {
        if(event.getLevel().isClientSide() || !(event.getPlayer() instanceof ServerPlayer))
            return;
        ServerPlayer player = (ServerPlayer) event.getPlayer();
        BlockState state = event.getState();
        JobsInfo jobs = PlayerData.getPlayerJobs(player);
        Block block = event.getState().getBlock();

        //Ores
        if(GainXPUtil.BREAK_BLOCK_XP.containsKey(block))
        {
            long xp = GainXPUtil.BREAK_BLOCK_XP.get(block)
                    [jobs.getLevelByJob(GainXPUtil.BREAK_BLOCK_JOB.get(block))];
            jobs.gainXP(GainXPUtil.BREAK_BLOCK_JOB.get(block), xp, player);
        }

        //Crops
        else if(GainXPUtil.HARVEST_CROP_XP.containsKey(getItemsFromCrops().get(block)) && block instanceof CropBlock && getItemsFromCrops().containsKey(block))
        {
            if(!((CropBlock)block).isMaxAge(event.getState())) return;

            long xp = GainXPUtil.HARVEST_CROP_XP.get(getItemsFromCrops().get(block))
                    [jobs.getLevelByJob(GainXPUtil.HARVEST_CROP_JOB.get(getItemsFromCrops().get(block)))];
            jobs.gainXP(GainXPUtil.HARVEST_CROP_JOB.get(getItemsFromCrops().get(block)), xp, player);
        }
    }

    public static Map<Block, Item> getItemsFromCrops()
    {
        Map<Block, Item> map = new HashMap<>();
        map.put(Blocks.WHEAT, Items.WHEAT);
        map.put(Blocks.POTATOES, Items.POTATO);
        map.put(Blocks.CARROTS, Items.CARROT);
        map.put(Blocks.BEETROOTS, Items.BEETROOT);
        return map;
    }
}
