package be.ninedocteur.docmod;

import be.ninedocteur.docmod.common.init.DMBlocks;
import be.ninedocteur.docmod.common.init.DMItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class DMCreativeTabs {
    public static enum TAB {
        MAIN,
        TOOLS,
        MATERIALS,
        FOOD,
        CHRISTMAS,
        ENTITIES,
        ROUNDELS,
        ANNIVERSARY,
        COMMUNITY
    }
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MOD_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DocMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MAIN = createTab("main", DMBlocks.ZINC_BLOCK);
    public static final RegistryObject<CreativeModeTab> TOOLS = createTab("tools", DMItems.HALFINUM_PICKAXE);
    public static final RegistryObject<CreativeModeTab> MATERIALS = createTab("materials", DMItems.RAW_STEEL_INGOT);
    public static final RegistryObject<CreativeModeTab> FOOD = createTab("food", DMItems.GINGERBREAD);
    public static final RegistryObject<CreativeModeTab> CHRISTMAS = createTab("christmas", DMBlocks.CHRISTMAS_TREE);
    public static final RegistryObject<CreativeModeTab> ENTITIES = createTab("entities", DMItems.STEVE_EGG);
    public static final RegistryObject<CreativeModeTab> ROUNDELS = createTab("roundels", DMBlocks.ROUNDEL_DARK);
    public static final RegistryObject<CreativeModeTab> ANNIVERSARY = createTab("anniversary", DMBlocks.CAKE);
    public static final RegistryObject<CreativeModeTab> COMMUNITY = createTab("community", DMItems.POINTER);


    private static RegistryObject<CreativeModeTab> createTab(String id, RegistryObject<? extends ItemLike> icon){
        return CREATIVE_MOD_TABS.register(id, () ->
                CreativeModeTab.builder()
                        .icon(() -> new ItemStack(icon.get()))
                        .title(Component.translatable("itemGroup." + DocMod.MOD_ID + "." + id))
                        .build()
        );
    }

    private static void addItemsToCreativeTab(BuildCreativeModeTabContentsEvent event, TAB tab) {
        DMItems.ITEMS.getEntries().forEach((item) -> {
            if(DMItems.ITEM_TAB_MAP.get(item.getId().getPath()) == tab) {
                event.accept(item.get());
            }
        });
    }

    public static void addItemsToCreativeTabs(BuildCreativeModeTabContentsEvent e) {
        if(e.getTab() == MAIN.get()) {
            addItemsToCreativeTab(e, TAB.MAIN);
        }else if(e.getTab() == TOOLS.get()) {
            addItemsToCreativeTab(e, TAB.TOOLS);
        }else if(e.getTab() == MATERIALS.get()) {
            addItemsToCreativeTab(e, TAB.MATERIALS);
        }else if(e.getTab() == FOOD.get()) {
            addItemsToCreativeTab(e, TAB.FOOD);
        } else if(e.getTab() == CHRISTMAS.get()) {
            addItemsToCreativeTab(e, TAB.CHRISTMAS);
        } else if(e.getTab() == ENTITIES.get()) {
            addItemsToCreativeTab(e, TAB.ENTITIES);
        } else if(e.getTab() == FOOD.get()) {
            addItemsToCreativeTab(e, TAB.FOOD);
        } else if(e.getTab() == ROUNDELS.get()) {
            addItemsToCreativeTab(e, TAB.ROUNDELS);
        } else if(e.getTab() == ANNIVERSARY.get()) {
            addItemsToCreativeTab(e, TAB.ANNIVERSARY);
        } else if(e.getTab() == COMMUNITY.get()) {
            addItemsToCreativeTab(e, TAB.COMMUNITY);
        }
    }
}

