package fr.ninedocteur.docmod.client.gui.title;
import com.google.common.util.concurrent.Runnables;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import fr.ninedocteur.docmod.DMConfig;
import fr.ninedocteur.docmod.DMSharedConstants;
import fr.ninedocteur.docmod.DocMod;
import fr.ninedocteur.docmod.client.gui.screens.*;
import fr.ninedocteur.docmod.utils.DMUtils;
import fr.ninedocteur.docmod.utils.ScreenUtils;
import fr.ninedocteur.docmod.utils.URLUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import net.minecraft.client.gui.CubeMapRenderer;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.client.gui.screen.CreditsScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.realms.gui.screen.RealmsNotificationsScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.level.storage.LevelStorage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class DMTitleScreen extends Screen {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final CubeMapRenderer CUBE_MAP = new CubeMapRenderer(new Identifier("textures/gui/title/background/panorama"));
    private static final Identifier PANORAMA_OVERLAY = new Identifier("textures/gui/title/background/panorama_overlay.png");
    @Nullable
    private String splash;
    private ButtonWidget resetDemoButton;
    private static final Identifier MINECRAFT_LOGO = new Identifier("textures/gui/title/minecraft.png");
    private static final Identifier DOCMOD_LOGO = new Identifier(DMUtils.MOD_ID, "textures/gui/title/docmod_logo_2023.png");
    private static final Identifier INFO = new Identifier(DMUtils.MOD_ID, "textures/gui/title/info.png");
    private static final Identifier DISCORD = new Identifier(DMUtils.MOD_ID, "textures/gui/title/discord.png");
    private static final Identifier YOUTUBE = new Identifier(DMUtils.MOD_ID, "textures/gui/title/youtube.png");
    private static final Identifier PARAM = new Identifier(DMUtils.MOD_ID, "textures/gui/title/param.png");
    private static final Identifier INFO_ON = new Identifier(DMUtils.MOD_ID, "textures/gui/title/info_on.png");
    private static final Identifier DISCORD_ON = new Identifier(DMUtils.MOD_ID, "textures/gui/title/discord_on.png");
    private static final Identifier YOUTUBE_ON = new Identifier(DMUtils.MOD_ID, "textures/gui/title/youtube_on.png");
    private static final Identifier PARAM_ON = new Identifier(DMUtils.MOD_ID, "textures/gui/title/param_on.png");
    private static final Identifier MINECRAFT_EDITION = new Identifier("textures/gui/title/edition.png");
    private Screen realmsNotificationsScreen;
    private int copyrightWidth;
    private int copyrightX;
    private RotatingCubeMapRenderer panorama;
    private long fadeInStart;
    private final boolean fading;
    private final boolean minceraftEasterEgg;
    private ButtonWidget infoButton, Quit, configButton, discordButton, youtubeButton, staffButton, closeButton, serverButton, dmServerButton;
    protected int leftPos;
    protected int topPos;
    private RunArgs pGameConfig;

    public DMTitleScreen() {
        this(true);
    }

    public DMTitleScreen(boolean pFading) {
        super(Text.translatable("narrator.screen.title"));
        this.panorama = new RotatingCubeMapRenderer(CUBE_MAP);
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
        MinecraftClient.getInstance().getWindow().setTitle("Minecraft " + SharedConstants.getGameVersion().getName() + " | " + DocMod.MODNAME + " " + DocMod.VERSION);
        //LaunchUtils.initWindowIcon(new Identifier(DocMod.MOD_ID, "icons/icon16x16.png"), new Identifier(DocMod.MOD_ID, "icons/icon32x32.png"));


    }

    public static CompletableFuture<Void> preloadResources(TextureManager pTexMngr, Executor pBackgroundExecutor) {
        return CompletableFuture.allOf(pTexMngr.loadTextureAsync(DOCMOD_LOGO, pBackgroundExecutor), pTexMngr.loadTextureAsync(MINECRAFT_EDITION, pBackgroundExecutor), pTexMngr.loadTextureAsync(PANORAMA_OVERLAY, pBackgroundExecutor), CUBE_MAP.loadTexturesAsync(pTexMngr, pBackgroundExecutor));
    }

    public boolean shouldPause() {
        return false;
    }

    public boolean shouldCloseOnEsc() {
        return false;
    }


    protected void init() {
        if (this.splash == null) {
            this.splash = MinecraftClient.getInstance().getSplashTextLoader().get();
        }

        preloadResources(this.client.getTextureManager(), this.client);

        this.copyrightWidth = this.textRenderer.getWidth("Copyright Mojang AB. Do not distribute!");
        this.copyrightX = this.width - this.copyrightWidth - 2;
        int i = 24;
        int j = this.height / 4 + 48;
        ButtonWidget modButton = null;
        if (this.client.isDemo()) {
            this.createDemoMenuOptions(j, 24);
        } else {
            this.createNormalMenuOptions(j, 24);
        }

        this.client.setConnectedToRealms(false);
        if (this.realmsNotificationsScreen == null) {
            this.realmsNotificationsScreen = new RealmsNotificationsScreen();
        }

        if (this.realmsNotificationsEnabled()) {
            this.realmsNotificationsScreen.init(this.client, this.width, this.height);
        }
    }

    private void createNormalMenuOptions(int p_96764_, int p_96765_) {
        boolean flag = this.client.isMultiplayerEnabled();

        if (DMConfig.Client.SHOW_WIDGET) {
            configButton = new TexturedButtonWidget(this.width / 2 - 210, this.height / 4 + 48 -106, 16, 16, 0, 96, new Identifier(DocMod.MOD_ID, "textures/gui/title/transparent.png"), (button -> {
                this.client.setScreen(new DocModConfigScreen(this));
            }));
            discordButton = new TexturedButtonWidget(this.width / 2 - 190, this.height / 4 + 48 -106, 16, 16, 0, 0, new Identifier(DocMod.MOD_ID, "textures/gui/title/transparent.png"), (button -> {
                URLUtils.openLink(DMUtils.getDiscordLink());
            }));
            youtubeButton = new TexturedButtonWidget(this.width / 2 - 170, this.height / 4 + 48 -106, 16, 16, 0, 64, new Identifier(DocMod.MOD_ID, "textures/gui/title/transparent.png"), (button -> {
                URLUtils.openLink(DMUtils.getYoutubeLink());
            }));

            staffButton = new TexturedButtonWidget(this.width / 2 + 170, this.height / 4 + 48 -106, 16, 16, 0, 0, new Identifier(DocMod.MOD_ID, "textures/gui/staff.png"), (button -> {
                ScreenUtils.openScreen(new DocModStaffScreen());
            }));

            serverButton = new TexturedButtonWidget(this.width / 2 + 190, this.height / 4 + 48 -106, 16, 16, 0, 0, new Identifier(DocMod.MOD_ID, "textures/gui/server.png"), (button -> {
                ScreenUtils.openScreen(new DMCommunityServer(this));
            }));

            infoButton = new TexturedButtonWidget(this.width / 2 - 150, this.height / 4 + 48 -106, 16, 16, 0, 129, new Identifier(DocMod.MOD_ID, "textures/gui/title/transparent.png"), (button -> {
                this.client.setScreen(new DocModChangelogScreen(this));
            }));

            this.addDrawableChild(discordButton);
            this.addDrawableChild(youtubeButton);
            this.addDrawableChild(configButton);
            this.addDrawableChild(infoButton);
            this.addDrawableChild(staffButton);
            this.addDrawableChild(serverButton);

            //if(!LaunchUtils.isRunningInDev()){
            //  serverButton.active = false;
            //}
        }

        Quit = ButtonWidget.builder(Text.translatable("menu.options"), (p_213094_1_) -> {
            this.client.setScreen(new OptionsScreen(this, this.client.options));
        }).dimensions(this.width / 2 - 100, p_96764_ + p_96765_ * 2, 200, 20).build();

        dmServerButton = ButtonWidget.builder(Text.translatable("DocMod Servers"), (p_96781_) -> {
            this.client.setScreen(new DMServerScreen(this));
        }).dimensions(this.width / 2 - 100, p_96764_ + p_96765_ * 1, 100, 20).build();

        this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.quit"), (p_96781_) -> {
            this.client.stop();
        }).dimensions(this.width / 2 - 100, p_96764_ + p_96765_ * 3, 200, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.multiplayer"), (p_96781_) -> {
            this.client.setScreen(new MultiplayerScreen(this));
        }).dimensions(this.width / 2 - 0, p_96764_ + p_96765_ * 1, 100, 20).build());

        //this.addDrawableChild(new Button(this.width / 2 - 0, p_96764_ + p_96765_ * -2, 100, 20, Text.translatable("DM Addons"), (p_96781_) -> {
        //   this.minecraft.setScreen(new DMAddonListScreen());
        //}));

        this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.singleplayer"), (p_96781_) -> {
            this.client.setScreen(new SelectWorldScreen(this));
        }).dimensions(this.width / 2 - 100, p_96764_ + p_96765_ * 0, 200, 20).build());

        this.addDrawableChild(Quit);
        this.addDrawableChild(dmServerButton);
        dmServerButton.active = true;


    }

    private void createDemoMenuOptions(int p_96773_, int p_96774_) {
        boolean flag = this.checkDemoWorldPresence();
    }

    private boolean checkDemoWorldPresence() {
        try {
            LevelStorage.Session levelstoragesource$levelstorageaccess = this.client.getLevelStorage().createSession("Demo_World");

            boolean flag;
            try {
                flag = levelstoragesource$levelstorageaccess.getLevelSummary() != null;
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
            SystemToast.addWorldAccessFailureToast(this.client, "Demo_World");
            LOGGER.warn("Failed to read demo world data", ioexception);
            return false;
        }
    }

    public Identifier getParamTexture() {
        if(configButton.isHovered()) {
            return PARAM_ON;
        } else {
            return PARAM;
        }
    }

    public Identifier getYoutubeTexture() {
        if(youtubeButton.isHovered()) {
            return YOUTUBE_ON;
        } else {
            return YOUTUBE;
        }
    }

    public Identifier getDiscordTexture() {
        if(discordButton.isHovered()) {
            return DISCORD_ON;
        } else {
            return DISCORD;
        }
    }

    public Identifier getInfoTexture() {
        if(infoButton.isHovered()) {
            return INFO_ON;
        } else {
            return INFO;
        }
    }

    public void render(MatrixStack p_96739_, int p_96740_, int p_96741_, float p_96742_) {
        float f = (float)(Util.getMeasuringTimeMs() - this.fadeInStart);
        this.panorama.render(p_96742_, MathHelper.clamp(f, 0.0F, 1.0F));
        int i = 274;
        int j = this.width / 2 - 137;
        int k = 30;
        float f1 = MathHelper.clamp(f - 1.0F, 0.0F, 1.0F);
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderTexture(0, PANORAMA_OVERLAY);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, (float)MathHelper.ceil(MathHelper.clamp(f, 0.0F, 1.0F)));
        drawTexture(p_96739_, 0, 0, this.width, this.height, 0.0F, 0.0F, 16, 128, 16, 128);
        int l = MathHelper.ceil(f1 * 255.0F) << 24;
        if ((l & -67108864) != 0) {
            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
            RenderSystem.setShaderTexture(0, DOCMOD_LOGO);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, f1);
            ScreenUtils.fillWithFullTexture(this.width /2 - 104, -5, 1280 / 6, 720 / 6);

            if (this.splash != null) {
                p_96739_.push();
                p_96739_.translate(this.width / 2 + 0, 80.0D, 0.0D);
                //p_96739_.mulPose(Vector3f.ZP.rotationDegrees(-20.0F));
                float f2 = 1.8F - MathHelper.abs(MathHelper.sin((float)(Util.getMeasuringTimeMs() % 1000L) / 1000.0F * ((float)Math.PI * 2F)) * 0.1F);
                f2 = f2 * 95 / (float)(this.textRenderer.getWidth(this.splash) + 32);
                p_96739_.scale(f2, f2, f2);
                //drawCenteredTextWithShadow(p_96739_, this.textRenderer, this.splash, 0, -8, 16776960 | l);
                p_96739_.pop();
            }


            //WIDGETS
            //PARAM
            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
            RenderSystem.setShaderTexture(0, getParamTexture());
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, f1);
            ScreenUtils.fillWithFullTexture(configButton.getX(), configButton.getY(), 256 / 15, 256 / 15);

            //DISCORD
            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
            RenderSystem.setShaderTexture(0, getDiscordTexture());
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, f1);
            ScreenUtils.fillWithFullTexture(discordButton.getX(), discordButton.getY(), 1024 / 54, 1024 / 54);

            //YOUTUBE
            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
            RenderSystem.setShaderTexture(0, getYoutubeTexture());
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, f1);
            ScreenUtils.fillWithFullTexture(youtubeButton.getX(), youtubeButton.getY(), 1024 / 54, 1024 / 54);

            //INFO
            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
            RenderSystem.setShaderTexture(0, getInfoTexture());
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, f1);
            ScreenUtils.fillWithFullTexture(infoButton.getX(), infoButton.getY(), 1024 / 62, 1024 / 62);
            

            drawTextWithShadow(p_96739_, this.textRenderer, "Copyright Mojang AB. Do not distribute!", this.copyrightX, this.height - 10, 16777215 | l);

            drawTextWithShadow(p_96739_, this.textRenderer, DMUtils.getCopyright(), this.copyrightX + 10, this.height - 20, 16777215 | l);

            drawTextWithShadow(p_96739_, this.textRenderer, DMUtils.getMinecraftVersion(), 0, this.height - 20, 16777215 | l);

            drawTextWithShadow(p_96739_, this.textRenderer, DMUtils.getDocTeam(), 0, this.height - 10, 16777215 | l);


            if(!DMSharedConstants.isSnapshot()) {
                drawTextWithShadow(p_96739_, this.textRenderer, DocMod.MODNAME + " " + DocMod.VERSION, 0, this.height - 30, 16777215 | l);
            } else {
                drawTextWithShadow(p_96739_, this.textRenderer, DocMod.MODNAME + " " + DocMod.VERSION + " Snapshot " + DMSharedConstants.getVersion(), 0, this.height - 30, 16777215 | l);
            }
        }



        if(DMConfig.Client.SHOW_VERSION) {
            drawCenteredTextWithShadow(p_96739_, textRenderer,  Text.translatable(DMUtils.MODNAME + " " + DMUtils.VERSION), width / 2, 10, 0xffffff);
            drawCenteredTextWithShadow(p_96739_, textRenderer,  Text.translatable("Codename : " + DMUtils.CODENAME), width / 2, 20, 0xffffff);
        }


        for(Element guieventlistener : this.children()) {
            if (guieventlistener instanceof ClickableWidget) {
                ((ClickableWidget)guieventlistener).setAlpha(f1);
            }
        }



        if(infoButton.isSelected()){
            this.renderTooltip(p_96739_, Text.translatable("gui.docmod.info"), p_96740_, p_96741_);
        }

        if(configButton.isSelected()){
            this.renderTooltip(p_96739_, Text.translatable("gui.docmod.config"), p_96740_, p_96741_);
        }

        if(youtubeButton.isSelected()){
            this.renderTooltip(p_96739_, Text.translatable("gui.docmod.youtube"), p_96740_, p_96741_);
        }

        if(discordButton.isSelected()){
            this.renderTooltip(p_96739_, Text.translatable("gui.docmod.discord"), p_96740_, p_96741_);
        }

        if(serverButton.isSelected()){
            this.renderTooltip(p_96739_, Text.literal(Formatting.RED + "Coming Soon!"), p_96740_, p_96741_);
        }

        if(staffButton.isSelected()){
            this.renderTooltip(p_96739_, Text.literal( "DocTeam Staff"), p_96740_, p_96741_);
        }


        super.render(p_96739_, p_96740_, p_96741_, p_96742_);
        if (this.realmsNotificationsEnabled() && f1 >= 1.0F) {
            this.realmsNotificationsScreen.render(p_96739_, p_96740_, p_96741_, p_96742_);
        }
    }




    public boolean mouseClicked(double p_96735_, double p_96736_, int p_96737_) {
        if (super.mouseClicked(p_96735_, p_96736_, p_96737_)) {
            return true;
        } else if (this.realmsNotificationsEnabled() && this.realmsNotificationsScreen.mouseClicked(p_96735_, p_96736_, p_96737_)) {
            return true;
        } else {
            if (p_96735_ > (double)this.copyrightX && p_96735_ < (double)(this.copyrightX + this.copyrightWidth) && p_96736_ > (double)(this.height - 10) && p_96736_ < (double)this.height) {
                this.client.setScreen(new CreditsScreen(false, Runnables.doNothing()));
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
                LevelStorage.Session levelstoragesource$levelstorageaccess = this.client.getLevelStorage().createSession("Demo_World");

                try {
                    levelstoragesource$levelstorageaccess.deleteSessionLock();
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
                SystemToast.addWorldDeleteFailureToast(this.client, "Demo_World");
                LOGGER.warn("Failed to delete demo world", ioexception);
            }
        }

        this.client.setScreen(this);
    }

}
