package be.ninedocteur.docmod.jobs.events.server;

import be.ninedocteur.docmod.jobs.data.GainXPUtil;
import be.ninedocteur.docmod.jobs.data.JobsInfo;
import be.ninedocteur.docmod.jobs.data.PlayerData;
import be.ninedocteur.docmod.jobs.util.Constants;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.player.PlayerEvent.ItemCraftedEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.ItemSmeltedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class CraftItemEvents {


    @SubscribeEvent
    public void onCraft(ItemCraftedEvent event)
    {
            if (event.getEntity().level.isClientSide()) return;
            int count = event.getCrafting().getCount();
            Item item = event.getCrafting().getItem();

            if (GainXPUtil.CRAFT_ITEM_JOB.containsKey(item)) {
                Constants.Job j = GainXPUtil.CRAFT_ITEM_JOB.get(item);
                Player player = event.getEntity();
                JobsInfo infos = PlayerData.getPlayerJobs(player);
                long xp = GainXPUtil.CRAFT_ITEM_XP.get(item)[infos.getLevelByJob(j)];

                infos.gainXP(j, xp * count, (ServerPlayer) player);
            }
    }

    @SubscribeEvent
    public void onSmelt(ItemSmeltedEvent event)
    {
        Player player = event.getEntity();
    	if(player.level.isClientSide()) return;
        int count = event.getSmelting().getCount();
        Item item = event.getSmelting().getItem();

        if(GainXPUtil.SMELT_ITEM_JOB.containsKey(item))
        {
            Constants.Job j = GainXPUtil.SMELT_ITEM_JOB.get(item);
            JobsInfo infos = PlayerData.getPlayerJobs(player);
            long xp = GainXPUtil.SMELT_ITEM_XP.get(item)[infos.getLevelByJob(j)];

            infos.gainXP(j, xp * count, (ServerPlayer) player);
        }
    }

    
}
