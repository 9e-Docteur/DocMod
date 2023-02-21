package be.ninedocteur.docmod.jobs.events.server;

import be.ninedocteur.docmod.jobs.data.GainXPUtil;
import be.ninedocteur.docmod.jobs.data.JobsInfo;
import be.ninedocteur.docmod.jobs.data.PlayerData;
import be.ninedocteur.docmod.jobs.util.Constants;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class KillEntityEvent {

    @SubscribeEvent
    public void onKill(LivingDeathEvent event)
    {
    	if(event.getEntity().level.isClientSide()) return;
        if(event.getSource().getEntity() instanceof ServerPlayer)
        {
            String name = "";
            if(event.getEntity() instanceof ServerPlayer) name = "Player";
            else name = Constants.getNamesByClass().get(event.getEntity().getType());

            ServerPlayer p = (ServerPlayer)event.getSource().getEntity();
            if(!GainXPUtil.KILL_ENTITY_XP.containsKey(name)) return;
            JobsInfo infos = PlayerData.getPlayerJobs(p);
            Constants.Job j = GainXPUtil.KILL_ENTITY_JOB.get(name);
            long xp = GainXPUtil.KILL_ENTITY_XP.get(name)[infos.getLevelByJob(j)];

            infos.gainXP(j, xp, p);
        }
    }


}
