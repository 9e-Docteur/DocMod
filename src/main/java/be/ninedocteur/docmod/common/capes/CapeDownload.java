package be.ninedocteur.docmod.common.capes;

import be.ninedocteur.docmod.utils.IOUtils;
import be.ninedocteur.docteam.api.DocTeamAPI;
import net.minecraft.resources.ResourceLocation;

import java.net.URL;

public class CapeDownload {
    public static void downloadCape(){
        ResourceLocation url = IOUtils.readImage(DocTeamAPI.getAPI() + "/docmod/cape/test.png");
    }
}
