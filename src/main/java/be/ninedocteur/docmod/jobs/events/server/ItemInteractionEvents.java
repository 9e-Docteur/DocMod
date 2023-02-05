package be.ninedocteur.docmod.jobs.events.server;

import be.ninedocteur.docmod.jobs.data.ServerJobsData;
import be.ninedocteur.docmod.jobs.data.capabilities.PlayerData;
import be.ninedocteur.docmod.jobs.data.capabilities.PlayerJobs;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.ItemCraftedEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.ItemSmeltedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ItemInteractionEvents {


    /**
     * Fired when an item is crafted. Checks if the player can gain xp and gives it to the player.
     * @param event the Craft Event
     */
    @SubscribeEvent
    public void onCraft(ItemCraftedEvent event) {
    	if(event.getEntity().level.isClientSide() || !(event.getEntity() instanceof ServerPlayer))
            return;
        PlayerJobs jobs = PlayerData.getPlayerJobs(event.getEntity());
        ItemStack stack = event.getCrafting();

        for(String job : jobs.getJobs()){
            int level = jobs.getLevelByJob(job);
            long xp = ServerJobsData.CRAFTING_ITEMS_XP.getXPByLevelAndJob(stack, level, job);
            jobs.gainXP(job, xp*stack.getCount(), (ServerPlayer) event.getEntity());
        }
    }

    /**
     * Fired when an item is smelted. Checks if the player can gain xp and gives it to the player.
     * @param event the Smelt Event
     */
    @SubscribeEvent
    public void onSmelt(ItemSmeltedEvent event) {
        if(event.getEntity().level.isClientSide() || !(event.getEntity() instanceof ServerPlayer))
            return;
        PlayerJobs jobs = PlayerData.getPlayerJobs(event.getEntity());
        ItemStack stack = event.getSmelting();

        for(String job : jobs.getJobs()){
            int level = jobs.getLevelByJob(job);
            long xp = ServerJobsData.SMELTING_ITEMS_XP.getXPByLevelAndJob(stack, level, job);
            jobs.gainXP(job, xp*stack.getCount(), (ServerPlayer) event.getEntity());
        }
    }

    @SubscribeEvent
    public void onFished(ItemFishedEvent event) {
        if(event.getEntity().level.isClientSide() || !(event.getEntity() instanceof ServerPlayer))
            return;
        PlayerJobs jobs = PlayerData.getPlayerJobs(event.getEntity());
        for(ItemStack stack : event.getDrops()){
            for(String job : jobs.getJobs()){
                int level = jobs.getLevelByJob(job);
                long xp = ServerJobsData.FISHING_ITEMS_XP.getXPByLevelAndJob(stack, level, job);
                jobs.gainXP(job, xp*stack.getCount(), (ServerPlayer) event.getEntity());
            }
        }
    }

    
}
