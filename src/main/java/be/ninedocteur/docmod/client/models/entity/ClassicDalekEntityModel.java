package be.ninedocteur.docmod.client.models.entity;// Made with Blockbench 4.1.5
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


import be.ninedocteur.docmod.common.entity.mob.ClassicDalek;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class ClassicDalekEntityModel<T extends ClassicDalek> extends EntityModel<T> {
    private final ModelPart head;
    private final ModelPart eyes;
    private final ModelPart body;

    public ClassicDalekEntityModel(ModelPart root) {
        this.head = root.getChild("head");
        this.eyes = head.getChild("eyes");
        this.body = root.getChild("body");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 50).addBox(-4.0F, -6.0F, -5.0F, 9.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 46).addBox(-5.0F, -3.0F, -6.0F, 11.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));

        PartDefinition cube_r7_r1 = head.addOrReplaceChild("cube_r7_r1", CubeListBuilder.create().texOffs(60, 25).addBox(-1.7F, 0.7F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.1302F, -6.3136F, 0.5F, 0.0F, 0.0F, -0.4363F));

        PartDefinition cube_r7_r2 = head.addOrReplaceChild("cube_r7_r2", CubeListBuilder.create().texOffs(60, 25).addBox(8.7F, -4.2F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.1302F, -5.3136F, 0.5F, 0.0F, 0.0F, 0.4363F));

        PartDefinition cube_r6 = head.addOrReplaceChild("cube_r6", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 23.0F, 3.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition eyes = head.addOrReplaceChild("eyes", CubeListBuilder.create().texOffs(38, 10).addBox(-0.5F, 0.5F, -7.72F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(56, 0).addBox(-1.5F, -0.5F, -3.72F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(56, 0).addBox(-1.5F, -0.5F, -4.72F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(56, 0).addBox(-1.5F, -0.5F, -5.72F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(4, 12).addBox(-1.5F, -0.5F, -7.62F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -5.5F, -4.28F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(11, 59).addBox(-2.0F, -17.5F, -7.5F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -17.5F, -6.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -17.5F, -4.8F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -17.5F, -3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -17.5F, -1.3F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -17.5F, 0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -17.5F, 2.2F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -17.5F, 3.9F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -17.5F, 5.6F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(1.0F, -11.0F, -8.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(4.0F, -11.0F, -8.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(-5.0F, -11.0F, -8.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(-2.0F, -11.0F, -8.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(-5.0F, -6.0F, -9.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(-2.0F, -6.0F, -9.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(1.0F, -6.0F, -9.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(4.0F, -6.0F, -9.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 1).addBox(-8.0F, -2.0F, -11.0F, 17.0F, 2.0F, 19.0F, new CubeDeformation(0.0F))
                .texOffs(0, 45).addBox(-6.0F, -18.0F, -7.0F, 13.0F, 5.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(0, 44).addBox(-6.0F, -13.0F, -8.0F, 13.0F, 5.0F, 15.0F, new CubeDeformation(0.0F))
                .texOffs(0, 42).addBox(-7.0F, -8.0F, -9.0F, 15.0F, 6.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(21, 18).addBox(-5.0F, -17.0F, -21.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(26, 10).addBox(-4.0F, -21.0F, -5.0F, 9.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(26, 10).addBox(-4.0F, -22.0F, -5.0F, 9.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 50).addBox(-5.0F, -19.0F, -6.0F, 11.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(0, 50).addBox(-5.0F, -21.0F, -6.0F, 11.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(13, 58).addBox(-7.5F, -17.0F, -4.8F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(13, 58).addBox(-7.5F, -17.0F, -6.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(13, 58).addBox(-7.5F, -17.0F, 2.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -17.0F, -6.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -17.0F, -4.6F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -17.0F, -2.8F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -17.0F, -1.1F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -17.0F, 0.7F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -17.0F, 2.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -17.0F, 4.2F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(4.0F, -5.5F, -8.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(0.0F, -5.5F, -8.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(-4.0F, -5.5F, -8.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(-8.0F, -5.5F, -8.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(-6.0F, -10.5F, -7.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(-3.0F, -10.5F, -7.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(0.0F, -10.5F, -7.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(3.0F, -10.5F, -7.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(52, 5).addBox(6.5F, -17.0F, -6.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -17.0F, -4.8F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -17.0F, -3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -17.0F, -1.3F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -17.0F, 0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -17.0F, 2.2F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -17.0F, 3.9F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -17.0F, 5.6F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -0.5F, 0.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(13, 58).addBox(-7.5F, -17.0F, 5.6F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -0.5F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r4 = body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(50, 0).addBox(3.0F, -11.0F, -7.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(3.0F, -6.0F, -7.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(0.0F, -6.0F, -7.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(0.0F, -11.0F, -7.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(-3.0F, -11.0F, -7.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(-6.0F, -11.0F, -7.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(-3.0F, -6.0F, -7.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(-6.0F, -6.0F, -7.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition cube_r5 = body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(50, 0).addBox(-6.0F, -6.0F, -7.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(-2.0F, -6.0F, -7.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(2.0F, -6.0F, -7.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(6.0F, -6.0F, -7.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(4.0F, -11.0F, -6.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(1.0F, -11.0F, -6.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(-2.0F, -11.0F, -6.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(-5.0F, -11.0F, -6.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition cube_r13 = body.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(41, 43).addBox(-5.0F, -16.0F, 6.0F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(36, 39).addBox(3.0F, -16.0F, 6.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition cube_r11 = body.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(36, 52).addBox(-14.0F, 14.0F, 4.5F, 7.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 3.1416F, -1.5708F, 0.0F));

        PartDefinition cube_r12 = body.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(36, 52).addBox(-14.0F, -17.0F, -4.5F, 7.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r10 = body.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(36, 52).addBox(-14.0F, 13.0F, 3.5F, 7.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, -18.75F, -1.0F, 3.1416F, -1.5708F, 1.5708F));

        PartDefinition cube_r9 = body.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(36, 52).addBox(-14.0F, -16.0F, -3.5F, 7.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, -18.75F, -1.0F, 0.0F, -1.5708F, 1.5708F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.eyes.xRot = headPitch * ((float)Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(poseStack, buffer, packedLight, packedOverlay);
        body.render(poseStack, buffer, packedLight, packedOverlay);
    }
}