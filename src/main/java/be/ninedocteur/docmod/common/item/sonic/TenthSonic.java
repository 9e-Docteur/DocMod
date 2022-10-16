package be.ninedocteur.docmod.common.item.sonic;

import be.ninedocteur.docmod.common.block.DMRedstoneLampOn;
import be.ninedocteur.docmod.common.init.DMItems;
import be.ninedocteur.docmod.common.sound.DMSound;
import be.ninedocteur.docmod.utils.KeyUtils;
import be.ninedocteur.docmod.sonic.SonicInteractionItem;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;

public class TenthSonic extends SonicInteractionItem {
    public TenthSonic(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level pLevel = pContext.getLevel();
        Player pPlayer = pContext.getPlayer();
        BlockPos pos = pContext.getClickedPos();
        BlockState state = pContext.getLevel().getBlockState(pos);
        InteractionHand pHand = Minecraft.getInstance().player.getUsedItemHand();
        ItemStack pStack = pContext.getItemInHand();
        Interaction interaction = new Interaction();
        TNTInteraction tntinteraction = new TNTInteraction();
        RedstoneLamp.LampOn redstoneLampOn = new RedstoneLamp.LampOn();
        RedstoneLamp.LampOff redstoneLampOff= new RedstoneLamp.LampOff();
        OreSmelting.IronOre ironOreSmelt = new OreSmelting.IronOre();
        if(state.getBlock() instanceof DoorBlock) {
            interaction.blockInteraction(pLevel, pPlayer, ItemStack.EMPTY, pos, pLevel.getBlockState(pos));
            pPlayer.getCooldowns().addCooldown(this, 5);
            pLevel.playSound((Player)null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), DMSound.SONIC.get(), SoundSource.AMBIENT, 1.0F, 3.0F / (pLevel.getRandom().nextFloat() * 0.4F + 1.2F) * 0.5F);
            //if (!pPlayer.isCreative())
            //pPlayer.giveExperienceLevels(-1);

        } else if(state.getBlock() instanceof TntBlock) {
            tntinteraction.blockInteraction(pLevel, pPlayer, ItemStack.EMPTY, pos, pLevel.getBlockState(pos));
            pPlayer.getCooldowns().addCooldown(this, 20);
            pLevel.playSound((Player)null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), DMSound.SONIC.get(), SoundSource.AMBIENT, 1.0F, 4.0F / (pLevel.getRandom().nextFloat() * 0.4F + 1.2F) * 0.5F);
            // if (!pPlayer.isCreative())
            // pPlayer.giveExperienceLevels(-4);
        } else if(state.getBlock() instanceof RedstoneLampBlock) {
            redstoneLampOn.blockInteraction(pLevel, pPlayer, ItemStack.EMPTY, pos, pLevel.getBlockState(pos));
            pPlayer.getCooldowns().addCooldown(this, 2);
            pLevel.playSound((Player) null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), DMSound.SONIC.get(), SoundSource.AMBIENT, 1.0F, 3.0F / (pLevel.getRandom().nextFloat() * 0.4F + 1.2F) * 0.5F);
            // if (!pPlayer.isCreative())
            // pPlayer.giveExperienceLevels(-4);
        }else if(state.getBlock() instanceof DMRedstoneLampOn) {
            redstoneLampOff.blockInteraction(pLevel, pPlayer, ItemStack.EMPTY, pos, pLevel.getBlockState(pos));
            pPlayer.getCooldowns().addCooldown(this, 2);
            pLevel.playSound((Player) null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), DMSound.SONIC.get(), SoundSource.AMBIENT, 1.0F, 3.0F / (pLevel.getRandom().nextFloat() * 0.4F + 1.2F) * 0.5F);
            // if (!pPlayer.isCreative())
            //   pPlayer.giveExperienceLevels(-4);
       /* }else if(state.getBlock() instanceof OreBlock) {
            ironOreSmelt.blockInteraction(pLevel, pPlayer, ItemStack.EMPTY, pos, pLevel.getBlockState(pos));
            pPlayer.getCooldowns().addCooldown(this, 2);
            pLevel.playSound((Player) null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), DMSound.SONIC.get(), SoundSource.AMBIENT, 1.0F, 3.0F / (pLevel.getRandom().nextFloat() * 0.4F + 1.2F) * 0.5F);
            //if (!pPlayer.isCreative())
            //pPlayer.giveExperienceLevels(-4);

        */
        }
        pLevel.playSound((Player)null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), DMSound.SONIC.get(), SoundSource.AMBIENT, 1.0F, 3.0F / (pLevel.getRandom().nextFloat() * 0.4F + 1.2F) * 0.5F);
        if(KeyUtils.hasShiftDown()){
            pPlayer.getInventory().clearOrCountMatchingItems(p -> new ItemStack(DMItems.TEN_SONIC_SCREWDRIVER.get()).getItem() == p.getItem(), 1, pPlayer.inventoryMenu.getCraftSlots());
            pPlayer.getInventory().add(new ItemStack(DMItems.TEN_SONIC_SCREWDRIVER_EXTENDED.get()));
        }
        return super.useOn(pContext);
    }
}
