package be.ninedocteur.docmod.common.item.computer.parts;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Disks extends Item {
    public Disks(Properties p_41383_) {
        super(p_41383_);
    }

    //COMMAND SHOP BUY <os> VA DONNER UN TAG (tag nom = nom package de l'os) ET A CHAQUE DEMARAGE LE PC CHECK SI LE DISK A UN TAG, SI OUI IL BOOT SUR L'OS DU TAG.


    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        CompoundTag compoundTag = new CompoundTag();
        p_41423_.add(Component.literal(ChatFormatting.GOLD + "OS " + compoundTag.getCompound("OS")));
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
    }
}
