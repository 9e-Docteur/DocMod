package be.ninedocteur.docmod.network.packet;

import be.ninedocteur.docmod.jobs.data.ClientInfos;
import be.ninedocteur.docmod.jobs.network.PacketAddXP;
import be.ninedocteur.docmod.jobs.util.Constants;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class GetRankPacket {
    private UUID serverPlayerUUID;

    public GetRankPacket(){}
    public GetRankPacket(UUID serverPlayerUUID)
    {
        this.serverPlayerUUID = serverPlayerUUID;
    }

    public static GetRankPacket fromBytes(FriendlyByteBuf buf)
    {
        return new GetRankPacket(buf.readUUID());
    }


    public static void toBytes(GetRankPacket packet, FriendlyByteBuf buf)
    {
        buf.writeUUID(packet.serverPlayerUUID);
    }


    public static void handle(GetRankPacket message, Supplier<NetworkEvent.Context> ctx)
    {
        if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
        {

        }
        ctx.get().setPacketHandled(true);
    }
}
