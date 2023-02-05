package fr.ninedocteur.docmod.client.screens;

import com.google.common.util.concurrent.Runnables;
import com.mojang.authlib.minecraft.BanDetails;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.DstFactor;
import com.mojang.blaze3d.platform.GlStateManager.SrcFactor;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.logging.LogUtils;

import java.awt.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import fr.ninedocteur.docmod.DocMod;
import fr.ninedocteur.docmod.utils.DMUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.CubeMapRenderer;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.client.gui.screen.ConnectScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerWarningScreen;
import net.minecraft.client.gui.screen.option.AccessibilityOptionsScreen;
import net.minecraft.client.gui.screen.option.LanguageOptionsScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.realms.gui.screen.RealmsMainScreen;
import net.minecraft.client.realms.gui.screen.RealmsNotificationsScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.GeneratorOptions;
import net.minecraft.world.gen.WorldPresets;
import net.minecraft.world.level.storage.LevelStorage;
import net.minecraft.world.level.storage.LevelSummary;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

@Environment(EnvType.CLIENT)
public class DMTitleScreen extends Screen {
    private static final Logger LOGGER =DocMod.LOGGER;
    public static final CubeMapRenderer CUBE_MAP = new CubeMapRenderer(new Identifier("textures/gui/title/background/panorama"));
    private static final Identifier PANORAMA_OVERLAY = new Identifier("textures/gui/title/background/panorama_overlay.png");
    @Nullable
    private String splash;
    private ButtonWidget resetDemoButtonWidget;
    private static final Identifier MINECRAFT_LOGO = new Identifier("textures/gui/title/minecraft.png");
    private static final Identifier DOCMOD_LOGO = new Identifier(DocMod.MOD_ID, "textures/gui/title/docmodv3.png");
    private static final Identifier MINECRAFT_EDITION = new Identifier("textures/gui/title/edition.png");
    private Screen realmsNotificationsScreen;
    private int copyrightWidth;
    private int copyrightX;
    private final RotatingCubeMapRenderer backgroundRenderer = new RotatingCubeMapRenderer(CUBE_MAP);
    private boolean doBackgroundFade;
    private long backgroundFadeStart;
    private final boolean minceraftEasterEgg;
    private ButtonWidget infoButtonWidget, Quit, configButtonWidget, discordButtonWidget, youtubeButtonWidget, staffButtonWidget, closeButtonWidget, serverButtonWidget, dmServerButtonWidget;
    protected int leftPos;
    protected int topPos;
    MinecraftClient minecraft  = MinecraftClient.getInstance();

    public DMTitleScreen() {
        super(Text.translatable("narrator.screen.title"));
        this.doBackgroundFade = doBackgroundFade;
        this.minceraftEasterEgg = (double)Random.create().nextFloat() < 1.0E-4;
        //renderEntityInInventory(this.leftPos, this.topPos, 20, (float)(this.leftPos), (float)(this.topPos), Minecraft.getInstance().player);
    }
    private boolean realmsNotificationsEnabled() {
        return this.realmsNotificationsScreen != null;
    }

    public void tick() {
        if (this.realmsNotificationsEnabled()) {
            this.realmsNotificationsScreen.tick();
        }
        MinecraftClient.getInstance().getWindow().setTitle("Minecraft " + SharedConstants.getGameVersion().getName() + " | " + DocMod.MODNAME + " " + DocMod.VERSION);
        //LaunchUtils.initWindowIcon(new Identifier(DocMod.MOD_ID, "icons/icon16x16.png"), new Identifier(DocMod.MOD_ID, "icons/icon32x32.png"));
    }

    public static CompletableFuture<Void> loadTextureAsyncResources(TextureManager pTexMngr, Executor pBackgroundExecutor) {
        return CompletableFuture.allOf(pTexMngr.loadTextureAsync(DOCMOD_LOGO, pBackgroundExecutor), pTexMngr.loadTextureAsync(MINECRAFT_EDITION, pBackgroundExecutor), pTexMngr.loadTextureAsync(PANORAMA_OVERLAY, pBackgroundExecutor), CUBE_MAP.loadTexturesAsync(pTexMngr, pBackgroundExecutor));
    }

