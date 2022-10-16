package be.ninedocteur.docmod.registry;

import be.ninedocteur.docmod.common.commands.DMRankCommand;
import be.ninedocteur.docmod.common.commands.DMVersionCommand;
import be.ninedocteur.docmod.utils.DMUtils;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = DMUtils.MOD_ID)
public class CommandRegistry {
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event){
        //new DMRankCommand(event.getDispatcher());
        //new DMVersionCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }
}
