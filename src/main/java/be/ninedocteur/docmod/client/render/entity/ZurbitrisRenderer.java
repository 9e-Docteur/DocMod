package be.ninedocteur.docmod.client.render.entity;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.models.entity.ZurbitrisEntityModel;
import be.ninedocteur.docmod.common.entity.mob.Zurbitris;
import be.ninedocteur.docmod.registry.LayerDefinitionsRegistry;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZurbitrisRenderer extends MobRenderer<Zurbitris, ZurbitrisEntityModel<Zurbitris>> {

    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(DocMod.MOD_ID, "textures/entity/zurbitris.png");

    public ZurbitrisRenderer(EntityRendererProvider.Context p_173929_) {
        super(p_173929_, new ZurbitrisEntityModel(p_173929_.bakeLayer(LayerDefinitionsRegistry.ZURBITRIS_LAYER_LOCATION)), 0.25F);
    }

    @Override
    public ResourceLocation getTextureLocation(Zurbitris pEntity) {
        return TEXTURE_LOCATION;
    }
}
