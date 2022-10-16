package be.ninedocteur.docmod.data;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.utils.IOUtils;

public class Splashes {
    public static String[] SPLASHES;

    public static String loadSplashes(){
        String str = IOUtils.readURLContent("");
        if (str != null)
            try {
                SPLASHES = DocMod.GSON.fromJson(str, String[].class);
                IOUtils.writeContentToFile(SPLASHES, "/mods/DocMod/splash.json", DocMod.GSON);
                return str;
            }catch (Exception e){
                Object object = IOUtils.loadContentFromFile("/mods/DocMod/splash.json", String[].class, DocMod.GSON, true);
                if (object !=null && object instanceof String[] && (
                        (String[])object).length > 0){
                    SPLASHES = (String[])object;
                    return str;
                }
            }

        SPLASHES = new String[] {"DocMod By 9e_Docteur"};
        return str;
    }
}
