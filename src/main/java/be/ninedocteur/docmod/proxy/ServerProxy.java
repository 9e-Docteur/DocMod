package be.ninedocteur.docmod.proxy;

import be.ninedocteur.docmod.DMConfig;
import be.ninedocteur.docmod.DocMod;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import org.lwjgl.system.CallbackI;

public class ServerProxy {


    public static void serverProxyRegistry(IEventBus eventBus, IEventBus forgeBus){
        eventBus.addListener(ServerProxy::onServerStarting);
    }
    private static void onServerStarting(ServerStartedEvent event){
        DocMod.LOGGER.info("Thank you for downloading DocMod!");
        DocMod.LOGGER.warn("You can support us by joining our discord server : https://discord.gg/7VA9X67xRB");
    }
}
