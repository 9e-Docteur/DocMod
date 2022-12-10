package be.ninedocteur.docmod.common.tileentity;

import be.ninedocteur.docmod.common.init.DMTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.TickingBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SolarPanelTile extends EnergyTileEntity {
    public int ENERGY_STOCKED;
    public int currentTick = 0;
    public int genPerTick = 30;
    public int maxStocked = 260;

    public SolarPanelTile(BlockPos pos, BlockState state) {
        super(DMTileEntity.SOLAR_PANEL.get(), pos, state, 260);
    }

    public void generateEnergyFromSun(){
        CompoundTag tag = new CompoundTag();
        if(level.getBrightness(LightLayer.SKY, worldPosition) > 3){
            //tick();
            currentTick++;
            //currentTick = currentTick * level.getBrightness(LightLayer.SKY, worldPosition);
            if(currentTick > getGenPerTick()){
                if(!isStockageFull()){
                    ENERGY_STOCKED++;
                }
                currentTick = 0;
            }
            //if(level.getBrightness(LightLayer.SKY, worldPosition) ){
            //ENERGY_STOCKED = ENERGY_STOCKED
            //}
        }
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("energy_stocked", ENERGY_STOCKED);
        return super.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        super.deserializeNBT(nbt);
        nbt.getInt("energy_stocked");
    }

    public int getGenPerTick() {
        return genPerTick;
    }

    public void setGenPerTick(int genPerTick) {
        this.genPerTick = genPerTick;
    }

    public int getEnergyStocked() {
        return ENERGY_STOCKED;
    }
/*
    public void setEnergyStocked(int energyStocked) {
        ENERGY_STOCKED = energyStocked;
    }

 */

    public void tick() {
        generateEnergyFromSun();
        setEnergyStocked(ENERGY_STOCKED);
    }

//    public static void tick(Level p_155253_, BlockPos p_155254_, BlockState p_155255_, BlockEntity p_155256_) {
//        //generateEnergyFromSun();
//        //tick();
//        if(p_155253_.getBrightness(LightLayer.SKY, p_155254_) > 0){
//            //tick();
//        }
////        Minecraft.getInstance().player.sendSystemMessage(Component.literal("Im ticking bro"));
//    }
}
