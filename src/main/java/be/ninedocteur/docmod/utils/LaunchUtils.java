package be.ninedocteur.docmod.utils;

import be.ninedocteur.docmod.DMConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;

import java.io.IOException;
import java.io.InputStream;

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

    public static boolean isBeta = false;
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

    public static void initWindowIcon(ResourceLocation icon16, ResourceLocation icon32) {
        try {
            if(Minecraft.ON_OSX) {
                return;
            }
            InputStream icon1 = Minecraft.getInstance().getResourceManager().getResourceOrThrow(icon16).open();
            InputStream icon2 = Minecraft.getInstance().getResourceManager().getResourceOrThrow(icon32).open();
            Minecraft.getInstance().getWindow().setIcon(icon1, icon2);
        }catch(IOException e) {
            return;
        }
    }
}
