package be.ninedocteur.docmod;

import be.ninedocteur.docmod.common.init.DMBlocks;
import be.ninedocteur.docmod.common.init.DMItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class DMCreativeTabs {
    public static final CreativeModeTab BETA = new CreativeModeTab("DocModBETA") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(DMBlocks.ZINC_BLOCK.get());
        }
    };

    public static final CreativeModeTab TOOLS = new CreativeModeTab("DocMod - Tools") {
        @Override
        public ItemStack makeIcon() {

            return new ItemStack(DMItems.ZINC_PICKAXE.get());
        }
    };

    public static final CreativeModeTab MATERIALS = new CreativeModeTab("DocMod - Materials") {
        @Override
        public ItemStack makeIcon() {

            return new ItemStack(Items.COPPER_INGOT);
        }
    };
    public static final CreativeModeTab FOOD = new CreativeModeTab("DocMod - Food") {
        @Override
        public ItemStack makeIcon() {

            return new ItemStack(DMItems.BELGIUM_FRIES.get());
        }
    };

    public static final CreativeModeTab CHRISTMAS = new CreativeModeTab("DocMod - Christmas Update") {
        @Override
        public ItemStack makeIcon() {

            return new ItemStack(DMBlocks.CHRISTMAS_TREE.get());
        }
    };

    public static final CreativeModeTab ENTITIES = new CreativeModeTab("DocMod - Entities") {
        @Override
        public ItemStack makeIcon() {

            return new ItemStack(DMItems.CYBERBOSS_EGG.get());
        }
    };

    public static final CreativeModeTab ROUNDEL = new CreativeModeTab("DocMod - Roundel") {
        @Override
        public ItemStack makeIcon() {

            return new ItemStack(DMBlocks.ROUNDEL_DARK.get());
        }
    };

//    public static final CreativeModeTab COMPUTER = new CreativeModeTab("DocMod - Computers") {
//        @Override
//        public ItemStack makeIcon() {
//
//            return new ItemStack(DMBlocks.COMPUTER.get());
//        }
//    };
    public static final CreativeModeTab ANNIVERSARY = new CreativeModeTab("DocMod - Anniversary Update") {
        @Override
        public ItemStack makeIcon() {

            return new ItemStack(DMBlocks.CAKE.get());
        }
    };
}

