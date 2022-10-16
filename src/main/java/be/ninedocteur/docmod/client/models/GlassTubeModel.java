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
public class GlassTubeModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
//	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(DocMod.MOD_ID, "textures/block/hartnell_rotor.png"));
	private final ModelPart glass;
	private final ModelPart rods;


	public GlassTubeModel(ModelPart root) {
		this.glass = root.getChild("glass");
		this.rods = root.getChild("rods");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition glass = partdefinition.addOrReplaceChild("glass", CubeListBuilder.create().texOffs(0, 0).addBox(-24.0F, -128.0F, -24.0F, 48.0F, 128.0F, 48.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition rods = partdefinition.addOrReplaceChild("rods", CubeListBuilder.create().texOffs(200, 114).addBox(-21.0F, -128.0F, -20.0F, 14.0F, 128.0F, 14.0F, new CubeDeformation(0.0F))
				.texOffs(200, 114).addBox(-21.0F, -128.0F, 6.0F, 14.0F, 128.0F, 14.0F, new CubeDeformation(0.0F))
				.texOffs(200, 114).addBox(6.0F, -128.0F, -20.0F, 14.0F, 128.0F, 14.0F, new CubeDeformation(0.0F))
				.texOffs(200, 114).addBox(6.0F, -128.0F, 6.0F, 14.0F, 128.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		glass.render(poseStack, buffer, packedLight, packedOverlay);
		rods.render(poseStack, buffer, ModelUtils.getModelGlow(2F), packedOverlay);
	}


}