package be.ninedocteur.docmod.client.models.entity;

import be.ninedocteur.docmod.common.entity.mob.Dalek;
import be.ninedocteur.docmod.common.entity.mob.SWDalek;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class SpecialWeaponsDalekEntityModel<T extends SWDalek> extends EntityModel<T> {

    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart gun;

    public SpecialWeaponsDalekEntityModel(ModelPart root) {
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.gun = root.getChild("gun");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 50).addBox(-4.0F, -30.0F, -5.0F, 9.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 46).addBox(-5.0F, -27.0F, -6.0F, 11.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(56, 0).addBox(-1.0F, -30.0F, -8.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(56, 0).addBox(-1.0F, -30.0F, -9.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(56, 0).addBox(-1.0F, -30.0F, -10.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(4, 12).addBox(-1.0F, -30.0F, -11.9F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition cube_r7_r1 = head.addOrReplaceChild("cube_r7_r1", CubeListBuilder.create().texOffs(60, 25).addBox(7.4F, -7.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.1302F, -26.3136F, 0.5F, 0.0F, 0.0F, 0.4363F));

        PartDefinition cube_r7_r2 = head.addOrReplaceChild("cube_r7_r2", CubeListBuilder.create().texOffs(60, 25).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.1302F, -27.3136F, 0.5F, 0.0F, 0.0F, -0.4363F));

        PartDefinition cube_r6 = head.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -29.0F, 7.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(52, 5).addBox(6.5F, -17.5F, -6.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
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
                .texOffs(0, 45).addBox(-6.0F, -19.0F, -7.0F, 13.0F, 6.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(0, 44).addBox(-6.0F, -13.0F, -8.0F, 13.0F, 5.0F, 15.0F, new CubeDeformation(0.0F))
                .texOffs(0, 42).addBox(-7.0F, -8.0F, -9.0F, 15.0F, 6.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(26, 10).addBox(-4.0F, -22.0F, -5.0F, 9.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(26, 10).addBox(-4.0F, -23.1F, -5.0F, 9.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 50).addBox(-5.0F, -20.0F, -6.0F, 11.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(0, 50).addBox(-5.0F, -22.0F, -6.0F, 11.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(52, 5).addBox(6.5F, -17.0F, -6.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
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

        PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0F, -0.5F, 0.0F, 0.0F, -1.5708F, 0.0F));

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

        PartDefinition cube_r13 = body.addOrReplaceChild("cube_r13", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition cube_r11 = body.addOrReplaceChild("cube_r11", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 3.1416F, -1.5708F, 0.0F));

        PartDefinition cube_r12 = body.addOrReplaceChild("cube_r12", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r10 = body.addOrReplaceChild("cube_r10", CubeListBuilder.create(), PartPose.offsetAndRotation(-10.0F, -18.75F, -1.0F, 3.1416F, -1.5708F, 1.5708F));

        PartDefinition cube_r9 = body.addOrReplaceChild("cube_r9", CubeListBuilder.create(), PartPose.offsetAndRotation(-10.0F, -18.75F, -1.0F, 0.0F, -1.5708F, 1.5708F));

        PartDefinition gun = partdefinition.addOrReplaceChild("gun", CubeListBuilder.create().texOffs(14, 31).addBox(-2.0F, -17.4F, -8.3F, 5.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(1, 30).addBox(-1.6F, -17.4F, -12.3F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 29).addBox(-1.6F, -17.4F, -15.3F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(14, 26).addBox(-0.5F, -16.4F, -18.8F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.head.zRot = 0.0F;
        this.body.yRot = 0.0F;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(poseStack, buffer, packedLight, packedOverlay);
        body.render(poseStack, buffer, packedLight, packedOverlay);
        gun.render(poseStack, buffer, packedLight, packedOverlay);
    }

}