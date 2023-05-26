package fr.ninedocteur.docteam.api;

import fr.ninedocteur.docmod.DocMod;
import fr.ninedocteur.docmod.utils.IOUtils;

import java.io.File;
public class DocTeamAPI {
    public static String API_VERSION = "1.0";
    public static String API_NAME = "DocTeamAPI";
    public static String API_WEB = "http://130.61.39.222";
    
    //private String username;
    //private String password;
    
    public static String API_RESPONSE;
    
    private static boolean isConnected;
    public static boolean isDevLoginEnabled = true;
    
    public DocTeamAPI() {
    	
    }
    public DocTeamAPI(String username, String password) {
    	DocMod.LOGGER.info("Started " + API_NAME + " " + API_VERSION);
    	connect(username, password);
    }
    

    public static String getAPI(){
        return API_WEB;
    }
    
    public boolean connect(String username, String password) {
    	DocMod.LOGGER.info("Executing connection...");
    	String LOGIN_SCRIPT = getAPI() + "/scripts/loginCheck.php?user=" + username + "&pass=" + password;
    	String script = IOUtils.readURLContent(LOGIN_SCRIPT);
    	API_RESPONSE = script.toString();
    	if(API_RESPONSE.equals("login ok")) {
    		DocMod.LOGGER.info("Connection successful");
    		return isConnected = true;
    	} else {
    		DocMod.LOGGER.info("Wrong login. Incorrect Username or Password.");
    		return isConnected = false;
    	}
    }
    
    public static boolean disableDevLogin() {
    	return isDevLoginEnabled = false;
    }
    
    public static File userFile() {
    	return new File("docmod/user.txt");
    }
    
    public static boolean isConnected() {
    	return isConnected;
    }
}
