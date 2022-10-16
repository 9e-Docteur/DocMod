package be.ninedocteur.docmod.client.render.entity;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.models.entity.CyberHumanModel;
import be.ninedocteur.docmod.common.entity.mob.CyberHumanEntity;
import be.ninedocteur.docmod.registry.LayerDefinitionsRegistry;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CyberHumanRenderer extends MobRenderer<CyberHumanEntity, CyberHumanModel<CyberHumanEntity>> {

    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(DocMod.MOD_ID, "textures/entity/cyberhuman.png");

    public CyberHumanRenderer(EntityRendererProvider.Context p_173929_) {
        super(p_173929_, new CyberHumanModel(p_173929_.bakeLayer(LayerDefinitionsRegistry.CYBERHUMAN_LAYER_LOCATION_VARIANT_1)), 0.25F);
    }

    @Override
    public ResourceLocation getTextureLocation(CyberHumanEntity pEntity) {
        return TEXTURE_LOCATION;
    }
}
