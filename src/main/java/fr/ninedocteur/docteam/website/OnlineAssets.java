package fr.ninedocteur.docteam.website;

import be.ninedocteur.docmod.utils.IOUtils;
import net.minecraft.resources.ResourceLocation;

public class OnlineAssets {

    private static ResourceLocation EMPTY, ONLINE;

    //OVERLAY
    public static ResourceLocation getZincIcon(){
        return IOUtils.readImage("http://www.docteamwebsite.tk/modinfoio/Assets/zinc.png");
    }

    //STATUS
    public static ResourceLocation getEmptyStatus(){
        if(EMPTY == null) EMPTY = IOUtils.readImage("http://www.docteamwebsite.tk/modinfoio/Assets/empty.png");
        return EMPTY;
    }

    public static ResourceLocation getOnlineStatus(){
        if(ONLINE == null) ONLINE = IOUtils.readImage("http://www.docteamwebsite.tk/modinfoio/Assets/online.png");
        return ONLINE;
    }

}
