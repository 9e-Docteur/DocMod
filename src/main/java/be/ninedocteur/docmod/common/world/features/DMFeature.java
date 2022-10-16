package be.ninedocteur.docmod.common.world.features;

import be.ninedocteur.docmod.common.init.DMBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
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


    public static class Placed {
        public static final Holder<PlacedFeature> ALBIZIA_TREE_PLACED =
                PlacementUtils.register("albizia_placed",
                        Spawn.ALBIZIA_TREE_SPAWN, VegetationPlacements.treePlacement(
                                PlacementUtils.countExtra(1, 0.1f, 1)));

        public static final Holder<PlacedFeature> CLASSIC_TREE_PLACED =
                PlacementUtils.register("clssic_placed",
                        Spawn.CLASSIC_TREE_SPAWN, VegetationPlacements.treePlacement(
                                PlacementUtils.countExtra(1, 0.1f, 1)));


        //Vein 6, Entre 0 - 16

        public static final Holder<PlacedFeature> ZINC_ORE_PLACED = PlacementUtils.register("zinc_ore_placed",
                Raw.ZINC_ORE, commonOrePlacement(6,
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.aboveBottom(16))));

        public static final Holder<PlacedFeature> DEEP_ZINC_ORE_PLACED = PlacementUtils.register("deep_zinc_ore_placed",
                Raw.DEEP_ZINC_ORE, commonOrePlacement(4,
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.aboveBottom(-64))));

        public static final Holder<PlacedFeature> NETHER_ZINC_ORE_PLACED = PlacementUtils.register("nether_zinc_ore_placed",
                Raw.NETHER_ZINC_ORE, commonOrePlacement(8,
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.aboveBottom(80))));

        public static final Holder<PlacedFeature> STEEL_ORE_PLACED = PlacementUtils.register("steel_ore_placed",
                Raw.STEEL_ORE, commonOrePlacement(8,
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.aboveBottom(40))));

        public static final Holder<PlacedFeature> CRYSTALINE_ORE_PLACED = PlacementUtils.register("crystaline_ore_placed",
                Raw.crystaline_ORE, commonOrePlacement(7,
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.aboveBottom(30))));

        public static final Holder<PlacedFeature> CRYSTAL_ORE_PLACED = PlacementUtils.register("crystal_ore_placed",
                Raw.CRYSTAL_ORE, commonOrePlacement(7,
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.aboveBottom(30))));

        public static final Holder<PlacedFeature> CRYOLITE_ORE_PLACED = PlacementUtils.register("cryolite_ore_placed",
                Raw.CRYOLITE_ORE, commonOrePlacement(8,
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.aboveBottom(80))));

        public static final Holder<PlacedFeature> HALFINUM_ORE_PLACED = PlacementUtils.register("halfinum_ore_placed",
                Raw.HALFINUM_ORE, commonOrePlacement(1,
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.aboveBottom(5))));
    }

    public static class Checked{
        private static final Holder<PlacedFeature> ALBIZIA_TREE_CHECKED = PlacementUtils.register("albizia_checked",
                Raw.ALBIZIA_TREE, PlacementUtils.filteredByBlockSurvival(DMBlocks.ALBIZIA_PLANKS.get()));

        private static final Holder<PlacedFeature> CLASSIC_TREE_CHECKED = PlacementUtils.register("classic_checked",
                Raw.CLASSIC_TREE, PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING));
    }

    public static class Raw {
        public static final Holder<? extends ConfiguredFeature<TreeConfiguration, ?>> ALBIZIA_TREE =
                FeatureUtils.register("albizia_tree", Feature.TREE,
                        new TreeConfiguration.TreeConfigurationBuilder(
                                BlockStateProvider.simple(DMBlocks.ALBIZIA_LOG.get()),
                                new StraightTrunkPlacer(5, 6, 3),
                                BlockStateProvider.simple(DMBlocks.ALBIZIA_LEAVES.get()),
                                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                                new TwoLayersFeatureSize(1, 0, 2)).build());

        public static final Holder<? extends ConfiguredFeature<TreeConfiguration, ?>> CLASSIC_TREE =
                FeatureUtils.register("classic_tree", Feature.TREE,
                        new TreeConfiguration.TreeConfigurationBuilder(
                                BlockStateProvider.simple(Blocks.OAK_LOG),
                                new StraightTrunkPlacer(5, 6, 3),
                                BlockStateProvider.simple(DMBlocks.ALBIZIA_LEAVES.get()),
                                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2),
                                new TwoLayersFeatureSize(1, 0, 2)).build());


        public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ZINC_ORE = FeatureUtils.register("zinc_ore",
                Feature.ORE, new OreConfiguration(NATURAL_STONE, DMBlocks.ZINC_ORE.get().defaultBlockState(), 8));

        public static final Holder<ConfiguredFeature<OreConfiguration, ?>> DEEP_ZINC_ORE = FeatureUtils.register("deep_zinc_ore",
                Feature.ORE, new OreConfiguration(NATURAL_DEEPSLATE, DMBlocks.DEEPSLATE_ZINC_ORE.get().defaultBlockState(), 6));

        public static final Holder<ConfiguredFeature<OreConfiguration, ?>> NETHER_ZINC_ORE = FeatureUtils.register("nether_zinc_ore",
                Feature.ORE, new OreConfiguration(NATURAL_NETHERRACK, DMBlocks.NETHER_ZINC_ORE.get().defaultBlockState(), 8));

        public static final Holder<ConfiguredFeature<OreConfiguration, ?>> STEEL_ORE = FeatureUtils.register("steel_ore",
                Feature.ORE, new OreConfiguration(NATURAL_STONE, DMBlocks.STEEL_ORE.get().defaultBlockState(), 8));

        public static final Holder<ConfiguredFeature<OreConfiguration, ?>> crystaline_ORE = FeatureUtils.register("crystaline_ore",
                Feature.ORE, new OreConfiguration(NATURAL_STONE, DMBlocks.CRYSTALINE_ORE.get().defaultBlockState(), 8));

        public static final Holder<ConfiguredFeature<OreConfiguration, ?>> CRYSTAL_ORE = FeatureUtils.register("crystal_ore",
                Feature.ORE, new OreConfiguration(NATURAL_STONE, DMBlocks.CRYSTAL_ORE.get().defaultBlockState(), 8));

        public static final Holder<ConfiguredFeature<OreConfiguration, ?>> CRYOLITE_ORE = FeatureUtils.register("cryolite_ore",
                Feature.ORE, new OreConfiguration(NATURAL_STONE, DMBlocks.CRYOLITE_ORE.get().defaultBlockState(), 8));

        public static final Holder<ConfiguredFeature<OreConfiguration, ?>> HALFINUM_ORE = FeatureUtils.register("halfinum_ore",
                Feature.ORE, new OreConfiguration(NATURAL_STONE, DMBlocks.HALFINUM_ORE.get().defaultBlockState(), 1));
    }
    
    public static class Spawn{
        private static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> ALBIZIA_TREE_SPAWN =
                FeatureUtils.register("albizia_spawn", Feature.RANDOM_SELECTOR,
                        new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(Checked.ALBIZIA_TREE_CHECKED,
                                0.5F)), Checked.ALBIZIA_TREE_CHECKED));

        private static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> CLASSIC_TREE_SPAWN =
                FeatureUtils.register("classic_spawn", Feature.RANDOM_SELECTOR,
                        new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(Checked.CLASSIC_TREE_CHECKED,
                                0.5F)), Checked.CLASSIC_TREE_CHECKED));
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
}
