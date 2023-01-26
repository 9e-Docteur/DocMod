package be.ninedocteur.docmod.client.gui.screens;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.data.json.DMServer;
import be.ninedocteur.docmod.utils.DMTranslationString;
import be.ninedocteur.docmod.utils.IOUtils;
import be.ninedocteur.docteam.Servers;

import com.google.gson.*;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.ConnectScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.resolver.ServerAddress;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URI;
import java.net.URISyntaxException;
@OnlyIn(Dist.CLIENT)

public class DMCommunityServerScreen extends Screen {

    public static int width2;
    public static Font font2;
    private DMServer[] dmServers;

    private boolean isPinging = true;
    private boolean AlreadyCheck = false;

    public DMCommunityServerScreen() {
        super(DMTranslationString.DM_COMMUNITY_SERVER);
    }

    @Override
    protected void init() {
        super.init();
        width2 = width;
        font2 = this.font;
        PoseStack poseStack = new PoseStack();
        drawCenteredString(poseStack, font,  Component.literal("DocMod - Communauty Servers"), width/2, 10, 0xffffff);
        Button cancel = Button.builder( Component.translatable("gui.docmod.cancel" ), (p_96781_) -> {
            this.getMinecraft().setScreen(new TitleScreen(this.passEvents));
        }).bounds(225, 210, 180, 20).build();
        Button joinServer = Button.builder(Component.translatable("gui.docmod.join"), (p_96781_) -> {
            ConnectScreen.startConnecting(this, this.getMinecraft(), new ServerAddress(Servers.HOST, Servers.PORT), new ServerData("DocMod Server", Servers.HOST, false));
        }).bounds(25, 210, 180, 20).build();
        this.addRenderableWidget(cancel);
        this.addRenderableWidget(joinServer);
        joinServer.active = false;
        
        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(DMServer.class, new DMServer.Deserializer()).create();
        try {
			JsonElement element = JsonParser.parseString(IOUtils.readURLContent("http://docteam.capmine.tk/docteamdatabase/CommunautyServer.json"));
			dmServers = gson.fromJson(element, DMServer[].class);
		} catch (JsonIOException | JsonSyntaxException e) {
			e.printStackTrace();
		}
        
        for(int i = 0; i < dmServers.length; i++) {
        	Screen tmp = this;
        	String ip = dmServers[i].getIp();
        	int port = dmServers[i].getPort();
        	String name = dmServers[i].getName();
        	
        	int x = width / 2 - width / 4;
        	int y = i * 16 + 32;
        	
//        	ImageButton server = new ImageButton(x - 8, y - 8, 64, 21, 0, 0, new ResourceLocation(DocMod.MOD_ID, "textures/gui/servers.png"),
//            		new Button.OnPress() {
//						@Override
//						public void onPress(Button p_93751_) {
//							ConnectScreen.startConnecting(
//			                		tmp,
//			                		new ServerAddress(ip, port),
//			                		new ServerData(name, ip, false)
//			               );
//						}
//					});
//            addRenderableWidget(server);
        }
    }

    @Override
    public void renderDirtBackground(int p_96627_) {
        super.renderDirtBackground(p_96627_);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void render(PoseStack p_96562_, int p_96563_, int p_96564_, float p_96565_) {
    	renderBackground(p_96562_);
        for(int i = 0; i < dmServers.length; i++) {
        	String ip = dmServers[i].getIp();
        	String name = dmServers[i].getName();
        	
        	int x = width / 2 - width / 4;
        	int y = i * 16 + 32;
        	
            drawString(p_96562_, font, name, x, y, -1);
            if(!AlreadyCheck){
                if(!isServerUp(ip)){
            	    drawString(p_96562_, font, Component.nullToEmpty(ChatFormatting.RED + "Server Offline"), x, y + 16, -1);
                    AlreadyCheck = true;
             } else{
                  drawString(p_96562_, font, Component.nullToEmpty(ChatFormatting.GREEN + "Server Online"), x, y + 16, -1);
                  AlreadyCheck = true;
                }
            }
        }
        super.render(p_96562_, p_96563_, p_96564_, p_96565_);
    }
    
    private boolean isServerUp(String ip){
        return !IOUtils.readURLContent("https://mcapi.xdefcon.com/server/" + ip + "/players/text").equals("Server status: Offline");
    }
}
