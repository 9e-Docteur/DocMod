package be.ninedocteur.docmod.client.render;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.models.SafeChestModel;
import be.ninedocteur.docmod.client.models.ToyotaRotorModel;
import be.ninedocteur.docmod.common.tileentity.SafeChestTileEntity;
import be.ninedocteur.docmod.common.tileentity.ToyotaRotorTileEntity;
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
import net.minecraft.world.entity.player.Player;
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