    public boolean isPauseScreen() {
        return false;
    }

    public boolean shouldCloseOnEsc() {
        return false;
    }


    protected void init() {
        if (this.splash == null) {
            this.splash = MinecraftClient.getInstance().getSplashTextLoader().get();
        }
        
        loadTextureAsyncResources(MinecraftClient.getInstance().getTextureManager(), minecraft);

        this.copyrightWidth = this.textRenderer.getWidth("Copyright Mojang AB. Do not distribute!");
        this.copyrightX = this.width - this.copyrightWidth - 2;
        int i = 24;
        int j = this.height / 4 + 48;
        ButtonWidget modButtonWidget = null;
            this.createNormalMenuOptions(j, 24);

        minecraft.setConnectedToRealms(false);
        if (this.realmsNotificationsScreen == null) {
            this.realmsNotificationsScreen = new RealmsNotificationsScreen();
        }

        if (this.realmsNotificationsEnabled()) {
            this.realmsNotificationsScreen.init(minecraft, this.width, this.height);
        }
    }

    private void createNormalMenuOptions(int p_96764_, int p_96765_) {
        boolean flag = minecraft.isMultiplayerEnabled();
        

        Quit = ButtonWidget.builder(Text.translatable("menu.options"), (p_213094_1_) -> {
            minecraft.setScreen(new OptionsScreen(this, minecraft.options));
        }).dimensions(this.width / 2 - 100, p_96764_ + p_96765_ * 1, 200, 20).build();

        dmServerButtonWidget = ButtonWidget.builder(Text.translatable("DocMod Servers"), (p_96781_) -> {
            //ConnectScreen.connect(this, minecraft, new ServerAddress(Servers.HOST, Servers.PORT), new ServerData("DocMod Server", Servers.HOST, false));
        }).dimensions(this.width / 2 - 100, p_96764_ + p_96765_ * 0, 100, 20).build();
        dmServerButtonWidget.active = false;

        this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.quit"), (p_96781_) -> {
            minecraft.stop();
        }).dimensions(this.width / 2 - 100, p_96764_ + p_96765_ * 2, 200, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.multiplayer"), (p_96781_) -> {
            minecraft.setScreen(new MultiplayerScreen(this));
        }).dimensions(this.width / 2 - 0, p_96764_ + p_96765_ * 0, 100, 20).build());

        //this.addDrawableChild(new ButtonWidget(this.width / 2 - 0, p_96764_ + p_96765_ * -2, 100, 20, Text.translatable("DM Addons"), (p_96781_) -> {
        //   minecraft.setScreen(new DMAddonListScreen());
        //}));

        this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.singleplayer"), (p_96781_) -> {
            minecraft.setScreen(new SelectWorldScreen(this));
        }).dimensions(this.width / 2 - 100, p_96764_ + p_96765_ * -1, 200, 20).build());

        this.addDrawableChild(Quit);
        this.addDrawableChild(dmServerButtonWidget);
        dmServerButtonWidget.active = true;


    }
    

    public void render(MatrixStack p_96739_, int p_96740_, int p_96741_, float p_96742_) {
        if (this.backgroundFadeStart == 0L && this.doBackgroundFade) {
            this.backgroundFadeStart = Util.getMeasuringTimeMs();
        }

        float f = this.doBackgroundFade ? (float)(Util.getMeasuringTimeMs() - this.backgroundFadeStart) / 1000.0F : 1.0F;
        this.backgroundRenderer.render(p_96742_, MathHelper.clamp(f, 0.0F, 1.0F));
        boolean i = true;
        int j = this.width / 2 - 137;
        boolean k = true;
        float f1 = MathHelper.clamp(f - 1.0F, 0.0F, 1.0F);
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderTexture(0, PANORAMA_OVERLAY);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(SrcFactor.SRC_ALPHA, DstFactor.ONE_MINUS_SRC_ALPHA);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.doBackgroundFade ? (float)MathHelper.ceil(MathHelper.clamp(f, 0.0F, 1.0F)) : 1.0F);
        drawTexture(p_96739_, 0, 0, this.width, this.height, 0.0F, 0.0F, 16, 128, 16, 128);
        int l = MathHelper.ceil(f1 * 255.0F) << 24;
        if ((l & -67108864) != 0) {
            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
            RenderSystem.setShaderTexture(0, DOCMOD_LOGO);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, f1);
            //drawTextWithShadowure(p_96739_, 0, 0, this.width, this.height, 0.0F, 0.0F, 16, 128, 16, 128);
            if (this.minceraftEasterEgg) {
                this.drawWithOutline(j, 30, (p_96768_, p_96769_) -> {
                    this.drawTexture(p_96739_, p_96768_ + 0, p_96769_, 0, 0, 99, 44);
                    this.drawTexture(p_96739_, p_96768_ + 99, p_96769_, 129, 0, 27, 44);
                    this.drawTexture(p_96739_, p_96768_ + 99 + 26, p_96769_, 126, 0, 3, 44);
                    this.drawTexture(p_96739_, p_96768_ + 99 + 26 + 3, p_96769_, 99, 0, 26, 44);
                    this.drawTexture(p_96739_, p_96768_ + 155, p_96769_, 0, 45, 155, 44);
                });
            } else {
                this.drawWithOutline(j - 18, 30, (p_96768_, p_96769_) -> {
                    this.drawTexture(p_96739_, p_96768_ + 0, p_96769_, 0, 0, 155, 44);
                    this.drawTexture(p_96739_, p_96768_ + 155, p_96769_, 0, 45, 155, 44);
                });
            }
