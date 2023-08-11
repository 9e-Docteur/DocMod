package be.ninedocteur.docmod;

import be.ninedocteur.docmod.common.DMCapabilities;
import be.ninedocteur.docmod.common.ITardis;
import be.ninedocteur.docmod.common.event.DMEvent;
import be.ninedocteur.docmod.common.world.tardis.TardisData;
import be.ninedocteur.docmod.proxy.ClientProxy;
import be.ninedocteur.docmod.common.init.DMWoodTypes;
import be.ninedocteur.docmod.registry.ClassRegistry;
import be.ninedocteur.docmod.utils.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.mojang.blaze3d.platform.GlUtil;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(DocMod.MOD_ID)
public class DocMod {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "docmod";
    public static boolean isRunningInDev = false;
    public static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
    public static final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    public static final String VERSION = "7.0";
    public static final String BUILD = "0";
    public static final String CODENAME = "Longhorn";
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
        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::onLaunch);
        MinecraftForge.EVENT_BUS.addListener(DMEvent::onWorldEvent);
        MinecraftForge.EVENT_BUS.addListener(PlanetUtils::initMoon);
        MinecraftForge.EVENT_BUS.addListener(PlanetUtils::initSpace);
        eventBus.addListener(DMCapabilities::register);
        //MinecraftForge.EVENT_BUS.addListener(DMListeners::onPlayerEvent);
        MinecraftForge.EVENT_BUS.register(this);
        LOGGER.info("DocMod is fully Initialized.");
    }

    private void commonSetup(FMLCommonSetupEvent event){
        LOGGER.info("Starting Common Setup...");
        DMWoodTypes.registerWoodTypes();
        LOGGER.info("Registring Staff...");
        TeamUUIDs.addAdmin();
        LOGGER.info("Init Core Addon..");
    }

    private void onLaunch(FMLClientSetupEvent event){
        if(LaunchUtils.isRunningInPublicDevMode()){
            LOGGER.warn("Dev Mode as been successfully detected as ENABLED.");
            LOGGER.error("THIS MODE IS IN DEVELOPEMENT. SOMES DEV FEATURES AREN'T AVAILAIBLE.");
            isRunningInDev = true;
        }
        else if(LaunchUtils.isRunningInDev()){
            LOGGER.info("Welcome back DocTeam Developper. Successfuly launched DocMod in Dev Mode.");
            LOGGER.info("Running on:");
            LOGGER.info("CPU: " + GlUtil.getCpuInfo());
            isRunningInDev = true;
        } else{
            isRunningInDev = false;
        }
        LOGGER.info("Welcome to DocMod " + DMUtils.CODENAME);
    }

    private void onWordLoad(LevelEvent.Load event){
        TardisData tardisData = new TardisData();
        TardisData.registerOldTardises(event.getLevel().getServer(), tardisData.tardisLevelKeys);
    }
}
