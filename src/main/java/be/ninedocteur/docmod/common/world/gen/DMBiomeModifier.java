package be.ninedocteur.docmod.common.world.gen;

public class DMBiomeModifier /*implements BiomeModifier*/ {
    /*
    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        /*GENERATION*//*
        if(biome.is(BiomeTags.HAS_VILLAGE_PLAINS)){
            builder.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, DMFeature.Placed.ALBIZIA_TREE_PLACED);
        }
        if(biome.is(DMBiomes.CLASSIC_KEY)){
            builder.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, DMFeature.Placed.CLASSIC_TREE_PLACED);
        }
        builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, DMFeature.Placed.CRYOLITE_ORE_PLACED);
        builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, DMFeature.Placed.CRYSTALINE_ORE_PLACED);
        builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, DMFeature.Placed.DEEP_ZINC_ORE_PLACED);
        builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, DMFeature.Placed.CRYSTAL_ORE_PLACED);
        builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, DMFeature.Placed.HALFINUM_ORE_PLACED);
        builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, DMFeature.Placed.NETHER_ZINC_ORE_PLACED);
        builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, DMFeature.Placed.STEEL_ORE_PLACED);
        builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, DMFeature.Placed.ZINC_ORE_PLACED);

        /*MOB SPAWN*//*
        if(biome.is(BiomeTags.IS_OVERWORLD)){
            builder.getMobSpawnSettings().addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(DMEntityType.CYBERMAN.get(), 70, 2, 5));
        }
    }
/*
    @Override
    public Codec<? extends BiomeModifier> codec() {
        return DMBiomes.DM_BIOME_MODIFIER.get();
    }

 */
}
