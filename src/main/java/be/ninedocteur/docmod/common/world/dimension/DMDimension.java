package be.ninedocteur.docmod.common.world.dimension;

import be.ninedocteur.docmod.DMConfig;
import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.world.gen.TardisChunkGenerator;
import be.ninedocteur.docmod.network.Network;
import be.ninedocteur.docmod.network.packet.SynchroniseDimensionPacket;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Lifecycle;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.WritableRegistry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.progress.ChunkProgressListener;
import net.minecraft.server.level.progress.ChunkProgressListenerFactory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.border.BorderChangeListener;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.WorldGenSettings;
import net.minecraft.world.level.storage.DerivedLevelData;
import net.minecraft.world.level.storage.LevelStorageSource;
import net.minecraft.world.level.storage.WorldData;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

import java.util.Map;
import java.util.concurrent.Executor;
import java.util.function.BiFunction;

public class DMDimension {
    public static final ResourceKey<Level> MOON_KEY = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(DocMod.MOD_ID, "moon"));
    public static final ResourceKey<Level> TARDIS_KEY = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(DocMod.MOD_ID, "tardis"));

    public static final ResourceKey<DimensionType> MOON_TYPE = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, MOON_KEY.registry());
    public static final ResourceKey<DimensionType> TARDIS_TYPE = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, TARDIS_KEY.registry());

    public static LevelStem tardisDimensionBuilder(MinecraftServer server, ResourceKey<LevelStem> resourceKey){
        long seed = server.getLevel(Level.OVERWORLD).getSeed() + resourceKey.location().hashCode();
        RegistryAccess registryAccess = server.registryAccess();
        return new LevelStem(registryAccess.registryOrThrow(Registry.DIMENSION_TYPE_REGISTRY).getHolderOrThrow(TARDIS_TYPE), new TardisChunkGenerator(server));
    }

    public static ServerLevel getOrCreateWorld(MinecraftServer server, ResourceKey<Level> worldKey, BiFunction<MinecraftServer, ResourceKey<LevelStem>, LevelStem> biFunction){
        Map<ResourceKey<Level>, ServerLevel> serverLevelMap = server.forgeGetWorldMap();
        if(serverLevelMap.containsKey(worldKey)){
            return serverLevelMap.get(worldKey);
        }
        ServerLevel newWorld = createDynamicLevel(server, serverLevelMap, worldKey, biFunction);
        return newWorld;
    }

    public static ServerLevel createLevel(MinecraftServer server, Map<ResourceKey<Level>, ServerLevel> map, ResourceKey<Level> worldKey, BiFunction<MinecraftServer, ResourceKey<LevelStem>, LevelStem> biFunction){
        ServerLevel overworld = server.getLevel(Level.OVERWORLD);
        ResourceKey<LevelStem> dimKey = ResourceKey.create(Registry.LEVEL_STEM_REGISTRY, worldKey.location());
        LevelStem dimension = biFunction.apply(server, dimKey);
        ChunkProgressListener chunkProgressListener = server.progressListenerFactory.create(11);
        Executor executor = server.executor;
        LevelStorageSource.LevelStorageAccess storageAccess = server.storageSource;
        WorldData worldData = server.getWorldData();
        WorldGenSettings worldGenSettings = worldData.worldGenSettings();
        Registry<LevelStem> dimensionRegistry = worldGenSettings.dimensions();

        if(dimensionRegistry instanceof WritableRegistry<LevelStem> levelStemWritableRegistry){
            levelStemWritableRegistry.register(dimKey, dimension, Lifecycle.stable());
        } else {
            throw new IllegalStateException("Failed to register the dimension " + dimKey.location());
        }

        DerivedLevelData derivedLevelData = new DerivedLevelData(worldData, worldData.overworldData());
        ServerLevel serverLevel = new ServerLevel(server, executor, storageAccess, derivedLevelData, worldKey, dimension, chunkProgressListener, worldGenSettings.isDebug(), BiomeManager.obfuscateSeed(worldGenSettings.seed()), ImmutableList.of(), false);
        overworld.getWorldBorder().addListener(new BorderChangeListener.DelegateBorderChangeListener(serverLevel.getWorldBorder()));
        map.put(worldKey, serverLevel);
        server.markWorldsDirty();
        MinecraftForge.EVENT_BUS.post(new LevelEvent.Load(serverLevel));
        return serverLevel;

    }

    private static ServerLevel createDynamicLevel(MinecraftServer server, Map<ResourceKey<Level>, ServerLevel> map, ResourceKey<Level> worldKey, BiFunction<MinecraftServer, ResourceKey<LevelStem>, LevelStem> biFunction){
        ServerLevel serverLevel = createLevel(server, map, worldKey, biFunction);
        Network.sendPacketToAll(new SynchroniseDimensionPacket(worldKey, true));
        return serverLevel;
    }



    public static void register(){
        DocMod.LOGGER.info("Init DocMod Dimension...");
    }
}
