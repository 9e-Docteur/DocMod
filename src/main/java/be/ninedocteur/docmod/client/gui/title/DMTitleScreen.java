package be.ninedocteur.docmod.client.gui.title;

import be.ninedocteur.docmod.DMConfig;
import be.ninedocteur.docmod.client.gui.screens.*;
import be.ninedocteur.docmod.utils.*;
import be.ninedocteur.docteam.Servers;
import com.google.common.util.concurrent.Runnables;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.realmsclient.RealmsMainScreen;
import com.mojang.realmsclient.gui.screens.RealmsNotificationsScreen;
import be.ninedocteur.docmod.DocMod;
import net.minecraft.ChatFormatting;
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
import net.minecraft.client.main.GameConfig;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.resolver.ServerAddress;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.PanoramaRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.ClientPackSource;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.packs.PackType;
import net.minecraft.util.Mth;
import net.minecraft.world.level.levelgen.WorldGenSettings;
import net.minecraft.world.level.storage.LevelStorageSource;
import net.minecraft.world.level.storage.LevelSummary;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.commons.lang3.JavaVersion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

@OnlyIn(Dist.CLIENT)
public class DMTitleScreen extends Screen {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final CubeMap CUBE_MAP = new CubeMap(new ResourceLocation("textures/gui/title/background/panorama"));
    private static final ResourceLocation PANORAMA_OVERLAY = new ResourceLocation("textures/gui/title/background/panorama_overlay.png");
    @Nullable
    private String splash;
    private Button resetDemoButton;
    private static final ResourceLocation MINECRAFT_LOGO = new ResourceLocation("textures/gui/title/minecraft.png");
    private static final ResourceLocation DOCMOD_LOGO = new ResourceLocation(DMUtils.MOD_ID, "textures/gui/title/docmodv3.png");
    private static final ResourceLocation MINECRAFT_EDITION = new ResourceLocation("textures/gui/title/edition.png");
    private Screen realmsNotificationsScreen;
    private int copyrightWidth;
    private int copyrightX;
    private PanoramaRenderer panorama = new PanoramaRenderer(CUBE_MAP);
    private long fadeInStart;
    private final boolean fading;
    private final boolean minceraftEasterEgg;
    private DMNotificationUpdateScreen modUpdateNotification;
    private Button infoButton, Quit, configButton, discordButton, youtubeButton, staffButton, closeButton, serverButton, dmServerButton;
    protected int leftPos;
    protected int topPos;
    private GameConfig pGameConfig;

    public DMTitleScreen() {
        this(false);
    }

    public DMTitleScreen(boolean pFading) {
        super(Component.translatable("narrator.screen.title"));
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
        return CompletableFuture.allOf(pTexMngr.preload(DOCMOD_LOGO, pBackgroundExecutor), pTexMngr.preload(MINECRAFT_EDITION, pBackgroundExecutor), pTexMngr.preload(PANORAMA_OVERLAY, pBackgroundExecutor), CUBE_MAP.preload(pTexMngr, pBackgroundExecutor));
    }

    public boolean isPauseScreen() {
        return false;
    }

    public boolean shouldCloseOnEsc() {
        return false;
    }


