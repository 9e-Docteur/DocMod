package be.ninedocteur.docmod.common.computer.gui.OS;

import be.ninedocteur.docmod.common.computer.gui.BaseOS;

public class DocModOS extends BaseOS {
    /**
     * @param name        The name of the OS
     * @param arch        Is os supported on x64/x32/ARM/UNIVERSAL
     * @param shopPackage The name of the package containing the os in the shop.
     */
    public DocModOS(String name, Arch arch, String shopPackage) {
        super(name, arch, shopPackage);
    }

    @Override
    public void setupRequirements(int cpuFreq, int ram) {

    }

    @Override
    public void boot() {

    }

    @Override
    public void bootMessages() {

    }

    @Override
    public void commands() {

    }
}
