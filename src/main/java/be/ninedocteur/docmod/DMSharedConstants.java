package be.ninedocteur.docmod;

public class DMSharedConstants {
	private static boolean isSnapshot = true;
	
	public static String getVersion() {
		return "S08-04-23B";
	}
	
	public static boolean isSnapshot() {
		return isSnapshot;
	}
}
