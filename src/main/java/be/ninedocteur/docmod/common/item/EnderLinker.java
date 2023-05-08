package be.ninedocteur.docmod.common.item;

import be.ninedocteur.docmod.common.block.EnderPadBlock;
import be.ninedocteur.docmod.common.tileentity.EnderPadTile;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;

public class EnderLinker extends Item{
	public boolean isEncoded;
	public BlockPos pos;

	public EnderLinker(Properties p_41383_) {
		super(p_41383_);
		// TODO Auto-generated constructor stub
		p_41383_.durability(1);
	}
	
	@Override
	public InteractionResult useOn(UseOnContext p_41427_) {
		BlockPos pos = p_41427_.getClickedPos();
		BlockState state = p_41427_.getLevel().getBlockState(pos);
		Player player = p_41427_.getPlayer();
		if(state.getBlock() instanceof EnderPadBlock block) {
			if(!isEncoded) {
				player.sendSystemMessage(Component.literal("§cYou must first click on the destination block!"));
			} else {
				EnderPadTile tile = (EnderPadTile) player.level.getBlockEntity(pos);
				tile.setLinkedPos(this.pos);
				tile.isLinked = true;
				player.sendSystemMessage(Component.literal("§eEncoded into the pad!"));
			}
		} else {
			isEncoded = true;
			this.pos = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
			
			player.sendSystemMessage(Component.literal("§eEncoded! Now right click on the ender pad!"));
		}
		
		
		return super.useOn(p_41427_);
	}
	
	@Override
	public boolean isFoil(ItemStack p_41453_) {
		return isEncoded;
	}

}
