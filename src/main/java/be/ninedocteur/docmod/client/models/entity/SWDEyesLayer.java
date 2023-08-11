package be.ninedocteur.docmod.client.models.entity;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.entity.mob.SWDalek;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SWDEyesLayer<T extends SWDalek> extends DMEyesLayer<T, SpecialWeaponsDalekEntityModel<T>> {
    private static final RenderType SWDALEK_EYES = RenderType.eyes(new ResourceLocation(DocMod.MOD_ID, "textures/entity/special_weapons_e.png"));

    public SWDEyesLayer(RenderLayerParent<T, SpecialWeaponsDalekEntityModel<T>> p_116964_) {
        super(p_116964_);
    }

    public RenderType renderType() {
        return SWDALEK_EYES;
    }
}
