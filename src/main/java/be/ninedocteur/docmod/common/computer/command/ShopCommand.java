package be.ninedocteur.docmod.common.computer.command;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.computer.BaseOS;
import be.ninedocteur.docmod.common.computer.CommandRegistry;
import be.ninedocteur.docmod.common.computer.OSRegistry;
import be.ninedocteur.docmod.common.init.DMBlocks;
import be.ninedocteur.docmod.common.init.DMItems;
import be.ninedocteur.docmod.common.init.DMTileEntity;
import be.ninedocteur.docmod.common.tileentity.ComputerTileEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

public class ShopCommand extends BaseCommand {
    private BaseOS currentOS;
    private ComputerTileEntity computerTileEntity;

    public String packg;

    public ShopCommand() {
        super("shop", "<buy> <package>", "Buy & Download packages", CommandType.UNIVERSAL);
        for(BaseOS baseOS : OSRegistry.OSes){
            currentOS = baseOS;
            if(currentOS == null){
                currentOS = OSRegistry.MOTHERBOARD;
            }
            this.computerTileEntity = this.currentOS.getComputerTileEntity();
            for(ItemStack itemStack : computerTileEntity.getItems()){
                if(itemStack.is(DMItems.DISK.get())){
                    if(baseOS.getName().equals(itemStack.getTag().getString("OS"))){
                        break;
                    }
                }
            }
            break;
        }
    }

    @Override
    public void performCommand(String[] args) {
        for(BaseOS baseOS : OSRegistry.OSes) {
            currentOS = baseOS;
            if (currentOS == null) {
                currentOS = OSRegistry.MOTHERBOARD;
            }
        }
        if(args[1].equals("buy")){
            if(args[2].equals(currentOS.getName())){
                downloadPackage(currentOS.getName());
            }  else {
                answer("This package does not exist.", AnswerType.FAILED);
            }
        } else {
            answer("The command is not used properly.", AnswerType.FAILED);
            answer("Usage: " + getUsage(), AnswerType.NORMAL);
        }
    }

    public void downloadPackage(String pkg){
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        final Runnable runnable = new Runnable() {
            int pourcent = 0;

            public void run() {

                System.out.println(pourcent);
                pourcent++;
                computerTileEntity.TERMINAL_HISTORY.add("Downloading... " + pourcent + "%");
                DocMod.LOGGER.info("Downloading... " + pourcent + "%");

                if (pourcent > 100) {
                    scheduler.shutdown();
                    //tag.putString("OS", currentOS.getShopPackage());
                    //CHERCHER DISQUE DANS CONTENER -> AJOUTER TAG OSNAME
                    if(pkg.equals(currentOS.getName())){
                        CompoundTag compoundTag = new CompoundTag();
                        compoundTag.putString("OS", currentOS.getName());
                        for(ItemStack itemStack : computerTileEntity.getItems()){
                            if(itemStack.is(DMItems.DISK.get())){
                                itemStack.setTag(compoundTag);
                                break;
                            }
                        }
                    }
                    computerTileEntity.TERMINAL_HISTORY.add("Downloaded " + pkg + " !");
                    DocMod.LOGGER.info("Started downloading a shop package." + pkg);
                }
            }
        };

        runnable.run();
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
    }

    public void downloadPackage(BaseCommand command){
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        final Runnable runnable = new Runnable() {
            int pourcent = 0;

            public void run() {

                DocMod.LOGGER.info("Started downloading a shop package." + command.getName());
                System.out.println(pourcent);
                pourcent++;
                computerTileEntity.TERMINAL_HISTORY.add("Downloading... " + pourcent + "%");
                DocMod.LOGGER.info("Downloading... " + pourcent + "%");

                if (pourcent > 2) {
                    scheduler.shutdown();
//                    if(command.equals(CommandRegistry.getCommands(command))){
//                        computerTileEntity.installApp(command);
//                    }
                    DocMod.LOGGER.info("Downloading... " + "100%");
                    computerTileEntity.TERMINAL_HISTORY.add("Downloaded " + command.getName() + " !");

                }
            }
        };

        runnable.run();
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
    }


}