/*
            //3D SKIN
            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
            RenderSystem.setShaderTexture(0, PlayerUtils.get3DSkin(Minecraft.getInstance().getUser().getUuid()));
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            drawTextWithShadowure(p_96739_, width / 2 - 180, height / 2 - 50, 0, 0, 120/2, 270/2, 120 / 2, 270 / 2);
 */
            //RenderSystem.setShaderTexture(0, MINECRAFT_EDITION);
            //drawTextWithShadowure(p_96739_, j + 88, 67, 0.0F, 0.0F, 98, 14, 128, 16);
            if (this.splash != null) {
                p_96739_.push();
                p_96739_.translate(this.width / 2 + 0, 80.0D, 0.0D);
                //p_96739_.mulPose(Vector3f.ZP.rotationDegrees(-20.0F));
                float f2 = 1.8F - MathHelper.abs(MathHelper.sin((float)(Util.getMeasuringTimeMs() % 1000L) / 1000.0F * ((float)Math.PI * 2F)) * 0.1F);
                f2 = f2 * 95 / (float)(this.textRenderer.getWidth(this.splash) + 32);
                p_96739_.scale(f2, f2, f2);
                //drawCenteredString(p_96739_, this.textRenderer, this.splash, 0, -8, 16776960 | l);
                p_96739_.pop();
            }

            String s = "Minecraft " + SharedConstants.getGameVersion().getName();
            if (minecraft.isDemo()) {
                s = s + " Demo";
            } else {
                s = s + ("release".equalsIgnoreCase(minecraft.getVersionType()) ? "" : "/" + minecraft.getVersionType());
            }

            drawTextWithShadow(p_96739_, this.textRenderer, Text.literal("Copyright Mojang AB. Do not distribute!"), this.copyrightX, this.height - 10, 16777215 | l);

            drawTextWithShadow(p_96739_, this.textRenderer, Text.literal(DMUtils.getCopyright()), this.copyrightX + 10, this.height - 20, 16777215 | l);
            
            drawTextWithShadow(p_96739_, this.textRenderer, Text.literal(DMUtils.getMinecraftVersion()), 0, this.height - 20, 16777215 | l);

            drawTextWithShadow(p_96739_, this.textRenderer, Text.literal(DMUtils.getDocTeam()), 0, this.height - 10, 16777215 | l);

            //drawTextWithShadow(p_96739_, this.textRenderer, "Page : 1 / " + TitleScreenUtils.getMaxTitleScreen(), singlePlayer.x + 14, this.height - 50, 16777215 | l);

