package fr.ninedocteur.docmod.utils;

import fr.ninedocteur.docmod.DocMod;
import fr.ninedocteur.docmod.integrations.discord.rpc.DiscordEventHandlers;
import fr.ninedocteur.docmod.integrations.discord.rpc.DiscordRPC;
import fr.ninedocteur.docmod.integrations.discord.rpc.DiscordRichPresence;
import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;

public class DMRPC {

    public static void startRPC(){
        DiscordRPC lib = DiscordRPC.INSTANCE;
        String applicationId = "741334907174781000";
        String steamId = "";
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        handlers.ready = (user) ->{
            DocMod.LOGGER.info("DocMod RPC Ready!");
            DocMod.LOGGER.warn("DocMod RPC Version 1.0.1");
        };
        lib.Discord_Initialize(applicationId, handlers, true, steamId);
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.startTimestamp = System.currentTimeMillis() / 1000;
        presence.largeImageKey = "docmod_logo";
        presence.largeImageText = DocMod.MODNAME + " " + DocMod.VERSION;
        presence.smallImageKey = "zinc_icon";
        presence.smallImageText = "Player: " + MinecraftClient.getInstance().getSession().getUsername();
        presence.details = "Playing " + DocMod.MODNAME + " " + DocMod.VERSION;
        presence.state = "Loading in Minecraft " + SharedConstants.getGameVersion().getName();
        lib.Discord_UpdatePresence(presence);
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                lib.Discord_RunCallbacks();
                try {
                    if(MinecraftClient.getInstance().isInSingleplayer()){
                        presence.state = "Playing in Singleplayer";
                        presence.smallImageKey = "grass";
                    }else if (MinecraftClient.getInstance().getCurrentServerEntry() != null) {
                        presence.state = "Playing Multiplayer: " + MinecraftClient.getInstance().getCurrentServerEntry().address;
                        presence.smallImageKey = "server";

                    }else if(MinecraftClient.getInstance().currentScreen instanceof TitleScreen){
                        presence.state = "In the main menu";
                        presence.smallImageKey = "zinc_icon";
                    }
                    lib.Discord_UpdatePresence(presence);
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {}
            }
        }, "DocMod-RPC").start();
    }

    //TODO : DETECT DEV AND BETA
}
