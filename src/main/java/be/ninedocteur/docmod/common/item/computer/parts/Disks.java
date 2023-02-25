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
    private final DiskType diskType;
    private String installedOS;
    public Disks(Properties p_41383_, DiskType diskType) {
        super(p_41383_);
        this.diskType = diskType;
    }

    //COMMAND SHOP BUY <os> VA DONNER UN TAG (tag nom = nom package de l'os) ET A CHAQUE DEMARAGE LE PC CHECK SI LE DISK A UN TAG, SI OUI IL BOOT SUR L'OS DU TAG.


    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        p_41423_.add(Component.literal(ChatFormatting.GOLD + "OS: " + ChatFormatting.RESET +  installedOS));
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
    }

    @Override
    public @Nullable CompoundTag getShareTag(ItemStack stack) {
        stack.getTag().putString("installedOS", installedOS);
        return super.getShareTag(stack);
    }

    @Override
    public void readShareTag(ItemStack stack, @Nullable CompoundTag nbt) {
        installedOS = nbt.getString("installedOS");
        super.readShareTag(stack, nbt);
    }

    public DiskType getDiskType() {
        return diskType;
    }

    public void setInstalledOS(String installedOS) {
        this.installedOS = installedOS;
    }

    public String getInstalledOS() {
        return installedOS;
    }
}
