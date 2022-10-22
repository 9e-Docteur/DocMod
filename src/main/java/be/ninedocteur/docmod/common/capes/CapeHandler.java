package be.ninedocteur.docmod.common.capes;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

public class CapeHandler {

    private static HashMap<UUID, CapeHandler> capes = new HashMap<>();
    private UUID playerUUID = Minecraft.getInstance().player.getUUID();
    private static final Map<String, ResourceLocation> DOWNLOADED_TEXTURES = new HashMap<>();


    public static CapeHandler getCape(Player player){
        CapeHandler capeHandler = capes.get(player.getUUID());
        return getCape(player);
    }

    public ResourceLocation getCapeLocation(){
        return new ResourceLocation("docmod", "capes/" + playerUUID);
    }

    @OnlyIn(Dist.CLIENT)
    public static ResourceLocation readCapeTexture(final String url){
        if(DOWNLOADED_TEXTURES.containsKey(url)){
            return DOWNLOADED_TEXTURES.get(url);
        }
        ResourceLocation resourceLocation = new ResourceLocation("docmod", "capes/");
        DOWNLOADED_TEXTURES.put(url, resourceLocation);
        try{
            InputStream inputStream = new URL(url).openStream();
            NativeImage image = NativeImage.read(inputStream);
            DynamicTexture texture = new DynamicTexture(image);
            inputStream.close();
            Minecraft.getInstance().getTextureManager().register(resourceLocation, texture);
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resourceLocation;
    }
}
