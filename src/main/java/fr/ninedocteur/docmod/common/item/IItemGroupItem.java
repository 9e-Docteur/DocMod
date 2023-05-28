package fr.ninedocteur.docmod.common.item;

import fr.ninedocteur.docmod.common.init.DMItemGroups;
import net.minecraft.item.Item;

public interface IItemGroupItem {
    DMItemGroups.DMItemGroup getItemGroup();
    Item getItem();
}
