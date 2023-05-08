package be.ninedocteur.docmod.client.gui.screens;

import be.ninedocteur.docmod.DMConfig;
import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.gui.title.DMTitleScreen;
import be.ninedocteur.docmod.common.init.DMItems;
import be.ninedocteur.docmod.data.DMCommunityServerData;
import be.ninedocteur.docmod.data.DMCommunityServerData.ServerInfo;
import be.ninedocteur.docmod.data.DMServerData;
import be.ninedocteur.docmod.data.json.DMServer;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.lwjgl.glfw.GLFW;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;

import be.ninedocteur.docmod.utils.ColorUtils;
import be.ninedocteur.docmod.utils.DMUtils;
import be.ninedocteur.docmod.utils.IOUtils;
import be.ninedocteur.docmod.utils.PlayerUtils;
import be.ninedocteur.docmod.utils.ScreenUtils;
import be.ninedocteur.docmod.utils.ServerUtils;
import be.ninedocteur.docmod.utils.StaffUUIDs;
import be.ninedocteur.docmod.utils.TeamUtils;
import be.ninedocteur.docteam.BetaServerInfo;
import be.ninedocteur.docteam.DevServerInfo;
import be.ninedocteur.docteam.PublicServerInfo;
import be.ninedocteur.docteam.Servers;
import be.ninedocteur.docteam.website.Database;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.User;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.screens.ConnectScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerStatusPinger;
import net.minecraft.client.multiplayer.resolver.ServerAddress;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.PanoramaRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent.Chat;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.common.MinecraftForge;

@OnlyIn(Dist.CLIENT)
public class DMCommunityServer extends Screen {
    public static final CubeMap CUBE_MAP = new CubeMap(new ResourceLocation("textures/gui/title/background/panorama"));
    private static final ResourceLocation PANORAMA_OVERLAY = new ResourceLocation("textures/gui/title/background/panorama_overlay.png");
    private PanoramaRenderer panorama = new PanoramaRenderer(CUBE_MAP);
    private static final ResourceLocation FRAME = new ResourceLocation("docmod", "textures/gui/server/community/frame.png");
    private final Screen previousScreen;
    private long fadeInStart;
    private Minecraft minecraft = Minecraft.getInstance();
    
    //private String onlineUsers, maxPlayers;
    public Button joinServer;
    private static Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
    private DMCommunityServerData data;
    private int scrollOffset = 0;
    private int maxVisibleServers = 8;
    private int scrollableHeight;
    private boolean isScrollBarPressed;
    private int scrollBarHeight;
    private int mouseY;
    private int scrollBarY;
    private HashMap<String, Button> joinButtons = new HashMap<>();


    public DMCommunityServer(Screen previousScreen) {
        super( Component.translatable("narrator.screen.title"));
        this.previousScreen = previousScreen;
        (new Thread(new Runnable() {
			
			@Override
			public void run() {
				String jsonData = IOUtils.readURLContent("http://130.61.36.120/docteam/com_serv_data.php");
				data = GSON.fromJson(jsonData, DMCommunityServerData.class);
			}
		})).start();
    }

    public DMCommunityServer() {
        super( Component.translatable("narrator.screen.title"));
        this.previousScreen = null;
    }
    
