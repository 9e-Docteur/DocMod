package be.ninedocteur.docmod.utils;

import net.minecraft.resources.ResourceLocation;

public class PlayerUtils {

    public static ResourceLocation getPlayerHead(final String name){
        return IOUtils.readTexture("https://minotar.net/helm/" + name + "/16.png", name + "_player_helm");
    }

    public static ResourceLocation get3DPlayerHead(final String uuid){
        return IOUtils.readTexture("https://crafatar.com/renders/head/" + uuid + "?scale=10&size=512&overlay", uuid + "_player_head");
    }

    public static ResourceLocation get3DSkin(final String uuid){
        return IOUtils.readTexture("https://crafatar.com/renders/body/" + uuid, uuid + "_player_body");
    }

    public static ResourceLocation getSkin(final String UUID){
        return IOUtils.readTexture("https://crafatar.com/skins/", UUID);
    }
}
