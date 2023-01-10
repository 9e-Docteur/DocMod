package be.ninedocteur.docmod.common.item.admin;

import be.ninedocteur.docmod.utils.TeamUUIDs;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class NinetyPartyWand extends SpecificAdminItem {
    public NinetyPartyWand(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        //ADD TUNAK TUNAK EASTER EGG
        return super.use(p_41432_, p_41433_, p_41434_);
    }
}
