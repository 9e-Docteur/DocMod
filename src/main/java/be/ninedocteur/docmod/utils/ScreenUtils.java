package be.ninedocteur.docmod.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;

public class ScreenUtils {
    public static void open(Screen screen, Screen screen1) {
        open(Minecraft.getInstance().screen, screen);
    }

    public static void openScreen(Screen screen) {
        Minecraft.getInstance()
                .tell(() -> {
                    Minecraft.getInstance()
                            .setScreen(screen);
                });
    }
}
