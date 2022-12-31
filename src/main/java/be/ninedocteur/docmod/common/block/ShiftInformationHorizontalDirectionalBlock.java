package be.ninedocteur.docmod.common.block;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class ShiftInformationHorizontalDirectionalBlock extends HorizontalDirectionalBlock {
    private Component line;
    public ShiftInformationHorizontalDirectionalBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public void appendHoverText(ItemStack p_49816_, @Nullable BlockGetter p_49817_, List<Component> p_49818_, TooltipFlag p_49819_) {
        super.appendHoverText(p_49816_, p_49817_, p_49818_, p_49819_);
        if(line != null){
            if(Screen.hasShiftDown()){
                p_49818_.add(line);
            } else {
                p_49818_.add(Component.literal("Press "+ ChatFormatting.YELLOW + "SHIFT "+ ChatFormatting.RESET + "to get more information"));
            }
        }
    }

    public Component setLine(Component line) {
       return this.line = line;
    }

    public Component getLine() {
        return line;
    }
}
