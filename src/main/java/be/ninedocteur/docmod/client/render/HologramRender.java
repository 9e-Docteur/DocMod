package be.ninedocteur.docmod.client.render;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.models.PlayerEntityModel;
import be.ninedocteur.docmod.common.tileentity.HologramTileEntity;
import be.ninedocteur.docmod.registry.ModelRegistry;
import be.ninedocteur.docmod.utils.PlayerUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HologramRender implements BlockEntityRenderer<HologramTileEntity> {
    private PlayerEntityModel model;

    public HologramRender(BlockEntityRendererProvider.Context pContextd) {
        model = new PlayerEntityModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelRegistry.PLAYER));
    }

    @Override
    public void render(HologramTileEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        pPoseStack.pushPose();
        if(pBlockEntity.isThereTextures){
            DocMod.LOGGER.info("Getting skin of: " + pBlockEntity.getPlayerTexture().toString());
            model.renderToBuffer(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(PlayerUtils.getSkin(pBlockEntity.getPlayerTexture().toString()))), pPackedLight, pPackedOverlay, 0.5F,0.5F,0.5F,0.5F);
        } else {
            model.renderToBuffer(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/default_hologram.png"))), pPackedLight, pPackedOverlay, 0.5F,0.5F,0.5F,0.5F);
        }
        pPoseStack.popPose();
    }
}
