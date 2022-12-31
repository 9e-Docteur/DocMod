package be.ninedocteur.docmod.utils;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.integrations.discord.IPCClient;
import be.ninedocteur.docmod.integrations.discord.IPCListener;
import be.ninedocteur.docmod.integrations.discord.entities.DiscordBuild;
import be.ninedocteur.docmod.integrations.discord.entities.RichPresence;
import be.ninedocteur.docmod.integrations.discord.exceptions.NoDiscordClientException;
import net.minecraft.SharedConstants;
import net.minecraft.client.Minecraft;

public class DMRPC {

    public static final RichPresence.Builder DEFAULT_BUILDER =  new RichPresence.Builder()
            .setState("Minecraft: " + SharedConstants.getCurrentVersion().getName())
            .setDetails("Playing DocMod " + DocMod.VERSION)
            .setLargeImage("docmod_logo", "DocMod")
            .setButton1("Download The Mod", "https://www.curseforge.com/minecraft/mc-mods/docmod")
            .setButton2("Join our discord", "https://discord.gg/7VA9X67xRB");
    public static final long ID = 741334907174781000L;
    public static IPCClient RPC;

    public static void startRPC() {
        RPC = new IPCClient(ID);
        RPC.setListener(new IPCListener() {
            @Override
            public void onReady(IPCClient client) {
                client.sendRichPresence(DEFAULT_BUILDER.build());
                DMUtils.LOGGER.info("DocMod RPC Ready!");
                DMUtils.LOGGER.warn("DocMod RPC Version 2.0");
            }
        });
        try {
            DocMod.LOGGER.info("Starting DocMod RPC...");
            RPC.connect(DiscordBuild.ANY);
        } catch (NoDiscordClientException e) {
            DocMod.LOGGER.error("DocMod RPC Failed:\n" + e.getMessage());
        }
    }
}
