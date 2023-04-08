package be.ninedocteur.docmod.client.render;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.models.ComputerModel;
import be.ninedocteur.docmod.client.models.ToyotaRotorModel;
import be.ninedocteur.docmod.common.tileentity.ComputerTileEntity;
import be.ninedocteur.docmod.common.tileentity.ToyotaRotorTileEntity;
import be.ninedocteur.docmod.registry.ModelRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ComputerBlockRender/* implements BlockEntityRenderer<ComputerTileEntity>*/ {


//    public static ComputerModel model;
//
//    public ComputerBlockRender(BlockEntityRendererProvider.Context pContextd) {
//
//    }
//
//    @Override
//    public void render(ComputerTileEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
//        pPoseStack.pushPose();
//        pPoseStack.translate(0.5, 1.5, 0.5);
//        pPoseStack.mulPose(Axis.ZP.rotationDegrees(180));
//        new ComputerModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelRegistry.COMPUTER), pBlockEntity).renderToBuffer(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/computer_2.png"))), pPackedLight, pPackedOverlay, 1,1,1,1);
//        pPoseStack.popPose();
//    }
}
