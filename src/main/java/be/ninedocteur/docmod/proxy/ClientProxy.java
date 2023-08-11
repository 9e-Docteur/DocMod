package be.ninedocteur.docmod.proxy;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.TileRenders;
import be.ninedocteur.docmod.common.init.*;
import be.ninedocteur.docmod.utils.DMRPC;
import be.ninedocteur.docmod.utils.LaunchUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.resources.ResourceLocation;
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
        DocMod.LOGGER.info("Init DocMod Menus...");
        DMMenu.register(modBus);
    }

    private static void doClientStuff(FMLClientSetupEvent event) {
        TileRenders.register();
        Sheets.addWoodType(DMWoodTypes.DEATH);
        Sheets.addWoodType(DMWoodTypes.ALBIZIA);

        ItemBlockRenderTypes.setRenderLayer(DMBlocks.CIRCLE_GLASS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.KILLER_BLOCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.DEATH_LEAVES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.CIRCLE_GLASS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.KILLER_BLOCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.DEATH_LEAVES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.COUPE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.ALBIZIA_LEAVES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.NINEDOCTEUR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.ELECTRONIC_VINE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.CHRISTMAS_TREE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.SNOW_BALL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(Block.byItem(DMItems.DALEK_LASER.get()), RenderType.cutout());
        //ItemBlockRenderTypes.setRenderLayer(DMFluids.SOURCE_GAZOLINE.get(), RenderType.translucent());
        //ItemBlockRenderTypes.setRenderLayer(DMFluids.FLOWING_SOURCE_GAZOLINE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(DMBlocks.CAKE.get(), RenderType.cutout());

        LaunchUtils.initWindowIcon(new ResourceLocation(DocMod.MOD_ID, "icons/icon16x16.png"), new ResourceLocation(DocMod.MOD_ID, "icons/icon32x32.png"));

        DMRPC.startRPC();
    }
}