/*
            if(LaunchUtils.checkLaunchedVersion()){
                //drawTextWithShadow(p_96739_, this.textRenderer, ChatFormatting.GREEN + "DocMod Dev is activated", singlePlayer.x -14, this.height -40, 16777215 | l);
                MemoryUsage heapMemoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
                long maxMB = heapMemoryUsage.getMax()/(1024*1024);
                long usedMB = heapMemoryUsage.getUsed()/(1024*1024);
                final int MegaBytes = 10241024;
                drawTextWithShadow(p_96739_, this.textRenderer, "Java Runtime: " + usedMB + "MB/" + maxMB + "MB", 0, this.height - 30, 16777215 | l);
            }
            */

            drawTextWithShadow(p_96739_, this.textRenderer, Text.literal(DocMod.MODNAME + " " + DocMod.VERSION), 0, this.height - 30, 16777215 | l);

            if(dmServerButtonWidget.isHovered()){
                renderTooltip(p_96739_, Text.literal("Disabled because there's no server for this version for now."), p_96740_, p_96741_);
            }

        }


            /*
            if(infoButtonWidget.isHoveredOrFocused()){
                this.drawTextWithShadow(p_96739_, textRenderer, "Changelog", infoButtonWidget.x - 50, infoButtonWidget.y, 0xffffff);
            }
             */
/*
            if(singlePlayer.isHoveredOrFocused()){
                drawTextWithShadow(p_96739_, textRenderer, "Singleplayer", singlePlayer.x + 14, singlePlayer.y +90, ColorUtils.getWhite());
            }
            if(multiPlayer.isHoveredOrFocused()){
                drawTextWithShadow(p_96739_, textRenderer, "Multiplayer", multiPlayer.x + 18, multiPlayer.y +90, ColorUtils.getWhite());
            }
            if(Quit.isHoveredOrFocused()){
                drawTextWithShadow(p_96739_, textRenderer, "Options", Quit.x + 28, Quit.y +90, ColorUtils.getWhite());
            }
            if(closeButtonWidget.isHoveredOrFocused()){
                this.renderTooltip(p_96739_, Text.translatable("gui.docmod.quit"), p_96740_, p_96741_);
            }
 */



        super.render(p_96739_, p_96740_, p_96741_, p_96742_);
        if (this.realmsNotificationsEnabled() && f1 >= 1.0F) {
            this.realmsNotificationsScreen.render(p_96739_, p_96740_, p_96741_, p_96742_);
        }
    }


    private void onDemoDeletionConfirmed(boolean delete) {
        if (delete) {
            try {
                LevelStorage.Session session = this.client.getLevelStorage().createSession("Demo_World");

                try {
                    session.deleteSessionLock();
                } catch (Throwable var6) {
                    if (session != null) {
                        try {
                            session.close();
                        } catch (Throwable var5) {
                            var6.addSuppressed(var5);
                        }
                    }

                    throw var6;
                }

                if (session != null) {
                    session.close();
                }
            } catch (IOException var7) {
                SystemToast.addWorldDeleteFailureToast(this.client, "Demo_World");
                LOGGER.warn("Failed to delete demo world", var7);
            }
        }

        this.client.setScreen(this);
    }


//TODO MAKE A ButtonWidget ON GAME MENU FOR FEEDBACK
}
