package be.ninedocteur.docmod.client.render;

import be.ninedocteur.docmod.common.tileentity.ImageTileEntity;
import be.ninedocteur.docmod.utils.IOUtils;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ImageLoaderRender implements BlockEntityRenderer<ImageTileEntity> {

    public ImageLoaderRender(BlockEntityRendererProvider.Context pContextd) {
        
    }

    @Override
    public void render(ImageTileEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
    	float rotationYaw = 0.0f;
    	float rotationPitch = 0.0f;
    	int blockRotation = pBlockEntity.getRotation();
    	int x = 505;
    	int y = 505;
    	
    	pBlockEntity.getImage();
    	RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, getImage(pBlockEntity));
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    	BufferBuilder buffer = Tesselator.getInstance().getBuilder();
        buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        buffer.vertex(x, y, 0).uv(0, 1).endVertex();
        buffer.vertex(x, y, 0).uv(1, 1).endVertex();
        buffer.vertex(x, y, 0).uv(1, 0).endVertex();
        buffer.vertex(x, y, 0).uv(0, 0).endVertex();
        BufferUploader.drawWithShader(buffer.end());
    }
    
    public ResourceLocation getImage(ImageTileEntity tile) {
    	return IOUtils.readImage("http://130.61.36.120/docteam/img/" + tile.getImageName() + ".png");
    }
    
}
