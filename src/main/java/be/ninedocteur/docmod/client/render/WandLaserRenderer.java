package be.ninedocteur.docmod.client.render;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.entity.projectile.AbstractDalekLaser;
import be.ninedocteur.docmod.common.entity.projectile.AbstractWandLaser;
import be.ninedocteur.docmod.utils.ModelUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.PoseStack.Pose;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

@OnlyIn(Dist.CLIENT)
public class WandLaserRenderer<T extends AbstractWandLaser> extends EntityRenderer<T> {
    public WandLaserRenderer(Context p_173917_) {
        super(p_173917_);
    }


    public void render(T pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        pPoseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(pPartialTicks, pEntity.yRotO, pEntity.getYRot()) - 90.0F));
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(pPartialTicks, pEntity.xRotO, pEntity.getXRot())));
        boolean $$6 = false;
        float $$7 = 0.0F;
        float $$8 = 0.5F;
        float $$9 = 0.0F;
        float $$10 = 0.15625F;
        float $$11 = 0.0F;
        float $$12 = 0.15625F;
        float $$13 = 0.15625F;
        float $$14 = 0.3125F;
        float $$15 = 0.05625F;
        float $$16 = (float)pEntity.shakeTime - pPartialTicks;
        if ($$16 > 0.0F) {
            float $$17 = -Mth.sin($$16 * 3.0F) * $$16;
            pPoseStack.mulPose(Axis.ZP.rotationDegrees($$17));
        }

        pPoseStack.mulPose(Axis.XP.rotationDegrees(45.0F));
        pPoseStack.scale(0.05625F, 0.05625F, 0.05625F);
        pPoseStack.translate(-4.0D, 0.0D, 0.0D);
        VertexConsumer $$18 = pBuffer.getBuffer(RenderType.entityCutout(this.getTextureLocation(pEntity)));
        Pose $$19 = pPoseStack.last();
        Matrix4f $$20 = $$19.pose();
        Matrix3f $$21 = $$19.normal();
        this.vertex($$20, $$21, $$18, -7, -2, -2, 0.0F, 0.15625F, -1, 0, 0, ModelUtils.getModelGlow(1F));
        this.vertex($$20, $$21, $$18, -7, -2, 2, 0.15625F, 0.15625F, -1, 0, 0, ModelUtils.getModelGlow(1F));
        this.vertex($$20, $$21, $$18, -7, 2, 2, 0.15625F, 0.3125F, -1, 0, 0, ModelUtils.getModelGlow(1F));
        this.vertex($$20, $$21, $$18, -7, 2, -2, 0.0F, 0.3125F, -1, 0, 0, ModelUtils.getModelGlow(1F));
        this.vertex($$20, $$21, $$18, -7, 2, -2, 0.0F, 0.15625F, 1, 0, 0, ModelUtils.getModelGlow(1F));
        this.vertex($$20, $$21, $$18, -7, 2, 2, 0.15625F, 0.15625F, 1, 0, 0, ModelUtils.getModelGlow(1F));
        this.vertex($$20, $$21, $$18, -7, -2, 2, 0.15625F, 0.3125F, 1, 0, 0, ModelUtils.getModelGlow(1F));
        this.vertex($$20, $$21, $$18, -7, -2, -2, 0.0F, 0.3125F, 1, 0, 0, ModelUtils.getModelGlow(1F));

        for(int $$22 = 0; $$22 < 4; ++$$22) {
            pPoseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
            this.vertex($$20, $$21, $$18, -8, -2, 0, 0.0F, 0.0F, 0, 1, 0, ModelUtils.getModelGlow(1F));
            this.vertex($$20, $$21, $$18, 8, -2, 0, 0.5F, 0.0F, 0, 1, 0, ModelUtils.getModelGlow(1F));
            this.vertex($$20, $$21, $$18, 8, 2, 0, 0.5F, 0.15625F, 0, 1, 0, ModelUtils.getModelGlow(1F));
            this.vertex($$20, $$21, $$18, -8, 2, 0, 0.0F, 0.15625F, 0, 1, 0, ModelUtils.getModelGlow(1F));
        }

        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(T t) {
        return new ResourceLocation(DocMod.MOD_ID, "textures/transparent.png");
    }

    public void vertex(Matrix4f p_113826_, Matrix3f p_113827_, VertexConsumer p_113828_, int p_113829_, int p_113830_, int p_113831_, float p_113832_, float p_113833_, int p_113834_, int p_113835_, int p_113836_, int p_113837_) {
        p_113828_.vertex(p_113826_, (float)p_113829_, (float)p_113830_, (float)p_113831_).color(255, 255, 255, 255).uv(p_113832_, p_113833_).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(p_113837_).normal(p_113827_, (float)p_113834_, (float)p_113836_, (float)p_113835_).endVertex();
    }
}

