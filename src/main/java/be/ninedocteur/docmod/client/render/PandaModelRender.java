package be.ninedocteur.docmod.client.render;

import be.ninedocteur.docmod.common.tileentity.PandaTileEntity;
import be.ninedocteur.docmod.registry.ModelRegistry;
import be.ninedocteur.docmod.utils.PlayerUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PandaModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PandaModelRender implements BlockEntityRenderer<PandaTileEntity> {


    public static PandaModel model;

    public PandaModelRender(BlockEntityRendererProvider.Context pContextd) {
        model = new PandaModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelRegistry.PANDA_SKIN));
    }

    @Override
    public void render(PandaTileEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        pPoseStack.pushPose();
        pPoseStack.translate(0.5, 1.5, 0.5);
        model.renderToBuffer(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(PlayerUtils.getSkin("45949380-580d-4dd3-8526-6ee05dd75c22"))), pPackedLight, pPackedOverlay, 1,1,1,1);
        pPoseStack.popPose();
    }
}
