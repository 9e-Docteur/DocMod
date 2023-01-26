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
import net.minecraft.core.registries.Registries;
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
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;
import java.util.concurrent.Executor;
import java.util.function.BiFunction;

public class DMDimension {
    public static final ResourceKey<Level> MOON_KEY = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(DocMod.MOD_ID, "moon"));
    public static final ResourceKey<Level> TARDIS_KEY = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(DocMod.MOD_ID, "tardis"));
    public static final ResourceKey<Level> SPACE_KEY = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(DocMod.MOD_ID, "space"));
    public static final ResourceKey<DimensionType> MOON_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, MOON_KEY.registry());
    public static final ResourceKey<DimensionType> TARDIS_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, TARDIS_KEY.registry());
    public static final ResourceKey<DimensionType> SPACE_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, SPACE_KEY.registry());


    public static void register(){
        DocMod.LOGGER.info("Init DocMod Dimension...");
    }
}
