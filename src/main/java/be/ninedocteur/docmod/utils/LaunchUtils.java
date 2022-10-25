package be.ninedocteur.docmod.utils;

import be.ninedocteur.docmod.DMConfig;
import net.minecraft.client.Minecraft;

public class LaunchUtils {
	
	/**
	 * Check if the game is running in a dev environement
	 */
    public static boolean isRunningInDev() {
        return Minecraft.getInstance().getLaunchedVersion().equalsIgnoreCase("MOD_DEV");
    }

    /**
     * Check if the config says that the public dev mode is on/off
     */
    public static boolean isRunningInPublicDevMode(){
        return DMConfig.Client.DEV_MODE.get();
    }

    /**
     * Get dev mode public or not
     */
    public static boolean checkLaunchedVersion(){
        if(isRunningInDev()){
            return true;
        } else if(isRunningInPublicDevMode()){
            return true;
        }
        return false;
    }

    public static boolean isBeta = true;
    public static boolean isPreRelease = false;

    public static boolean checkVersionState(){
        if(isBeta){
            return true;
        }
        if(isPreRelease){
            return false;
        }
        return false;
    }
}
