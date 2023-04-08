package be.ninedocteur.docmod.common.item.sonic;

import be.ninedocteur.docmod.common.block.DMRedstoneLampOn;
import be.ninedocteur.docmod.common.sound.DMSound;
import be.ninedocteur.docmod.sonic.SonicInteractionItem;
import be.ninedocteur.docmod.utils.KeyUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SonicItem extends Item {
    private boolean oreInteract;
    private boolean blockInteract;
    private boolean doorInteract;
    private boolean redstoneInteract;
    private boolean repairAnvilInteraction;
    private boolean doesHaveOtherVersion;
    private ItemLike itemToReplaceWith;
    private SonicItem baseItem;

    private boolean tntInteract;
    private boolean isReplaced;
    private boolean shearInteract;
    private int charge;
    private SoundEvent sonicSound;
    private boolean haveCredits;
    private Component credits;
    private boolean haveChargeUpgrade;
    private int chargeUpgradeLevel;
    private int chargeToZeroCount;
    private int maxCharge;
    private int sonicLevel;
    private int damageLevel;
    private int xp;
    private Mode mode;
    CompoundTag tag = Minecraft.getInstance().player.getPersistentData();

    public SonicItem(Properties p_41383_) {
        super(p_41383_);
        this.doesHaveOtherVersion = false;
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> tooltip, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_41422_, tooltip, p_41424_);
        tooltip.add(Component.literal(ChatFormatting.GOLD + "Charge: " + ChatFormatting.WHITE + this.charge));
        tooltip.add(Component.literal(ChatFormatting.GOLD + "Level: " + ChatFormatting.WHITE + this.getSonicLevel()));
        tooltip.add(Component.literal(ChatFormatting.GOLD + "XP: " + ChatFormatting.WHITE + this.getXp()));
        tooltip.add(Component.literal(getColorForIndication(Interaction.ORE) + "Ore"));
        tooltip.add(Component.literal(getColorForIndication(Interaction.DOOR) + "Door"));
        tooltip.add(Component.literal(getColorForIndication(Interaction.REDSTONE) + "Redstone"));
        tooltip.add(Component.literal(getColorForIndication(Interaction.ANVIL) + "Anvil"));
        tooltip.add(Component.literal(getColorForIndication(Interaction.TNT) + "Tnt"));
        tooltip.add(Component.literal(getColorForIndication(Interaction.SHEARS) + "Shears"));
        tooltip.add(Component.literal(getColorForIndication(Interaction.BLOCK) + "Block"));
        if(isHaveCredits()){
            tooltip.add(getCredits());
        }
    }
    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level pLevel = pContext.getLevel();
        Player pPlayer = pContext.getPlayer();
        BlockPos pos = pContext.getClickedPos();
        BlockState state = pContext.getLevel().getBlockState(pos);
        avoidSoundToBeNull();
        if(charge > 0 && !isReplaced){
            if(!pPlayer.isCrouching()){
                oreInteraction(state, pLevel, pContext);
                blockInteraction(state, pLevel, pPlayer, pContext);
                doorInteraction(state, pLevel, pPlayer, pContext);
                redstoneInteraction(state, pLevel, pPlayer, pContext);
                tntInteraction(state, pLevel, pPlayer, pContext);
                anvilInteraction(state, pLevel, pPlayer, pContext);
            } else {
                changeMode();
                pPlayer.sendSystemMessage(Component.literal("Set sonic mode to :" + getMode()));
            }
        }
        if(isDoesHaveOtherVersion()){
            replaceSonicOnSnick(this, getItemToReplaceWith(), pPlayer);
        }

        if(isDoesHaveOtherVersion() && isReplaced()){
            replaceSonicOnSnick(this, getBaseItem(), pPlayer);
        }
        pLevel.playSound(pPlayer, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), getSonicSound(), SoundSource.AMBIENT, 1.0F, 3.0F / (pLevel.getRandom().nextFloat() * 0.4F + 1.2F) * 0.5F);
        return super.useOn(pContext);
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Mode changeMode(){
        if(getMode() == Mode.SIMPLE){
            return Mode.ADVANCED;
        } else {
            return Mode.SIMPLE;
        }
    }

    public Mode getMode() {
        return mode;
    }
    public void oreInteraction(BlockState state, Level level, UseOnContext context) {
        BlockPos pos = context.getClickedPos();
        if (oreInteract && sonicLevel >= 3){
            if (state.getBlock() == Blocks.COAL_ORE) {
                dropItem(level, pos, Items.COAL.getDefaultInstance(), 1);
                level.removeBlock(pos, false);
                addXp(1);
            } else if (state.getBlock() == Blocks.DIAMOND_ORE) {
                dropItem(level, pos, Items.DIAMOND.getDefaultInstance(), 3);
                level.removeBlock(pos, false);
                addXp(5);
            } else if (state.getBlock() == Blocks.REDSTONE_ORE) {
                dropItem(level, pos, Items.REDSTONE.getDefaultInstance(), 1);
                level.removeBlock(pos, false);
                addXp(1);
            } else if (state.getBlock() == Blocks.GOLD_ORE) {
                dropItem(level, pos, Items.GOLD_INGOT.getDefaultInstance(), 2);
                level.removeBlock(pos, false);
                addXp(3);
            }
        }
    }

    public void blockInteraction(BlockState state, Level level, Player player, UseOnContext context){
        BlockPos pos = context.getClickedPos();
        if(blockInteract && sonicLevel >= 1) {
            if(state.getBlock() == Blocks.GLASS){
                level.removeBlock(pos, true);
            }
        }
    }
    public void doorInteraction(BlockState state, Level level, Player player, UseOnContext context){
        BlockPos pos = context.getClickedPos();
        SonicInteractionItem.Interaction interaction = new SonicInteractionItem.Interaction();
        if(state.getBlock() instanceof DoorBlock && doorInteract) {
            interaction.blockInteraction(level, player, ItemStack.EMPTY, pos, level.getBlockState(pos));
            player.getCooldowns().addCooldown(this, 5);
            removeCharge(1);
        }
    }

    public void redstoneInteraction(BlockState state, Level level, Player player, UseOnContext context){
        BlockPos pos = context.getClickedPos();
        SonicInteractionItem.RedstoneLamp.LampOn redstoneLampOn = new SonicInteractionItem.RedstoneLamp.LampOn();
        SonicInteractionItem.RedstoneLamp.LampOff redstoneLampOff= new SonicInteractionItem.RedstoneLamp.LampOff();
        if(state.getBlock() instanceof RedstoneLampBlock && redstoneInteract) {
            redstoneLampOn.blockInteraction(level, player, ItemStack.EMPTY, pos, level.getBlockState(pos));
            player.getCooldowns().addCooldown(this, 2);
            removeCharge(2);
        }else if(state.getBlock() instanceof DMRedstoneLampOn && redstoneInteract) {
            redstoneLampOff.blockInteraction(level, player, ItemStack.EMPTY, pos, level.getBlockState(pos));
            player.getCooldowns().addCooldown(this, 2);
            removeCharge(2);
            addXp(1);
        }
    }

    public void tntInteraction(BlockState state, Level level, Player player, UseOnContext context){
        BlockPos pos = context.getClickedPos();
        SonicInteractionItem.TNTInteraction tntinteraction = new SonicInteractionItem.TNTInteraction();
        if(tntInteract && sonicLevel >= 2) {
            if (state.getBlock() instanceof TntBlock && tntInteract) {
                tntinteraction.blockInteraction(level, player, ItemStack.EMPTY, pos, level.getBlockState(pos));
                player.getCooldowns().addCooldown(this, 20);
                removeCharge(5);
                addXp(2);
            }
        }
    }

    public void anvilInteraction(BlockState state, Level level, Player player, UseOnContext context){
        BlockPos pos = context.getClickedPos();
        SonicInteractionItem.TNTInteraction tntinteraction = new SonicInteractionItem.TNTInteraction();
        if(repairAnvilInteraction && sonicLevel >= 2) {
            if (state.getBlock() == Blocks.DAMAGED_ANVIL && repairAnvilInteraction) {
                level.removeBlock(pos, false);
                level.setBlock(pos, Blocks.CHIPPED_ANVIL.defaultBlockState(), 1);
                removeCharge(7);
                addXp(5);
            } else if (state.getBlock() == Blocks.CHIPPED_ANVIL && repairAnvilInteraction) {
                level.removeBlock(pos, false);
                level.setBlock(pos, Blocks.ANVIL.defaultBlockState(), 1);
                removeCharge(7);
                addXp(2);
            }
        }
    }

    public void dropItem(Level level, BlockPos pos, ItemStack itemStack){
        level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), itemStack, 0.1D, 0.1D, 0.1D));
    }

    public void dropItem(Level level, BlockPos pos, ItemStack itemStack, int removeCharge){
        if(!(getMode() == Mode.ADVANCED)){
            level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), itemStack, 0.1D, 0.1D, 0.1D));
        } else {
            level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), itemStack, 1D, 1D, 1D));
        }
        removeCharge(removeCharge);
    }

    @Override
    public net.minecraft.world.InteractionResult interactLivingEntity(ItemStack stack, net.minecraft.world.entity.player.Player playerIn, LivingEntity entity, net.minecraft.world.InteractionHand hand) {
        if(!isReplaced() && charge > 0 && shearInteract && sonicLevel >= 2){
            if (entity instanceof net.minecraftforge.common.IForgeShearable target) {
                if (entity.level.isClientSide) return net.minecraft.world.InteractionResult.SUCCESS;
                BlockPos pos = new BlockPos(entity.getX(), entity.getY(), entity.getZ());
                if (target.isShearable(stack, entity.level, pos)) {
                    java.util.List<ItemStack> drops = target.onSheared(playerIn, stack, entity.level, pos,
                            net.minecraft.world.item.enchantment.EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.world.item.enchantment.Enchantments.BLOCK_FORTUNE, stack));
                    java.util.Random rand = new java.util.Random();
                    drops.forEach(d -> {
                        net.minecraft.world.entity.item.ItemEntity ent = entity.spawnAtLocation(d, 1.0F);
                        ent.setDeltaMovement(ent.getDeltaMovement().add((double)((rand.nextFloat() - rand.nextFloat()) * 0.1F), (double)(rand.nextFloat() * 0.05F), (double)((rand.nextFloat() - rand.nextFloat()) * 0.1F)));
                    });
                    this.removeCharge(5);
                }
                return net.minecraft.world.InteractionResult.SUCCESS;
            }
            return net.minecraft.world.InteractionResult.PASS;
        }
        return InteractionResult.PASS;
    }


    public void setupSonic(SoundEvent sonicSound, boolean shearInteract, boolean blockInteract, boolean oreInteract, boolean doorInteract, boolean tntInteract, boolean redstoneInteract, boolean repairAnvilInteraction){
        setShearInteract(shearInteract);
        setBlockInteract(blockInteract);
        setSonicSound(sonicSound);
        setDoorInteract(doorInteract);
        setTntInteract(tntInteract);
        setOreInteract(oreInteract);
        setRedstoneInteract(redstoneInteract);
        setRepairAnvilInteraction(repairAnvilInteraction);
    }

    public void avoidSoundToBeNull(){
        if(sonicSound == null){
            getDefaultSonicSound();
        }
    }

    public void addCredits(Component component){
        haveCredits = true;
        this.credits = component;
    }

    public Component getCredits() {
        return credits;
    }

    public boolean isHaveCredits() {
        return haveCredits;
    }

    public void replaceSonicOnSnick(ItemLike thisItem, ItemLike itemToReplace, Player player){
        if(KeyUtils.hasShiftDown()){
            this.isReplaced = true;
            if(itemToReplace instanceof SonicItem sonicItem){
                player.getInventory().clearOrCountMatchingItems(p -> new ItemStack(thisItem).getItem() == p.getItem(), 1, player.inventoryMenu.getCraftSlots());
                player.getInventory().add(new ItemStack(itemToReplace));
                sonicItem.isReplaced = true;
                sonicItem.setCharge(this.charge);
                sonicItem.setBaseItem(this);
                sonicItem.setDoesHaveOtherVersion(true);
                sonicItem.addCredits(this.getCredits());
            } else {
                player.getInventory().clearOrCountMatchingItems(p -> new ItemStack(thisItem).getItem() == p.getItem(), 1, player.inventoryMenu.getCraftSlots());
                player.getInventory().add(new ItemStack(itemToReplace));
            }
        }
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public void removeCharge(int toRemove){
        this.charge = this.charge - toRemove;
    }

    public void addCharge(int toAdd){
        this.charge = this.charge + toAdd;
    }

    public void setItemToReplaceWith(ItemLike itemToReplaceWith) {
        this.itemToReplaceWith = itemToReplaceWith;
    }

    public ItemLike getItemToReplaceWith() {
        return itemToReplaceWith;
    }

    public boolean isDoesHaveOtherVersion() {
        return doesHaveOtherVersion;
    }

    public void setDoesHaveOtherVersion(boolean doesHaveOtherVersion) {
        this.doesHaveOtherVersion = doesHaveOtherVersion;
    }

    public int getCharge() {
        return charge;
    }

    public boolean doesInteractWith(Interaction interaction){
        if(interaction == Interaction.DOOR){
            if(this.doorInteract){
                return true;
            } else {
                return false;
            }
        } else if(interaction == Interaction.ORE){
            if(this.oreInteract){
                return true;
            } else {
                return false;
            }
        } else if(interaction == Interaction.ANVIL){
            if(this.repairAnvilInteraction){
                return true;
            } else {
                return false;
            }
        } else if(interaction == Interaction.TNT){
            if(this.tntInteract){
                return true;
            } else {
                return false;
            }
        } else if(interaction == Interaction.REDSTONE){
            if(this.redstoneInteract){
                return true;
            } else {
                return false;
            }
        } else if(interaction == Interaction.SHEARS){
            if(this.shearInteract){
                return true;
            } else {
                return false;
            }
        } else if(interaction == Interaction.BLOCK){
            if(this.blockInteract){
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public ChatFormatting getColorForIndication(Interaction interaction){
        if(interaction == Interaction.ANVIL && doesInteractWith(Interaction.ANVIL) && sonicLevel >= 2){
            return ChatFormatting.GREEN;
        }
        else if(interaction == Interaction.REDSTONE && doesInteractWith(Interaction.REDSTONE)){
            return ChatFormatting.GREEN;
        }
        else if(interaction == Interaction.ORE && doesInteractWith(Interaction.ORE) && sonicLevel >= 3){
            return ChatFormatting.GREEN;
        }
        else if(interaction == Interaction.TNT && doesInteractWith(Interaction.TNT) && sonicLevel >= 2){
            return ChatFormatting.GREEN;
        }
        else if(interaction == Interaction.DOOR && doesInteractWith(Interaction.DOOR)){
            return ChatFormatting.GREEN;
        } else if(interaction == Interaction.SHEARS && doesInteractWith(Interaction.SHEARS) && sonicLevel >= 2){
            return ChatFormatting.GREEN;
        } else if(interaction == Interaction.BLOCK && doesInteractWith(Interaction.BLOCK) && sonicLevel >= 1){
            return ChatFormatting.GREEN;
        } else {
            return ChatFormatting.RED;
        }
    }

    @Override
    public @Nullable CompoundTag getShareTag(ItemStack stack) {
//        stack.getTag().putInt("charge", this.getCharge());
//        stack.getTag().putInt("tozerocount", this.chargeToZeroCount);
//        stack.getTag().putInt("level", this.getSonicLevel());
//        stack.getTag().putInt("xp", this.getXp());
        tag.putInt("charge", this.getCharge());
        tag.putInt("tozerocount", this.chargeToZeroCount);
        tag.putInt("level", this.getSonicLevel());
        tag.putInt("xp", this.getXp());
        return super.getShareTag(stack);
    }

    @Override
    public void onCraftedBy(ItemStack p_41447_, Level p_41448_, Player p_41449_) {
        super.onCraftedBy(p_41447_, p_41448_, p_41449_);
        this.charge = zeroCountForCharge();
    }

    @Override
    public void readShareTag(ItemStack stack, @Nullable CompoundTag nbt) {
        super.readShareTag(stack, nbt);
        this.charge = tag.getInt("charge");
        this.chargeToZeroCount = tag.getInt("tozerocount");
        this.sonicLevel = tag.getInt("level");
        this.xp = tag.getInt("xp");
    }

    public SoundEvent getDefaultSonicSound(){
        return DMSound.SONIC.get();
    }

    public SoundEvent getSonicSound() {
        if(sonicSound != null){
            return sonicSound;
        } else {
            return DMSound.SONIC.get();
        }
    }

    public void setTntInteract(boolean tntInteract) {
        this.tntInteract = tntInteract;
    }

    public void setBlockInteract(boolean blockInteract) {
        this.blockInteract = blockInteract;
    }

    public void setShearInteract(boolean shearInteract) {
        this.shearInteract = shearInteract;
    }

    public boolean isShearInteract() {
        return shearInteract;
    }

    public boolean isBlockInteract() {
        return blockInteract;
    }

    public void setSonicSound(SoundEvent sonicSound) {
        this.sonicSound = sonicSound;
    }

    public void setDoorInteract(boolean doorInteract) {
        this.doorInteract = doorInteract;
    }

    public void setOreInteract(boolean oreInteract) {
        this.oreInteract = oreInteract;
    }

    public void setRedstoneInteract(boolean redstoneInteract) {
        this.redstoneInteract = redstoneInteract;
    }

    public void setRepairAnvilInteraction(boolean repairAnvilInteraction) {
        this.repairAnvilInteraction = repairAnvilInteraction;
    }

    public boolean isDoorInteract() {
        return doorInteract;
    }

    public boolean isOreInteract() {
        return oreInteract;
    }

    public boolean isRedstoneInteract() {
        return redstoneInteract;
    }

    public boolean isRepairAnvilInteraction() {
        return repairAnvilInteraction;
    }

    public boolean isTntInteract() {
        return tntInteract;
    }

    public boolean isReplaced() {
        return isReplaced;
    }

    public void setReplaced(boolean replaced) {
        isReplaced = replaced;
    }

    public void setBaseItem(SonicItem baseItem) {
        this.baseItem = baseItem;
    }

    public SonicItem getBaseItem() {
        return baseItem;
    }

    public boolean isHaveChargeUpgrade() {
        return haveChargeUpgrade;
    }

    public void setChargeUpgradeLevel(int chargeUpgradeLevel) {
        this.chargeUpgradeLevel = chargeUpgradeLevel;
    }

    public int getChargeUpgradeLevel() {
        return chargeUpgradeLevel;
    }

    public int getMaxCharge() {
        return maxCharge;
    }

    public int setMaxCharge(int maxCharge) {
        this.maxCharge = maxCharge;
        return maxCharge;
    }

    public int setSonicLevel(int sonicLevel) {
        this.sonicLevel = sonicLevel;
        return sonicLevel;
    }

    public int getSonicLevel(){
        if(xp == 0){
            return setSonicLevel(0);
        } else if(xp > 25){
            return setSonicLevel(1);
        } else if(xp > 100){
            return setSonicLevel(2);
        } else if(xp > 250){
            return setSonicLevel(3);
        } else if(xp > 500) {
            return setSonicLevel(4);
        } else {
            return setSonicLevel(0);
        }
    }

    public void setChargeToZeroCount(int chargeToZeroCount) {
        this.chargeToZeroCount = chargeToZeroCount;
    }

    public int getChargeToZeroCount() {
        return chargeToZeroCount;
    }

    public int zeroCountForCharge() {
        if(chargeToZeroCount == 0){
            setDamageLevel(4);
           return setMaxCharge(100);
        } else if(chargeToZeroCount == 1){
            setDamageLevel(3);
            return setMaxCharge(200);
        } else if(chargeToZeroCount == 2){
            setDamageLevel(2);
            return setMaxCharge(400);
        } else if(chargeToZeroCount >= 3){
            setDamageLevel(1);
            return setMaxCharge(800);
        } else {
            return setMaxCharge(100);
        }
    }

    public void setDamageLevel(int damageLevel) {
        this.damageLevel = damageLevel;
    }

    public int getDamageLevel() {
        return damageLevel;
    }

    public void addDamageLevel(int toAdd){
        this.damageLevel = damageLevel + toAdd;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void addXp(int toAdd) {
        this.xp = xp + toAdd;
    }

    public int getXp() {
        return xp;
    }

    public enum Interaction {
        ORE,
        TNT,
        REDSTONE,
        ANVIL,
        DOOR,
        BLOCK,
        SHEARS
    }

    public enum Mode {
        SIMPLE,
        ADVANCED
    }
}
