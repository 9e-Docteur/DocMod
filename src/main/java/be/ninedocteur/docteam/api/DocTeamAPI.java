package be.ninedocteur.docteam.api;

import be.ninedocteur.docmod.utils.IOUtils;

public class DocTeamAPI {
    public static String API_VERSION = "1.0-DEV";
    public static String API_NAME = "DocTeamAPI";

    public static String getAPI(){
        return IOUtils.readURLContent("https://api.docteam.tk");
    }
}
