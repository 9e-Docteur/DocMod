package be.ninedocteur.docmod.utils;

import be.ninedocteur.docmod.DMConfig;
import be.ninedocteur.docmod.client.gui.title.DMTitleScreen;
import be.ninedocteur.docmod.integrations.discord.rpc.DiscordEventHandlers;
import be.ninedocteur.docmod.integrations.discord.rpc.DiscordRPC;
import be.ninedocteur.docmod.integrations.discord.rpc.DiscordRichPresence;
import be.ninedocteur.docmod.utils.DMUtils;
import be.ninedocteur.docmod.utils.RankUtils;
import be.ninedocteur.docmod.utils.ServerUtils;
import be.ninedocteur.docteam.website.Database;
import net.minecraft.SharedConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.User;
import net.minecraft.world.entity.player.Player;

public class DMRPC {

    public static void startRPC(){
        DiscordRPC lib = DiscordRPC.INSTANCE;
        String applicationId = "741334907174781000";
        String steamId = "";
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        handlers.ready = (user) ->{
            DMUtils.LOGGER.info("DocMod RPC Ready!");
            DMUtils.LOGGER.warn("DocMod RPC Version 1.0.1");
        };
        lib.Discord_Initialize(applicationId, handlers, true, steamId);
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.startTimestamp = System.currentTimeMillis() / 1000;
        presence.largeImageKey = "docmod_logo";
        presence.largeImageText = DMUtils.MODNAME + " " + DMUtils.VERSION;
        presence.smallImageKey = "zinc_icon";
        presence.smallImageText = "Player: " + Minecraft.getInstance().getUser().getName();
        presence.details = "Playing " + DMUtils.MODNAME + " " + DMUtils.VERSION;
        presence.state = "Loading in Minecraft " + SharedConstants.getCurrentVersion().getName();
        lib.Discord_UpdatePresence(presence);
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                lib.Discord_RunCallbacks();
                try {
                    if(Minecraft.getInstance().hasSingleplayerServer()){
                        presence.state = "Playing in Singleplayer";
                        presence.smallImageKey = "grass";
                    }else if (Minecraft.getInstance().getCurrentServer() != null) {
                        presence.state = "Playing Multiplayer: " + Minecraft.getInstance().getCurrentServer().ip;
                        presence.smallImageKey = "server";

                    }else if(Minecraft.getInstance().screen instanceof DMTitleScreen){
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
