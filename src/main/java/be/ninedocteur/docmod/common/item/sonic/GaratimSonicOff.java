package be.ninedocteur.docmod.common.item.sonic;

import be.ninedocteur.docmod.common.block.DMRedstoneLampOn;
import be.ninedocteur.docmod.common.init.DMItems;
import be.ninedocteur.docmod.common.sound.DMSound;
import be.ninedocteur.docmod.sonic.SonicInteractionItem;
import be.ninedocteur.docmod.utils.KeyUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GaratimSonicOff extends SonicInteractionItem {
    public GaratimSonicOff(Properties pProperties) {
        super(pProperties);
    }


    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.literal(ChatFormatting.YELLOW  + "Made by: " + ChatFormatting.WHITE + "Garatim"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }


    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Player pPlayer = pContext.getPlayer();
        if(KeyUtils.hasShiftDown()){
            pPlayer.getInventory().clearOrCountMatchingItems(p -> new ItemStack(DMItems.GARATIM_SONIC_SCREWDRIVER_OFF.get()).getItem() == p.getItem(), 1, pPlayer.inventoryMenu.getCraftSlots());
            pPlayer.getInventory().add(new ItemStack(DMItems.GARATIM_SONIC_SCREWDRIVER.get()));
        }
        return super.useOn(pContext);
    }
}
