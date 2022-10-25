package be.ninedocteur.docmod.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import be.ninedocteur.docmod.utils.LaunchUtils;
import be.ninedocteur.docmod.utils.PlayerUtils;
import be.ninedocteur.docteam.api.DocTeamAPI;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.client.renderer.MultiBufferSource;

public class DMCapeCommand {
	public DMCapeCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("cape").then(Commands.literal("set").then(Commands.argument("playerName", EntityArgument.player())).executes((command) -> {
            return cape(command.getSource(), EntityArgument.getPlayer(command, "playerName"));
        })));
    }
	
	 private int cape(CommandSourceStack source, ServerPlayer serverPlayer) throws CommandSyntaxException {
		 String playerName = serverPlayer.getName().toString();
		 String playerUUID = PlayerUtils.getUserUUID(playerName);
		 
		 if(LaunchUtils.isRunningInDev()) {
			 if(DocTeamAPI.isConnected()) {
				 
			 }
		 } else {
			 
		 }
		 	return -1;
	    }
}
