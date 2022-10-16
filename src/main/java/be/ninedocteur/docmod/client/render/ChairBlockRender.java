package be.ninedocteur.docmod.client.render;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.models.ChairModel;
import be.ninedocteur.docmod.client.models.ToyotaRotorModel;
import be.ninedocteur.docmod.common.tileentity.ChairTileEntity;
import be.ninedocteur.docmod.common.tileentity.ToyotaRotorTileEntity;
import be.ninedocteur.docmod.registry.ModelRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChairBlockRender implements BlockEntityRenderer<ChairTileEntity> {


    public static ChairModel model;

    public ChairBlockRender(BlockEntityRendererProvider.Context pContextd) {
        model = new ChairModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelRegistry.CHAIR));
    }

    @Override
    public void render(ChairTileEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        pPoseStack.pushPose();
        pPoseStack.translate(0.5, 1.5, 0.5);
        model.renderToBuffer(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/chair.png"))), pPackedLight, pPackedOverlay, 1,1,1,1);
    }
}
