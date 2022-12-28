package be.ninedocteur.docmod.client;

import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.*;
import net.minecraftforge.fml.event.IModBusEvent;
import net.minecraftforge.fml.loading.moddiscovery.ModFile;
import net.minecraftforge.fml.loading.moddiscovery.ModFileInfo;
import net.minecraftforge.fml.loading.moddiscovery.ModInfo;
import net.minecraftforge.forgespi.language.IModFileInfo;
import net.minecraftforge.forgespi.language.IModInfo;
import net.minecraftforge.forgespi.language.ModFileScanData;
import net.minecraftforge.forgespi.locating.IModFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AddonList {
//    private static Logger LOGGER = LogManager.getLogger();
//    private static AddonList INSTANCE;
//    //private final List<String> modFiles;
//    private String addonName;
//    private String addonVersion;
//    private String addonWebsite;
//    private String addonReportBug;
//    static ArrayList<ArrayList<String>> list = new ArrayList<>();
//    static ArrayList<String> innerList = new ArrayList<>();
//
//    public static ArrayList<ArrayList<String>> getList() {
//        return list;
//    }
//
//    public static void addAddon(String addonName, String addonModId, String addonVersion, String addonWebsite, String addonReportBug) {
//        // Créer une nouvelle ArrayList de chaînes de caractères
//
//        // Ajouter les éléments à la liste intérieure
//        innerList.add(addonName);
//        innerList.add(addonModId);
//        innerList.add(addonVersion);
//        innerList.add(addonWebsite);
//        innerList.add(addonReportBug);
//
//
//        // Ajouter la liste intérieure à la liste externe
//        list.add(innerList);
//    }
//
//    public String getAddon(int index) {
//        return innerList.get(index);
//    }
}
