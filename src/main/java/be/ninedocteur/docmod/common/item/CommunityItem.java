package be.ninedocteur.docmod.common.item;

import be.ninedocteur.docmod.DocMod;
import net.minecraft.world.item.Item;

public class CommunityItem extends Item{

	public CommunityItem(Properties p_41383_) {
		super(p_41383_);
		addToCommunityTab();
	}
	
	public void addToCommunityTab() {
		DocMod.addToCommunityTab(this);
	}

}
