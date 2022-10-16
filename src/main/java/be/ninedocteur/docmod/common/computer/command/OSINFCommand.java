package be.ninedocteur.docmod.common.computer.command;

import be.ninedocteur.docmod.common.computer.BaseOS;

public class OSINFCommand extends BaseCommand{
    protected BaseOS os;
    public OSINFCommand(BaseOS os) {
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
