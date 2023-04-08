package be.ninedocteur.docmod.jobs.commands;

import be.ninedocteur.docmod.jobs.data.PlayerData;
import be.ninedocteur.docmod.jobs.network.PacketSendChatMessage;
import be.ninedocteur.docmod.jobs.network.PacketUpdateClientJob;
import be.ninedocteur.docmod.jobs.util.Constants;
import be.ninedocteur.docmod.network.PacketHandler;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.LongArgumentType;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;

public class CommandAdd {
	
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher)
	{
		String job;
		dispatcher.register(Commands.literal("jobs-add").requires((source)-> source.hasPermission(2))
			.then(Commands.argument("target", EntityArgument.player()).then(Commands.argument("job", StringArgumentType.string())
			.then(Commands.argument("xp", LongArgumentType.longArg(0, Constants.MAX_XP)).executes((ctx) ->
				{
					addXP(ctx.getSource(), EntityArgument.getPlayer(ctx, "target"), StringArgumentType.getString(ctx, "job"), LongArgumentType.getLong(ctx, "xp"));
					return 0;
				}))))
			);
	}

	
	public static void addXP(CommandSourceStack source, ServerPlayer target, String jobArgument, long xp)
	{
		PlayerData.getPlayerJobs(target).gainXP(Constants.Job.valueOf(jobArgument), xp, target);
		Constants.Job job = Constants.Job.valueOf(jobArgument);
        //PacketHandler.INSTANCE.sendTo(new PacketUpdateClientJob(PlayerData.getPlayerJobs(target).toTotalXPs()), target.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
        
        if(source.getEntity() instanceof ServerPlayer)
        {
			ServerPlayer sender = (ServerPlayer)source.getEntity();
        	//PacketHandler.INSTANCE.sendTo(new PacketSendChatMessage(Component.literal(xp + " xp added to " + target.getName().getString() + " for job " + job.name())),
    			//	sender.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
        }
	}
}
