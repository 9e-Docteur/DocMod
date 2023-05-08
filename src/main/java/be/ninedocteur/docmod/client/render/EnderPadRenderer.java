package be.ninedocteur.docmod.client.render;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.models.ComputerModel;
import be.ninedocteur.docmod.client.models.EnderPadModel;
import be.ninedocteur.docmod.client.models.HartnellModel;
import be.ninedocteur.docmod.client.models.MonitorModel;
import be.ninedocteur.docmod.client.models.ZurbTeleporterModel;
import be.ninedocteur.docmod.common.tileentity.*;
import be.ninedocteur.docmod.registry.ModelRegistry;
import be.ninedocteur.docmod.utils.IOUtils;
import be.ninedocteur.docmod.utils.ModelUtils;

import java.util.Random;

import org.joml.Matrix4f;
import org.joml.Vector3d;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Font.DisplayMode;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EnderPadRenderer implements BlockEntityRenderer<EnderPadTile> {

	 public static EnderPadModel model;

	    public EnderPadRenderer(BlockEntityRendererProvider.Context pContextd) {
	        model = new EnderPadModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelRegistry.PAD));
	    }

    @Override
    public void render(EnderPadTile pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
    	pPoseStack.pushPose();
        pPoseStack.translate(0.5, 1.5, 0.5);
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(180));
        model.renderToBuffer(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/ender_pad.png"))), pPackedLight, pPackedOverlay, 1,1,1,1);
        if(pBlockEntity.hasName) {
            renderLabel(pPoseStack, pBufferSource, LightTexture.FULL_BRIGHT, new Vector3d(0, -1.5, 0), Component.literal(pBlockEntity.getName()), -1);
        }
        pPoseStack.popPose();
    }
    
    public static void renderLabel(PoseStack stack, MultiBufferSource buffer, int lightLevel, Vector3d corner, Component text, int color) {
    	Minecraft mc = Minecraft.getInstance();
		Font font = mc.font;
        float scale = 0.025f;
        int opacity = (int) (.4f * 255.0f) << 24;
        float offset = (float) (-font.width(text) / 2);
        stack.pushPose();
        Matrix4f matrix = stack.last().pose();
        stack.translate(corner.x, corner.y + .4f, corner.z);
        stack.scale(scale, scale, scale);
        stack.mulPose(Axis.YP.rotationDegrees(-mc.player.getYRot()));
        //stack.mulPose(Axis.ZP.rotationDegrees(180f));
        font.drawInBatch(text, offset, 0, color, false, matrix, buffer, DisplayMode.NORMAL, opacity, lightLevel);
        stack.popPose();
    }
	
}
