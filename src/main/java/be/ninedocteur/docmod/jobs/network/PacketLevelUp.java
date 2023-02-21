package be.ninedocteur.docmod.jobs.network;

import be.ninedocteur.docmod.jobs.data.ClientInfos;
import be.ninedocteur.docmod.jobs.util.Constants;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketLevelUp{

    private Constants.Job job = Constants.Job.NONE;

    public PacketLevelUp(){}
    public PacketLevelUp(Constants.Job j)
    {
        this.job = j;
    }

    public static PacketLevelUp fromBytes(FriendlyByteBuf buf)
    {
        return new PacketLevelUp(Constants.Job.byIndex(buf.readInt()));
    }

    public static void toBytes(PacketLevelUp msg, FriendlyByteBuf buf)
    {
        buf.writeInt(msg.job.index);
    }

    public static void handle(PacketLevelUp message, Supplier<NetworkEvent.Context> ctx)
    {
        if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
        {
            if(Minecraft.getInstance().player == null) return;
            ClientInfos.showLevelUpGui(message.job);
        }
        ctx.get().setPacketHandled(true);
    }
}
