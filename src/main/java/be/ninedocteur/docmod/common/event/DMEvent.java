package be.ninedocteur.docmod.common.event;

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
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Optional;

public class DMEvent {
/*
    @SubscribeEvent
    public static void registerBiomeEvent(final BiomeLoadingEvent e){
        Decoration.addDecorations(e);
        Ores.addOres(e);
        DMEntityGeneration.onEntitySpawn(e);
    }

    public class Decoration{
     private static void addDecorations(final BiomeLoadingEvent e) {
            ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, e.getName());
            Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);
            List<Holder<PlacedFeature>> base = e.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION);
            if (types.contains(BiomeDictionary.Type.PLAINS)) {
             base.add(DMFeature.Placed.ALBIZIA_TREE_PLACED);
            }else if(types.contains(DMBiome.CLASSIC_BIOME)) {
             base.add(DMFeature.Placed.CLASSIC_TREE_PLACED);
         }
     }
    }
    public class Ores{
        private static void addOres(final BiomeLoadingEvent e) {
            List<Holder<PlacedFeature>> base = e.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);
            base.add(DMFeature.Placed.DEEP_ZINC_ORE_PLACED);
            base.add(DMFeature.Placed.ZINC_ORE_PLACED);
            base.add(DMFeature.Placed.NETHER_ZINC_ORE_PLACED);
            base.add(DMFeature.Placed.STEEL_ORE_PLACED);
            base.add(DMFeature.Placed.CRYSTALINE_ORE_PLACED);
            base.add(DMFeature.Placed.CRYSTAL_ORE_PLACED);
            base.add(DMFeature.Placed.HALFINUM_ORE_PLACED);
            base.add(DMFeature.Placed.CRYOLITE_ORE_PLACED);
        }
    }


 */


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
    public static void onPlayerJoinedClient(EntityJoinLevelEvent event) {
        if(!(event.getEntity() instanceof AbstractClientPlayer))
            return;
        PacketHandler.INSTANCE.sendToServer(PacketAskClientUpdate.instance);
    }

    @SubscribeEvent
    public static void onEntityCreating(AttachCapabilitiesEvent<Entity> event) {
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

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerJobs.class);
    }
}
