package be.ninedocteur.docteam.website;

public class WebsiteUtils {
   // private Strings websiteString;
   // private static WebsiteUtils websiteUtils;

    public static String getWebsite() {
        return Strings.DOMAIN + Strings.MOD_FOLDER;
    }

    public static String getServerFolder(){
        return Strings.SERVER_FOLDER;
    }

    public static String getAPI(){
        return API.API_DOMAIN;
    }
}
