package be.ninedocteur.docmod.common.block.energy;

import be.ninedocteur.docmod.common.init.DMTileEntity;
import be.ninedocteur.docmod.common.tileentity.SolarPanelTile;
import be.ninedocteur.docmod.common.tileentity.ZurbTeleporterTile;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class SolarPanelBlock extends EnergyBlock {

    public SolarPanelBlock(Properties p_49224_) {
        super(p_49224_);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        SolarPanelTile solarPanelTile = (SolarPanelTile) Minecraft.getInstance().level.getBlockEntity(p_153215_);
        return new SolarPanelTile(p_153215_, p_153216_);
    }

    @Override
    public InteractionResult use(BlockState p_60503_, Level p_60504_, BlockPos p_60505_, Player p_60506_, InteractionHand p_60507_, BlockHitResult p_60508_) {
        SolarPanelTile solarPanelTile = (SolarPanelTile) p_60504_.getBlockEntity(p_60505_);
        p_60506_.sendSystemMessage(Component.literal(String.valueOf(solarPanelTile.ENERGY_STOCKED)));
        return super.use(p_60503_, p_60504_, p_60505_, p_60506_, p_60507_, p_60508_);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153212_, BlockState p_153213_, BlockEntityType<T> p_153214_) {
        return p_153212_.isClientSide ? null
                : (level0, pos, state0, blockEntity) -> ((SolarPanelTile) blockEntity).tick();
    }
}
