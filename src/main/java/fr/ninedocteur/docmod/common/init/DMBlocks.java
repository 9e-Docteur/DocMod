package fr.ninedocteur.docmod.common.init;

import fr.ninedocteur.docmod.DocMod;
import fr.ninedocteur.docmod.common.block.*;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.DyeColor;
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

    public static final Block CIRCLE_GLASS = add("circle_glass", new CircleGlassBlock(AbstractBlock.Settings.of(Material.GLASS).nonOpaque().requiresTool().strength(5)));
    public static final Block DEATH_LOG = add("death_log", new Block(AbstractBlock.Settings.of(Material.WOOD).requiresTool().strength(3)));

    public static final Block CLASSIC_GRASS = add("classic_grass", new Block(AbstractBlock.Settings.of(Material.SOLID_ORGANIC).requiresTool().strength(1).sounds(BlockSoundGroup.GRASS)));
    public static final Block DEATH_LEAVES = add("death_leaves", new LeavesBlock(AbstractBlock.Settings.of(Material.LEAVES).nonOpaque().requiresTool().strength(0.2f).sounds(BlockSoundGroup.FUNGUS)));
    public static final Block DEATH_GRASS_BLOCK = add("death_grass_block", new Block(AbstractBlock.Settings.of(Material.STONE).requiresTool().strength(3)));
    public static final Block NETHER_ZINC_ORE = add("nether_zinc_ore", new DMOreBlock(DyeColor.RED, 2, 2));
    public static final Block MOON_BLOCK = add("moon_block", new Block(AbstractBlock.Settings.of(Material.STONE).requiresTool().strength(1)));
    public static final Block MOON_STONE = add("moon_stone", new Block(AbstractBlock.Settings.of(Material.STONE).requiresTool().strength(1)));
    public static final Block ALBIZIA_LOG = add("albizia_log", new Block(AbstractBlock.Settings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).requiresTool().strength(1)));
    public static final Block ALBIZIA_PLANKS = add("albizia_planks", new Block(AbstractBlock.Settings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).requiresTool().strength(1)));
    public static final Block ALBIZIA_STAIRS = add("albizia_stairs", new StairsBlock(ALBIZIA_PLANKS.getDefaultState(), AbstractBlock.Settings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).requiresTool().strength(1)));
    public static final Block ALBIZIA_DOOR = add("albizia_door", new DoorBlock(AbstractBlock.Settings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).requiresTool().strength(1), BlockSetType.OAK));
    public static final Block ALBIZIA_LEAVES = add("albizia_leaves", new LeavesBlock(AbstractBlock.Settings.of(Material.LEAVES).nonOpaque().requiresTool().strength(5).sounds(BlockSoundGroup.AZALEA_LEAVES).breakInstantly()));
    public static final Block CRYOLITE_ORE = add("cryolite_ore", new Block(AbstractBlock.Settings.of(Material.STONE).nonOpaque().requiresTool().strength(3)));
    public static final Block HALFINUM_ORE = add("halfinum_ore", new Block(AbstractBlock.Settings.of(Material.STONE).nonOpaque().requiresTool().strength(8)));
    public static final Block DEEPSLATE_HALFINUM_ORE = add("deepslate_halfinum_ore", new DMOreBlock(true, 8, 8));
    public static final Block GLASS_TUBE = add("glass_tube", new GlassTubeBlock(AbstractBlock.Settings.of(Material.STONE).nonOpaque().requiresTool().strength(1)));
    public static final Block ZURB_TELEPORTER = add("zurb_teleporter", new ZurbTeleporterBlock(AbstractBlock.Settings.of(Material.STONE).nonOpaque().requiresTool().strength(1)));

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