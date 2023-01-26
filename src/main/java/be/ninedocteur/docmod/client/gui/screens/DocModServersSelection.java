package be.ninedocteur.docmod.client.gui.screens;

import be.ninedocteur.docmod.utils.*;
import be.ninedocteur.docteam.BetaServerInfo;
import be.ninedocteur.docteam.DevServerInfo;
import be.ninedocteur.docteam.PublicServerInfo;
import be.ninedocteur.docteam.Servers;
import be.ninedocteur.docteam.website.Database;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.User;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.ConnectScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.resolver.ServerAddress;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.client.renderer.PanoramaRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DocModServersSelection extends Screen {
    public static final CubeMap CUBE_MAP = new CubeMap(new ResourceLocation(DMUtils.MOD_ID, "textures/gui/server/background/panorama"));
    private static final ResourceLocation PANORAMA_OVERLAY = new ResourceLocation("textures/gui/title/background/panorama_overlay.png");
    private PanoramaRenderer panorama = new PanoramaRenderer(CUBE_MAP);
    private long fadeInStart;

    public boolean showContent;

    private final Screen lastScreen;

    private String onlineUsers, maxPlayers, betaOnlineUsers, betaMaxPlayers, devOnlineUsers, devMaxPlayers;

    public DocModServersSelection(Screen p_101338_) {
        super( Component.translatable("screen.title"));
        this.lastScreen = p_101338_;
        this.panorama = new PanoramaRenderer(CUBE_MAP);
    }

    @Override
    public void tick() {
        super.tick();

        if(System.currentTimeMillis() % 15000 == 0) {
            maxPlayers = PublicServerInfo.getMaxPlayers();
            betaMaxPlayers = BetaServerInfo.getMaxPlayers();
            devMaxPlayers = DevServerInfo.getMaxPlayers();
        }
    }

    public void render(PoseStack p_99694_, int p_99695_, int p_99696_, float p_99697_) {
        float f = (float)(Util.getMillis() - this.fadeInStart);
        this.panorama.render(p_99697_, Mth.clamp(f, 0.0F, 1.0F));
        drawCenteredString(p_99694_, font, Component.translatable("DocMod Server"), width/2, 10, ColorUtils.getWhite());
        //drawCenteredString(p_99694_, font, Component.translatable("Players: "+ ChatFormatting.GOLD + onlineUsers + ChatFormatting.WHITE +  " / " + ChatFormatting.GOLD + maxPlayers), width/2, 120, DMUtils.getWhite());
        drawCenteredString(p_99694_, font, Component.translatable("DocMod " + ChatFormatting.GOLD + PublicServerInfo.DOCMOD_VERSION_NEEDED + ChatFormatting.WHITE + " is needed to join the server."), width/2, 30, ColorUtils.getWhite());
        drawCenteredString(p_99694_, font, Component.translatable("DocMod " + ChatFormatting.GOLD + BetaServerInfo.DOCMOD_VERSION_NEEDED + ChatFormatting.WHITE + " is needed to join the beta server."), width/2, 40, ColorUtils.getWhite());
        //drawCenteredString(p_99694_, font, Component.translatable("IP: " + ChatFormatting.GOLD + PublicServerInfo.IP), width/2, 130, DMUtils.getWhite());
            if (maxPlayers.equals("Server status: Offline")) {
                drawCenteredString(p_99694_, font, Component.translatable("Players" + ": " + ChatFormatting.RED + "Server Offline"), width / 2, 120, ColorUtils.getWhite());
            } else {
                drawCenteredString(p_99694_, font, Component.translatable("Players" + ": " + ChatFormatting.GOLD + onlineUsers + ChatFormatting.WHITE + " / " + ChatFormatting.GOLD + maxPlayers), width / 2, 120, ColorUtils.getWhite());
            }

            if (betaMaxPlayers.equals("Server status: Offline")) {
                drawCenteredString(p_99694_, font, Component.translatable("Beta Players" + ": " + ChatFormatting.RED + "Server Offline"), width / 2, 130, ColorUtils.getWhite());
            } else {
                drawCenteredString(p_99694_, font, Component.translatable("Beta Players" + ": " + ChatFormatting.GOLD + betaOnlineUsers + ChatFormatting.WHITE + " / " + ChatFormatting.GOLD + betaMaxPlayers), width / 2, 130, ColorUtils.getWhite());
            }


        super.render(p_99694_, p_99695_, p_99696_, p_99697_);
    }

    @Override
    protected void init() {
        onlineUsers = PublicServerInfo.getPLAYERS();
        maxPlayers = PublicServerInfo.getMaxPlayers();

        betaOnlineUsers = BetaServerInfo.getPLAYERS();
        betaMaxPlayers = BetaServerInfo.getMaxPlayers();

        devOnlineUsers = DevServerInfo.getPLAYERS();
        devMaxPlayers = DevServerInfo.getMaxPlayers();

        Button cancel = Button.builder(Component.translatable("gui.docmod.cancel" ), (p_96781_) -> {
            this.getMinecraft().setScreen(new TitleScreen(this.passEvents));
        }).bounds(225, 210, 180, 20).build();
        Button joinServer = Button.builder(Component.translatable("gui.docmod.join" ), (p_96781_) -> {
            ConnectScreen.startConnecting(this, this.getMinecraft(), new ServerAddress(Servers.HOST, Servers.PORT), new ServerData("DocMod Server", Servers.HOST, false));
        }).bounds(25, 210, 90, 20).build();
        Button joinBetaServer = Button.builder(Component.translatable("gui.docmod.joinbeta" ), (p_96781_) -> {
            User user = Minecraft.getInstance().getUser();
            TeamUtils.TeamMember teamMember = TeamUtils.getTeamMembers().get(user.getUuid());
            if(Database.getBetaRankDir().contains(user.getUuid())){
                ConnectScreen.startConnecting(this, this.getMinecraft(), new ServerAddress(Servers.BetaHOST, Servers.PrivateServerPORT), new ServerData("DocMod Server", Servers.BetaHOST, false));
            }else if(teamMember == teamMember){
                ConnectScreen.startConnecting(this, this.getMinecraft(), new ServerAddress(Servers.BetaHOST, Servers.PrivateServerPORT), new ServerData("DocMod Server", Servers.BetaHOST, false));
            }else {
                this.minecraft.setScreen(new DocModServerRankErrorScreen(this));
            }
        }).bounds(116, 210, 90, 20).build();

        this.addRenderableWidget(joinBetaServer);
        this.addRenderableWidget(cancel);
        this.addRenderableWidget(joinServer);
        super.init();
    }

    @Override
    public void onClose() {
        super.onClose();
        Minecraft.getInstance().setScreen(this.lastScreen);
    }
}
