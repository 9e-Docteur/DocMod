package fr.ninedocteur.docmod.common.init;

import fr.ninedocteur.docmod.DocMod;
import fr.ninedocteur.docmod.common.block.entity.GlassTubeEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class DMTileEntities {
    public static final BlockEntityType<GlassTubeEntity> GlassTile = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(DocMod.MOD_ID, "glasstile"), BlockEntityType.Builder.create(GlassTubeEntity::new, DMBlocks.GLASS_TUBE).build(null));
    public static final BlockEntityType<GlassTubeEntity> ZurbTeleporterTile = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(DocMod.MOD_ID, "zurbteleportertile"), BlockEntityType.Builder.create(GlassTubeEntity::new, DMBlocks.ZURB_TELEPORTER).build(null));
}
