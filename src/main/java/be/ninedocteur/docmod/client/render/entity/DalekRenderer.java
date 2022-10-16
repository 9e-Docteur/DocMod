package be.ninedocteur.docmod.client.render.entity;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.models.entity.DalekEntityModel;
import be.ninedocteur.docmod.client.models.entity.DalekEyesLayer;
import be.ninedocteur.docmod.common.entity.mob.Dalek;
import be.ninedocteur.docmod.registry.LayerDefinitionsRegistry;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DalekRenderer extends MobRenderer<Dalek, DalekEntityModel<Dalek>> {

    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(DocMod.MOD_ID, "textures/entity/dalek.png");

    public DalekRenderer(EntityRendererProvider.Context p_173929_) {
        super(p_173929_, new DalekEntityModel(p_173929_.bakeLayer(LayerDefinitionsRegistry.DALEK_LAYER_LOCATION)), 0.25F);
        this.addLayer(new DalekEyesLayer<>(this));
    }


    @Override
    public ResourceLocation getTextureLocation(Dalek pEntity) {
        return TEXTURE_LOCATION;
    }
}
