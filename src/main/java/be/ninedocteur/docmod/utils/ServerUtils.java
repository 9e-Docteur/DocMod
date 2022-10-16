package be.ninedocteur.docmod.utils;

import net.minecraft.resources.ResourceLocation;

public class ServerUtils {


    public static ResourceLocation getServerInfo(){
        ResourceLocation resourceLocation = IOUtils.readTexture("https://mcapi.us/server/image?ip=docteamdev.mine.fun&theme=dark", "serverimg");
        return resourceLocation;
    }

    public static boolean isServerUp(String ip){
        return !IOUtils.readURLContent("https://mcapi.xdefcon.com/server/" + ip + "/players/text").equals("Server status: Offline");
    }
}
