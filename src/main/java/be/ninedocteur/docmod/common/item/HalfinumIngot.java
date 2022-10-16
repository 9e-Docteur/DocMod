package be.ninedocteur.docmod.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class HalfinumIngot extends Item {

    public HalfinumIngot(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.literal(ChatFormatting.OBFUSCATED  + "t" + ChatFormatting.RESET + "OMGGG"+ ChatFormatting.OBFUSCATED  + "t"));
    }
}
