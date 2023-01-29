package fr.ninedocteur.docmod;

import fr.ninedocteur.docmod.common.init.DMBlocks;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DocMod implements ModInitializer {
    public static final String MOD_ID = "docmod";
    private static final Logger LOGGER = LoggerFactory.getLogger("docmod");
    @Override
    public void onInitialize() {
        LOGGER.info("Starting DocMod...");

        DMBlocks.register();
    }
}
