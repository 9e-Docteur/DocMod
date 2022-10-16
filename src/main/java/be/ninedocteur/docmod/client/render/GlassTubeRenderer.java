package be.ninedocteur.docmod.client.render;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.models.GlassTubeModel;
import be.ninedocteur.docmod.common.tileentity.GlassTubeTile;
import com.mojang.blaze3d.vertex.PoseStack;
import be.ninedocteur.docmod.registry.ModelRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GlassTubeRenderer implements BlockEntityRenderer<GlassTubeTile> {


    public static GlassTubeModel model;

    public GlassTubeRenderer(BlockEntityRendererProvider.Context pContextd) {
        model = new GlassTubeModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelRegistry.Glass));
    }

    @Override
    public void render(GlassTubeTile pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        pPoseStack.pushPose();
        pPoseStack.translate(0.5, 6.5, 0.5);
        model.renderToBuffer(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/sglass_tube.png"))), pPackedLight, pPackedOverlay, 1,1,1,1);
        pPoseStack.popPose();
    }
}
