package be.ninedocteur.docmod.client.models;// Made with Blockbench 4.1.3
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


import be.ninedocteur.docmod.utils.DMUtils;
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
public class TardisModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
//	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(DocMod.MOD_ID, "textures/block/hartnell_rotor.png"));
	private final ModelPart rightdoor;
	private final ModelPart leftdoor;
	private final ModelPart lightmap;
	private final ModelPart bb_main;

	public TardisModel(ModelPart root) {
		this.rightdoor = root.getChild("rightdoor");
		this.leftdoor = root.getChild("leftdoor");
		this.lightmap = root.getChild("lightmap");
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition cBL() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition rightdoor = partdefinition.addOrReplaceChild("rightdoor", CubeListBuilder.create().texOffs(144, 0).addBox(-13.5F, -27.0F, 0.0F, 14.0F, 51.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(13.0F, -3.0F, -16.0F));

		PartDefinition cube_r1 = rightdoor.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(112, 213).addBox(-2.0F, -56.0F, -31.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(124, 211).addBox(-2.0F, -63.0F, -32.5F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-27.0F, 26.0F, -15.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition leftdoor = partdefinition.addOrReplaceChild("leftdoor", CubeListBuilder.create().texOffs(0, 167).addBox(-0.5F, -48.0F, 0.0F, 13.0F, 51.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-13.0F, 18.0F, -16.0F));

		PartDefinition cube_r2 = leftdoor.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(124, 211).addBox(-26.0F, -41.0F, -32.5F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 5.0F, -15.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition lightmap = partdefinition.addOrReplaceChild("lightmap", CubeListBuilder.create().texOffs(226, 0).addBox(-26.5F, -27.0F, 0.1F, 14.0F, 51.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(226, 0).addBox(-13.5F, -27.0F, 0.1F, 14.0F, 51.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(182, 1).addBox(-28.9F, -26.0F, 30.5F, 29.0F, 51.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(205, 1).addBox(-13.9F, -26.0F, 30.5F, 17.0F, 51.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(190, 248).addBox(-28.5F, -33.0F, 32.0F, 31.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(13.0F, -3.0F, -16.0F));

		PartDefinition cube_r3 = lightmap.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(190, 248).addBox(-15.5F, -60.0F, 16.0F, 31.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.0F, 27.0F, 16.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r4 = lightmap.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(190, 248).addBox(-15.5F, -60.0F, 16.0F, 31.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(205, 1).addBox(0.0F, -53.0F, 14.9F, 17.0F, 51.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(182, 1).addBox(-15.0F, -53.0F, 14.9F, 29.0F, 51.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 39).addBox(-1.5F, -71.0F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.0F, 27.0F, 16.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r5 = lightmap.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(190, 248).addBox(-15.5F, -60.0F, 16.0F, 31.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.0F, 27.0F, 16.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r6 = lightmap.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(182, 1).addBox(-11.3F, -25.5F, 29.5F, 29.0F, 51.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(205, 1).addBox(3.7F, -25.5F, 29.5F, 17.0F, 51.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4F, -0.5F, 12.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(78, 167).addBox(-17.0F, -59.0F, -17.0F, 4.0F, 56.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(62, 167).addBox(-17.0F, -59.0F, 13.0F, 4.0F, 56.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(46, 167).addBox(13.0F, -59.0F, -17.0F, 4.0F, 56.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(30, 167).addBox(13.0F, -59.0F, 13.0F, 4.0F, 56.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 79).addBox(-16.5F, -62.0F, -16.5F, 33.0F, 9.0F, 33.0F, new CubeDeformation(0.0F))
				.texOffs(99, 79).addBox(-15.0F, -65.0F, -15.0F, 30.0F, 3.0F, 30.0F, new CubeDeformation(0.0F))
				.texOffs(0, 9).addBox(-2.5F, -71.0F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 9).addBox(1.5F, -70.0F, -2.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 9).addBox(-2.5F, -70.0F, -2.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 9).addBox(-2.5F, -70.0F, 1.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 9).addBox(1.5F, -70.0F, 1.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-15.5F, -60.0F, 16.0F, 31.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 9).addBox(-3.5F, -66.0F, -3.5F, 7.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(0, 79).addBox(-18.5F, -3.0F, -18.5F, 37.0F, 3.0F, 37.0F, new CubeDeformation(0.0F))
				.texOffs(120, 116).addBox(-15.0F, -53.0F, 15.0F, 29.0F, 51.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(120, 116).addBox(-15.0F, -53.0F, 15.0F, 29.0F, 51.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r7 = bb_main.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 0).addBox(-15.5F, -60.0F, 16.0F, 31.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(120, 116).addBox(-15.0F, -53.0F, -12.0F, 29.0F, 51.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r8 = bb_main.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(120, 116).addBox(-15.0F, -52.0F, -15.0F, 29.0F, 51.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(120, 116).addBox(-15.0F, -52.0F, 15.0F, 29.0F, 51.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-15.5F, -59.0F, 16.0F, 31.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 38).addBox(-1.5F, -71.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r9 = bb_main.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(120, 116).addBox(-15.0F, -52.0F, -15.0F, 29.0F, 51.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-15.5F, -59.0F, 16.0F, 31.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(120, 116).addBox(-15.0F, -52.0F, 15.0F, 29.0F, 51.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, -1.5708F, 0.0F));
		return LayerDefinition.create(meshdefinition, 256, 256);

	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		rightdoor.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		leftdoor.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		bb_main.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		lightmap.render(poseStack, buffer, ModelUtils.getModelGlow(5F), packedOverlay);
	}


}