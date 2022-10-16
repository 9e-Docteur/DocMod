package be.ninedocteur.docmod.client.gui.screens;

import be.ninedocteur.docmod.DMConfig;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import be.ninedocteur.docmod.utils.DMUtils;
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
public class DocModConfigScreen extends Screen {
    public static final CubeMap CUBE_MAP = new CubeMap(new ResourceLocation(DMUtils.MOD_ID, "textures/gui/config/background/panorama"));
    private static final ResourceLocation PANORAMA_OVERLAY = new ResourceLocation("textures/gui/title/background/panorama_overlay.png");
    private PanoramaRenderer panorama = new PanoramaRenderer(CUBE_MAP);
    private final Screen previousScreen;
    private long fadeInStart;
    private Checkbox showVersionCheckbox, moddedTitleScreenCheckbox, showWIPContent, showWidget, showThreeDHead, showVersionIGCheckbox;

    public DocModConfigScreen(Screen previousScreen) {
        super( Component.translatable("narrator.screen.title"));
        this.previousScreen = previousScreen;
        this.panorama = new PanoramaRenderer(CUBE_MAP);
    }

    public DocModConfigScreen() {
        super( Component.translatable("narrator.screen.title"));
        this.previousScreen = null;
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        float f = (float) (Util.getMillis() - this.fadeInStart);
        this.panorama.render(pPartialTick, Mth.clamp(f, 0.0F, 1.0F));
        drawCenteredString(pPoseStack, font,  Component.translatable("DocMod Config GUI"), width/2, 10, 0xffffff);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

/*
    @Override
    public void renderDirtBackground(int pVOffset) {
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tesselator.getBuilder();
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderTexture(0, BACKGROUND_LOCATION);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        float f = 32.0F;
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
        bufferbuilder.vertex(0.0D, this.height, 0.0D).uv(0.0F, (float)this.height / 32.0F + (float)pVOffset).color(64, 64, 64, 255).endVertex();
        bufferbuilder.vertex(this.width, this.height, 0.0D).uv((float)this.width / 32.0F, (float)this.height / 32.0F + (float)pVOffset).color(64, 64, 64, 255).endVertex();
        bufferbuilder.vertex(this.width, 0.0D, 0.0D).uv((float)this.width / 32.0F, (float)pVOffset).color(64, 64, 64, 255).endVertex();
        bufferbuilder.vertex(0.0D, 0.0D, 0.0D).uv(0.0F, (float)pVOffset).color(64, 64, 64, 255).endVertex();
        tesselator.end();
        MinecraftForge.EVENT_BUS.post(new ScreenEvent.BackgroundDrawnEvent(this, new PoseStack()));
    }

 */

    @Override
    protected void init() {
        this.minecraft.getWindow().setTitle("DocMod " + DMUtils.VERSION + " " + DMUtils.CODENAME + " - Configuration");
        Button cancelButton = new Button(this.width / 2 -200, this.height / 4 + 48 + 80, 100, 20,  Component.translatable("gui.docmod.cancel"), (button -> {
            onClose();
        }));
        Button applyButton = new Button(this.width / 2 +100, this.height / 4 + 48 + 80, 100, 20,  Component.translatable("gui.docmod.apply"), (button -> {
            apply();
            this.minecraft.getWindow().setTitle("DocMod " + DMUtils.VERSION + " " + DMUtils.CODENAME);
        }));
        addRenderableWidget(cancelButton);
        addRenderableWidget(applyButton);

        showVersionCheckbox = new Checkbox(this.width / 2 -200, this.height / 4 + 48, 20, 20,  Component.translatable("gui.docmod.config.showversion"), DMConfig.Client.showVersion.get());
        moddedTitleScreenCheckbox = new Checkbox(this.width / 2 -200, this.height / 4 + 48 - 20, 20, 20,  Component.translatable("gui.docmod.config.custommainmenu"), DMConfig.Client.customtitlescreen.get());
        //showWIPContent = new Checkbox(this.width / 2 -200, this.height / 4 + 48 - 40, 20, 20, new TranslatableComponent("WIP Tab"), DocModConfig.showWIPTabs.get());
        showWidget = new Checkbox(this.width / 2 -200, this.height / 4 + 48 - 40, 20, 20,  Component.translatable("gui.docmod.config.showwidget"), DMConfig.Client.showWidget.get());
        showThreeDHead = new Checkbox(this.width / 2 -200, this.height / 4 + 48 - 60, 20, 20,  Component.translatable("gui.docmod.config.show3dhead"), DMConfig.Client.ThreeDHead.get());
        showVersionIGCheckbox = new Checkbox(this.width / 2 -200, this.height / 4 + 48 + 20, 20, 20,  Component.translatable("gui.docmod.config.showversionig"), DMConfig.Client.showVersionInGame.get());
        addRenderableWidget(showVersionCheckbox);
        addRenderableWidget(moddedTitleScreenCheckbox);
        addRenderableWidget(showWidget);
        addRenderableWidget(showThreeDHead);
        addRenderableWidget(showVersionIGCheckbox);
       // addRenderableWidget(showWIPContent);

        super.init();
    }

    private void apply(){
        DMConfig.Client.showVersion.set(showVersionCheckbox.selected());
        DMConfig.Client.customtitlescreen.set(moddedTitleScreenCheckbox.selected());
        DMConfig.Client.showWidget.set(showWidget.selected());
        DMConfig.Client.ThreeDHead.set(showThreeDHead.selected());
        if(!showWidget.selected()){
            DMConfig.Client.customtitlescreen.set(showWidget.selected());
        }
        onClose();
    }

    @Override
    public void onClose() {
        if(previousScreen != null){
            minecraft.setScreen(previousScreen);
        }
        super.onClose();
    }
}
