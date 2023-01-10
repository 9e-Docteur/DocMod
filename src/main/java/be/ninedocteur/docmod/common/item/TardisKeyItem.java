package be.ninedocteur.docmod.common.item;

import be.ninedocteur.docmod.common.tileentity.TardisTileEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class TardisKeyItem extends Item {
    public TardisKeyItem(Properties p_41383_) {
        super(p_41383_);
    }
//
//    @Override
//    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
//        if(p_41421_.hasTag()){
//            p_41423_.add(Component.literal(ChatFormatting.GREEN + "Linked to the tardis: " + ChatFormatting.YELLOW + p_41421_.getTag().get("linked_tardis_id")));
//        } else {
//            p_41423_.add(Component.literal(ChatFormatting.RED + "Key not linked to a tardis"));
//        }
//    }
//
//    @Override
//    public InteractionResult useOn(UseOnContext p_41427_) {
//        ItemStack item = p_41427_.getItemInHand();
//        BlockPos clickedPos = p_41427_.getClickedPos();
//        Level level = p_41427_.getPlayer().level;
//        if(item.hasTag()) {
//            TardisTileEntity tardisTileEntity = TardisTileEntity.getOrCreateTardis(item.getTag().getInt("linked_tardis_id"));
//            //tardisTileEntity.setTargetDimension(level);
//            tardisTileEntity.setTargetPosition(clickedPos);
//            tardisTileEntity.demat();
//            tardisTileEntity.remat();
//        }
//        return super.useOn(p_41427_);
//    }
}
