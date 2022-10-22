package be.ninedocteur.docmod.common.world.features;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.init.DMBlocks;
import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.GeodeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class DMConfiguredFeature {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURE = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, DocMod.MOD_ID);

    //ORE
    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_ZINC_ORE = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, DMBlocks.ZINC_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, DMBlocks.DEEPSLATE_ZINC_ORE.get().defaultBlockState())
    ));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_HALFINUM_ORE = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, DMBlocks.HALFINUM_ORE.get().defaultBlockState())
    ));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_CRYOLITE_ORE = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, DMBlocks.CRYOLITE_ORE.get().defaultBlockState())
    ));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_CRYSTALINE_ORE = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, DMBlocks.CRYSTALINE_ORE.get().defaultBlockState())
    ));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_CRYSTAL_ORE = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, DMBlocks.CRYSTAL_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, DMBlocks.DEEPSLATE_CRYSTAL_ORE.get().defaultBlockState())
    ));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_STEEL_ORE = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, DMBlocks.STEEL_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, DMBlocks.DEEPSLATE_STEEL_ORE.get().defaultBlockState())
    ));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_XP_ORE = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, DMBlocks.XP_ORE.get().defaultBlockState())
    ));

    public static final RegistryObject<ConfiguredFeature<?, ?>> ZINC_ORE = CONFIGURED_FEATURE.register("zinc_ore_configured", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_ZINC_ORE.get(), 4))); //4 = vein size
    public static final RegistryObject<ConfiguredFeature<?, ?>> HALFINUM_ORE = CONFIGURED_FEATURE.register("halfinum_ore_configured", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_HALFINUM_ORE.get(), 1)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> CRYOLITE_ORE = CONFIGURED_FEATURE.register("cryolite_ore_configured", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_CRYOLITE_ORE.get(), 6)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> CRYSTAL_ORE = CONFIGURED_FEATURE.register("crystal_ore_configured", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_CRYSTAL_ORE.get(), 4)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> CRYSTALINE_ORE = CONFIGURED_FEATURE.register("crystaline_ore_configured", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_CRYSTALINE_ORE.get(), 6)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> STEEL_ORE = CONFIGURED_FEATURE.register("steel_ore_configured", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_STEEL_ORE.get(), 6)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> XP_ORE = CONFIGURED_FEATURE.register("xp_ore_configured", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_XP_ORE.get(), 8)));

    //GEODE
    public static final RegistryObject<ConfiguredFeature<?, ?>> CRYSTALINE_GEODE = CONFIGURED_FEATURE.register("crystaline_geode",
            () -> new ConfiguredFeature<>(Feature.GEODE,
                    new GeodeConfiguration(new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR),
                            BlockStateProvider.simple(Blocks.DEEPSLATE),
                            BlockStateProvider.simple(DMBlocks.CRYSTALINE_ORE.get()),
                            BlockStateProvider.simple(Blocks.STONE),
                            BlockStateProvider.simple(Blocks.EMERALD_BLOCK),
                            List.of(DMBlocks.CRYSTALINE_ORE.get().defaultBlockState()),
                            BlockTags.FEATURES_CANNOT_REPLACE , BlockTags.GEODE_INVALID_BLOCKS),
                            new GeodeLayerSettings(1.7D, 1.2D, 2.5D, 3.5D),
                            new GeodeCrackSettings(0.25D, 1.5D, 1), 0.5D, 0.1D,
                            true, UniformInt.of(3, 8),
                            UniformInt.of(2, 6), UniformInt.of(1, 2),
                            -18, 18, 0.075D, 1)));

    //TREE
    public static final RegistryObject<ConfiguredFeature<?, ?>> ALBIZIA_TREE = CONFIGURED_FEATURE.register("albizia_tree",
            () -> new ConfiguredFeature<>(Feature.TREE,
                    new TreeConfiguration.TreeConfigurationBuilder(
                            BlockStateProvider.simple(DMBlocks.ALBIZIA_LOG.get()),
                            new StraightTrunkPlacer(5, 6, 3),
                            BlockStateProvider.simple(DMBlocks.ALBIZIA_LEAVES.get()),
                            new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                            new TwoLayersFeatureSize(1, 0, 2)).build()));

    public static final RegistryObject<ConfiguredFeature<?, ?>> ALBIZIA_TREE_SPAWN = CONFIGURED_FEATURE.register("albizia_tree_spawn", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(DMPlacedFeature.ALBIZIA_TREE_CHECKED.getHolder().get(), 0.5F)), DMPlacedFeature.ALBIZIA_TREE_CHECKED.getHolder().get())));
    public static void register(IEventBus eventBus){
        CONFIGURED_FEATURE.register(eventBus);
    }
}
