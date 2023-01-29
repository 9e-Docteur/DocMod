package fr.ninedocteur.docmod.common.init;

import fr.ninedocteur.docmod.DocMod;
import fr.ninedocteur.docmod.common.block.HealBlock;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class DMBlocks {
    public static final Block HEAL_BLOCK = add("heal_block", new HealBlock(AbstractBlock.Settings.of(Material.STONE).nonOpaque().requiresTool().strength(5)));


    public static Map<String, Block> BLOCKS = new HashMap<>();
    public static Block add(String id, Block block) {
        BLOCKS.put(id, block);
        return block;
    }
    public static void register() {
        BLOCKS.forEach((id, block) -> {
            Registry.register(Registries.BLOCK, id, block);
            Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
            ItemGroupEvents.modifyEntriesEvent(GROUP).register(content -> {
                content.add(new ItemStack(block));
            });
        });
    }

    private static final ItemGroup GROUP = FabricItemGroup.builder(new Identifier(DocMod.MOD_ID, "blocks"))
            .icon(() -> new ItemStack(HEAL_BLOCK)).build();

}
