package be.ninedocteur.docmod.client.gui.overlay;

import be.ninedocteur.docmod.common.item.space.SpaceSuitHelmet;
import be.ninedocteur.docmod.utils.ColorUtils;
import be.ninedocteur.docmod.utils.DMUtils;
import be.ninedocteur.docmod.utils.LaunchUtils;
import com.mojang.blaze3d.platform.GlUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.SharedConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;

@OnlyIn(Dist.CLIENT)
public class DMSpaceSuitOverlay {
    public static boolean showSpaceSuitOverlay = false;
    private static final ResourceLocation SPACE_SUIT_OVERLAY = new ResourceLocation("docmod:textures/gui/overlay/space_suit_overlay");

    public static void render(RenderGuiOverlayEvent.Pre event){
        if (showSpaceSuitOverlay) {
            int w = event.getWindow().getGuiScaledWidth();
            int posX = 4;
            int posY = 4;
            Minecraft.getInstance().font.draw(event.getPoseStack(), "Air: " + SpaceSuitHelmet.air + "%", posX, posY, -1);
            //RenderSystem.setShaderTexture(0, SPACE_SUIT_OVERLAY);
            //Minecraft.getInstance().screen.blit(poseStack, 0, 0, 0, 0, 1920, 1080);
        }
    }
}
