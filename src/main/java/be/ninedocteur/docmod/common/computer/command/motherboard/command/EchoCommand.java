package be.ninedocteur.docmod.common.computer.command.motherboard.command;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.computer.command.BaseCommand;

public class EchoCommand extends BaseCommand {
    public EchoCommand() {
        super("echo", "echo [text]", "Print text", CommandType.NORMAL);
    }

    @Override
    public void performCommand(String[] args) {
        if(args.length >= 0){
            String value = "";
            for(int i = 1; i < args.length; i++){
                value += args[i] + " ";
            }
            answer(value, AnswerType.NORMAL);
        }
    }
}
