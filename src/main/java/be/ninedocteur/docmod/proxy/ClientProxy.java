package be.ninedocteur.docmod.proxy;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.TileRenders;
import be.ninedocteur.docmod.client.gui.containers.DMContainers;
import be.ninedocteur.docmod.client.event.ClientEventHandler;
import be.ninedocteur.docmod.client.gui.overlay.DMSpaceSuitOverlay;
import be.ninedocteur.docmod.client.gui.overlay.DocModDebugOverlay;
import be.ninedocteur.docmod.client.gui.overlay.DocModSonicChargeOverlay;
import be.ninedocteur.docmod.client.gui.overlay.DocModVersionOverlay;
import be.ninedocteur.docmod.client.gui.screens.*;
import be.ninedocteur.docmod.client.gui.screens.block.ZurbTeleporterScreen;
import be.ninedocteur.docmod.client.gui.title.DMLoadingTitleScreen;
import be.ninedocteur.docmod.common.init.*;
import be.ninedocteur.docmod.common.listeners.DMListeners;
import be.ninedocteur.docmod.utils.DMRPC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.resource.PathPackResources;

@OnlyIn(Dist.CLIENT)
public class ClientProxy {

    public static void doClientEvents(IEventBus modBus, IEventBus forgeBus){
        modBus.addListener(ClientProxy::doClientStuff);
        forgeBus.addListener(DocModVersionOverlay::render);
        DocMod.LOGGER.warn("Loading title screen...");
        forgeBus.addListener(DMLoadingTitleScreen::loadDMTitleScreen);
        forgeBus.addListener(DMListeners::onPLayerLevelJoin);
        forgeBus.addListener(DMListeners::onPlayerUpdate);
        forgeBus.addListener(DMPauseButtons::initScreen);
        forgeBus.addListener(DocModDebugOverlay::render);
        forgeBus.addListener(DocModSonicChargeOverlay::render);
        forgeBus.addListener(DMSpaceSuitOverlay::render);
        forgeBus.addListener(ClientEventHandler::onKeyInput);
        modBus.addListener(ClientEventHandler::onKeyRegister);
        modBus.addListener(ClientProxy::addOldTexturePack);
        DocMod.LOGGER.info("Init DocMod Menus...");
        DMMenu.register(modBus);
        //forgeBus.addListener(DMOreFeatures::addFeatures);
        forgeBus.register(new ClientEventHandler());
    }
    
    public static void addOldTexturePack(AddPackFindersEvent event) {
    	if(event.getPackType() == PackType.CLIENT_RESOURCES) {
    		Path txtPath = ModList.get().getModFileById(DocMod.MOD_ID).getFile().findResource("olddocmodtextures");
    		Pack pack = Pack.readMetaAndCreate("docmod_old_resources", Component.literal("DocMod Old Textures"), false, path -> new PathPackResources(path, true, txtPath), PackType.CLIENT_RESOURCES, Pack.Position.TOP, PackSource.BUILT_IN);
    		event.addRepositorySource(cs -> cs.accept(pack));
    	}
    }

    private static void doClientStuff(FMLClientSetupEvent event) {
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory((mc, screen) -> new DocModConfigScreen(screen)));
        TileRenders.register();
//        Sheets.addWoodType(DMWoodTypes.DEATH);
//        Sheets.addWoodType(DMWoodTypes.ALBIZIA);
//
//        MenuScreens.register(DMContainers.ZURBTELEPORTERCONTAINER.get(), ZurbTeleporterScreen::new);
//        MenuScreens.register(DMMenu.COMPUTER.get(), DMComputerHardwareScreen::new);
//        MenuScreens.register(DMMenu.INFUSION.get(), InfusionScreen::new);
//        MenuScreens.register(DMContainers.SAFE_CHEST_CONTAINER.get(), SafeChestScreen::new);

        ItemBlockRenderTypes.setRenderLayer(DMBlocks.CIRCLE_GLASS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.KILLER_BLOCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.DEATH_LEAVES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.CIRCLE_GLASS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.KILLER_BLOCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.DEATH_LEAVES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.COUPE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.ALBIZIA_LEAVES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.NINEDOCTEUR.get(), RenderType.cutout());
        //ItemBlockRenderTypes.setRenderLayer(DMBlocks.LIGHT_BLOCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.ELECTRONIC_VINE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.CHRISTMAS_TREE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.SNOW_BALL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(Block.byItem(DMItems.DALEK_LASER.get()), RenderType.cutout());
        //ItemBlockRenderTypes.setRenderLayer(DMFluids.SOURCE_GAZOLINE.get(), RenderType.translucent());
        //ItemBlockRenderTypes.setRenderLayer(DMFluids.FLOWING_SOURCE_GAZOLINE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.CAKE.get(), RenderType.cutout());

        //LaunchUtils.initWindowIcon(new ResourceLocation(DocMod.MOD_ID, "icons/icon16x16.png"), new ResourceLocation(DocMod.MOD_ID, "icons/icon32x32.png"));
        setWindowIcon();

        DMRPC.startRPC();
    }
    
    public static void setWindowIcon() {
    	try {
    		ResourceLocation icon16 = new ResourceLocation("docmod", "icons/icon_16x16.png");
    		ResourceLocation icon32 = new ResourceLocation("docmod", "icons/icon_32x32.png");
    		InputStream icon1 = Minecraft.getInstance().getResourceManager().getResourceOrThrow(icon16).open();
    		InputStream icon2 = Minecraft.getInstance().getResourceManager().getResourceOrThrow(icon32).open();
    		Minecraft.getInstance().getWindow().setIcon(() -> icon1, () -> icon2);
    	}catch(IOException e) {
    		e.printStackTrace();
    		return;
    	}
    }
}
