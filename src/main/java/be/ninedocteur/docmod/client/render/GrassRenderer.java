package be.ninedocteur.docmod.client.render;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.models.ComputerModel;
import be.ninedocteur.docmod.client.models.GrassModel;
import be.ninedocteur.docmod.client.models.HartnellModel;
import be.ninedocteur.docmod.client.models.LightSensorModel;
import be.ninedocteur.docmod.client.models.MonitorModel;
import be.ninedocteur.docmod.common.tileentity.ComputerTileEntity;
import be.ninedocteur.docmod.common.tileentity.GrassTile;
import be.ninedocteur.docmod.common.tileentity.HartnellTileEntity;
import be.ninedocteur.docmod.common.tileentity.LightSensorTile;
import be.ninedocteur.docmod.common.tileentity.MonitorTileEntity;
import be.ninedocteur.docmod.registry.ModelRegistry;
import be.ninedocteur.docmod.utils.ModelUtils;

import java.util.Random;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GrassRenderer implements BlockEntityRenderer<GrassTile> {


    public static GrassModel model;

    public GrassRenderer(BlockEntityRendererProvider.Context pContextd) {
        model = new GrassModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelRegistry.GRASS));
    }

    @Override
    public void render(GrassTile pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        pPoseStack.pushPose();
        pPoseStack.translate(0.5, -1.05, 0.5);
        //pPoseStack.mulPose(Axis.ZP.rotationDegrees(180))
        //System.out.println(pBlockEntity.stage);
        if(pBlockEntity.stage == 1) {
            model.renderStage1(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/grass.png"))), pPackedLight, pPackedOverlay, 1,1,1,1f);
        } else if(pBlockEntity.getStage() == 2) {
            model.renderStage2(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/grass.png"))), pPackedLight, pPackedOverlay, 1,1,1,1f);
        } else if(pBlockEntity.getStage() == 3) {
            model.renderStage3(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/grass.png"))), pPackedLight, pPackedOverlay, 1,1,1,1f);
        } else if(pBlockEntity.getStage() == 4) {
            model.renderStage4(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/grass.png"))), pPackedLight, pPackedOverlay, 1,1,1,1f);
        } else if(pBlockEntity.getStage() == 5) {
            model.renderStage5(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/grass.png"))), pPackedLight, pPackedOverlay, 1,1,1,1f);
        } else {
            model.renderStage5(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/grass.png"))), pPackedLight, pPackedOverlay, 1,1,1,1f);
            //System.out.println(pBlockEntity.stage);
        }
        pPoseStack.popPose();
    }
    
}
