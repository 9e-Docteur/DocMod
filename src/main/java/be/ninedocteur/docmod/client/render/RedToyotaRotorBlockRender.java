package be.ninedocteur.docmod.client.render;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.models.RedToyotaRotorModel;
import be.ninedocteur.docmod.common.tileentity.RedToyotaRotorTileEntity;
import be.ninedocteur.docmod.registry.ModelRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RedToyotaRotorBlockRender implements BlockEntityRenderer<RedToyotaRotorTileEntity> {


    public static RedToyotaRotorModel model;

    public RedToyotaRotorBlockRender(BlockEntityRendererProvider.Context pContextd) {
        model = new RedToyotaRotorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelRegistry.RED_TOYOTA));
    }

    @Override
    public void render(RedToyotaRotorTileEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        pPoseStack.pushPose();
        pPoseStack.translate(0.5, 1.5, 0.5);
        model.renderToBuffer(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/red_toyota_rotor.png"))), pPackedLight, pPackedOverlay, 1,1,1,1);
        pPoseStack.popPose();
    }
}
