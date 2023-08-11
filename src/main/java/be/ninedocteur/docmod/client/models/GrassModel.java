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
public class GrassModel<T extends Entity> extends EntityModel<T> {
	private final ModelPart g1;
	private final ModelPart g2;
	private final ModelPart g3;
	private final ModelPart g4;
	private final ModelPart g5;

	public GrassModel(ModelPart root) {
		this.g1 = root.getChild("g1");
		this.g2 = root.getChild("g2");
		this.g3 = root.getChild("g3");
		this.g4 = root.getChild("g4");
		this.g5 = root.getChild("g5");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition g1 = partdefinition.addOrReplaceChild("g1", CubeListBuilder.create().texOffs(1, 1).addBox(2.8F, -1.0F, 3.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(1.8F, -1.0F, -4.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(0.8F, -1.0F, 0.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(0.8F, -1.0F, 2.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(0.8F, -1.0F, 6.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(-0.2F, -1.0F, 0.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(-0.2F, -1.0F, -2.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(0.8F, -1.0F, -6.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(1.8F, -1.0F, 1.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(-0.2F, -1.0F, 2.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(-0.2F, -1.0F, -2.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(-0.2F, -1.0F, -4.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(0.8F, -1.0F, -8.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(1.8F, -1.0F, -1.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(-0.2F, -1.0F, 6.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(-0.2F, -1.0F, 2.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(-0.2F, -1.0F, 0.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(0.8F, -1.0F, -4.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(1.8F, -1.0F, 3.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(6.8F, -1.0F, -6.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(5.8F, -1.0F, -2.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(5.8F, -1.0F, 0.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(5.8F, -1.0F, 4.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(7.8F, -1.0F, 1.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(4.8F, -1.0F, 4.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(4.8F, -1.0F, 0.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(4.8F, -1.0F, -2.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(5.8F, -1.0F, -6.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(6.8F, -1.0F, 1.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(4.8F, -1.0F, 2.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(4.8F, -1.0F, -2.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(4.8F, -1.0F, -4.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(5.8F, -1.0F, -8.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(6.8F, -1.0F, -1.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(4.8F, -1.0F, 6.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(4.8F, -1.0F, 2.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(4.8F, -1.0F, 0.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(5.8F, -1.0F, -4.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(6.8F, -1.0F, 3.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(5.8F, -1.0F, 6.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(5.8F, -1.0F, 2.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(5.8F, -1.0F, 0.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(6.8F, -1.0F, -4.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(7.8F, -1.0F, 3.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(-1.2F, -1.0F, 3.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(-2.2F, -1.0F, -4.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(-3.2F, -1.0F, 0.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(-3.2F, -1.0F, 2.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(-3.2F, -1.0F, 6.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(-1.2F, -1.0F, -1.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(-2.2F, -1.0F, -8.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(-3.2F, -1.0F, -4.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(-3.2F, -1.0F, -2.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(-3.2F, -1.0F, 2.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(-1.2F, -1.0F, 1.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(-2.2F, -1.0F, -6.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(-3.2F, -1.0F, -2.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(-3.2F, -1.0F, 0.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(-2.2F, -1.0F, 6.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(-2.2F, -1.0F, 2.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(-2.2F, -1.0F, 0.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(-1.2F, -1.0F, -4.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r1 = g1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(1, 1).addBox(7.8F, -1.0F, 3.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(6.8F, -1.0F, -4.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(5.8F, -1.0F, 0.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(5.8F, -1.0F, 2.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(5.8F, -1.0F, 6.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(4.8F, -1.0F, 0.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(4.8F, -1.0F, -2.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(5.8F, -1.0F, -6.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(6.8F, -1.0F, 1.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(4.8F, -1.0F, 2.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(4.8F, -1.0F, -2.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(4.8F, -1.0F, -4.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(5.8F, -1.0F, -8.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(6.8F, -1.0F, -1.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(4.8F, -1.0F, 6.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(4.8F, -1.0F, 2.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(4.8F, -1.0F, 0.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(5.8F, -1.0F, -4.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(6.8F, -1.0F, 3.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 2.9671F, 0.0F));

		PartDefinition cube_r2 = g1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(1, 1).addBox(4.8F, -1.0F, 3.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(3.8F, -1.0F, -4.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(2.8F, -1.0F, 0.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(2.8F, -1.0F, 2.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(4.8F, -1.0F, 6.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(2.8F, -1.0F, 6.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(1.8F, -1.0F, 0.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(1.8F, -1.0F, -2.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(2.8F, -1.0F, -6.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(3.8F, -1.0F, 1.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(1.8F, -1.0F, 2.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(1.8F, -1.0F, -2.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(1.8F, -1.0F, -4.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(2.8F, -1.0F, -8.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(3.8F, -1.0F, -1.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(1.8F, -1.0F, 6.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(1.8F, -1.0F, 2.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(1.8F, -1.0F, 0.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(2.8F, -1.0F, -4.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(3.8F, -1.0F, 3.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.2182F, 0.0F));

		PartDefinition g2 = partdefinition.addOrReplaceChild("g2", CubeListBuilder.create().texOffs(0, 0).addBox(2.8F, -2.0F, 3.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -2.0F, -4.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -2.0F, 0.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -2.0F, 2.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -2.0F, 6.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -2.0F, 0.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -2.0F, -2.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -2.0F, -6.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -2.0F, 1.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -2.0F, 2.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -2.0F, -2.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -2.0F, -4.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -2.0F, -8.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -2.0F, -1.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -2.0F, 6.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -2.0F, 2.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -2.0F, 0.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -2.0F, -4.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -2.0F, 3.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -2.0F, -6.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -2.0F, -2.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -2.0F, 0.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -2.0F, 4.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(7.8F, -2.0F, 1.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -2.0F, 4.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -2.0F, 0.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -2.0F, -2.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -2.0F, -6.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -2.0F, 1.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -2.0F, 2.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -2.0F, -2.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -2.0F, -4.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -2.0F, -8.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -2.0F, -1.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -2.0F, 6.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -2.0F, 2.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -2.0F, 0.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -2.0F, -4.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -2.0F, 3.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -2.0F, 6.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -2.0F, 2.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -2.0F, 0.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -2.0F, -4.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(7.8F, -2.0F, 3.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -2.0F, 3.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -2.0F, -4.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -2.0F, 0.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -2.0F, 2.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -2.0F, 6.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -2.0F, -1.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -2.0F, -8.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -2.0F, -4.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -2.0F, -2.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -2.0F, 2.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -2.0F, 1.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -2.0F, -6.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -2.0F, -2.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -2.0F, 0.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -2.0F, 6.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -2.0F, 2.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -2.0F, 0.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -2.0F, -4.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r3 = g2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).addBox(7.8F, -2.0F, 3.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -2.0F, -4.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -2.0F, 0.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -2.0F, 2.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -2.0F, 6.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -2.0F, 0.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -2.0F, -2.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -2.0F, -6.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -2.0F, 1.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -2.0F, 2.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -2.0F, -2.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -2.0F, -4.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -2.0F, -8.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -2.0F, -1.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -2.0F, 6.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -2.0F, 2.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -2.0F, 0.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -2.0F, -4.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -2.0F, 3.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 2.9671F, 0.0F));

		PartDefinition cube_r4 = g2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 0).addBox(4.8F, -2.0F, 3.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(3.8F, -2.0F, -4.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(2.8F, -2.0F, 0.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(2.8F, -2.0F, 2.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -2.0F, 6.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(2.8F, -2.0F, 6.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -2.0F, 0.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -2.0F, -2.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(2.8F, -2.0F, -6.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(3.8F, -2.0F, 1.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -2.0F, 2.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -2.0F, -2.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -2.0F, -4.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(2.8F, -2.0F, -8.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(3.8F, -2.0F, -1.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -2.0F, 6.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -2.0F, 2.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -2.0F, 0.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(2.8F, -2.0F, -4.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(3.8F, -2.0F, 3.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.2182F, 0.0F));

		PartDefinition bone = g2.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r5 = bone.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 0).addBox(-3.2F, -1.0F, 4.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -1.0F, 3.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -1.0F, 4.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -1.0F, 5.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -1.0F, 5.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -3.1416F, 0.1745F, 3.1416F));

		PartDefinition cube_r6 = bone.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 0).addBox(-5.2F, -1.0F, 4.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -1.0F, 3.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -1.0F, 4.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -1.0F, 5.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -1.0F, 5.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.48F, 0.0F));

		PartDefinition cube_r7 = bone.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 0).addBox(-3.2F, -1.0F, 4.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -1.0F, 5.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -1.0F, 5.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -1.0F, 4.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -1.0F, 3.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3054F, 0.0F));

		PartDefinition cube_r8 = bone.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 0).addBox(-0.2F, -1.0F, 3.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -1.0F, 4.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -1.0F, 5.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -1.0F, 5.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -1.0F, 4.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 1.0F, 0.0F, 0.3054F, 0.0F));

		PartDefinition cube_r9 = bone.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 0).addBox(-5.2F, -1.0F, 4.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -1.0F, 3.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -1.0F, 4.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -1.0F, 5.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -1.0F, 5.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 1.0F, 0.0F, 0.48F, 0.0F));

		PartDefinition cube_r10 = bone.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 0).addBox(-3.2F, -1.0F, 4.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -1.0F, 3.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -1.0F, 4.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -1.0F, 5.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -1.0F, 5.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 1.0F, -3.1416F, 0.1745F, 3.1416F));

		PartDefinition cube_r11 = bone.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 0).addBox(-0.2F, -1.0F, 3.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -1.0F, 4.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -1.0F, 5.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -1.0F, 5.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -1.0F, 4.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r12 = bone.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 0).addBox(-5.2F, -1.0F, 4.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -1.0F, 3.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -1.0F, 4.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -1.0F, 5.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -1.0F, 5.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.0F, -0.1745F, 0.0F));

		PartDefinition cube_r13 = bone.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 0).addBox(-3.2F, -1.0F, 4.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -1.0F, 3.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -1.0F, 4.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -1.0F, 5.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -1.0F, 5.0F, 0.2F, 1.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, -3.1416F, 0.829F, 3.1416F));

		PartDefinition g3 = partdefinition.addOrReplaceChild("g3", CubeListBuilder.create().texOffs(0, 0).addBox(2.8F, -3.0F, 3.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -3.0F, -4.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -3.0F, 0.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -3.0F, 2.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -3.0F, 6.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -3.0F, 0.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -3.0F, -2.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -3.0F, -6.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -3.0F, 1.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -3.0F, 2.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -3.0F, -2.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -3.0F, -4.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -3.0F, -8.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -3.0F, -1.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -3.0F, 6.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -3.0F, 2.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -3.0F, 0.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -3.0F, -4.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -3.0F, 3.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -3.0F, -6.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -3.0F, -2.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -3.0F, 0.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -3.0F, 4.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(7.8F, -3.0F, 1.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -3.0F, 4.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -3.0F, 0.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -3.0F, -2.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -3.0F, -6.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -3.0F, 1.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -3.0F, 2.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -3.0F, -2.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -3.0F, -4.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -3.0F, -8.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -3.0F, -1.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -3.0F, 6.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -3.0F, 2.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -3.0F, 0.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -3.0F, -4.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -3.0F, 3.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -3.0F, 6.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -3.0F, 2.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -3.0F, 0.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -3.0F, -4.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(7.8F, -3.0F, 3.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -3.0F, 3.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -3.0F, -4.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -3.0F, 0.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -3.0F, 2.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -3.0F, 6.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -3.0F, -1.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -3.0F, -8.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -3.0F, -4.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -3.0F, -2.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -3.0F, 2.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -3.0F, 1.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -3.0F, -6.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -3.0F, -2.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -3.0F, 0.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -3.0F, 6.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -3.0F, 2.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -3.0F, 0.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -3.0F, -4.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r14 = g3.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(0, 0).addBox(7.8F, -3.0F, 3.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -3.0F, -4.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -3.0F, 0.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -3.0F, 2.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -3.0F, 6.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -3.0F, 0.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -3.0F, -2.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -3.0F, -6.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -3.0F, 1.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -3.0F, 2.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -3.0F, -2.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -3.0F, -4.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -3.0F, -8.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -3.0F, -1.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -3.0F, 6.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -3.0F, 2.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -3.0F, 0.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -3.0F, -4.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -3.0F, 3.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 2.9671F, 0.0F));

		PartDefinition cube_r15 = g3.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(0, 0).addBox(4.8F, -3.0F, 3.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(3.8F, -3.0F, -4.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(2.8F, -3.0F, 0.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(2.8F, -3.0F, 2.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -3.0F, 6.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(2.8F, -3.0F, 6.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -3.0F, 0.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -3.0F, -2.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(2.8F, -3.0F, -6.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(3.8F, -3.0F, 1.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -3.0F, 2.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -3.0F, -2.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -3.0F, -4.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(2.8F, -3.0F, -8.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(3.8F, -3.0F, -1.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -3.0F, 6.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -3.0F, 2.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -3.0F, 0.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(2.8F, -3.0F, -4.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(3.8F, -3.0F, 3.0F, 0.2F, 3.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.2182F, 0.0F));

		PartDefinition bone2 = g3.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r16 = bone2.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 0).addBox(-3.2F, -2.0F, 4.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -2.0F, 3.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -2.0F, 4.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -2.0F, 5.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -2.0F, 5.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -3.1416F, 0.1745F, 3.1416F));

		PartDefinition cube_r17 = bone2.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 0).addBox(-5.2F, -2.0F, 4.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -2.0F, 3.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -2.0F, 4.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -2.0F, 5.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -2.0F, 5.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.48F, 0.0F));

		PartDefinition cube_r18 = bone2.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(0, 0).addBox(-3.2F, -2.0F, 4.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -2.0F, 5.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -2.0F, 5.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -2.0F, 4.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -2.0F, 3.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3054F, 0.0F));

		PartDefinition cube_r19 = bone2.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(0, 0).addBox(-0.2F, -2.0F, 3.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -2.0F, 4.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -2.0F, 5.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -2.0F, 5.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -2.0F, 4.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 1.0F, 0.0F, 0.3054F, 0.0F));

		PartDefinition cube_r20 = bone2.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(0, 0).addBox(-5.2F, -2.0F, 4.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -2.0F, 3.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -2.0F, 4.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -2.0F, 5.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -2.0F, 5.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 1.0F, 0.0F, 0.48F, 0.0F));

		PartDefinition cube_r21 = bone2.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(0, 0).addBox(-3.2F, -2.0F, 4.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -2.0F, 3.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -2.0F, 4.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -2.0F, 5.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -2.0F, 5.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 1.0F, -3.1416F, 0.1745F, 3.1416F));

		PartDefinition cube_r22 = bone2.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(0, 0).addBox(-0.2F, -2.0F, 3.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -2.0F, 4.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -2.0F, 5.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -2.0F, 5.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -2.0F, 4.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r23 = bone2.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(0, 0).addBox(-5.2F, -2.0F, 4.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -2.0F, 3.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -2.0F, 4.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -2.0F, 5.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -2.0F, 5.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.0F, -0.1745F, 0.0F));

		PartDefinition cube_r24 = bone2.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(0, 0).addBox(-3.2F, -2.0F, 4.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -2.0F, 3.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -2.0F, 4.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -2.0F, 5.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -2.0F, 5.0F, 0.2F, 2.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, -3.1416F, 0.829F, 3.1416F));

		PartDefinition g4 = partdefinition.addOrReplaceChild("g4", CubeListBuilder.create().texOffs(0, 0).addBox(2.8F, -5.0F, 3.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -5.0F, -4.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -5.0F, 0.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -5.0F, 2.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -5.0F, 6.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -5.0F, 0.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -5.0F, -2.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -5.0F, -6.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -5.0F, 1.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -5.0F, 2.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -5.0F, -2.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -5.0F, -4.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -5.0F, -8.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -5.0F, -1.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -5.0F, 6.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -5.0F, 2.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -5.0F, 0.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -5.0F, -4.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -5.0F, 3.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -5.0F, -6.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -5.0F, -2.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -5.0F, 0.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -5.0F, 4.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(7.8F, -5.0F, 1.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -5.0F, 4.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -5.0F, 0.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -5.0F, -2.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -5.0F, -6.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -5.0F, 1.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -5.0F, 2.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -5.0F, -2.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -5.0F, -4.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -5.0F, -8.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -5.0F, -1.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -5.0F, 6.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -5.0F, 2.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -5.0F, 0.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -5.0F, -4.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -5.0F, 3.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -5.0F, 6.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -5.0F, 2.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -5.0F, 0.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -5.0F, -4.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(7.8F, -5.0F, 3.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -5.0F, 3.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -5.0F, -4.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -5.0F, 0.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -5.0F, 2.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -5.0F, 6.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -5.0F, -1.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -5.0F, -8.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -5.0F, -4.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -5.0F, -2.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -5.0F, 2.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -5.0F, 1.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -5.0F, -6.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -5.0F, -2.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -5.0F, 0.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -5.0F, 6.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -5.0F, 2.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -5.0F, 0.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -5.0F, -4.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r25 = g4.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(0, 0).addBox(7.8F, -5.0F, 3.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -5.0F, -4.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -5.0F, 0.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -5.0F, 2.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -5.0F, 6.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -5.0F, 0.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -5.0F, -2.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -5.0F, -6.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -5.0F, 1.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -5.0F, 2.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -5.0F, -2.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -5.0F, -4.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -5.0F, -8.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -5.0F, -1.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -5.0F, 6.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -5.0F, 2.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -5.0F, 0.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -5.0F, -4.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -5.0F, 3.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 2.9671F, 0.0F));

		PartDefinition cube_r26 = g4.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(0, 0).addBox(4.8F, -5.0F, 3.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(3.8F, -5.0F, -4.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(2.8F, -5.0F, 0.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(2.8F, -5.0F, 2.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -5.0F, 6.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(2.8F, -5.0F, 6.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -5.0F, 0.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -5.0F, -2.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(2.8F, -5.0F, -6.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(3.8F, -5.0F, 1.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -5.0F, 2.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -5.0F, -2.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -5.0F, -4.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(2.8F, -5.0F, -8.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(3.8F, -5.0F, -1.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -5.0F, 6.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -5.0F, 2.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -5.0F, 0.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(2.8F, -5.0F, -4.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(3.8F, -5.0F, 3.0F, 0.2F, 5.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.2182F, 0.0F));

		PartDefinition bone3 = g4.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r27 = bone3.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(0, 0).addBox(-3.2F, -4.0F, 4.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -4.0F, 3.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -4.0F, 4.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -4.0F, 5.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -4.0F, 5.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -3.1416F, 0.1745F, 3.1416F));

		PartDefinition cube_r28 = bone3.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(0, 0).addBox(-5.2F, -4.0F, 4.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -4.0F, 3.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -4.0F, 4.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -4.0F, 5.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -4.0F, 5.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.48F, 0.0F));

		PartDefinition cube_r29 = bone3.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(0, 0).addBox(-3.2F, -4.0F, 4.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -4.0F, 5.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -4.0F, 5.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -4.0F, 4.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -4.0F, 3.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3054F, 0.0F));

		PartDefinition cube_r30 = bone3.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(0, 0).addBox(-0.2F, -4.0F, 3.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -4.0F, 4.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -4.0F, 5.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -4.0F, 5.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -4.0F, 4.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 1.0F, 0.0F, 0.3054F, 0.0F));

		PartDefinition cube_r31 = bone3.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(0, 0).addBox(-5.2F, -4.0F, 4.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -4.0F, 3.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -4.0F, 4.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -4.0F, 5.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -4.0F, 5.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 1.0F, 0.0F, 0.48F, 0.0F));

		PartDefinition cube_r32 = bone3.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(0, 0).addBox(-3.2F, -4.0F, 4.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -4.0F, 3.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -4.0F, 4.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -4.0F, 5.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -4.0F, 5.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 1.0F, -3.1416F, 0.1745F, 3.1416F));

		PartDefinition cube_r33 = bone3.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(0, 0).addBox(-0.2F, -4.0F, 3.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -4.0F, 4.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -4.0F, 5.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -4.0F, 5.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -4.0F, 4.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r34 = bone3.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(0, 0).addBox(-5.2F, -4.0F, 4.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -4.0F, 3.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -4.0F, 4.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -4.0F, 5.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -4.0F, 5.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.0F, -0.1745F, 0.0F));

		PartDefinition cube_r35 = bone3.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(0, 0).addBox(-3.2F, -4.0F, 4.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -4.0F, 3.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -4.0F, 4.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -4.0F, 5.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -4.0F, 5.0F, 0.2F, 4.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, -3.1416F, 0.829F, 3.1416F));

		PartDefinition g5 = partdefinition.addOrReplaceChild("g5", CubeListBuilder.create().texOffs(0, 0).addBox(2.8F, -7.0F, 3.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -7.0F, -4.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -7.0F, 0.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -7.0F, 2.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -7.0F, 6.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -7.0F, 0.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -7.0F, -2.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -7.0F, -6.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -7.0F, 1.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -7.0F, 2.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -7.0F, -2.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -7.0F, -4.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -7.0F, -8.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -7.0F, -1.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -7.0F, 6.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -7.0F, 2.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -7.0F, 0.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -7.0F, -4.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -7.0F, 3.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -7.0F, -6.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -7.0F, -2.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -7.0F, 0.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -7.0F, 4.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(7.8F, -7.0F, 1.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -7.0F, 4.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -7.0F, 0.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -7.0F, -2.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -7.0F, -6.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -7.0F, 1.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -7.0F, 2.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -7.0F, -2.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -7.0F, -4.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -7.0F, -8.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -7.0F, -1.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -7.0F, 6.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -7.0F, 2.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -7.0F, 0.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -7.0F, -4.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -7.0F, 3.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -7.0F, 6.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -7.0F, 2.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -7.0F, 0.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -7.0F, -4.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(7.8F, -7.0F, 3.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -7.0F, 3.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -7.0F, -4.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -7.0F, 0.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -7.0F, 2.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -7.0F, 6.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -7.0F, -1.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -7.0F, -8.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -7.0F, -4.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -7.0F, -2.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -7.0F, 2.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -7.0F, 1.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -7.0F, -6.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -7.0F, -2.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -7.0F, 0.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -7.0F, 6.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -7.0F, 2.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -7.0F, 0.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -7.0F, -4.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r36 = g5.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(0, 0).addBox(7.8F, -7.0F, 3.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -7.0F, -4.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -7.0F, 0.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -7.0F, 2.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -7.0F, 6.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -7.0F, 0.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -7.0F, -2.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -7.0F, -6.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -7.0F, 1.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -7.0F, 2.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -7.0F, -2.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -7.0F, -4.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -7.0F, -8.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -7.0F, -1.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -7.0F, 6.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -7.0F, 2.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -7.0F, 0.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(5.8F, -7.0F, -4.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.8F, -7.0F, 3.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 2.9671F, 0.0F));

		PartDefinition cube_r37 = g5.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(0, 0).addBox(4.8F, -7.0F, 3.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(3.8F, -7.0F, -4.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(2.8F, -7.0F, 0.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(2.8F, -7.0F, 2.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.8F, -7.0F, 6.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(2.8F, -7.0F, 6.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -7.0F, 0.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -7.0F, -2.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(2.8F, -7.0F, -6.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(3.8F, -7.0F, 1.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -7.0F, 2.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -7.0F, -2.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -7.0F, -4.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(2.8F, -7.0F, -8.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(3.8F, -7.0F, -1.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -7.0F, 6.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -7.0F, 2.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -7.0F, 0.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(2.8F, -7.0F, -4.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(3.8F, -7.0F, 3.0F, 0.2F, 7.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.2182F, 0.0F));

		PartDefinition bone4 = g5.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r38 = bone4.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(0, 0).addBox(-3.2F, -6.0F, 4.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -6.0F, 3.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -6.0F, 4.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -6.0F, 5.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -6.0F, 5.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -3.1416F, 0.1745F, 3.1416F));

		PartDefinition cube_r39 = bone4.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(0, 0).addBox(-5.2F, -6.0F, 4.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -6.0F, 3.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -6.0F, 4.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -6.0F, 5.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -6.0F, 5.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.48F, 0.0F));

		PartDefinition cube_r40 = bone4.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(0, 0).addBox(-3.2F, -6.0F, 4.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -6.0F, 5.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -6.0F, 5.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -6.0F, 4.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -6.0F, 3.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3054F, 0.0F));

		PartDefinition cube_r41 = bone4.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(0, 0).addBox(-0.2F, -6.0F, 3.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -6.0F, 4.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -6.0F, 5.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -6.0F, 5.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -6.0F, 4.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 1.0F, 0.0F, 0.3054F, 0.0F));

		PartDefinition cube_r42 = bone4.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(0, 0).addBox(-5.2F, -6.0F, 4.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -6.0F, 3.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -6.0F, 4.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -6.0F, 5.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -6.0F, 5.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 1.0F, 0.0F, 0.48F, 0.0F));

		PartDefinition cube_r43 = bone4.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(0, 0).addBox(-3.2F, -6.0F, 4.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -6.0F, 3.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -6.0F, 4.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -6.0F, 5.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -6.0F, 5.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 1.0F, -3.1416F, 0.1745F, 3.1416F));

		PartDefinition cube_r44 = bone4.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(0, 0).addBox(-0.2F, -6.0F, 3.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -6.0F, 4.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -6.0F, 5.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -6.0F, 5.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -6.0F, 4.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r45 = bone4.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(0, 0).addBox(-5.2F, -6.0F, 4.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.2F, -6.0F, 3.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -6.0F, 4.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -6.0F, 5.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.2F, -6.0F, 5.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.0F, -0.1745F, 0.0F));

		PartDefinition cube_r46 = bone4.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(0, 0).addBox(-3.2F, -6.0F, 4.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.2F, -6.0F, 3.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8F, -6.0F, 4.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8F, -6.0F, 5.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.2F, -6.0F, 5.0F, 0.2F, 6.0F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, -3.1416F, 0.829F, 3.1416F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
	}
	
	public void renderStage1(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		g1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
	
	public void renderStage2(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		g2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
	
	public void renderStage3(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		g3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
	
	public void renderStage4(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		g4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
	
	public void renderStage5(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		g5.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