    protected void init() {
        if (this.splash == null) {
            this.splash = Minecraft.getInstance().getSplashManager().getSplash();
        }

        this.minecraft.getWindow().setTitle("DocMod " + DMUtils.VERSION + " " + DMUtils.CODENAME);
        preloadResources(this.minecraft.getTextureManager(), this.minecraft);
        DMSecondTitleScreen.preloadResources(this.minecraft.getTextureManager(), this.minecraft);

        this.copyrightWidth = this.font.width("Copyright Mojang AB. Do not distribute!");
        this.copyrightX = this.width - this.copyrightWidth - 2;
        int i = 24;
        int j = this.height / 4 + 48;
        Button modButton = null;
        if (this.minecraft.isDemo()) {
            this.createDemoMenuOptions(j, 24);
        } else {
            this.createNormalMenuOptions(j, 24);
        }
        modUpdateNotification = DMNotificationUpdateScreen.init(this, modButton);

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
                    DMTitleScreen.this.renderTooltip(p_169459_, DMTitleScreen.this.minecraft.font.split(this.text, Math.max(DMTitleScreen.this.width / 2 - 43, 170)), p_169460_, p_169461_);
                }

            }

            public void narrateTooltip(Consumer<Component> p_169456_) {
                p_169456_.accept(this.text);
            }
        };
        if (DMConfig.Client.showWidget.get()) {
            configButton = new ImageButton(this.width / 2 - 210, this.height / 4 + 48 -106, 16, 16, 0, 96, new ResourceLocation(DocMod.MOD_ID, "textures/gui/widgets.png"), (button -> {
                this.minecraft.setScreen(new DocModConfigScreen(this));
            }));
            discordButton = new ImageButton(this.width / 2 - 190, this.height / 4 + 48 -106, 16, 16, 0, 0, new ResourceLocation(DocMod.MOD_ID, "textures/gui/widgets.png"), (button -> {
                URLUtils.openLink(DMUtils.getDiscordLink());
            }));
            youtubeButton = new ImageButton(this.width / 2 - 170, this.height / 4 + 48 -106, 16, 16, 0, 64, new ResourceLocation(DocMod.MOD_ID, "textures/gui/widgets.png"), (button -> {
                URLUtils.openLink(DMUtils.getYoutubeLink());
            }));

            //closeButton = new ImageButton(this.width / 2 + 190, this.height / 4 + 48 -106, 16, 16, 0, 0, new ResourceLocation(DocMod.MOD_ID, "textures/gui/close.png"), (button -> {
                //this.minecraft.close();
            //}));

            staffButton = new ImageButton(this.width / 2 + 170, this.height / 4 + 48 -106, 16, 16, 0, 0, new ResourceLocation(DocMod.MOD_ID, "textures/gui/staff.png"), (button -> {
                ScreenUtils.openScreen(new DocModStaffScreen());
            }));

            serverButton = new ImageButton(this.width / 2 + 190, this.height / 4 + 48 -106, 16, 16, 0, 0, new ResourceLocation(DocMod.MOD_ID, "textures/gui/server.png"), (button -> {
                //ScreenUtils.openScreen(new DMCommunityMainScreen(this));
            }));

            infoButton = new ImageButton(this.width / 2 - 150, this.height / 4 + 48 -106, 16, 16, 0, 129, new ResourceLocation(DocMod.MOD_ID, "textures/gui/widgets.png"), (button -> {
                this.minecraft.setScreen(new DocModChangelogScreen(this));
            }));
/*
            singlePlayer = new ImageButton(this.width / 2 - 40, this.height / 4 + 48 -20, 21 *2 *2 + 4, 21 *2 *2 + 4, 0, 0, new ResourceLocation(DocMod.MOD_ID, "textures/gui/title/singleplayer.png"), (button -> {
                this.minecraft.setScreen(new SelectWorldScreen(this));
            }));

            multiPlayer = new ImageButton(this.width / 2 -140, this.height / 4 + 48 - 20, 21 *2 *2 + 4, 21 *2 *2 + 4, 0, 0, new ResourceLocation(DocMod.MOD_ID, "textures/gui/title/multiplayer.png"), (button -> {
                Screen screen = this.minecraft.options.skipMultiplayerWarning ? new JoinMultiplayerScreen(this) : new SafetyScreen(this);
                this.minecraft.setScreen(screen);
            }));

            Quit = new ImageButton(this.width / 2 + 60, this.height / 4 + 48- 20, 21 *2 *2 + 4, 21 *2 *2 + 4, 0, 0, new ResourceLocation(DocMod.MOD_ID, "textures/gui/title/options.png"), (button -> {
                this.minecraft.setScreen(new OptionsScreen(this, this.minecraft.options));
            }));
            this.addRenderableWidget(new ImageButton(this.width / 2 + 120, this.height / 4 + -60 + 120, 20 * 2 * 2 -0, 9 * 2 * 2 -0, 0, 0, new ResourceLocation(DocMod.MOD_ID, "textures/gui/title/next.png"), (p_96781_) -> {
                this.minecraft.setScreen(new DMSecondTitleScreen(this.passEvents));
            }));
            this.addRenderableWidget(new ImageButton(this.width / 2 - 200, this.height / 4 + -60 + 120, 20 * 2 * 2 -0, 9 * 2 * 2 -0, 0, 0, new ResourceLocation(DocMod.MOD_ID, "textures/gui/title/back.png"), (p_96781_) -> {

            }));

 */

           // Button ninedocteur = new ImageButton(this.width / 2 - 120, this.height / 4 + 48 + 20, 32, 32, 0, 0, new ResourceLocation(ImageIO.read()), (button -> {
              //  URLUtils.openLink(DMUtils.getYoutubeLink());
           // }));


            this.addRenderableWidget(discordButton);
            this.addRenderableWidget(youtubeButton);
            this.addRenderableWidget(configButton);
            this.addRenderableWidget(infoButton);
            this.addRenderableWidget(staffButton);
            this.addRenderableWidget(serverButton);

            //if(!LaunchUtils.isRunningInDev()){
              //  serverButton.active = false;
            //}
        }

        Quit = new Button(this.width / 2 - 100, p_96764_ + p_96765_ * 1, 200, 20, Component.translatable("menu.options"), (p_213094_1_) -> {
            this.minecraft.setScreen(new OptionsScreen(this, this.minecraft.options));
        });

        dmServerButton = new Button(this.width / 2 - 100, p_96764_ + p_96765_ * 0, 100, 20, Component.translatable("DocMod Servers"), (p_96781_) -> {
            ConnectScreen.startConnecting(this, this.getMinecraft(), new ServerAddress(Servers.HOST, Servers.PORT), new ServerData("DocMod Server", Servers.HOST, false));
        });

        this.addRenderableWidget(new Button(this.width / 2 - 100, p_96764_ + p_96765_ * 2, 200, 20, Component.translatable("menu.quit"), (p_96781_) -> {
            this.minecraft.stop();
        }));

        this.addRenderableWidget(new Button(this.width / 2 - 0, p_96764_ + p_96765_ * 0, 100, 20, Component.translatable("menu.multiplayer"), (p_96781_) -> {
            this.minecraft.setScreen(new JoinMultiplayerScreen(this));
        }));

        this.addRenderableWidget(new Button(this.width / 2 - 100, p_96764_ + p_96765_ * -1, 200, 20, Component.translatable("menu.singleplayer"), (p_96781_) -> {
            this.minecraft.setScreen(new SelectWorldScreen(this));
        }));

        this.addRenderableWidget(Quit);
        this.addRenderableWidget(dmServerButton);
        dmServerButton.active = true;


    }

    private void createDemoMenuOptions(int p_96773_, int p_96774_) {
        boolean flag = this.checkDemoWorldPresence();
        this.addRenderableWidget(new Button(this.width / 2 - 100, p_96773_, 200, 20, Component.translatable("menu.playdemo"), (p_169444_) -> {
            if (flag) {

            } else {
            }

        }));
        this.resetDemoButton = this.addRenderableWidget(new Button(this.width / 2 - 100, p_96773_ + p_96774_ * 1, 200, 20,  Component.translatable("menu.resetdemo"), (p_169441_) -> {
            LevelStorageSource levelstoragesource = this.minecraft.getLevelSource();

            try {
                LevelStorageSource.LevelStorageAccess levelstoragesource$levelstorageaccess = levelstoragesource.createAccess("Demo_World");

                try {
                    LevelSummary levelsummary = levelstoragesource$levelstorageaccess.getSummary();
                    if (levelsummary != null) {
                        this.minecraft.setScreen(new ConfirmScreen(this::confirmDemo,  Component.translatable("selectWorld.deleteQuestion"),  Component.translatable("selectWorld.deleteWarning", levelsummary.getLevelName()), Component.translatable("selectWorld.deleteButton"), CommonComponents.GUI_CANCEL));
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

    public void render(PoseStack p_96739_, int p_96740_, int p_96741_, float p_96742_) {
        float f = (float)(Util.getMillis() - this.fadeInStart);
        this.panorama.render(p_96742_, Mth.clamp(f, 0.0F, 1.0F));
        int i = 274;
        int j = this.width / 2 - 137;
        int k = 30;
        float f1 = Mth.clamp(f - 1.0F, 0.0F, 1.0F);
        //RenderSystem.setShader(GameRenderer::getPositionTexShader);
        //RenderSystem.setShaderTexture(0, DOCMOD_LOGO);
        //RenderSystem.setShaderTexture(0, MINECRAFT_LOGO);
        //RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1F);
        //blit(p_96739_, 67, 32, this.width, this.height, 0.0F, 0.0F, 800, 800, 700, 700);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, PANORAMA_OVERLAY);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, (float)Mth.ceil(Mth.clamp(f, 0.0F, 1.0F)));
        blit(p_96739_, 0, 0, this.width, this.height, 0.0F, 0.0F, 16, 128, 16, 128);
        int l = Mth.ceil(f1 * 255.0F) << 24;
        if ((l & -67108864) != 0) {
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, DOCMOD_LOGO);
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
                this.blitOutlineBlack(j - 18, 30, (p_96768_, p_96769_) -> {
                    this.blit(p_96739_, p_96768_ + 0, p_96769_, 0, 0, 155, 44);
                    this.blit(p_96739_, p_96768_ + 155, p_96769_, 0, 45, 155, 44);
                });
            }
/*
            //3D SKIN
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, PlayerUtils.get3DSkin(Minecraft.getInstance().getUser().getUuid()));
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            blit(p_96739_, width / 2 - 180, height / 2 - 50, 0, 0, 120/2, 270/2, 120 / 2, 270 / 2);

 */
            //RenderSystem.setShaderTexture(0, MINECRAFT_EDITION);
            //blit(p_96739_, j + 88, 67, 0.0F, 0.0F, 98, 14, 128, 16);
            if (this.splash != null) {
                p_96739_.pushPose();
                p_96739_.translate(this.width / 2 + 0, 80.0D, 0.0D);
                //p_96739_.mulPose(Vector3f.ZP.rotationDegrees(-20.0F));
                float f2 = 1.8F - Mth.abs(Mth.sin((float)(Util.getMillis() % 1000L) / 1000.0F * ((float)Math.PI * 2F)) * 0.1F);
                f2 = f2 * 95 / (float)(this.font.width(this.splash) + 32);
                p_96739_.scale(f2, f2, f2);
                //drawCenteredString(p_96739_, this.font, this.splash, 0, -8, 16776960 | l);
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

            drawString(p_96739_, this.font, DMUtils.getCopyright(), this.copyrightX + 10, this.height - 20, 16777215 | l);

            if(LaunchUtils.isBeta) {
                drawString(p_96739_, this.font, "This is a Beta. The mod can contains bugs.", this.copyrightX - 16, this.height - 30, ColorUtils.getRed() | l);
            }
            if(LaunchUtils.isPreRelease){
                drawString(p_96739_, this.font, "This is a Pre-Release. Somes things aren't ok.", this.copyrightX - 34, this.height - 30, ColorUtils.getYellow() | l);
            }
            drawString(p_96739_, this.font, DMUtils.getMinecraftVersion(), 0, this.height - 20, 16777215 | l);

            drawString(p_96739_, this.font, DMUtils.getDocTeam(), 0, this.height - 10, 16777215 | l);

            //drawString(p_96739_, this.font, "Page : 1 / " + TitleScreenUtils.getMaxTitleScreen(), singlePlayer.x + 14, this.height - 50, 16777215 | l);


            if(LaunchUtils.checkLaunchedVersion()){
                //drawString(p_96739_, this.font, ChatFormatting.GREEN + "DocMod Dev is activated", singlePlayer.x -14, this.height -40, 16777215 | l);
                MemoryUsage heapMemoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
                long maxMB = heapMemoryUsage.getMax()/(1024*1024);
                long usedMB = heapMemoryUsage.getUsed()/(1024*1024);
                final int MegaBytes = 10241024;
                drawString(p_96739_, this.font, "Java Runtime: " + usedMB + "MB/" + maxMB + "MB", 0, this.height - 30, 16777215 | l);
            }
            }
            
            

            if(DMConfig.Client.showVersion.get()) {
                drawCenteredString(p_96739_, font,  Component.translatable(DMUtils.MODNAME + " " + DMUtils.VERSION), width / 2, 10, 0xffffff);
                drawCenteredString(p_96739_, font,  Component.translatable("Codename : " + DMUtils.CODENAME), width / 2, 20, 0xffffff);
            }


            for(GuiEventListener guieventlistener : this.children()) {
                if (guieventlistener instanceof AbstractWidget) {
                    ((AbstractWidget)guieventlistener).setAlpha(f1);
                }
            }

            /*
            if(infoButton.isHoveredOrFocused()){
                this.drawString(p_96739_, font, "Changelog", infoButton.x - 50, infoButton.y, 0xffffff);
            }

             */
/*
            if(singlePlayer.isHoveredOrFocused()){
                drawString(p_96739_, font, "Singleplayer", singlePlayer.x + 14, singlePlayer.y +90, ColorUtils.getWhite());
            }

            if(multiPlayer.isHoveredOrFocused()){
                drawString(p_96739_, font, "Multiplayer", multiPlayer.x + 18, multiPlayer.y +90, ColorUtils.getWhite());
            }

            if(Quit.isHoveredOrFocused()){
                drawString(p_96739_, font, "Options", Quit.x + 28, Quit.y +90, ColorUtils.getWhite());
            }

            if(closeButton.isHoveredOrFocused()){
                this.renderTooltip(p_96739_, Component.translatable("gui.docmod.quit"), p_96740_, p_96741_);
            }

 */

        if(infoButton.isHoveredOrFocused()){
            this.renderTooltip(p_96739_, Component.translatable("gui.docmod.info"), p_96740_, p_96741_);
        }

        if(configButton.isHoveredOrFocused()){
            this.renderTooltip(p_96739_, Component.translatable("gui.docmod.config"), p_96740_, p_96741_);
        }

        if(youtubeButton.isHoveredOrFocused()){
            this.renderTooltip(p_96739_, Component.translatable("gui.docmod.youtube"), p_96740_, p_96741_);
        }

        if(discordButton.isHoveredOrFocused()){
            this.renderTooltip(p_96739_, Component.translatable("gui.docmod.discord"), p_96740_, p_96741_);
        }

        if(serverButton.isHoveredOrFocused()){
            this.renderTooltip(p_96739_, Component.literal(ChatFormatting.RED + "Coming Soon!"), p_96740_, p_96741_);
        }

        if(staffButton.isHoveredOrFocused()){
            this.renderTooltip(p_96739_, Component.literal( "DocTeam Staff"), p_96740_, p_96741_);
        }


            super.render(p_96739_, p_96740_, p_96741_, p_96742_);
            if (this.realmsNotificationsEnabled() && f1 >= 1.0F) {
                this.realmsNotificationsScreen.render(p_96739_, p_96740_, p_96741_, p_96742_);
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

//TODO MAKE A BUTTON ON GAME MENU FOR FEEDBACK
}
