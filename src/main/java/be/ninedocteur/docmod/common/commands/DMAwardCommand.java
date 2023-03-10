package be.ninedocteur.docmod.common.commands;

import be.ninedocteur.docmod.utils.AwardUtils;
import be.ninedocteur.docmod.utils.TeamUtils;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class DMAwardCommand {
    public DMAwardCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("docmod").then(Commands.literal("award").then(Commands.argument("player", EntityArgument.player()).executes((command) -> {
            return getRank(command.getSource(), EntityArgument.getPlayer(command, "player"));
        }))));
    }

    private int getRank(CommandSourceStack source, ServerPlayer player) throws CommandSyntaxException {
        AwardUtils.Award award = AwardUtils.getAward().get(player.getUUID());
        source.sendSuccess(Component.literal(ChatFormatting.GOLD + "The award(s) of " + player.getName() + " are: " + award.getAwardName()), true);
        return 1;
    }
}
