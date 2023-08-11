package be.ninedocteur.docmod.client.render;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.models.MonitorModel;
import be.ninedocteur.docmod.common.tileentity.MonitorTileEntity;
import be.ninedocteur.docmod.registry.ModelRegistry;
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
public class MonitorBlockRender implements BlockEntityRenderer<MonitorTileEntity> {


    public static MonitorModel model;

    public MonitorBlockRender(BlockEntityRendererProvider.Context pContextd) {
        model = new MonitorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelRegistry.MONITOR));
    }

    @Override
    public void render(MonitorTileEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        pPoseStack.pushPose();
        pPoseStack.translate(0.5, 1.5, 0.5);
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(180));
        model.renderToBuffer(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/monitor.png"))), pPackedLight, pPackedOverlay, 1,1,1,1);
        pPoseStack.popPose();
    }
}
