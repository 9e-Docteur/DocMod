package be.ninedocteur.docmod.common.computer.command;

import be.ninedocteur.docmod.common.tileentity.ComputerTileEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

public abstract class BaseCommand {
    protected String name, usage, description, shopPackageName;
    protected CommandType commandType;
    protected ComputerTileEntity computerTileEntity;
    private BaseCommand instance;

    /**
     * @param name        Give the command a name
     * @param usage       Give the command a usage
     * @param description Give the command a description
     * @param commandType If the command is for all OSes or just OSes who allow this command
     */
    public BaseCommand(String name, String usage, String description, CommandType commandType) {
        this.name = name;
        this.usage = usage;
        this.description = description;
        this.commandType = commandType;
    }

    /**
     * If you want to add your command to the shop, use this.
     *
     * @param name            Give the command a name
     * @param usage           Give the command a usage
     * @param description     Give the command a description
     * @param shopPackageName Name of the package containing this command.
     * @param commandType     If the command is for all OSes or just OSes who allow this command
     */
    public BaseCommand(String name, String usage, String description, String shopPackageName, CommandType commandType) {
        this.name = name;
        this.usage = usage;
        this.description = description;
        this.commandType = commandType;
        this.shopPackageName = shopPackageName;
    }


    public void init(ComputerTileEntity computerTileEntity) {
        this.computerTileEntity = computerTileEntity;
    }

    /**
     * This function will be called when the user have typed your command and pressed enter to execute it.
     *
     * @param args
     */
    public abstract void performCommand(String[] args);

    /**
     * Get the description of a command
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the name of a command
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Get the usage of a command
     *
     * @return
     */
    public String getUsage() {
        return usage;
    }

    /**
     * To answer a text when the command is executed.
     *
     * @param string     What will be answered.
     * @param answerType Is the command normal, or failed or other.
     */
    public void answer(String string, AnswerType answerType) {
        computerTileEntity.TERMINAL_HISTORY.add(answerType.color + string);
    }

    /**
     * Get the type of a command
     *
     * @return
     */
    public CommandType getCommandType() {
        return commandType;
    }

    /**
     * Get the package name of a command/os on the shop
     * If this function is called on a os or command who's not in the shop, it will return a null pointer exception. To avoid crash, if it's null, the function will answer that you cannot use this function on something who's not in the shop
     *
     * @return
     */
    public String getShopPackageName() {
        if (shopPackageName == null) {
            answer("WARNING! You have used the function getShopPackageName() but your command is not a SHOP command Type", AnswerType.FAILED);
            System.out.println("WARNING! You have used the function getShopPackageName() but your command is not a SHOP command Type");
        } else {
            return shopPackageName;
        }
        return shopPackageName;
    }

    public enum CommandType {
        UNIVERSAL,
        NORMAL,
        SHOP
    }

    public enum AnswerType {
        FAILED(ChatFormatting.RED),
        INFO(ChatFormatting.GOLD),
        SUCCESS(ChatFormatting.GREEN),
        NORMAL(ChatFormatting.WHITE);

        public ChatFormatting color;

        AnswerType(ChatFormatting color) {
            this.color = color;
        }
    }

}
