package be.ninedocteur.docmod.common.enchantement;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class StrikeEnchant extends Enchantment {
    protected StrikeEnchant() {
        super(Rarity.VERY_RARE, DMEnchantementsCategory.MAGIC_WAND, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    public int getMinCost(int p_45264_) {
        return 35;
    }

    public int getMaxCost(int p_45268_) {
        return super.getMinCost(p_45268_) + 50;
    }

    public int getMaxLevel() {
        return 1;
    }
}
