package be.ninedocteur.docmod.common.tileentity.system;

import be.ninedocteur.docmod.common.tileentity.DMBaseTileEntity;
import be.ninedocteur.docmod.common.tileentity.DMNBTTile;

public interface IDMNBT {
	
	void setName(DMNBTTile tile);
	void save(NBTEngine nbt);
	void load(NBTEngine nbt);
}
