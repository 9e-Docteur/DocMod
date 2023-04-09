package be.ninedocteur.docmod.utils;

import java.util.Properties;

public class BuildUtils {
	public static String get(String key) {
		Properties properties = new Properties();
            String result = properties.getProperty(key);
            if (result.startsWith("%") && result.endsWith("%")) {
                String var = result.replaceAll("%", "");
                String varValue = get(var);
                if (varValue != null) {
                    return varValue;
                }
            }
        return properties != null ? properties.getProperty(key) : null;
    }
}
