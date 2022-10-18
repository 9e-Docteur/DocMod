package be.ninedocteur.docmod.common.item;

import be.ninedocteur.docmod.common.init.DMItems;
import be.ninedocteur.docmod.utils.TeamUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.User;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class AdminItem extends Item {
    public AdminItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {
        super.inventoryTick(p_41404_, p_41405_, p_41406_, p_41407_, p_41408_);
        if(!p_41405_.isClientSide){
            if(p_41406_ instanceof Player player){
                Player pPlayer = player;
                User user = Minecraft.getInstance().getUser();
                TeamUtils.TeamMember teamMember = TeamUtils.getTeamMembers().get(user.getUuid());
                if(teamMember == teamMember){

                } else {
                    pPlayer.getInventory().clearOrCountMatchingItems(p -> new ItemStack(this).getItem() == p.getItem(), 1, pPlayer.inventoryMenu.getCraftSlots());
                    pPlayer.sendSystemMessage(Component.literal(ChatFormatting.RED + "You are not allowed to use this item."));
                }
            }
        }
    }
}
