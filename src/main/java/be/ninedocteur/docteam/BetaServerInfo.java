package be.ninedocteur.docteam;

import be.ninedocteur.docmod.utils.IOUtils;
import be.ninedocteur.docteam.website.WebsiteUtils;

public class BetaServerInfo {

    public static String getPLAYERS() {
        return IOUtils.readURLContent("https://mcapi.xdefcon.com/server/beta.docteam.tk/players/text");
    }

    public static String getMaxPlayers() {
        return IOUtils.readURLContent("https://mcapi.xdefcon.com/server/beta.docteam.tk/maxplayers/text");
    }
    public static final String IP = Servers.HOST;

    public static String DOCMOD_VERSION_NEEDED = IOUtils.readURLContent(WebsiteUtils.getWebsite() + WebsiteUtils.getServerFolder() + "modversionneededbeta.txt");
}
