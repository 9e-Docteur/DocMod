package be.ninedocteur.docmod.client.models;

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
public class HartnellModel<T extends Entity> extends EntityModel<T> {
	private final ModelPart rotor;
	private final ModelPart rod1;
	private final ModelPart rod2;
	private final ModelPart rod3;
	private final ModelPart rod4;
	private final ModelPart rod4_lightmap;
	private final ModelPart rod3_lightmap;
	private final ModelPart rod2_lightmap;
	private final ModelPart rod1_lightmap;
	private final ModelPart base;
	private final ModelPart glass;
	private final ModelPart glass_top;
	private final ModelPart bb_main;

	public HartnellModel(ModelPart root) {
		this.rotor = root.getChild("rotor");
		this.rod1 = root.getChild("rod1");
		this.rod2 = root.getChild("rod2");
		this.rod3 = root.getChild("rod3");
		this.rod4 = root.getChild("rod4");
		this.rod4_lightmap = root.getChild("rod4_lightmap");
		this.rod3_lightmap = root.getChild("rod3_lightmap");
		this.rod2_lightmap = root.getChild("rod2_lightmap");
		this.rod1_lightmap = root.getChild("rod1_lightmap");
		this.base = root.getChild("base");
		this.glass = root.getChild("glass");
		this.glass_top = root.getChild("glass_top");
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition rotor = partdefinition.addOrReplaceChild("rotor", CubeListBuilder.create().texOffs(0, 65).addBox(-0.5F, -6.5515F, -0.5485F, 1.0F, 18.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.0F, 7.4485F, -2.0485F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 35).addBox(-5.0F, 3.4485F, -5.0485F, 10.0F, 0.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(28, 31).addBox(-5.0F, -5.5515F, -5.0485F, 10.0F, 0.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(15, 21).addBox(-5.0F, -6.5515F, -5.0485F, 10.0F, 0.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(0, 8).addBox(-2.5F, 5.4485F, -0.5485F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 25).addBox(2.0F, 4.9485F, -1.0485F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 21).addBox(-4.0F, 4.9485F, -1.0485F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(61, 23).addBox(-4.0F, -0.7515F, -0.0485F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(61, 21).addBox(-4.0F, -4.1515F, -0.0485F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.5515F, -0.7485F));

				PartDefinition cube_r1 = rotor.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(34, 35).addBox(-1.0F, -1.5F, 0.9F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(8, 32).addBox(5.0F, -1.5F, 0.9F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -1.2476F, -1.1084F, -0.5585F, 0.0F, 0.0F));

				PartDefinition cube_r2 = rotor.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(30, 35).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(33, 31).addBox(5.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -2.8515F, -0.0485F, 0.5585F, 0.0F, 0.0F));

				PartDefinition cube_r3 = rotor.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 35).addBox(-1.0F, -1.5F, 0.9F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(4, 35).addBox(-7.0F, -1.5F, 0.9F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -4.5476F, -1.1084F, -0.5585F, 0.0F, 0.0F));

				PartDefinition cube_r4 = rotor.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(29, 31).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(0, 37).addBox(-7.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.4485F, -0.0485F, 0.5585F, 0.0F, 0.0F));

				PartDefinition rod1 = partdefinition.addOrReplaceChild("rod1", CubeListBuilder.create().texOffs(4, 31).addBox(-0.5F, 5.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(25, 31).addBox(-0.5F, -6.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.5F, 5.5F));

				PartDefinition rod2 = partdefinition.addOrReplaceChild("rod2", CubeListBuilder.create().texOffs(0, 31).addBox(-0.5F, 5.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(7, 30).addBox(-0.5F, -6.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(5.5F, 8.5F, 0.0F));

				PartDefinition rod3 = partdefinition.addOrReplaceChild("rod3", CubeListBuilder.create().texOffs(4, 29).addBox(-0.5F, 5.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 29).addBox(-0.5F, -6.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.55F, 8.5F, 0.0F));

				PartDefinition rod4 = partdefinition.addOrReplaceChild("rod4", CubeListBuilder.create().texOffs(8, 26).addBox(-0.5F, 5.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(12, 8).addBox(-0.5F, -6.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.5F, -5.5F));

				PartDefinition rod4_lightmap = partdefinition.addOrReplaceChild("rod4_lightmap", CubeListBuilder.create().texOffs(68, 0).addBox(-0.5F, -5.5F, -0.5F, 1.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.5F, -5.5F));

				PartDefinition rod3_lightmap = partdefinition.addOrReplaceChild("rod3_lightmap", CubeListBuilder.create().texOffs(68, 0).addBox(-0.5F, -5.5F, -0.5F, 1.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.55F, 8.5F, 0.0F));

				PartDefinition rod2_lightmap = partdefinition.addOrReplaceChild("rod2_lightmap", CubeListBuilder.create().texOffs(68, 0).addBox(-0.5F, -5.5F, -0.5F, 1.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(5.5F, 8.5F, 0.0F));

				PartDefinition rod1_lightmap = partdefinition.addOrReplaceChild("rod1_lightmap", CubeListBuilder.create().texOffs(68, 0).addBox(-0.5F, -5.5F, -0.5F, 1.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.5F, 5.5F));

				PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -2.5F, -8.0F, 16.0F, 5.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.5F, 0.0F));

				PartDefinition glass = partdefinition.addOrReplaceChild("glass", CubeListBuilder.create().texOffs(14, 45).addBox(-3.0F, -19.0F, -7.0F, 6.0F, 19.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 45).addBox(-3.0F, -19.0F, 6.0F, 6.0F, 19.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(64, 0).addBox(-4.0F, -19.0F, -6.0F, 1.0F, 19.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(28, 61).addBox(3.0F, -19.0F, -6.0F, 1.0F, 19.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(44, 61).addBox(-4.0F, -19.0F, 5.0F, 1.0F, 19.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(40, 61).addBox(3.0F, -19.0F, 5.0F, 1.0F, 19.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(62, 51).addBox(-5.0F, -19.0F, -5.0F, 1.0F, 19.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(58, 51).addBox(4.0F, -19.0F, -5.0F, 1.0F, 19.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(48, 61).addBox(-5.0F, -19.0F, 4.0F, 1.0F, 19.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(36, 61).addBox(4.0F, -19.0F, 4.0F, 1.0F, 19.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(62, 31).addBox(-6.0F, -19.0F, -4.0F, 1.0F, 19.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(58, 31).addBox(5.0F, -19.0F, -4.0F, 1.0F, 19.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(52, 61).addBox(-6.0F, -19.0F, 3.0F, 1.0F, 19.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(32, 61).addBox(5.0F, -19.0F, 3.0F, 1.0F, 19.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 19.0F, 0.0F));

				PartDefinition cube_r5 = glass.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(30, 41).addBox(-9.5F, -18.5F, 6.0F, 6.0F, 19.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(44, 41).addBox(-9.5F, -18.5F, -7.0F, 6.0F, 19.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -6.5F, 0.0F, 1.5708F, 0.0F));

				PartDefinition glass_top = partdefinition.addOrReplaceChild("glass_top", CubeListBuilder.create().texOffs(45, 26).addBox(-3.0F, -1.0F, -7.0F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(45, 21).addBox(-3.0F, -1.0F, 3.0F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(12, 2).addBox(-4.0F, -1.0F, -6.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(12, 0).addBox(3.0F, -1.0F, -6.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(8, 10).addBox(-4.0F, -1.0F, 3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 10).addBox(3.0F, -1.0F, 3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(7, 28).addBox(-5.0F, -1.0F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(7, 24).addBox(3.0F, -1.0F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(5, 10).addBox(-5.0F, -1.0F, 3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 2).addBox(4.0F, -1.0F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(8, 14).addBox(-6.0F, -1.0F, -4.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 14).addBox(3.0F, -1.0F, -4.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(1, 14).addBox(-6.0F, -1.0F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(6, 21).addBox(4.0F, -1.0F, 3.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

				PartDefinition cube_r6 = glass_top.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 21).addBox(-9.5F, -0.5F, -6.0F, 6.0F, 1.0F, 13.0F, new CubeDeformation(0.0F))
				.texOffs(0, 6).addBox(-9.5F, -0.5F, -7.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -6.5F, 0.0F, 1.5708F, 0.0F));

				PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(72, 93).addBox(-7.0F, -25.0F, -7.0F, 14.0F, 21.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

				return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		rotor.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
	
	public void renderBase(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		base.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
	
	public void renderRodsLightMap(PoseStack poseStack, VertexConsumer vertexConsumer, int rodsNb, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		switch(rodsNb) {
		case 1:
			rod1_lightmap.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		case 2:
			rod2_lightmap.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		case 3:
			rod3_lightmap.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		case 4:
			rod4_lightmap.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		}
	}
	
	public void renderCircleGlass(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		glass.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		glass_top.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
	
	public void renderRods(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		rod1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rod2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rod3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rod4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
	
	public void renderCubeGlass(PoseStack poseStack, PoseStack rot, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		rot.pushPose();
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rot.popPose();
	}
	
}
