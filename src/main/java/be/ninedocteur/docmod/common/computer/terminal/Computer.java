package be.ninedocteur.docmod.common.computer.terminal;

import be.ninedocteur.docmod.DocMod;
import net.minecraftforge.eventbus.api.IEventBus;

public class Computer {
    public static String COMPUTER_PREFIX = "[DocMod Computer] ";
    public static void registerCommand(){
        DocMod.LOGGER.info(COMPUTER_PREFIX + "Registering commands...");
        //Brain.registerCommands();
        DocMod.LOGGER.info(COMPUTER_PREFIX + "All commands registered!");
    }

    public class Process{
//        public static boolean checkComposant(ComputerHarwareMenu menu){
//            if(menu.getItems().contains(DMItems.CPU.get()) && menu.getItems().contains(DMItems.ALIMENTATION.get()) && menu.getItems().contains(DMItems.MOTHERBOARD.get()) && menu.getItems().contains(DMItems.RAM.get()) && menu.getItems().contains(DMItems.DISK.get())){
//                return true;
//            }
//            return false;
//        }



        public static void startComputer(){
            //if(!checkComposant()){
                //Brain.text.add("Failed to boot... Required composant aren't present to boot!");    NEED TO FINISH THIS
                //return;
           // }
            //if(!os.notPresent){
                //MotherboardOS.boot();
            // }


            //TODO: OS HELLO WORLD
        }
    }

    public class Status{
        public static boolean isRunning;
    }
}
