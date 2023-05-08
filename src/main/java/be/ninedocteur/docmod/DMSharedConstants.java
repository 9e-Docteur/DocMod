package be.ninedocteur.docmod;

public class DMSharedConstants {
	private static boolean isSnapshot = true;
	
	public static String getVersion() {
		return "S14-04-23";
	}
	
	public static boolean isSnapshot() {
		return isSnapshot;
	}
}
