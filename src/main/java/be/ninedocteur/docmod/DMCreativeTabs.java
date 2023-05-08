package be.ninedocteur.docmod;

import be.ninedocteur.docmod.common.init.DMBlocks;
import be.ninedocteur.docmod.common.init.DMItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DocMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DMCreativeTabs {
    public static CreativeModeTab DOCMOD_MAIN;
    public static CreativeModeTab TOOLS;
    public static CreativeModeTab MATERIALS;
    public static CreativeModeTab FOOD;
    public static CreativeModeTab CHRISTMAS;
    public static CreativeModeTab ENTITIES;
    public static CreativeModeTab ROUNDELS;
    public static CreativeModeTab ANNIVERSARY;
    public static CreativeModeTab COMMUNITY;


    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event){
        DOCMOD_MAIN = event.registerCreativeModeTab(new ResourceLocation(DocMod.MOD_ID, "docmod_main"), builder -> builder.icon(() -> new ItemStack(DMItems.ZINC_INGOT.get())).title(Component.translatable("DocMod Main Tab")).build());
        TOOLS = event.registerCreativeModeTab(new ResourceLocation(DocMod.MOD_ID, "tools"), builder -> builder.icon(() -> new ItemStack(DMItems.ZINC_PICKAXE.get())).title(Component.translatable("Tools Tab")).build());
        MATERIALS = event.registerCreativeModeTab(new ResourceLocation(DocMod.MOD_ID, "materials"), builder -> builder.icon(() -> new ItemStack(Items.COPPER_INGOT)).title(Component.translatable("Materials Tab")).build());
        FOOD = event.registerCreativeModeTab(new ResourceLocation(DocMod.MOD_ID, "food"), builder -> builder.icon(() -> new ItemStack(DMItems.BELGIUM_FRIES.get())).title(Component.translatable("Food Tab")).build());
        CHRISTMAS = event.registerCreativeModeTab(new ResourceLocation(DocMod.MOD_ID, "christmas"), builder -> builder.icon(() -> new ItemStack(DMBlocks.CHRISTMAS_TREE.get())).title(Component.translatable("Christmas Tab")).build());
        ENTITIES = event.registerCreativeModeTab(new ResourceLocation(DocMod.MOD_ID, "entities"), builder -> builder.icon(() -> new ItemStack(DMItems.CYBERBOSS_EGG.get())).title(Component.translatable("Entities Tab")).build());
        ROUNDELS = event.registerCreativeModeTab(new ResourceLocation(DocMod.MOD_ID, "roundels"), builder -> builder.icon(() -> new ItemStack(DMBlocks.ROUNDEL_DARK_2.get())).title(Component.translatable("Roundels Tab")).build());
        ANNIVERSARY = event.registerCreativeModeTab(new ResourceLocation(DocMod.MOD_ID, "anniversary"), builder -> builder.icon(() -> new ItemStack(DMBlocks.CAKE.get())).title(Component.translatable("Anniversary Tab")).build());
        COMMUNITY = event.registerCreativeModeTab(new ResourceLocation(DocMod.MOD_ID, "community"), builder -> builder.icon(() -> new ItemStack(DMItems.GARATIM_SONIC_SCREWDRIVER.get())).title(Component.translatable("Community Tab")).build());
    }
}

