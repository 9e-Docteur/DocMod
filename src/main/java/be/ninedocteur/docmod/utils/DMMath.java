package be.ninedocteur.docmod.utils;

public class DMMath {
	
    public static int convertSecondsToTicks(int seconds) {
        return seconds * 20;
    }

    public static int convertTickToSeconds(int ticks) {
        return ticks / 20;
    }
}
