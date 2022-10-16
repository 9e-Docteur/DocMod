package be.ninedocteur.docmod.client.gui.screens;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.DMConfig;
import be.ninedocteur.docmod.utils.*;
import be.ninedocteur.docteam.website.Database;
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
public class DocModPlayerScreen extends Screen {
    public static final CubeMap CUBE_MAP = new CubeMap(new ResourceLocation("textures/gui/title/background/panorama"));
    private static final ResourceLocation PANORAMA_OVERLAY = new ResourceLocation("textures/gui/title/background/panorama_overlay.png");
    private final PanoramaRenderer panorama = new PanoramaRenderer(CUBE_MAP);
    private final Screen previousScreen;
    private long fadeInStart;
    private ResourceLocation headResource;
    private String betarankDir = Database.getBetaRankDir();

    public DocModPlayerScreen(Screen previousScreen) {
        super( Component.translatable("narrator.screen.title"));
        this.previousScreen = previousScreen;
        String uuid = Minecraft.getInstance().getUser().getUuid();
        DocMod.LOGGER.info(Minecraft.getInstance().getUser().getName());
    }

    public DocModPlayerScreen() {
        super( Component.translatable("narrator.screen.title"));
        this.previousScreen = null;
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pPoseStack);
        drawCenteredString(pPoseStack, font,  Component.translatable("DocTeam - Player Info"), width / 2, 10, 0xffffff);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        float f = (float) (Util.getMillis() - this.fadeInStart);
        float f1 = Mth.clamp(f - 1.0F, 0.0F, 1.0F);
        int l = Mth.ceil(f1 * 255.0F) << 24;

        User user = Minecraft.getInstance().getUser();

        drawCenteredString(pPoseStack, font,  Component.translatable(ChatFormatting.GOLD + "Name: " + ChatFormatting.WHITE + user.getName()), width / 2, height / 2 + 2, 0xffffff);

        TeamUtils.TeamMember teamMember = TeamUtils.getTeamMembers().get(user.getUuid());
        /*
        drawCenteredString(pPoseStack, font, new TranslatableComponent(ChatFormatting.GOLD + "Member Type: " + ChatFormatting.WHITE +
                (teamMember == null ? "Player" : teamMember.getTitles())), width / 2, height / 2 + 12, 0xffffff);

 */

        AwardUtils.Award award = AwardUtils.getAward().get(user.getUuid());
        drawCenteredString(pPoseStack, font, Component.translatable(ChatFormatting.GOLD + "Award(s): " + ChatFormatting.WHITE +
                (award == null ? ChatFormatting.RED + "No Awards" : award.getAwardName())), width / 2, height / 2 + 22, 0xffffff);


        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, headResource);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        if (DMConfig.Client.ThreeDHead.get()) {
            blit(pPoseStack, width / 2 - 32, height / 2 - 60, 0, 0, 60, 55, 60, 55);
        } else {
            blit(pPoseStack, width / 2 - 32, height / 2 - 64, 0, 0, 64, 64, 64, 64);
        }

        if (teamMember != null){
            drawCenteredString(pPoseStack, font,  Component.translatable(ChatFormatting.GOLD + "Member Type: " + teamMember.getTitles()), width / 2, height / 2 + 12, 0xffffff);
        }else if (betarankDir.contains(Minecraft.getInstance().getUser().getUuid())) {
            drawCenteredString(pPoseStack, font,  Component.translatable(ChatFormatting.GOLD + "Member Type: " + ChatFormatting.BLUE + "BETA"), width / 2, height / 2 + 12, 0xffffff);
        } else {
            drawCenteredString(pPoseStack, font,  Component.translatable(ChatFormatting.GOLD + "Member Type :"+ ChatFormatting.WHITE + "Player" + ChatFormatting.WHITE), width / 2, height / 2 + 12, 0xffffff);
        }


/*
        if(IOUtils.readURLContent("http://www.docteamwebsite.tk/modinfoio/rank/beta/").equals(Minecraft.getInstance().getUser().getUuid())){
            drawCenteredString(pPoseStack, font, new TranslatableComponent(ChatFormatting.GOLD + "Member Type: " + ChatFormatting.BLUE + "BETA", width / 2, height / 2 + 12, 0xffffff);
        }else{
            drawCenteredString(pPoseStack, font, new TranslatableComponent(ChatFormatting.GOLD + "Member Type: " + ChatFormatting.WHITE +
                    (teamMember.getTitles())), width / 2, height / 2 + 12, 0xffffff);
        }

 */
    }


    @Override
    public void renderDirtBackground(int pVOffset) {
        super.renderDirtBackground(pVOffset);
    }

    @Override
    protected void init() {
        this.minecraft.getWindow().setTitle("DocMod " + DMUtils.VERSION + " " + DMUtils.CODENAME + " - Player Info");
        Button cancelButton = new Button(this.width / 2 -50, this.height / 4 + 58 + 80, 100, 20,  Component.translatable("Close"), (button -> {
            onClose();
        }));
        Button playerScreen = new Button(this.width / 2 - 210, this.height / 4 + 58 - 90, 140, 20,  Component.translatable("Player"), (button -> {

        }));
        Button docteamAPIStatus = new Button(this.width / 2 -70, this.height / 4 + 58 -90, 140, 20,  Component.translatable("DocTeam APIs"), (button -> {
            this.minecraft.setScreen(new DocModAPIStatus(this));
        }));
        Button staff = new Button(this.width / 2 +70, this.height / 4 + 58 -90, 140, 20,  Component.translatable("Staff"), (button -> {
            this.minecraft.setScreen(new DocModStaffScreen(this));
        }));
        addRenderableWidget(cancelButton);
        addRenderableWidget(playerScreen);
        addRenderableWidget(docteamAPIStatus);
        addRenderableWidget(staff);
        docteamAPIStatus.active = false;
        super.init();
        headResource = DMConfig.Client.ThreeDHead.get() ?
                PlayerUtils.get3DPlayerHead(Minecraft.getInstance().getUser().getUuid())
                : PlayerUtils.getPlayerHead(Minecraft.getInstance().getUser().getName());
        betarankDir = Database.getBetaRankDir();
    }

    @Override
    public void onClose() {
        if(previousScreen != null){
            minecraft.setScreen(previousScreen);
        }
        super.onClose();
    }
}
