package be.ninedocteur.docmod.common.item.cupdate;

import be.ninedocteur.docmod.common.content.IChristmasItem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ChristmasItem extends Item implements IChristmasItem {
    private int addedYear;

    public ChristmasItem(Properties p_41383_) {
        super(p_41383_);
    }
    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
        if(Screen.hasShiftDown() && getAddedYear() != 0){
            p_41423_.add(Component.literal(ChatFormatting.YELLOW + "Added in Christmas Update : "+ ChatFormatting.RESET + getAddedYear()));
        } else {
            p_41423_.add(Component.literal("Press "+ChatFormatting.YELLOW + "SHIFT "+ ChatFormatting.RESET + "to get more information"));
        }
    }

    @Override
    public int setAddedYear(int addedYear) {
        return this.addedYear = addedYear;
    }

    public int getAddedYear() {
        return addedYear;
    }
}
