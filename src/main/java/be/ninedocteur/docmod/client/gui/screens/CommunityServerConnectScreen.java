package be.ninedocteur.docmod.client.gui.screens;

import java.net.InetSocketAddress;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Nullable;

import org.slf4j.Logger;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.logging.LogUtils;

import be.ninedocteur.docmod.utils.ScreenUtils;
import net.minecraft.DefaultUncaughtExceptionHandler;
import net.minecraft.Util;
import net.minecraft.client.GameNarrator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.DisconnectedScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientHandshakePacketListenerImpl;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.chat.report.ReportEnvironment;
import net.minecraft.client.multiplayer.resolver.ResolvedServerAddress;
import net.minecraft.client.multiplayer.resolver.ServerAddress;
import net.minecraft.client.multiplayer.resolver.ServerNameResolver;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.Connection;
import net.minecraft.network.ConnectionProtocol;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.handshake.ClientIntentionPacket;
import net.minecraft.network.protocol.login.ServerboundHelloPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CommunityServerConnectScreen extends Screen{
	private static final AtomicInteger UNIQUE_THREAD_ID = new AtomicInteger(0);
	   static final Logger LOGGER = LogUtils.getLogger();
	   private static final long NARRATION_DELAY_MS = 2000L;
	   public static final Component UNKNOWN_HOST_MESSAGE = Component.translatable("disconnect.genericReason", Component.translatable("disconnect.unknownHost"));
	   @Nullable
	   volatile Connection connection;
	   volatile boolean aborted;
	   final Screen parent;
	   private Component status = Component.translatable("connect.connecting");
	   private long lastNarration = -1L;
	   private static final ResourceLocation BACKGROUND = new ResourceLocation("docmod", "textures/gui/server/community/background.png");
	   private static ResourceLocation LOADING;
	   public int frame;

	   private CommunityServerConnectScreen(Screen p_169263_) {
	      super(GameNarrator.NO_TITLE);
	      this.parent = p_169263_;
	   }

	   public static void startConnecting(Screen p_169268_, Minecraft p_169269_, ServerAddress p_169270_, ServerData p_169271_) {
	      CommunityServerConnectScreen CommunityServerConnectScreen = new CommunityServerConnectScreen(p_169268_);
	      p_169269_.clearLevel();
	      p_169269_.prepareForMultiplayer();
	      p_169269_.updateReportEnvironment(ReportEnvironment.thirdParty(p_169271_ != null ? p_169271_.ip : p_169270_.getHost()));
	      p_169269_.setScreen(CommunityServerConnectScreen);
	      CommunityServerConnectScreen.connect(p_169269_, p_169270_, p_169271_);
	   }

	   private void connect(final Minecraft p_251955_, final ServerAddress p_249536_, @Nullable final ServerData p_252078_) {
	      LOGGER.info("Connecting to {}, {}", p_249536_.getHost(), p_249536_.getPort());
	      Thread thread = new Thread("Server Connector #" + UNIQUE_THREAD_ID.incrementAndGet()) {
	         public void run() {
	            InetSocketAddress inetsocketaddress = null;

	            try {
	               if (CommunityServerConnectScreen.this.aborted) {
	                  return;
	               }

	               Optional<InetSocketAddress> optional = ServerNameResolver.DEFAULT.resolveAddress(p_249536_).map(ResolvedServerAddress::asInetSocketAddress);
	               if (CommunityServerConnectScreen.this.aborted) {
	                  return;
	               }

	               if (!optional.isPresent()) {
	                  p_251955_.execute(() -> {
	                     p_251955_.setScreen(new DisconnectedScreen(CommunityServerConnectScreen.this.parent, CommonComponents.CONNECT_FAILED, CommunityServerConnectScreen.UNKNOWN_HOST_MESSAGE));
	                  });
	                  return;
	               }

	               inetsocketaddress = optional.get();
	               CommunityServerConnectScreen.this.connection = Connection.connectToServer(inetsocketaddress, p_251955_.options.useNativeTransport());
	               CommunityServerConnectScreen.this.connection.setListener(new ClientHandshakePacketListenerImpl(CommunityServerConnectScreen.this.connection, p_251955_, p_252078_, CommunityServerConnectScreen.this.parent, false, (Duration)null, CommunityServerConnectScreen.this::updateStatus));
	               CommunityServerConnectScreen.this.connection.send(new ClientIntentionPacket(inetsocketaddress.getHostName(), inetsocketaddress.getPort(), ConnectionProtocol.LOGIN));
	               CommunityServerConnectScreen.this.connection.send(new ServerboundHelloPacket(p_251955_.getUser().getName(), Optional.ofNullable(p_251955_.getUser().getProfileId())));
	            } catch (Exception exception2) {
	               if (CommunityServerConnectScreen.this.aborted) {
	                  return;
	               }

	               Throwable throwable = exception2.getCause();
	               Exception exception;
	               if (throwable instanceof Exception exception1) {
	                  exception = exception1;
	               } else {
	                  exception = exception2;
	               }

	               CommunityServerConnectScreen.LOGGER.error("Couldn't connect to server", (Throwable)exception2);
	               String s = inetsocketaddress == null ? exception.getMessage() : exception.getMessage().replaceAll(inetsocketaddress.getHostName() + ":" + inetsocketaddress.getPort(), "").replaceAll(inetsocketaddress.toString(), "");
	               p_251955_.execute(() -> {
	                  p_251955_.setScreen(new DisconnectedScreen(CommunityServerConnectScreen.this.parent, CommonComponents.CONNECT_FAILED, Component.translatable("disconnect.genericReason", s)));
	               });
	            }

	         }
	      };
	      thread.setUncaughtExceptionHandler(new DefaultUncaughtExceptionHandler(LOGGER));
	      thread.start();
	   }

	   private void updateStatus(Component p_95718_) {
	      this.status = p_95718_;
	   }

	   public void tick() {
	      if (this.connection != null) {
	         if (this.connection.isConnected()) {
	            this.connection.tick();
	         } else {
	            this.connection.handleDisconnection();
	         }
	      }
	      
	      frame++;
	      LOADING = new ResourceLocation("docmod", "textures/gui/server/community/load/" + frame + ".png");
	      if(frame > 100) {
	    	  frame = 0;
	      }

	   }

	   public boolean shouldCloseOnEsc() {
	      return false;
	   }

	   protected void init() {
	      this.addRenderableWidget(Button.builder(CommonComponents.GUI_CANCEL, (p_95705_) -> {
	         this.aborted = true;
	         if (this.connection != null) {
	            this.connection.disconnect(Component.translatable("connect.aborted"));
	         }

	         this.minecraft.setScreen(this.parent);
	      }).bounds(this.width / 2 - 100, this.height / 4 + 120 + 12, 200, 20).build());
	   }

	   public void render(PoseStack p_95700_, int p_95701_, int p_95702_, float p_95703_) {
	      //BACKGROUND
           RenderSystem.setShader(GameRenderer::getPositionTexShader);
           RenderSystem.setShaderTexture(0, BACKGROUND);
           RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0f);
           ScreenUtils.fillWithFullTexture(0, 0, this.width, this.height);
           
         //LOAD
           RenderSystem.setShader(GameRenderer::getPositionTexShader);
           RenderSystem.setShaderTexture(0, LOADING);
           RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0f);
           ScreenUtils.fillWithFullTexture(this.width / 4, this.height / 4, this.width /2 , this.height / 2);
	      long i = Util.getMillis();
	      if (i - this.lastNarration > 2000L) {
	         this.lastNarration = i;
	         this.minecraft.getNarrator().sayNow(Component.translatable("narrator.joining"));
	      }

	      drawCenteredString(p_95700_, this.font, this.status, this.width / 2, this.height / 2 - 50, 16777215);
	      super.render(p_95700_, p_95701_, p_95702_, p_95703_);
	   }
}
