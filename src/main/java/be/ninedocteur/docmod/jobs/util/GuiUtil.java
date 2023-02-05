package be.ninedocteur.docmod.jobs.util;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.jobs.data.ClientJobsData;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;

import java.awt.*;

@OnlyIn(Dist.CLIENT)
public class GuiUtil {

	private static final ResourceLocation GRADIENT_TEXTURE = new ResourceLocation(DocMod.MOD_ID + ":textures/gui/gui_gain_xp.png");

	/**
	 * Renders a texture on the screen
	 * @param mStack
	 * @param gui
	 * @param x
	 * @param y
	 * @param textX
	 * @param textY
	 * @param width
	 * @param height
	 */
	public static void drawTexture(PoseStack mStack, Button gui, int x, int y, int textX, int textY, int width, int height) {
		gui.blit(mStack, x, y, textX, textY, width, height);
	}

	/**
	 * Renders a scaled texture on the screen
	 * @param mStack
	 * @param x
	 * @param y
	 * @param u
	 * @param v
	 * @param uWidth
	 * @param vHeight
	 * @param width
	 * @param height
	 */
	public static void drawScaledTexture(PoseStack mStack, int x, int y, float u, float v, int uWidth, int vHeight, int width, int height) {
		Screen.blit(mStack, x, y, u, v, width, height, uWidth, vHeight);
    }

	/**
	 * Renders the job icon on the screen
	 * @param mStack
	 * @param gui
	 * @param job
	 * @param centerX
	 * @param centerY
	 * @param size
	 */
	public static void drawJobIcon(PoseStack mStack, Button gui, String job, int centerX, int centerY, int size) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		ClientJobsData.JOBS_ICONS.get(job).bind();
		GuiUtil.drawScaledTexture(mStack, centerX-size/2, centerY-size/2, 0, 0, size, size, size, size);
	}
	public static void drawJobIcon(PoseStack mStack, Screen gui, String job, int centerX, int centerY, int size) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		ClientJobsData.JOBS_ICONS.get(job).bind();
		GuiUtil.drawScaledTexture(mStack, centerX-size/2, centerY-size/2, 0, 0, size, size, size, size);
	}

	/**
	 * Renders an entity on the screen
	 * @param posX
	 * @param posY
	 * @param scale
	 * @param mouseX
	 * @param mouseY
	 * @param entity
	 */
	public static void renderEntityInGui(PoseStack poseStack, int posX, int posY, int scale, float mouseX, float mouseY, LivingEntity entity) {
		float size = scale*1.1f/entity.getBbHeight();
		float f = (float)Math.atan((double)(mouseX / 40.0F));
		float f1 = (float)Math.atan((double)(mouseY / 40.0F));
		GL11.glPushMatrix();
		poseStack.translate((float)posX, (float)posY, 1050.0F);
		poseStack.scale(1.0F, 1.0F, -1.0F);
		PoseStack PoseStack = new PoseStack();
		PoseStack.translate(0.0D, 0.0D, 1000.0D);
		PoseStack.scale(size, size, size);
		Axis quaternion = (Axis) Axis.ZP.rotationDegrees(180.0F);
		Axis quaternion1 = (Axis) Axis.XP.rotationDegrees(f1 * 20.0F);
		PoseStack.mulPose((Quaternionf) quaternion1);
		PoseStack.mulPose((Quaternionf) quaternion);
		float f2 = entity.yBodyRot;
		float f3 = entity.yRotO;
		float f4 = entity.xRotO;
		float f5 = entity.yHeadRotO;
		float f6 = entity.yHeadRot;
		entity.yBodyRot = 180.0F + f * 20.0F;
		entity.yRotO = 180.0F + f * 40.0F;
		entity.xRotO = -f1 * 20.0F;
		entity.yHeadRot = entity.yRotO;
		entity.yHeadRotO = entity.yRotO;
		EntityRenderDispatcher entityrenderermanager = Minecraft.getInstance().getEntityRenderDispatcher();
		entityrenderermanager.overrideCameraOrientation((Quaternionf) quaternion1);
		entityrenderermanager.setRenderShadow(false);
		MultiBufferSource irendertypebuffer$impl = Minecraft.getInstance().renderBuffers().bufferSource();
		RenderSystem.runAsFancy(() -> {
			entityrenderermanager.render(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, PoseStack, irendertypebuffer$impl, 15728880);
		});
		irendertypebuffer$impl.getBuffer(RenderType.LINES);
		entityrenderermanager.setRenderShadow(true);
		entity.yBodyRot = f2;
		entity.yRotO = f3;
		entity.xRotO = f4;
		entity.yHeadRotO = f5;
		entity.yHeadRot = f6;
		GL11.glPopMatrix();
	}

	/**
	 * Renders a centered string on the screen
	 * @param mStack
	 * @param text
	 * @param color
	 * @param x
	 * @param y
	 * @param scale
	 */
	public static void renderCenteredString(PoseStack mStack, String text, int color, int x, int y, float scale){
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, 0);
		GL11.glScalef(scale, scale, scale);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		float xPos = Minecraft.getInstance().font.width(text)/-2.0f;
		float yPos = -4.5f;
		Minecraft.getInstance().font.draw(mStack, text, xPos, yPos, color);
		GL11.glPopMatrix();
	}

	/**
	 * Renders a progress bar with the progress written inside in the format "progress/total"
	 * @param mStack
	 * @param gui
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param progress
	 * @param total
	 */
	public static void renderProgressBar(PoseStack mStack, Button gui, int x, int y, int width, int height, long progress, long total){
		String info = progress < total ? progress + "/" + total :  I18n.get("text.level.max");
		renderProgressBarWithText(mStack, gui, x, y, width, height, progress, total, info);
	}

	/**
	 * Renders a progress bar with a custom text inside
	 * @param mStack
	 * @param gui
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param progress
	 * @param total
	 * @param text
	 */
	public static void renderProgressBarWithText(PoseStack mStack, Button gui, int x, int y, int width, int height, long progress, long total, String text){
		Minecraft.getInstance().getTextureManager().bindForSetup(GRADIENT_TEXTURE);
		if(progress >= total)
			drawTexture(mStack, gui, x, y, 0, 74, width, height); //full
		else{
			int size = (int)(width*((double)progress / (double)total));
			drawTexture(mStack, gui, x, y, 0, 50, width, height); //background
			drawTexture(mStack, gui, x, y, 0, 62, size, height);  // progress
		}
		renderCenteredString(mStack, text, Color.white.getRGB(), x+width/2, y+height/2+1, 0.6f);
	}

}
