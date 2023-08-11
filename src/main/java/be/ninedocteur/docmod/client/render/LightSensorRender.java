package be.ninedocteur.docmod.client.render;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.models.LightSensorModel;
import be.ninedocteur.docmod.common.tileentity.LightSensorTile;
import be.ninedocteur.docmod.registry.ModelRegistry;
import be.ninedocteur.docmod.utils.ModelUtils;
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
public class LightSensorRender implements BlockEntityRenderer<LightSensorTile> {


    public static LightSensorModel model;

    public LightSensorRender(BlockEntityRendererProvider.Context pContextd) {
        model = new LightSensorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelRegistry.LIGHT_SENSOR));
    }

    @Override
    public void render(LightSensorTile pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        pPoseStack.pushPose();
        pPoseStack.translate(0.5, 1.5, 0.5);
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(180));
        model.renderToBuffer(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/light_sensor.png"))), pPackedLight, pPackedOverlay, 1,1,1,1f);
        model.renderLightMap(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/light_sensor.png"))), pPackedLight, pPackedOverlay, 1,1,ModelUtils.getModelGlow(0D),0.2F);

        if(pBlockEntity.shoudShowLight) {
            model.renderLightMap(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/light_sensor.png"))), pPackedLight, pPackedOverlay, 1,1,ModelUtils.getModelGlow(1D),1);
        }
        pPoseStack.popPose();
    }
    
}
