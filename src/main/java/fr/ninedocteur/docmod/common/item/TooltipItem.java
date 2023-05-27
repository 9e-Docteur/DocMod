package fr.ninedocteur.docmod.common.item;

import fr.ninedocteur.docmod.common.init.DMItemGroups;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;

public class TooltipItem extends DMBaseItem {
    public Text tooltip;

    public TooltipItem(Settings settings,DMItemGroups.DMItemGroup itemGroup, Text tooltip) {
        super(settings, itemGroup);
        this.tooltip = tooltip;
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(this.tooltip);
    }
}
