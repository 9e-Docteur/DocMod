package be.ninedocteur.docmod.jobs.events.server;

import be.ninedocteur.docmod.jobs.data.ServerJobsData;
import be.ninedocteur.docmod.jobs.data.capabilities.PlayerData;
import be.ninedocteur.docmod.jobs.data.capabilities.PlayerJobs;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class EntityInteractionEvents {

    /**
     * Fired when an entity dies. Checks if it was killed by a player and checks if the player can gain xp.
     * If yes it gives it to the player.
     * @param event the Death Event
     */
    @SubscribeEvent
    public void onKill(LivingDeathEvent event) {
    	if(event.getEntity().level.isClientSide() ||
                !(event.getSource().getEntity() instanceof ServerPlayer))
            return;
        if(event.getEntity() instanceof Player &&
                event.getSource().getEntity().equals(event.getEntity()))
            return;
        EntityType<? extends Entity> type = event.getEntity().getType();

        ServerPlayer p = (ServerPlayer)event.getSource().getEntity();
        PlayerJobs jobs = PlayerData.getPlayerJobs(p);

        for(String job : jobs.getJobs()){
            int level = jobs.getLevelByJob(job);
            long xp = ServerJobsData.KILLING_ENTITY_XP.getXPByLevelAndJob(type, level, job);
            jobs.gainXP(job, xp, p);
        }
    }

    /**
     * Fired when a baby mob is born. If the parents where bred by the player, checks if the player can gain xp.
     * If yes it gives it to the player.
     * @param event the Baby Spawn Event
     */
    @SubscribeEvent
    public void onBreed(BabyEntitySpawnEvent event) {
        if (event.getCausedByPlayer() == null || event.getCausedByPlayer().level.isClientSide())
            return;
        System.out.println("Breeding");
        EntityType<? extends Entity> type = event.getChild().getType();

        PlayerJobs jobs = PlayerData.getPlayerJobs(event.getCausedByPlayer());

        for (String job : jobs.getJobs()) {
            int level = jobs.getLevelByJob(job);
            long xp = ServerJobsData.BREEDING_ENTITY_XP.getXPByLevelAndJob(type, level, job);
            jobs.gainXP(job, xp, (ServerPlayer) event.getCausedByPlayer());
            System.out.println("gaining " + xp + " xp");
        }
    }
}
