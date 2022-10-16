package be.ninedocteur.docmod.utils;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class DMTags {

    public static class Blocks {

        public static final TagKey<Block> ORE_FINDER_VALUABLES = BlockTags.create(new ResourceLocation("ore_finder_valuables"));

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(DMUtils.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Items {
        public static final TagKey<Item> CYBER_GUN_MUNITIONS = tag("cybergun_munitions");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(DMUtils.MOD_ID, name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}
