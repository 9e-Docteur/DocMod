package be.ninedocteur.docmod.client.models;// Made with Blockbench 4.1.3
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


import be.ninedocteur.docmod.common.block.ComputerBlock;
import be.ninedocteur.docmod.common.computer.BaseOS;
import be.ninedocteur.docmod.common.computer.OSRegistry;
import be.ninedocteur.docmod.common.init.DMItems;
import be.ninedocteur.docmod.common.tileentity.ComputerTileEntity;
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
public class ComputerModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
//	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(DocMod.MOD_ID, "textures/block/hartnell_rotor.png"));
	private final ModelPart fan;
	private final ModelPart fan2;
	private final ModelPart base;
	private final ModelPart glass;
	private final ModelPart CPU;
	private final ModelPart motherboard;
	private final ModelPart GPU;
	private final ModelPart front_lightmap;
	private final ModelPart RAM;
	private final ModelPart motherboard_lightmap;
	private final ModelPart Disk_Station;
	private final ModelPart Disks;
	private final ModelPart case_lightmap;
	private final ModelPart Ventirad;
	private final ModelPart Alimentation;

	private ComputerTileEntity computerTileEntity;

	public ComputerModel(ModelPart root, ComputerTileEntity computerTileEntity) {
		this.fan = root.getChild("fan");
		this.fan2 = root.getChild("fan2");
		this.base = root.getChild("base");
		this.glass = root.getChild("glass");
		this.CPU = root.getChild("CPU");
		this.motherboard = root.getChild("motherboard");
		this.GPU = root.getChild("GPU");
		this.front_lightmap = root.getChild("front_lightmap");
		this.RAM = root.getChild("RAM");
		this.motherboard_lightmap = root.getChild("motherboard_lightmap");
		this.Disk_Station = root.getChild("Disk_Station");
		this.Disks = root.getChild("Disks");
		this.case_lightmap = root.getChild("case_lightmap");
		this.Ventirad = root.getChild("Ventirad");
		this.Alimentation = root.getChild("Alimentation");
		this.computerTileEntity = computerTileEntity;
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition fan = partdefinition.addOrReplaceChild("fan", CubeListBuilder.create().texOffs(59, 53).addBox(-3.0F, -0.5F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(59, 55).addBox(-3.0F, -0.5F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(59, 51).addBox(-2.0F, -0.5F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(59, 57).addBox(-2.0F, -0.5F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(59, 49).addBox(-1.0F, -0.5F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(59, 59).addBox(-2.0F, -0.5F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(16, 3).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 17.5F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition cube_r1 = fan.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(59, 47).addBox(-3.5F, -0.5F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(28, 59).addBox(-3.5F, -0.5F, 3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(32, 59).addBox(-4.5F, -0.5F, 3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(59, 37).addBox(-4.5F, -0.5F, 2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(24, 59).addBox(-2.5F, -0.5F, 2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(59, 45).addBox(-3.5F, -0.5F, 2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 0.0F, 1.5F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r2 = fan.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(58, 34).addBox(-3.0F, -0.5F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(58, 43).addBox(-3.0F, -0.5F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 59).addBox(-2.0F, -0.5F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(59, 1).addBox(-2.0F, -0.5F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(59, 3).addBox(-2.0F, -0.5F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(4, 59).addBox(-1.0F, -0.5F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r3 = fan.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(59, 5).addBox(-1.0F, -0.5F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(8, 59).addBox(-2.0F, -0.5F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(59, 11).addBox(-3.0F, -0.5F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(12, 59).addBox(-3.0F, -0.5F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(16, 59).addBox(-2.0F, -0.5F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(20, 59).addBox(-2.0F, -0.5F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition fan2 = partdefinition.addOrReplaceChild("fan2", CubeListBuilder.create().texOffs(0, 57).addBox(-3.0F, -0.5F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(56, 36).addBox(-3.0F, -0.5F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(56, 6).addBox(-2.0F, -0.5F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(56, 4).addBox(-2.0F, -0.5F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(56, 2).addBox(-1.0F, -0.5F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(56, 0).addBox(-2.0F, -0.5F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 11.5F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition cube_r4 = fan2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(30, 14).addBox(-3.5F, -0.5F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(50, 12).addBox(-3.5F, -0.5F, 3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(52, 43).addBox(-4.5F, -0.5F, 3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(28, 55).addBox(-4.5F, -0.5F, 2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(32, 55).addBox(-2.5F, -0.5F, 2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(55, 44).addBox(-3.5F, -0.5F, 2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 0.0F, 1.5F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r5 = fan2.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(4, 57).addBox(-3.0F, -0.5F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(8, 57).addBox(-3.0F, -0.5F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(12, 57).addBox(-2.0F, -0.5F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(16, 57).addBox(-2.0F, -0.5F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(20, 57).addBox(-2.0F, -0.5F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(24, 57).addBox(-1.0F, -0.5F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r6 = fan2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(28, 57).addBox(-1.0F, -0.5F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(32, 57).addBox(-2.0F, -0.5F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(58, 9).addBox(-3.0F, -0.5F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(58, 28).addBox(-3.0F, -0.5F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(58, 30).addBox(-2.0F, -0.5F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(58, 32).addBox(-2.0F, -0.5F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create().texOffs(22, 19).addBox(-8.0F, -1.0F, -4.0F, 16.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.0F, -3.0F, -3.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(-8.0F, -17.0F, -4.0F, 16.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 28).addBox(-8.0F, -16.0F, 3.0F, 16.0F, 15.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(38, 46).addBox(7.0F, -16.0F, -4.0F, 1.0F, 15.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(43, 39).addBox(-8.0F, -16.0F, -4.0F, 1.0F, 15.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r7 = base.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(20, 30).addBox(3.0F, -3.0F, -7.0F, 1.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition glass = partdefinition.addOrReplaceChild("glass", CubeListBuilder.create().texOffs(0, 38).addBox(8.0F, -16.0F, -3.0F, 0.0F, 13.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r8 = glass.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 0).addBox(3.0F, -16.0F, -7.0F, 1.0F, 13.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition CPU = partdefinition.addOrReplaceChild("CPU", CubeListBuilder.create(), PartPose.offset(-4.8F, 11.6F, 3.3F));

		PartDefinition cpu_r1 = CPU.addOrReplaceChild("cpu_r1", CubeListBuilder.create().texOffs(8, 0).addBox(-1.0F, -2.5F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition motherboard = partdefinition.addOrReplaceChild("motherboard", CubeListBuilder.create().texOffs(12, 46).addBox(-7.0F, -15.0F, 2.9F, 8.0F, 11.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(16, 9).addBox(-2.3F, -13.4F, 2.8F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(10, 11).addBox(-5.3F, -13.4F, 2.8F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-7.0F, -14.2F, 2.1F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(12, 44).addBox(-5.7F, -8.2F, 2.8F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(16, 6).addBox(-5.7F, -6.6F, 2.8F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r9 = motherboard.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(10, 8).addBox(-2.0F, -2.5F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(8, 3).addBox(1.0F, -2.5F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.8F, -12.4F, 3.3F, 0.0F, 0.0F, 1.5708F));

		PartDefinition GPU = partdefinition.addOrReplaceChild("GPU", CubeListBuilder.create().texOffs(16, 9).addBox(-7.0F, -8.2F, -1.0F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition front_lightmap = partdefinition.addOrReplaceChild("front_lightmap", CubeListBuilder.create().texOffs(36, 22).addBox(-0.5F, -3.5F, -3.0F, 0.0F, 15.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, 11.5F, 0.0F));

		PartDefinition RAM = partdefinition.addOrReplaceChild("RAM", CubeListBuilder.create().texOffs(50, 15).addBox(-0.7F, -14.0F, 2.5F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(48, 38).addBox(-0.4F, -14.0F, 2.5F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(38, 8).addBox(-0.1F, -14.0F, 2.5F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(12, 2).addBox(0.2F, -14.0F, 2.5F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.1F, 24.1F, 0.0F));

		PartDefinition motherboard_lightmap = partdefinition.addOrReplaceChild("motherboard_lightmap", CubeListBuilder.create().texOffs(36, 9).addBox(-6.9F, -8.0F, 2.8F, 1.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r10 = motherboard_lightmap.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(16, 0).addBox(1.1F, 0.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.4F, -10.1F, 2.8F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r11 = motherboard_lightmap.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(4, 0).addBox(-3.3F, -6.8F, 0.0F, 1.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.4F, -6.0F, 2.8F, 0.0F, 0.0F, 1.5708F));

		PartDefinition Disk_Station = partdefinition.addOrReplaceChild("Disk_Station", CubeListBuilder.create().texOffs(28, 41).addBox(-4.0F, -2.5F, -2.5F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 3).addBox(0.0F, -2.5F, -2.5F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 21.5F, 0.5F));

		PartDefinition cube_r12 = Disk_Station.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(48, 27).addBox(-2.5F, 0.0F, -2.5F, 0.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(28, 46).addBox(-0.4F, 0.0F, -2.5F, 0.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(48, 23).addBox(-1.4F, 0.0F, -2.5F, 0.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition Disks = partdefinition.addOrReplaceChild("Disks", CubeListBuilder.create().texOffs(50, 9).addBox(-3.5F, -2.4F, -2.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(48, 36).addBox(-3.5F, -1.4F, -2.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 21.5F, 0.5F));

		PartDefinition case_lightmap = partdefinition.addOrReplaceChild("case_lightmap", CubeListBuilder.create().texOffs(30, 16).addBox(-4.5F, -0.5F, -0.5F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(52, 39).addBox(4.5F, -4.4F, -0.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 23.1F, 3.1F));

		PartDefinition cube_r13 = case_lightmap.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(52, 41).addBox(-4.0F, -5.2F, -0.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition Ventirad = partdefinition.addOrReplaceChild("Ventirad", CubeListBuilder.create().texOffs(52, 13).addBox(-4.8F, -13.8F, -0.4F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Alimentation = partdefinition.addOrReplaceChild("Alimentation", CubeListBuilder.create().texOffs(35, 9).addBox(-7.0F, -3.0F, -3.0F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		fan.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		fan2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		base.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		glass.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		if(computerTileEntity.getItems().contains(DMItems.CPU.get())){
			CPU.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		}
		if(computerTileEntity.getItems().contains(DMItems.GPU.get())){
			GPU.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		}
		if(computerTileEntity.getItems().contains(DMItems.VENTIRAD.get())){
			Ventirad.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		}
		if(computerTileEntity.getItems().contains(DMItems.ALIMENTATION.get())){
			Alimentation.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		}
		if(computerTileEntity.getItems().contains(DMItems.MOTHERBOARD.get())){
			motherboard.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		}
		if(computerTileEntity.getItems().contains(DMItems.RAM.get())){
			RAM.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		}
		if(computerTileEntity.getItems().contains(DMItems.DISK.get())){
			Disks.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		}
		if(computerTileEntity.getBlockState().getBlock() instanceof ComputerBlock computerBlock){
			if(computerBlock.isRunning){
				front_lightmap.render(poseStack, buffer, ModelUtils.getModelGlow(1F), packedOverlay, red, green, blue, alpha);
				motherboard_lightmap.render(poseStack, buffer, ModelUtils.getModelGlow(1F), packedOverlay, red, green, blue, alpha);
				Disk_Station.render(poseStack, buffer, ModelUtils.getModelGlow(1F), packedOverlay, red, green, blue, alpha);
				case_lightmap.render(poseStack, buffer, ModelUtils.getModelGlow(1F), packedOverlay, red, green, blue, alpha);
			}
			if(!computerBlock.isRunning){
				front_lightmap.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
				motherboard_lightmap.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
				Disk_Station.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
				case_lightmap.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
			}
		}
	}


}