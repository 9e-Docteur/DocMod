package be.ninedocteur.docmod.common.computer.terminal.command.shop;

import be.ninedocteur.docmod.common.computer.terminal.command.BaseCommand;
import be.ninedocteur.docmod.common.init.DMItems;

public class DocModOSCommand extends BaseCommand {

    public DocModOSCommand() {
        super("DocModOS", "", "Install DocMod OS", "docmodOS", CommandType.SHOP);
    }

    @Override
    public void performCommand(String[] args) {
        answer("Installing DocMod OS...", AnswerType.NORMAL);
        answer("Downloading System Config...", AnswerType.NORMAL);
        answer("Installing DocModKernel (v1.0-NR)...", AnswerType.NORMAL);
        answer("Configuring drive C: " + "\" + DocModOS" + "\" install.dmos", AnswerType.NORMAL);
        //this.getComputerTileEntity().getInstalledDisk();
        answer("Base OS installed, restart your system", AnswerType.NORMAL);
    }
}
