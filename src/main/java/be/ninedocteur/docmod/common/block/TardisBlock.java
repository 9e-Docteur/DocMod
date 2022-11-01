package be.ninedocteur.docmod.common.block;

import be.ninedocteur.docmod.DMConfig;
import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.init.DMItems;
import be.ninedocteur.docmod.common.tileentity.TardisTileEntity;
import be.ninedocteur.docmod.common.world.dimension.DMDimension;
import be.ninedocteur.docmod.sonic.SonicInteractionItem;
import be.ninedocteur.docmod.utils.LevelUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.protocol.game.ClientboundLevelEventPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket;
import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class TardisBlock extends BaseEntityBlock {
   public TardisBlock(Properties p_49224_) {
        super(p_49224_);
    }

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.box(0, 0, 0, 16, 36, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        switch (p_60555_.getValue(BlockStateProperties.FACING)) {
            case SOUTH:
                return SHAPE_N;
            case WEST:
                return SHAPE_N;
            case EAST:
                return SHAPE_N;
            default:
                return SHAPE_N;
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TardisTileEntity(pPos, pState);
    }

    public BlockEntity create(BlockPos p_155268_, BlockState p_155269_) {
        return new TardisTileEntity(p_155268_, p_155269_);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.FACING);
    }

    @javax.annotation.Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(BlockStateProperties.FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public void onPlace(BlockState p_60566_, Level p_60567_, BlockPos p_60568_, BlockState p_60569_, boolean p_60570_) {
        super.onPlace(p_60566_, p_60567_, p_60568_, p_60569_, p_60570_);
        if(p_60567_ instanceof ServerLevel serverLevel){
            LevelUtils.forcedChunkIfLoaded(serverLevel, new ChunkPos(p_60568_), p_60568_);
        }
    }

    @Override
    public void onRemove(BlockState p_60515_, Level p_60516_, BlockPos p_60517_, BlockState p_60518_, boolean p_60519_) {
        super.onRemove(p_60515_, p_60516_, p_60517_, p_60518_, p_60519_);
        if(p_60515_.hasBlockEntity() && p_60515_.getBlock() != p_60518_.getBlock()){
            p_60516_.removeBlockEntity(p_60517_);
            if(p_60516_ instanceof ServerLevel serverLevel){
                LevelUtils.unforcedChunkIfLoaded(serverLevel, new ChunkPos(p_60517_), p_60517_);
            }
        }
    }

    @Override
    public boolean isSignalSource(BlockState p_60571_) {
        return true;
    }

    @Override
    public int getSignal(BlockState p_60483_, BlockGetter p_60484_, BlockPos p_60485_, Direction p_60486_) {
        return 15;
    }

    @Override
    public InteractionResult use(BlockState p_60503_, Level pLevel, BlockPos pos, Player player, InteractionHand p_60507_, BlockHitResult p_60508_) {
        //TODO : DEMAT SYSTHEME
        if (!pLevel.isClientSide && pLevel instanceof ServerLevel serverLevel) {
            ServerPlayer serverPlayer = (ServerPlayer) player;
            ServerLevel nextLevel = serverPlayer.server.getLevel(DMDimension.TARDIS_KEY);
            ServerLevel tardisLevel = serverLevel.getServer().getLevel(DMDimension.TARDIS_KEY);
            ItemStack tardisKey = player.getMainHandItem();

            StructureTemplate template = tardisLevel.getStructureManager().getOrCreate(new ResourceLocation(DocMod.MOD_ID, "toyota_tardis"));
            TardisTileEntity tardisTileEntity = (TardisTileEntity) serverLevel.getBlockEntity(pos);
            if (template != null && tardisTileEntity.getId() == -1) {
                //tardisLevel = DMDimension.
                tardisTileEntity.generateId(player.getUUID());
                template.placeInWorld(tardisLevel,
                        new BlockPos(tardisTileEntity.getId() * 1000, 0, tardisTileEntity.getId() * 1000),
                        new BlockPos(tardisTileEntity.getId() * 1000, 0, tardisTileEntity.getId() * 1000),
                        new StructurePlaceSettings()
                        , tardisLevel.random, 3);
            }

            if (tardisTileEntity.isLocked) {
                if (tardisTileEntity.getOwnerUUID() != player.getUUID()) {
                    player.sendSystemMessage(Component.literal(ChatFormatting.RED + "THIS IS NOT YOUR TARDIS"));
                }
            }

            if (player.getMainHandItem().getItem() instanceof SonicInteractionItem) {
                ((ServerPlayer) player).sendSystemMessage(Component.literal(ChatFormatting.BLUE + "---------------------"));
                ((ServerPlayer) player).sendSystemMessage(Component.literal(ChatFormatting.YELLOW + "Type:" + ChatFormatting.RESET + " Tardis"));
                ((ServerPlayer) player).sendSystemMessage(Component.literal(ChatFormatting.YELLOW + "Tardis ID: " + ChatFormatting.RESET + tardisTileEntity.getId()));
                ((ServerPlayer) player).sendSystemMessage(Component.literal(ChatFormatting.YELLOW + "Tardis Owner: " + ChatFormatting.RESET + tardisTileEntity.getOwnerName()));
                ((ServerPlayer) player).sendSystemMessage(Component.literal(ChatFormatting.BLUE + "---------------------"));
            }

            if (player.getMainHandItem().is(DMItems.TARDIS_KEY.get())) {
                if (player.isCrouching() && tardisTileEntity.getOwnerUUID().equals(player.getUUID())) {
                    if (tardisKey.hasTag()) {
                        ((ServerPlayer) player).sendSystemMessage(Component.literal(ChatFormatting.RED + "Tardis already linked to a tardis."));
                    } else {
                        tardisKey.getTag().putInt("linked_tardis_id", tardisTileEntity.getId());
                        ((ServerPlayer) player).sendSystemMessage(Component.literal(ChatFormatting.GREEN + "Key linked to: " + ChatFormatting.YELLOW + tardisTileEntity.getId()));
                    }
                } else {
                    ((ServerPlayer) player).sendSystemMessage(Component.literal(ChatFormatting.RED + "Cannot link your key to this tardis. You are not the owner of it."));
                }
            } else {
                if (!tardisTileEntity.isLocked() && !(player.getMainHandItem().getItem() instanceof SonicInteractionItem)) {
                    if (nextLevel != null) {
                        serverPlayer.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 0));
                        serverPlayer.teleportTo(nextLevel, tardisTileEntity.getId() * 1000 + 19, 11, tardisTileEntity.getId() * 1000 + 6, serverPlayer.getYRot(), serverPlayer.getXRot());
                        serverPlayer.connection.send(new ClientboundPlayerAbilitiesPacket(serverPlayer.getAbilities()));
                        for (MobEffectInstance effectinstance : serverPlayer.getActiveEffects())
                            serverPlayer.connection.send(new ClientboundUpdateMobEffectPacket(serverPlayer.getId(), effectinstance));
                        serverPlayer.connection.send(new ClientboundLevelEventPacket(1032, BlockPos.ZERO, 0, false));
                    }
                } else {
                    if (player.getMainHandItem().is(DMItems.TARDIS_KEY.get())) {
                        if (tardisTileEntity != null) {
                            int id = tardisTileEntity.getId();
                            if (tardisTileEntity.getOwnerUUID().equals(player.getUUID())) {
                                if (tardisTileEntity.isLocked) {
                                    tardisTileEntity.setLocked(false);
                                    player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "Tardis Unlocked"));
                                } else {
                                    tardisTileEntity.setLocked(true);
                                    player.sendSystemMessage(Component.literal(ChatFormatting.RED + "Tardis Locked"));
                                }
                            } else {
                                player.sendSystemMessage(Component.literal(ChatFormatting.RED + "THIS IS NOT YOUR TARDIS!"));
                            }
                        } else {
                            player.sendSystemMessage(Component.literal(ChatFormatting.RED + "Cannot enter. Tardis is locked."));
                        }
                    }
                }
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.SUCCESS;
    }
}
