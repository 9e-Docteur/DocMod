package be.ninedocteur.docmod.client.gui.title;

import be.ninedocteur.docmod.DMConfig;
import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.gui.screens.*;
import be.ninedocteur.docmod.utils.ColorUtils;
import be.ninedocteur.docmod.utils.DMUtils;
import be.ninedocteur.docmod.utils.PlayerUtils;
import be.ninedocteur.docmod.utils.URLUtils;
import com.google.common.util.concurrent.Runnables;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.realmsclient.RealmsMainScreen;
import com.mojang.realmsclient.gui.screens.RealmsNotificationsScreen;
import net.minecraft.SharedConstants;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.client.gui.screens.*;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.multiplayer.SafetyScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.PanoramaRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mth;
import net.minecraft.world.level.levelgen.WorldGenSettings;
import net.minecraft.world.level.storage.LevelStorageSource;
import net.minecraft.world.level.storage.LevelSummary;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

@OnlyIn(Dist.CLIENT)
public class DMBetaTitleScreen  extends TitleScreen {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final CubeMap CUBE_MAP = new CubeMap(new ResourceLocation(DMUtils.MOD_ID, "textures/gui/title/background/panorama"));
    private static final ResourceLocation PANORAMA_OVERLAY = new ResourceLocation("textures/gui/title/background/panorama_overlay.png");
    @Nullable
    private String splash;
    private Button resetDemoButton;
    private static final ResourceLocation MINECRAFT_LOGO = new ResourceLocation(DMUtils.MOD_ID, "textures/gui/title/docmod.png");
    private static final ResourceLocation MINECRAFT_EDITION = new ResourceLocation("textures/gui/title/edition.png");
    private Screen realmsNotificationsScreen;
    private int copyrightWidth;
    private int copyrightX;
    private PanoramaRenderer panorama = new PanoramaRenderer(CUBE_MAP);
    private long fadeInStart;
    private final boolean fading;
    private final boolean minceraftEasterEgg;
    private DMNotificationUpdateScreen modUpdateNotification;
    private Button infoButton;
    protected int leftPos;
    protected int topPos;

    public DMBetaTitleScreen() {
        this(false);
    }

    public DMBetaTitleScreen(boolean pFading) {
        this.panorama = new PanoramaRenderer(CUBE_MAP);
        this.fading = pFading;
        this.minceraftEasterEgg = (double)(new Random()).nextFloat() < 1.0E-4D;
        //renderEntityInInventory(this.leftPos, this.topPos, 20, (float)(this.leftPos), (float)(this.topPos), Minecraft.getInstance().player);
    }
    private boolean realmsNotificationsEnabled() {
        return this.realmsNotificationsScreen != null;
    }

    public void tick() {
        if (this.realmsNotificationsEnabled()) {
            this.realmsNotificationsScreen.tick();
        }

    }

    public static CompletableFuture<Void> preloadResources(TextureManager pTexMngr, Executor pBackgroundExecutor) {
        return CompletableFuture.allOf(pTexMngr.preload(MINECRAFT_LOGO, pBackgroundExecutor), pTexMngr.preload(MINECRAFT_EDITION, pBackgroundExecutor), pTexMngr.preload(PANORAMA_OVERLAY, pBackgroundExecutor), CUBE_MAP.preload(pTexMngr, pBackgroundExecutor));
    }

    public boolean isPauseScreen() {
        return false;
    }

    public boolean shouldCloseOnEsc() {
        return false;
    }

