package be.ninedocteur.docmod.jobs.events.server;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.jobs.data.GainXPUtil;

import be.ninedocteur.docmod.jobs.data.JobsInfo;
import be.ninedocteur.docmod.jobs.data.PlayerData;
import be.ninedocteur.docmod.jobs.network.PacketAskClientUpdate;
import be.ninedocteur.docmod.jobs.network.PacketUpdateClientJob;
import be.ninedocteur.docmod.jobs.util.handler.PacketHandler;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.network.NetworkDirection;

import java.util.Optional;

@EventBusSubscriber
public class CommonEvents {

	@SubscribeEvent
	public static void onEntityCreating(AttachCapabilitiesEvent<Entity> event) {
		if(event.getObject().level.isClientSide)
			return;
		if(!(event.getObject() instanceof Player))
			return;
		DocMod.LOGGER.warn("Get player capabilities!");
		Optional<JobsInfo> capability = event.getObject()
				.getCapability(PlayerData.JOBS, null)
				.resolve();
		if(!capability.isPresent()) {
			event.addCapability(new ResourceLocation(DocMod.MOD_ID, "jobs"),
					new PlayerData.JobsDispatcher());
		}
	}

	@SubscribeEvent
	public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
		event.register(JobsInfo.class);
	}

	@SubscribeEvent
	public static void onEntityCloned(PlayerEvent.Clone event) {
		if (!event.isWasDeath())
			return;
		Optional<JobsInfo> capability = event.getOriginal()
				.getCapability(PlayerData.JOBS, null)
				.resolve();
		if (capability.isPresent()) {
			JobsInfo old_jobs = capability.get();
			JobsInfo new_jobs = PlayerData.getPlayerJobs(event.getEntity());
			new_jobs.copy(old_jobs);
		}

	}

	@SubscribeEvent
	public void onPlayerJoined(EntityJoinLevelEvent event)
	{
		if(!(event.getEntity() instanceof ServerPlayer serverPlayer)) return;
		GainXPUtil.sendDataToClient((ServerPlayer)event.getEntity());
		PacketHandler.INSTANCE.sendTo(new PacketUpdateClientJob(PlayerData.getPlayerJobs(serverPlayer).toTotalXPs()), serverPlayer.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
	}
}
