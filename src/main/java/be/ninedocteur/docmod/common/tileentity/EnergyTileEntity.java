package be.ninedocteur.docmod.common.tileentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class EnergyTileEntity extends BlockEntity {
    public int ENERGY_STOCKED;
    public int MAX_STOCKED;

    public EnergyTileEntity(BlockEntityType<?> p_155228_, BlockPos p_155229_, BlockState p_155230_, int maxStocked) {
        super(p_155228_, p_155229_, p_155230_);
        this.MAX_STOCKED = maxStocked;
    }

    public int getEnergyStocked() {
        return ENERGY_STOCKED;
    }

    public int getMaxStocked() {
        return MAX_STOCKED;
    }

    public void setEnergyStocked(int ENERGY_STOCKED) {
        this.ENERGY_STOCKED = ENERGY_STOCKED;
    }

    public boolean isStockageFull(){
        if(ENERGY_STOCKED >= MAX_STOCKED){
            return true;
        } else {
            return false;
        }
    }
}
