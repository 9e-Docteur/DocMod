package be.ninedocteur.docmod.jobs.events;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.jobs.data.ServerJobsData;
import be.ninedocteur.docmod.jobs.data.capabilities.PlayerData;
import be.ninedocteur.docmod.jobs.data.capabilities.PlayerJobs;
import be.ninedocteur.docmod.jobs.network.PacketAskClientUpdate;
import be.ninedocteur.docmod.jobs.util.config.ReadConfigManager;
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
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

import java.util.Optional;

@Mod.EventBusSubscriber(modid = DocMod.MOD_ID, bus = Bus.FORGE)
public class CommonEvents {

	@SubscribeEvent
    public static void onEntityCloned(PlayerEvent.Clone event) {
        if (!event.isWasDeath())
            return;
        Optional<PlayerJobs> capability = event.getOriginal()
                .getCapability(PlayerData.JOBS, null)
                .resolve();
        if (capability.isPresent()) {
            PlayerJobs old_jobs = capability.get();
            PlayerJobs new_jobs = PlayerData.getPlayerJobs(event.getEntity());
            new_jobs.copy(old_jobs);
        }

    }

    @SubscribeEvent
    public static void onPlayerJoinedServer(EntityJoinLevelEvent event){
        if(event.getEntity() instanceof ServerPlayer player) {
	        ReadConfigManager.readConfigFiles(player.getServer());
	        ServerJobsData.sendDataToClient(player);
        }
    }

    /**
     * Fired when a player joins a world (client side) : asks the data to the server.
     * @param event the Join World Event
     */
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onPlayerJoinedClient(EntityJoinLevelEvent event) {
        if(!(event.getEntity() instanceof AbstractClientPlayer))
            return;
        DocMod.LOGGER.info("Send player capabilities to server!");
        PacketHandler.INSTANCE.sendToServer(PacketAskClientUpdate.instance);
    }

    @SubscribeEvent
    public static void onEntityCreating(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject().level.isClientSide)
            return;
        if(!(event.getObject() instanceof Player))
            return;
        DocMod.LOGGER.warn("Get player capabilities!");
        Optional<PlayerJobs> capability = event.getObject()
                .getCapability(PlayerData.JOBS, null)
                .resolve();
        if(!capability.isPresent()) {
            event.addCapability(new ResourceLocation(DocMod.MOD_ID, "jobs"),
                    new PlayerData.JobsDispatcher(ServerJobsData.JOBS_LEVELS));
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerJobs.class);
    }
}
