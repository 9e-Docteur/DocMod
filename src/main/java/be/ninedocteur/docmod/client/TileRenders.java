package be.ninedocteur.docmod.client;

import be.ninedocteur.docmod.client.render.*;
import be.ninedocteur.docmod.common.init.DMTileEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TileRenders {

    @OnlyIn(Dist.CLIENT)
    public static void register() {
        BlockEntityRenderers.register(DMTileEntity.Toyota.get(), ToyotaRotorBlockRender::new);
        BlockEntityRenderers.register(DMTileEntity.Tardis.get(), TardisRender::new);
        BlockEntityRenderers.register(DMTileEntity.GlassTile.get(), GlassTubeRenderer::new);
        BlockEntityRenderers.register(DMTileEntity.ZurbTeleporterTile.get(), ZurbTeleporterRender::new);
        BlockEntityRenderers.register(DMTileEntity.RedGlassTile.get(), RedGlassTubeRenderer::new);
        BlockEntityRenderers.register(DMTileEntity.OrangeGlassTile.get(), OrangeGlassTubeRenderer::new);
        BlockEntityRenderers.register(DMTileEntity.GreenGlassTile.get(), GreenGlassTubeRenderer::new);
        BlockEntityRenderers.register(DMTileEntity.DeathSign.get(), SignRenderer::new);
        BlockEntityRenderers.register(DMTileEntity.AlbiziaSign.get(), SignRenderer::new);
        //BlockEntityRenderers.register(DMTileEntity.item_shower.get(), ItemShowerBlockRender::new);
        BlockEntityRenderers.register(DMTileEntity.red_toyota.get(), RedToyotaRotorBlockRender::new);
        BlockEntityRenderers.register(DMTileEntity.DAMAGED_DALEK_TILE_ENTITY.get(), DamagedDalekBlockRender::new);
       // BlockEntityRenderers.register(DMTileEntity.COMPUTER_TILE_ENTITY.get(), ComputerBlockRender::new);
        //BlockEntityRenderers.register(DMTileEntity.MONITOR_TILE_ENTITY.get(), MonitorBlockRender::new);
        BlockEntityRenderers.register(DMTileEntity.CHAIR_TILE_ENTITY.get(), ChairBlockRender::new);

    }
}
