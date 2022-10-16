package be.ninedocteur.docmod.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.models.TardisModel;
import be.ninedocteur.docmod.registry.ModelRegistry;
import be.ninedocteur.docmod.common.tileentity.TardisTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TardisRender implements BlockEntityRenderer<TardisTileEntity> {


    public static TardisModel model;

    public TardisRender(BlockEntityRendererProvider.Context pContextd) {
        model = new TardisModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelRegistry.NEW_Tardis));
    }

    @Override
    public void render(TardisTileEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        pPoseStack.pushPose();
        pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(180));
        pPoseStack.translate(-0.5, -1.0, 0.5);
        pPoseStack.scale(0.68f, 0.68f, 0.68f);
        model.renderToBuffer(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/2018_police_box.png"))), pPackedLight, pPackedOverlay, 1,1,1,1);
        pPoseStack.popPose();
    }

}
