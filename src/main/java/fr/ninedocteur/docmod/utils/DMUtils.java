package fr.ninedocteur.docmod.utils;

import fr.ninedocteur.docmod.DocMod;
import fr.ninedocteur.docteam.website.Database;
import net.minecraft.SharedConstants;
import net.minecraft.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DMUtils {
    public static void openLink (String url){
        Util.getOperatingSystem().open(url);
    }

    /*
    STRINGS
     */

    //UTILS
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = DocMod.MOD_ID;

    //MOD/VERSION
    public static final String VERSION = DocMod.VERSION;
    //public static final String BUILD = DocMod.BUILD;
    public static final String CODENAME = DocMod.CODENAME;
    public static final String MODNAME = DocMod.MODNAME;


    public static String getCopyright(){
        return "Copyright DocTeam. Do not distribute!";
    }

    public static String getMinecraftVersion(){
        return "Minecraft " + SharedConstants.getGameVersion().getName();
    }

    public static String getDiscordLink(){
        return "https://discord.gg/7VA9X67xRB";
    }

    public static String getYoutubeLink(){
        return "https://www.youtube.com/channel/UCHv9axbnIhFzzWep8LqekWw";
    }

    public static String getDocTeam(){
        return "DocMod - DocTeam 2021 - 2023";
    }


    public static String getChangelog(){
        return Database.CHANGELOG;
    }
}
