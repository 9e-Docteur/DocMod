package be.ninedocteur.docmod.client.render;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.models.ComputerModel;
import be.ninedocteur.docmod.client.models.HartnellModel;
import be.ninedocteur.docmod.client.models.MonitorModel;
import be.ninedocteur.docmod.common.tileentity.ComputerTileEntity;
import be.ninedocteur.docmod.common.tileentity.HartnellTileEntity;
import be.ninedocteur.docmod.common.tileentity.MonitorTileEntity;
import be.ninedocteur.docmod.registry.ModelRegistry;
import be.ninedocteur.docmod.utils.ModelUtils;

import java.util.Random;

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
public class HartnellRender implements BlockEntityRenderer<HartnellTileEntity> {


    public static HartnellModel model;
    private Random random = new Random();
    int outpout = 1;
    int currentTick;
    private final double ANIMATION_DURATION = 60.0; // Durée totale de l'animation en ticks
    private final double MAX_HEIGHT = 0.6; // Hauteur maximale de l'animation en blocs
    private double MIN_HEIGHT = -0; // Hauteur maximale de l'animation en blocs
    float rotation = 0;

    public HartnellRender(BlockEntityRendererProvider.Context pContextd) {
        model = new HartnellModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelRegistry.HARTNELL));
    }

    @Override
    public void render(HartnellTileEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        pPoseStack.pushPose();
        //pPoseStack.mulPose(Axis.ZP.rotationDegrees(180));
        pPoseStack.translate(0.5, -1.2, 0.5);
        model.renderBase(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/new_hartnell_rotor.png"))), pPackedLight, pPackedOverlay, 1,1,1,1);
        tick();
        pPoseStack.popPose();
        pPoseStack.pushPose();
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(180));
        PoseStack rotPose = new PoseStack();
        rotPose.pushPose();
        //rotPose.mulPose(Axis.YP.rotationDegrees((float) (rotation + 10)));
        //pPoseStack.mulPose(Axis.YP.rotationDegrees((float) (rotation + 10)));
        rotPose.popPose();
        pPoseStack.translate(-0.5, -1.5, 0.5);
        //pPoseStack.scale(0.1f, 0.1f, 0.1f);
       // int x = ;
        //int y;
        //int z;
        //pPoseStack.translate(0.0, 0, 0.0);
        
        double animationTime = (pBlockEntity.getLevel().getGameTime() + pPartialTick) % ANIMATION_DURATION; // Temps écoulé depuis le début de l'animation
        double height;
        if (animationTime < ANIMATION_DURATION / 2) {
            // Descendre le rendu jusqu'à la hauteur minimale (MIN_HEIGHT) pendant la première moitié de l'animation
            double remappedTime = animationTime / (ANIMATION_DURATION / 2);
            height = MIN_HEIGHT + (MAX_HEIGHT - MIN_HEIGHT) * remappedTime;
        } else {
            // Remonter le rendu jusqu'à la hauteur maximale (MAX_HEIGHT) pendant la deuxième moitié de l'animation
            double remappedTime = (animationTime - ANIMATION_DURATION / 2) / (ANIMATION_DURATION / 2);
            height = MAX_HEIGHT - (MAX_HEIGHT - MIN_HEIGHT) * remappedTime;
        }

        // Limiter la hauteur minimale de l'animation
//        if (height < MIN_HEIGHT) {
//            height = MIN_HEIGHT;
//        }
        
        pPoseStack.translate(0, height, 0);
        
        model.renderToBuffer(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/new_hartnell_rotor.png"))), pPackedLight, pPackedOverlay, 1,1,1,1);
        model.renderRodsLightMap(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/new_hartnell_rotor.png"))), 1, ModelUtils.getModelGlow(1D), pPackedOverlay, 1,1,1,1);
        model.renderRods(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/new_hartnell_rotor.png"))), pPackedLight, pPackedOverlay, 1,1,1,1);
        model.renderCubeGlass(pPoseStack, rotPose, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/new_hartnell_rotor.png"))), pPackedLight, pPackedOverlay, 1,1,1,1);

        pPoseStack.popPose();
    }
    
    public void tick() {
    	if(currentTick != 20) {
    		currentTick++;
    	} else {
    		 outpout = random.nextInt(4) + 1;
    	}
    	
    	if(rotation != 360) {
    		rotation++;
    	} else {
    		rotation = 0;
    	}
    }
    
}
