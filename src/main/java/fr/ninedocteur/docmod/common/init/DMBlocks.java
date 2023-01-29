package fr.ninedocteur.docmod.common.init;

import fr.ninedocteur.docmod.DocMod;
import fr.ninedocteur.docmod.common.block.DMOreBlock;
import fr.ninedocteur.docmod.common.block.HealBlock;
import fr.ninedocteur.docmod.common.block.XPOreBlock;
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
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class DMBlocks {
    public static Map<String, Block> BLOCKS = new HashMap<>();

    public static final Block HEAL_BLOCK = add("heal_block", new HealBlock(AbstractBlock.Settings.of(Material.STONE).nonOpaque().requiresTool().strength(5)));
    public static final Block ZINC_ORE = add("zinc_ore", new DMOreBlock(false, 4, 10));
    public static final Block DEEPSLATE_ZINC_ORE = add("deepslate_zinc_ore", new DMOreBlock(true, 4, 10));
    public static final Block STEEL_ORE = add("steel_ore", new DMOreBlock(false, 4, 10));
    public static final Block DEEPSLATE_STEEL_ORE = add("deepslate_steel_ore", new DMOreBlock(true, 4, 10));
    public static final Block ZINC_BLOCK = add("zinc_block", new Block(AbstractBlock.Settings.of(Material.METAL).requiresTool().strength(3)));
    public static final Block STEEL_BLOCK = add("steel_block", new Block(AbstractBlock.Settings.of(Material.METAL).requiresTool().strength(3)));
    public static final Block CRYSTALINE_BLOCK = add("crystaline_block", new Block(AbstractBlock.Settings.of(Material.METAL).requiresTool().strength(5).sounds(BlockSoundGroup.AMETHYST_BLOCK)));
    public static final Block CRYSTALINE_ORE = add("crystaline_ore", new DMOreBlock(false, 4, 10));
    public static final Block CRYSTAL_ORE = add("crystal_ore", new DMOreBlock(false, 4, 10));
    public static final Block DEEPSLATE_CRYSTAL_ORE = add("deepslate_crystal_ore", new DMOreBlock(true, 4, 10));
    public static final Block XP_ORE = add("xp_ore", new XPOreBlock(false, 5,5, 40));
    

    public static Block add(String id, Block block) {
        BLOCKS.put(id, block);
        return block;
    }
    public static void register() {
        BLOCKS.forEach((id, block) -> {
            Identifier identifier = new Identifier(DocMod.MOD_ID, id);
            Registry.register(Registries.BLOCK, identifier, block);
            Registry.register(Registries.ITEM, identifier, new BlockItem(block, new Item.Settings()));
            ItemGroupEvents.modifyEntriesEvent(GROUP).register(content -> {
                content.add(new ItemStack(block));
            });
        });
    }

    private static final ItemGroup GROUP = FabricItemGroup.builder(new Identifier(DocMod.MOD_ID, "blocks"))
            .icon(() -> new ItemStack(HEAL_BLOCK)).build();

}