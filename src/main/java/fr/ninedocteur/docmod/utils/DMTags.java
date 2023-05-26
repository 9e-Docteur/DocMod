package fr.ninedocteur.docmod.utils;

import fr.ninedocteur.docmod.DocMod;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class DMTags {
    public static class Blocks {
        public static final TagKey<Block> ORE_FINDER_VALUABLES = TagKey.of(RegistryKeys.BLOCK, new Identifier("ore_finder_valuables"));

    }
}
