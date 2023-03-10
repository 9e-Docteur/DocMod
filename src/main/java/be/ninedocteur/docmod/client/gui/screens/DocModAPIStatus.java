package be.ninedocteur.docmod.client.gui.screens;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DocModAPIStatus { //} extends Screen {
//    public static final CubeMap CUBE_MAP = new CubeMap(new ResourceLocation("textures/gui/title/background/panorama"));
//    private static final ResourceLocation PANORAMA_OVERLAY = new ResourceLocation("textures/gui/title/background/panorama_overlay.png");
//    private final PanoramaRenderer panorama = new PanoramaRenderer(CUBE_MAP);
//    private final Screen previousScreen;
//    private long fadeInStart;
//    private String apiStatus, websiteStatus;
//
//    public DocModAPIStatus(Screen previousScreen) {
//        super( Component.translatable("narrator.screen.title"));
//        this.previousScreen = previousScreen;
//        String uuid = Minecraft.getInstance().getUser().getUuid();
//        DocMod.LOGGER.info(Minecraft.getInstance().getUser().getName());
//    }
//
//    public DocModAPIStatus() {
//        super( Component.translatable("narrator.screen.title"));
//        this.previousScreen = null;
//    }
//
//    @Override
//    public void tick() {
//        super.tick();
//
//        if(System.currentTimeMillis() % 15000 == 0) {
//            apiStatus = WebsiteStatus.getAPIStatus();
//            websiteStatus = WebsiteStatus.getWebsiteStatus();
//        }
//    }
//
//    @Override
//    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
//        this.renderBackground(pPoseStack);
//        drawCenteredString(pPoseStack, font,  Component.translatable("DocTeam - DocTeam"), width / 2, 10, 0xffffff);
//        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
//        float f = (float) (Util.getMillis() - this.fadeInStart);
//        float f1 = Mth.clamp(f - 1.0F, 0.0F, 1.0F);
//        int l = Mth.ceil(f1 * 255.0F) << 24;
//
//        User user = Minecraft.getInstance().getUser();
//
//
//        if(apiStatus.equals("DOWN")){
//            RenderSystem.setShader(GameRenderer::getPositionTexShader);
//            RenderSystem.setShaderTexture(0, OnlineAssets.getEmptyStatus());
//            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
//            blit(pPoseStack, width / 2 - 50, height / 2 - 0, 0, 0, 16, 16, 16, 16);
//            drawString(pPoseStack, font,  Component.translatable(ChatFormatting.RED + "API : Down"), width / 2 - 30, height / 2 + 4, 0xffffff);
//        }else if(apiStatus.equals("OK")){
//            RenderSystem.setShader(GameRenderer::getPositionTexShader);
//            RenderSystem.setShaderTexture(0, OnlineAssets.getOnlineStatus());
//            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
//            blit(pPoseStack, width / 2 - 50, height / 2 - 0, 0, 0, 16, 16, 16, 16);
//            drawString(pPoseStack, font,  Component.translatable(ChatFormatting.GREEN + "API : Online"), width / 2 - 30, height / 2 + 4, 0xffffff);
//        }else {
//            RenderSystem.setShader(GameRenderer::getPositionTexShader);
//            RenderSystem.setShaderTexture(0, OnlineAssets.getEmptyStatus());
//            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
//            blit(pPoseStack, width / 2 - 50, height / 2 - 0, 0, 0, 16, 16, 16, 16);
//            drawString(pPoseStack, font,  Component.translatable(ChatFormatting.WHITE + "API : Status Unavailable"), width / 2 - 30, height / 2 + 4, 0xffffff);
//        }
//
//        if(websiteStatus.equals("DOWN")){
//            RenderSystem.setShader(GameRenderer::getPositionTexShader);
//            RenderSystem.setShaderTexture(0, OnlineAssets.getEmptyStatus());
//            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
//            blit(pPoseStack, width / 2 - 50, height / 2 - 17, 0, 0, 16, 16, 16, 16);
//            drawString(pPoseStack, font,  Component.translatable(ChatFormatting.RED + "Website : Down"), width / 2 - 30, height / 2 - 13, 0xffffff);
//        }else if(websiteStatus.equals("OK")){
//            RenderSystem.setShader(GameRenderer::getPositionTexShader);
//            RenderSystem.setShaderTexture(0, OnlineAssets.getOnlineStatus());
//            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
//            blit(pPoseStack, width / 2 - 50, height / 2 - 17, 0, 0, 16, 16, 16, 16);
//            drawString(pPoseStack, font,  Component.translatable(ChatFormatting.GREEN + "Website : Online"), width / 2 - 30, height / 2 - 13, 0xffffff);
//        }else {
//            RenderSystem.setShader(GameRenderer::getPositionTexShader);
//            RenderSystem.setShaderTexture(0, OnlineAssets.getEmptyStatus());
//            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
//            blit(pPoseStack, width / 2 - 50, height / 2 - 17, 0, 0, 16, 16, 16, 16);
//            drawString(pPoseStack, font,  Component.translatable(ChatFormatting.WHITE + "Website : Status Unavailable"), width / 2 - 30, height / 2 - 13, 0xffffff);
//        }
//    }
//
//
//
//    @Override
//    public void renderDirtBackground(int pVOffset) {
//        super.renderDirtBackground(pVOffset);
//    }
//
//    @Override
//    protected void init() {
//        apiStatus = WebsiteStatus.getAPIStatus();
//        websiteStatus = WebsiteStatus.getWebsiteStatus();
//        this.minecraft.getWindow().setTitle("DocMod " + DMUtils.VERSION + " " + DMUtils.CODENAME + " - Player Info");
//        Button cancelButton = new Button(this.width / 2 -50, this.height / 4 + 58 + 80, 100, 20,  Component.translatable("Close"), (button -> {
//            onClose();
//        }));
//        Button playerScreen = new Button(this.width / 2 - 210, this.height / 4 + 58 - 90, 140, 20,  Component.translatable("Player"), (button -> {
//            this.minecraft.setScreen(new DocModPlayerScreen (this));
//        }));
//        Button docteamAPIStatus = new Button(this.width / 2 -70, this.height / 4 + 58 -90, 140, 20,  Component.translatable("DocTeam APIs"), (button -> {
//
//        }));
//        Button staff = new Button(this.width / 2 +70, this.height / 4 + 58 -90, 140, 20,  Component.translatable("Staff"), (button -> {
//            this.minecraft.setScreen(new DocModStaffScreen(this));
//        }));
//        addRenderableWidget(cancelButton);
//        addRenderableWidget(playerScreen);
//        addRenderableWidget(docteamAPIStatus);
//        addRenderableWidget(staff);
//        super.init();
//    }
//
//    @Override
//    public void onClose() {
//        if(previousScreen != null){
//            minecraft.setScreen(previousScreen);
//        }
//        super.onClose();
//    }
}
