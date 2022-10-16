package be.ninedocteur.docmod.client.render;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.BaseItemShower;
import be.ninedocteur.docmod.client.models.ItemShowerModel;
import be.ninedocteur.docmod.client.models.ToyotaRotorModel;
import be.ninedocteur.docmod.common.tileentity.ItemShowerTile;
import be.ninedocteur.docmod.common.tileentity.ToyotaRotorTileEntity;
import be.ninedocteur.docmod.registry.ModelRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BaseSpawner;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ItemShowerBlockRender implements BlockEntityRenderer<ItemShowerTile> {


    public static ItemShowerModel model;

    public ItemShowerBlockRender(BlockEntityRendererProvider.Context pContextd) {
        model = new ItemShowerModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelRegistry.ITEM_SHOWER));
    }

    @Override
    public void render(ItemShowerTile pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        pPoseStack.pushPose();
        pPoseStack.translate(0.5D, 1.5D, 0.5D);
        pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(180));
        pPoseStack.popPose();


        //pPoseStack.pushPose();
        final LocalPlayer player = Minecraft.getInstance().player;
        final ItemStack setIn = player.getMainHandItem();
        final ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        itemRenderer.renderStatic(player, setIn, ItemTransforms.TransformType.FIXED, false, pPoseStack, pBufferSource, Minecraft.getInstance().level, pPackedOverlay, pPackedLight, 0);
        model.renderToBuffer(pPoseStack, pBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DocMod.MOD_ID, "textures/block/item_shower.png"))), pPackedLight, pPackedOverlay, 1,1,1,1);
        //pPoseStack.translate(0D, -1D, 0D);
        //pPoseStack.mulPose(Vector3f.XN.rotationDegrees(180));
       // pPoseStack.popPose();
    }
}
