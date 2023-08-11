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
public class LightSensorModel<T extends Entity> extends EntityModel<T> {
		private final ModelPart light;
		private final ModelPart bone;

		public LightSensorModel(ModelPart root) {
			this.light = root.getChild("light");
			this.bone = root.getChild("bone");
		}

		public static LayerDefinition createBodyLayer() {
			MeshDefinition meshdefinition = new MeshDefinition();
			PartDefinition partdefinition = meshdefinition.getRoot();

			PartDefinition light = partdefinition.addOrReplaceChild("light", CubeListBuilder.create().texOffs(8, 5).addBox(-1.9F, -6.9F, -1.1F, 3.8F, 2.8F, 0.1F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.0F, 7.0F));

			PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -7.0F, -1.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(0, 5).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(7, 8).addBox(-0.3F, -4.0F, 0.5F, 0.5F, 2.0F, 0.5F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.0F, 7.0F));

			return LayerDefinition.create(meshdefinition, 16, 16);
		}

		@Override
		public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

		}

		@Override
		public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
			bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		}
		
		public void renderLightMap(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
			light.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		}
	
}
