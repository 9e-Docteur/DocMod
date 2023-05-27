package fr.ninedocteur.docmod.common.item;

import fr.ninedocteur.docmod.common.init.DMItemGroups;
import net.minecraft.item.Item;

public class DMBaseItem extends Item {
    public DMItemGroups.DMItemGroup itemGroup;

    public DMBaseItem(Settings settings, DMItemGroups.DMItemGroup itemGroup) {
        super(settings);
        this.itemGroup = itemGroup;
    }
}
