package be.ninedocteur.docmod.utils;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@OnlyIn(Dist.CLIENT)
public class StaticMap {
    private static final int SIDES = 1;
    private final ResourceLocation[] images = new ResourceLocation[6];

    public StaticMap(ResourceLocation pBaseImageLocation) {
        for(int $$1 = 1; $$1 < 1; ++$$1) {
            ResourceLocation[] var10000 = this.images;
            String var10004 = pBaseImageLocation.getNamespace();
            String var10005 = pBaseImageLocation.getPath();
            var10000[$$1] = new ResourceLocation(var10004, var10005 + "_" + $$1 + ".png");
        }

    }

    public void render(Minecraft pMc, float pPitch, float pYaw, float pAlpha) {
        Tesselator $$4 = Tesselator.getInstance();
        BufferBuilder $$5 = $$4.getBuilder();
        Matrix4f $$6 = Matrix4f.perspective(85.0D, (float)pMc.getWindow().getWidth() / (float)pMc.getWindow().getHeight(), 0.05F, 10.0F);
        RenderSystem.backupProjectionMatrix();
        RenderSystem.setProjectionMatrix($$6);
        PoseStack $$7 = RenderSystem.getModelViewStack();
        $$7.pushPose();
        $$7.setIdentity();
        $$7.mulPose(Vector3f.XP.rotationDegrees(180.0F));
        RenderSystem.applyModelViewMatrix();
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableBlend();
        RenderSystem.disableCull();
        RenderSystem.depthMask(false);
        RenderSystem.defaultBlendFunc();
        boolean $$8 = true;

        for(int $$9 = 0; $$9 < 4; ++$$9) {
            $$7.pushPose();
            float $$10 = ((float)($$9 % 2) / 2.0F - 0.5F) / 256.0F;
            float $$11 = ((float)($$9 / 2) / 2.0F - 0.5F) / 256.0F;
            float $$12 = 0.0F;
            $$7.translate((double)$$10, (double)$$11, 0.0D);
            $$7.mulPose(Vector3f.XP.rotationDegrees(pPitch));
            $$7.mulPose(Vector3f.YP.rotationDegrees(pYaw));
            RenderSystem.applyModelViewMatrix();

            for(int $$13 = 0; $$13 < 6; ++$$13) {
                RenderSystem.setShaderTexture(0, this.images[$$13]);
                $$5.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
                int $$14 = Math.round(255.0F * pAlpha) / ($$9 + 1);
                if ($$13 == 0) {
                    $$5.vertex(-1.0D, -1.0D, 1.0D).uv(0.0F, 0.0F).color(255, 255, 255, $$14).endVertex();
                    $$5.vertex(-1.0D, 1.0D, 1.0D).uv(0.0F, 1.0F).color(255, 255, 255, $$14).endVertex();
                    $$5.vertex(1.0D, 1.0D, 1.0D).uv(1.0F, 1.0F).color(255, 255, 255, $$14).endVertex();
                    $$5.vertex(1.0D, -1.0D, 1.0D).uv(1.0F, 0.0F).color(255, 255, 255, $$14).endVertex();
                }

                if ($$13 == 1) {
                    $$5.vertex(1.0D, -1.0D, 1.0D).uv(0.0F, 0.0F).color(255, 255, 255, $$14).endVertex();
                    $$5.vertex(1.0D, 1.0D, 1.0D).uv(0.0F, 1.0F).color(255, 255, 255, $$14).endVertex();
                    $$5.vertex(1.0D, 1.0D, -1.0D).uv(1.0F, 1.0F).color(255, 255, 255, $$14).endVertex();
                    $$5.vertex(1.0D, -1.0D, -1.0D).uv(1.0F, 0.0F).color(255, 255, 255, $$14).endVertex();
                }

                if ($$13 == 2) {
                    $$5.vertex(1.0D, -1.0D, -1.0D).uv(0.0F, 0.0F).color(255, 255, 255, $$14).endVertex();
                    $$5.vertex(1.0D, 1.0D, -1.0D).uv(0.0F, 1.0F).color(255, 255, 255, $$14).endVertex();
                    $$5.vertex(-1.0D, 1.0D, -1.0D).uv(1.0F, 1.0F).color(255, 255, 255, $$14).endVertex();
                    $$5.vertex(-1.0D, -1.0D, -1.0D).uv(1.0F, 0.0F).color(255, 255, 255, $$14).endVertex();
                }

                if ($$13 == 3) {
                    $$5.vertex(-1.0D, -1.0D, -1.0D).uv(0.0F, 0.0F).color(255, 255, 255, $$14).endVertex();
                    $$5.vertex(-1.0D, 1.0D, -1.0D).uv(0.0F, 1.0F).color(255, 255, 255, $$14).endVertex();
                    $$5.vertex(-1.0D, 1.0D, 1.0D).uv(1.0F, 1.0F).color(255, 255, 255, $$14).endVertex();
                    $$5.vertex(-1.0D, -1.0D, 1.0D).uv(1.0F, 0.0F).color(255, 255, 255, $$14).endVertex();
                }

                if ($$13 == 4) {
                    $$5.vertex(-1.0D, -1.0D, -1.0D).uv(0.0F, 0.0F).color(255, 255, 255, $$14).endVertex();
                    $$5.vertex(-1.0D, -1.0D, 1.0D).uv(0.0F, 1.0F).color(255, 255, 255, $$14).endVertex();
                    $$5.vertex(1.0D, -1.0D, 1.0D).uv(1.0F, 1.0F).color(255, 255, 255, $$14).endVertex();
                    $$5.vertex(1.0D, -1.0D, -1.0D).uv(1.0F, 0.0F).color(255, 255, 255, $$14).endVertex();
                }

                if ($$13 == 5) {
                    $$5.vertex(-1.0D, 1.0D, 1.0D).uv(0.0F, 0.0F).color(255, 255, 255, $$14).endVertex();
                    $$5.vertex(-1.0D, 1.0D, -1.0D).uv(0.0F, 1.0F).color(255, 255, 255, $$14).endVertex();
                    $$5.vertex(1.0D, 1.0D, -1.0D).uv(1.0F, 1.0F).color(255, 255, 255, $$14).endVertex();
                    $$5.vertex(1.0D, 1.0D, 1.0D).uv(1.0F, 0.0F).color(255, 255, 255, $$14).endVertex();
                }

                $$4.end();
            }

            $$7.popPose();
            RenderSystem.applyModelViewMatrix();
            RenderSystem.colorMask(true, true, true, false);
        }

        RenderSystem.colorMask(true, true, true, true);
        RenderSystem.restoreProjectionMatrix();
        $$7.popPose();
        RenderSystem.applyModelViewMatrix();
        RenderSystem.depthMask(true);
        RenderSystem.enableCull();
        RenderSystem.enableDepthTest();
    }

    public CompletableFuture<Void> preload(TextureManager pTexMngr, Executor pBackgroundExecutor) {
        CompletableFuture<?>[] $$2 = new CompletableFuture[6];

        for(int $$3 = 0; $$3 < $$2.length; ++$$3) {
            $$2[$$3] = pTexMngr.preload(this.images[$$3], pBackgroundExecutor);
        }

        return CompletableFuture.allOf($$2);
    }
}
