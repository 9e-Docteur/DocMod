package be.ninedocteur.docmod.common.computer;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.computer.command.BaseCommand;
import be.ninedocteur.docmod.common.computer.command.OSINFCommand;
import be.ninedocteur.docmod.common.tileentity.ComputerTileEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseOS /*implements ICommand*/ {

    protected String name, shopPackage;
    protected Arch arch;
    protected List<String> commands = new ArrayList<>();
    protected ComputerTileEntity computerTileEntity;
    protected Player player;

    /**
     * Is the computer running
     */
    public boolean isRunning;

    /**
     *
     * @param name The name of the OS
     * @param arch Is os supported on x64/x32/ARM/UNIVERSAL
     * @param shopPackage The name of the package containing the os in the shop.
     */
    public BaseOS(String name, Arch arch, String shopPackage){
        this.name = name;
        this.arch = arch;
        this.shopPackage = shopPackage;
        isRunning = false;
        player = Minecraft.getInstance().player;
    }


    public void init(ComputerTileEntity computerTileEntity) {
        this.computerTileEntity = computerTileEntity;
        isRunning = true;
        commands();
        try {
            verifyArch();
        } catch (OSException e){
            DocMod.LOGGER.error("An Error Occured:" + e);
            player.sendSystemMessage(Component.literal(ChatFormatting.RED + "An Error Occured : " + e));
        }
         BaseCommand OSINFO = CommandRegistry.register(new OSINFCommand(this));
    }

    /**
     * Verify if the arch is correct. If not, system will not boot and will send an exception in the chat and the logs.
     * @throws OSException
     */
    private void verifyArch() throws OSException{
        if(arch == Arch.x64 || arch == Arch.x32 || arch == Arch.ARM || arch == Arch.UNIVERSAL){

        } else {
            throw new OSException();
        }
    }

    /**
     * MANDATORY : This function is called to boot on the OS.
     */
    public abstract void boot();

    /**
     * If you want to add messages when your system boot, you can use this function
     */
    public abstract void bootMessages();

    /**
     * Here you can allow existing non-universal commands or your own commands
     */
    public abstract void commands();

    /**
     * Get the OS Arch
     * @return
     */
    public Arch getArch() {
        return arch;
    }

    /**
     * Get the OS Name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Get the package name of the os in the shop
     * @return
     */
    public String getShopPackage() {
        return shopPackage;
    }

    public ComputerTileEntity getComputerTileEntity() {
        return computerTileEntity;
    }

    public void execute(String command){
        String[] args = command.split(" ");
        for(BaseCommand command1 : CommandRegistry.commands){
            command1.init(computerTileEntity);
            if(command1.getName().equals(args[0])) {
                if (computerTileEntity.isAppInstalled(command1)) {
                    System.out.println(command);
                    if (command1.getCommandType() == BaseCommand.CommandType.UNIVERSAL || commands.contains(command1.getName())) {
                        command1.performCommand(args);
                        System.out.println(command);
                        break;
                    } else {
                        command1.answer("Unknown Command", BaseCommand.AnswerType.FAILED);
                    }
                }
            } else {
                command1.answer("Unknown Command", BaseCommand.AnswerType.FAILED);
            }
        }
        computerTileEntity.TERMINAL_HISTORY.add(">" + command);
    }

/*
    @Override
    public void execute(String command, List<BaseCommand> yourOSCommandsList) {
        String[] args = command.split(" ");
        for(BaseUniversalCommand universalCommand : UniversalCommands){ //BASE COMMAND ASKED WILL BE LOOKING IN THE COMMAND REGISTERED IN THE LIST "commands" OF THE MOTHERBOARD// COMMAND PER OS
            if(args[0].equals(universalCommand.getName())){
                universalCommand.performCommand(args); //PERFORM COMMAND
                return;
            } else {
                for(BaseCommand baseCommand : yourOSCommandsList){ //BASE COMMAND ASKED WILL BE LOOKING IN THE COMMAND REGISTERED IN THE LIST "commands" OF THE MOTHERBOARD// COMMAND PER OS
                    if(args[0].equals(baseCommand.getName())){
                        baseCommand.performCommand(args); //PERFORM COMMAND
                        return;
                    }
                }
                computerTileEntity.TERMINAL_HISTORY.add(">" + command);
            }
        }
        computerTileEntity.TERMINAL_HISTORY.add(">" + command);
    }

 */
/*
    public void checkIfCommandIsUniversal(String command, List<BaseCommand> yourOSCommandsList){
        String[] args = command.split(" ");
        for(BaseUniversalCommand universalCommand : UniversalCommands){ //BASE COMMAND ASKED WILL BE LOOKING IN THE COMMAND REGISTERED IN THE LIST "commands" OF THE MOTHERBOARD// COMMAND PER OS
            if(args[0].equals(universalCommand.getName())){
                universalCommand.performCommand(args); //PERFORM COMMAND
                return;
            } else {
                for(BaseCommand baseCommand : yourOSCommandsList){ //BASE COMMAND ASKED WILL BE LOOKING IN THE COMMAND REGISTERED IN THE LIST "commands" OF THE MOTHERBOARD// COMMAND PER OS
                    if(args[0].equals(baseCommand.getName())){
                        baseCommand.performCommand(args); //PERFORM COMMAND
                        return;
                    }
                }
                computerTileEntity.TERMINAL_HISTORY.add(">" + command);
            }
        }
        computerTileEntity.TERMINAL_HISTORY.add(">" + command);
    }

 */

   // public void executeUniversalCommand(String universalCommand){

    //}


    public List<String> getCommands() {
        return commands;
    }

    /**
     * Put your command/existing non-universal command in the commands(); function easily
     * @param commandName
     */
    public void putCommand(String commandName){
        commands.add(commandName);
    }

    /**
     * Select a Arch for your OS from here.
     * This Enum avoid dev to set other things that will be unknown to DocMod.
     */
    public enum Arch{
        UNIVERSAL,
        x64,
        x32,
        ARM,
        NULL
    }
}
