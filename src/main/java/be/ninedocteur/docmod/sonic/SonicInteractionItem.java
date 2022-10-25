package be.ninedocteur.docmod.sonic;

import be.ninedocteur.docmod.common.init.DMBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;

public class SonicInteractionItem extends Item {

    public SonicInteractionItem(Properties pProperties) {
        super(pProperties);
    }

    public static class Interaction implements IDoorInteraction{

        @Override
        public void blockInteraction(Level pLevel, Player pPlayer, ItemStack pStack, BlockPos pos, BlockState state) {
            if(state.getBlock() instanceof DoorBlock){
                openDoor(pLevel, state, pos, !((Boolean)state.getValue((Property)DoorBlock.OPEN)).booleanValue());
                ((DoorBlock)state.getBlock()).setOpen(pPlayer, pLevel, state, pos, !((Boolean)state.getValue((Property)DoorBlock.OPEN)).booleanValue());
            }
        }
    }

    public static class TNTInteraction implements IDoorInteraction{

        @Override
        public void blockInteraction(Level pLevel, Player pPlayer, ItemStack pStack, BlockPos pos, BlockState state) {
            pLevel.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
            Blocks.TNT.onCaughtFire(pLevel.getBlockState(pos), pLevel, pos, Direction.UP, pPlayer);
        }
    }

    public static void openDoor(Level pLevel, BlockState state, BlockPos pos, boolean isOpen){
        if(state.is(state.getBlock()) && ((Boolean)state.getValue((Property)DoorBlock.OPEN)).booleanValue() != isOpen){
            pLevel.setBlock(pos, state.setValue(DoorBlock.OPEN, Boolean.valueOf(isOpen)), 10);
            pLevel.levelEvent( null, isOpen ? 1 : 1, pos, 0);
        }
    }

    public class RedstoneLamp {
        public static class LampOn implements IDoorInteraction{

            @Override
            public void blockInteraction(Level pLevel, Player pPlayer, ItemStack pStack, BlockPos pos, BlockState state) {
                pLevel.setBlockAndUpdate(pos, DMBlocks.REDSTONE_LAMP_ON.get().defaultBlockState());
            }
        }
        public static class LampOff implements IDoorInteraction{

            @Override
            public void blockInteraction(Level pLevel, Player pPlayer, ItemStack pStack, BlockPos pos, BlockState state) {
                pLevel.setBlockAndUpdate(pos, Blocks.REDSTONE_LAMP.defaultBlockState());
            }
        }
    }

    public class OreSmelting{
        public static class IronOre implements IDoorInteraction{

            @Override
            public void blockInteraction(Level pLevel, Player pPlayer, ItemStack pStack, BlockPos pos, BlockState state) {
                pLevel.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                pPlayer.getInventory().add(new ItemStack(Items.IRON_INGOT));
            }
        }
        public static class DiamondOre implements IDoorInteraction{

            @Override
            public void blockInteraction(Level pLevel, Player pPlayer, ItemStack pStack, BlockPos pos, BlockState state) {
                pLevel.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                pPlayer.getInventory().add(new ItemStack(Items.DIAMOND));
            }
        }
        public static class GoldOre implements IDoorInteraction{

            @Override
            public void blockInteraction(Level pLevel, Player pPlayer, ItemStack pStack, BlockPos pos, BlockState state) {
                pLevel.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                pPlayer.getInventory().add(new ItemStack(Items.GOLD_INGOT));
            }
        }
        public static class CoalOre implements IDoorInteraction{

            @Override
            public void blockInteraction(Level pLevel, Player pPlayer, ItemStack pStack, BlockPos pos, BlockState state) {
                pLevel.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                pPlayer.getInventory().add(new ItemStack(Items.COAL));
            }
        }
    }
    public interface IDoorInteraction{
        void blockInteraction(Level pLevel, Player pPlayer, ItemStack pStack, BlockPos pos, BlockState state);
    }
    
    public class GlassInteraction implements IDoorInteraction {
    	@Override
        public void blockInteraction(Level pLevel, Player pPlayer, ItemStack pStack, BlockPos pos, BlockState state) {
            if(state.getBlock() instanceof GlassBlock glassBlock){
            	pLevel.destroyBlock(pos, false);
            }
        }
    }
}
