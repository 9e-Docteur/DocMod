package fr.ninedocteur.docmod.client;

import fr.ninedocteur.docmod.client.models.GlassTubeModel;
import fr.ninedocteur.docmod.registry.ModelRegistry;
import fr.ninedocteur.docmod.utils.DMRPC;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;

@Environment(EnvType.CLIENT)
public class DocModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRenders.register();

        EntityModelLayerRegistry.registerModelLayer(ModelRegistry.Glass, GlassTubeModel::createBodyLayer);

        DMRPC.startRPC();
    }
}
