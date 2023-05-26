package fr.ninedocteur.docteam;

import fr.ninedocteur.docmod.utils.IOUtils;
import fr.ninedocteur.docteam.website.WebsiteUtils;

public class Servers {
    /*
    DOCMOD PUBLIC SERVER
     */
    public static final String HOST = "capmine.duckdns.org";
    public static final int PORT = 25565;

    /*
    DOCMOD PRIVATE SERVER
     */
    public static final String BetaHOST = "beta.docteam.tk";
    public static final int PrivateServerPORT = 25570;

    /*
    DEV
     */
    public static final String DEV_HOST = IOUtils.readURLContent(WebsiteUtils.getWebsite() + WebsiteUtils.getServerFolder() + "host.txt");
//    public static final int DEV_PORT = ;

    public static final String DEVHOST = "dev.docteam.tk";
    public static final int DEVPORT = 25560;
}
