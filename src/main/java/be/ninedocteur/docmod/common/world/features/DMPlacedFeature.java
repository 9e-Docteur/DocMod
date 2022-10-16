package be.ninedocteur.docmod.common.world.features;

import be.ninedocteur.docmod.DocMod;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class DMPlacedFeature {

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURE = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, DocMod.MOD_ID);

    //ORES
    public static final RegistryObject<PlacedFeature> ZINC_ORE_PLACED = PLACED_FEATURE.register("zinc_ore_placed", () -> new PlacedFeature(DMConfiguredFeature.ZINC_ORE.getHolder().get(), commonOrePlacement(7 /*Ore per chunk*/, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(32)))));
    public static final RegistryObject<PlacedFeature> HALFINUM_ORE_PLACED = PLACED_FEATURE.register("halfinum_ore_placed", () -> new PlacedFeature(DMConfiguredFeature.HALFINUM_ORE.getHolder().get(), commonOrePlacement(3, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(16)))));
    public static final RegistryObject<PlacedFeature> CRYOLITE_ORE_PLACED = PLACED_FEATURE.register("cryolite_ore_placed", () -> new PlacedFeature(DMConfiguredFeature.CRYOLITE_ORE.getHolder().get(), commonOrePlacement(9, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));
    public static final RegistryObject<PlacedFeature> CRYSTALINE_ORE_PLACED = PLACED_FEATURE.register("crystaline_ore_placed", () -> new PlacedFeature(DMConfiguredFeature.CRYSTALINE_ORE.getHolder().get(), commonOrePlacement(8, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));
    public static final RegistryObject<PlacedFeature> STEEL_ORE_PLACED = PLACED_FEATURE.register("steel_ore_placed", () -> new PlacedFeature(DMConfiguredFeature.STEEL_ORE.getHolder().get(), commonOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(60)))));
    public static final RegistryObject<PlacedFeature> CRYSTAL_ORE_PLACED = PLACED_FEATURE.register("crystal_ore_placed", () -> new PlacedFeature(DMConfiguredFeature.CRYSTAL_ORE.getHolder().get(), commonOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(40)))));

    //GEODES
    public static final RegistryObject<PlacedFeature> CRYSTALINE_GEODE_PLACED = PLACED_FEATURE.register("crystaline_geode_placed",
            () -> new PlacedFeature(DMConfiguredFeature.CRYSTALINE_GEODE.getHolder().get(), List.of(
                    RarityFilter.onAverageOnceEvery(50), InSquarePlacement.spread(),
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(50)),
                    BiomeFilter.biome())));

    //TREE
    public static final RegistryObject<PlacedFeature> ALBIZIA_TREE_CHECKED = PLACED_FEATURE.register("albizia_tree_checked", () -> new PlacedFeature(DMConfiguredFeature.ALBIZIA_TREE.getHolder().get(), List.of(PlacementUtils.filteredByBlockSurvival(Blocks.GRASS_BLOCK))));
    public static final RegistryObject<PlacedFeature> ALBIZIA_TREE_PLACED = PLACED_FEATURE.register("albizia_tree_placed", () -> new PlacedFeature(DMConfiguredFeature.ALBIZIA_TREE_SPAWN.getHolder().get(), VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2))));

    public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    public static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }

    public static void register(IEventBus eventBus){
        PLACED_FEATURE.register(eventBus);
    }
}
