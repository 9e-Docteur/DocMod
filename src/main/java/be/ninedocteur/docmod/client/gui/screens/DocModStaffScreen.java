package be.ninedocteur.docmod.client.gui.screens;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.DMConfig;
import be.ninedocteur.docmod.utils.DMUtils;
import be.ninedocteur.docmod.utils.PlayerUtils;
import be.ninedocteur.docmod.utils.StaffUUIDs;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.User;
import net.minecraft.client.gui.components.Button;
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
public class DocModStaffScreen extends Screen {
    public static final CubeMap CUBE_MAP = new CubeMap(new ResourceLocation("textures/gui/title/background/panorama"));
    private static final ResourceLocation PANORAMA_OVERLAY = new ResourceLocation("textures/gui/title/background/panorama_overlay.png");
    private final PanoramaRenderer panorama = new PanoramaRenderer(CUBE_MAP);
    private final Screen previousScreen;
    private long fadeInStart;

    public DocModStaffScreen(Screen previousScreen) {
        super(Component.translatable("narrator.screen.title"));
        this.previousScreen = previousScreen;
        String uuid = Minecraft.getInstance().getUser().getUuid();
        DocMod.LOGGER.info(Minecraft.getInstance().getUser().getName());
    }

    public DocModStaffScreen() {
        super(Component.translatable("narrator.screen.title"));
        this.previousScreen = null;
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pPoseStack);
        drawCenteredString(pPoseStack, font, Component.translatable("DocTeam - DocTeam"), width / 2, 10, 0xffffff);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        float f = (float) (Util.getMillis() - this.fadeInStart);
        float f1 = Mth.clamp(f - 1.0F, 0.0F, 1.0F);
        int l = Mth.ceil(f1 * 255.0F) << 24;

        User user = Minecraft.getInstance().getUser();

        if(DMConfig.Client.ThreeDHead.get()) {
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, PlayerUtils.get3DPlayerHead(StaffUUIDs.NINEDOCTEUR));
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            blit(pPoseStack, width / 2 - 200, height / 2 -60, 0, 0, 70, 64, 70, 64);
            drawString(pPoseStack, font, Component.translatable(ChatFormatting.GOLD + "Name: " + ChatFormatting.WHITE + "9e_Docteur"), width / 2 - 200, height / 2 + 8, 0xffffff);
            drawString(pPoseStack, font, Component.translatable(ChatFormatting.GOLD + "Rank: " + ChatFormatting.RED + "Founder"), width / 2 - 200, height / 2 + 18, 0xffffff);

