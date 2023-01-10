package be.ninedocteur.docmod.common.commands;

import be.ninedocteur.docmod.common.tileentity.TardisTileEntity;
import be.ninedocteur.docmod.utils.LaunchUtils;
import be.ninedocteur.docmod.utils.PlayerUtils;
import be.ninedocteur.docteam.api.DocTeamAPI;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class DMTardisPowerCommand {
//	public DMTardisPowerCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
//        dispatcher.register(Commands.literal("tardis").then(Commands.literal("power").executes((command) -> {
//            return cape(command.getSource());
//        })));
//    }
//
//	 private int cape(CommandSourceStack source) throws CommandSyntaxException {
//		 TardisTileEntity tardisTileEntity = TardisTileEntity.getOrCreateTardis((int) (source.getPlayer().getX() / 1000));
//		 if(tardisTileEntity.isOn()){
//			 tardisTileEntity.isOn = false;
//			 source.getPlayer().sendSystemMessage(Component.literal("OFF"));
//		 } else {
//			 tardisTileEntity.isOn = true;
//			 source.getPlayer().sendSystemMessage(Component.literal("ON"));
//		 }
//		 	return -1;
//	    }
}
