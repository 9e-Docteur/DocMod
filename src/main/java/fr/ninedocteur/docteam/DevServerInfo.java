package fr.ninedocteur.docteam;


import fr.ninedocteur.docmod.utils.IOUtils;

public class DevServerInfo {

    public static String getPLAYERS() {
        return IOUtils.readURLContent("https://mcapi.xdefcon.com/server/dev.docteam.tk/players/text");
    }

    public static String getMaxPlayers() {
        return IOUtils.readURLContent("https://mcapi.xdefcon.com/server/dev.docteam.tk/maxplayers/text");
    }

    public static String MOTD = IOUtils.readURLContent("https://mcapi.xdefcon.com/server/dev.docteam.tk/motd/text");
    public static final String IP = Servers.DEVHOST;

}
