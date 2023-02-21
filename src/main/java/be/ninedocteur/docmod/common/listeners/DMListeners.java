package be.ninedocteur.docmod.common.listeners;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.gui.overlay.DMSpaceSuitOverlay;
import be.ninedocteur.docmod.common.block.HandBrakeBlock;
import be.ninedocteur.docmod.common.capes.Cape;
import be.ninedocteur.docmod.common.init.DMItems;
import be.ninedocteur.docmod.utils.*;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DMListeners {
    private static boolean isMessageSend;

    public DMListeners(){
        isMessageSend = false;
    }
    @SubscribeEvent
    public static void onPLayerLevelJoin(EntityJoinLevelEvent e){
        if(!e.getLevel().isClientSide()) return;

        //CAPE
        if(e.getEntity() instanceof Player player){
                if (LaunchUtils.isRunningInDev()) {
                    if(!isMessageSend) { // AVOID TO SEND 2 TIME THE MESSAGE
                        player.sendSystemMessage(Component.literal(ChatFormatting.GOLD + DocMod.FULLDOCMODVERSION + " is running in developper mode."));
                        isMessageSend = true;
                    }
                }
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
    public static void onPlayerUpdate(LivingEvent.LivingTickEvent event){
        if(event.getEntity() instanceof Player player){
            if(player.getInventory().getArmor(3).is(DMItems.SPACE_HELMET.get())){
                DMSpaceSuitOverlay.showSpaceSuitOverlay = true;
            } else {
                DMSpaceSuitOverlay.showSpaceSuitOverlay = false;
            }
        }
    }

    @SubscribeEvent
    public static void onButtonPressed(ScreenEvent.Init.Post event){
        if(event.getScreen() instanceof PauseScreen pauseScreen){
            final Window window = Minecraft.getInstance().getWindow();

        }
    }
}
