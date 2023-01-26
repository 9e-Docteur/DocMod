package be.ninedocteur.docmod.client.gui.screens;

import be.ninedocteur.docmod.utils.DMUtils;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.ChatFormatting;
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
public class DocModChangelogScreen extends Screen {
    public static final CubeMap CUBE_MAP = new CubeMap(new ResourceLocation("textures/gui/title/background/panorama"));
    private static final ResourceLocation PANORAMA_OVERLAY = new ResourceLocation("textures/gui/title/background/panorama_overlay.png");
    private final PanoramaRenderer panorama = new PanoramaRenderer(CUBE_MAP);
    private final Screen previousScreen;
    private long fadeInStart;
    private int copyrightWidth;
    private int copyrightX;
    private Checkbox showVersionCheckbox, moddedTitleScreenCheckbox, showWIPContent, showWidget;

    public DocModChangelogScreen(Screen previousScreen) {
        super( Component.translatable("narrator.screen.title"));
        this.previousScreen = previousScreen;
    }

    public DocModChangelogScreen() {
        super( Component.translatable("narrator.screen.title"));
        this.previousScreen = null;
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pPoseStack);
        drawCenteredString(pPoseStack, font,  Component.literal("DocMod - 1.18/1.19 Thanks"), width/2, 10, 0xffffff);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        float f = (float)(Util.getMillis() - this.fadeInStart);
        float f1 = Mth.clamp(f - 1.0F, 0.0F, 1.0F);
        int l = Mth.ceil(f1 * 255.0F) << 24;


        drawCenteredString(pPoseStack, this.font, "DocMod and DocTeam created by 9e_Docteur", this.copyrightX + 225, this.height - 168, 16777215 | l);
        drawCenteredString(pPoseStack, this.font, "Textures by PandaRebel", this.copyrightX + 225, this.height - 158, 16777215 | l);
        drawCenteredString(pPoseStack, this.font, "Mod codded, updated, and fixed by 9e_Docteur, Josia, and Killar", this.copyrightX + 225, this.height - 148, 16777215 | l);
        drawCenteredString(pPoseStack, this.font, "Contributor: Balisto, Garatim, Robainks", this.copyrightX + 225, this.height - 138, 16777215 | l);
        drawCenteredString(pPoseStack, this.font, "Thanks to everyone who helped the project!", this.copyrightX+ 225, this.height -118, 16777215 | l);
        drawCenteredString(pPoseStack, this.font, ChatFormatting.BLACK + "Made "+ ChatFormatting.YELLOW + "In " + ChatFormatting.RED + "Belgium", this.copyrightX + 210, this.height - 88, 16777215 | l);
        //drawString(pPoseStack, this.font, DMUtils.getChangelog(), this.copyrightX, this.height - 200, 16777215 | l);
    }


    @Override
    public void renderDirtBackground(int pVOffset) {
    	super.renderDirtBackground(pVOffset);
    }

    @Override
    protected void init() {
        this.minecraft.getWindow().setTitle("DocMod " + DMUtils.VERSION + " " + DMUtils.CODENAME + " - Info");
        Button cancelButton = Button.builder(Component.translatable("Close"), (button -> {
            onClose();
        })).bounds(this.width / 2 -50, this.height / 4 + 58 + 80, 100, 20).build();
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
