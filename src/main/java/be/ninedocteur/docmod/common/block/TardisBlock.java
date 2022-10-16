package be.ninedocteur.docmod.common.block;

import be.ninedocteur.docmod.DMConfig;
import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.tileentity.TardisTileEntity;
import be.ninedocteur.docmod.common.world.dimension.DMDimension;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
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
/*
    @Override
    public InteractionResult use(BlockState p_60503_, Level pLevel, BlockPos pos, Player player, InteractionHand p_60507_, BlockHitResult p_60508_) {
        //TODO : DIM PAR TARDIS / DEMAT SYSTHEME / VOID DANS LA DIM TARDIS
        if(!pLevel.isClientSide && pLevel instanceof ServerLevel serverLevel) {
            ServerPlayer serverPlayer = (ServerPlayer) player;
            ServerLevel nextLevel = serverPlayer.server.getLevel(DMDimension.TARDIS_KEY);
            ServerLevel tardisLevel = serverLevel.getServer().getLevel(DMDimension.TARDIS_KEY);

            StructureTemplate template = tardisLevel.getStructureManager().getOrCreate(new ResourceLocation(DocMod.MOD_ID, "devtardis"));
            TardisTileEntity tardisTileEntity = (TardisTileEntity) serverLevel.getBlockEntity(pos);
                if (template != null && tardisTileEntity.getId() < 0) {
                    tardisLevel = DMDimension.
                    tardisTileEntity.generateId();
                    template.placeInWorld(tardisLevel,
                            new BlockPos(0, 0, 0),
                            new BlockPos(0, 0, 0),
                            new StructurePlaceSettings()
                            , tardisLevel.random, 3);
            }

            if (nextLevel != null) {
                serverPlayer.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 0));
                serverPlayer.teleportTo(nextLevel, 8, 1, 2, serverPlayer.getYRot(), serverPlayer.getXRot());
                serverPlayer.connection.send(new ClientboundPlayerAbilitiesPacket(serverPlayer.getAbilities()));
                for (MobEffectInstance effectinstance : serverPlayer.getActiveEffects())
                    serverPlayer.connection.send(new ClientboundUpdateMobEffectPacket(serverPlayer.getId(), effectinstance));
                serverPlayer.connection.send(new ClientboundLevelEventPacket(1032, BlockPos.ZERO, 0, false));
            }
        }
        return InteractionResult.SUCCESS;
        }

 */
}
