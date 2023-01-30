package fr.ninedocteur.docmod.utils;

import net.minecraft.util.Util;

public class URLUtils {
    public static void openLink (String url){
        Util.getOperatingSystem().open(url);
    }
}
