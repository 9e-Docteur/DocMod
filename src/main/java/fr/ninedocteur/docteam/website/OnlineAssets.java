package fr.ninedocteur.docteam.website;

import fr.ninedocteur.docmod.utils.IOUtils;
import net.minecraft.util.Identifier;

public class OnlineAssets {

    private static Identifier EMPTY, ONLINE;

    //OVERLAY
    public static Identifier getZincIcon(){
        return IOUtils.readImage("http://www.docteamwebsite.tk/modinfoio/Assets/zinc.png");
    }

    //STATUS
    public static Identifier getEmptyStatus(){
        if(EMPTY == null) EMPTY = IOUtils.readImage("http://www.docteamwebsite.tk/modinfoio/Assets/empty.png");
        return EMPTY;
    }

    public static Identifier getOnlineStatus(){
        if(ONLINE == null) ONLINE = IOUtils.readImage("http://www.docteamwebsite.tk/modinfoio/Assets/online.png");
        return ONLINE;
    }

}
