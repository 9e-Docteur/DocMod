package be.ninedocteur.docmod.common.commands;

import be.ninedocteur.docmod.utils.TeamUtils;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.User;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

public class DMRankCommand {
    public DMRankCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("docmod").then(Commands.literal("rank").executes((command) -> {
            return getRank(command.getSource());
        })));
    }

    private int getRank(CommandSourceStack source) throws CommandSyntaxException {

        Level level = Minecraft.getInstance().level;
        if(level.isClientSide()) {
            User user = Minecraft.getInstance().getUser();
            TeamUtils.TeamMember teamMember = TeamUtils.getTeamMembers().get(user.getUuid());
            source.sendSuccess(Component.literal(ChatFormatting.GOLD + "Your rank(s) are: " + teamMember.getTitles()), true);
        } else {
            source.sendSuccess(Component.literal(ChatFormatting.RED + "This command can't be executed on server."), true);
        }
        return 1;
    }
}
