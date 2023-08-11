package be.ninedocteur.docmod.common.world.tardis;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.init.DMBiomes;
import be.ninedocteur.docmod.common.world.dimension.DMDimension;
import be.ninedocteur.docmod.mixin.MinecraftServerAccessor;
import be.ninedocteur.docmod.network.DMPackets;
import be.ninedocteur.docmod.network.packets.SyncDimensionListPacket;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Lifecycle;
import net.minecraft.core.Holder;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.progress.ChunkProgressListener;
import net.minecraft.world.RandomSequences;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.border.BorderChangeListener;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.storage.DerivedLevelData;
import net.minecraft.world.level.storage.LevelStorageSource;
import net.minecraft.world.level.storage.WorldData;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.level.LevelEvent;

import java.util.Map;
import java.util.concurrent.Executor;
import java.util.function.BiFunction;

public class LevelCreator {
    public static ServerLevel getOrCreateLevel(MinecraftServer server, ResourceLocation location) {
        return getOrCreateLevel(server, ResourceKey.create(Registries.DIMENSION, location), LevelCreator::createStem);
    }

    public static LevelStem createStem(MinecraftServer server, ResourceKey<LevelStem> stemKey) {
        RegistryAccess registries = server.registryAccess();
        Holder<Biome> defaultBiome = registries.registryOrThrow(Registries.BIOME).getHolderOrThrow(DMBiomes.SPACE_BIOME_KEY);
        Holder<NoiseGeneratorSettings> settings = registries.registryOrThrow(Registries.NOISE_SETTINGS).getHolderOrThrow(NoiseGeneratorSettings.OVERWORLD);
        LevelStem stem = new LevelStem(
                registries.registryOrThrow(Registries.DIMENSION_TYPE).getHolderOrThrow(DMDimension.TARDIS_TYPE),
                new NoiseBasedChunkGenerator(new FixedBiomeSource(defaultBiome), settings)
        );
        return stem;
    }

    public static ServerLevel getOrCreateLevel(
            MinecraftServer server, ResourceKey<Level> levelKey,
            BiFunction<MinecraftServer, ResourceKey<LevelStem>, LevelStem> dimensionFactory
    ) {
        Map<ResourceKey<Level>, ServerLevel> levelMap = server.forgeGetWorldMap();
        if(levelMap.containsKey(levelKey)) {
            return levelMap.get(levelKey);
        }
        return createLevel(server, levelMap, levelKey, dimensionFactory, true);
    }

    public static ServerLevel getOrCreateUnSyncLevel(
            MinecraftServer server, ResourceKey<Level> levelKey,
            BiFunction<MinecraftServer, ResourceKey<LevelStem>, LevelStem> dimensionFactory
    ) {
        Map<ResourceKey<Level>, ServerLevel> levelMap = server.forgeGetWorldMap();
        if(levelMap.containsKey(levelKey)) {
            return levelMap.get(levelKey);
        }
        return createLevel(server, levelMap, levelKey, dimensionFactory);
    }

    private static ServerLevel createLevel(
            MinecraftServer server, Map<ResourceKey<Level>, ServerLevel> levelMap,
            ResourceKey<Level> levelKey, BiFunction<MinecraftServer, ResourceKey<LevelStem>, LevelStem> dimensionFactory, boolean gotoList
    ) {
        ServerLevel newLevel = createLevel(server, levelMap, levelKey, dimensionFactory);
        DMPackets.sendPacketToAll(new SyncDimensionListPacket(levelKey, gotoList));
        return newLevel;
    }

    private static ServerLevel createLevel(
            MinecraftServer server, Map<ResourceKey<Level>, ServerLevel> levelMap,
            ResourceKey<Level> levelKey, BiFunction<MinecraftServer, ResourceKey<LevelStem>, LevelStem> dimensionFactory
    ) {
        DocMod.LOGGER.debug("Creating new dimension: '%s'", levelKey.location().toString());
        ServerLevel overworld = server.getLevel(Level.OVERWORLD);
        ResourceKey<LevelStem> dimensionKey = ResourceKey.create(Registries.LEVEL_STEM, levelKey.location());
        LevelStem dimension = dimensionFactory.apply(server, dimensionKey);

        ChunkProgressListener chunkListener = ((MinecraftServerAccessor) server).getProgressListenerFactory().create(11);
        Executor executor = ((MinecraftServerAccessor) server).getExecutor();
        LevelStorageSource.LevelStorageAccess levelAccess = ((MinecraftServerAccessor) server).getStorageSource();

        WorldData worldData = server.getWorldData();

        MappedRegistry<LevelStem> dimensionRegistry = (MappedRegistry<LevelStem>) server.registryAccess().registryOrThrow(Registries.LEVEL_STEM);
        dimensionRegistry.unfreeze();
        dimensionRegistry.register(dimensionKey, dimension, Lifecycle.stable());

        DerivedLevelData derivedLevelData = new DerivedLevelData(worldData, worldData.overworldData());
        long seed = BiomeManager.obfuscateSeed(worldData.worldGenOptions().seed());

        ServerLevel newLevel = new ServerLevel(
                server, executor, levelAccess, derivedLevelData, levelKey, dimension, chunkListener,
                worldData.isDebugWorld(), seed, ImmutableList.of(), false, new RandomSequences(seed)
        );

        overworld.getWorldBorder().addListener(new BorderChangeListener.DelegateBorderChangeListener(newLevel.getWorldBorder()));
        levelMap.put(levelKey, newLevel);
        server.markWorldsDirty();
        MinecraftForge.EVENT_BUS.post(new LevelEvent.Load(newLevel));
        DocMod.LOGGER.debug("Created new dimension: '%s'", levelKey.location().toString());
        return newLevel;
    }
}
