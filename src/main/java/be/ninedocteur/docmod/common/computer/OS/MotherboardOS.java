package be.ninedocteur.docmod.common.computer.OS;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.computer.BaseOS;
import be.ninedocteur.docmod.common.computer.command.BaseCommand;
import be.ninedocteur.docmod.common.computer.command.motherboard.command.EchoCommand;

import java.util.ArrayList;
import java.util.List;


public class MotherboardOS extends BaseOS {


    public MotherboardOS() {
        super("Motherboard OS", Arch.UNIVERSAL, "Not in the shop.");
    }

    @Override
    public void boot() {
        bootMessages();
        DocMod.LOGGER.info("Successfuly Launched MotherboardOS");
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
