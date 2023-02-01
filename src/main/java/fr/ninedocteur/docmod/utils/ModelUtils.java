package fr.ninedocteur.docmod.utils;

import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.util.math.MathHelper;

public class ModelUtils {
    public static int getModelGlow(double percent){
        int i = (int) MathHelper.clampedLerp(0.0F, 15.0F, percent);
        return LightmapTextureManager.pack(i, i);
    }
}
