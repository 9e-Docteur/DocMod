package be.ninedocteur.docmod.utils;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.MixinEnvironment;

public class ModelUtils {
    public static int getModelGlow(double percent){
        int i = (int) Mth.clampedLerp(0.0F, 15.0F, percent);
        return LightTexture.pack(i, i);
    }

    public static int getModelPartGlow(ModelPart modelPart, double percent){
        int i = (int) Mth.clampedLerp(0.0F, 15.0F, percent);
        return LightTexture.pack(i, i);
    }


}
