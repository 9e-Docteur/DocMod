package fr.ninedocteur.docmod.common.init;

import fr.ninedocteur.docmod.common.item.DMBaseItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.text.Text;

public class DMItemGroups {

    public static void register() {
        for (DMItemGroup value : DMItemGroup.values()){
            ItemGroupEvents.modifyEntriesEvent(value.itemGroup).register(content ->{
                DMItems.ITEMS.forEach((s, item) -> {
                    if (item.itemGroup.itemGroup == value.itemGroup) {
                        content.add(item);
                    }
                });
            });
        }
    }

    public enum DMItemGroup {
        MAIN(ItemGroup.create(ItemGroup.Row.BOTTOM, 0)
                .displayName(Text.translatable("DocMod Main Tab"))
                .icon(DMItems.ZINC_INGOT::getDefaultStack)
                .build()),
        TOOLS(ItemGroup.create(ItemGroup.Row.BOTTOM, 0)
                .displayName(Text.translatable("Tools Tab"))
                .icon(DMItems.ZINC_PICKAXE::getDefaultStack)
                .build()),
        MATERIALS(ItemGroup.create(ItemGroup.Row.BOTTOM, 0)
                .displayName(Text.translatable("Materials Tab"))
                .icon(DMItems.ZINC_INGOT::getDefaultStack)
                .build()),
        FOOD(ItemGroup.create(ItemGroup.Row.BOTTOM, 0)
                .displayName(Text.translatable("Food Tab"))
                .icon(DMItems.BELGIUM_FRIES::getDefaultStack)
                .build()),
        CHRISTMAS(ItemGroup.create(ItemGroup.Row.BOTTOM, 0)
                .displayName(Text.translatable("Christmas Tab"))
                .icon(DMBlocks.CHRISTMAS_TREE::getDefaultStack)
                .build()),
        ENTITIES(ItemGroup.create(ItemGroup.Row.BOTTOM, 0)
                .displayName(Text.translatable("Entities Tab"))
                .icon(DMItems.CYBERBOSS_EGG::getDefaultStack)
                .build()),
        ROUNDELS(ItemGroup.create(ItemGroup.Row.BOTTOM, 0)
                .displayName(Text.translatable("Roundels Tab"))
                .icon(DMBlocks.ROUNDEL_DARK_2::getDefaultStack)
                .build()),
        ANNIVERSARY(ItemGroup.create(ItemGroup.Row.BOTTOM, 0)
                .displayName(Text.translatable("Anniversary Tab"))
                .icon(DMBlocks.CAKE::getDefaultStack)
                .build()),
        COMMUNITY(ItemGroup.create(ItemGroup.Row.BOTTOM, 0)
                .displayName(Text.translatable("Community Tab"))
                .icon(DMItems.GARATIM_SONIC_SCREWDRIVER::getDefaultStack)
                .build());

        public final ItemGroup itemGroup;
        DMItemGroup(ItemGroup itemGroup) {
            this.itemGroup = itemGroup;
        }
    }
}
