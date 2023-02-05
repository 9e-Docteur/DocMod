package fr.ninedocteur.docmod.common.event;

import fr.ninedocteur.docmod.client.screens.DMTitleScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.fabricmc.fabric.api.event.AutoInvokingEvent;
import net.fabricmc.fabric.impl.client.screen.ScreenEventFactory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;

public class DMEvent {

    @Environment(EnvType.CLIENT)
    public static void beforeTitleScreenShow(){
        TitleScreen titleScreen = new TitleScreen();
        ScreenEvents.afterRender(titleScreen);
        MinecraftClient.getInstance().setScreen(new DMTitleScreen());
    }
}
