package be.ninedocteur.docmod.jobs.util.handler;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.jobs.commands.CommandAdd;
import be.ninedocteur.docmod.jobs.commands.CommandInfo;
import be.ninedocteur.docmod.jobs.commands.CommandSet;
import be.ninedocteur.docmod.jobs.events.CommonEvents;
import be.ninedocteur.docmod.jobs.events.client.GuiEvents;
import be.ninedocteur.docmod.jobs.events.server.BlockInteractionEvents;
import be.ninedocteur.docmod.jobs.events.server.EntityInteractionEvents;
import be.ninedocteur.docmod.jobs.events.server.ItemInteractionEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Mod.EventBusSubscriber(modid = DocMod.MOD_ID)
public class RegistryHandler {

	/**
	 * Registers the Commands of the mod
	 * @param event the Register Event
	 */
	@SubscribeEvent
	public void onCommandsRegistered(RegisterCommandsEvent event) {
		CommandInfo.register(event.getDispatcher());
		CommandSet.register(event.getDispatcher());
		CommandAdd.register(event.getDispatcher());
		DocMod.LOGGER.info("Commands Registered", false);
	}

	/**
	 * Registers the Event Listeners of the mod
	 */
	public static void registerListeners() {
		MinecraftForge.EVENT_BUS.register(new GuiEvents());
		MinecraftForge.EVENT_BUS.register(new RegistryHandler());
		MinecraftForge.EVENT_BUS.register(new CommonEvents());
		MinecraftForge.EVENT_BUS.register(new BlockInteractionEvents());
		MinecraftForge.EVENT_BUS.register(new EntityInteractionEvents());
		MinecraftForge.EVENT_BUS.register(new ItemInteractionEvents());
	}

}
