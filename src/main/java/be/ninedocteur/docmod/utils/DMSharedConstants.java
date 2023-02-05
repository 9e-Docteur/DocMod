package be.ninedocteur.docmod.utils;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docteam.website.Database;
import net.minecraft.SharedConstants;

public class DMSharedConstants {

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
        return "DocMod - DocTeam 2021 - 2023";
    }


    public static String getChangelog(){
        return Database.CHANGELOG;
    }

    public static String getUpdateName(){
        return DocMod.UPDATE_NAME;
    }

    public static String getVersion(){
        return DocMod.VERSION;
    }
}
