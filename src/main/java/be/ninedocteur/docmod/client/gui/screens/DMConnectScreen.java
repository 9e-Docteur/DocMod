package be.ninedocteur.docmod.client.gui.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.logging.LogUtils;
import net.minecraft.DefaultUncaughtExceptionHandler;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.ConnectScreen;
import net.minecraft.client.gui.screens.DisconnectedScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientHandshakePacketListenerImpl;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.chat.report.ReportEnvironment;
import net.minecraft.client.multiplayer.resolver.ResolvedServerAddress;
import net.minecraft.client.multiplayer.resolver.ServerAddress;
import net.minecraft.client.multiplayer.resolver.ServerNameResolver;
import net.minecraft.network.Connection;
import net.minecraft.network.ConnectionProtocol;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.handshake.ClientIntentionPacket;
import net.minecraft.network.protocol.login.ServerboundHelloPacket;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.net.InetSocketAddress;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@OnlyIn(Dist.CLIENT)
public class DMConnectScreen{ //} extends Screen {
//    private static final AtomicInteger UNIQUE_THREAD_ID = new AtomicInteger(0);
//    static final Logger LOGGER = LogUtils.getLogger();
//    private static final long NARRATION_DELAY_MS = 2000L;
//    public static final Component UNKNOWN_HOST_MESSAGE = Component.translatable("This server doesn't exist.", Component.translatable("This server doesn't exist."));
//    @Nullable
//    volatile Connection connection;
//    volatile boolean aborted;
//    final Screen parent;
//    private Component status = Component.translatable("Connecting to the communauty server... Please wait...");
//    private long lastNarration = -1L;
//
//    private DMConnectScreen(Screen p_169263_) {
//        super(Component.empty());
//        this.parent = p_169263_;
//    }
//
//    public static void startConnecting(Screen p_169268_, ServerAddress p_169270_, @Nullable ServerData p_169271_) {
//        DMConnectScreen connectscreen = new DMConnectScreen(p_169268_);
//        Minecraft mc = Minecraft.getInstance();
//        mc.clearLevel();
//        mc.prepareForMultiplayer();
//        mc.updateReportEnvironment(ReportEnvironment.thirdParty(p_169271_ != null ? p_169271_.ip : p_169270_.getHost()));
//        mc.setScreen(connectscreen);
//        connectscreen.connect(mc, p_169270_);
//    }
//
//    private void connect(final Minecraft p_169265_, final ServerAddress p_169266_) {
//        LOGGER.info("Connecting to {}, {}", p_169266_.getHost(), p_169266_.getPort());
//        Thread thread = new Thread("Server Connector #" + UNIQUE_THREAD_ID.incrementAndGet()) {
//            public void run() {
//                InetSocketAddress inetsocketaddress = null;
//
//                try {
//                    if (DMConnectScreen.this.aborted) {
//                        return;
//                    }
//
//                    Optional<InetSocketAddress> optional = ServerNameResolver.DEFAULT.resolveAddress(p_169266_).map(ResolvedServerAddress::asInetSocketAddress);
//                    if (DMConnectScreen.this.aborted) {
//                        return;
//                    }
//
//                    if (!optional.isPresent()) {
//                        p_169265_.execute(() -> {
//                            p_169265_.setScreen(new DisconnectedScreen(DMConnectScreen.this.parent, Component.translatable("Cannot connect to the communuty server."), DMConnectScreen.UNKNOWN_HOST_MESSAGE));
//                        });
//                        return;
//                    }
//
//                    inetsocketaddress = optional.get();
//                    DMConnectScreen.this.connection = Connection.connectToServer(inetsocketaddress, p_169265_.options.useNativeTransport());
//                    DMConnectScreen.this.connection.setListener(new ClientHandshakePacketListenerImpl(DMConnectScreen.this.connection, minecraft, ser, DMConnectScreen.this.parent, false, (Duration)null, DMConnectScreen.this::updateStatus));
//                    DMConnectScreen.this.connection.send(new ClientIntentionPacket(inetsocketaddress.getHostName(), inetsocketaddress.getPort(), ConnectionProtocol.LOGIN));
//                    //DMConnectScreen.this.connection.send(new ServerboundHelloPacket(p_169265_.getUser().getName(), p_169265_.getProfileKeyPairManager().profilePublicKeyData()));
//                } catch (Exception exception2) {
//                    if (DMConnectScreen.this.aborted) {
//                        return;
//                    }
//
//                    Throwable throwable = exception2.getCause();
//                    Exception exception;
//                    if (throwable instanceof Exception exception1) {
//                        exception = exception1;
//                    } else {
//                        exception = exception2;
//                    }
//
//                    DMConnectScreen.LOGGER.error("Couldn't connect to server", (Throwable)exception2);
//                    String s = inetsocketaddress == null ? exception.getMessage() : exception.getMessage().replaceAll(inetsocketaddress.getHostName() + ":" + inetsocketaddress.getPort(), "").replaceAll(inetsocketaddress.toString(), "");
//                    p_169265_.execute(() -> {
//                        p_169265_.setScreen(new DisconnectedScreen(DMConnectScreen.this.parent, CommonComponents.CONNECT_FAILED, Component.translatable("disconnect.genericReason", s)));
//                    });
//                }
//
//            }
//        };
//        thread.setUncaughtExceptionHandler(new DefaultUncaughtExceptionHandler(LOGGER));
//        thread.start();
//    }
//
//    private void updateStatus(Component p_95718_) {
//        this.status = p_95718_;
//    }
//
//    public void tick() {
//        if (this.connection != null) {
//            if (this.connection.isConnected()) {
//                this.connection.tick();
//            } else {
//                this.connection.handleDisconnection();
//            }
//        }
//
//    }
//
//    public boolean shouldCloseOnEsc() {
//        return false;
//    }
//
//    protected void init() {
//        this.addRenderableWidget(new Button(this.width / 2 - 100, this.height / 4 + 120 + 12, 200, 20, CommonComponents.GUI_CANCEL, (p_95705_) -> {
//            this.aborted = true;
//            if (this.connection != null) {
//                this.connection.disconnect(Component.translatable("connect.aborted"));
//            }
//
//            this.minecraft.setScreen(this.parent);
//        }));
//    }
//
//    public void render(PoseStack p_95700_, int p_95701_, int p_95702_, float p_95703_) {
//        this.renderBackground(p_95700_);
//        long i = Util.getMillis();
//        if (i - this.lastNarration > 2000L) {
//            this.lastNarration = i;
//            //NarratorChatListener.INSTANCE.sayNow(Component.translatable("narrator.joining"));
//        }
//
//        drawCenteredString(p_95700_, this.font, this.status, this.width / 2, this.height / 2 - 50, 16777215);
//        super.render(p_95700_, p_95701_, p_95702_, p_95703_);
//    }
}
