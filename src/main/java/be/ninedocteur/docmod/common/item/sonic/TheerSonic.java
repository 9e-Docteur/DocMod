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
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;

public class TheerSonic extends SonicItem {
    public TheerSonic(Properties pProperties) {
        super(pProperties);
        //this.setupSonic();
        //this.setItemToReplaceWith(DMItems.THEER_SONIC_SCREWDRIVER_OFF.get()); //TO FIX
    }
}
