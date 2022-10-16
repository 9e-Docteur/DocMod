package be.ninedocteur.docmod.common.computer;

import be.ninedocteur.docmod.common.computer.command.BaseCommand;
import be.ninedocteur.docmod.common.computer.command.OSINFCommand;
import be.ninedocteur.docmod.common.computer.command.ShopCommand;
import be.ninedocteur.docmod.common.computer.command.motherboard.command.EchoCommand;
import be.ninedocteur.docmod.common.tileentity.ComputerTileEntity;

import java.util.ArrayList;
import java.util.List;

public class CommandRegistry {
    public static List<BaseCommand> commands = new ArrayList<>();


    public static BaseCommand register(BaseCommand command){
        commands.add(command);
        return command;
    }


    public static final BaseCommand ECHO = register(new EchoCommand());
    public static final BaseCommand SHOP = register(new ShopCommand());
}