            //PANDA
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, PlayerUtils.get3DPlayerHead(StaffUUIDs.PANDA));
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            blit(pPoseStack, width / 2 - 30, height / 2 -60, 0, 0, 70, 64, 70, 64);
            drawString(pPoseStack, font, Component.translatable(ChatFormatting.GOLD + "Name: " + ChatFormatting.WHITE + "PandaRebel4307"), width / 2 - 50, height / 2 + 8, 0xffffff);
            drawString(pPoseStack, font, Component.translatable(ChatFormatting.GOLD + "Rank: " + ChatFormatting.RED + "Admin" + ChatFormatting.WHITE + ", " + ChatFormatting.DARK_PURPLE + "Assets"), width / 2 - 50, height / 2 + 18, 0xffffff);

            //JOSIA
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, PlayerUtils.get3DPlayerHead(StaffUUIDs.JOSIA));
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            blit(pPoseStack, width / 2 + 120, height / 2 -60, 0, 0, 70, 64, 70, 64);
            drawString(pPoseStack, font, Component.translatable(ChatFormatting.GOLD + "Name: " + ChatFormatting.WHITE + "Josia50"), width / 2 + 120, height / 2 + 8, 0xffffff);
            drawString(pPoseStack, font, Component.translatable(ChatFormatting.GOLD + "Rank: " + ChatFormatting.RED + "Admin" + ChatFormatting.WHITE + ", " + ChatFormatting.AQUA + "Developer"), width / 2 + 120, height / 2 + 18, 0xffffff);

            //KILLAR
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, PlayerUtils.get3DPlayerHead(StaffUUIDs.KILLAR));
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            blit(pPoseStack, width / 2 - 30, height / 2 + 30, 0, 0, 70, 64, 70, 64);
            drawString(pPoseStack, font, Component.translatable(ChatFormatting.GOLD + "Name: " + ChatFormatting.WHITE + "Neoxos"), width / 2 - 30, height / 2 + 100, 0xffffff);
            drawString(pPoseStack, font, Component.translatable(ChatFormatting.GOLD + "Rank: " + ChatFormatting.RED + "Admin" + ChatFormatting.WHITE + ", " + ChatFormatting.AQUA + "Developer"), width / 2 - 30, height / 2 + 110, 0xffffff);

        }else{
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, PlayerUtils.getPlayerHead("0f471df5-e6e4-4bf3-9f22-35c2d7e6a4bb"));
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            blit(pPoseStack, width / 2 - 200, height / 2 -60, 0, 0, 64, 64, 64, 64);
            drawString(pPoseStack, font, Component.translatable(ChatFormatting.GOLD + "Name: " + ChatFormatting.WHITE + "9e_Docteur"), width / 2 - 200, height / 2 + 8, 0xffffff);
            drawString(pPoseStack, font, Component.translatable(ChatFormatting.GOLD + "Rank: " + ChatFormatting.RED + "Founder"), width / 2 - 200, height / 2 + 18, 0xffffff);

            //PANDA
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, PlayerUtils.getPlayerHead("45949380-580d-4dd3-8526-6ee05dd75c22"));
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            blit(pPoseStack, width / 2 - 30, height / 2 -60, 0, 0, 64, 64, 64, 64);
            drawString(pPoseStack, font, Component.translatable(ChatFormatting.GOLD + "Name: " + ChatFormatting.WHITE + "PandaRebel4307"), width / 2 - 50, height / 2 + 8, 0xffffff);
            drawString(pPoseStack, font, Component.translatable(ChatFormatting.GOLD + "Rank: " + ChatFormatting.RED + "Admin" + ChatFormatting.WHITE + ", " + ChatFormatting.DARK_PURPLE + "Assets"), width / 2 - 50, height / 2 + 18, 0xffffff);

            //JOSIA
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, PlayerUtils.getPlayerHead("a0b89882-8509-42b2-921b-14facddfb5dc"));
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            blit(pPoseStack, width / 2 + 120, height / 2 -60, 0, 0, 64, 64, 64, 64);
            drawString(pPoseStack, font, Component.translatable(ChatFormatting.GOLD + "Name: " + ChatFormatting.WHITE + "Josia50"), width / 2 + 120, height / 2 + 8, 0xffffff);
            drawString(pPoseStack, font, Component.translatable(ChatFormatting.GOLD + "Rank: " + ChatFormatting.RED + "Admin" + ChatFormatting.WHITE + ", " + ChatFormatting.AQUA + "Developer"), width / 2 + 120, height / 2 + 18, 0xffffff);

            //KILLAR
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, PlayerUtils.getPlayerHead("5ff408b5-8851-4303-afe8-e14f3e91c613"));
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            blit(pPoseStack, width / 2 - 30, height / 2 + 30, 0, 0, 64, 64, 64, 64);
            drawString(pPoseStack, font, Component.translatable(ChatFormatting.GOLD + "Name: " + ChatFormatting.WHITE + "Neoxos"), width / 2 - 30, height / 2 + 100, 0xffffff);
            drawString(pPoseStack, font, Component.translatable(ChatFormatting.GOLD + "Rank: " + ChatFormatting.RED + "Admin" + ChatFormatting.WHITE + ", " + ChatFormatting.AQUA + "Developer"), width / 2 - 30, height / 2 + 110, 0xffffff);

        }
    }



    @Override
    public void renderDirtBackground(int pVOffset) {
        super.renderDirtBackground(pVOffset);
    }

    @Override
    protected void init() {
        this.minecraft.getWindow().setTitle("DocMod " + DMUtils.VERSION + " " + DMUtils.CODENAME + " - Player Info");
        Button playerScreen = Button.builder(Component.translatable("Player"), (button -> {
            this.minecraft.setScreen(new DocModPlayerScreen (this));
        })).bounds(this.width / 2 - 210, this.height / 4 + 58 - 90, 140, 20).build();
        Button docteamAPIStatus = Button.builder(Component.translatable("DocTeam APIs"), (button -> {

        })).bounds(this.width / 2 -70, this.height / 4 + 58 -90, 140, 20).build();
        Button staff = Button.builder(Component.translatable("Staff"), (button -> {

        })).bounds(this.width / 2 +70, this.height / 4 + 58 -90, 140, 20).build();
        addRenderableWidget(playerScreen);
        addRenderableWidget(docteamAPIStatus);
        addRenderableWidget(staff);
        docteamAPIStatus.active = false;
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
