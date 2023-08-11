package be.ninedocteur.docmod.client.render;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TardisDoorRender { //implements BlockEntityRenderer<TardisDoorTileEntity> {

//
//    public static TardisDoorModel model;
//
//    public TardisDoorRender(BlockEntityRendererProvider.Context pContextd) {
//        model = new TardisDoorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelRegistry.TARDIS_DOOR));
//    }
//
//    @Override
//    public void render(TardisDoorTileEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
//        pPoseStack.pushPose();
//        pPoseStack.translate(0.5, 1.5, 0.5);
//        model.renderToBuffer(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/tardis_door.png"))), pPackedLight, pPackedOverlay, 1,1,1,1);
//        pPoseStack.popPose();
//    }

}