    protected void init() {
        if (this.splash == null) {
            this.splash = this.minecraft.getSplashManager().getSplash();
        }

        this.minecraft.getWindow().setTitle("DocMod " + DMUtils.VERSION + " " + DMUtils.CODENAME);
        //InputStream inputstream = this.getClientPackSource().getVanillaPack().getResource(PackType.CLIENT_RESOURCES, new ResourceLocation("icons/icon_16x16.png"));
        //InputStream inputstream1 = this.getClientPackSource().getVanillaPack().getResource(PackType.CLIENT_RESOURCES, new ResourceLocation("icons/icon_32x32.png"));
        // this.minecraft.getWindow().setIcon();
        preloadResources(this.minecraft.getTextureManager(), this.minecraft);

        this.copyrightWidth = this.font.width("Copyright Mojang AB. Do not distribute!");
        this.copyrightX = this.width - this.copyrightWidth - 2;
        int i = 24;
        int j = this.height / 4 + 48;
        Button modButton = null;
        if (this.minecraft.isDemo()) {
            this.createDemoMenuOptions(j, 24);
        } else {
            this.createNormalMenuOptions(j, 24);
            //modButton = this.addRenderableWidget(new Button(this.width / 2 - 100, j + 24 * 2, 98, 20, Component.translatable("fml.menu.mods"), button -> {
            //   this.minecraft.setScreen(new net.minecraftforge.client.gui.ModListScreen(this));
            // }));
        }

        this.minecraft.setConnectedToRealms(false);
        if (this.realmsNotificationsScreen == null) {
            this.realmsNotificationsScreen = new RealmsNotificationsScreen();
        }

        if (this.realmsNotificationsEnabled()) {
            this.realmsNotificationsScreen.init(this.minecraft, this.width, this.height);
        }
    }

    private void createNormalMenuOptions(int p_96764_, int p_96765_) {
        boolean flag = this.minecraft.allowsMultiplayer();
        Button.OnTooltip button$ontooltip = flag ? Button.NO_TOOLTIP : new Button.OnTooltip() {
            private final Component text = Component.translatable("title.multiplayer.disabled");

            public void onTooltip(Button p_169458_, PoseStack p_169459_, int p_169460_, int p_169461_) {
                if (!p_169458_.active) {
                    DMBetaTitleScreen.this.renderTooltip(p_169459_, DMBetaTitleScreen.this.minecraft.font.split(this.text, Math.max(DMBetaTitleScreen.this.width / 2 - 43, 170)), p_169460_, p_169461_);
                }

            }

            public void narrateTooltip(Consumer<Component> p_169456_) {
                p_169456_.accept(this.text);
            }
        };
        this.addRenderableWidget(new Button(this.width / 2 - 100, p_96764_ + p_96765_ * -1, 200, 20, Component.translatable("menu.singleplayer"), (p_96781_) -> {
            this.minecraft.setScreen(new SelectWorldScreen(this));
        }));
        this.addRenderableWidget(new Button(this.width / 2 - 100, p_96764_ + p_96765_ * 1, 200, 20, Component.translatable("fml.menu.mods"), button -> {
            this.minecraft.setScreen(new net.minecraftforge.client.gui.ModListScreen(this));
        }));
        this.addRenderableWidget(new Button(this.width / 2 - 100, p_96764_ + p_96765_ * 2, 200, 20, Component.translatable("menu.options"), (p_96788_) -> {
            this.minecraft.setScreen(new OptionsScreen(this, this.minecraft.options));
        }));
        (this.addRenderableWidget(new Button(this.width / 2 - 100, p_96764_ + p_96765_ * 0, 98, 20, Component.translatable("menu.multiplayer"), (p_169450_) -> {
            Screen screen = this.minecraft.options.skipMultiplayerWarning ? new JoinMultiplayerScreen(this) : new SafetyScreen(this);
            this.minecraft.setScreen(screen);
        }, button$ontooltip))).active = flag;
        (this.addRenderableWidget(new Button(this.width / 2 + 2, p_96764_ + p_96765_ * 0, 98, 20, Component.translatable("DocMod Server"), (p_96771_) -> {
            // ConnectScreen.startConnecting(this, this.getMinecraft(), new ServerAddress(DMUtils.getPublicServerHost(), DMUtils.getPublicServerPort()), new ServerData("DocMod Server", Servers.HOST, false));
            this.minecraft.setScreen(new DocModServersSelection(this));
        }, button$ontooltip))).active = flag;
        (this.addRenderableWidget(new Button(this.width / 2 - 100, p_96764_ + p_96765_ * 3, 200, 20, Component.translatable("Quit game"), (p_96771_) -> {
            this.minecraft.stop();
        }, button$ontooltip))).active = flag;

        if (DMConfig.Client.showWidget.get()) {
            Button configButton = new ImageButton(this.width / 2 - 120, this.height / 4 + 48 + 60, 16, 16, 0, 96, new ResourceLocation(DocMod.MOD_ID, "textures/gui/widgets.png"), (button -> {
                this.minecraft.setScreen(new DocModConfigScreen(this));
            }));
            Button discordButton = new ImageButton(this.width / 2 - 120, this.height / 4 + 48 + 40, 16, 16, 0, 0, new ResourceLocation(DocMod.MOD_ID, "textures/gui/widgets.png"), (button -> {
                URLUtils.openLink(DMUtils.getDiscordLink());
            }));
            Button youtubeButton = new ImageButton(this.width / 2 - 120, this.height / 4 + 48 + 20, 16, 16, 0, 64, new ResourceLocation(DocMod.MOD_ID, "textures/gui/widgets.png"), (button -> {
                URLUtils.openLink(DMUtils.getYoutubeLink());
            }));

            infoButton = new ImageButton(this.width / 2 - 120, this.height / 4 + 48 + 0, 16, 16, 0, 129, new ResourceLocation(DocMod.MOD_ID, "textures/gui/widgets.png"), (button -> {
                this.minecraft.setScreen(new DocModChangelogScreen(this));
            }));

            this.addRenderableWidget(new Button(this.width / 2 + 160, this.height / 4 + -60 + 0, 50, 20, Component.translatable("DocTeam"), (p_96781_) -> {
                this.minecraft.setScreen(new DocModPlayerScreen(this));
            }));

            // Button ninedocteur = new ImageButton(this.width / 2 - 120, this.height / 4 + 48 + 20, 32, 32, 0, 0, new ResourceLocation(ImageIO.read()), (button -> {
            //  URLUtils.openLink(DMUtils.getYoutubeLink());
            // }));
            this.addRenderableWidget(discordButton);
            this.addRenderableWidget(youtubeButton);
            this.addRenderableWidget(configButton);
            this.addRenderableWidget(infoButton);
            //this.addRenderableWidget(ninedocteur);

        }
    }

