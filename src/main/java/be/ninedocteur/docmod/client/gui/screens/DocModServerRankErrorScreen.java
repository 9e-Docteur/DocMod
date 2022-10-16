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
public class DocModServerRankErrorScreen extends Screen {
    public static final CubeMap CUBE_MAP = new CubeMap(new ResourceLocation("textures/gui/title/background/panorama"));
    private static final ResourceLocation PANORAMA_OVERLAY = new ResourceLocation("textures/gui/title/background/panorama_overlay.png");
    private final PanoramaRenderer panorama = new PanoramaRenderer(CUBE_MAP);
    private final Screen previousScreen;
    private long fadeInStart;
    private int copyrightWidth;
    private int copyrightX;
    private Checkbox showVersionCheckbox, moddedTitleScreenCheckbox, showWIPContent, showWidget;

    public DocModServerRankErrorScreen(Screen previousScreen) {
        super( Component.translatable("narrator.screen.title"));
        this.previousScreen = previousScreen;
    }

    public DocModServerRankErrorScreen() {
        super( Component.translatable("narrator.screen.title"));
        this.previousScreen = null;
    }

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
    public void renderDirtBackground(int pVOffset) {
        super.renderDirtBackground(pVOffset);
    }

    @Override
    protected void init() {
        this.minecraft.getWindow().setTitle("DocMod " + DMUtils.VERSION + " " + DMUtils.CODENAME + " - Error");
        Button cancelButton = new Button(this.width / 2 -50, this.height / 4 + 58 + 0, 100, 20, Component.translatable("Close"), (button -> {
            onClose();
        }));
        addRenderableWidget(cancelButton);



        super.init();
    }

    @Override
    public void onClose() {
        if(previousScreen != null){
            minecraft.setScreen(previousScreen);
        }
        super.onClose();
    }
}
