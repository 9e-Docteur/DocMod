package be.ninedocteur.docmod.client.models;// Made with Blockbench 4.1.3
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


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
public class RedToyotaRotorModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
//	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(DocMod.MOD_ID, "textures/block/hartnell_rotor.png"));
	private final ModelPart Rotor;
	private final ModelPart MainRods;

	public RedToyotaRotorModel(ModelPart root) {
		this.Rotor = root.getChild("Rotor");
		this.MainRods = root.getChild("mid_rods");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Rotor = partdefinition.addOrReplaceChild("Rotor", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition up = Rotor.addOrReplaceChild("up", CubeListBuilder.create().texOffs(62, 62).addBox(5.0F, -12.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(8, 64).addBox(-7.0F, -12.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 19).addBox(-8.0F, -3.0F, -8.0F, 16.0F, 3.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -48.0F, 0.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition cube_r1 = up.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 64).addBox(-7.0F, -12.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -3.1416F, 0.7418F, 3.1416F));

		PartDefinition cube_r2 = up.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 64).addBox(5.0F, -12.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 64).addBox(-7.0F, -12.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(48, 64).addBox(5.0F, -12.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r3 = up.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 64).addBox(-7.0F, -12.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.829F, 0.0F));

		PartDefinition cube_r4 = up.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 64).addBox(-7.0F, -12.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7418F, 0.0F));

		PartDefinition cube_r5 = up.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(62, 62).addBox(-7.0F, -12.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -3.1416F, -0.829F, 3.1416F));

		PartDefinition down = Rotor.addOrReplaceChild("down", CubeListBuilder.create().texOffs(24, 64).addBox(5.0F, -12.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(64, 22).addBox(-7.0F, -12.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 19).addBox(-8.0F, -3.0F, -8.0F, 16.0F, 3.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r6 = down.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(8, 64).addBox(-7.0F, -12.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -3.1416F, 0.829F, 3.1416F));

		PartDefinition cube_r7 = down.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(16, 64).addBox(-7.0F, -12.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(64, 11).addBox(5.0F, -12.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r8 = down.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 64).addBox(-7.0F, -12.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7418F, 0.0F));

		PartDefinition cube_r9 = down.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(64, 0).addBox(-7.0F, -12.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.829F, 0.0F));

		PartDefinition cube_r10 = down.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(62, 62).addBox(-7.0F, -12.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -3.1416F, -0.7418F, 3.1416F));

		PartDefinition mid_rods = partdefinition.addOrReplaceChild("mid_rods", CubeListBuilder.create().texOffs(56, 38).addBox(5.0F, -27.0F, -1.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(24, 38).addBox(-7.0F, -27.0F, -1.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, 0.0F));

		PartDefinition cube_r11 = mid_rods.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 38).addBox(-7.0F, -27.0F, -1.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -3.1416F, 0.829F, 3.1416F));

		PartDefinition cube_r12 = mid_rods.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(8, 38).addBox(-7.0F, -27.0F, -1.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(40, 38).addBox(5.0F, -27.0F, -1.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r13 = mid_rods.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(16, 38).addBox(-7.0F, -27.0F, -1.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7418F, 0.0F));

		PartDefinition cube_r14 = mid_rods.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(32, 38).addBox(-7.0F, -27.0F, -1.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.829F, 0.0F));

		PartDefinition cube_r15 = mid_rods.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(48, 38).addBox(-7.0F, -27.0F, -1.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -3.1416F, -0.7418F, 3.1416F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Rotor.render(poseStack, buffer, packedLight, packedOverlay);
		MainRods.render(poseStack, buffer, ModelUtils.getModelGlow(2F), packedOverlay);
	}


}