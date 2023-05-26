package fr.ninedocteur.docteam.website;

import be.ninedocteur.docmod.utils.IOUtils;

public class WebsiteStatus {

    public static String getAPIStatus(){
        return IOUtils.readURLContent("http://www.docteamwebsite.tk/modinfoio/ServersStatus/api.txt");
    }

    public static String getWebsiteStatus(){
        return IOUtils.readURLContent("http://www.docteamwebsite.tk/modinfoio/ServersStatus/website.txt");
    }
}
