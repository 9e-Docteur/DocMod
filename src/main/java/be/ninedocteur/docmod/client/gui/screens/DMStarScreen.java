package be.ninedocteur.docmod.client.gui.screens;

import be.ninedocteur.docmod.client.gui.content.StarRenderer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class DMStarScreen extends Screen {
    private static final int NUM_STARS = 100;
    private final Star[] stars = new Star[NUM_STARS];
    private final ResourceLocation starTexture = new ResourceLocation("docmod:textures/misc/stars.png");
    private final float scrollSpeed = 0.1f;

    public DMStarScreen() {
        super(Component.empty());
        Random rand = new Random();
        for (int i = 0; i < NUM_STARS; i++) {
            float x = width / 2;
            float y = height / 2;
            float depth = rand.nextFloat() * 3;
            float speed = (depth / 3) * 2; // Plus l'étoile est profonde, plus sa vitesse est lente
            stars[i] = new Star(x, y, depth, speed);
        }
    }

    @Override
    public void render(PoseStack p_96562_, int p_96563_, int p_96564_, float p_96565_) {
        super.render(p_96562_, p_96563_, p_96564_, p_96565_);
        RenderSystem.setShaderColor(0.0f, 0.0f, 0.0f, 1.0f);
        p_96562_.pushPose();
        Matrix4f matrix = Matrix4f.createScaleMatrix(width, height, 1.0f);
        Tesselator tessellator = Tesselator.getInstance();
        BufferBuilder buffer = tessellator.getBuilder();
        buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        buffer.vertex(0.0f, 0.0f, 0.0f).color(0.0f, 0.0f, 0.0f, 1.0f).endVertex();
        buffer.vertex(0.0f, height, 0.0f).color(0.0f, 0.0f, 0.0f, 1.0f).endVertex();
        buffer.vertex(width, height, 0.0f).color(0.0f, 0.0f, 0.0f, 1.0f).endVertex();
        buffer.vertex(width, 0.0f, 0.0f).color(0.0f, 0.0f, 0.0f, 1.0f).endVertex();
        BufferUploader.drawWithShader(buffer.end());
        p_96562_.popPose();

        // Déplace les étoiles selon le temps écoulé
        float delta = p_96565_ * scrollSpeed;
        for (Star star : stars) {
            star.update(delta, width);
        }

        // Dessine les étoiles
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, starTexture);
        for (Star star : stars) {
            blit(p_96562_, (int) star.x, (int) star.y, 0, 0, 1, 1, 1, 1);
        }


    }

    private static class Star {
        float x, y;
        float depth;
        float speed;

        public Star(float x, float y, float depth, float speed) {
            this.x = x;
            this.y = y;
            this.depth = depth;
            this.speed = speed;
        }

        public void update(float delta, float width) {
            x -= delta * speed;
            if (x < 0) {
                x += width;
            }
        }
    }
}
