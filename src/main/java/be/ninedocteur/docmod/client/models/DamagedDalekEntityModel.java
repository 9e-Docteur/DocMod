package be.ninedocteur.docmod.client.models;// Made with Blockbench 4.1.5
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


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
public class DamagedDalekEntityModel<T extends Entity> extends EntityModel<T> {
    private final ModelPart block;

    public DamagedDalekEntityModel(ModelPart root) {
        this.block = root.getChild("block");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition block = partdefinition.addOrReplaceChild("block", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

        PartDefinition eyes = block.addOrReplaceChild("eyes", CubeListBuilder.create(), PartPose.offsetAndRotation(0.4999F, -29.5F, -5.24F, 0.0F, 3.1416F, 0.0F));

        PartDefinition eyelightmap = block.addOrReplaceChild("eyelightmap", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 0.0F));

        PartDefinition earlightmap = block.addOrReplaceChild("earlightmap", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 0.0F));

        PartDefinition body = block.addOrReplaceChild("body", CubeListBuilder.create().texOffs(52, 5).addBox(-2.0F, -16.5F, -7.5F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -16.5F, -6.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -16.5F, -4.8F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -16.5F, -3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -16.5F, -1.3F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -16.5F, 0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -16.5F, 2.2F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -16.5F, 3.9F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -16.5F, 5.6F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(-5.0F, -5.0F, -9.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 1).addBox(-8.0F, -1.0F, -11.0F, 17.0F, 2.0F, 19.0F, new CubeDeformation(0.0F))
                .texOffs(0, 45).addBox(7.0F, -17.0F, -7.0F, 0.0F, 5.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(0, 45).addBox(-6.0F, -17.0F, -7.0F, 0.0F, 5.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(0, 44).addBox(-6.0F, -12.0F, -8.0F, 13.0F, 5.0F, 15.0F, new CubeDeformation(0.0F))
                .texOffs(0, 42).addBox(-7.0F, -7.0F, -9.0F, 15.0F, 6.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

        PartDefinition bb_main_r1 = body.addOrReplaceChild("bb_main_r1", CubeListBuilder.create().texOffs(21, 18).addBox(-1.5F, -1.5F, -4.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -11.5F, -17.0F, 0.2618F, -0.2182F, 0.0F));

        PartDefinition bb_main_r2 = body.addOrReplaceChild("bb_main_r2", CubeListBuilder.create().texOffs(0, 45).addBox(-7.0F, -2.5F, 0.0F, 0.0F, 5.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(0, 45).addBox(7.0F, -2.5F, 0.0F, 0.0F, 5.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, -14.5F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bb_main_r3 = body.addOrReplaceChild("bb_main_r3", CubeListBuilder.create().texOffs(50, 0).addBox(4.0F, -5.0F, -9.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(-5.0F, -10.0F, -8.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

        PartDefinition bb_main_r4 = body.addOrReplaceChild("bb_main_r4", CubeListBuilder.create().texOffs(50, 0).addBox(1.0F, -5.0F, -9.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3054F));

        PartDefinition bb_main_r5 = body.addOrReplaceChild("bb_main_r5", CubeListBuilder.create().texOffs(50, 0).addBox(4.0F, -10.0F, -8.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

        PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(52, 5).addBox(-7.5F, -16.0F, -4.8F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(-7.5F, -16.0F, -6.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(-7.5F, -16.0F, 2.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -16.0F, -6.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -16.0F, -4.6F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -16.0F, -2.8F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -16.0F, -1.1F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -16.0F, 0.7F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -16.0F, 2.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -16.0F, 4.2F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r1_r1 = cube_r1.addOrReplaceChild("cube_r1_r1", CubeListBuilder.create().texOffs(50, 0).addBox(3.0F, -10.0F, -7.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(0.0F, -10.0F, -7.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 0.0F, 0.0F, 0.0F, 0.1309F));

        PartDefinition cube_r1_r2 = cube_r1.addOrReplaceChild("cube_r1_r2", CubeListBuilder.create().texOffs(50, 0).addBox(-3.0F, -10.0F, -7.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(4.0F, -5.0F, -8.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 0.0F, 0.0F, 0.0F, 0.0436F));

        PartDefinition cube_r1_r3 = cube_r1.addOrReplaceChild("cube_r1_r3", CubeListBuilder.create().texOffs(50, 0).addBox(-6.0F, -10.0F, -7.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 0.0F, 0.0F, 0.0F, -0.0436F));

        PartDefinition cube_r1_r4 = cube_r1.addOrReplaceChild("cube_r1_r4", CubeListBuilder.create().texOffs(50, 0).addBox(-8.0F, -5.0F, -8.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(-4.0F, -5.0F, -8.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 0.0F, 0.0F, 0.0F, -0.1745F));

        PartDefinition cube_r1_r5 = cube_r1.addOrReplaceChild("cube_r1_r5", CubeListBuilder.create().texOffs(50, 0).addBox(0.0F, -5.0F, -8.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 0.0F, 0.0F, 0.0F, 0.2182F));

        PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(52, 5).addBox(6.5F, -16.0F, -6.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -16.0F, -4.8F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -16.0F, -3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -16.0F, -1.3F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -16.0F, 0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -16.0F, 2.2F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -16.0F, 3.9F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 5).addBox(6.5F, -16.0F, 5.6F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -0.5F, 0.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(52, 5).addBox(-7.5F, -16.0F, 5.6F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -0.5F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r4 = body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(50, 0).addBox(3.0F, -5.0F, -7.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(0.0F, -5.0F, -7.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition cube_r4_r1 = cube_r4.addOrReplaceChild("cube_r4_r1", CubeListBuilder.create().texOffs(50, 0).addBox(-6.0F, -5.0F, -7.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

        PartDefinition cube_r4_r2 = cube_r4.addOrReplaceChild("cube_r4_r2", CubeListBuilder.create().texOffs(50, 0).addBox(-3.0F, -5.0F, -7.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

        PartDefinition cube_r4_r3 = cube_r4.addOrReplaceChild("cube_r4_r3", CubeListBuilder.create().texOffs(50, 0).addBox(-6.0F, -10.0F, -7.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0436F));

        PartDefinition cube_r4_r4 = cube_r4.addOrReplaceChild("cube_r4_r4", CubeListBuilder.create().texOffs(50, 0).addBox(-3.0F, -10.0F, -7.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(0.0F, -10.0F, -7.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(3.0F, -10.0F, -7.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0873F));

        PartDefinition cube_r5 = body.addOrReplaceChild("cube_r5", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition cube_r5_r1 = cube_r5.addOrReplaceChild("cube_r5_r1", CubeListBuilder.create().texOffs(50, 0).addBox(-5.0F, -10.0F, -6.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1309F));

        PartDefinition cube_r5_r2 = cube_r5.addOrReplaceChild("cube_r5_r2", CubeListBuilder.create().texOffs(50, 0).addBox(-4.0F, -10.0F, -6.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(4.0F, -10.0F, -6.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1309F));

        PartDefinition cube_r5_r3 = cube_r5.addOrReplaceChild("cube_r5_r3", CubeListBuilder.create().texOffs(50, 0).addBox(1.0F, -11.0F, -6.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

        PartDefinition cube_r5_r4 = cube_r5.addOrReplaceChild("cube_r5_r4", CubeListBuilder.create().texOffs(50, 0).addBox(2.0F, -5.0F, -7.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3054F));

        PartDefinition cube_r5_r5 = cube_r5.addOrReplaceChild("cube_r5_r5", CubeListBuilder.create().texOffs(50, 0).addBox(-6.0F, -5.0F, -7.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

        PartDefinition cube_r13 = body.addOrReplaceChild("cube_r13", CubeListBuilder.create(), PartPose.offsetAndRotation(0.4999F, -14.5F, -12.5F, 0.0F, 3.1416F, 0.0F));

        PartDefinition cube_r13_r1 = cube_r13.addOrReplaceChild("cube_r13_r1", CubeListBuilder.create().texOffs(36, 39).addBox(-2.4525F, 2.2857F, -8.8054F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.9999F, 0.0F, 4.5F, -0.2618F, -0.2182F, 0.0F));

        PartDefinition cube_r13_r2 = cube_r13.addOrReplaceChild("cube_r13_r2", CubeListBuilder.create().texOffs(41, 43).addBox(-0.2679F, 0.6637F, -5.129F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.0F, -1.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition cube_r11 = body.addOrReplaceChild("cube_r11", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 3.1416F, -1.5708F, 0.0F));

        PartDefinition cube_r11_r1 = cube_r11.addOrReplaceChild("cube_r11_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.8709F, -2.6638F, -0.2319F, 7.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.5F, 14.5F, 4.4998F, 0.0F, 0.0F, 0.2182F));

        PartDefinition cube_r12 = body.addOrReplaceChild("cube_r12", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r12_r1 = cube_r12.addOrReplaceChild("cube_r12_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.8709F, -0.3363F, 0.232F, 7.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.5F, -14.5F, -4.4999F, 0.0F, 0.0F, -0.2182F));

        PartDefinition cube_r10 = body.addOrReplaceChild("cube_r10", CubeListBuilder.create(), PartPose.offsetAndRotation(-10.0F, -18.75F, -1.0F, 3.1416F, -1.5708F, 1.5708F));

        PartDefinition cube_r10_r1 = cube_r10.addOrReplaceChild("cube_r10_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.8709F, -1.7321F, 1.4139F, 7.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.5F, 14.5F, 4.2498F, 0.0F, 0.2182F, 0.0F));

        PartDefinition cube_r9 = body.addOrReplaceChild("cube_r9", CubeListBuilder.create(), PartPose.offsetAndRotation(-10.0F, -18.75F, -1.0F, 0.0F, -1.5708F, 1.5708F));

        PartDefinition bb_main_r6 = cube_r9.addOrReplaceChild("bb_main_r6", CubeListBuilder.create().texOffs(56, 0).addBox(-1.5F, -4.5F, 1.4F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(56, 0).addBox(-1.5F, -4.5F, 0.4F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(56, 0).addBox(-1.5F, -4.5F, -0.6F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(4, 12).addBox(-1.5F, -4.5F, 2.3F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-0.5F, -3.5F, -4.8F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6338F, -9.75F, -6.0545F, 0.6109F, -1.309F, 0.0F));

        PartDefinition cube_r9_r1 = cube_r9.addOrReplaceChild("cube_r9_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.8709F, -1.268F, -1.4138F, 7.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.5F, -14.5F, -4.2499F, 0.0F, -0.2182F, 0.0F));

        PartDefinition head = block.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -24.9167F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        block.render(poseStack, buffer, packedLight, packedOverlay);
    }
}