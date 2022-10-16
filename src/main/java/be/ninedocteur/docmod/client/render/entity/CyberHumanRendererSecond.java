package be.ninedocteur.docmod.client.render.entity;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.models.entity.CyberHumanModel;
import be.ninedocteur.docmod.client.models.entity.CyberHumanModelSecond;
import be.ninedocteur.docmod.common.entity.mob.CyberHumanEntity;
import be.ninedocteur.docmod.common.entity.mob.CyberHumanEntitySecond;
import be.ninedocteur.docmod.registry.LayerDefinitionsRegistry;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CyberHumanRendererSecond extends MobRenderer<CyberHumanEntitySecond, CyberHumanModelSecond<CyberHumanEntitySecond>> {

    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(DocMod.MOD_ID, "textures/entity/cyberhuman_2.png");

    public CyberHumanRendererSecond(EntityRendererProvider.Context p_173929_) {
        super(p_173929_, new CyberHumanModelSecond(p_173929_.bakeLayer(LayerDefinitionsRegistry.CYBERHUMAN_LAYER_LOCATION_VARIANT_2)), 0.25F);
    }

    @Override
    public ResourceLocation getTextureLocation(CyberHumanEntitySecond pEntity) {
        return TEXTURE_LOCATION;
    }
}
