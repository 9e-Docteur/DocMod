package be.ninedocteur.docmod.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

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
