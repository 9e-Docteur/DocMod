package fr.ninedocteur.docmod.common.init;

import fr.ninedocteur.docmod.common.item.DMBaseItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.text.Text;

public class DMItemGroups {

    public static DMItemGroup MAIN = new DMItemGroup(ItemGroup.create(ItemGroup.Row.BOTTOM, 0)
            .displayName(Text.translatable("DocMod Main Tab"))
            .icon(DMItems.ZINC_INGOT::getDefaultStack)
            .build());
    public static DMItemGroup TOOLS = new DMItemGroup(ItemGroup.create(ItemGroup.Row.BOTTOM, 0)
            .displayName(Text.translatable("Tools Tab"))
            .icon(DMItems.ZINC_PICKAXE::getDefaultStack)
            .build());
    public static DMItemGroup MATERIALS = new DMItemGroup(ItemGroup.create(ItemGroup.Row.BOTTOM, 0)
            .displayName(Text.translatable("Armor Tab"))
            .icon(DMItems.ZINC_HELMET::getDefaultStack)
            .build());
    public static DMItemGroup FOOD = new DMItemGroup(ItemGroup.create(ItemGroup.Row.BOTTOM, 0)
            .displayName(Text.translatable("Food Tab"))
            .icon(DMItems.BELGIUM_FRIES::getDefaultStack)
            .build());
    public static DMItemGroup CHRISTMAS = new DMItemGroup(ItemGroup.create(ItemGroup.Row.BOTTOM, 0)
            .displayName(Text.translatable("Misc Tab"))
            .icon(DMItems.TARDIS_KEY::getDefaultStack)
            .build());
    public static DMItemGroup ENTITIES = new DMItemGroup(ItemGroup.create(ItemGroup.Row.BOTTOM, 0)
            .displayName(Text.translatable("Misc Tab"))
            .icon(DMItems.TARDIS_KEY::getDefaultStack)
            .build());
    public static DMItemGroup ROUNDELS = new DMItemGroup(ItemGroup.create(ItemGroup.Row.BOTTOM, 0)
            .displayName(Text.translatable("Misc Tab"))
            .icon(DMItems.TARDIS_KEY::getDefaultStack)
            .build());
    public static DMItemGroup ANNIVERSARY = new DMItemGroup(ItemGroup.create(ItemGroup.Row.BOTTOM, 0)
            .displayName(Text.translatable("Misc Tab"))
            .icon(DMItems.TARDIS_KEY::getDefaultStack)
            .build());
    public static DMItemGroup COMMUNITY = new DMItemGroup(ItemGroup.create(ItemGroup.Row.BOTTOM, 0)
            .displayName(Text.translatable("Misc Tab"))
            .icon(DMItems.TARDIS_KEY::getDefaultStack)
            .build());

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

    public static class DMItemGroup {
        public static DMItemGroup[] values() {
            return new DMItemGroup[]{MAIN, TOOLS, MATERIALS, FOOD, CHRISTMAS, ENTITIES, ROUNDELS, ANNIVERSARY, COMMUNITY};
        }
        public final ItemGroup itemGroup;
        DMItemGroup(ItemGroup itemGroup) {
            this.itemGroup = itemGroup;
        }
    }
}
