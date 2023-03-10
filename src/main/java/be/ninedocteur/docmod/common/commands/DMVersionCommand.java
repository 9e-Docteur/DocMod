package be.ninedocteur.docmod.common.commands;

import be.ninedocteur.docmod.utils.DMUtils;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

public class DMVersionCommand {

    public DMVersionCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("docmod").then(Commands.literal("version").executes((command) -> {
            return getVersionCommand(command.getSource());
        })));
    }

    private int getVersionCommand(CommandSourceStack source) throws CommandSyntaxException {
        source.sendSuccess(Component.literal(ChatFormatting.GOLD + "Version of the DocMod: " + ChatFormatting.WHITE + DMUtils.MODNAME + " " +  DMUtils.VERSION), true);
        return 1;
    }

}
