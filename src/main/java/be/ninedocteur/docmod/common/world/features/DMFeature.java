package be.ninedocteur.docmod.common.world.features;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.init.DMBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.common.Tags;

import java.util.List;

public class DMFeature {

    private static final RuleTest NATURAL_NETHERRACK = new BlockMatchTest(Blocks.NETHERRACK);
    private static final RuleTest NATURAL_STONE = new TagMatchTest(Tags.Blocks.STONE);
    private static final RuleTest NATURAL_END_STONE = new TagMatchTest(Tags.Blocks.END_STONES);
    private static final RuleTest NATURAL_DEEPSLATE = new TagMatchTest(Tags.Blocks.COBBLESTONE_DEEPSLATE);

    public static final ResourceKey<PlacedFeature> ZINC_ORE_PLACED_KEY = createKey("overworld_zinc_ore_placed");
    public static final ResourceKey<PlacedFeature> HALFINUM_ORE_PLACED_KEY = createKey("overworld_zinc_ore_placed");
    public static final ResourceKey<PlacedFeature> CRYOLITE_ORE_PLACED_KEY = createKey("overworld_zinc_ore_placed");
    public static final ResourceKey<PlacedFeature> CRYSTALINE_ORE_PLACED_KEY = createKey("overworld_zinc_ore_placed");
    public static final ResourceKey<PlacedFeature> CRYSTALPLACED_PLACED_KEY = createKey("overworld_zinc_ore_placed");
    public static final ResourceKey<PlacedFeature> STEEL_ORE_PLACED_KEY = createKey("overworld_zinc_ore_placed");
    public static final ResourceKey<PlacedFeature> XP_ORE_PLACED_KEY = createKey("overworld_zinc_ore_placed");

    public static final ResourceKey<PlacedFeature> CRYSTALINE_GEODEPLACED_PLACED_KEY = createKey("crystaline_geode_placed");


    public static final ResourceKey<PlacedFeature> ALBIZIA_TREE_PLACED_KEY = createKey("albizia_tree_placed");
    public static final ResourceKey<PlacedFeature> ALBIZIA_TREE_CHECKED_KEY = createKey("albizia_tree_check");


    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        
        register(context, ZINC_ORE_PLACED_KEY, configuredFeatures.getOrThrow(DMConfiguredFeature.OVERWORLD_ZINC_ORE_KEY), commonOrePlacement(7 /*Ore per chunk*/, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(32))));
        register(context, HALFINUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(DMConfiguredFeature.OVERWORLD_HALFINUM_ORE_KEY), commonOrePlacement(3, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(16))));
        register(context, CRYOLITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(DMConfiguredFeature.OVERWORLD_CRYOLITE_ORE_KEY), commonOrePlacement(9, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
        register(context, CRYSTALINE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(DMConfiguredFeature.OVERWORLD_CRYSTALINE_ORE_KEY), commonOrePlacement(8, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
        register(context, STEEL_ORE_PLACED_KEY, configuredFeatures.getOrThrow(DMConfiguredFeature.OVERWORLD_STEEL_KEY), commonOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(60))));
        register(context, CRYSTALPLACED_PLACED_KEY, configuredFeatures.getOrThrow(DMConfiguredFeature.OVERWORLD_CRYSTAL_KEY), commonOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(40))));
        register(context, XP_ORE_PLACED_KEY, configuredFeatures.getOrThrow(DMConfiguredFeature.OVERWORLD_XP_ORE_KEY), commonOrePlacement(8 /*Ore per chunk*/, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

        register(context, CRYSTALINE_GEODEPLACED_PLACED_KEY, configuredFeatures.getOrThrow(DMConfiguredFeature.CRYSTALINE_GEODE_KEY),
                List.of(RarityFilter.onAverageOnceEvery(50), InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(50)),
                        BiomeFilter.biome()));

        register(context, ALBIZIA_TREE_CHECKED_KEY, configuredFeatures.getOrThrow(DMConfiguredFeature.ALBIZIA_TREE_KEY),
                List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)));
        register(context, ALBIZIA_TREE_PLACED_KEY, configuredFeatures.getOrThrow(DMConfiguredFeature.ALBIZIA_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2)));
    }


    public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    public static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }


    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(DocMod.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}
