package be.ninedocteur.docmod.common.tileentity;

import be.ninedocteur.docmod.common.block.energy.EnergyPipeBlock;
import be.ninedocteur.docmod.common.init.DMTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class EnergyPipeTileEntity extends BlockEntity {
    public int DEInPipe;

    public EnergyPipeTileEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(DMTileEntity.ENERGY_PIPE.get(), p_155229_, p_155230_);
    }


    public void getDEInTheOtherPipe(){
        BlockPos north = getBlockPos().north();
        BlockPos south = getBlockPos().south();
        BlockPos east = getBlockPos().east();
        BlockPos west = getBlockPos().west();
        if(level.getBlockEntity(north).getBlockState().getBlock() instanceof EnergyPipeBlock){
            EnergyPipeTileEntity leftTile = (EnergyPipeTileEntity) level.getBlockEntity(north);
            this.setDEInPipe(leftTile.getDEInPipe());
        }
        if(level.getBlockEntity(south).getBlockState().getBlock() instanceof EnergyPipeBlock){
            EnergyPipeTileEntity leftTile = (EnergyPipeTileEntity) level.getBlockEntity(south);
            this.setDEInPipe(leftTile.getDEInPipe());
        }
        if(level.getBlockEntity(east).getBlockState().getBlock() instanceof EnergyPipeBlock){
            EnergyPipeTileEntity leftTile = (EnergyPipeTileEntity) level.getBlockEntity(east);
            this.setDEInPipe(leftTile.getDEInPipe());
        }
        if(level.getBlockEntity(west).getBlockState().getBlock() instanceof EnergyPipeBlock){
            EnergyPipeTileEntity leftTile = (EnergyPipeTileEntity) level.getBlockEntity(west);
            this.setDEInPipe(leftTile.getDEInPipe());
        }
    }

    public void setDEInPipe(int DEInPipe) {
        this.DEInPipe = DEInPipe;
    }

    public int getDEInPipe() {
        return DEInPipe;
    }

    public void tick() {
        getDEInTheOtherPipe();
    }
}