    @Override
    public void tick() {
    	super.tick();
    	if (isScrollBarPressed) {
    		int mouseY = /* get the Y position of the mouse */this.mouseY;
    		// Calculate the new scroll offset based on the position of the mouse
    		scrollOffset = (int) ((float) (mouseY - 30) / (this.height - 60 - scrollBarHeight) * scrollableHeight);
    	}
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        float f = (float) (Util.getMillis() - this.fadeInStart);
        this.mouseY = pMouseY;
        this.panorama.render(pPartialTick, Mth.clamp(f, 0.0F, 1.0F));
        drawCenteredString(pPoseStack, font,  Component.translatable("DocMod Community Server"), width/2, 10, 0xffffff);
        if(data != null && data.getServers() != null) {
        	int serverSize = Integer.valueOf(data.getServerCount());
        	DMCommunityServerData.ServerInfo[] servers = data.getServers();
        	 int numVisibleServers = Math.min(maxVisibleServers, serverSize); // Nombre réel de serveurs visibles
        	    scrollableHeight = Math.max(0, serverSize - maxVisibleServers); // Hauteur de défilement maximale
        	    scrollBarHeight = Math.max(30, (int) ((float) maxVisibleServers / serverSize * (this.height - 60))); // Hauteur de la barre de défilement
        	    scrollBarY = 30 + (int) ((float) scrollOffset / scrollableHeight * (this.height - 60 - scrollBarHeight)); // Position Y de la barre de défilement


        	    // Mettre à jour l'offset de défilement si nécessaire
        	    if (scrollOffset > scrollableHeight) {
        	        scrollOffset = scrollableHeight;
        	    }
        	    
        	    if(scrollOffset < 0) {
        	    	scrollOffset = 0;
        	    }
        	    
        	for(int i = 0; /*i < serverSize;*/ i < numVisibleServers; i++) {
                //DMCommunityServerData.ServerInfo server = servers[i];
        		DMCommunityServerData.ServerInfo server = servers[i + scrollOffset];
        		
                //FRAME
                RenderSystem.setShaderTexture(0, FRAME);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                ScreenUtils.fillWithFullTexture(this.width / 120, this.height / 120 + 40 + i * 24, this.width - 8, 24);
                
                //ICON
                RenderSystem.setShaderTexture(0, getServerIcon(server.getIcon(), server.getServerName()));
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                ScreenUtils.fillWithFullTexture(this.width / 120 + 16, this.height / 120 + 44 + i * 24, 16, 16);
                
                //OWNER HEAD
                //RenderSystem.setShaderTexture(0, getTexture(server.getServerOwner()));
                //RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                //ScreenUtils.fillWithFullTexture(this.width / 2 - 50, this.height / 120 + 44 + i * 24, 16, 16);
                
                this.font.draw(pPoseStack, Component.literal(server.getServerName()), this.width / 120 + 36, this.height / 120 + 48 + i * 24, - 1);
                this.font.draw(pPoseStack, Component.literal(server.getServerOwner()), this.width / 2 - 30, this.height / 120 + 48 + i * 24, - 1);
                this.font.draw(pPoseStack, getSlotsInfo(server, server.getServerIp()),  this.width / 2 + 70, this.height / 120 + 48 + i * 24, - 1);

                joinServer = Button.builder(Component.translatable("gui.docmod.join"), (p_96781_) -> {
                     CommunityServerConnectScreen.startConnecting(this, this.getMinecraft(), new ServerAddress(server.getServerIp(), 25565), new ServerData("DocMod Community Server " + server.getServerIp(), server.getServerIp(), false));
                }).bounds(this.width /2 + 160, this.height / 120 + 42 + i * 24, 40, 20).build();
                
              
                
                fill(pPoseStack, this.width - 8, 30, this.width, this.height - 30, 0xFF000000); // Fond de la barre de défilement
                fill(pPoseStack, this.width - 8, scrollBarY, this.width, scrollBarY + scrollBarHeight, 0xFFCCCCCC); // Barre de défilement
                
                if(!joinButtons.containsKey(server.getServerName())) {
                	this.addRenderableWidget(joinServer);
                }
                
            }
        	drawCenteredString(pPoseStack, font,  Component.literal(data.getServerCount() + " §eservers registered!"), width/2, 20, 0xffffff);
        } else {
            drawCenteredString(pPoseStack, font,  Component.literal("§6Loading..."), width/2, height/2, 0xffffff);
        }
      
        
        //System.out.println(data.getServers());
      
        

        
        

        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }
    
    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_UP) { // Touche flèche haut
            scrollOffset = Math.max(0, scrollOffset - 1); // Décrémente l'offset de défilement
            return true;
        } else if (keyCode == GLFW.GLFW_KEY_DOWN) { // Touche flèche bas
            scrollOffset = Math.min(scrollableHeight, scrollOffset + 1); // Incrémente l'offset de défilement
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
    
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
    	if (mouseX >= this.width - 8 && mouseX <= this.width &&
    		mouseY >= scrollBarY && mouseY <= scrollBarY + scrollBarHeight) {
    		// Update the state of the scrollbar to "pressed"
    		isScrollBarPressed = true;
    		return true;
    	}
    	return super.mouseClicked(mouseX, mouseY, button);
    }
    
    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
    	if (isScrollBarPressed) {
    		// Update the state of the scrollbar to "released"
    		isScrollBarPressed = false;
    		return true;
    	}
    	return super.mouseReleased(mouseX, mouseY, button);
    }

    
    public static boolean isServerUp(String ip){
        return !IOUtils.readURLContent("https://mcapi.xdefcon.com/server/" + ip + "/players/text").equals("Server status: Offline");
    }
    
    public Component getSlotsInfo(ServerInfo server, String ip) {
    	if(!(server.getPlayerOnline() == null)) {
    		return Component.literal(ChatFormatting.GOLD + server.getPlayerOnline() + ChatFormatting.RESET + "/" + ChatFormatting.GOLD + server.getPlayerMax());
    	} else {
    		return Component.literal(ChatFormatting.RED + "Offline");
    	}
    }
    
    public ResourceLocation getServerIcon(String URL, String serverName) {
    	return IOUtils.readServerIcon(URL, serverName);
    }
    
    public ResourceLocation getTexture(String serverOwner) {
    	return PlayerUtils.getPlayerHead(serverOwner);
    }

    @Override
    protected void init() {


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
