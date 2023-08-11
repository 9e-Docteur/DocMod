package be.ninedocteur.docmod.client.models.entity;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.entity.mob.Dalek;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DalekEyesLayer<T extends Dalek> extends DMEyesLayer<T, DalekEntityModel<T>> {
    private static final RenderType DALEK_EYES = RenderType.eyes(new ResourceLocation(DocMod.MOD_ID, "textures/entity/dalek_e.png"));

    public DalekEyesLayer(RenderLayerParent<T, DalekEntityModel<T>> p_116964_) {
        super(p_116964_);
    }

    public RenderType renderType() {
        return DALEK_EYES;
    }
}
