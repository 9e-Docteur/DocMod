package be.ninedocteur.docmod.common.computer.command;

import be.ninedocteur.docmod.common.computer.BaseOS;

public class SatelliteCommand extends BaseCommand{
    protected BaseOS os;
    public SatelliteCommand(BaseOS os) {
        super("satellite", "", "Locate your computer", "satapp", CommandType.SHOP);
        this.os = os;
    }

    @Override
    public void performCommand(String[] args) {
        answer("Computer located on " + os.getComputerTileEntity().getBlockPos(), AnswerType.INFO);
    }
}
