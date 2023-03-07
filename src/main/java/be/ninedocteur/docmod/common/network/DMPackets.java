package be.ninedocteur.docmod.common.network;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.network.packets.FluidSyncS2CPacket;
import be.ninedocteur.docmod.common.network.packets.TardisDematPacket;
import be.ninedocteur.docmod.common.network.packets.TardisRematPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class DMPackets {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(DocMod.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
//        SimpleChannel net = NetworkRegistry.ChannelBuilder
//                .named(new ResourceLocation(DocMod.MOD_ID, "messages"))
//                .networkProtocolVersion(() -> "1.0")
//                .clientAcceptedVersions(s -> true)
//                .serverAcceptedVersions(s -> true)
//                .simpleChannel();
//
//        INSTANCE = net;


//        net.messageBuilder(FluidSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
//                .decoder(FluidSyncS2CPacket::new)
//                .encoder(FluidSyncS2CPacket::toBytes)
//                .consumerMainThread(FluidSyncS2CPacket::handle)
//                .add();
        INSTANCE.registerMessage(id(), TardisDematPacket.class, TardisDematPacket::toBytes, TardisDematPacket::fromBytes, TardisDematPacket::handle);
        INSTANCE.registerMessage(id(), TardisRematPacket.class, TardisRematPacket::toBytes, TardisRematPacket::fromBytes, TardisRematPacket::handle);
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void sendToClients(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }


}
