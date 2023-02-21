package be.ninedocteur.docmod.jobs.network;

import be.ninedocteur.docmod.jobs.data.ClientInfos;
import be.ninedocteur.docmod.jobs.data.JobsInfo;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketUpdateClientJob{

    private long[] xps = new long[]{0, 0, 0, 0};

    public PacketUpdateClientJob(){}
    public PacketUpdateClientJob(long[] xp_values)
    {
        this.xps = xp_values;
    }



    public static PacketUpdateClientJob fromBytes(FriendlyByteBuf buf)
    {
    	PacketUpdateClientJob packet = new PacketUpdateClientJob();
        for(int i = 0; i < 4; i++)
            packet.xps[i] = buf.readLong();
        return packet;
    }


    public static void toBytes(PacketUpdateClientJob packet, FriendlyByteBuf buf)
    {
        for(int i = 0; i < 4; i++)
            buf.writeLong(packet.xps[i]);
    }

    public static void handle(PacketUpdateClientJob message, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
        {
            ClientInfos.job = new JobsInfo().fromTotalXPs(message.xps);
        }

        ctx.get().setPacketHandled(true);
    }
}
