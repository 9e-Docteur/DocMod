package be.ninedocteur.docmod.client.render.entity;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.models.entity.ZurbionEntityModel;
import be.ninedocteur.docmod.common.entity.mob.Zurbion;
import be.ninedocteur.docmod.registry.LayerDefinitionsRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZurbionRenderer extends MobRenderer<Zurbion, ZurbionEntityModel<Zurbion>> {

    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(DocMod.MOD_ID, "textures/entity/zurbion.png");

    public ZurbionRenderer(EntityRendererProvider.Context p_173929_) {
        super(p_173929_, new ZurbionEntityModel(p_173929_.bakeLayer(LayerDefinitionsRegistry.ZURBION_LAYER_LOCATION)), 0.25F);
    }

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getTextureLocation(Zurbion pEntity) {
        return TEXTURE_LOCATION;
    }

    protected void scale(Zurbion pLivingEntity, PoseStack pPoseStack, float pPartialTickTime) {
        pPoseStack.scale(0.35F, 0.35F, 0.35F);
    }

    protected void setupRotations(Zurbion pEntityLiving, PoseStack pPoseStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        if (pEntityLiving.isResting()) {
            pPoseStack.translate(0.0D, -0.1F, 0.0D);
        } else {
            pPoseStack.translate(0.0D, Mth.cos(pAgeInTicks * 0.3F) * 0.1F, 0.0D);
        }

        super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks);
    }
}
