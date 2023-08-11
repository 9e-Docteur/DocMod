package be.ninedocteur.docmod.network;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.network.packets.SyncDimensionListPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class DMPackets {
    private static int CURRENT_ID = 0;
    public static final String VERSION = "1.0";
    public static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(DocMod.MOD_ID, "channel"))
            .networkProtocolVersion(() -> VERSION)
            .clientAcceptedVersions(s -> true)
            .serverAcceptedVersions(s -> true)
            .simpleChannel();


    public static void registerPackets() {
        CHANNEL.messageBuilder(SyncDimensionListPacket.class, getCurrentId())
                .decoder(SyncDimensionListPacket::new)
                .encoder(SyncDimensionListPacket::encode)
                .consumerMainThread(SyncDimensionListPacket::handle)
                .add();
    }

    public static void sendPacketToAll(Object packet) {
        CHANNEL.send(PacketDistributor.ALL.noArg(), packet);
    }

    public static int getCurrentId() {
        return CURRENT_ID++;
    }
}
