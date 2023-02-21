package be.ninedocteur.docmod.jobs.commands;

import be.ninedocteur.docmod.jobs.data.PlayerData;
import be.ninedocteur.docmod.jobs.network.PacketSendChatMessage;
import be.ninedocteur.docmod.jobs.network.PacketUpdateClientJob;
import be.ninedocteur.docmod.jobs.util.Constants;
import be.ninedocteur.docmod.jobs.util.handler.PacketHandler;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.LongArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;


public class CommandSet {
	
	
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher)
	{
		dispatcher.register(Commands.literal("jobs-set")
			.requires((source) -> {return source.hasPermission(2);})
			.then(Commands.argument("target", EntityArgument.player()).then(Commands.argument("job", StringArgumentType.string())
			.then(Commands.argument("total xp", LongArgumentType.longArg(0, Constants.MAX_XP)).executes((ctx) ->
				{
					setJobs(ctx.getSource(), EntityArgument.getPlayer(ctx, "target"), StringArgumentType.getString(ctx, "job"), LongArgumentType.getLong(ctx, "total xp"));
					return 0;
				}))))
			.then(Commands.argument("target", EntityArgument.player()).then(Commands.argument("job", StringArgumentType.string())
					.then(Commands.argument("level", IntegerArgumentType.integer(0, 25)).then(Commands.argument("xp", LongArgumentType.longArg(0, Constants.MAX_XP)).executes((ctx) ->
						{
							setJobs(ctx.getSource(), EntityArgument.getPlayer(ctx, "target"), StringArgumentType.getString(ctx, "job"), IntegerArgumentType.getInteger(ctx, "level"), LongArgumentType.getLong(ctx, "xp"));
							return 0;
						})))))
			);
	}
	
	private static void setJobs(CommandSourceStack source, ServerPlayer target, String jobArgument, int lvl, long xp)
	{
		setJobs(source, target, jobArgument, Constants.TOTAL_XP_BY_LEVEL[lvl] + xp);
	}
	
	private static void setJobs(CommandSourceStack source, ServerPlayer target, String jobArgument, long total)
	{
		PlayerData.getPlayerJobs(target).set(Constants.Job.valueOf(jobArgument), total);
        PacketHandler.INSTANCE.sendTo(new PacketUpdateClientJob(PlayerData.getPlayerJobs(target).toTotalXPs()), target.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
        
        if(source.getEntity() instanceof ServerPlayer)
        {
        	ServerPlayer sender = (ServerPlayer)source.getEntity();
        	int lvl = PlayerData.getPlayerJobs(target).getLevelByJob(Constants.Job.valueOf(jobArgument));
        	long xp = PlayerData.getPlayerJobs(target).getXPByJob(Constants.Job.valueOf(jobArgument));
        	PacketHandler.INSTANCE.sendTo(new PacketSendChatMessage(Component.literal("Job " + Constants.Job.valueOf(jobArgument).name() + " of " + target.getDisplayName().getString() + " set to lvl " + lvl + ", xp " + xp)),
        			sender.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
        }
	}

}
