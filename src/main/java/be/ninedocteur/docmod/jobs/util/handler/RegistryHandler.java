package be.ninedocteur.docmod.jobs.util.handler;

import be.ninedocteur.docmod.jobs.commands.CommandAdd;
import be.ninedocteur.docmod.jobs.commands.CommandInfo;
import be.ninedocteur.docmod.jobs.commands.CommandSet;
import be.ninedocteur.docmod.jobs.events.client.GuiEvents;
import be.ninedocteur.docmod.jobs.events.server.BreakBlockEvents;
import be.ninedocteur.docmod.jobs.events.server.CommonEvents;
import be.ninedocteur.docmod.jobs.events.server.CraftItemEvents;
import be.ninedocteur.docmod.jobs.events.server.KillEntityEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class RegistryHandler {

	@SubscribeEvent
	public void onCommandsRegistered(RegisterCommandsEvent event)
	{
		CommandInfo.register(event.getDispatcher());
		CommandSet.register(event.getDispatcher());
		CommandAdd.register(event.getDispatcher());
	}


	
	public static void registerListeners()
	{
		MinecraftForge.EVENT_BUS.register(new GuiEvents());
		MinecraftForge.EVENT_BUS.register(new RegistryHandler());
		MinecraftForge.EVENT_BUS.register(new CommonEvents());
		MinecraftForge.EVENT_BUS.register(new BreakBlockEvents());
		MinecraftForge.EVENT_BUS.register(new KillEntityEvent());
		MinecraftForge.EVENT_BUS.register(new CraftItemEvents());
	}

}
