package fr.ninedocteur.docmod.client;

import fr.ninedocteur.docmod.client.models.GlassTubeModel;
import fr.ninedocteur.docmod.common.init.DMBlocks;
import fr.ninedocteur.docmod.registry.ModelRegistry;
import fr.ninedocteur.docmod.utils.DMRPC;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class DocModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRenders.register();

        EntityModelLayerRegistry.registerModelLayer(ModelRegistry.Glass, GlassTubeModel::createBodyLayer);

        BlockRenderLayerMap.INSTANCE.putBlock(DMBlocks.CIRCLE_GLASS, RenderLayer.getTranslucent());

        DMRPC.startRPC();
    }
}
