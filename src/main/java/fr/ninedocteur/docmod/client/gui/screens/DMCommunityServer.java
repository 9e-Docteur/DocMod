package fr.ninedocteur.docmod.client.gui.screens;

import java.util.HashMap;

import fr.ninedocteur.docmod.data.DMCommunityServerData;
import fr.ninedocteur.docmod.utils.IOUtils;
import fr.ninedocteur.docmod.utils.PlayerUtils;
import fr.ninedocteur.docmod.utils.ScreenUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.CubeMapRenderer;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.glfw.GLFW;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.blaze3d.systems.RenderSystem;

public class DMCommunityServer extends Screen {
    public static final CubeMapRenderer CUBE_MAP = new CubeMapRenderer(new Identifier("textures/gui/title/background/panorama"));
    private static final Identifier PANORAMA_OVERLAY = new Identifier("textures/gui/title/background/panorama_overlay.png");
    private RotatingCubeMapRenderer panorama = new RotatingCubeMapRenderer(CUBE_MAP);
    private static final Identifier FRAME = new Identifier("docmod", "textures/gui/server/community/frame.png");
    private final Screen previousScreen;
    private long fadeInStart;
    private MinecraftClient minecraft = MinecraftClient.getInstance();
    
    //private String onlineUsers, maxPlayers;
    public ButtonWidget joinServer;
    private static Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
    private DMCommunityServerData data;
    private int scrollOffset = 0;
    private int maxVisibleServers = 8;
    private int scrollableHeight;
    private boolean isScrollBarPressed;
    private int scrollBarHeight;
    private int mouseY;
    private int scrollBarY;
    private HashMap<String, ButtonWidget> joinButtons = new HashMap<>();


    public DMCommunityServer(Screen previousScreen) {
        super( Text.translatable("narrator.screen.title"));
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
        super( Text.translatable("narrator.screen.title"));
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
    public void render(MatrixStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        float f = (float) (Util.getMeasuringTimeMs() - this.fadeInStart);
        this.mouseY = pMouseY;
        this.panorama.render(pPartialTick, MathHelper.clamp(f, 0.0F, 1.0F));
        drawCenteredTextWithShadow(pPoseStack, textRenderer,  Text.translatable("DocMod Community Server"), width/2, 10, 0xffffff);
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
                
                this.textRenderer.draw(pPoseStack, Text.literal(server.getServerName()), this.width / 120 + 36, this.height / 120 + 48 + i * 24, - 1);
                this.textRenderer.draw(pPoseStack, Text.literal(server.getServerOwner()), this.width / 2 - 30, this.height / 120 + 48 + i * 24, - 1);
                this.textRenderer.draw(pPoseStack, getSlotsInfo(server, server.getServerIp()),  this.width / 2 + 70, this.height / 120 + 48 + i * 24, - 1);

                joinServer = ButtonWidget.builder(Text.translatable("gui.docmod.join"), (p_96781_) -> {
                     CommunityServerConnectScreen.startConnecting(this, this.client, new ServerAddress(server.getServerIp(), 25565), new ServerInfo("DocMod Community Server " + server.getServerIp(), server.getServerIp(), false));
                }).dimensions(this.width /2 + 160, this.height / 120 + 42 + i * 24, 40, 20).build();
                
              
                
                fill(pPoseStack, this.width - 8, 30, this.width, this.height - 30, 0xFF000000); // Fond de la barre de défilement
                fill(pPoseStack, this.width - 8, scrollBarY, this.width, scrollBarY + scrollBarHeight, 0xFFCCCCCC); // Barre de défilement
                
                if(!joinButtons.containsKey(server.getServerName())) {
                	this.addDrawableChild(joinServer);
                }
                
            }
            drawCenteredTextWithShadow(pPoseStack, textRenderer,  Text.literal(data.getServerCount() + " §eservers registered!"), width/2, 20, 0xffffff);
        } else {
            drawCenteredTextWithShadow(pPoseStack, textRenderer,  Text.literal("§6Loading..."), width/2, height/2, 0xffffff);
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
    
    public Text getSlotsInfo(DMCommunityServerData.ServerInfo server, String ip) {
    	if(!(server.getPlayerOnline() == null)) {
    		return Text.literal(Formatting.GOLD + server.getPlayerOnline() + Formatting.RESET + "/" + Formatting.GOLD + server.getPlayerMax());
    	} else {
    		return Text.literal(Formatting.RED + "Offline");
    	}
    }
    
    public Identifier getServerIcon(String URL, String serverName) {
    	return IOUtils.readServerIcon(URL, serverName);
    }
    
    public Identifier getTexture(String serverOwner) {
    	return PlayerUtils.getPlayerHead(serverOwner);
    }

    @Override
    protected void init() {


        super.init();
    }

    @Override
    public void close() {
        if(previousScreen != null){
            minecraft.setScreen(previousScreen);
        }
        super.close();
    }
}
