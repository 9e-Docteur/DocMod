package be.ninedocteur.docmod.common.computer.OS;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.computer.BaseOS;


public class InvalidArchOS extends BaseOS {


    public InvalidArchOS() {
        super("Invalid Arch OS", Arch.NULL, "Just for testing.");
    }

    @Override
    public void boot() {
        bootMessages();
        DocMod.LOGGER.info("Successfuly Launched Invalid Arch OS");
    }

    @Override
    public void bootMessages() {
        computerTileEntity.TERMINAL_HISTORY.add("Boting...");
        computerTileEntity.TERMINAL_HISTORY.add("NO OS Detected! Running in the os of the motherboard");
    }

    @Override
    public void commands() {
        putCommand("echo");
    }
}
