package be.ninedocteur.docmod.common.listeners;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.capes.Cape;
import be.ninedocteur.docmod.common.init.DMItems;
import be.ninedocteur.docmod.utils.LaunchUtils;
import be.ninedocteur.docmod.utils.TeamUUIDs;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DMListeners {
    private static boolean isMessageSend;

    public DMListeners(){
        isMessageSend = false;
    }
    @SubscribeEvent
    public static void onPLayerLevelJoin(EntityJoinLevelEvent e){
        if(e.getEntity() instanceof Player player){
                if (LaunchUtils.isRunningInDev()) {
                    if(!isMessageSend) { // AVOID TO SEND 2 TIME THE MESSAGE
                        player.sendSystemMessage(Component.literal(ChatFormatting.GOLD + DocMod.FULLDOCMODVERSION + " is running in developper mode."));
                        isMessageSend = true;
                    }
                }
        }
    }

    @SubscribeEvent
    public void onPlayerRender(RenderLivingEvent.Pre event){
        if(event.getEntity() instanceof Player player){

        }
    }

    private void renderCape(){
        for(PlayerRenderer renderer : Minecraft.getInstance().getEntityRenderDispatcher().getSkinMap().values()){
            renderer.addLayer(new Cape(renderer));
        }
    }

}
