package fr.ninedocteur.docmod.client.render;

import fr.ninedocteur.docmod.DocMod;
import fr.ninedocteur.docmod.client.models.GlassTubeModel;
import fr.ninedocteur.docmod.common.block.entity.GlassTubeEntity;
import fr.ninedocteur.docmod.registry.ModelRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class GlassTubeRenderer implements BlockEntityRenderer<GlassTubeEntity> {

    public static GlassTubeModel model;

    public GlassTubeRenderer(BlockEntityRendererFactory.Context context) {
        model = new GlassTubeModel(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(ModelRegistry.Glass));
    }

    @Override
    public void render(GlassTubeEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();
        matrices.translate(0.5, 6.5, 0.5);
        model.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(new Identifier(DocMod.MOD_ID, "textures/block/sglass_tube.png"))), light, overlay, 1, 1, 1, 1);
        matrices.pop();
    }
}
