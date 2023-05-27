package fr.ninedocteur.docmod.client.gui.screens;

import fr.ninedocteur.docmod.utils.DMUtils;
import net.minecraft.client.gui.CubeMapRenderer;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;

public class DocModChangelogScreen extends Screen {
    public static final CubeMapRenderer CUBE_MAP = new CubeMapRenderer(new Identifier("textures/gui/title/background/panorama"));
    private static final Identifier PANORAMA_OVERLAY = new Identifier("textures/gui/title/background/panorama_overlay.png");
    private final RotatingCubeMapRenderer panorama = new RotatingCubeMapRenderer(CUBE_MAP);
    private final Screen previousScreen;
    private long fadeInStart;
    private int copyrightWidth;
    private int copyrightX;
    private CheckboxWidget showVersionCheckbox, moddedTitleScreenCheckbox, showWIPContent, showWidget;

    public DocModChangelogScreen(Screen previousScreen) {
        super( Text.translatable("narrator.screen.title"));
        this.previousScreen = previousScreen;
    }

    public DocModChangelogScreen() {
        super( Text.translatable("narrator.screen.title"));
        this.previousScreen = null;
    }

    @Override
    public void render(MatrixStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pPoseStack);
        drawCenteredTextWithShadow(pPoseStack, textRenderer, Text.literal("DocMod - 1.18/1.19 Thanks"), width/2, 10, 0xffffff);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        float f = (float)(Util.getMeasuringTimeMs() - this.fadeInStart);
        float f1 = MathHelper.clamp(f - 1.0F, 0.0F, 1.0F);
        int l = MathHelper.ceil(f1 * 255.0F) << 24;


        drawCenteredTextWithShadow(pPoseStack, this.textRenderer, "DocMod and DocTeam created by 9e_Docteur", this.copyrightX + 225, this.height - 168, 16777215 | l);
        drawCenteredTextWithShadow(pPoseStack, this.textRenderer, "Textures by PandaRebel", this.copyrightX + 225, this.height - 158, 16777215 | l);
        drawCenteredTextWithShadow(pPoseStack, this.textRenderer, "Mod codded, updated, and fixed by 9e_Docteur, Josia, and Killar", this.copyrightX + 225, this.height - 148, 16777215 | l);
        drawCenteredTextWithShadow(pPoseStack, this.textRenderer, "Contributor: Balisto, Garatim, Robainks", this.copyrightX + 225, this.height - 138, 16777215 | l);
        drawCenteredTextWithShadow(pPoseStack, this.textRenderer, "Thanks to everyone who helped the project!", this.copyrightX+ 225, this.height -118, 16777215 | l);
        drawCenteredTextWithShadow(pPoseStack, this.textRenderer, Formatting.BLACK + "Made "+ Formatting.YELLOW + "In " + Formatting.RED + "Belgium", this.copyrightX + 210, this.height - 88, 16777215 | l);
        //drawString(pPoseStack, this.font, DMUtils.getChangelog(), this.copyrightX, this.height - 200, 16777215 | l);
    }


    @Override
    public void renderBackgroundTexture(MatrixStack p_265092_) {
    	// TODO Auto-generated method stub
    	super.renderBackgroundTexture(p_265092_);
    }

    @Override
    protected void init() {
        this.client.getWindow().setTitle("DocMod " + DMUtils.VERSION + " " + DMUtils.CODENAME + " - Info");
        ButtonWidget cancelButton = ButtonWidget.builder(Text.translatable("Close"), (button -> {
            close();
        })).dimensions(this.width / 2 -50, this.height / 4 + 58 + 80, 100, 20).build();
        addDrawableChild(cancelButton);

        super.init();
    }

    @Override
    public void close() {
        if(previousScreen != null){
            client.setScreen(previousScreen);
        }
        super.close();
    }
}
