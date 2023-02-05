package be.ninedocteur.docmod.jobs.network;

import be.ninedocteur.docmod.jobs.data.ClientJobsData;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;

public class PacketLevelUp{

    private String job;

    public PacketLevelUp(){}

    /**
     * Constructs a packet
     * @param j the job contained in the packet
     */
    public PacketLevelUp(String j) {
        this.job = j;
    }

    /**
     * Reads the packet from the buffer
     * @param buf the buffer to read
     * @return the packet read
     */
    public static PacketLevelUp fromBytes(FriendlyByteBuf buf) {
        int length = buf.readInt();
        return new PacketLevelUp(buf.readUtf(length));
    }

    /**
     * Writes the packet to the buffer
     * @param packet the packet to write
     * @param buf the buffer where to write
     */
    public static void toBytes(PacketLevelUp packet, FriendlyByteBuf buf) {
        buf.writeInt(packet.job.getBytes(StandardCharsets.UTF_8).length);
        buf.writeUtf(packet.job);
    }

    /**
     * Handles the packet on the client side by showing the Level Up GUI
     * @param message the packet to handle
     * @param ctx the context of the packet
     */
    public static void handle(PacketLevelUp message, Supplier<NetworkEvent.Context> ctx) {
        if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
            if(Minecraft.getInstance().player == null)
                return;
            ClientJobsData.showLevelUpGui(message.job);
        }
        ctx.get().setPacketHandled(true);
    }
}
