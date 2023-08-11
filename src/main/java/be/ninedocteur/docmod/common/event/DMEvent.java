package be.ninedocteur.docmod.common.event;

import be.ninedocteur.docmod.common.tileentity.TardisTileEntity;
import be.ninedocteur.docmod.common.world.tardis.TardisWorldManager;
import net.minecraftforge.event.level.LevelEvent;

public class DMEvent {
/*
    @SubscribeEvent
    public static void registerBiomeEvent(final BiomeLoadingEvent e){
        Decoration.addDecorations(e);
        Ores.addOres(e);
        DMEntityGeneration.onEntitySpawn(e);
    }

    public class Decoration{
     private static void addDecorations(final BiomeLoadingEvent e) {
            ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, e.getName());
            Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);
            List<Holder<PlacedFeature>> base = e.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION);
            if (types.contains(BiomeDictionary.Type.PLAINS)) {
             base.add(DMFeature.Placed.ALBIZIA_TREE_PLACED);
            }else if(types.contains(DMBiome.CLASSIC_BIOME)) {
             base.add(DMFeature.Placed.CLASSIC_TREE_PLACED);
         }
     }
    }
    public class Ores{
        private static void addOres(final BiomeLoadingEvent e) {
            List<Holder<PlacedFeature>> base = e.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);
            base.add(DMFeature.Placed.DEEP_ZINC_ORE_PLACED);
            base.add(DMFeature.Placed.ZINC_ORE_PLACED);
            base.add(DMFeature.Placed.NETHER_ZINC_ORE_PLACED);
            base.add(DMFeature.Placed.STEEL_ORE_PLACED);
            base.add(DMFeature.Placed.CRYSTALINE_ORE_PLACED);
            base.add(DMFeature.Placed.CRYSTAL_ORE_PLACED);
            base.add(DMFeature.Placed.HALFINUM_ORE_PLACED);
            base.add(DMFeature.Placed.CRYOLITE_ORE_PLACED);
        }
    }


 */

    public static void onWorldEvent(LevelEvent event){
        if(event.getLevel() != null){
            for(TardisTileEntity tile : TardisWorldManager.getTardis()){
                if(tile.isDemating){
                    tile.demat();
                }
                if(tile.isAlreadyDemat){
                    event.getLevel().removeBlock(tile.getCurrentPosition(), false);
                }
                if(tile.isRematting){
                    tile.remat();
                }
            }
        }
    }
}
