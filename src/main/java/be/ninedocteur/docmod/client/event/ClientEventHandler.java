package be.ninedocteur.docmod.client.event;

import be.ninedocteur.docmod.DMConfig;
import be.ninedocteur.docmod.client.KeyBinds;
import be.ninedocteur.docmod.client.gui.overlay.DocModDebugOverlay;
import be.ninedocteur.docmod.jobs.gui.screens.MainJobsMenu;
import be.ninedocteur.docmod.utils.TeamUUIDs;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;

public class ClientEventHandler {
/*
    @SubscribeEvent
    public void onGUIOpen(ScreenEvent.InitScreenEvent.Post event) {
        if (event.getScreen() instanceof JoinMultiplayerScreen multiplayerScreen) {
            ServerList list = multiplayerScreen.getServers();
            //list.add(new ServerData("DocMod Server", DMUtils.getPublicServerHost() + ":" + DMUtils.getPublicServerPort(), false));
            multiplayerScreen.serverSelectionList.updateOnlineServers(multiplayerScreen.getServers());
            list.load();
        }
    }

 */

    /*
    @SubscribeEvent
    public void onGUI(ScreenEvent.InitScreenEvent.Post event) {
        if(event.getScreen() instanceof DocModServersSelection multiplayerScreen) {
            DocModServerList list = multiplayerScreen.getServers();
            list.add(new DocModServerData("DocMod Server", Servers.HOST + ":" + Servers.PORT, false));
            multiplayerScreen.serverSelectionList.updateOnlineServers(multiplayerScreen.getServers());
            list.load();
        }
    }

     */

    public static void onKeyRegister(RegisterKeyMappingsEvent event){
        event.register(KeyBinds.DEV_MODE_KEY);
        event.register(KeyBinds.DEBUG_MODE_KEY);
        event.register(KeyBinds.JOBS_KEY);
    }

    public static void onKeyInput(InputEvent.Key event){
        if(KeyBinds.DEV_MODE_KEY.consumeClick()){
            if(DMConfig.Client.DEV_MODE.get()) {
                Minecraft.getInstance().player.sendSystemMessage(Component.literal("Dev Mode :" + ChatFormatting.RED + " OFF"));
                DMConfig.Client.DEV_MODE.set(false);
            } else {
                Minecraft.getInstance().player.sendSystemMessage(Component.literal("Dev Mode :" + ChatFormatting.GREEN + " ON"));
                DMConfig.Client.DEV_MODE.set(true);
            }
        }
        if(KeyBinds.DEBUG_MODE_KEY.consumeClick()){
            if(DocModDebugOverlay.showDebugOverlay){
                DocModDebugOverlay.showDebugOverlay = false;
            } else {
                DocModDebugOverlay.showDebugOverlay = true;
            }
        }

        if(KeyBinds.JOBS_KEY.consumeClick()){
            Minecraft.getInstance().setScreen(new MainJobsMenu());
        }
    }
}
