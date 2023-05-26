package fr.ninedocteur.docmod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DMConfig {

    public class Client {
        public static boolean SHOW_VERSION;
        public static boolean SHOW_VERSION_INGAME;
        public static boolean CUSTOM_TITLE_SCREEN;
        public static boolean SHOW_WIP_TABS;
        public static boolean SHOW_WIDGET;
        public static boolean THREE_D_HEAD;
        public static boolean RPC;
        public static boolean DEV_MODE;
        public static boolean DEV_WARN;

        public static void loadClient() {
            Properties properties = new Properties();
            File file = new File("config/docmod-client.properties");
            if (file.exists()) {
                try {
                    properties.load(new FileInputStream(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    file.createNewFile();
                    properties.load(new FileInputStream(file));
                    properties.setProperty("SHOW_VERSION", "true");
                    properties.setProperty("SHOW_VERSION_INGAME", "true");
                    properties.setProperty("CUSTOM_TITLE_SCREEN", "true");
                    properties.setProperty("SHOW_WIP_TABS", "false");
                    properties.setProperty("SHOW_WIDGET", "true");
                    properties.setProperty("THREE_D_HEAD", "true");
                    properties.setProperty("RPC", "true");
                    properties.setProperty("DEV_MODE", "false");
                    properties.setProperty("DEV_WARN", "false");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            SHOW_VERSION = Boolean.parseBoolean(properties.getProperty("SHOW_VERSION"));
            SHOW_VERSION_INGAME = Boolean.parseBoolean(properties.getProperty("SHOW_VERSION_INGAME"));
            CUSTOM_TITLE_SCREEN = Boolean.parseBoolean(properties.getProperty("CUSTOM_TITLE_SCREEN"));
            SHOW_WIP_TABS = Boolean.parseBoolean(properties.getProperty("SHOW_WIP_TABS"));
            SHOW_WIDGET = Boolean.parseBoolean(properties.getProperty("SHOW_WIDGET"));
            THREE_D_HEAD = Boolean.parseBoolean(properties.getProperty("THREE_D_HEAD"));
            RPC = Boolean.parseBoolean(properties.getProperty("RPC"));
            DEV_MODE = Boolean.parseBoolean(properties.getProperty("DEV_MODE"));
            DEV_WARN = Boolean.parseBoolean(properties.getProperty("DEV_WARN"));
        }
    }

    public static boolean GUN_EXPLOSION;
    public static boolean GUN_EXPLOSION_SIZE;

    public static void loadCommon() {
        Properties properties = new Properties();
        File file = new File("config/docmod-common.properties");
        if (file.exists()) {
            try {
                properties.load(new FileInputStream(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                file.createNewFile();
                properties.load(new FileInputStream(file));
                properties.setProperty("GUN_EXPLOSION", "true");
                properties.setProperty("GUN_EXPLOSION_SIZE", "true");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        GUN_EXPLOSION = Boolean.parseBoolean(properties.getProperty("GUN_EXPLOSION"));
        GUN_EXPLOSION_SIZE = Boolean.parseBoolean(properties.getProperty("GUN_EXPLOSION_SIZE"));
    }
}
