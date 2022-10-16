package be.ninedocteur.docmod.common.item.computer.parts;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Prosessor extends Item {

    public static boolean integratedGraphics;

    public Prosessor(Properties p_41383_, boolean integratedGraphics) {
        super(p_41383_);
        integratedGraphics = Prosessor.integratedGraphics;
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        if(integratedGraphics) {
            p_41423_.add(Component.literal("Integrated Graphics"));
        }
    }
}
