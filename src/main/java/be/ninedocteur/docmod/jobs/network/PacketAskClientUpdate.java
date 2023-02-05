package be.ninedocteur.docmod.jobs.network;

import be.ninedocteur.docmod.jobs.data.capabilities.PlayerData;
import be.ninedocteur.docmod.jobs.util.handler.PacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketAskClientUpdate{

    public static final PacketAskClientUpdate instance = new PacketAskClientUpdate();


    /**
     * Reads the packet from the buffer (actually returns the default instance)
     * @param buf the buffer to read
     * @return the default instance
     */
        public static PacketAskClientUpdate fromBytes(FriendlyByteBuf buf){
        return instance;
    }


    /**
     * Writes the packet to the buffer
     * @param packet the packet to write
     * @param buf the buffer where to write
     */
    public static void toBytes(PacketAskClientUpdate packet, FriendlyByteBuf buf){
    }

    /**
     * Handles the packet on the server by sending back the jobs to the client
     * @param message the packet to handle
     * @param ctx the context of the packet
     */
    public static void handle(PacketAskClientUpdate message, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER &&
            ctx.get().getSender() != null) {
            PacketHandler.INSTANCE.sendTo(new PacketUpdateClientJob(PlayerData.getPlayerJobs(ctx.get().getSender())),
                                          ctx.get().getNetworkManager(),
                                          NetworkDirection.PLAY_TO_CLIENT);
        }
        ctx.get().setPacketHandled(true);
    }
}
