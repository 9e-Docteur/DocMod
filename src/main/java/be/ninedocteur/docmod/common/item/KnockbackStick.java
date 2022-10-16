package be.ninedocteur.docmod.common.item;

import net.minecraft.world.item.Item;

public class KnockbackStick extends Item {

    public KnockbackStick(Properties pProperties) {
        super(pProperties);
    }
/*
    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Player player;
        User user = Minecraft.getInstance().getUser();
        AdminUtils.TeamMember teamMember = AdminUtils.getTeamMembers().get(user.getUuid());
        if(teamMember.getUuid().equals(AdminUtils.DEV)){

        }else{
            //pContext.getPlayer().remove(pContext.getPlayer().getInventory().removeItem(DMItems.KNOCKBACK_STICK););
        }
        return super.useOn(pContext);
    }

 */
/*
    @Override
    public void onCraftedBy(ItemStack pStack, Level pLevel, Player pPlayer) {
        ItemStack item = new ItemStack(DMItems.KNOWCKBACK_STICK.get());
        pLevel.addParticle(ParticleTypes.CLOUD, 0, 0 + 0.5D, 0, 0d, 0.08d, 0d);
        pLevel.playSound(pPlayer, pPlayer, SoundEvents.BELL_BLOCK, SoundSource.AMBIENT, 2.0F, 1.0F);
        item.enchant(Enchantments.KNOCKBACK, 10);
        super.onCraftedBy(pStack, pLevel, pPlayer);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        ItemStack item = new ItemStack(DMItems.KNOWCKBACK_STICK.get());
        Level pLevel = Minecraft.getInstance().level;
        pLevel.addParticle(ParticleTypes.CLOUD, 0, 0 + 0.5D, 0, 0d, 0.08d, 0d);
        pLevel.playSound(player, player, SoundEvents.BELL_BLOCK, SoundSource.AMBIENT, 2.0F, 1.0F);
        item.enchant(Enchantments.KNOCKBACK, 10);
        return super.onLeftClickEntity(stack, player, entity);
    }

 */
}
