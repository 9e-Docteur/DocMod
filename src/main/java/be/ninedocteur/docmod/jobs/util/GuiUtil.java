package be.ninedocteur.docmod.jobs.util;


import be.ninedocteur.docmod.DocMod;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Matrix4f;

@OnlyIn(Dist.CLIENT)
public class GuiUtil {
	
	public static final ResourceLocation ICONS = new ResourceLocation(DocMod.MOD_ID, "textures/gui/jobs_icons.png");
	
	public static void drawTexture(PoseStack mStack, GuiComponent gui, int x, int y, int textX, int textY, int width, int height)
	{
		gui.blit(mStack, x, y, textX, textY, width, height);
	}
	
	
	public static void drawScaledTexture(PoseStack mStack, int x, int y, float u, float v, int uWidth, int vHeight, int width, int height)
    {
		Matrix4f matrix = mStack.last().pose();
        float f = 1.0F / 256.0f;
        float f1 = 1.0F / 256.0f;
        Tesselator tessellator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuilder();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex(matrix, (float)x, (float)(y + height), 0.0F).uv(u * f, (v + (float)vHeight) * f1).endVertex();
        bufferbuilder.vertex(matrix, (float)(x + width), (float)(y + height), 0.0F).uv((u + (float)uWidth) * f, (v + (float)vHeight) * f1).endVertex();
        bufferbuilder.vertex(matrix, (float)(x + width), (float)y, 0.0F).uv((u + (float)uWidth) * f, v * f1).endVertex();
        bufferbuilder.vertex(matrix, (float)x, (float)y, 0.0F).uv(u * f, v * f1).endVertex();
        tessellator.end();
    }
	
	public static void drawJobIcon(PoseStack mStack, GuiComponent gui, Constants.Job job, int centerX, int centerY)
	{
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, ICONS);
		gui.blit(mStack, centerX-20, centerY-20, 40*job.index, 216, 40, 40);
	}

}
