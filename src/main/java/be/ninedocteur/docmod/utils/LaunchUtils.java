package be.ninedocteur.docmod.utils;

import be.ninedocteur.docmod.DMConfig;
import net.minecraft.client.Minecraft;

public class LaunchUtils {
    public static boolean isRunningInDev() {
        return Minecraft.getInstance().getLaunchedVersion().equalsIgnoreCase("MOD_DEV");
    }

    public static boolean isRunningInPublicDevMode(){
        return DMConfig.Client.DEV_MODE.get();
    }

    public static boolean checkLaunchedVersion(){
        if(isRunningInDev()){
            return true;
        } else if(isRunningInPublicDevMode()){
            return true;
        }
        return false;
    }

    public static boolean isBeta = false;
    public static boolean isPreRelease = true;

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
