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
    
    public static String getServerOnlinePlayers(String ip) {
    	return IOUtils.readURLContent("https://mcapi.xdefcon.com/server/" + ip +"/players/text");
    }
    
    public static String getServerMaxPlayers(String ip) {
    	return IOUtils.readURLContent("https://mcapi.xdefcon.com/server/" + ip +"/maxplayers/text");
    }
    
    public static String getServerPing(String ip) {
    	return IOUtils.readURLContent("https://mcapi.xdefcon.com/server/" + ip +"/ping/text");
    }
    
}
