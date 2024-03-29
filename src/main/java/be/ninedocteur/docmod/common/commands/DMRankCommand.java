package be.ninedocteur.docmod.common.commands;

import be.ninedocteur.docmod.utils.TeamUtils;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.User;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class DMRankCommand {
    public DMRankCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("docmod").then(Commands.literal("rank").then(Commands.argument("player", EntityArgument.player()))).executes((command) -> {
            return getRank(command.getSource(), EntityArgument.getPlayer(command, "player"));
        }));
    }

    private int getRank(CommandSourceStack source, ServerPlayer player) throws CommandSyntaxException {
        TeamUtils.TeamMember teamMember = TeamUtils.getTeamMembers().get(player.getUUID());
        source.sendSuccess(Component.literal(ChatFormatting.GOLD + "The rank(s) of " + player.getName() + " are: " + teamMember.getTitles()), true);
        return 1;
    }
}
