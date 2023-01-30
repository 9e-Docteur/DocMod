package fr.ninedocteur.docmod.client;

import fr.ninedocteur.docmod.utils.DMRPC;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class DocModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        DMRPC.startRPC();
    }
}
