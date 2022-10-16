package be.ninedocteur.docmod.utils;

import be.ninedocteur.docteam.website.Database;
import net.minecraft.client.Minecraft;
import net.minecraft.client.User;

public class RankUtils {

    public static String ReadRank(){
        User player = Minecraft.getInstance().getUser();
        return IOUtils.readURLContent(Database.getRankDir() + player.getName() + ".txt");
    }
}
