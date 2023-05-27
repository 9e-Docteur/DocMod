package fr.ninedocteur.docmod.common.init;

import fr.ninedocteur.docmod.DocMod;
import fr.ninedocteur.docmod.common.item.*;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.HashMap;
import java.util.Map;

public class DMItems {
    public static Map<String, DMBaseItem> ITEMS = new HashMap<>();

    public static final Item AMETHYST = add("amethyst", new DMBaseItem(
            new Item.Settings(),
            DMItemGroups.DMItemGroup.MATERIALS
    ));
    public static final Item ZINC_INGOT = add("zinc_ingot", new DMBaseItem(
            new Item.Settings(),
            DMItemGroups.DMItemGroup.MATERIALS
    ));
    public static final Item CRYSTALINE = add("crystaline", new DMBaseItem(
            new Item.Settings(),
            DMItemGroups.DMItemGroup.MATERIALS
    ));
    public static final Item CRYSTAL = add("crystal", new DMBaseItem(
            new Item.Settings().rarity(Rarity.UNCOMMON),
            DMItemGroups.DMItemGroup.MATERIALS
    ));
    public static final Item ZINC_NUGGET = add("zinc_nugget", new DMBaseItem(
            new Item.Settings(),
            DMItemGroups.DMItemGroup.MATERIALS
    ));
    public static final Item HALFINUM_NUGGET = add("halfinum_nugget", new DMBaseItem(
            new Item.Settings(),
            DMItemGroups.DMItemGroup.MATERIALS
    ));
    public static final Item CRYOLITE = add("cryolite", new DMBaseItem(
            new Item.Settings(),
            DMItemGroups.DMItemGroup.MATERIALS
    ));
    public static final Item LIGHT_BLUE_BRICKS_INGOT = add("light_blue_bricks_ingot", new DMBaseItem(
            new Item.Settings(),
            DMItemGroups.DMItemGroup.MATERIALS
    ));
    public static final Item GREEN_BRICKS_INGOT = add("green_bricks_ingot", new DMBaseItem(
            new Item.Settings(),
            DMItemGroups.DMItemGroup.MATERIALS
    ));
    public static final Item YELLOW_BRICKS_INGOT= add("yellow_bricks_ingot", new DMBaseItem(
            new Item.Settings(),
            DMItemGroups.DMItemGroup.MATERIALS
    ));
    public static final Item BLUE_BRICKS_INGOT = add("blue_bricks_ingot", new DMBaseItem(
            new Item.Settings(),
            DMItemGroups.DMItemGroup.MATERIALS
    ));
    public static final Item HALFINUM_INGOT = add("halfinum_ingot", new TooltipItem(
            new Item.Settings().rarity(Rarity.RARE),
            DMItemGroups.DMItemGroup.MATERIALS,
            Text.literal(Formatting.OBFUSCATED  + "t" + Formatting.RESET + "OMGGG"+ Formatting.OBFUSCATED  + "t")
    ));
    public static final Item FUEL = add("fuel", new DMBaseItem(
            new Item.Settings().rarity(Rarity.RARE),
            DMItemGroups.DMItemGroup.MATERIALS
    ));
    public static final Item WHITE_POINTED_STAR = add("white_pointed_star", new GlitItem(
            new Item.Settings().rarity(Rarity.RARE),
            DMItemGroups.DMItemGroup.MATERIALS
    ));
    public static final Item STEEL_INGOT = add("steel_ingot", new DMBaseItem(
            new Item.Settings(),
            DMItemGroups.DMItemGroup.MATERIALS
    ));
    public static final Item RAW_STEEL_INGOT = add("raw_steel_ingot", new DMBaseItem(
            new Item.Settings(),
            DMItemGroups.DMItemGroup.MATERIALS
    ));

