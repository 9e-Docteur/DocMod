package be.ninedocteur.docmod.client.gui.screens;

import be.ninedocteur.docmod.DMConfig;
import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.gui.title.DMTitleScreen;
import be.ninedocteur.docmod.common.init.DMItems;
import be.ninedocteur.docmod.data.DMServerData;
import be.ninedocteur.docmod.data.json.DMServer;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;

import be.ninedocteur.docmod.utils.ColorUtils;
import be.ninedocteur.docmod.utils.DMUtils;
import be.ninedocteur.docmod.utils.IOUtils;
import be.ninedocteur.docmod.utils.PlayerUtils;
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
public class DMServerScreen extends Screen {
    public static final CubeMap CUBE_MAP = new CubeMap(new ResourceLocation("textures/gui/title/background/panorama"));
    private static final ResourceLocation PANORAMA_OVERLAY = new ResourceLocation("textures/gui/title/background/panorama_overlay.png");
    private PanoramaRenderer panorama = new PanoramaRenderer(CUBE_MAP);
    private final Screen previousScreen;
    private long fadeInStart;
    
    private String onlineUsers, maxPlayers;
    public Button joinServer, cancel;
    private static Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
    private DMServerData data;

    public DMServerScreen(Screen previousScreen) {
        super( Component.translatable("narrator.screen.title"));
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
        super( Component.translatable("narrator.screen.title"));
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
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        float f = (float) (Util.getMillis() - this.fadeInStart);
        this.panorama.render(pPartialTick, Mth.clamp(f, 0.0F, 1.0F));
        drawCenteredString(pPoseStack, font,  Component.translatable("DocMod Server"), width/2, 10, 0xffffff);
        if(data != null) {
            drawCenteredString(pPoseStack, font,  Component.translatable("DocMod " + ChatFormatting.GOLD + data.getVersion() + ChatFormatting.RESET + " is needed to join the server!"), width/2, 20, 0xffffff);
            String versionToJoinTheServer = data.getVersion();
            String ourVersion = DocMod.VERSION;
            if(versionToJoinTheServer.equals(ourVersion)) {
                drawCenteredString(pPoseStack, font,  Component.translatable(ChatFormatting.GREEN + "Your are up-to-date! You are able to join the server!"), width/2, 30, 0xffffff);
            } else {
                drawCenteredString(pPoseStack, font,  Component.translatable(ChatFormatting.RED + "You can't join the server! You are not up-to-date, you are using DocMod " + ChatFormatting.BOLD + ChatFormatting.UNDERLINE + DocMod.VERSION + ChatFormatting.RESET + ChatFormatting.RED + "!"), width/2, 30, 0xffffff);
                drawCenteredString(pPoseStack, font,  Component.translatable(ChatFormatting.RED + "Please update DocMod to " + ChatFormatting.BOLD + ChatFormatting.UNDERLINE + data.getVersion() + ChatFormatting.RESET + ChatFormatting.RED  + "!"), width/2, 40, 0xffffff);
                joinServer.active = false;
           	 if(joinServer.isHoveredOrFocused()) {
                  	 this.renderTooltip(pPoseStack, Component.literal(ChatFormatting.RED + "Nein! Tu bist nicht up-to-date!"), pMouseX, pMouseY);
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
            	drawCenteredString(pPoseStack, font,  Component.translatable(ChatFormatting.RED + "No one on the server." + ChatFormatting.BLACK + " || " + ChatFormatting.RESET + "Player Online: " + ChatFormatting.GOLD + data.getOnlinePlayers() + ChatFormatting.RESET + "/" + ChatFormatting.GOLD + data.getSlots()), width/2, 50, 0xffffff);
            } else {
            	drawCenteredString(pPoseStack, font,  Component.translatable("Player Online: " + ChatFormatting.GOLD + data.getOnlinePlayers() + ChatFormatting.RESET + "/" + ChatFormatting.GOLD + data.getSlots()), width/2, 50, 0xffffff);
            }
            
            for(int i = 0; i < serverSlots; i++) {
             	boolean free;
             	RenderSystem.setShader(GameRenderer::getPositionTexShader);
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
                     	 this.renderTooltip(pPoseStack, Component.literal(data.getPlayers().get(i).getUsername()), pMouseX, pMouseY);
                      }
                    RenderSystem.setShaderTexture(0, getTexture(i));
                    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                    free = false;
                  
             	}
                 blit(pPoseStack, xCooord, yCooord, 0, 0, 16, 16, 16, 16);
                 
            }
            if(joinServer.isHoveredOrFocused()) {
            	RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1F);
           	 if(joinServer.active) {
           		this.renderTooltip(pPoseStack, Component.literal("Yeah my boy! Join us!"), pMouseX, pMouseY);
           	 }
           }
        } else {
        	drawCenteredString(pPoseStack, font,  Component.translatable(ChatFormatting.RED + "Server Offline!"), width/2, this.height/2, 0xffffff);
        	joinServer.active = false;
        	 if(joinServer.isHoveredOrFocused()) {
               	 this.renderTooltip(pPoseStack, Component.literal(ChatFormatting.RED + "Nein! Der Server ist nicht online!"), pMouseX, pMouseY);
               }
        }
        

        
        

        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }
    
    public ResourceLocation getTexture(int i) {
    	return PlayerUtils.getPlayerHead(data.getPlayers().get(i).getUsername());
    }

    @Override
    protected void init() {
        
        cancel = Button.builder(Component.translatable("gui.docmod.cancel"), (p_96781_) -> {
            this.getMinecraft().setScreen(new TitleScreen(this.passEvents));
        }).bounds(this.width /2 + 10, 210, 180, 20).build();
        joinServer = Button.builder(Component.translatable("gui.docmod.join"), (p_96781_) -> {
            ConnectScreen.startConnecting(this, this.getMinecraft(), new ServerAddress(Servers.HOST, Servers.PORT), new ServerData("DocMod Server", Servers.HOST, false));
        }).bounds(this.width /2 - cancel.getWidth(), 210, 180, 20).build();
      

        
        this.addRenderableWidget(cancel);
        this.addRenderableWidget(joinServer);



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
