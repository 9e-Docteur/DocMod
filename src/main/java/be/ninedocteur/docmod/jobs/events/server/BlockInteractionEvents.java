package be.ninedocteur.docmod.jobs.events.server;


import be.ninedocteur.docmod.jobs.data.ServerJobsData;
import be.ninedocteur.docmod.jobs.data.capabilities.PlayerData;
import be.ninedocteur.docmod.jobs.data.capabilities.PlayerJobs;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class BlockInteractionEvents {

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
        PlayerJobs jobs = PlayerData.getPlayerJobs(player);

        if(!ServerJobsData.BLOCKED_BLOCKS.canBreakBlock(PlayerData.getPlayerJobs(player), state.getBlock())){
            event.setCanceled(true);
            return;
        }

        boolean isGrownCrop = false;
        if(state.getBlock() instanceof CropBlock){
            isGrownCrop = ((CropBlock)state.getBlock()).isMaxAge(state);
        }

        for(String job : jobs.getJobs()){
            int level = jobs.getLevelByJob(job);
            long xp = ServerJobsData.BREAKING_BLOCKS_XP.getXPByLevelAndJob(state, level, job);
            if(xp > 0)
                jobs.gainXP(job, xp, player);

            if(!isGrownCrop)
                continue;

            long xp2 = ServerJobsData.HARVESTING_CROPS_XP.getXPByLevelAndJob(state, level, job);
            if(xp2 > 0)
                jobs.gainXP(job, xp2, player);
        }

    }
}
