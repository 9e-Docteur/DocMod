package be.ninedocteur.docmod.common.block;

import be.ninedocteur.docmod.DocMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public class ImageBlock extends Block {

    public ImageBlock(Properties p_49795_) {
        super(p_49795_);
    }

    public ResourceLocation getTextureLocation(Object object) {
        return new ResourceLocation(DocMod.MOD_ID, "textures/block/hartnell_rotor.png");
    }
}
