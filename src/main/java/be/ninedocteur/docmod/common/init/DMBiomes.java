package be.ninedocteur.docmod.common.init;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.world.biome.ClassicBiome;
import be.ninedocteur.docmod.common.world.biome.MoonBiome;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DMBiomes {
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, DocMod.MOD_ID);

    public static final RegistryObject<Biome> CLASSIC_BIOME = BIOMES.register("classic_biome", ClassicBiome::get);
    public static final ResourceKey<Biome> CLASSIC_BIOME_KEY = registerBiome("classic_biome", 10);
    
    public static final RegistryObject<Biome> MOON_BIOME = BIOMES.register("moon_biome", MoonBiome::get);
    public static final ResourceKey<Biome> MOON_BIOME_KEY = registerBiome("moon_biome", 10);

    private static ResourceKey<Biome> registerBiome(String name, int weight){
        ResourceKey<Biome> biome = ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(DocMod.MOD_ID, name));
        BiomeManager.addBiome(BiomeManager.BiomeType.ICY, new BiomeManager.BiomeEntry(biome, weight));
        BiomeManager.addAdditionalOverworldBiomes(biome);
        return biome;
    }
}
