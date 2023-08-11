package be.ninedocteur.docmod.client.models;

import be.ninedocteur.docmod.utils.ModelUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZurbTeleporterModel<T extends Entity> extends EntityModel<T> {
    private final ModelPart block;
    private final ModelPart lightmap;

    public ZurbTeleporterModel(ModelPart root) {
        this.block = root.getChild("block");
        this.lightmap = root.getChild("lightmap");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition block = partdefinition.addOrReplaceChild("block", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, -6.0F, 0.0F, 16.0F, 6.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(0, 45).addBox(-16.0F, -9.0F, 0.0F, 16.0F, 3.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(17, 4).addBox(-11.0F, -7.0F, 4.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 24.0F, -8.0F));

        PartDefinition cube_r1 = block.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(17, 4).addBox(-4.75F, -0.5F, -2.25F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.25F, -6.5F, 9.75F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r2 = block.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(17, 4).addBox(-1.25F, -0.5F, -2.25F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.25F, -6.5F, 9.75F, -3.1416F, 0.0F, 3.1416F));

        PartDefinition cube_r3 = block.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(17, 4).addBox(-6.5F, -0.5F, -4.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, -6.5F, 4.5F, 0.0F, 1.5708F, 0.0F));

        PartDefinition lightmap = partdefinition.addOrReplaceChild("lightmap", CubeListBuilder.create().texOffs(40, 39).addBox(-11.0F, -6.1F, 5.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 24.0F, -8.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        block.render(poseStack, buffer, packedLight, packedOverlay);
        lightmap.render(poseStack, buffer, ModelUtils.getModelGlow(1F), packedOverlay);
    }
}
