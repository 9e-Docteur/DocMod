package be.ninedocteur.docmod.common.computer.terminal.command;

import be.ninedocteur.docmod.common.computer.terminal.BaseTerminalOS;

public class OSINFCommand extends BaseCommand{
    protected BaseTerminalOS os;
    public OSINFCommand(BaseTerminalOS os) {
        super("osinf", "", "Get a description of the os", CommandType.UNIVERSAL);
        this.os = os;
    }

    @Override
    public void performCommand(String[] args) {
        answer("OS: " + os.getName(), AnswerType.INFO);
        answer("Arch: " + os.getArch(), AnswerType.INFO);
        answer("Shop Package Name: " + os.getShopPackage(), AnswerType.INFO);
    }
}
