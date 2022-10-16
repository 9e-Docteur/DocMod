package be.ninedocteur.docmod.client.render.entity;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.models.entity.CyberBossEyesLayer;
import be.ninedocteur.docmod.client.models.entity.CybermanEntityModel;
import be.ninedocteur.docmod.client.models.entity.CybermanEyesLayer;
import be.ninedocteur.docmod.client.models.entity.DalekEyesLayer;
import be.ninedocteur.docmod.common.entity.mob.CybermanEntity;
import be.ninedocteur.docmod.registry.LayerDefinitionsRegistry;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CybermanRenderer extends MobRenderer<CybermanEntity, CybermanEntityModel<CybermanEntity>> {

    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(DocMod.MOD_ID, "textures/entity/cyberman.png");

    public CybermanRenderer(EntityRendererProvider.Context p_173929_) {
        super(p_173929_, new CybermanEntityModel(p_173929_.bakeLayer(LayerDefinitionsRegistry.CYBERMAN_LAYER_LOCATION)), 0.25F);
        this.addLayer(new CybermanEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(CybermanEntity pEntity) {
        return TEXTURE_LOCATION;
    }
}