    private void createDemoMenuOptions(int p_96773_, int p_96774_) {
        boolean flag = this.checkDemoWorldPresence();
        this.addRenderableWidget(new Button(this.width / 2 - 100, p_96773_, 200, 20, Component.translatable("menu.playdemo"), (p_169444_) -> {
            if (flag) {

            } else {
            }

        }));
        this.resetDemoButton = this.addRenderableWidget(new Button(this.width / 2 - 100, p_96773_ + p_96774_ * 1, 200, 20, Component.translatable("menu.resetdemo"), (p_169441_) -> {
            LevelStorageSource levelstoragesource = this.minecraft.getLevelSource();

            try {
                LevelStorageSource.LevelStorageAccess levelstoragesource$levelstorageaccess = levelstoragesource.createAccess("Demo_World");

                try {
                    LevelSummary levelsummary = levelstoragesource$levelstorageaccess.getSummary();
                    if (levelsummary != null) {
                        this.minecraft.setScreen(new ConfirmScreen(this::confirmDemo, Component.translatable("selectWorld.deleteQuestion"), Component.translatable("selectWorld.deleteWarning", levelsummary.getLevelName()), Component.translatable("selectWorld.deleteButton"), CommonComponents.GUI_CANCEL));
                    }
                } catch (Throwable throwable1) {
                    if (levelstoragesource$levelstorageaccess != null) {
                        try {
                            levelstoragesource$levelstorageaccess.close();
                        } catch (Throwable throwable) {
                            throwable1.addSuppressed(throwable);
                        }
                    }

                    throw throwable1;
                }

                if (levelstoragesource$levelstorageaccess != null) {
                    levelstoragesource$levelstorageaccess.close();
                }
            } catch (IOException ioexception) {
                SystemToast.onWorldAccessFailure(this.minecraft, "Demo_World");
                LOGGER.warn("Failed to access demo world", ioexception);
            }

        }));
        this.resetDemoButton.active = flag;
    }

