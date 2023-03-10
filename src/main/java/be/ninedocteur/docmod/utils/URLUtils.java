package be.ninedocteur.docmod.utils;

import net.minecraft.Util;

public class URLUtils {
    public static void openLink(String url){
        Util.getPlatform().openUri(url);
    }

}
