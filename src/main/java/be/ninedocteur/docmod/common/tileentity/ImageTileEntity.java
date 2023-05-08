package be.ninedocteur.docmod.common.tileentity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import be.ninedocteur.docmod.common.block.ImageLoaderBlock;
import be.ninedocteur.docmod.common.init.DMTileEntity;
import be.ninedocteur.docmod.utils.ImageLoaderException;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class ImageTileEntity extends BlockEntity{
	private BufferedImage image = null;

	public ImageTileEntity(BlockPos p_155229_, BlockState p_155230_) {
		super(DMTileEntity.IMAGE.get(), p_155229_, p_155230_);
		// TODO Auto-generated constructor stub
	}
	
	public String getImageName() {
		return this.getBlockState().getBlock().getName().toString();
	}
	
	public void getImageTexture() {
		// Récupérer l'URL de l'image en ligne
		String imageURL = "http://130.61.36.120/docteam/img/" + getImageName() + ".png";

		// Charger l'image à partir de l'URL
		try {
		    URL url = new URL(imageURL);
		    image = ImageIO.read(url);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public int getImageX() {
		return image.getWidth();
	}
	
	public int getImageY() {
		return image.getHeight();
	}
	
	public int getResolution() {
		return Math.max(getImageX(), getImageY());
	}
	
	public int getRotation() {
        // Récupérer le bloc voisin à l'ouest
        BlockState westBlock = level.getBlockState(worldPosition.west());
        // Récupérer le bloc voisin à l'est
        BlockState eastBlock = level.getBlockState(worldPosition.east());
        // Récupérer le bloc voisin au nord
        BlockState northBlock = level.getBlockState(worldPosition.north());
        // Récupérer le bloc voisin au sud
        BlockState southBlock = level.getBlockState(worldPosition.south());

        // Déterminer l'orientation du bloc en fonction des blocs voisins
        if (westBlock.getBlock() instanceof ImageLoaderBlock) {
            return 1; // Rotation de 90 degrés (vers l'ouest)
        } else if (eastBlock.getBlock() instanceof ImageLoaderBlock) {
            return 3; // Rotation de 270 degrés (vers l'est)
        } else if (northBlock.getBlock() instanceof ImageLoaderBlock) {
            return 2; // Rotation de 180 degrés (vers le nord)
        } else if (southBlock.getBlock() instanceof ImageLoaderBlock) {
            return 0; // Aucune rotation (vers le sud)
        }

        return 0; // Par défaut, aucune rotation
    }
}
