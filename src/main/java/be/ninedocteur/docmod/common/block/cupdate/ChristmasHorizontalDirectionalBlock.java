package be.ninedocteur.docmod.common.block.cupdate;

import be.ninedocteur.docmod.common.block.ShiftInformationHorizontalDirectionalBlock;
import be.ninedocteur.docmod.common.content.IChristmasItem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class ChristmasHorizontalDirectionalBlock extends HorizontalDirectionalBlock implements IChristmasItem {
    private int addedYear;
    
    public ChristmasHorizontalDirectionalBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public void appendHoverText(ItemStack p_49816_, @Nullable BlockGetter p_49817_, List<Component> p_49818_, TooltipFlag p_49819_) {
        super.appendHoverText(p_49816_, p_49817_, p_49818_, p_49819_);
        if(Screen.hasShiftDown() && getAddedYear() != 0){
            p_49818_.add(Component.literal(ChatFormatting.YELLOW + "Added in Christmas Update : "+ ChatFormatting.RESET + getAddedYear()));
        } else {
            p_49818_.add(Component.literal("Press "+ChatFormatting.YELLOW + "SHIFT "+ ChatFormatting.RESET + "to get more information"));
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
