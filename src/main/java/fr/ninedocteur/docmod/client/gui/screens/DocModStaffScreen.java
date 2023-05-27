package fr.ninedocteur.docmod.client.gui.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import fr.ninedocteur.docmod.DMConfig;
import fr.ninedocteur.docmod.DocMod;
import fr.ninedocteur.docmod.utils.DMUtils;
import fr.ninedocteur.docmod.utils.PlayerUtils;
import fr.ninedocteur.docmod.utils.StaffUUIDs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.CubeMapRenderer;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.Session;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;

public class DocModStaffScreen extends Screen {
    public static final CubeMapRenderer CUBE_MAP = new CubeMapRenderer(new Identifier("textures/gui/title/background/panorama"));
    private static final Identifier PANORAMA_OVERLAY = new Identifier("textures/gui/title/background/panorama_overlay.png");
    private final RotatingCubeMapRenderer panorama = new RotatingCubeMapRenderer(CUBE_MAP);
    private final Screen previousScreen;
    private long fadeInStart;

    public DocModStaffScreen(Screen previousScreen) {
        super(Text.translatable("narrator.screen.title"));
        this.previousScreen = previousScreen;
        String uuid = MinecraftClient.getInstance().getSession().getUuid();
        DocMod.LOGGER.info(MinecraftClient.getInstance().getSession().getUsername());
    }

    public DocModStaffScreen() {
        super(Text.translatable("narrator.screen.title"));
        this.previousScreen = null;
    }

    @Override
    public void render(MatrixStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pPoseStack);
        drawCenteredTextWithShadow(pPoseStack, textRenderer, Text.translatable("DocTeam - DocTeam"), width / 2, 10, 0xffffff);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        float f = (float) (Util.getMeasuringTimeMs() - this.fadeInStart);
        float f1 = MathHelper.clamp(f - 1.0F, 0.0F, 1.0F);
        int l = MathHelper.ceil(f1 * 255.0F) << 24;

        Session user = MinecraftClient.getInstance().getSession();

