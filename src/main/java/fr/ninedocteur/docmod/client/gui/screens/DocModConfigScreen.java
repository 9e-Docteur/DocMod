package fr.ninedocteur.docmod.client.gui.screens;

import fr.ninedocteur.docmod.DMConfig;
import fr.ninedocteur.docmod.utils.DMUtils;
import net.minecraft.client.gui.CubeMapRenderer;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;

public class DocModConfigScreen extends Screen {
    public static final CubeMapRenderer CUBE_MAP = new CubeMapRenderer(new Identifier(DMUtils.MOD_ID, "textures/gui/config/background/panorama"));
    private static final Identifier PANORAMA_OVERLAY = new Identifier("textures/gui/title/background/panorama_overlay.png");
    private RotatingCubeMapRenderer panorama = new RotatingCubeMapRenderer(CUBE_MAP);
    private final Screen previousScreen;
    private long fadeInStart;
    private CheckboxWidget showVersionCheckbox, moddedTitleScreenCheckbox, showWIPContent, showWidget, showThreeDHead, showVersionIGCheckbox;

    public DocModConfigScreen(Screen previousScreen) {
        super(Text.translatable("narrator.screen.title"));
        this.previousScreen = previousScreen;
        this.panorama = new RotatingCubeMapRenderer(CUBE_MAP);
    }

    public DocModConfigScreen() {
        super(Text.translatable("narrator.screen.title"));
        this.previousScreen = null;
    }

    @Override
    public void render(MatrixStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        float f = (float) (Util.getMeasuringTimeMs() - this.fadeInStart);
        this.panorama.render(pPartialTick, MathHelper.clamp(f, 0.0F, 1.0F));
        drawCenteredTextWithShadow(pPoseStack, textRenderer,  Text.translatable("DocMod Config GUI"), width/2, 10, 0xffffff);
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
        this.client.getWindow().setTitle("DocMod " + DMUtils.VERSION + " " + DMUtils.CODENAME + " - Configuration");
        ButtonWidget cancelButton = ButtonWidget.builder(Text.translatable("gui.docmod.cancel"), (button -> {
            close();
        })).dimensions(this.width / 2 -200, this.height / 4 + 48 + 80, 100, 20).build();
        ButtonWidget applyButton = ButtonWidget.builder(Text.translatable("gui.docmod.apply"), (button -> {
            apply();
            this.client.getWindow().setTitle("DocMod " + DMUtils.VERSION + " " + DMUtils.CODENAME);
        })).dimensions(this.width / 2 +100, this.height / 4 + 48 + 80, 100, 20).build();
        addDrawableChild(cancelButton);
        addDrawableChild(applyButton);

        showVersionCheckbox = new CheckboxWidget(this.width / 2 -200, this.height / 4 + 48, 20, 20,  Text.translatable("gui.docmod.config.showversion"), DMConfig.Client.SHOW_VERSION);
        moddedTitleScreenCheckbox = new CheckboxWidget(this.width / 2 -200, this.height / 4 + 48 - 20, 20, 20,  Text.translatable("gui.docmod.config.custommainmenu"), DMConfig.Client.CUSTOM_TITLE_SCREEN);
        //showWIPContent = new Checkbox(this.width / 2 -200, this.height / 4 + 48 - 40, 20, 20, new TranslatableComponent("WIP Tab"), DocModConfig.showWIPTabs.get());
        showWidget = new CheckboxWidget(this.width / 2 -200, this.height / 4 + 48 - 40, 20, 20,  Text.translatable("gui.docmod.config.showwidget"), DMConfig.Client.SHOW_WIDGET);
        showThreeDHead = new CheckboxWidget(this.width / 2 -200, this.height / 4 + 48 - 60, 20, 20,  Text.translatable("gui.docmod.config.show3dhead"), DMConfig.Client.THREE_D_HEAD);
        showVersionIGCheckbox = new CheckboxWidget(this.width / 2 -200, this.height / 4 + 48 + 20, 20, 20,  Text.translatable("gui.docmod.config.showversionig"), DMConfig.Client.SHOW_VERSION_INGAME);
        addDrawableChild(showVersionCheckbox);
        addDrawableChild(moddedTitleScreenCheckbox);
        addDrawableChild(showWidget);
        addDrawableChild(showThreeDHead);
        addDrawableChild(showVersionIGCheckbox);
        // addDrawableChild(showWIPContent);

        super.init();
    }

    private void apply(){
        DMConfig.Client.SHOW_VERSION = showVersionCheckbox.isChecked();
        DMConfig.Client.CUSTOM_TITLE_SCREEN = moddedTitleScreenCheckbox.isChecked();
        DMConfig.Client.SHOW_WIDGET = showWidget.isChecked();
        DMConfig.Client.THREE_D_HEAD = showThreeDHead.isChecked();
        DMConfig.Client.SHOW_VERSION_INGAME = showVersionIGCheckbox.isChecked();
        DMConfig.Client.saveClient();
        close();
    }

    @Override
    public void close() {
        if(previousScreen != null){
            client.setScreen(previousScreen);
        }
        super.close();
    }
}
