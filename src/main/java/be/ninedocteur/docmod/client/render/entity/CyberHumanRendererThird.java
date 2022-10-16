package be.ninedocteur.docmod.client.render.entity;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.models.entity.CyberHumanModel;
import be.ninedocteur.docmod.client.models.entity.CyberHumanModelThird;
import be.ninedocteur.docmod.common.entity.mob.CyberHumanEntity;
import be.ninedocteur.docmod.common.entity.mob.CyberHumanEntityThird;
import be.ninedocteur.docmod.registry.LayerDefinitionsRegistry;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CyberHumanRendererThird extends MobRenderer<CyberHumanEntityThird, CyberHumanModelThird<CyberHumanEntityThird>> {

    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(DocMod.MOD_ID, "textures/entity/cyberhuman_3.png");

    public CyberHumanRendererThird(EntityRendererProvider.Context p_173929_) {
        super(p_173929_, new CyberHumanModelThird(p_173929_.bakeLayer(LayerDefinitionsRegistry.CYBERMAN_LAYER_LOCATION)), 0.25F);
    }

    @Override
    public ResourceLocation getTextureLocation(CyberHumanEntityThird pEntity) {
        return TEXTURE_LOCATION;
    }
}
