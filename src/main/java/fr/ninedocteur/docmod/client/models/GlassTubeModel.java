package fr.ninedocteur.docmod.client.models;

import fr.ninedocteur.docmod.utils.ModelUtils;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class GlassTubeModel<T extends Entity> extends EntityModel<T> {
    private final ModelPart glass;
    private final ModelPart rods;

    public GlassTubeModel(ModelPart root) {
        this.glass = root.getChild("glass");
        this.rods = root.getChild("rods");
    }

    public static TexturedModelData createBodyLayer() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData glass = modelPartData.addChild("glass", ModelPartBuilder.create().uv(0, 0).cuboid(-24.0F, -128.0F, -24.0F, 48.0F, 128.0F, 48.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        ModelPartData rods = modelPartData.addChild("rods", ModelPartBuilder.create().uv(200, 114).cuboid(-21.0F, -128.0F, -20.0F, 14.0F, 128.0F, 14.0F, new Dilation(0.0F))
                .uv(200, 114).cuboid(-21.0F, -128.0F, 6.0F, 14.0F, 128.0F, 14.0F, new Dilation(0.0F))
                .uv(200, 114).cuboid(6.0F, -128.0F, -20.0F, 14.0F, 128.0F, 14.0F, new Dilation(0.0F))
                .uv(200, 114).cuboid(6.0F, -128.0F, 6.0F, 14.0F, 128.0F, 14.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        return TexturedModelData.of(modelData, 256, 256);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {}

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        glass.render(matrices, vertices, light, overlay);
        rods.render(matrices, vertices, ModelUtils.getModelGlow(2F), overlay);
    }
}
