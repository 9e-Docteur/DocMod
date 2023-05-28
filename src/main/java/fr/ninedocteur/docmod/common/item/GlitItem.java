package fr.ninedocteur.docmod.common.item;

import fr.ninedocteur.docmod.common.init.DMItemGroups;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GlitItem extends DMBaseItem {

    public GlitItem(Settings settings, DMItemGroups.DMItemGroup itemGroup) {
        super(settings, itemGroup);
    }

    @Override
    public boolean hasGlint(ItemStack pStack) {
        return true;
    }
}
