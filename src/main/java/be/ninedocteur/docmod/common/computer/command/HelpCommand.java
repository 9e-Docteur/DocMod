package be.ninedocteur.docmod.common.computer.command;

public class HelpCommand extends BaseCommand {
    public HelpCommand() {
        super("help", "", "Get every commands", CommandType.NORMAL);
    }

    @Override
    public void performCommand(String[] args) {

    }
}
