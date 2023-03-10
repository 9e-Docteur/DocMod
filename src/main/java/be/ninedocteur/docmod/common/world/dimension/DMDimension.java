package be.ninedocteur.docmod.common.world.dimension;

import be.ninedocteur.docmod.DocMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class DMDimension {
    public static final ResourceKey<Level> MOON_KEY = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(DocMod.MOD_ID, "moon"));
    public static final ResourceKey<Level> TARDIS_KEY = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(DocMod.MOD_ID, "tardis"));
    public static final ResourceKey<Level> SPACE_KEY = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(DocMod.MOD_ID, "space"));
    public static final ResourceKey<DimensionType> MOON_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, MOON_KEY.registry());
    public static final ResourceKey<DimensionType> TARDIS_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, TARDIS_KEY.registry());
    public static final ResourceKey<DimensionType> SPACE_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, SPACE_KEY.registry());


    public static void register(){
        DocMod.LOGGER.info("Init DocMod Dimension...");
    }
}
