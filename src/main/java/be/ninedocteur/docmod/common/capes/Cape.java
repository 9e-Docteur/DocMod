package be.ninedocteur.docmod.common.capes;

import be.ninedocteur.docmod.utils.IOUtils;
import be.ninedocteur.docmod.utils.LaunchUtils;
import be.ninedocteur.docteam.api.DocTeamAPI;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Arrays;
import java.util.Objects;

public class Cape extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    public Cape(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> p_117346_) {
        super(p_117346_);
    }

    @Override
    public void render(PoseStack p_116615_, MultiBufferSource p_117350_, int p_117351_, AbstractClientPlayer p_116618_, float p_117353_, float p_117354_, float p_116621_, float p_117356_, float p_117357_, float p_117358_) {
        ItemStack itemstack = p_116618_.getItemBySlot(EquipmentSlot.CHEST);
        //CapeHandler capeHandler = CapeHandler.getCape(p_116618_);
        if (!itemstack.is(Items.ELYTRA)) {
            p_116615_.pushPose();
            p_116615_.translate(0.0D, 0.0D, 0.125D);
            double d0 = Mth.lerp((double)p_116621_, p_116618_.xCloakO, p_116618_.xCloak) - Mth.lerp((double)p_116621_, p_116618_.xo, p_116618_.getX());
            double d1 = Mth.lerp((double)p_116621_, p_116618_.yCloakO, p_116618_.yCloak) - Mth.lerp((double)p_116621_, p_116618_.yo, p_116618_.getY());
            double d2 = Mth.lerp((double)p_116621_, p_116618_.zCloakO, p_116618_.zCloak) - Mth.lerp((double)p_116621_, p_116618_.zo, p_116618_.getZ());
            float f = p_116618_.yBodyRotO + (p_116618_.yBodyRot - p_116618_.yBodyRotO);
            double d3 = (double)Mth.sin(f * ((float)Math.PI / 180F));
            double d4 = (double)(-Mth.cos(f * ((float)Math.PI / 180F)));
            float f1 = (float)d1 * 10.0F;
            f1 = Mth.clamp(f1, -6.0F, 32.0F);
            float f2 = (float)(d0 * d3 + d2 * d4) * 100.0F;
            f2 = Mth.clamp(f2, 0.0F, 150.0F);
            float f3 = (float)(d0 * d4 - d2 * d3) * 100.0F;
            f3 = Mth.clamp(f3, -20.0F, 20.0F);
            if (f2 < 0.0F) {
                f2 = 0.0F;
            }

            float f4 = Mth.lerp(p_116621_, p_116618_.oBob, p_116618_.bob);
            f1 += Mth.sin(Mth.lerp(p_116621_, p_116618_.walkDistO, p_116618_.walkDist) * 6.0F) * 32.0F * f4;
            if (p_116618_.isCrouching()) {
                f1 += 25.0F;
            }

            p_116615_.mulPose(Vector3f.XP.rotationDegrees(6.0F + f2 / 2.0F + f1));
            p_116615_.mulPose(Vector3f.ZP.rotationDegrees(f3 / 2.0F));
            p_116615_.mulPose(Vector3f.YP.rotationDegrees(180.0F - f3 / 2.0F));
            checkCapeAndApply(p_116615_, p_117350_, p_116618_, p_117351_);
            p_116615_.popPose();
        }
    }

    private void checkCapeAndApply(PoseStack poseStack, MultiBufferSource multiBufferSource, AbstractClientPlayer abstractClientPlayer, int integer){
        if(IOUtils.getCapeUsers().contains(abstractClientPlayer.getUUID().toString())) {
            if(CapeHandler.DOWNLOADED_TEXTURES.containsKey("https://api.docteam.tk/docmod/cape/" + abstractClientPlayer.getUUID() + ".png")) {
                VertexConsumer vertexconsumer = multiBufferSource.getBuffer(RenderType.entitySolid(CapeHandler.DOWNLOADED_TEXTURES.get("https://api.docteam.tk/docmod/cape/" + abstractClientPlayer.getUUID() + ".png")));
                this.getParentModel().renderCloak(poseStack, vertexconsumer, integer, OverlayTexture.NO_OVERLAY);
            } else {
                VertexConsumer vertexconsumer = multiBufferSource.getBuffer(RenderType.entitySolid(CapeHandler.readCapeTexture(getCape(abstractClientPlayer), abstractClientPlayer.getUUID())));
                this.getParentModel().renderCloak(poseStack, vertexconsumer, integer, OverlayTexture.NO_OVERLAY);
            }
        }
        //if(LaunchUtils.isRunningInDev()) {
        	//if(DocTeamAPI.isConnected) {
        	//	if(IOUtils.getCapeUsers().contains(abstractClientPlayer.getUUID().toString())) {
        		//	if(CapeHandler.DOWNLOADED_TEXTURES.containsKey("https://api.docteam.tk/docmod/dev/cape/" + abstractClientPlayer.getUUID() + ".png")) {
        		//		VertexConsumer vertexconsumer = multiBufferSource.getBuffer(RenderType.entitySolid(CapeHandler.DOWNLOADED_TEXTURES.get("https://api.docteam.tk/docmod/dev/cape/" + abstractClientPlayer.getUUID() + ".png")));
        		//		this.getParentModel().renderCloak(poseStack, vertexconsumer, integer, OverlayTexture.NO_OVERLAY);
        		//	} else {
        			//	VertexConsumer vertexconsumer = multiBufferSource.getBuffer(RenderType.entitySolid(CapeHandler.readCapeTexture(getDevCape(abstractClientPlayer), abstractClientPlayer.getUUID())));
        			//	this.getParentModel().renderCloak(poseStack, vertexconsumer, integer, OverlayTexture.NO_OVERLAY);
        			//}
        		//}
        	//}
       // }
    }

    private String getCape(AbstractClientPlayer abstractClientPlayer){
        return DocTeamAPI.getAPI() + "docmod/cape/" + abstractClientPlayer.getUUID() + ".png";
    }
    
    private String getDevCape(AbstractClientPlayer abstractClientPlayer){
        return DocTeamAPI.getAPI() + "docmod/dev/cape/" + abstractClientPlayer.getUUID() + ".png";
    }
}