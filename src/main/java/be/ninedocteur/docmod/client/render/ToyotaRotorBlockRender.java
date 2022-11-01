package be.ninedocteur.docmod.client.render;

import be.ninedocteur.docmod.client.models.TardisModel;
import be.ninedocteur.docmod.client.models.ToyotaRotorModel;
import be.ninedocteur.docmod.common.tileentity.TardisTileEntity;
import be.ninedocteur.docmod.utils.ModelUtils;
import be.ninedocteur.docmod.utils.PlanetUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.registry.ModelRegistry;
import be.ninedocteur.docmod.common.tileentity.ToyotaRotorTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ToyotaRotorBlockRender implements BlockEntityRenderer<ToyotaRotorTileEntity> {


    public static ToyotaRotorModel model;

    public ToyotaRotorBlockRender(BlockEntityRendererProvider.Context pContextd) {
        model = new ToyotaRotorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelRegistry.MODEl));
    }

    @Override
    public void render(ToyotaRotorTileEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        pPoseStack.pushPose();
        pPoseStack.translate(0.5, 1.5, 0.5);
        TardisTileEntity tardisTileEntity = TardisTileEntity.getOrCreateTardis(pBlockEntity.getBlockPos().getX() / 1000);
        if(PlanetUtils.getDimension("tardis")){
            if(tardisTileEntity != null){
                if(tardisTileEntity.isOn()){
                    model.renderToBuffer(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/toyota_rotor.png"))), pPackedLight, pPackedOverlay, 1,1,1,1);
                    model.renderMainRods(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/toyota_rotor.png"))), ModelUtils.getModelGlow(1F), pPackedOverlay, 0,0,0,1);
                } else {
                    model.renderToBuffer(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/toyota_rotor.png"))), pPackedLight, pPackedOverlay, 1,1,1,1);
                    model.renderMainRods(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/toyota_rotor_off.png"))), 0, pPackedOverlay, 0,0,0,1);
                }
            } else {
                model.renderToBuffer(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/toyota_rotor.png"))), ModelUtils.getModelGlow(1F), pPackedOverlay, 1,1,1,1);
                model.renderMainRods(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/toyota_rotor.png"))), ModelUtils.getModelGlow(1F), pPackedOverlay, 0,0,0,1);

            }
        } else {
            model.renderToBuffer(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/toyota_rotor.png"))), ModelUtils.getModelGlow(1F), pPackedOverlay, 1,1,1,1);
            model.renderMainRods(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/toyota_rotor.png"))), ModelUtils.getModelGlow(1F), pPackedOverlay, 0,0,0,1);
        }
        pPoseStack.popPose();
    }
}
