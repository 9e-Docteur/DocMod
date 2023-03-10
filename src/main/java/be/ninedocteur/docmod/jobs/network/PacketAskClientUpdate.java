package be.ninedocteur.docmod.jobs.network;

import java.util.function.Supplier;

import be.ninedocteur.docmod.jobs.data.PlayerData;
import be.ninedocteur.docmod.network.PacketHandler;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import net.minecraft.network.FriendlyByteBuf;

public class PacketAskClientUpdate{


    public PacketAskClientUpdate(){}



    public static PacketAskClientUpdate fromBytes(FriendlyByteBuf buf){return new PacketAskClientUpdate();}


    public static void toBytes(PacketAskClientUpdate packet, FriendlyByteBuf buf){}

    public static void handle(PacketAskClientUpdate message, Supplier<NetworkEvent.Context> ctx)
    {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
        {
            PacketHandler.INSTANCE.sendTo(new PacketUpdateClientJob(PlayerData.getPlayerJobs(ctx.get().getSender()).toTotalXPs()), ctx.get().getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
        }
        ctx.get().setPacketHandled(true);
    }
}
