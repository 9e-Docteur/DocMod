package be.ninedocteur.docmod.common.block;

import be.ninedocteur.docmod.DocMod;
import net.minecraft.world.level.block.Block;

public class CommunityBlock extends Block{

	public CommunityBlock(Properties p_49795_) {
		super(p_49795_);
		addToCommunityTab();
	}

	public void addToCommunityTab() {
		DocMod.addToCommunityTab(this);
	}
}
