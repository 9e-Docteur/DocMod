package be.ninedocteur.docmod;

import be.ninedocteur.docmod.api.Addon;
import be.ninedocteur.docmod.client.containers.DMContainers;
import be.ninedocteur.docmod.client.event.ClientEventHandler;
import be.ninedocteur.docmod.client.gui.screens.DMReportBug;
import be.ninedocteur.docmod.common.capes.AnimatedCapeHandler;
import be.ninedocteur.docmod.common.event.DMEvent;
import be.ninedocteur.docmod.common.init.DMBlocks;
import be.ninedocteur.docmod.common.init.DMItems;
import be.ninedocteur.docmod.common.init.DMMenu;
import be.ninedocteur.docmod.common.listeners.DMListeners;
import be.ninedocteur.docmod.jobs.data.ServerJobsData;
import be.ninedocteur.docmod.jobs.util.config.ReadConfigManager;
import be.ninedocteur.docmod.jobs.util.handler.PacketHandler;
import be.ninedocteur.docmod.jobs.util.handler.RegistryHandler;
import be.ninedocteur.docmod.proxy.ClientProxy;
import be.ninedocteur.docmod.common.init.DMWoodTypes;
import be.ninedocteur.docmod.proxy.CommonProxy;
import be.ninedocteur.docmod.registry.ClassRegistry;
import be.ninedocteur.docmod.utils.*;
import be.ninedocteur.docteam.api.DMLogin;
import be.ninedocteur.docteam.api.DocTeamAPI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.mojang.blaze3d.platform.GlUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.UUID;

