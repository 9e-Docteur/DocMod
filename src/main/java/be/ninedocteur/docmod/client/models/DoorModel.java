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
public class DoorModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
//	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(DocMod.MOD_ID, "textures/block/hartnell_rotor.png"));
	private final ModelPart door;

	public DoorModel(ModelPart root) {
		this.door = root.getChild("door");
	}

	public static LayerDefinition cBL() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition door = partdefinition.addOrReplaceChild("door", CubeListBuilder.create().texOffs(78, 167).addBox(-17.0F, -59.0F, -17.0F, 4.0F, 56.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(46, 167).addBox(13.0F, -59.0F, -17.0F, 4.0F, 56.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(144, 0).addBox(-0.5F, -54.0F, -16.0F, 14.0F, 51.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 167).addBox(-13.5F, -54.0F, -16.0F, 13.0F, 51.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 27.0F, 24.0F));

		PartDefinition cube_r1 = door.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-15.5F, -60.0F, 16.0F, 31.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);

	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		door.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}


}