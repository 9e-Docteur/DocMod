package be.ninedocteur.docmod.common.commands;

import be.ninedocteur.docmod.common.item.sonic.SonicItem;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;

public class DMSonicLevelCommand {

    public DMSonicLevelCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("docmod").then(Commands.literal("sonic").then(Commands.literal("level").then(Commands.argument("int", IntegerArgumentType.integer(1, 4)).executes((command) -> {
            return execute(command.getSource(), IntegerArgumentType.getInteger(command, "int"));
        })))));
    }

    private int execute(CommandSourceStack source, int level) throws CommandSyntaxException {
        SonicItem sonic = (SonicItem) source.getPlayer().getItemInHand(InteractionHand.MAIN_HAND).getItem();
        if(source.getPlayer().getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof SonicItem){
            sonic.setSonicLevel(level);
            source.sendSuccess(Component.literal(ChatFormatting.GREEN + "Sonic level set to " + level), true);
        } else {
            source.sendSuccess(Component.literal(ChatFormatting.RED + "You do not have a sonic in your main hand."), true);
        }
        return 1;
    }

}
