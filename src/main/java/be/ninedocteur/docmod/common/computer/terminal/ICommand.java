package be.ninedocteur.docmod.common.computer.terminal;

import be.ninedocteur.docmod.common.computer.terminal.command.BaseCommand;

import java.util.List;

public interface ICommand {
    void execute(String command, List<BaseCommand> yourOSCommandsList);
}
