package fr.ninedocteur.docmod.client.gui.screens;

import java.net.InetSocketAddress;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import fr.ninedocteur.docmod.utils.ScreenUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.network.*;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.report.ReporterEnvironment;
import net.minecraft.client.util.NarratorManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.NetworkState;
import net.minecraft.network.packet.c2s.handshake.HandshakeC2SPacket;
import net.minecraft.network.packet.c2s.login.LoginHelloC2SPacket;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.logging.UncaughtExceptionLogger;
import org.slf4j.Logger;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.logging.LogUtils;


public class CommunityServerConnectScreen extends Screen {
	private static final AtomicInteger UNIQUE_THREAD_ID = new AtomicInteger(0);
	   static final Logger LOGGER = LogUtils.getLogger();
	   private static final long NARRATION_DELAY_MS = 2000L;
	   public static final Text UNKNOWN_HOST_MESSAGE = Text.translatable("disconnect.genericReason", Text.translatable("disconnect.unknownHost"));

	   volatile ClientConnection connection;
	   volatile boolean aborted;
	   final Screen parent;
	   private Text status = Text.translatable("connect.connecting");
	   private long lastNarration = -1L;
	   private static final Identifier BACKGROUND = new Identifier("docmod", "textures/gui/server/community/background.png");
	   private static Identifier LOADING;
	   public int frame;

	   private CommunityServerConnectScreen(Screen p_169263_) {
	      super(NarratorManager.EMPTY);
	      this.parent = p_169263_;
	   }

	   public static void startConnecting(Screen p_169268_, MinecraftClient p_169269_, ServerAddress p_169270_, ServerInfo p_169271_) {
	      CommunityServerConnectScreen CommunityServerConnectScreen = new CommunityServerConnectScreen(p_169268_);
	      p_169269_.disconnect();
	      p_169269_.loadBlockList();
	      p_169269_.ensureAbuseReportContext(ReporterEnvironment.ofThirdPartyServer(p_169271_ != null ? p_169271_.address : p_169270_.getAddress()));
	      p_169269_.setScreen(CommunityServerConnectScreen);
	      CommunityServerConnectScreen.connect(p_169269_, p_169270_, p_169271_);
	   }

	   private void connect(final MinecraftClient p_251955_, final ServerAddress p_249536_, final ServerInfo p_252078_) {
	      LOGGER.info("Connecting to {}, {}", p_249536_.getAddress(), p_249536_.getPort());
	      Thread thread = new Thread("Server Connector #" + UNIQUE_THREAD_ID.incrementAndGet()) {
	         public void run() {
	            InetSocketAddress inetsocketaddress = null;

	            try {
	               if (CommunityServerConnectScreen.this.aborted) {
	                  return;
	               }

	               Optional<InetSocketAddress> optional = AllowedAddressResolver.DEFAULT.resolve(p_249536_).map(Address::getInetSocketAddress);
	               if (CommunityServerConnectScreen.this.aborted) {
	                  return;
	               }

	               if (!optional.isPresent()) {
	                  p_251955_.execute(() -> {
	                     p_251955_.setScreen(new DisconnectedScreen(CommunityServerConnectScreen.this.parent, ScreenTexts.CONNECT_FAILED, CommunityServerConnectScreen.UNKNOWN_HOST_MESSAGE));
	                  });
	                  return;
	               }

	               inetsocketaddress = optional.get();
	               CommunityServerConnectScreen.this.connection = ClientConnection.connect(inetsocketaddress, p_251955_.options.useNativeTransport);
	               CommunityServerConnectScreen.this.connection.setPacketListener(new ClientLoginNetworkHandler(CommunityServerConnectScreen.this.connection, p_251955_, p_252078_, CommunityServerConnectScreen.this.parent, false, (Duration)null, CommunityServerConnectScreen.this::updateStatus));
	               CommunityServerConnectScreen.this.connection.send(new HandshakeC2SPacket(inetsocketaddress.getHostName(), inetsocketaddress.getPort(), NetworkState.LOGIN));
	               CommunityServerConnectScreen.this.connection.send(new LoginHelloC2SPacket(p_251955_.getSession().getUsername(), Optional.ofNullable(p_251955_.getSession().getUuidOrNull())));
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
	                  p_251955_.setScreen(new DisconnectedScreen(CommunityServerConnectScreen.this.parent, ScreenTexts.CONNECT_FAILED, Text.translatable("disconnect.genericReason", s)));
	               });
	            }

	         }
	      };
	      thread.setUncaughtExceptionHandler(new UncaughtExceptionLogger(LOGGER));
	      thread.start();
	   }

	   private void updateStatus(Text p_95718_) {
	      this.status = p_95718_;
	   }

	   public void tick() {
	      if (this.connection != null) {
	         if (this.connection.isOpen()) {
	            this.connection.tick();
	         } else {
	            this.connection.handleDisconnection();
	         }
	      }
	      
	      frame++;
	      LOADING = new Identifier("docmod", "textures/gui/server/community/load/" + frame + ".png");
	      if(frame > 100) {
	    	  frame = 0;
	      }

	   }

	   public boolean shouldCloseOnEsc() {
	      return false;
	   }

	   protected void init() {
	      this.addDrawableChild(ButtonWidget.builder(ScreenTexts.CANCEL, (p_95705_) -> {
	         this.aborted = true;
	         if (this.connection != null) {
	            this.connection.disconnect(Text.translatable("connect.aborted"));
	         }

	         this.client.setScreen(this.parent);
	      }).dimensions(this.width / 2 - 100, this.height / 4 + 120 + 12, 200, 20).build());
	   }

	   public void render(MatrixStack p_95700_, int p_95701_, int p_95702_, float p_95703_) {
	      //BACKGROUND
           RenderSystem.setShader(GameRenderer::getPositionTexProgram);
           RenderSystem.setShaderTexture(0, BACKGROUND);
           RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0f);
           ScreenUtils.fillWithFullTexture(0, 0, this.width, this.height);
           
         //LOAD
           RenderSystem.setShader(GameRenderer::getPositionTexProgram);
           RenderSystem.setShaderTexture(0, LOADING);
           RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0f);
           ScreenUtils.fillWithFullTexture(this.width / 4, this.height / 4, this.width /2 , this.height / 2);
	      long i = Util.getMeasuringTimeMs();
	      if (i - this.lastNarration > 2000L) {
	         this.lastNarration = i;
	         this.client.getNarratorManager().narrate(Text.translatable("narrator.joining"));
	      }

	      drawCenteredTextWithShadow(p_95700_, this.textRenderer, this.status, this.width / 2, this.height / 2 - 50, 16777215);
	      super.render(p_95700_, p_95701_, p_95702_, p_95703_);
	   }
}
