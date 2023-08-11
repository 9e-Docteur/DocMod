package be.ninedocteur.docmod.client.models;// Made with Blockbench 4.1.3
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
public class ChairModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
//	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(DocMod.MOD_ID, "textures/block/hartnell_rotor.png"));
	private final ModelPart chair;
	public final ModelPart movingpart;

	public ChairModel(ModelPart root) {
		this.chair = root.getChild("chair");
		this.movingpart = root.getChild("movingpart");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition chair = partdefinition.addOrReplaceChild("chair", CubeListBuilder.create().texOffs(8, 24).addBox(-1.0F, 12.5F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 24).addBox(6.0F, 12.5F, 4.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 10).addBox(13.0F, 12.5F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(6, 8).addBox(6.0F, 12.5F, -10.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(18, 17).addBox(6.5F, 11.5F, -10.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.0F, 3.5F, -3.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, 9.5F, 2.0F));

		PartDefinition cube_r1 = chair.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 16).addBox(-0.5F, -0.5F, -8.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 12.0F, -2.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition movingpart = partdefinition.addOrReplaceChild("movingpart", CubeListBuilder.create().texOffs(0, 0).addBox(1.0F, 1.5F, -10.0F, 12.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
				.texOffs(0, 34).addBox(1.0F, -15.5F, 4.0F, 12.0F, 17.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(36, 16).addBox(13.0F, -6.0F, -4.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(28, 34).addBox(-1.0F, -6.0F, -4.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, 9.5F, 2.0F));

		PartDefinition cube_r2 = movingpart.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, -4.0F, -4.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(18, 16).addBox(13.0F, -4.0F, -4.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		chair.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		movingpart.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}


}