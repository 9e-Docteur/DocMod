package fr.ninedocteur.docmod.common.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class HalfinumIngotItem extends Item {
    public HalfinumIngotItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal(Formatting.OBFUSCATED  + "t" + Formatting.RESET + "OMGGG"+ Formatting.OBFUSCATED  + "t"));
    }
}
