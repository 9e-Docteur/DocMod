package fr.ninedocteur.docmod.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import be.ninedocteur.docmod.utils.DMRPC;

@Environment(EnvType.CLIENT)
public class DocModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        DMRPC.startRPC();
    }
}
