package be.ninedocteur.docmod.common.world.structures;

public class ClassicMansionStructure /*extends StructureFeature<JigsawConfiguration>*/ {
/*
    public ClassicMansionStructure() {
        super(JigsawConfiguration.CODEC, ClassicMansionStructure::createPiecesGenerator);
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    @Override
    public boolean canGenerate(RegistryAccess p_197172_, ChunkGenerator p_197173_, BiomeSource source, StructureManager p_197175_, long p_197176_, ChunkPos p_197177_, JigsawConfiguration p_197178_, LevelHeightAccessor p_197179_, Predicate<Holder<Biome>> p_197180_) {
        if(source.equals(DMBiomes.CLASSIC_KEY)){
            return true;
        }
        return false;
    }

    private static boolean isFeatureChunk(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {
        ChunkPos chunkpos = context.chunkPos();
        return !context.chunkGenerator().hasFeatureChunkInRange(BuiltinStructureSets.OCEAN_MONUMENTS, context.seed(), chunkpos.x, chunkpos.z, 10);
    }

    public static Optional<PieceGenerator<JigsawConfiguration>> createPiecesGenerator(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {
        if (!ClassicMansionStructure.isFeatureChunk(context)) {
            return Optional.empty();
        }
        BlockPos blockpos = context.chunkPos().getMiddleBlockPosition(0);
        int topLandY = context.chunkGenerator().getFirstFreeHeight(blockpos.getX(), blockpos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());
        blockpos = blockpos.above(topLandY);

        Optional<PieceGenerator<JigsawConfiguration>> structurePiecesGenerator =
                JigsawPlacement.addPieces(
                        context,
                        PoolElementStructurePiece::new,
                        blockpos,
                        false,
                        false
                );
        if(structurePiecesGenerator.isPresent()) {
            DocMod.LOGGER.debug("Rundown House at {}", blockpos);
        }
        return structurePiecesGenerator;
    }

 */
}
