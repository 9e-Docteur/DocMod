package be.ninedocteur.docmod.utils;

import be.ninedocteur.docmod.client.models.TardisModel;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

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

    public static void renderEntityModelOnScreen(PoseStack poseStack, int posX, int posY, int scale, float rotation, float angleYComponent, Model p_98856_, ResourceLocation resourceLocation, float r, float g, float b, float a) {
        float f1 = angleYComponent;
        poseStack.pushPose();
        poseStack.translate((double)posX, (double)posY, 1050.0D);
        poseStack.scale(1.0F, 1.0F, -1.0F);
        RenderSystem.applyModelViewMatrix();
        PoseStack posestack1 = new PoseStack();
        posestack1.translate(0.0D, 0.0D, 1000.0D);
        posestack1.scale((float)scale, (float)scale, (float)scale);
        Quaternion quaternion = Vector3f.ZN.rotationDegrees(0F);
        Quaternion quaternion1 = Vector3f.XN.rotationDegrees(-25F);
        Quaternion quaternion2 = Vector3f.YN.rotationDegrees(rotation);
        posestack1.mulPose(quaternion);
        posestack1.mulPose(quaternion1);
        posestack1.mulPose(quaternion2);
        Lighting.setupForEntityInInventory();
        EntityRenderDispatcher entityrenderdispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        quaternion1.conj();
        entityrenderdispatcher.overrideCameraOrientation(quaternion1);
        entityrenderdispatcher.setRenderShadow(false);
        MultiBufferSource.BufferSource multibuffersource$buffersource = Minecraft.getInstance().renderBuffers().bufferSource();
        RenderSystem.runAsFancy(() -> {
            p_98856_.renderToBuffer(poseStack, multibuffersource$buffersource.getBuffer(RenderType.entityTranslucent(resourceLocation)), 15728880, OverlayTexture.NO_OVERLAY, r, g, b, a);
            //entityrenderdispatcher.render(p_98856_, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, posestack1, multibuffersource$buffersource, 15728880);
        });
        multibuffersource$buffersource.endBatch();
        entityrenderdispatcher.setRenderShadow(true);
        poseStack.popPose();
        RenderSystem.applyModelViewMatrix();
        Lighting.setupFor3DItems();
    }

    public static void renderEntityOnScreen(PoseStack poseStack, int posX, int posY, int scale, float rotation, float angleYComponent, LivingEntity p_98856_) {
        float f1 = angleYComponent;
        poseStack.pushPose();
        poseStack.translate((double)posX, (double)posY, 1050.0D);
        poseStack.scale(1.0F, 1.0F, -1.0F);
        RenderSystem.applyModelViewMatrix();
        PoseStack posestack1 = new PoseStack();
        posestack1.translate(0.0D, 0.0D, 1000.0D);
        posestack1.scale((float)scale, (float)scale, (float)scale);
        Quaternion quaternion = Vector3f.ZN.rotationDegrees(0F);
        Quaternion quaternion1 = Vector3f.XN.rotationDegrees(-25F);
        Quaternion quaternion2 = Vector3f.YN.rotationDegrees(rotation);
        posestack1.mulPose(quaternion);
        posestack1.mulPose(quaternion1);
        posestack1.mulPose(quaternion2);
        Lighting.setupForEntityInInventory();
        EntityRenderDispatcher entityrenderdispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        quaternion1.conj();
        entityrenderdispatcher.overrideCameraOrientation(quaternion1);
        entityrenderdispatcher.setRenderShadow(false);
        MultiBufferSource.BufferSource multibuffersource$buffersource = Minecraft.getInstance().renderBuffers().bufferSource();
        RenderSystem.runAsFancy(() -> {
            entityrenderdispatcher.render(p_98856_, 0.0, 0.0, 0.0, 0.0F, 1.0F, posestack1, multibuffersource$buffersource, 15728880);
            //entityrenderdispatcher.render(p_98856_, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, posestack1, multibuffersource$buffersource, 15728880);
        });
        multibuffersource$buffersource.endBatch();
        entityrenderdispatcher.setRenderShadow(true);
        poseStack.popPose();
        RenderSystem.applyModelViewMatrix();
        Lighting.setupFor3DItems();
    }


    public static void scissor(int screenX, int screenY, int boxWidth, int boxHeight)
    {
        Minecraft mc = Minecraft.getInstance();
        int scale = (int) mc.getWindow().getGuiScale();
        int x = screenX * scale;
        int y = mc.getWindow().getHeight() - screenY * scale - boxHeight * scale;
        int width = Math.max(0, boxWidth * scale);
        int height = Math.max(0, boxHeight * scale);
        RenderSystem.enableScissor(x, y, width, height);
    }

    public static boolean isMouseWithin(int x, int y, int width, int height, int mouseX, int mouseY)
    {
        return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
    }
}
