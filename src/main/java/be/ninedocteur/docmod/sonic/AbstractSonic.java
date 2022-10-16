package be.ninedocteur.docmod.sonic;

public abstract class AbstractSonic {

    /*

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        Player pPlayer = Minecraft.getInstance().player;
        pTooltipComponents.add(new TranslatableComponent(ChatFormatting.GOLD  + "XP: " + ChatFormatting.WHITE + pPlayer.experienceLevel));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level pLevel = pContext.getLevel();
        Player pPlayer = pContext.getPlayer();
        BlockPos pos = pContext.getClickedPos();
        BlockState state = pContext.getLevel().getBlockState(pos);
        SonicInteractionItem.Interaction interaction = new SonicInteractionItem.Interaction();
        SonicInteractionItem.TNTInteraction tntinteraction = new SonicInteractionItem.TNTInteraction();
        SonicInteractionItem.RedstoneLamp.LampOn redstoneLampOn = new SonicInteractionItem.RedstoneLamp.LampOn();
        SonicInteractionItem.RedstoneLamp.LampOff redstoneLampOff= new SonicInteractionItem.RedstoneLamp.LampOff();
        SonicInteractionItem.OreSmelting.IronOre ironOreSmelt = new SonicInteractionItem.OreSmelting.IronOre();
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
        }else if(state.getBlock().equals(Blocks.IRON_ORE)) {
            ironOreSmelt.blockInteraction(pLevel, pPlayer, ItemStack.EMPTY, pos, pLevel.getBlockState(pos));
            pPlayer.getCooldowns().addCooldown(this, 2);
            pLevel.playSound((Player) null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), DMSound.SONIC.get(), SoundSource.AMBIENT, 1.0F, 3.0F / (pLevel.getRandom().nextFloat() * 0.4F + 1.2F) * 0.5F);
            //if (!pPlayer.isCreative())
            //pPlayer.giveExperienceLevels(-4);
        }
        pLevel.playSound((Player)null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), DMSound.SONIC.get(), SoundSource.AMBIENT, 1.0F, 3.0F / (pLevel.getRandom().nextFloat() * 0.4F + 1.2F) * 0.5F);
        return super.useOn(pContext);
    }

     */
}
