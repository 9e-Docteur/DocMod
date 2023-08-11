package be.ninedocteur.docmod.client.render;

import be.ninedocteur.docmod.client.models.SafeChestModel;
import be.ninedocteur.docmod.registry.ModelRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SafeChestRender /*implements BlockEntityRenderer<SafeChestTileEntity>*/ {


    public static SafeChestModel model;

    public SafeChestRender(BlockEntityRendererProvider.Context pContextd) {
        model = new SafeChestModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelRegistry.SAFE_CHEST));
    }

   // @Override
   // public void render(SafeChestTileEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
      //  pPoseStack.pushPose();
       // pPoseStack.mulPose(Axis.ZP.rotationDegrees(180));
       // pPoseStack.translate(-0.5, -1.5, -0.5);
       // model.renderToBuffer(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/safe_chest_perm.png"))), pPackedLight, pPackedOverlay, 1,1,1,1);
       // if(pBlockEntity.isOwner(Minecraft.getInstance().player.getUUID()) || pBlockEntity.isPersonAllowedToUseChest(Minecraft.getInstance().player.getUUID())){
         //   model.renderAccess(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/safe_chest_perm.png"))), ModelUtils.getModelGlow(1F), pPackedOverlay);
       // } else {
        //    model.renderAccess(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/safe_chest_no_perm.png"))), ModelUtils.getModelGlow(1F), pPackedOverlay);
       // }
       // pPoseStack.popPose();
    //}
}
