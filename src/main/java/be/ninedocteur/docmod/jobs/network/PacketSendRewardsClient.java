package be.ninedocteur.docmod.jobs.network;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import be.ninedocteur.docmod.jobs.data.ClientInfos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

public class PacketSendRewardsClient{

    private final List<ItemStack> stacks = new ArrayList<>();

    public PacketSendRewardsClient(){}
    public PacketSendRewardsClient(List<ItemStack> rewards)
    {
        for(ItemStack s : rewards)
            stacks.add(s.copy());
    }


    public static PacketSendRewardsClient fromBytes(FriendlyByteBuf buf)
    {
    	PacketSendRewardsClient packet = new PacketSendRewardsClient();
        int size = buf.readInt();
        for(int i = 0; i < size; i++)
        {
            Item item = Item.byId(buf.readInt());
            int count = buf.readInt();
            packet.stacks.add(new ItemStack(item, count));
        }
        return packet;
    }


    public static void toBytes(PacketSendRewardsClient packet, FriendlyByteBuf buf)
    {
        buf.writeInt(packet.stacks.size());
        for(ItemStack s : packet.stacks)
        {
            buf.writeInt(Item.getId(s.getItem()));
            buf.writeInt(s.getCount());
        }

    }

    public static void handle(PacketSendRewardsClient m, Supplier<NetworkEvent.Context> ctx)
    {
        if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
        {
            ClientInfos.CURRENT_REWARDS.clear();
            for(ItemStack s : m.stacks)
                ClientInfos.CURRENT_REWARDS.add(s.copy());
        }
        ctx.get().setPacketHandled(true);
    }
}
