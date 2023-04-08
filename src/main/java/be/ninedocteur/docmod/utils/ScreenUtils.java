package be.ninedocteur.docmod.utils;

import be.ninedocteur.docmod.client.models.TardisModel;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import org.joml.Vector3f;

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

    public static void fillWithFullTexture(double x, double y, int width, int height)
    {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        BufferBuilder buffer = Tesselator.getInstance().getBuilder();
        buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        buffer.vertex(x, y + height, 0).uv(0, 1).endVertex();
        buffer.vertex(x + width, y + height, 0).uv(1, 1).endVertex();
        buffer.vertex(x + width, y, 0).uv(1, 0).endVertex();
        buffer.vertex(x, y, 0).uv(0, 0).endVertex();
        BufferUploader.drawWithShader(buffer.end());
    }
}
