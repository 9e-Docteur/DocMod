package fr.ninedocteur.docmod.common.item;

import fr.ninedocteur.docmod.common.init.DMItemGroups;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;

public class DMArmorItem extends ArmorItem implements IItemGroupItem{
    public DMItemGroups.DMItemGroup itemGroup;

    public DMArmorItem(ArmorMaterial material, Type type, Settings settings, DMItemGroups.DMItemGroup itemGroup) {
        super(material, type, settings);
        this.itemGroup = itemGroup;
    }

    @Override
    public DMItemGroups.DMItemGroup getItemGroup() {
        return itemGroup;
    }

    @Override
    public Item getItem() {
        return this;
    }
}
