package be.ninedocteur.docmod.client.render.entity;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.models.entity.SWDEyesLayer;
import be.ninedocteur.docmod.client.models.entity.SpecialWeaponsDalekEntityModel;
import be.ninedocteur.docmod.common.entity.mob.SWDalek;
import be.ninedocteur.docmod.registry.LayerDefinitionsRegistry;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpecialWeaponsDalekRenderer extends MobRenderer<SWDalek, SpecialWeaponsDalekEntityModel<SWDalek>> {

    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(DocMod.MOD_ID, "textures/entity/special_weapons.png");

    public SpecialWeaponsDalekRenderer(EntityRendererProvider.Context p_173929_) {
        super(p_173929_, new SpecialWeaponsDalekEntityModel(p_173929_.bakeLayer(LayerDefinitionsRegistry.SWDALEK_LAYER_LOCATION)), 0.25F);
        this.addLayer(new SWDEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(SWDalek pEntity) {
        return TEXTURE_LOCATION;
    }
}
