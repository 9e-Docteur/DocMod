package be.ninedocteur.docmod.common.computer;

import be.ninedocteur.docmod.common.computer.OS.DocModOS;
import be.ninedocteur.docmod.common.computer.OS.InvalidArchOS;
import be.ninedocteur.docmod.common.computer.OS.MotherboardOS;
import be.ninedocteur.docmod.utils.LaunchUtils;

import java.util.ArrayList;
import java.util.List;

public class OSRegistry {
    public static List<BaseOS> OSes = new ArrayList<>();

    public static BaseOS register(BaseOS command){
        OSes.add(command);
        return command;
    }

    public static final BaseOS MOTHERBOARD = register(new MotherboardOS());
    public static final BaseOS DocModOS = register(new DocModOS());
    public static final BaseOS InvalidArchOS = register(new InvalidArchOS());
}
