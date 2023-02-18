package be.ninedocteur.docmod.common.capes;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.mojang.blaze3d.platform.NativeImage;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.utils.IOUtils;
import be.ninedocteur.docteam.api.DocTeamAPI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class AnimatedCapeHandler {
    private final int frames;
    private final int fpt;

    private float currentTick = 0;
    private int currentFrame = 0;
    public static int i;

    private ResourceLocation[] textures;
   

    protected AnimatedCapeHandler(int frames, int fpt) {
        this.frames = frames;
        this.fpt = 210;
        textures = new ResourceLocation[frames];

        for(int i = 0; i < frames; i++) {
            textures[i] = readCapeTexture(DocTeamAPI.getAPI() + "docmod/cape/ninety/" + i + ".png", i);
        	//textures[i] = new ResourceLocation("docmod:textures/models/anm/" + i + ".png");
            AnimatedCapeHandler.i = i;
        }

    }

    public ResourceLocation getTexture() {
        return textures[currentFrame];
    }

    public void update(float deltaTime) {
    	 if(currentTick > 6) {
             currentTick = 0;
             currentFrame++;
             if(currentFrame >= textures.length - 1) {
                 currentFrame = 0;
             }
         }
         currentTick++;
    }
    
    @OnlyIn(Dist.CLIENT)
    public static ResourceLocation readCapeTexture(final String url, int i){
            ResourceLocation resourceLocation = new ResourceLocation("docmod", "animcapes/" + i);
            try {
                InputStream inputStream = new URL(url).openStream();
                NativeImage image = NativeImage.read(inputStream);
                DynamicTexture texture = new DynamicTexture(image);
                Minecraft.getInstance().getTextureManager().register(resourceLocation, texture);
                inputStream.close();
            } catch (IOException e) {
                DocMod.LOGGER.warn("Failed to get Docmod capes");
            }
            return resourceLocation;
        }
}
