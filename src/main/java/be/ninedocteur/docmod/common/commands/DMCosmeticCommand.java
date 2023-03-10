package be.ninedocteur.docmod.common.commands;

import be.ninedocteur.docmod.client.gui.screens.CosmeticScreen;
import be.ninedocteur.docmod.utils.LaunchUtils;
import be.ninedocteur.docmod.utils.PlayerUtils;
import be.ninedocteur.docteam.api.DocTeamAPI;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class DMCosmeticCommand {
	public DMCosmeticCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("docmod").then(Commands.literal("cosmetics")).executes((command) -> {
            return exec(command.getSource());
        }));
    }
	
	 private int exec(CommandSourceStack source) throws CommandSyntaxException {
//		 String playerName = source.getPlayer().getName().toString();
//		 Player player = source.getPlayer();


		 Minecraft.getInstance().setScreen(new CosmeticScreen());
//		 if(player.getLevel().isClientSide){
//			 Minecraft.getInstance().setScreen(new CosmeticScreen());
//		 } else {
//			 source.sendSystemMessage(Component.literal("Cannot run this command on a server side!"));
//		 }
		 
//		 if(LaunchUtils.isRunningInDev()) {
//			 if(DocTeamAPI.isConnected()) {
//
//			 }
//		 } else {
//
//		 }
		 	return -1;
	    }
}
