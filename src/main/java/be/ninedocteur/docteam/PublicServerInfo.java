package be.ninedocteur.docteam;

import be.ninedocteur.docmod.utils.IOUtils;
import be.ninedocteur.docteam.website.WebsiteUtils;

public class PublicServerInfo {

    public static String getPLAYERS() {
        return IOUtils.readURLContent("https://mcapi.xdefcon.com/server/public.docteam.tk/players/text");
    }

    public static String getMaxPlayers() {
        return IOUtils.readURLContent("https://mcapi.xdefcon.com/server/public.docteam.tk/maxplayers/text");
    }

    public static String MOTD = IOUtils.readURLContent("https://mcapi.xdefcon.com/server/public.docteam.tk/motd/text");
    public static final String IP = Servers.HOST;

    public static String BetaURL = "https://mcapi.xdefcon.com/server/beta.docteam.tk/maxplayers/text";

    public static String DOCMOD_VERSION_NEEDED = IOUtils.readURLContent(WebsiteUtils.getWebsite() + WebsiteUtils.getServerFolder() + "modversionneeded.txt");
}
