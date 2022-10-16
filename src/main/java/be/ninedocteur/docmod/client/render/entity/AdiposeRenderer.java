package be.ninedocteur.docmod.client.render.entity;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.models.entity.AdiposeModel;
import be.ninedocteur.docmod.client.models.entity.CyberBossEntityModel;
import be.ninedocteur.docmod.common.entity.mob.AdiposeEntity;
import be.ninedocteur.docmod.common.entity.mob.CyberBossEntity;
import be.ninedocteur.docmod.registry.LayerDefinitionsRegistry;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AdiposeRenderer extends MobRenderer<AdiposeEntity, AdiposeModel<AdiposeEntity>> {

    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(DocMod.MOD_ID, "textures/entity/adipose.png");

    public AdiposeRenderer(EntityRendererProvider.Context p_173929_) {
        super(p_173929_, new AdiposeModel(p_173929_.bakeLayer(LayerDefinitionsRegistry.ADIPOSE)), 0.25F);
    }

    @Override
    public ResourceLocation getTextureLocation(AdiposeEntity pEntity) {
        return TEXTURE_LOCATION;
    }
}
