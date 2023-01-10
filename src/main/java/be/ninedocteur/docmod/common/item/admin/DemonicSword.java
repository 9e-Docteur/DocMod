package be.ninedocteur.docmod.common.item.admin;

import be.ninedocteur.docmod.utils.DMDamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class DemonicSword extends AdminSwordItem {
    public DemonicSword(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
        super(p_43269_, p_43270_, p_43271_, p_43272_);
    }

    @Override
    public boolean hurtEnemy(ItemStack p_43278_, LivingEntity pTarget, LivingEntity p_43280_) {
        pTarget.die(DMDamageSource.DEMONIC);
        return super.hurtEnemy(p_43278_, pTarget, p_43280_);
    }
}
