package fr.ninedocteur.docmod.client;

import fr.ninedocteur.docmod.client.render.GlassTubeRenderer;
import fr.ninedocteur.docmod.common.init.DMTileEntities;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.GlassBlock;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class BlockEntityRenders {

    @Environment(EnvType.CLIENT)
    public static void register() {
        BlockEntityRendererFactories.register(DMTileEntities.GlassTile, GlassTubeRenderer::new);
    }
}