    //FOOD
    public static final Item BELGIUM_FRIES = add("belgium_fries", new BelgiumFriesFoodItem(new Item.Settings().food(new FoodComponent.Builder().statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 2), 0.2f).hunger(2).saturationModifier(1).alwaysEdible().build())));
    public static final Item TEA = add("tea", new Item(new Item.Settings().food(new FoodComponent.Builder().hunger(2).saturationModifier(1).alwaysEdible().build())));
    public static final Item COFFEE = add("coffee", new Item(new Item.Settings().food(new FoodComponent.Builder().statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 2), 1).statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 200, 3), 1f).hunger(2).saturationModifier(1).alwaysEdible().build())));
    public static final Item JUPILER = add("jupiler", new JupilerFoodItem(new Item.Settings().food(new FoodComponent.Builder().statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 150, 2), 0.4f).statusEffect(new StatusEffectInstance(StatusEffects.POISON, 20, 3), 0.2f).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 100, 2),0.4f).hunger(2).saturationModifier(0.2f).alwaysEdible().build())));
    public static final Item ICE_CREAM = add("ice_cream", new Item(new Item.Settings().food(new FoodComponent.Builder().statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 2), 0.2f).hunger(4).saturationModifier(1).alwaysEdible().build())));
    public static final Item HAMBURGER = add("hamburger", new Item(new Item.Settings().food(new FoodComponent.Builder().statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 2), 0.2f).hunger(8).saturationModifier(2).alwaysEdible().build())));
    public static final Item DOCTEAM_COLA = add("docteam_cola", new Item(new Item.Settings().food(new FoodComponent.Builder().hunger(5).saturationModifier(2).alwaysEdible().build())));

    //FLASK
    public static final Item EMPTY_FLASK = add("empty_flask", new DMBaseItem(
            new Item.Settings(),
            DMItemGroups.DMItemGroup.MATERIALS
    ));
    public static final Item COPPER_FLASK = add("copper_flask", new DMBaseItem(
            new Item.Settings(),
            DMItemGroups.DMItemGroup.MATERIALS
    ));
    public static final Item ZINC_FLASK = add("zinc_flask", new DMBaseItem(
            new Item.Settings(),
            DMItemGroups.DMItemGroup.MATERIALS
    ));
    public static final Item HALFINUM_FLASK = add("halfinum_flask", new DMBaseItem(
            new Item.Settings(),
            DMItemGroups.DMItemGroup.MATERIALS
    ));

    //TOOLS
    public static final Item ZINC_HELMET = add("zinc_helmet", new ArmorItem(DMArmorMaterials.ZINC, ArmorItem.Type.HELMET, new Item.Settings()));
    public static final Item ZINC_CHESTPLATE = add("zinc_chestplate", new ArmorItem(DMArmorMaterials.ZINC, ArmorItem.Type.CHESTPLATE, new Item.Settings()));
    public static final Item ZINC_LEGGINGS = add("zinc_leggings", new ArmorItem(DMArmorMaterials.ZINC, ArmorItem.Type.LEGGINGS, new Item.Settings()));
    public static final Item ZINC_BOOTS = add("zinc_boots", new ArmorItem(DMArmorMaterials.ZINC, ArmorItem.Type.BOOTS, new Item.Settings()));
    public static final Item COPPER_HELMET = add("copper_helmet", new ArmorItem(DMArmorMaterials.COPPER, ArmorItem.Type.HELMET, new Item.Settings()));
    public static final Item COPPER_CHESTPLATE = add("copper_chestplate", new ArmorItem(DMArmorMaterials.COPPER, ArmorItem.Type.CHESTPLATE, new Item.Settings()));
    public static final Item COPPER_LEGGINGS = add("copper_leggings", new ArmorItem(DMArmorMaterials.COPPER, ArmorItem.Type.LEGGINGS, new Item.Settings()));
    public static final Item COPPER_BOOTS = add("copper_boots", new ArmorItem(DMArmorMaterials.COPPER, ArmorItem.Type.BOOTS, new Item.Settings()));
    public static final Item HALFINUM_HELMET = add("halfinum_helmet", new ArmorItem(DMArmorMaterials.HALFINUM, ArmorItem.Type.HELMET, new Item.Settings()));
    public static final Item HALFINUM_CHESTPLATE = add("halfinum_chestplate", new ArmorItem(DMArmorMaterials.HALFINUM, ArmorItem.Type.CHESTPLATE, new Item.Settings()));
    public static final Item HALFINUM_LEGGINGS = add("halfinum_leggings", new ArmorItem(DMArmorMaterials.HALFINUM, ArmorItem.Type.LEGGINGS, new Item.Settings()));
    public static final Item HALFINUM_BOOTS = add("halfinum_boots", new ArmorItem(DMArmorMaterials.HALFINUM, ArmorItem.Type.BOOTS, new Item.Settings()));
    public static final Item SPACE_HELMET = add("space_helmet", new ArmorItem(DMArmorMaterials.SPACE_SUIT, ArmorItem.Type.HELMET, new Item.Settings()));
    public static final Item SPACE_CHESTPLATE = add("space_chestplate", new ArmorItem(DMArmorMaterials.SPACE_SUIT, ArmorItem.Type.CHESTPLATE, new Item.Settings()));
    public static final Item SPACE_LEGGINGS = add("space_leggings", new ArmorItem(DMArmorMaterials.SPACE_SUIT, ArmorItem.Type.LEGGINGS, new Item.Settings()));
    public static final Item SPACE_BOOTS = add("space_boots", new ArmorItem(DMArmorMaterials.SPACE_SUIT, ArmorItem.Type.BOOTS, new Item.Settings()));
    public static final Item COPPER_SWORD = add("copper_sword", new SwordItem(DMToolMaterials.COPPER, 6, -3, new Item.Settings()));
    public static final Item COPPER_PICKAXE = add("copper_pickaxe", new PickaxeItem(DMToolMaterials.COPPER, 0, -1, new Item.Settings()));
    public static final Item COPPER_AXE = add("copper_axe", new AxeItem(DMToolMaterials.COPPER, 0, -1, new Item.Settings()));
    public static final Item ZINC_SWORD = add("zinc_sword", new SwordItem(DMToolMaterials.ZINC, 6, 3f, new Item.Settings()));
    public static final Item ZINC_PICKAXE = add("zinc_pickaxe", new PickaxeItem(DMToolMaterials.ZINC, 0, -1, new Item.Settings()));
    public static final Item ZINC_AXE = add("zinc_axe", new AxeItem(DMToolMaterials.ZINC, 0, -1, new Item.Settings()));
    public static final Item STAFF = add("staff", new StaffItem(DMToolMaterials.ZINC, 0, -1, new Item.Settings()));
    public static final Item TARDIS_KEY = add("tardis_key", new TardisKeyItem(new Item.Settings()));
    public static final Item ALBIZIA_STICK = add("albizia_stick");
    public static final Item HALFINUM_AXE = add("halfinum_axe", new AxeItem(DMToolMaterials.HALFINUM, 0, -1, new Item.Settings()));
    public static final Item HALFINUM_PICKAXE = add("halfinum_pickaxe", new PickaxeItem(DMToolMaterials.HALFINUM, 0, -1, new Item.Settings()));
    public static final Item HALFINUM_SWORD = add("halfinum_sword", new HalfinumSwordItem(DMToolMaterials.HALFINUM, 0, -1, new Item.Settings()));
    public static final Item HALFINUM_SHOVEL = add("halfinum_shovel", new ShovelItem(DMToolMaterials.HALFINUM, 0, -1, new Item.Settings()));
    public static final Item MASK = add("mask", new ArmorItem(DMArmorMaterials.MASK, ArmorItem.Type.HELMET, new Item.Settings()));
    public static final Item ORE_FINDER = add("ore_finder", new OreFinderItem(new Item.Settings().rarity(Rarity.RARE).maxDamage(100)));
    public static final Item ELECTRONIC_CICUIT  = add("amethyst");

    public static Item add(String id) {
        return add(id, new DMBaseItem(new Item.Settings(), null));
    }

    public static Item add(String id, DMBaseItem item) {
        ITEMS.put(id, item);
        return item;
    }

    public static void register() {
        ITEMS.forEach((id, item) -> {
            Identifier identifier = new Identifier(DocMod.MOD_ID, id);
            Registry.register(Registries.ITEM, identifier, item);
            ItemGroupEvents.modifyEntriesEvent(GROUP).register(content -> {
                content.add(item);
            });
        });
    }

    private static final ItemGroup GROUP = FabricItemGroup.builder(new Identifier(DocMod.MOD_ID, "items"))
            .icon(AMETHYST::getDefaultStack).build();
}