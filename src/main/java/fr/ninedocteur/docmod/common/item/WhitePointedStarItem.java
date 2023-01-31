package fr.ninedocteur.docmod.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class WhitePointedStarItem extends Item{
    public WhitePointedStarItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack pStack) {
        return true;
    }
}
