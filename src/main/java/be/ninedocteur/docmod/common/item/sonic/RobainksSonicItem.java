package be.ninedocteur.docmod.common.item.sonic;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

public class RobainksSonicItem extends be.ninedocteur.docmod.common.item.sonic.SonicItem {
    public RobainksSonicItem(Properties pProperties) {
        super(pProperties);
        this.setupSonic(getDefaultSonicSound(), false, false, true, true, true, true, false);
        this.addCredits(Component.literal(ChatFormatting.YELLOW  + "Made by: " + ChatFormatting.WHITE + "Robainks"));
    }
}
