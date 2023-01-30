package fr.ninedocteur.docmod.common.init;

import fr.ninedocteur.docmod.DocMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
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