import javax.print.Doc;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import net.minecraftforge.fml.loading.moddiscovery.ModAnnotation;
import net.minecraftforge.fml.loading.moddiscovery.ModInfo;
import net.minecraftforge.fml.loading.targets.FMLServerLaunchHandler;
import net.minecraftforge.forgespi.language.IModInfo;
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
    public static final String CODENAME = "Longhorn"; //LONGHORN FOR 7.X
    public static final String MODNAME = "DocMod";
    public static final String UPDATE_NAME = "Jobs Update";
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
        Addon addon = new Addon("DocMod", DocMod.MOD_ID, DocMod.VERSION, "no site", "no issue");
        Addon test = new Addon("JEI", "jei", "1.0", "no site", "no issue");
        Addon.registerModAsAPI(addon);
        Addon.registerModAsAPI(test);
        RegistryHandler.registerListeners();
        LOGGER.info("Event Handlers Registered", false);
        ServerJobsData.registerCommonXPRegistries();
        LOGGER.info("Common XP Categories Registered", false);
        MinecraftForge.EVENT_BUS.addListener(PlanetUtils::initMoon);
        MinecraftForge.EVENT_BUS.addListener(PlanetUtils::initSpace);
        MinecraftForge.EVENT_BUS.addListener(CommonProxy::commonSetup);
        MinecraftForge.EVENT_BUS.addListener(ClientEventHandler::onKeyInput);
        DocMod.LOGGER.info("Init DocMod Creative Tabs...");
        eventBus.addListener(ClientEventHandler::onKeyRegister);
        eventBus.addListener(this::addCreative);
        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::onLaunch);
        MinecraftForge.EVENT_BUS.register(this);
        LOGGER.info("DocMod is fully Initialized.");
    }

    //TODO: MOVE THIS -> TAKE TO MUCH PLACE LOL
    private void addCreative(CreativeModeTabEvent.BuildContents event){
        if(event.getTab() == DMCreativeTabs.DOCMOD_MAIN){
            event.accept(DMBlocks.KILLER_BLOCK);
            event.accept(DMBlocks.HEAL_BLOCK);
            event.accept(DMBlocks.ZINC_ORE);
            event.accept(DMBlocks.DEEPSLATE_ZINC_ORE);
            event.accept(DMBlocks.STEEL_ORE);
            event.accept(DMBlocks.DEEPSLATE_STEEL_ORE);
            event.accept(DMBlocks.ZINC_BLOCK);
            event.accept(DMBlocks.STEEL_BLOCK);
            event.accept(DMBlocks.CRYSTALINE_BLOCK);
            event.accept(DMBlocks.CRYSTALINE_ORE);
            event.accept(DMBlocks.CRYSTAL_ORE);
            event.accept(DMBlocks.DEEPSLATE_CRYSTAL_ORE);
            event.accept(DMBlocks.XP_ORE);
            event.accept(DMBlocks.CIRCLE_GLASS);
            event.accept(DMBlocks.DEATH_LOG);
            event.accept(DMBlocks.CLASSIC_GRASS);
            //event.accept(DMBlocks.DEATH_SIGN);
            event.accept(DMBlocks.DEATH_LEAVES);
            event.accept(DMBlocks.DEATH_GRASS_BLOCK);
            event.accept(DMBlocks.NETHER_ZINC_ORE);
            event.accept(DMBlocks.MOON_BLOCK);
            event.accept(DMBlocks.MOON_STONE);
            event.accept(DMBlocks.ALBIZIA_LOG);
            event.accept(DMBlocks.ALBIZIA_PLANKS);
            event.accept(DMBlocks.ALBIZIA_STAIRS);
            event.accept(DMBlocks.ALBIZIA_SLAB);
            event.accept(DMBlocks.ALBIZIA_DOOR);
            //event.accept(DMBlocks.ALBIZIA_SIGN);
            event.accept(DMBlocks.ALBIZIA_LEAVES);
            event.accept(DMBlocks.CRYOLITE_ORE);
            event.accept(DMBlocks.HALFINUM_ORE);
            event.accept(DMBlocks.DEEPSLATE_HALFINUM_ORE);
            event.accept(DMBlocks.GLASS_TUBE);
            event.accept(DMBlocks.ZURBTELEPORTER);
            event.accept(DMBlocks.RED_GLASS_TUBE);
            event.accept(DMBlocks.GREEN_GLASS_TUBE);
            event.accept(DMBlocks.ORANGE_GLASS_TUBE);
            event.accept(DMBlocks.ELECTRONIC_VINE);
            event.accept(DMBlocks.HOUSE_WALL);
            event.accept(DMBlocks.HOUSE_WALL_B);
            event.accept(DMBlocks.FACTORY_BLOCK);
            event.accept(DMBlocks.COUPE);
            event.accept(DMBlocks.MARKS_CONCRETE);
            event.accept(DMBlocks.STONE_ROCK);
            event.accept(DMBlocks.BLUE_BRICKS);
            event.accept(DMBlocks.GREEN_BRICKS);
            event.accept(DMBlocks.YELLOW_BRICKS);
            event.accept(DMBlocks.LIGHT_BLUE_BRICKS);
            event.accept(DMBlocks.GREEN_SCREEN);
            event.accept(DMBlocks.CREEPER_WALLPAPER);
            event.accept(DMBlocks.CHECKERBOARD_WALLPAPER);
            event.accept(DMBlocks.YELLOW_ORANGE_WALLPAPER);
            event.accept(DMBlocks.SMOKE_BLOCK);
            event.accept(DMBlocks.Toyota);
            event.accept(DMBlocks.RedToyota);
            event.accept(DMBlocks.NINEDOCTEUR);
            event.accept(DMBlocks.PANDAREBEL);
            event.accept(DMBlocks.JOSIA);
            event.accept(DMBlocks.LIGHT_BLOCK);
        }
        if(event.getTab() == DMCreativeTabs.MATERIALS){
            event.accept(DMItems.AMETHYST);
            event.accept(DMItems.ZINC_INGOT);
            event.accept(DMItems.CRYSTALINE);
            event.accept(DMItems.CRYSTAL);
            event.accept(DMItems.ZINC_NUGGET);
            event.accept(DMItems.HALFINUM_NUGGET);
            event.accept(DMItems.CRYOLITE);
            event.accept(DMItems.LIGHT_BLUE_BRICKS_INGOT);
            event.accept(DMItems.GREEN_BRICKS_INGOT);
            event.accept(DMItems.YELLOW_BRICKS_INGOT);
            event.accept(DMItems.BLUE_BRICKS_INGOT);
            event.accept(DMItems.HALFINUM_INGOT);
            event.accept(DMItems.WHITE_POINTED_STAR);
            event.accept(DMItems.FUEL);
            event.accept(DMItems.WHITE_POINTED_STAR);
            event.accept(DMItems.STEEL_INGOT);
            //event.accept(DMItems.DEATH_SIGN);
            event.accept(DMItems.RAW_STEEL_INGOT);
            event.accept(DMItems.EMPTY_FLASK);
            event.accept(DMItems.COPPER_FLASK);
            event.accept(DMItems.ZINC_FLASK);
            event.accept(DMItems.HALFINUM_FLASK);
            //event.accept(DMItems.ALBIZIA_SIGN);
        }
        if(event.getTab() == DMCreativeTabs.TOOLS){
            event.accept(DMItems.ZINC_HELMET);
            event.accept(DMItems.ZINC_CHESTPLATE);
            event.accept(DMItems.ZINC_LEGGINGS);
            event.accept(DMItems.ZINC_BOOTS);
            event.accept(DMItems.COPPER_HELMET);
            event.accept(DMItems.COPPER_CHESTPLATE);
            event.accept(DMItems.COPPER_LEGGINGS);
            event.accept(DMItems.COPPER_BOOTS);
            event.accept(DMItems.HALFINUM_HELMET);
            event.accept(DMItems.HALFINUM_CHESTPLATE);
            event.accept(DMItems.HALFINUM_LEGGINGS);
            event.accept(DMItems.HALFINUM_BOOTS);
            event.accept(DMItems.COPPER_SWORD);
            event.accept(DMItems.COPPER_PICKAXE);
            event.accept(DMItems.COPPER_AXE);
            event.accept(DMItems.ZINC_SWORD);
            event.accept(DMItems.ZINC_PICKAXE);
            event.accept(DMItems.ZINC_AXE);
            event.accept(DMItems.STAFF);
            event.accept(DMItems.ALBIZIA_STICK);
            event.accept(DMItems.HALFINUM_AXE);
            event.accept(DMItems.HALFINUM_PICKAXE);
            event.accept(DMItems.HALFINUM_SHOVEL);
            event.accept(DMItems.HALFINUM_SWORD);
            //event.accept(DMItems.DEATH_SIGN);
            event.accept(DMItems.ORE_FINDER);
            event.accept(DMItems.ELECTRONIC_CICUIT);
            event.accept(DMItems.ELECTRONIC_CICUIT);
            event.accept(DMItems.SONIC_SCREWDRIVER);
            event.accept(DMItems.TEN_SONIC_SCREWDRIVER);
            event.accept(DMItems.THEER_SONIC_SCREWDRIVER);
            event.accept(DMItems.ROBAINKS_SONIC_SCREWDRIVER);
            event.accept(DMItems.GARATIM_SONIC_SCREWDRIVER);
            event.accept(DMItems.CYBER_GUN);
            event.accept(DMItems.DALEK_GUN);
            event.accept(DMItems.RPG_GUN);
            event.accept(DMItems.MAGIC_WAND);
        }
        if(event.getTab() == DMCreativeTabs.FOOD){
            event.accept(DMItems.BELGIUM_FRIES);
            event.accept(DMItems.TEA);
            event.accept(DMItems.COFFEE);
            event.accept(DMItems.JUPILER);
            event.accept(DMItems.ICE_CREAM);
            event.accept(DMItems.HAMBURGER);
            event.accept(DMItems.DOCTEAM_COLA);
        }
        if(event.getTab() == DMCreativeTabs.ENTITIES){
            event.accept(DMItems.STEVE_EGG);
            event.accept(DMItems.CYBERMAN_EGG);
            event.accept(DMItems.CYBERBOSS_EGG);
            event.accept(DMItems.CYBERHUMAN_EGG);
            event.accept(DMItems.CYBERHUMAN_2_EGG);
            event.accept(DMItems.CYBERHUMAN_3_EGG);
            event.accept(DMItems.DALEK_EGG);
            event.accept(DMItems.SWDALEK_EGG);
        }
        if(event.getTab() == DMCreativeTabs.ROUNDELS){
            event.accept(DMBlocks.ROUNDEL_DARK);
            event.accept(DMBlocks.ROUNDEL_DARK_2);
            event.accept(DMBlocks.ROUNDEL_YELLOW);
            event.accept(DMBlocks.ROUNDEL_YELLOW_2);
            event.accept(DMBlocks.ROUNDEL_BLUE);
            event.accept(DMBlocks.ROUNDEL_BLUE_2);
            event.accept(DMBlocks.ROUNDEL_RED);
            event.accept(DMBlocks.ROUNDEL_RED_2);
            event.accept(DMBlocks.ROUNDEL_GREEN);
            event.accept(DMBlocks.ROUNDEL_GREEN_2);
            event.accept(DMBlocks.ROUNDEL_COPPER);
            event.accept(DMBlocks.ROUNDEL_CORAL);
            event.accept(DMBlocks.ROUNDEL_RED_CUSTOM);
            event.accept(DMBlocks.ROUNDEL_BLUE_CUSTOM);
            event.accept(DMBlocks.ROUNDEL_LIME_CUSTOM);
            event.accept(DMBlocks.ROUNDEL_GREEN_CUSTOM);
            event.accept(DMBlocks.ROUNDEL_CYAN_CUSTOM);
            event.accept(DMBlocks.ROUNDEL_LIGHT_BLUE_CUSTOM);
            event.accept(DMBlocks.ROUNDEL_MAGENTA_CUSTOM);
            event.accept(DMBlocks.ROUNDEL_ORANGE_CUSTOM);
            event.accept(DMBlocks.ROUNDEL_PURPLE_CUSTOM);
            event.accept(DMBlocks.ROUNDEL_YELLOW_CUSTOM);
            event.accept(DMBlocks.ROUNDEL_LAVA);
            event.accept(DMBlocks.ROUNDEL_LAVA_2);
            event.accept(DMItems.ROUND_THING_FRAME);
            event.accept(DMItems.RED_ROUND_THING_FRAME);
            event.accept(DMItems.BLUE_ROUND_THING_FRAME);
            event.accept(DMItems.GREEN_ROUND_THING_FRAME);
            event.accept(DMItems.YELLOW_ROUND_THING_FRAME);
            event.accept(DMItems.DARK_ROUND_THING_FRAME);
        }
        if(event.getTab() == DMCreativeTabs.CHRISTMAS){
            event.accept(DMBlocks.CHRISTMAS_TREE);
            event.accept(DMBlocks.SNOW_BALL);
            event.accept(DMBlocks.CHIMNEY);
            event.accept(DMBlocks.RED_CHRISTMAS_BALL);
            event.accept(DMBlocks.BLUE_CHRISTMAS_BALL);
            event.accept(DMBlocks.GREEN_CHRISTMAS_BALL);
            event.accept(DMBlocks.GOLD_CHRISTMAS_BALL);
            event.accept(DMBlocks.RED_CANDYCANE);
            event.accept(DMBlocks.YELLOW_CANDYCANE);
            event.accept(DMBlocks.GREEN_CANDYCANE);
            event.accept(DMBlocks.ORANGE_CANDYCANE);
            event.accept(DMBlocks.PURPLE_CANDYCANE);
            event.accept(DMBlocks.ROSE_CANDYCANE);
            event.accept(DMBlocks.BLUE_CANDYCANE);
            event.accept(DMItems.GINGERBREAD);
            event.accept(DMItems.EXPLOSIVE_BALL);
        }
        if(event.getTab() == DMCreativeTabs.ANNIVERSARY){
            event.accept(DMBlocks.CAKE);
        }
    }

    private void commonSetup(FMLCommonSetupEvent event){
    	event.enqueueWork(() -> {
    		LOGGER.info("Starting Common Setup...");
    		PacketHandler.registerPackets();
	        LOGGER.info("Packets Registered", false);
	        DMWoodTypes.registerWoodTypes();
	        LOGGER.info("Registring Staff...");
	        TeamUUIDs.addAdmin();
	        LOGGER.info("Init Core Addon..");
    	});
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
    
    private void closeGameForBannedPeopleRunningTheMod() {
    	if(getBannedPeople()) {
    		System.exit(1);
    	}
    }
    
    public static boolean getBannedPeople() {
    	//TODO: Change to UUID to avoid player changing name are unbanned
    	UUID playerUUID = Minecraft.getInstance().player.getUUID();
    	return playerUUID.toString().contains((CharSequence) Component.literal(IOUtils.readURLContent(DocTeamAPI.getAPI() + "docmod/ban/banned_players.txt")));
    }
    
    public static void prepareDownload() {
    	AnimatedCapeHandler.readCapeTexture(DocTeamAPI.getAPI() + "docmod/cape/ninety/" + AnimatedCapeHandler.i + ".png", AnimatedCapeHandler.i);
    }
}
