package fr.ninedocteur.docmod.client.gui.screens;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.blaze3d.systems.RenderSystem;
import fr.ninedocteur.docmod.DocMod;
import fr.ninedocteur.docmod.data.DMServerData;
import fr.ninedocteur.docmod.utils.IOUtils;
import fr.ninedocteur.docmod.utils.PlayerUtils;
import fr.ninedocteur.docteam.Servers;
import net.minecraft.client.gui.CubeMapRenderer;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.client.gui.screen.ConnectScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;

public class DMServerScreen extends Screen {
    public static final CubeMapRenderer CUBE_MAP = new CubeMapRenderer(new Identifier("textures/gui/title/background/panorama"));
    private static final Identifier PANORAMA_OVERLAY = new Identifier("textures/gui/title/background/panorama_overlay.png");
    private RotatingCubeMapRenderer panorama = new RotatingCubeMapRenderer(CUBE_MAP);
    private final Screen previousScreen;
    private long fadeInStart;
    
    private String onlineUsers, maxPlayers;
    public ButtonWidget joinServer, cancel;
    private static Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
    private DMServerData data;

    public DMServerScreen(Screen previousScreen) {
        super( Text.translatable("narrator.screen.title"));
        this.previousScreen = previousScreen;
        (new Thread(new Runnable() {
			
			@Override
			public void run() {
				String jsonData = IOUtils.readURLContent("http://130.61.36.120/docteam/play.php");
				data = GSON.fromJson(jsonData, DMServerData.class);
				onlineUsers = data.getOnlinePlayers();
		        maxPlayers = data.getSlots();
		        joinServer.active = true;
			}
		})).start();
    }

    public DMServerScreen() {
        super( Text.translatable("narrator.screen.title"));
        this.previousScreen = null;
    }
    
    @Override
    public void tick() {
    	super.tick();
    	if(System.currentTimeMillis() % 15000 == 0) {
    		onlineUsers = data.getOnlinePlayers();
            maxPlayers = data.getSlots();
        }
    }

