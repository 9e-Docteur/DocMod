package be.ninedocteur.docmod.common.computer.terminal;

import java.util.ArrayList;
import java.util.List;

public class ComputerSpecs{
    public static int RAM_INSTALLED;
    public static boolean isRAMInstalled;
    public static List<Integer> INSTALLED_RAM = new ArrayList<>();

    public static boolean checkIfRAMIsInstalled(){
        if(RAM_INSTALLED > 0){
            return true;
        } else {
            return false;
        }
    }
}
