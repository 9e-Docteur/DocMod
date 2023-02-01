package fr.ninedocteur.docmod;

import fr.ninedocteur.docmod.common.init.DMBlocks;
import fr.ninedocteur.docmod.common.init.DMItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DocMod implements ModInitializer {
    public static final String MOD_ID = "docmod";
    public static final String MODNAME = "DocMod Fabric";
    public static final String VERSION = "6.2.1-FAB";

    public static final Logger LOGGER = LoggerFactory.getLogger("docmod");

    @Override
    public void onInitialize() {
        LOGGER.info("Starting DocMod...");

        DMBlocks.register();
        DMItems.register();
    }
}
