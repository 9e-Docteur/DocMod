package fr.ninedocteur.docmod.common.init;

import fr.ninedocteur.docmod.DocMod;
import fr.ninedocteur.docmod.common.item.BelgiumFriesFoodItem;
import fr.ninedocteur.docmod.common.item.HalfinumIngotItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.HashMap;
import java.util.Map;

public class DMItems {
    public static Map<String, Item> ITEMS = new HashMap<>();

    public static final Item AMETHYST = add("amethyst");
    public static final Item ZINC_INGOT = add("zinc_ingot");
    public static final Item CRYSTALINE = add("crystaline");
    public static final Item CRYSTAL = add("crystal", new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item ZINC_NUGGET = add("zinc_nugget");
    public static final Item HALFINUM_NUGGET = add("halfinum_nugget");
    public static final Item CRYOLITE = add("cryolite");
    public static final Item LIGHT_BLUE_BRICKS_INGOT = add("light_blue_bricks_ingot");
    public static final Item GREEN_BRICKS_INGOT = add("green_bricks_ingot");
    public static final Item YELLOW_BRICKS_INGOT= add("yellow_bricks_ingot");
    public static final Item BLUE_BRICKS_INGOT = add("blue_bricks_ingot");
    public static final Item HALFINUM_INGOT = add("halfinum_ingot", new HalfinumIngotItem(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item FUEL = add("fuel", new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item WHITE_POINTED_STAR = add("white_pointed_star", new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item STEEL_INGOT = add("steel_ingot");
    public static final Item RAW_STEEL_INGOT = add("raw_steel_ingot");
    public static final Item BELGIUM_FRIES = add("belgium_fries", new BelgiumFriesFoodItem(new Item.Settings().food(new FoodComponent.Builder().statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 2), 0.2f).hunger(2).saturationModifier(1).alwaysEdible().build())));

    public static Item add(String id) {
        return add(id, new Item(new Item.Settings()));
    }

    public static Item add(String id, Item item) {
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