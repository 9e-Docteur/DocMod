package be.ninedocteur.docmod.client.render.entity;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.models.entity.OldSteveEntityModel;
import be.ninedocteur.docmod.common.entity.mob.OldSteve;
import be.ninedocteur.docmod.registry.LayerDefinitionsRegistry;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OldSteveRenderer extends MobRenderer<OldSteve, OldSteveEntityModel<OldSteve>> {

    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(DocMod.MOD_ID, "textures/entity/steve.png");

    public OldSteveRenderer(EntityRendererProvider.Context p_173929_) {
        super(p_173929_, new OldSteveEntityModel(p_173929_.bakeLayer(LayerDefinitionsRegistry.ZURBITRIS_LAYER_LOCATION)), 0.25F);
    }

    @Override
    public ResourceLocation getTextureLocation(OldSteve pEntity) {
        return TEXTURE_LOCATION;
    }
}
