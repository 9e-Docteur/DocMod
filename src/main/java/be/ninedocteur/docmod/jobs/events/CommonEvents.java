package be.ninedocteur.docmod.jobs.events;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.jobs.data.ServerJobsData;
import be.ninedocteur.docmod.jobs.data.capabilities.PlayerData;
import be.ninedocteur.docmod.jobs.data.capabilities.PlayerJobs;
import be.ninedocteur.docmod.jobs.network.PacketAskClientUpdate;
import be.ninedocteur.docmod.jobs.util.handler.PacketHandler;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.Optional;

@Mod.EventBusSubscriber(modid = DocMod.MOD_ID)
public class CommonEvents {

	/**
	 * Attaches the Jobs capability to a player if it is not already present
	 * @param event the Capability Event
	 */
	@SubscribeEvent
	public void onEntityCreating(AttachCapabilitiesEvent<Entity> event) {
		if(event.getObject().level.isClientSide)
			return;
		if(!(event.getObject() instanceof Player))
			return;
		Optional<PlayerJobs> capability = event.getObject()
											 .getCapability(PlayerData.JOBS, null)
											 .resolve();
		if(!capability.isPresent()) {
			event.addCapability(new ResourceLocation(DocMod.MOD_ID, "jobs"),
								new PlayerData.JobsDispatcher(ServerJobsData.JOBS_LEVELS));
		}
	}

	/**
	 * Fired when a player dies to attach his Jobs to him again, so that the progress is not lost.
	 * @param event Player Clone Event
	 */
	@SubscribeEvent
	public void onEntityCloned(PlayerEvent.Clone event) {
		if(!event.isWasDeath())
			return;
		Optional<PlayerJobs> capability = event.getOriginal()
											 .getCapability(PlayerData.JOBS, null)
											 .resolve();
		if(capability.isPresent()) {
			PlayerJobs old_jobs = capability.get();
			PlayerJobs new_jobs = PlayerData.getPlayerJobs(event.getEntity());
			new_jobs.copy(old_jobs);
		}
	}

	/**
	 * Fired when a player joins a world : send all the Jobs data to the Client Side.
	 * @param event the Join World Event
	 */
	@SubscribeEvent
	public void onPlayerJoinedServer(EntityJoinLevelEvent event){
		if(!(event.getEntity() instanceof ServerPlayer))
			return;
		ServerJobsData.sendDataToClient((ServerPlayer)event.getEntity());
	}

	/**
	 * Fired when a player joins a world (client side) : asks the data to the server.
	 * @param event the Join World Event
	 */
	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void onPlayerJoinedClient(EntityJoinLevelEvent event) {
		if(!(event.getEntity() instanceof AbstractClientPlayer))
			return;
		PacketHandler.INSTANCE.sendToServer(PacketAskClientUpdate.instance);
	}

}
