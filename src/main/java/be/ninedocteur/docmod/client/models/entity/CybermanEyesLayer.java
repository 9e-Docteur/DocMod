package be.ninedocteur.docmod.client.models.entity;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.entity.mob.CyberBossEntity;
import be.ninedocteur.docmod.common.entity.mob.CybermanEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CybermanEyesLayer<T extends CybermanEntity> extends DMEyesLayer<T, CybermanEntityModel<T>> {
    private static final RenderType CYBER_EYES = RenderType.eyes(new ResourceLocation(DocMod.MOD_ID, "textures/entity/cyberboss_e.png"));

    public CybermanEyesLayer(RenderLayerParent<T, CybermanEntityModel<T>> p_116964_) {
        super(p_116964_);
    }

    public RenderType renderType() {
        return CYBER_EYES;
    }
}
