package be.ninedocteur.docmod.common.listeners;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.capes.AnimatedCapeHandler;
import be.ninedocteur.docmod.common.capes.Cape;
import be.ninedocteur.docmod.common.capes.CapeHandler;
import be.ninedocteur.docmod.common.init.DMItems;
import be.ninedocteur.docmod.utils.LaunchUtils;
import be.ninedocteur.docmod.utils.TeamUUIDs;
import be.ninedocteur.docteam.api.DocTeamAPI;
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
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
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
        if(!e.getLevel().isClientSide()) return;

        if(e.getEntity() instanceof Player player){
                if (LaunchUtils.isRunningInDev()) {
                    if(!isMessageSend) { // AVOID TO SEND 2 TIME THE MESSAGE
                        player.sendSystemMessage(Component.literal(ChatFormatting.GOLD + DocMod.FULLDOCMODVERSION + " is running in developper mode."));
                        isMessageSend = true;
                    }
                }
                DocMod.prepareDownload();
            	Cape.NINETY_CAPE.update();
            	AnimatedCapeHandler.readCapeTexture(DocTeamAPI.getAPI() + "docmod/cape/ninety/" + AnimatedCapeHandler.i + ".png", AnimatedCapeHandler.i);
            	renderCape();
        }
    }

    private static void renderCape(){
        for(EntityRenderer<? extends Player> renderer : Minecraft.getInstance().getEntityRenderDispatcher().getSkinMap().values()){
             if(renderer instanceof PlayerRenderer playerRenderer) {
            	 playerRenderer.addLayer(new Cape(playerRenderer));
             }
        }
    }
    
    @SubscribeEvent
    public static void onPlayerLevelLeave(EntityLeaveLevelEvent event) {
    	if(event.getEntity() instanceof Player player) {
    		//CapeHandler.DOWNLOADED_TEXTURES.clear();
    		//CapeHandler.DOWNLOADED_TEXTURES.remove("https://api.docteam.tk/docmod/cape/" + player.getUUID() + ".png");
    		//DocMod.LOGGER.warn("Cache Cleared For Cape For " + player.getUUID());
    	}
    }
}
