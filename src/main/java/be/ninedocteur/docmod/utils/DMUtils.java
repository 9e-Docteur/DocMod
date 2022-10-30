package be.ninedocteur.docmod.utils;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.init.DMItems;
import be.ninedocteur.docteam.website.Database;
import net.minecraft.SharedConstants;
import net.minecraft.Util;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * FILE DATABASE & UTILS
 * Created by 9e_Docteur on 31-01-22 20:41
 */

public class DMUtils {
    /*
    UTILS
     */
    public static void openLink (String url){
        Util.getPlatform().openUri(url);
    }

    /*
    STRINGS
     */

    //UTILS
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = DocMod.MOD_ID;

    //MOD/VERSION
    public static final String VERSION = DocMod.VERSION;
    public static final String BUILD = DocMod.BUILD;
    public static final String CODENAME = DocMod.CODENAME;
    public static final String MODNAME = DocMod.MODNAME;


    public static String getCopyright(){
        return "Copyright DocTeam. Do not distribute!";
    }

    public static String getMinecraftVersion(){
        return "Minecraft " + SharedConstants.getCurrentVersion().getName();
    }

    public static String getDiscordLink(){
        return "https://discord.gg/7VA9X67xRB";
    }

    public static String getYoutubeLink(){
        return "https://www.youtube.com/channel/UCHv9axbnIhFzzWep8LqekWw";
    }

    public static String getDocTeam(){
        return "DocMod - DocTeam 2021 - 2022";
    }


    public static String getChangelog(){
        return Database.CHANGELOG;
    }

}
