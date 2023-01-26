package be.ninedocteur.docmod.common.world.features;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.init.DMBlocks;
import com.google.common.base.Suppliers;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
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
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class DMConfiguredFeature {

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_ZINC_ORE_KEY = registryKey("overworld_zinc_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_HALFINUM_ORE_KEY = registryKey("overworld_zinc_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_CRYOLITE_ORE_KEY = registryKey("overworld_zinc_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_CRYSTALINE_ORE_KEY = registryKey("overworld_zinc_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_CRYSTAL_KEY = registryKey("overworld_zinc_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_STEEL_KEY = registryKey("overworld_zinc_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_XP_ORE_KEY = registryKey("overworld_zinc_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> CRYSTALINE_GEODE_KEY = registryKey("crystaline_geode");


    public static final ResourceKey<ConfiguredFeature<?, ?>> ALBIZIA_TREE_KEY = registryKey("albizia_tree");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ALBIZIA_SPAWN_KEY = registryKey("albizia_tree");


    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_ZINC_ORE = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD), DMBlocks.ZINC_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), DMBlocks.DEEPSLATE_ZINC_ORE.get().defaultBlockState())
    ));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_HALFINUM_ORE = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD), DMBlocks.HALFINUM_ORE.get().defaultBlockState())
    ));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_CRYOLITE_ORE = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD), DMBlocks.CRYOLITE_ORE.get().defaultBlockState())
    ));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_CRYSTALINE_ORE = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD), DMBlocks.CRYSTALINE_ORE.get().defaultBlockState())
    ));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_CRYSTAL_ORE = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD), DMBlocks.CRYSTAL_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), DMBlocks.DEEPSLATE_CRYSTAL_ORE.get().defaultBlockState())
    ));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_STEEL_ORE = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD), DMBlocks.STEEL_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), DMBlocks.DEEPSLATE_STEEL_ORE.get().defaultBlockState())
    ));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_XP_ORE = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD), DMBlocks.XP_ORE.get().defaultBlockState())
    ));

    
    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context){
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);

        register(context, OVERWORLD_ZINC_ORE_KEY, Feature.ORE, new OreConfiguration(OVERWORLD_ZINC_ORE.get(), 4));
        register(context, OVERWORLD_HALFINUM_ORE_KEY, Feature.ORE, new OreConfiguration(OVERWORLD_HALFINUM_ORE.get(), 1));
        register(context, OVERWORLD_CRYOLITE_ORE_KEY, Feature.ORE, new OreConfiguration(OVERWORLD_CRYOLITE_ORE.get(), 6));
        register(context, OVERWORLD_CRYSTALINE_ORE_KEY, Feature.ORE, new OreConfiguration(OVERWORLD_CRYSTALINE_ORE.get(), 6));
        register(context, OVERWORLD_CRYSTAL_KEY, Feature.ORE, new OreConfiguration(OVERWORLD_CRYSTAL_ORE.get(), 4));
        register(context, OVERWORLD_STEEL_KEY, Feature.ORE, new OreConfiguration(OVERWORLD_STEEL_ORE.get(), 5));
        register(context, OVERWORLD_XP_ORE_KEY, Feature.ORE, new OreConfiguration(OVERWORLD_XP_ORE.get(), 8));

        register(context, OVERWORLD_XP_ORE_KEY, Feature.ORE, new OreConfiguration(OVERWORLD_XP_ORE.get(), 8));

        register(context, CRYSTALINE_GEODE_KEY, Feature.GEODE,
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
                        -18, 18, 0.075D, 1));

        register(context, ALBIZIA_TREE_KEY, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(DMBlocks.ALBIZIA_LOG.get()),
                        new StraightTrunkPlacer(5, 6, 3),
                        BlockStateProvider.simple(DMBlocks.ALBIZIA_LEAVES.get()),
                        new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                        new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, ALBIZIA_SPAWN_KEY, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placedFeatures.getOrThrow(DMFeature.ALBIZIA_TREE_CHECKED_KEY), 0.5F)), placedFeatures.getOrThrow(DMFeature.ALBIZIA_TREE_CHECKED_KEY)));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registryKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(DocMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
