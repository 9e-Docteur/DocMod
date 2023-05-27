package fr.ninedocteur.docmod.client.gui.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import fr.ninedocteur.docmod.DMConfig;
import fr.ninedocteur.docmod.DocMod;
import fr.ninedocteur.docmod.utils.AwardUtils;
import fr.ninedocteur.docmod.utils.DMUtils;
import fr.ninedocteur.docmod.utils.PlayerUtils;
import fr.ninedocteur.docmod.utils.TeamUtils;
import fr.ninedocteur.docteam.website.Database;
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

public class DocModPlayerScreen extends Screen {
    public static final CubeMapRenderer CUBE_MAP = new CubeMapRenderer(new Identifier("textures/gui/title/background/panorama"));
    private static final Identifier PANORAMA_OVERLAY = new Identifier("textures/gui/title/background/panorama_overlay.png");
    private final RotatingCubeMapRenderer panorama = new RotatingCubeMapRenderer(CUBE_MAP);
    private final Screen previousScreen;
    private long fadeInStart;
    private Identifier headResource;
    private String betarankDir = Database.getBetaRankDir();

    public DocModPlayerScreen(Screen previousScreen) {
        super( Text.translatable("narrator.screen.title"));
        this.previousScreen = previousScreen;
        String uuid = MinecraftClient.getInstance().getSession().getUuid();
        DocMod.LOGGER.info(MinecraftClient.getInstance().getSession().getUsername());
    }

    public DocModPlayerScreen() {
        super( Text.translatable("narrator.screen.title"));
        this.previousScreen = null;
    }

    @Override
    public void render(MatrixStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pPoseStack);
        drawCenteredTextWithShadow(pPoseStack, textRenderer,  Text.translatable("DocTeam - Player Info"), width / 2, 10, 0xffffff);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        float f = (float) (Util.getMeasuringTimeMs() - this.fadeInStart);
        float f1 = MathHelper.clamp(f - 1.0F, 0.0F, 1.0F);
        int l = MathHelper.ceil(f1 * 255.0F) << 24;

        Session user = MinecraftClient.getInstance().getSession();

        drawCenteredTextWithShadow(pPoseStack, textRenderer, Text.translatable(Formatting.GOLD + "Name: " + Formatting.WHITE + user.getUsername()), width / 2, height / 2 + 2, 0xffffff);

        TeamUtils.TeamMember teamMember = TeamUtils.getTeamMembers().get(user.getUuid());
        /*
        drawCenteredString(pPoseStack, font, new TranslatableComponent(Formatting.GOLD + "Member Type: " + Formatting.WHITE +
                (teamMember == null ? "Player" : teamMember.getTitles())), width / 2, height / 2 + 12, 0xffffff);

 */

        AwardUtils.Award award = AwardUtils.getAward().get(user.getUuid());
        drawCenteredTextWithShadow(pPoseStack, textRenderer, Text.translatable(Formatting.GOLD + "Award(s): " + Formatting.WHITE +
                (award == null ? Formatting.RED + "No Awards" : award.getAwardName())), width / 2, height / 2 + 22, 0xffffff);


        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderTexture(0, headResource);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        if (DMConfig.Client.THREE_D_HEAD) {
            drawTexture(pPoseStack, width / 2 - 32, height / 2 - 60, 0, 0, 60, 55, 60, 55);
        } else {
            drawTexture(pPoseStack, width / 2 - 32, height / 2 - 64, 0, 0, 64, 64, 64, 64);
        }

        if (teamMember != null){
            drawCenteredTextWithShadow(pPoseStack, textRenderer,  Text.translatable(Formatting.GOLD + "Member Type: " + teamMember.getTitles()), width / 2, height / 2 + 12, 0xffffff);
        }else if (betarankDir.contains(MinecraftClient.getInstance().getSession().getUuid())) {
            drawCenteredTextWithShadow(pPoseStack, textRenderer,  Text.translatable(Formatting.GOLD + "Member Type: " + Formatting.BLUE + "BETA"), width / 2, height / 2 + 12, 0xffffff);
        } else {
            drawCenteredTextWithShadow(pPoseStack, textRenderer,  Text.translatable(Formatting.GOLD + "Member Type :"+ Formatting.WHITE + "Player" + Formatting.WHITE), width / 2, height / 2 + 12, 0xffffff);
        }


/*
        if(IOUtils.readURLContent("http://www.docteamwebsite.tk/modinfoio/rank/beta/").equals(Minecraft.getInstance().getUser().getUuid())){
            drawCenteredString(pPoseStack, font, new TranslatableComponent(Formatting.GOLD + "Member Type: " + Formatting.BLUE + "BETA", width / 2, height / 2 + 12, 0xffffff);
        }else{
            drawCenteredString(pPoseStack, font, new TranslatableComponent(Formatting.GOLD + "Member Type: " + Formatting.WHITE +
                    (teamMember.getTitles())), width / 2, height / 2 + 12, 0xffffff);
        }

 */
    }


    @Override
    public void renderBackgroundTexture(MatrixStack p_265092_) {
    	// TODO Auto-generated method stub
    	super.renderBackgroundTexture(p_265092_);
    }

    @Override
    protected void init() {
        this.client.getWindow().setTitle("DocMod " + DMUtils.VERSION + " " + DMUtils.CODENAME + " - Player Info");
        ButtonWidget cancelButton = ButtonWidget.builder(Text.translatable("Close"), (button -> {
            close();
        })).dimensions(this.width / 2 -50, this.height / 4 + 58 + 80, 100, 20).build();
        ButtonWidget playerScreen = ButtonWidget.builder(Text.translatable("Player"), (button -> {

        })).dimensions(this.width / 2 - 210, this.height / 4 + 58 - 90, 140, 20).build();
        ButtonWidget docteamAPIStatus = ButtonWidget.builder(Text.translatable("DocTeam APIs"), (button -> {
            //this.minecraft.setScreen(new DocModAPIStatus(this));
        })).dimensions(this.width / 2 -70, this.height / 4 + 58 -90, 140, 20).build();
        ButtonWidget staff = ButtonWidget.builder(Text.translatable("Staff"), (button -> {
            this.client.setScreen(new DocModStaffScreen(this));
        })).dimensions(this.width / 2 +70, this.height / 4 + 58 -90, 140, 20).build();
        addDrawableChild(cancelButton);
        addDrawableChild(playerScreen);
        addDrawableChild(docteamAPIStatus);
        addDrawableChild(staff);
        docteamAPIStatus.active = false;
        super.init();
        headResource = DMConfig.Client.THREE_D_HEAD ?
                PlayerUtils.get3DPlayerHead(MinecraftClient.getInstance().getSession().getUuid())
                : PlayerUtils.getPlayerHead(MinecraftClient.getInstance().getSession().getUsername());
        betarankDir = Database.getBetaRankDir();
    }

    @Override
    public void close() {
        if(previousScreen != null){
            client.setScreen(previousScreen);
        }
        super.close();
    }
}
