package be.ninedocteur.docmod.jobs.commands;

import be.ninedocteur.docmod.jobs.data.JobsInfo;
import be.ninedocteur.docmod.jobs.data.PlayerData;
import be.ninedocteur.docmod.jobs.util.Constants;
import be.ninedocteur.docmod.network.PacketHandler;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class CommandInfo {
	
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher)
	{
		dispatcher.register(Commands.literal("jobs-info")
				.requires((source) -> {return source.hasPermission(2);})
				.executes((ctx) -> 
					{
						if(!(ctx.getSource().getEntity() instanceof ServerPlayer)) return 0;
						info(ctx.getSource(), (ServerPlayer)ctx.getSource().getEntity());
						return 0;
					})
				.then(Commands.argument("target", EntityArgument.player()).executes((ctx)->
					{
						info(ctx.getSource(), EntityArgument.getPlayer(ctx, "target"));
						return 0;
					})
				));
	}
	
	
	private static void info(CommandSourceStack source, ServerPlayer target)
	{
		if(!(source.getEntity() instanceof ServerPlayer)) return;
		ServerPlayer sender = (ServerPlayer)source.getEntity();
		JobsInfo infos = PlayerData.getPlayerJobs(target);
		if(sender.getGameProfile().getId().equals(target.getGameProfile().getId()))
			PacketHandler.sendMessageToClient(sender, Component.literal(ChatFormatting.BLUE + "Your Stats"));
		else
			PacketHandler.sendMessageToClient(sender, Component.literal(ChatFormatting.LIGHT_PURPLE + "Stats of " + ChatFormatting.BLUE + target.getName().getString()));
        for(int i = 0; i < 4; i++)
        {
            int lvl = infos.getLevelByJob(Constants.Job.byIndex(i));
            long xp = infos.getXPByJob(Constants.Job.byIndex(i));
            PacketHandler.sendMessageToClient(sender, Component.literal(ChatFormatting.LIGHT_PURPLE + Constants.Job.byIndex(i).name() + " : lvl " +
                    ChatFormatting.BLUE + lvl + ChatFormatting.LIGHT_PURPLE + ", xp " + ChatFormatting.BLUE + xp));
        }
	}

}
