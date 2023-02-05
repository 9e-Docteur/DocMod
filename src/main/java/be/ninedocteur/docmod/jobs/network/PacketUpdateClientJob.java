package be.ninedocteur.docmod.jobs.network;

import be.ninedocteur.docmod.jobs.data.ClientJobsData;
import be.ninedocteur.docmod.jobs.data.capabilities.PlayerJobs;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketUpdateClientJob{

    private PlayerJobs jobs = null;

    public PacketUpdateClientJob(){}

    /**
     * Constructs a packet containing the Jobs values
     * @param jobs the jobs to send to the client
     */
    public PacketUpdateClientJob(PlayerJobs jobs) {
        this.jobs = jobs;
    }



    /**
     * Reads the packet from the buffer
     * @param buf the buffer to read
     * @return the packet read
     */
    public static PacketUpdateClientJob fromBytes(FriendlyByteBuf buf) {
    	return new PacketUpdateClientJob(new PlayerJobs(buf));
    }


    /**
     * Writes the packet to the buffer
     * @param packet the packet to write
     * @param buf the buffer where to write
     */
    public static void toBytes(PacketUpdateClientJob packet, FriendlyByteBuf buf) {
        packet.jobs.writeToBytes(buf);
    }

    /**
     * Handles the packet on the client side by updating the Jobs on the client side
     * @param message the packet to handle
     * @param ctx the context of the packet
     */
    public static void handle(PacketUpdateClientJob message, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
            ClientJobsData.playerJobs = message.jobs;
        }
        ctx.get().setPacketHandled(true);
    }
}
