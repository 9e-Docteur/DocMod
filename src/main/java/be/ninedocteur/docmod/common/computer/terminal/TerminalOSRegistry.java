package be.ninedocteur.docmod.common.computer.terminal;

import be.ninedocteur.docmod.common.computer.terminal.OS.DocModTerminalOS;
import be.ninedocteur.docmod.common.computer.terminal.OS.InvalidArchTerminalOS;
import be.ninedocteur.docmod.common.computer.terminal.OS.MotherboardTerminalOS;

import java.util.ArrayList;
import java.util.List;

public class TerminalOSRegistry {
    public static List<BaseTerminalOS> OSes = new ArrayList<>();

    public static BaseTerminalOS register(BaseTerminalOS command){
        OSes.add(command);
        return command;
    }

    public static final BaseTerminalOS MOTHERBOARD = register(new MotherboardTerminalOS());
    public static final BaseTerminalOS DocModOS = register(new DocModTerminalOS());
    public static final BaseTerminalOS InvalidArchOS = register(new InvalidArchTerminalOS());
}
