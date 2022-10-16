package be.ninedocteur.docmod.common.commands;

public class DMAdminGiveCommand {
    /*
    public DMAdminGiveCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("docmod").then(Commands.literal("admin").then(Commands.literal("give"))).executes((command) -> {
            return getCommand(command.getSource());
        }));
    }

    //private int getCommand(CommandSourceStack source) throws CommandSyntaxException {
/*
        User user = Minecraft.getInstance().getUser();
        Player player = Minecraft.getInstance().player;
        AdminUtils.TeamMember teamMember = AdminUtils.getTeamMembers().get(user.getUuid());
        if (teamMember.getUuid().equals(AdminUtils.DEV)) {
                source.sendSuccess(new TextComponent(ChatFormatting.RED + "Incomplete Command. Please add an argument."), true);
        }else{
            source.sendSuccess(new TextComponent(ChatFormatting.RED + "You do not have access to this command."), true);
        }
        return 1;
    }

 */
}
