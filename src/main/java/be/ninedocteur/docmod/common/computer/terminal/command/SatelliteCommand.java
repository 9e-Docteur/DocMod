package be.ninedocteur.docmod.common.computer.terminal.command;

import be.ninedocteur.docmod.common.computer.terminal.BaseTerminalOS;

public class SatelliteCommand extends BaseCommand{
    protected BaseTerminalOS os;
    public SatelliteCommand(BaseTerminalOS os) {
        super("satellite", "", "Locate your computer", "satapp", CommandType.SHOP);
        this.os = os;
    }

    @Override
    public void performCommand(String[] args) {
        //answer("Computer located on " + os.getComputerTileEntity().getBlockPos(), AnswerType.INFO);
    }
}
