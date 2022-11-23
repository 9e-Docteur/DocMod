package be.ninedocteur.docmod.common.computer.gui;

import be.ninedocteur.docmod.common.computer.terminal.BaseTerminalOS;
import be.ninedocteur.docmod.common.computer.terminal.OS.DocModTerminalOS;
import be.ninedocteur.docmod.common.computer.terminal.OS.InvalidArchTerminalOS;
import be.ninedocteur.docmod.common.computer.terminal.OS.MotherboardTerminalOS;

import java.util.ArrayList;
import java.util.List;

public class OSRegistry {
    public static List<BaseOS> OSes = new ArrayList<>();

    public static BaseOS register(BaseOS command){
        OSes.add(command);
        return command;
    }


}