        if(DMConfig.Client.THREE_D_HEAD) {
            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
            RenderSystem.setShaderTexture(0, PlayerUtils.get3DPlayerHead(StaffUUIDs.NINEDOCTEUR));
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            drawTexture(pPoseStack, width / 2 - 200, height / 2 -60, 0, 0, 70, 64, 70, 64);
            drawTextWithShadow(pPoseStack, textRenderer, Text.translatable(Formatting.GOLD + "Name: " + Formatting.WHITE + "9e_Docteur"), width / 2 - 200, height / 2 + 8, 0xffffff);
            drawTextWithShadow(pPoseStack, textRenderer, Text.translatable(Formatting.GOLD + "Rank: " + Formatting.RED + "Founder"), width / 2 - 200, height / 2 + 18, 0xffffff);

            //PANDA
            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
            RenderSystem.setShaderTexture(0, PlayerUtils.get3DPlayerHead(StaffUUIDs.PANDA));
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            drawTexture(pPoseStack, width / 2 - 30, height / 2 -60, 0, 0, 70, 64, 70, 64);
            drawTextWithShadow(pPoseStack, textRenderer, Text.translatable(Formatting.GOLD + "Name: " + Formatting.WHITE + "PandaRebel4307"), width / 2 - 50, height / 2 + 8, 0xffffff);
            drawTextWithShadow(pPoseStack, textRenderer, Text.translatable(Formatting.GOLD + "Rank: " + Formatting.RED + "Admin" + Formatting.WHITE + ", " + Formatting.DARK_PURPLE + "Assets"), width / 2 - 50, height / 2 + 18, 0xffffff);

            //JOSIA
            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
            RenderSystem.setShaderTexture(0, PlayerUtils.get3DPlayerHead(StaffUUIDs.JOSIA));
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            drawTexture(pPoseStack, width / 2 + 120, height / 2 -60, 0, 0, 70, 64, 70, 64);
            drawTextWithShadow(pPoseStack, textRenderer, Text.translatable(Formatting.GOLD + "Name: " + Formatting.WHITE + "Josia50"), width / 2 + 120, height / 2 + 8, 0xffffff);
            drawTextWithShadow(pPoseStack, textRenderer, Text.translatable(Formatting.GOLD + "Rank: " + Formatting.RED + "Admin" + Formatting.WHITE + ", " + Formatting.AQUA + "Developer"), width / 2 + 120, height / 2 + 18, 0xffffff);

            //KILLAR
            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
            RenderSystem.setShaderTexture(0, PlayerUtils.get3DPlayerHead(StaffUUIDs.KILLAR));
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            drawTexture(pPoseStack, width / 2 - 30, height / 2 + 30, 0, 0, 70, 64, 70, 64);
            drawTextWithShadow(pPoseStack, textRenderer, Text.translatable(Formatting.GOLD + "Name: " + Formatting.WHITE + "Neoxos"), width / 2 - 30, height / 2 + 100, 0xffffff);
            drawTextWithShadow(pPoseStack, textRenderer, Text.translatable(Formatting.GOLD + "Rank: " + Formatting.RED + "Admin" + Formatting.WHITE + ", " + Formatting.AQUA + "Developer"), width / 2 - 30, height / 2 + 110, 0xffffff);

        }else{
            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
            RenderSystem.setShaderTexture(0, PlayerUtils.getPlayerHead("0f471df5-e6e4-4bf3-9f22-35c2d7e6a4bb"));
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            drawTexture(pPoseStack, width / 2 - 200, height / 2 -60, 0, 0, 64, 64, 64, 64);
            drawTextWithShadow(pPoseStack, textRenderer, Text.translatable(Formatting.GOLD + "Name: " + Formatting.WHITE + "9e_Docteur"), width / 2 - 200, height / 2 + 8, 0xffffff);
            drawTextWithShadow(pPoseStack, textRenderer, Text.translatable(Formatting.GOLD + "Rank: " + Formatting.RED + "Founder"), width / 2 - 200, height / 2 + 18, 0xffffff);

            //PANDA
            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
            RenderSystem.setShaderTexture(0, PlayerUtils.getPlayerHead("45949380-580d-4dd3-8526-6ee05dd75c22"));
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            drawTexture(pPoseStack, width / 2 - 30, height / 2 -60, 0, 0, 64, 64, 64, 64);
            drawTextWithShadow(pPoseStack, textRenderer, Text.translatable(Formatting.GOLD + "Name: " + Formatting.WHITE + "PandaRebel4307"), width / 2 - 50, height / 2 + 8, 0xffffff);
            drawTextWithShadow(pPoseStack, textRenderer, Text.translatable(Formatting.GOLD + "Rank: " + Formatting.RED + "Admin" + Formatting.WHITE + ", " + Formatting.DARK_PURPLE + "Assets"), width / 2 - 50, height / 2 + 18, 0xffffff);

            //JOSIA
            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
            RenderSystem.setShaderTexture(0, PlayerUtils.getPlayerHead("a0b89882-8509-42b2-921b-14facddfb5dc"));
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            drawTexture(pPoseStack, width / 2 + 120, height / 2 -60, 0, 0, 64, 64, 64, 64);
            drawTextWithShadow(pPoseStack, textRenderer, Text.translatable(Formatting.GOLD + "Name: " + Formatting.WHITE + "Josia50"), width / 2 + 120, height / 2 + 8, 0xffffff);
            drawTextWithShadow(pPoseStack, textRenderer, Text.translatable(Formatting.GOLD + "Rank: " + Formatting.RED + "Admin" + Formatting.WHITE + ", " + Formatting.AQUA + "Developer"), width / 2 + 120, height / 2 + 18, 0xffffff);

            //KILLAR
            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
            RenderSystem.setShaderTexture(0, PlayerUtils.getPlayerHead("5ff408b5-8851-4303-afe8-e14f3e91c613"));
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            drawTexture(pPoseStack, width / 2 - 30, height / 2 + 30, 0, 0, 64, 64, 64, 64);
            drawTextWithShadow(pPoseStack, textRenderer, Text.translatable(Formatting.GOLD + "Name: " + Formatting.WHITE + "Neoxos"), width / 2 - 30, height / 2 + 100, 0xffffff);
            drawTextWithShadow(pPoseStack, textRenderer, Text.translatable(Formatting.GOLD + "Rank: " + Formatting.RED + "Admin" + Formatting.WHITE + ", " + Formatting.AQUA + "Developer"), width / 2 - 30, height / 2 + 110, 0xffffff);

        }
    }



    @Override
    public void renderBackgroundTexture(MatrixStack p_265092_) {
    	// TODO Auto-generated method stub
    	super.renderBackgroundTexture(p_265092_);
    }

    @Override
    protected void init() {
        this.client.getWindow().setTitle("DocMod " + DMUtils.VERSION + " " + DMUtils.CODENAME + " - Player Info");
        ButtonWidget playerScreen = ButtonWidget.builder(Text.translatable("Player"), (button -> {
            this.client.setScreen(new DocModPlayerScreen (this));
        })).dimensions(this.width / 2 - 210, this.height / 4 + 58 - 90, 140, 20).build();
        ButtonWidget docteamAPIStatus = ButtonWidget.builder(Text.translatable("DocTeam APIs"), (button -> {

        })).dimensions(this.width / 2 -70, this.height / 4 + 58 -90, 140, 20).build();
        ButtonWidget staff = ButtonWidget.builder(Text.translatable("Staff"), (button -> {

        })).dimensions(this.width / 2 +70, this.height / 4 + 58 -90, 140, 20).build();
        addDrawableChild(playerScreen);
        addDrawableChild(docteamAPIStatus);
        addDrawableChild(staff);
        docteamAPIStatus.active = false;
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