    private boolean checkDemoWorldPresence() {
        try {
            LevelStorageSource.LevelStorageAccess levelstoragesource$levelstorageaccess = this.minecraft.getLevelSource().createAccess("Demo_World");

            boolean flag;
            try {
                flag = levelstoragesource$levelstorageaccess.getSummary() != null;
            } catch (Throwable throwable1) {
                if (levelstoragesource$levelstorageaccess != null) {
                    try {
                        levelstoragesource$levelstorageaccess.close();
                    } catch (Throwable throwable) {
                        throwable1.addSuppressed(throwable);
                    }
                }

                throw throwable1;
            }

            if (levelstoragesource$levelstorageaccess != null) {
                levelstoragesource$levelstorageaccess.close();
            }

            return flag;
        } catch (IOException ioexception) {
            SystemToast.onWorldAccessFailure(this.minecraft, "Demo_World");
            LOGGER.warn("Failed to read demo world data", ioexception);
            return false;
        }
    }

    private void realmsButtonClicked() {
        this.minecraft.setScreen(new RealmsMainScreen(this));
    }

    public void render(PoseStack p_96739_, int p_96740_, int p_96741_, float p_96742_) {

        float f = (float)(Util.getMillis() - this.fadeInStart);
        this.panorama.render(p_96742_, Mth.clamp(f, 0.0F, 1.0F));
        int i = 274;
        int j = this.width / 2 - 137;
        int k = 30;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, PANORAMA_OVERLAY);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, (float)Mth.ceil(Mth.clamp(f, 0.0F, 1.0F)));
        blit(p_96739_, 0, 0, this.width, this.height, 0.0F, 0.0F, 16, 128, 16, 128);
        float f1 = Mth.clamp(f - 1.0F, 0.0F, 1.0F);
        int l = Mth.ceil(f1 * 255.0F) << 24;
        if ((l & -67108864) != 0) {
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, MINECRAFT_LOGO);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, f1);
            //blit(p_96739_, 0, 0, this.width, this.height, 0.0F, 0.0F, 16, 128, 16, 128);
            if (this.minceraftEasterEgg) {
                this.blitOutlineBlack(j, 30, (p_96768_, p_96769_) -> {
                    this.blit(p_96739_, p_96768_ + 0, p_96769_, 0, 0, 99, 44);
                    this.blit(p_96739_, p_96768_ + 99, p_96769_, 129, 0, 27, 44);
                    this.blit(p_96739_, p_96768_ + 99 + 26, p_96769_, 126, 0, 3, 44);
                    this.blit(p_96739_, p_96768_ + 99 + 26 + 3, p_96769_, 99, 0, 26, 44);
                    this.blit(p_96739_, p_96768_ + 155, p_96769_, 0, 45, 155, 44);
                });
            } else {
                this.blitOutlineBlack(j, 30, (p_96768_, p_96769_) -> {
                    this.blit(p_96739_, p_96768_ + 0, p_96769_, 0, 0, 155, 44);
                    this.blit(p_96739_, p_96768_ + 155, p_96769_, 0, 45, 155, 44);
                });
            }

            //3D SKIN
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, PlayerUtils.get3DSkin(Minecraft.getInstance().getUser().getUuid()));
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            blit(p_96739_, width / 2 + 120, height / 2 - 50, 0, 0, 120/2, 270/2, 120 / 2, 270 / 2);

            //RenderSystem.setShaderTexture(0, MINECRAFT_EDITION);
            //blit(p_96739_, j + 88, 67, 0.0F, 0.0F, 98, 14, 128, 16);
            if (this.splash != null) {
                p_96739_.pushPose();
                p_96739_.translate(this.width / 2 + 0, 80.0D, 0.0D);
                //p_96739_.mulPose(Vector3f.ZP.rotationDegrees(-20.0F));
                float f2 = 1.8F - Mth.abs(Mth.sin((float)(Util.getMillis() % 1000L) / 1000.0F * ((float)Math.PI * 2F)) * 0.1F);
                f2 = f2 * 95 / (float)(this.font.width(this.splash) + 32);
                p_96739_.scale(f2, f2, f2);
                drawCenteredString(p_96739_, this.font, this.splash, 0, -8, 16776960 | l);
                p_96739_.popPose();
            }

            String s = "Minecraft " + SharedConstants.getCurrentVersion().getName();
            if (this.minecraft.isDemo()) {
                s = s + " Demo";
            } else {
                s = s + ("release".equalsIgnoreCase(this.minecraft.getVersionType()) ? "" : "/" + this.minecraft.getVersionType());
            }

            if (Minecraft.checkModStatus().shouldReportAsModified()) {
                s = s + I18n.get("menu.modded");
            }

            net.minecraftforge.internal.BrandingControl.forEachAboveCopyrightLine((brdline, brd) ->
                    drawString(p_96739_, this.font, brd, this.width - font.width(brd), this.height - (10 + (brdline + 1) * ( this.font.lineHeight + 1)), 16777215 | l)
            );

            drawString(p_96739_, this.font, "Copyright Mojang AB. Do not distribute!", this.copyrightX, this.height - 10, 16777215 | l);
            if (p_96740_ > this.copyrightX && p_96740_ < this.copyrightX + this.copyrightWidth && p_96741_ > this.height - 10 && p_96741_ < this.height) {
                fill(p_96739_, this.copyrightX, this.height - 1, this.copyrightX + this.copyrightWidth, this.height, 16777215 | l);
            }

            drawString(p_96739_, this.font, DMUtils.getCopyright(), this.copyrightX + 10, this.height - 20, 16777215 | l);
            if (p_96740_ > this.copyrightX && p_96740_ < this.copyrightX + this.copyrightWidth && p_96741_ > this.height - 10 && p_96741_ < this.height) {
                fill(p_96739_, this.copyrightX, this.height - 10, this.copyrightX + this.copyrightWidth, this.height, 16777215 | l);
            }

            drawString(p_96739_, this.font, "PRIVATE BETA BUILD. DO NOT SHARE.", this.copyrightX + 20, this.height - 30, ColorUtils.getRed() | l);

            drawString(p_96739_, this.font, DMUtils.getMinecraftVersion(), 0, this.height - 20, 16777215 | l);
            if (p_96740_ > this.copyrightX && p_96740_ < this.copyrightX + this.copyrightWidth && p_96741_ > this.height + 30 && p_96741_ < this.height) {
                fill(p_96739_, this.copyrightX, this.height + 20, this.copyrightX + this.copyrightWidth, this.height, 16777215 | l);
            }

            drawString(p_96739_, this.font, DMUtils.getDocTeam(), 0, this.height - 10, 16777215 | l);
            if (p_96740_ > this.copyrightX && p_96740_ < this.copyrightX + this.copyrightWidth && p_96741_ > this.height + 30 && p_96741_ < this.height) {
                fill(p_96739_, this.copyrightX, this.height + 20, this.copyrightX + this.copyrightWidth, this.height, 16777215 | l);
            }



            if(DMConfig.Client.showVersion.get()) {
                drawCenteredString(p_96739_, font, Component.translatable(DMUtils.MODNAME + " " + DMUtils.VERSION), width / 2, 10, 0xffffff);
                drawCenteredString(p_96739_, font, Component.translatable("Codename : " + DMUtils.CODENAME), width / 2, 20, 0xffffff);
            }


            for(GuiEventListener guieventlistener : this.children()) {
                if (guieventlistener instanceof AbstractWidget) {
                    ((AbstractWidget)guieventlistener).setAlpha(f1);
                }
            }

            if(infoButton.isHoveredOrFocused()){
                drawString(p_96739_, font, "Changelog", infoButton.x - 50, infoButton.y, 0xffffff);
            }


            super.render(p_96739_, p_96740_, p_96741_, p_96742_);
            if (this.realmsNotificationsEnabled() && f1 >= 1.0F) {
                this.realmsNotificationsScreen.render(p_96739_, p_96740_, p_96741_, p_96742_);
            }

        }
    }
