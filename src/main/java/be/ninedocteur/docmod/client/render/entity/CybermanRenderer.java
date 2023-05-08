package be.ninedocteur.docmod.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.models.entity.CyberBossEyesLayer;
import be.ninedocteur.docmod.client.models.entity.CybermanEntityModel;
import be.ninedocteur.docmod.client.models.entity.CybermanEyesLayer;
import be.ninedocteur.docmod.client.models.entity.DalekEyesLayer;
import be.ninedocteur.docmod.common.entity.mob.CybermanEntity;
import be.ninedocteur.docmod.registry.LayerDefinitionsRegistry;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CybermanRenderer extends MobRenderer<CybermanEntity, CybermanEntityModel<CybermanEntity>> {

    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(DocMod.MOD_ID, "textures/entity/cyberman.png");

    private CybermanEntityModel model;
    public CybermanRenderer(EntityRendererProvider.Context p_173929_) {
        super(p_173929_, new CybermanEntityModel(p_173929_.bakeLayer(LayerDefinitionsRegistry.CYBERMAN_LAYER_LOCATION)), 0.25F);
        this.addLayer(new CybermanEyesLayer<>(this));
        model = this.getModel();
    }
    
    @Override
    public void render(CybermanEntity p_115455_, float p_115456_, float p_115457_, PoseStack p_115458_, MultiBufferSource p_115459_, int p_115460_) {
    	if(p_115455_.isAttacking) {
    		model.leftarm.yRot = 90;
    	} else {
    		model.leftarm.yRot = 0;
    	}
    	super.render(p_115455_, p_115456_, p_115457_, p_115458_, p_115459_, p_115460_);
    }

    @Override
    public ResourceLocation getTextureLocation(CybermanEntity pEntity) {
        return TEXTURE_LOCATION;
    }
}
