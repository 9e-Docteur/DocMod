package be.ninedocteur.docmod.jobs.network;

import be.ninedocteur.docmod.jobs.data.ClientInfos;
import be.ninedocteur.docmod.jobs.util.Constants;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketAddXP{

    private int job;
    private long xpAdded;
    public PacketAddXP(){}
    public PacketAddXP(Constants.Job j, long xp)
    {
        this.job = j.index;
        this.xpAdded = xp;
    }

    public static PacketAddXP fromBytes(FriendlyByteBuf buf)
    {
    	return new PacketAddXP(Constants.Job.byIndex(buf.readInt()), buf.readLong());
	}


    public static void toBytes(PacketAddXP packet, FriendlyByteBuf buf)
    {
        buf.writeInt(packet.job);
        buf.writeLong(packet.xpAdded);
    }


    public static void handle(PacketAddXP message, Supplier<NetworkEvent.Context> ctx)
    {
        if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
        {
            ClientInfos.showAddGui(Constants.Job.byIndex(message.job), message.xpAdded);
        }
        ctx.get().setPacketHandled(true);
    }
}
