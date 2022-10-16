package be.ninedocteur.docmod.common.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class WhitePointedStar extends Item {
    public WhitePointedStar(Properties pProperties) {
        super(pProperties);
    }

    public boolean isFoil(ItemStack pStack) {
        return true;
    }
}