/*
    public static LivingEntity livingEntity;
    public static void renderEntityInInventory(int pPosX, int pPosY, int pScale, float pMouseX, float pMouseY, LivingEntity pLivingEntity) {
        float f = (float)Math.atan((double)(pMouseX / 40.0F));
        float f1 = (float)Math.atan((double)(pMouseY / 40.0F));
        PoseStack posestack = RenderSystem.getModelViewStack();
        posestack.pushPose();
        posestack.translate((double)pPosX, (double)pPosY, 1050.0D);
        posestack.scale(1.0F, 1.0F, -1.0F);
        RenderSystem.applyModelViewMatrix();
        PoseStack posestack1 = new PoseStack();
        posestack1.translate(0.0D, 0.0D, 1000.0D);
        posestack1.scale((float)pScale, (float)pScale, (float)pScale);
        Quaternion quaternion = Vector3f.ZP.rotationDegrees(180.0F);
        Quaternion quaternion1 = Vector3f.XP.rotationDegrees(f1 * 20.0F);
        quaternion.mul(quaternion1);
        posestack1.mulPose(quaternion);
        float f2 = livingEntity.yBodyRot;
        float f3 = livingEntity.getYRot();
        float f4 = livingEntity.getXRot();
        float f5 = livingEntity.yHeadRotO;
        float f6 = livingEntity.yHeadRot;
        livingEntity.yBodyRot = 180.0F + f * 20.0F;
        livingEntity.setYRot(180.0F + f * 40.0F);
        livingEntity.setXRot(-f1 * 20.0F);
        livingEntity.yHeadRot = livingEntity.getYRot();
        livingEntity.yHeadRotO = livingEntity.getYRot();
        Lighting.setupForEntityInInventory();
        EntityRenderDispatcher entityrenderdispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        quaternion1.conj();
        entityrenderdispatcher.overrideCameraOrientation(quaternion1);
        entityrenderdispatcher.setRenderShadow(false);
        MultiBufferSource.BufferSource multibuffersource$buffersource = Minecraft.getInstance().renderBuffers().bufferSource();
        RenderSystem.runAsFancy(() -> {
            entityrenderdispatcher.render(livingEntity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, posestack1, multibuffersource$buffersource, 15728880);
        });
        multibuffersource$buffersource.endBatch();
        entityrenderdispatcher.setRenderShadow(true);
        livingEntity.yBodyRot = f2;
        livingEntity.setYRot(f3);
        livingEntity.setXRot(f4);
        livingEntity.yHeadRotO = f5;
        livingEntity.yHeadRot = f6;
        posestack.popPose();
        RenderSystem.applyModelViewMatrix();
        Lighting.setupFor3DItems();
    }

 */




    public boolean mouseClicked(double p_96735_, double p_96736_, int p_96737_) {
        if (super.mouseClicked(p_96735_, p_96736_, p_96737_)) {
            return true;
        } else if (this.realmsNotificationsEnabled() && this.realmsNotificationsScreen.mouseClicked(p_96735_, p_96736_, p_96737_)) {
            return true;
        } else {
            if (p_96735_ > (double)this.copyrightX && p_96735_ < (double)(this.copyrightX + this.copyrightWidth) && p_96736_ > (double)(this.height - 10) && p_96736_ < (double)this.height) {
                this.minecraft.setScreen(new WinScreen(false, Runnables.doNothing()));
            }

            return false;
        }
    }

    public void removed() {
        if (this.realmsNotificationsScreen != null) {
            this.realmsNotificationsScreen.removed();
        }

    }

    private void confirmDemo(boolean p_96778_) {
        if (p_96778_) {
            try {
                LevelStorageSource.LevelStorageAccess levelstoragesource$levelstorageaccess = this.minecraft.getLevelSource().createAccess("Demo_World");

                try {
                    levelstoragesource$levelstorageaccess.deleteLevel();
                } catch (Throwable throwable1) {
                    if (levelstoragesource$levelstorageaccess != null) {
                        try {
                            levelstoragesource$levelstorageaccess.close();
                        } catch (Throwable throwable) {
                            throwable1.addSuppressed(throwable);
                        }
                    }

                    throw throwable1;
                }

                if (levelstoragesource$levelstorageaccess != null) {
                    levelstoragesource$levelstorageaccess.close();
                }
            } catch (IOException ioexception) {
                SystemToast.onWorldDeleteFailure(this.minecraft, "Demo_World");
                LOGGER.warn("Failed to delete demo world", ioexception);
            }
        }

        this.minecraft.setScreen(this);
    }
}
