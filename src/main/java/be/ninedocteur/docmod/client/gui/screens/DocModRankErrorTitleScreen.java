package be.ninedocteur.docmod.client.gui.screens;

import be.ninedocteur.docmod.utils.ColorUtils;
import be.ninedocteur.docmod.utils.DMUtils;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.Util;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.PanoramaRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.common.MinecraftForge;

@OnlyIn(Dist.CLIENT)
public class DocModRankErrorTitleScreen extends Screen {
    private final Screen previousScreen;
    private long fadeInStart;

    public DocModRankErrorTitleScreen(Screen previousScreen) {
        super( Component.translatable("narrator.screen.title"));
        this.previousScreen = previousScreen;
    }

    public DocModRankErrorTitleScreen() {
        super( Component.translatable("narrator.screen.title"));
        this.previousScreen = null;
    }
//"You do not have the rank to enter the server!"
    //"If you think that this is an error, please join our discord."
    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pPoseStack);
        drawCenteredString(pPoseStack, font,  Component.translatable("gui.docmod.error.rank"), width/2, 92, ColorUtils.getRed());
        drawCenteredString(pPoseStack, font,  Component.translatable("gui.docmod.notice.wrongerror"), width/2, 102, ColorUtils.getWhite());
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        float f = (float)(Util.getMillis() - this.fadeInStart);
        float f1 = Mth.clamp(f - 1.0F, 0.0F, 1.0F);
        int l = Mth.ceil(f1 * 255.0F) << 24;


    }


    @Override
    public void renderDirtBackground(PoseStack p_265092_) {
    	// TODO Auto-generated method stub
    	super.renderDirtBackground(p_265092_);
    }
}
