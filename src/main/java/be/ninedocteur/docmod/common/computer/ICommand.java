package be.ninedocteur.docmod.common.computer;

import be.ninedocteur.docmod.common.computer.command.BaseCommand;

import java.util.List;

public interface ICommand {
    void execute(String command, List<BaseCommand> yourOSCommandsList);
}
