package be.ninedocteur.docmod.common.item.sonic;

import be.ninedocteur.docmod.common.block.DMRedstoneLampOn;
import be.ninedocteur.docmod.common.init.DMItems;
import be.ninedocteur.docmod.common.sound.DMSound;
import be.ninedocteur.docmod.utils.KeyUtils;
import be.ninedocteur.docmod.sonic.SonicInteractionItem;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;

public class TheerSonicOff extends SonicInteractionItem {
    public TheerSonicOff(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Player pPlayer = pContext.getPlayer();
        if(KeyUtils.hasShiftDown()){
            pPlayer.getInventory().clearOrCountMatchingItems(p -> new ItemStack(DMItems.THEER_SONIC_SCREWDRIVER_OFF.get()).getItem() == p.getItem(), 1, pPlayer.inventoryMenu.getCraftSlots());
            pPlayer.getInventory().add(new ItemStack(DMItems.THEER_SONIC_SCREWDRIVER.get()));
        }
        return super.useOn(pContext);
    }
}
