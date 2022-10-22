package be.ninedocteur.docmod.common.capes;

import be.ninedocteur.docmod.utils.IOUtils;
import net.minecraft.resources.ResourceLocation;

import java.net.URL;

public class CapeDownload {
    public static void downloadCape(){
        ResourceLocation url = IOUtils.readImage("https://api.docteam.tk/docmod/cape/test.png");
    }
}
