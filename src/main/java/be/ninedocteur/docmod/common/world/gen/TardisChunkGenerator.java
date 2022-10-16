package be.ninedocteur.docmod.common.world.gen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryOps;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.*;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.structure.StructureSet;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class TardisChunkGenerator extends ChunkGenerator {
    public static final ChunkPos CHUNKPOS = new ChunkPos(16,16);
    public static final long CHUNKID = CHUNKPOS.toLong();
    public static final BlockPos CORNER = CHUNKPOS.getWorldPosition();
    public static final BlockPos CENTER = CORNER.offset(7, 7, 7);


    public static final Codec<TardisChunkGenerator> providerCodec = RecordCodecBuilder.create(builder -> builder.group(
            RegistryOps.retrieveRegistry(Registry.STRUCTURE_SET_REGISTRY).forGetter(TardisChunkGenerator::getStructureSetRegistry),
            RegistryOps.retrieveRegistry(Registry.BIOME_REGISTRY).forGetter(TardisChunkGenerator::getBiomeRegistry)
    ).apply(builder, TardisChunkGenerator::new));

    private final Registry<Biome> biomeRegistry;
    private final Registry<StructureSet> structureSets;

    // create chunk generator at runtime when dynamic dimension is created
    public TardisChunkGenerator(MinecraftServer server)
    {
        this(server.registryAccess().registryOrThrow(Registry.STRUCTURE_SET_REGISTRY),
                server.registryAccess() // get dynamic registry
                        .registryOrThrow(Registry.BIOME_REGISTRY));
    }

    public TardisChunkGenerator(Registry<StructureSet> structureSets, Registry<Biome> biomes){
        super(structureSets, Optional.empty(), new FixedBiomeSource(biomes.getHolderOrThrow(Biomes.THE_VOID)));
        this.structureSets = structureSets;
        this.biomeRegistry = biomes;
    }

    @Override
    protected Codec<? extends ChunkGenerator> codec() {
        return providerCodec;
    }

    @Override
    public void applyCarvers(WorldGenRegion worldGenRegion, long l, RandomState randomState, BiomeManager biomeManager, StructureManager structureManager, ChunkAccess chunkAccess, GenerationStep.Carving carving) {

    }


    public Registry<Biome> getBiomeRegistry(){
        return this.biomeRegistry;
    }

    @Override
    public int getGenDepth(){
        return 319;// total number of available y-levels (between bottom and top)
    }


    @Override
    public void spawnOriginalMobs(WorldGenRegion region) {
    }


    @Override
    public int getSeaLevel() {
        return 0;
    }

    @Override
    public int getMinY() {
        // the lowest y-level in the dimension
        // debug -> 0
        // flat -> 0
        // noise -> NoiseSettings#minY
        // overworld -> -64
        // nether -> 0
        return -64;
    }

    @Override
    public int getBaseHeight(int i, int i1, Heightmap.Types types, LevelHeightAccessor levelHeightAccessor, RandomState randomState) {
        return 0;
    }

    @Override
    public NoiseColumn getBaseColumn(int i, int i1, LevelHeightAccessor levelHeightAccessor, RandomState randomState) {
        return null;
    }

    @Override
    public void addDebugScreenInfo(List<String> list, RandomState randomState, BlockPos blockPos) {

    }

    @Override
    public void applyBiomeDecoration(WorldGenLevel world, ChunkAccess chunkAccess, StructureManager structures){
        // noop
    }

    @Override
    public void buildSurface(WorldGenRegion worldGenRegion, StructureManager structureManager, RandomState randomState, ChunkAccess chunkAccess) {

    }

    @Override
    public int getSpawnHeight(LevelHeightAccessor level)
    {
        return 1;
    }

    // create structure references
    @Override
    public void createReferences(WorldGenLevel world, StructureManager structures, ChunkAccess chunk){
        // no structures
    }

    @Override
    public CompletableFuture<ChunkAccess> fillFromNoise(Executor executor, Blender blender, RandomState randomState, StructureManager structureManager, ChunkAccess chunkAccess) {
        return null;
    }

    public Registry<StructureSet> getStructureSetRegistry() {
        return this.structureSets;
    }



}
