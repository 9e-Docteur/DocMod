package be.ninedocteur.docmod.client;

import be.ninedocteur.docmod.client.render.*;
import be.ninedocteur.docmod.common.init.DMTileEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TileRenders {

    @OnlyIn(Dist.CLIENT)
    public static void register() {
        BlockEntityRenderers.register(DMTileEntity.Toyota.get(), ToyotaRotorBlockRender::new);
        BlockEntityRenderers.register(DMTileEntity.GlassTile.get(), GlassTubeRenderer::new);
        BlockEntityRenderers.register(DMTileEntity.ZurbTeleporterTile.get(), ZurbTeleporterRender::new);
        BlockEntityRenderers.register(DMTileEntity.RedGlassTile.get(), RedGlassTubeRenderer::new);
        BlockEntityRenderers.register(DMTileEntity.OrangeGlassTile.get(), OrangeGlassTubeRenderer::new);
        BlockEntityRenderers.register(DMTileEntity.GreenGlassTile.get(), GreenGlassTubeRenderer::new);
        BlockEntityRenderers.register(DMTileEntity.item_shower.get(), ItemShowerBlockRender::new);
        BlockEntityRenderers.register(DMTileEntity.red_toyota.get(), RedToyotaRotorBlockRender::new);
        BlockEntityRenderers.register(DMTileEntity.CHAIR_TILE_ENTITY.get(), ChairBlockRender::new);
        //BlockEntityRenderers.register(DMTileEntity.SAFECHEST.get(), SafeChestRender::new);
        BlockEntityRenderers.register(DMTileEntity.HOLOGRAM.get(), HologramRender::new);
        BlockEntityRenderers.register(DMTileEntity.HARTNELL.get(), HartnellRender::new);
        BlockEntityRenderers.register(DMTileEntity.IMAGE.get(), ImageLoaderRender::new);
        BlockEntityRenderers.register(DMTileEntity.ENDER_TILE.get(), EnderPadRenderer::new);
        BlockEntityRenderers.register(DMTileEntity.LIGHT_SENSOR.get(), LightSensorRender::new);
        BlockEntityRenderers.register(DMTileEntity.GRASS_TILE.get(), GrassRenderer::new);
    }
}
