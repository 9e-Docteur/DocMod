package be.ninedocteur.docmod;

import be.ninedocteur.docmod.client.containers.DMContainers;
import be.ninedocteur.docmod.client.gui.screens.DMReportBug;
import be.ninedocteur.docmod.common.init.DMMenu;
import be.ninedocteur.docmod.proxy.ClientProxy;
import be.ninedocteur.docmod.common.init.DMWoodTypes;
import be.ninedocteur.docmod.registry.ClassRegistry;
import be.ninedocteur.docmod.utils.DMUtils;
import be.ninedocteur.docmod.utils.IOUtils;
import be.ninedocteur.docmod.utils.LaunchUtils;
import be.ninedocteur.docmod.utils.TeamUUIDs;
import be.ninedocteur.docteam.api.DMLogin;
import be.ninedocteur.docteam.api.DocTeamAPI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(DocMod.MOD_ID)
public class DocMod {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "docmod";
    public static boolean isRunningInDev = false;
    public static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
    public static final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    public static final String VERSION = "5.0";
    public static final String BUILD = "32";
    public static final String CODENAME = "Redstone";
    public static final String MODNAME = "DocMod";
    public static final String FULLDOCMODVERSION = MODNAME + " " + CODENAME + " " + VERSION;


    public DocMod() {
        LOGGER.info("Start initializing DocMod!");
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        LOGGER.info("Start Registering DocMod Classes.");
        ClassRegistry.registerClass(eventBus);
        LOGGER.info("Start initializing DocMod Config.");
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, DMConfig.Client.CLIENT_SPEC, "docmod-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DMConfig.Server.SERVER_SPEC, "docmod-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, DMConfig.Server.SERVER_SPEC, "docmod-server.toml");
        LOGGER.info("Start initializing DocMod Events.");
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientProxy.doClientEvents(eventBus, MinecraftForge.EVENT_BUS));
        DocMod.LOGGER.info("Init DocMod Containers...");
        DMContainers.CONTAINERS.register(eventBus);
        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::onLaunch);
        MinecraftForge.EVENT_BUS.register(this);
        LOGGER.info("DocMod is fully Initialized.");
    }

    private void commonSetup(FMLCommonSetupEvent event){
        LOGGER.info("Starting Common Setup...");
        DMWoodTypes.registerWoodTypes();
        LOGGER.info("Registring Staff...");
        TeamUUIDs.addAdmin();
    }

    private void onLaunch(FMLClientSetupEvent event){
        if(LaunchUtils.isRunningInPublicDevMode()){
            LOGGER.warn("Dev Mode as been successfully detected as ENABLED.");
            LOGGER.error("THIS MODE IS IN DEVELOPEMENT. SOMES DEV FEATURES AREN'T AVAILAIBLE.");
            isRunningInDev = true;
        }
        else if(LaunchUtils.isRunningInDev()){
            LOGGER.info("Welcome back DocTeam Developper. Successfuly launched DocMod in Dev Mode.");
            isRunningInDev = true;
        } else{
            isRunningInDev = false;
        }
        LOGGER.info("Welcome to DocMod " + DMUtils.CODENAME);
    }
    
    private void closeGameForBannedPeople() {
    	if(getBannedPeople()) {
    		System.exit(1);
    	}
    }
    
    public static boolean getBannedPeople() {
    	//TODO: Change to UUID to avoid player changing name are unbanned
    	return Minecraft.getInstance().player.getName().contains(Component.literal(IOUtils.readURLContent(DocTeamAPI.getAPI() + "docmod/ban/banned_players.txt")));
    }
}
