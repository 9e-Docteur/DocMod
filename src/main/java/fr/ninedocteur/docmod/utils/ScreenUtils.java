package fr.ninedocteur.docmod.utils;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.*;

public class ScreenUtils {
    public static void open(Screen screen, Screen screen1) {
        open(MinecraftClient.getInstance().currentScreen, screen);
    }

    public static void openScreen(Screen screen) {
        MinecraftClient.getInstance().send(() -> {
            MinecraftClient.getInstance().setScreen(screen);
        });
    }

    public static void fillWithFullTexture(double x, double y, int width, int height)
    {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        BufferBuilder buffer = Tessellator.getInstance().getBuffer();
        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
        buffer.vertex(x, y + height, 0).texture(0, 1).next();
        buffer.vertex(x + width, y + height, 0).texture(1, 1).next();
        buffer.vertex(x + width, y, 0).texture(1, 0).next();
        buffer.vertex(x, y, 0).texture(0, 0).next();
        BufferRenderer.drawWithGlobalProgram(buffer.end());
    }
}
