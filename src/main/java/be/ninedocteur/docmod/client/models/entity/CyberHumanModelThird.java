package be.ninedocteur.docmod.client.models.entity;

import be.ninedocteur.docmod.common.entity.mob.CyberHumanEntityThird;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class CyberHumanModelThird<T extends CyberHumanEntityThird> extends HumanoidModel<T> {
    private final ModelPart head;
    private final ModelPart hat;
    private final ModelPart body;
    private final ModelPart rightarm;
    private final ModelPart leftarm;
    private final ModelPart rightleg;
    private final ModelPart leftleg;
    //private final ModelPart body_lightmap;
    //private final ModelPart head_lightmap;



    public CyberHumanModelThird(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.rightarm = root.getChild("right_arm");
        this.leftarm = root.getChild("left_arm");
        this.rightleg = root.getChild("right_leg");
        this.leftleg = root.getChild("left_leg");
       // this.body_lightmap = root.getChild("body_lightmap");
      //  this.head_lightmap = root.getChild("head_lightmap");
        this.hat = root.getChild("hat");
    }

    public static LayerDefinition createbodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-12.0F, -8.0F, 4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(32, 0).addBox(-12.0F, -8.0F, 4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(8.0F, 0.0F, -8.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-12.0F, 0.0F, 6.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(16, 32).addBox(-12.0F, 0.0F, 6.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
                .texOffs(19, 52).addBox(-4.0F, -5.0F, 7.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(60, 37).addBox(-3.0F, -10.4F, 7.6F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 0.0F, -8.0F));

        PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(60, 38).addBox(0.8333F, -3.4667F, -0.4667F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(21, 53).addBox(-0.1667F, 1.9333F, -1.0667F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.8333F, -6.9333F, 8.0667F, 3.1416F, 0.0F, 3.1416F));

        PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 32).addBox(-11.0F, -2.0F, 6.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(3.0F, 2.0F, -8.0F));

        PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-9.0F, -2.0F, 6.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(32, 48).addBox(-21.0F, -2.0F, 6.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(48, 48).addBox(-9.0F, -2.0F, 6.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(13.0F, 2.0F, -8.0F));

        PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-10.0F, 0.0F, 6.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 32).addBox(-10.0F, 0.0F, 6.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(6.1F, 12.0F, -8.0F));

        PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-10.0F, 0.0F, 6.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 48).addBox(-10.0F, 0.0F, 6.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(9.9F, 12.0F, -8.0F));

        PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -16.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);

    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch) {

        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netheadYaw, headPitch);
        this.leftleg.copyFrom(this.leftleg);
        this.rightleg.copyFrom(this.rightleg);
        this.leftarm.copyFrom(this.leftarm);
        this.rightarm.copyFrom(this.rightarm);
        this.body.copyFrom(this.body);
        this.head.copyFrom(this.head);
       // this.body_lightmap.copyFrom(this.body);
       // this.head_lightmap.copyFrom(this.head);

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(poseStack, buffer, packedLight, packedOverlay);
        body.render(poseStack, buffer, packedLight, packedOverlay);
        rightarm.render(poseStack, buffer, packedLight, packedOverlay);
        leftarm.render(poseStack, buffer, packedLight, packedOverlay);
        rightleg.render(poseStack, buffer, packedLight, packedOverlay);
        leftleg.render(poseStack, buffer, packedLight, packedOverlay);
        /*
        body_lightmap.render(poseStack, buffer, ModelUtils.getModelGlow(1F), packedOverlay);
        head_lightmap.render(poseStack, buffer, ModelUtils.getModelGlow(1F), packedOverlay);

         */

    }


}
