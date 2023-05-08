package be.ninedocteur.docmod.common.tileentity;

import be.ninedocteur.docmod.common.init.DMTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class EnderPadTile extends BlockEntity{
	private BlockPos linkedPos;
	public boolean isLinked;
	public String name;
	public boolean hasName;

	public EnderPadTile(BlockPos p_155229_, BlockState p_155230_) {
		super(DMTileEntity.ENDER_TILE.get(), p_155229_, p_155230_);
		// TODO Auto-generated constructor stub
	}
	
	public void setName(String name) {
		this.name = name;
		hasName = true;
	}
	
	public String getName() {
		String coloredName = name.replaceAll("&", "§");
		return coloredName;
	}
	
	public void setLinked(boolean isLinked) {
		this.isLinked = isLinked;
	}
	
	public void setLinkedPos(BlockPos linkedPos) {
		this.linkedPos = linkedPos;
	}
	
	public boolean isLinked() {
		return isLinked;
	}
	
	public BlockPos getLinkedPos() {
		return linkedPos;
	}
	
	@Override
	protected void saveAdditional(CompoundTag p_187471_) {
		super.saveAdditional(p_187471_);
		p_187471_.putInt("x", linkedPos.getX());
		p_187471_.putInt("y", linkedPos.getY());
		p_187471_.putInt("z", linkedPos.getZ());
		p_187471_.putString("name", name);
	}
	
	
	@Override
	public void load(CompoundTag p_155245_) {
		super.load(p_155245_);
		linkedPos = new BlockPos(p_155245_.getInt("x"), p_155245_.getInt("y"), p_155245_.getInt("z"));
		this.name = p_155245_.getString("name");
	}
}
