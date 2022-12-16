package be.ninedocteur.docmod.common.tileentity;

import be.ninedocteur.docmod.common.block.energy.EnergyBlock;
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


    //WILL BE IMPROVED DON'T PANIC XD
    public void getDEInTheOtherPipe(){
        BlockPos north = getBlockPos().north();
        BlockPos south = getBlockPos().south();
        BlockPos east = getBlockPos().east();
        BlockPos west = getBlockPos().west();
        System.out.println("DE IN OTHER PIPE");
        //PIPE
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

        //ENERGY TILE
        if(level.getBlockEntity(north).getBlockState().getBlock() instanceof EnergyBlock){
            EnergyTileEntity leftTile = (EnergyTileEntity) level.getBlockEntity(north);
            this.setDEInPipe(leftTile.getEnergyStocked());
        }
        if(level.getBlockEntity(south).getBlockState().getBlock() instanceof EnergyBlock){
            EnergyTileEntity leftTile = (EnergyTileEntity) level.getBlockEntity(south);
            this.setDEInPipe(leftTile.getEnergyStocked());
        }
        if(level.getBlockEntity(east).getBlockState().getBlock() instanceof EnergyBlock){
            EnergyTileEntity leftTile = (EnergyTileEntity) level.getBlockEntity(east);
            this.setDEInPipe(leftTile.getEnergyStocked());
        }
        if(level.getBlockEntity(west).getBlockState().getBlock() instanceof EnergyBlock){
            EnergyTileEntity leftTile = (EnergyTileEntity) level.getBlockEntity(west);
            this.setDEInPipe(leftTile.getEnergyStocked());
        }
    }

    public void setDEInPipe(int DEToPutIntoPipe) {
        this.DEInPipe = DEInPipe;
    }

    public int getDEInPipe() {
        return DEInPipe;
    }

    public String getDEInPipeAsString(){
        return String.valueOf(DEInPipe);
    }

    public void tick() {
        System.out.println("TICKING1");
        //getDEInTheOtherPipe();
        System.out.println("TICKING2");
    }
}
