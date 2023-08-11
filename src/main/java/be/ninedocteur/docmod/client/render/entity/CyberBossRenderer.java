package be.ninedocteur.docmod.client.render.entity;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.models.entity.CyberBossEntityModel;
import be.ninedocteur.docmod.client.models.entity.CyberBossEyesLayer;
import be.ninedocteur.docmod.common.entity.mob.CyberBossEntity;
import be.ninedocteur.docmod.registry.LayerDefinitionsRegistry;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CyberBossRenderer extends MobRenderer<CyberBossEntity, CyberBossEntityModel<CyberBossEntity>> {

    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(DocMod.MOD_ID, "textures/entity/cyberboss.png");

    public CyberBossRenderer(EntityRendererProvider.Context p_173929_) {
        super(p_173929_, new CyberBossEntityModel(p_173929_.bakeLayer(LayerDefinitionsRegistry.CYBERBOSS_LAYER_LOCATION)), 0.25F);
        this.addLayer(new CyberBossEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(CyberBossEntity pEntity) {
        return TEXTURE_LOCATION;
    }
}
