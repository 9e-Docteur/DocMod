package be.ninedocteur.docmod.client.event;

import be.ninedocteur.docmod.DMConfig;
import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.KeyBinds;
import be.ninedocteur.docmod.client.gui.screens.DMConnectScreen;
import be.ninedocteur.docmod.utils.DMUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DocMod.MOD_ID, value = Dist.CLIENT)
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

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event){
        event.register(KeyBinds.DEV_MODE_KEY);
    }
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
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
    }
}
