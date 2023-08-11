package be.ninedocteur.docmod.client.render;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.models.ItemShowerModel;
import be.ninedocteur.docmod.common.tileentity.ItemShowerTile;
import be.ninedocteur.docmod.registry.ModelRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Matrix4f;
import org.joml.Vector3d;

@OnlyIn(Dist.CLIENT)
public class ItemShowerBlockRender implements BlockEntityRenderer<ItemShowerTile> {


    public static ItemShowerModel model;
    public int tick;
    public int itemRotation;

    public ItemShowerBlockRender(BlockEntityRendererProvider.Context pContextd) {
        model = new ItemShowerModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelRegistry.ITEM_SHOWER));
    }

    @Override
    public void render(ItemShowerTile pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        tick();
        final LocalPlayer player = Minecraft.getInstance().player;
        //final ItemStack setIn = player.getMainHandItem();
        final ItemStack setIn = pBlockEntity.itemIn;
        final ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        pPoseStack.pushPose();
        pPoseStack.translate(0.5D, 1.5D, 0.5D);
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(180));
        model.renderToBuffer(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/item_shower.png"))), pPackedLight, pPackedOverlay, 1,1,1,1);
        pPoseStack.popPose();
        pPoseStack.pushPose();
        pPoseStack.translate(0.5D, 0.5D, 0.5D);
        pPoseStack.scale(0.5f, 0.5f, 0.5f);
        //itemRenderer.renderStatic(setIn, ItemTransforms.TransformType.FIXED, getLightLevel(Minecraft.getInstance().level, pBlockEntity.getBlockPos()), OverlayTexture.NO_OVERLAY, pPoseStack, pBufferSource, 1);
        pPoseStack.mulPose(Axis.XP.rotationDegrees(itemRotation));
        pPoseStack.popPose();
        Component label = setIn.getHoverName();
        if(label != Component.literal("Air")){
            renderLabel(pPoseStack, pBufferSource, LightTexture.FULL_BRIGHT, new Vector3d(0.5, 1, 0.5), label, 0xffffff);
        }
    }

    public void tick(){

    }

    private void renderLabel(PoseStack stack, MultiBufferSource buffer, int lightLevel, Vector3d corner, Component text, int color) {
        Font font = Minecraft.getInstance().font;
        float scale = 0.025f;
        int opacity = (int) (.4f * 255.0f) << 24;
        float offset = (float) (-font.width(text) / 2);
        stack.pushPose();
        Matrix4f matrix = stack.last().pose();
        stack.translate(corner.x, corner.y + .4f, corner.z);
        stack.scale(scale, scale, scale);
        stack.mulPose(Axis.YP.rotationDegrees(-Minecraft.getInstance().player.getYRot()));
        stack.mulPose(Axis.ZP.rotationDegrees(180f));
        //font.drawInBatch(text, offset, 0, color, false, matrix, buffer, false, opacity, lightLevel);
        stack.popPose();
    }

    private int getLightLevel(Level level, BlockPos pos){
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}
