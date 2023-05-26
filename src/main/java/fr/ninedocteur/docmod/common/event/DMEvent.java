package fr.ninedocteur.docmod.common.event;

import fr.ninedocteur.docmod.client.gui.title.DMTitleScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
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