    @Override
    public void render(MatrixStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        float f = (float) (Util.getMeasuringTimeMs() - this.fadeInStart);
        this.panorama.render(pPartialTick, MathHelper.clamp(f, 0.0F, 1.0F));
        drawCenteredTextWithShadow(pPoseStack, textRenderer,  Text.translatable("DocMod Server"), width/2, 10, 0xffffff);
        if(data != null) {
            drawCenteredTextWithShadow(pPoseStack, textRenderer,  Text.translatable("DocMod " + Formatting.GOLD + data.getVersion() + Formatting.RESET + " is needed to join the server!"), width/2, 20, 0xffffff);
            String versionToJoinTheServer = data.getVersion();
            String ourVersion = DocMod.VERSION;
            if(versionToJoinTheServer.equals(ourVersion)) {
                drawCenteredTextWithShadow(pPoseStack, textRenderer,  Text.translatable(Formatting.GREEN + "Your are up-to-date! You are able to join the server!"), width/2, 30, 0xffffff);
            } else {
                drawCenteredTextWithShadow(pPoseStack, textRenderer,  Text.translatable(Formatting.RED + "You can't join the server! You are not up-to-date, you are using DocMod " + Formatting.BOLD + Formatting.UNDERLINE + DocMod.VERSION + Formatting.RESET + Formatting.RED + "!"), width/2, 30, 0xffffff);
                drawCenteredTextWithShadow(pPoseStack, textRenderer,  Text.translatable(Formatting.RED + "Please update DocMod to " + Formatting.BOLD + Formatting.UNDERLINE + data.getVersion() + Formatting.RESET + Formatting.RED  + "!"), width/2, 40, 0xffffff);
                joinServer.active = false;
           	 if(joinServer.isSelected()) {
                  	 this.renderTooltip(pPoseStack, Text.literal(Formatting.RED + "Nein! Tu bist nicht up-to-date!"), pMouseX, pMouseY);
                  }
            }
            
            int serverSlots;
            serverSlots = Integer.valueOf(maxPlayers);
            
            if(serverSlots >= 72) {
            	serverSlots = 72;
            }
            
            if(Integer.valueOf(onlineUsers) > Integer.valueOf(maxPlayers)) {
            	serverSlots = Integer.valueOf(onlineUsers);
            }
            
            if(onlineUsers.equals("0")) {
            	drawCenteredTextWithShadow(pPoseStack, textRenderer,  Text.translatable(Formatting.RED + "No one on the server." + Formatting.BLACK + " || " + Formatting.RESET + "Player Online: " + Formatting.GOLD + data.getOnlinePlayers() + Formatting.RESET + "/" + Formatting.GOLD + data.getSlots()), width/2, 50, 0xffffff);
            } else {
                drawCenteredTextWithShadow(pPoseStack, textRenderer,  Text.translatable("Player Online: " + Formatting.GOLD + data.getOnlinePlayers() + Formatting.RESET + "/" + Formatting.GOLD + data.getSlots()), width/2, 50, 0xffffff);
            }
            
            for(int i = 0; i < serverSlots; i++) {
             	boolean free;
             	RenderSystem.setShader(GameRenderer::getPositionTexProgram);
             	int xCooord;
             	int yCooord;
             	if(serverSlots <= 20) {
             		xCooord = this.width / 2 - 50+ i % 5 * 20;
                 	yCooord = this.height / 2 - 60 + i / 5 * 20;
             	} else if(serverSlots <= 48) {
             		xCooord = this.width / 2 -90 + i % 8 * 24;
                 	yCooord = this.height / 2 - 60 + i / 8 * 24;
             	}else if(serverSlots <= 50) {
             		xCooord = this.width / 2 -115 + i % 10 * 24;
                 	yCooord = this.height / 2 - 60 + i / 10 * 24;
             	}else if(serverSlots <= 72) {
             		xCooord = this.width / 2 -140 + i % 12 * 24;
                 	yCooord = this.height / 2 - 60 + i / 12 * 24;
             	} else {
             		xCooord = this.width / 2 -140 + i % 12 * 24;
                 	yCooord = this.height / 2 - 60 + i / 12 * 24;
             	}
             	if(i >= data.getPlayers().size()) {
             		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.4F);
             		RenderSystem.setShaderTexture(0, PlayerUtils.getFreeSlotTexture());
             		free = true;
             	}else {
             		if(pMouseX >= xCooord && pMouseX <= xCooord + 16 && pMouseY >= yCooord && pMouseY <= yCooord + 16) {
                     	 this.renderTooltip(pPoseStack, Text.literal(data.getPlayers().get(i).getUsername()), pMouseX, pMouseY);
                      }
                    RenderSystem.setShaderTexture(0, getTexture(i));
                    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                    free = false;
                  
             	}
                 drawTexture(pPoseStack, xCooord, yCooord, 0, 0, 16, 16, 16, 16);
                 
            }
            if(joinServer.isSelected()) {
            	RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1F);
           	 if(joinServer.active) {
           		this.renderTooltip(pPoseStack, Text.literal("Yeah my boy! Join us!"), pMouseX, pMouseY);
           	 }
           }
        } else {
        	drawCenteredTextWithShadow(pPoseStack, textRenderer,  Text.translatable(Formatting.RED + "Server Offline!"), width/2, this.height/2, 0xffffff);
        	joinServer.active = false;
        	 if(joinServer.isSelected()) {
               	 this.renderTooltip(pPoseStack, Text.literal(Formatting.RED + "Nein! Der Server ist nicht online!"), pMouseX, pMouseY);
               }
        }
        

        
        

        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }
    
    public Identifier getTexture(int i) {
    	return PlayerUtils.getPlayerHead(data.getPlayers().get(i).getUsername());
    }

    @Override
    protected void init() {
        
        cancel = ButtonWidget.builder(Text.translatable("gui.docmod.cancel"), (p_96781_) -> {
            this.client.setScreen(new TitleScreen(this.passEvents));
        }).dimensions(this.width /2 + 10, 210, 180, 20).build();
        joinServer = ButtonWidget.builder(Text.translatable("gui.docmod.join"), (p_96781_) -> {
            ConnectScreen.connect(this, this.client, new ServerAddress(Servers.HOST, Servers.PORT), new ServerInfo("DocMod Server", Servers.HOST, false));
        }).dimensions(this.width /2 - cancel.getWidth(), 210, 180, 20).build();
      

        
        this.addDrawableChild(cancel);
        this.addDrawableChild(joinServer);



        super.init();
    }

    @Override
    public void close() {
        if(previousScreen != null){
            client.setScreen(previousScreen);
        }
        super.close();
    }
}
