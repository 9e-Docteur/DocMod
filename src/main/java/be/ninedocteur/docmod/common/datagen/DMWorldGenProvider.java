package be.ninedocteur.docmod.common.datagen;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.world.features.DMConfiguredFeature;
import be.ninedocteur.docmod.common.world.features.DMFeature;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;

public class DMWorldGenProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, DMConfiguredFeature::bootstrap)
            .add(Registries.PLACED_FEATURE, DMFeature::bootstrap);

    public DMWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Collections.singleton(DocMod.MOD_ID));
    }
}
