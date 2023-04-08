package be.ninedocteur.docmod.common.computer.terminal.OS;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.computer.terminal.BaseTerminalOS;

public class DocModTerminalOS extends BaseTerminalOS {


    public DocModTerminalOS() {
        super("DocModOS", Arch.x64, "docmodos");
    }

    @Override
    public void boot() {
        bootMessages();
        DocMod.LOGGER.info("Successfuly Launched DocModOS");
    }

    @Override
    public void bootMessages() {
       // computerTileEntity.TERMINAL_HISTORY.add("Boting...");
        //computerTileEntity.TERMINAL_HISTORY.add("Welcome to DocMod OS!");
    }

    @Override
    public void commands() {
        putCommand("echo");
    }
}
