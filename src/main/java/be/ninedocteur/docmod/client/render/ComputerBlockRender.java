package be.ninedocteur.docmod.client.render;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ComputerBlockRender/* implements BlockEntityRenderer<ComputerTileEntity>*/ {


//    public static ComputerModel model;
//
//    public ComputerBlockRender(BlockEntityRendererProvider.Context pContextd) {
//
//    }
//
//    @Override
//    public void render(ComputerTileEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
//        pPoseStack.pushPose();
//        pPoseStack.translate(0.5, 1.5, 0.5);
//        pPoseStack.mulPose(Axis.ZP.rotationDegrees(180));
//        new ComputerModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelRegistry.COMPUTER), pBlockEntity).renderToBuffer(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/computer_2.png"))), pPackedLight, pPackedOverlay, 1,1,1,1);
//        pPoseStack.popPose();
//    }
}
