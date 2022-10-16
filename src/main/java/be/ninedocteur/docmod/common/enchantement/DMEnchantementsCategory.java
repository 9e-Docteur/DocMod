package be.ninedocteur.docmod.common.enchantement;

import be.ninedocteur.docmod.common.item.gun.MagicWand;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class DMEnchantementsCategory /*extends EnchantmentCategory implements IExtensibleEnum*/ {
    /*

    MAGIC_WAND {
        public boolean canEnchant(Item p_44801_) {
            return p_44801_ instanceof MagicWand;
        }
    };

    private Predicate<Item> delegate;

    private DMEnchantementsCategory() {
    }

    private DMEnchantementsCategory(Predicate delegate) {
        this.delegate = delegate;
    }

    public static EnchantmentCategory create(String name, Predicate<Item> delegate) {
        throw new IllegalStateException("Enum not extended");
    }

    public boolean canEnchant(Item p_44743_) {
        return this.delegate == null ? false : this.delegate.test(p_44743_);
    }

    @Override
    public void init() {
        IExtensibleEnum.super.init();
    }


     */

    public static final EnchantmentCategory MAGIC_WAND = EnchantmentCategory.create("magic_wand", item -> item instanceof MagicWand);

}
