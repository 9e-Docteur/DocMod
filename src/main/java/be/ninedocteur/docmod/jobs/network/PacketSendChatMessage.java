package be.ninedocteur.docmod.jobs.network;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

public class PacketSendChatMessage {
	
	private final Component message;
	public PacketSendChatMessage(Component message)
	{
		this.message = message;
	}
	
	
	public static void toBytes(PacketSendChatMessage packet, FriendlyByteBuf buf)
	{
		buf.writeComponent(packet.message);
	}
	
	public static PacketSendChatMessage fromBytes(FriendlyByteBuf buf)
	{
		return new PacketSendChatMessage(buf.readComponent());
	}
	
	public static void handle(PacketSendChatMessage packet, Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
		{
			Minecraft.getInstance().gui.getChat().addMessage(packet.message);
		}
		ctx.get().setPacketHandled(true);
	}

}
