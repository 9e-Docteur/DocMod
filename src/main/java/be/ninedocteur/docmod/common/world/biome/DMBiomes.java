package be.ninedocteur.docmod.common.world.biome;

import be.ninedocteur.docmod.DocMod;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DMBiomes {

    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, DocMod.MOD_ID);

   // public static final RegistryObject<Codec<? extends BiomeModifier>> DM_BIOME_MODIFIER = BIOME_MODIFIERS.register(DocMod.MOD_ID + "_biome_modifier", () -> Codec.unit(DMBiomeModifier::new));

    public static final ResourceKey<Biome> CLASSIC_KEY = registerBiome("classic");;

    private static ResourceKey<Biome> registerBiome(String id){
        return ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(DocMod.MOD_ID, id));
    }

    public static void registerBiomes(IEventBus bus){
        BIOME_MODIFIERS.register(bus);
    }
}
