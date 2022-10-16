package be.ninedocteur.docmod.proxy;

import be.ninedocteur.docmod.DMConfig;
import be.ninedocteur.docmod.client.TileRenders;
import be.ninedocteur.docmod.client.containers.DMContainers;
import be.ninedocteur.docmod.client.event.ClientEventHandler;
import be.ninedocteur.docmod.client.gui.overlay.DocModVersionOverlay;
import be.ninedocteur.docmod.client.gui.screens.DMComputerHardwareScreen;
import be.ninedocteur.docmod.client.gui.screens.DMReportBug;
import be.ninedocteur.docmod.client.gui.screens.DocModConfigScreen;
import be.ninedocteur.docmod.client.gui.screens.InfusionScreen;
import be.ninedocteur.docmod.client.gui.screens.block.ZurbTeleporterScreen;
import be.ninedocteur.docmod.client.gui.title.DMLoadingTitleScreen;
import be.ninedocteur.docmod.common.init.*;
import be.ninedocteur.docmod.common.listeners.DMListeners;
import be.ninedocteur.docmod.utils.DMRPC;
import be.ninedocteur.docteam.installer.DevWarn;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.CLIENT)
public class ClientProxy {

    public static void doClientEvents(IEventBus modBus, IEventBus forgeBus){
        modBus.addListener(ClientProxy::doClientStuff);
        forgeBus.addListener(DocModVersionOverlay::render);
        forgeBus.addListener(DMLoadingTitleScreen::loadDMTitleScreen);
        forgeBus.addListener(DMListeners::onPLayerLevelJoin);
        forgeBus.addListener(DMReportBug::initScreen);
        //forgeBus.addListener(DMOreFeatures::addFeatures);
        forgeBus.register(new ClientEventHandler());
    }

    private static void doClientStuff(FMLClientSetupEvent event) {
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory((mc, screen) -> new DocModConfigScreen(screen)));
        TileRenders.register();
        Sheets.addWoodType(DMWoodTypes.DEATH);
        Sheets.addWoodType(DMWoodTypes.ALBIZIA);

        MenuScreens.register(DMContainers.ZURBTELEPORTERCONTAINER.get(), ZurbTeleporterScreen::new);
        MenuScreens.register(DMMenu.COMPUTER.get(), DMComputerHardwareScreen::new);
        MenuScreens.register(DMMenu.INFUSION.get(), InfusionScreen::new);

        ItemBlockRenderTypes.setRenderLayer(DMBlocks.CIRCLE_GLASS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.KILLER_BLOCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.DEATH_LEAVES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.CIRCLE_GLASS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.KILLER_BLOCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.DEATH_LEAVES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.COUPE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.ALBIZIA_LEAVES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.NINEDOCTEUR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.LIGHT_BLOCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.ELECTRONIC_VINE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.CHRISTMAS_TREE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.SNOW_BALL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(Block.byItem(DMItems.DALEK_LASER.get()), RenderType.cutout());
        //ItemBlockRenderTypes.setRenderLayer(DMFluids.SOURCE_GAZOLINE.get(), RenderType.translucent());
        //ItemBlockRenderTypes.setRenderLayer(DMFluids.FLOWING_SOURCE_GAZOLINE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.CAKE.get(), RenderType.cutout());

        DMRPC.startRPC();
    }
}
