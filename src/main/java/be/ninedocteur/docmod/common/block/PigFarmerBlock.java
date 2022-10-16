package be.ninedocteur.docmod.common.block;

import be.ninedocteur.docmod.utils.DMUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class PigFarmerBlock extends Block {

    @Mod.EventBusSubscriber(modid = DMUtils.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class Events {
        @SubscribeEvent
        public static void livingTick(LivingEvent.LivingTickEvent event) {
            var entity = event.getEntity();
            var foundTag = new StringBuilder();
            entity.getTags().forEach(tag -> {
                if (tag.startsWith("PigFarmer-") && foundTag.isEmpty())
                    foundTag.append(tag);
            });

            if (!foundTag.isEmpty()) {
                int ticks = Integer.parseInt(foundTag.toString().split("-")[1]);
                if (ticks < 120) {
                    entity.removeTag(foundTag.toString());
                    entity.addTag("PigFarmer-" + (ticks + 1));
                } else {
                    entity.removeTag(foundTag.toString());
                    LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(entity.level);
                    lightning.setPos(entity.position());
                }
            }
        }
    }


    public PigFarmerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
                                 BlockHitResult result) {
        if (!level.isClientSide) {
            if (player.experienceLevel <= 10 && !player.isCreative()) {
                level.playSound(player, pos, SoundEvents.ANVIL_BREAK, SoundSource.PLAYERS, 1.0f, 1.0f);
                return InteractionResult.FAIL;
            }

            if (!player.isCreative())
                player.giveExperienceLevels(-5);
/*
            level.playSound(player, pos, SoundEvents.AMBIENT_UNDERWATER_LOOP, SoundSource.BLOCKS, 1.0f, 1.0f);
            for (int index = 0; index < this.RANDOM.nextInt(10) + 7; index++) {
                Pig pig = EntityType.PIG.create(level);
                pig.setPos(pos.getX() + this.RANDOM.nextInt(10) - 5, pos.getY(),
                        pos.getZ() + this.ran.nextInt(10) - 5);
                pig.addTag("PigFarmer-0");
                LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(player.level);
                lightning.setPos(player.position());
                level.addFreshEntity(pig);
            }

 */
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }
}
