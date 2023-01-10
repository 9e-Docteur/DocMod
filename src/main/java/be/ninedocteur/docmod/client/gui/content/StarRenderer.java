package be.ninedocteur.docmod.client.gui.content;

import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class StarRenderer {
    private static final int NUM_STARS = 100;
    private static final double SPEED = 0.2;

    private static double[] angles;
    private static int[] sizes;
    private static Color[] colors;

    static {
        angles = new double[NUM_STARS];
        sizes = new int[NUM_STARS];
        colors = new Color[NUM_STARS];

        for (int i = 0; i < NUM_STARS; i++) {
            angles[i] = Math.random() * Math.PI * 2;
            sizes[i] = (int) (Math.random() * 3 + 1);
            colors[i] = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
        }
    }

    public static void render(int tick) {
        Minecraft mc = Minecraft.getInstance();

//        GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
//        GL11.glDisable(GL11.GL_LIGHTING);

        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder buffer = tesselator.getBuilder();


        //buffer.clear();
        //buffer.end();
        try {
            buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        } catch (IllegalStateException e) {
            buffer.end();
            buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        }
        for (int i = 0; i < NUM_STARS; i++) {
            double angle = angles[i] + tick * SPEED;
            double x = Math.cos(angle) * 100;
            double y = Math.sin(angle) * 100;

            buffer.vertex(x, y, 0).color(colors[i].getRed(), colors[i].getGreen(), colors[i].getBlue(), 255).endVertex();
            buffer.vertex(x + sizes[i], y + sizes[i], 0).color(colors[i].getRed(), colors[i].getGreen(), colors[i].getBlue(), 255).endVertex();
            buffer.vertex(x + sizes[i], y, 0).color(colors[i].getRed(), colors[i].getGreen(), colors[i].getBlue(), 255).endVertex();
            buffer.vertex(x, y + sizes[i], 0).color(colors[i].getRed(), colors[i].getGreen(), colors[i].getBlue(), 255).endVertex();
        }
        BufferUploader.drawWithShader(buffer.end());
        //buffer.end();
//        GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
//        GL11.glDisable(GL11.GL_LIGHTING);
    }
}
