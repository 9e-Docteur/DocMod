package be.ninedocteur.docmod.client.gui.overlay;

import be.ninedocteur.docmod.DMConfig;
import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.init.DMItems;
import be.ninedocteur.docmod.utils.ColorUtils;
import be.ninedocteur.docmod.utils.DMUtils;
import be.ninedocteur.docmod.utils.IOUtils;
import be.ninedocteur.docmod.utils.LaunchUtils;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.SharedConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.Event;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;

import static net.minecraft.client.gui.GuiComponent.blit;

@OnlyIn(Dist.CLIENT)
public class DocModVersionOverlay {

    public static void render(RenderGuiOverlayEvent.Pre event){
//        ResourceLocation zincIcon = IOUtils.readImage("http://docteamwebsite.tk/modinfoio/Assets/zinc.png");
        if (DMConfig.Client.DEV_MODE.get() || LaunchUtils.isRunningInDev() && !DocModDebugOverlay.showDebugOverlay) {
            int w = event.getWindow().getGuiScaledWidth();
            int h = event.getWindow().getGuiScaledHeight();
            int posX = 22;
            int posXimg = 10;
            int posY = 10;
            int posYBeta = 20;
            int posYsrv = 40;
            int posYram = 30;
            ItemStack itemIcon = new ItemStack(DMItems.BELGIUM_FRIES.get());
            MemoryUsage heapMemoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
            PoseStack poseStack = new PoseStack();
            long maxMB = heapMemoryUsage.getMax()/(1024*1024);
            long usedMB = heapMemoryUsage.getUsed()/(1024*1024);
            if(LaunchUtils.checkLaunchedVersion()){
                Minecraft.getInstance().font.draw(event.getPoseStack(), "DocMod " + DMUtils.VERSION + " " + DMUtils.CODENAME, posX, posY, ColorUtils.getWhite());
                Minecraft.getInstance().getItemRenderer().renderGuiItem(new PoseStack(), itemIcon, 4, 6);
            } else if(!LaunchUtils.isRunningInDev() && DocMod.isInDevVersion) {
                Minecraft.getInstance().font.draw(event.getPoseStack(), "DocMod 1.19 Development", posX - 10, posY, ColorUtils.getWhite());
            }
        }
        updateWindowName();
    }

    public static void updateWindowName() {
    	Minecraft.getInstance().getWindow().setTitle("Minecraft " + SharedConstants.getCurrentVersion().getName() + " | " + DocMod.MODNAME + " " + DocMod.VERSION);
    }


}
