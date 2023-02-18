package be.ninedocteur.docmod.jobs.data;

import be.ninedocteur.docmod.jobs.data.registry.*;
import be.ninedocteur.docmod.jobs.network.PacketUpdateClientJobsData;
import be.ninedocteur.docmod.jobs.util.config.JobsIconUtil;
import be.ninedocteur.docmod.jobs.util.handler.PacketHandler;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Items;
import net.minecraftforge.network.NetworkDirection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ServerJobsData {

    public static final XPRegistry.ItemXPRegistry CRAFTING_ITEMS_XP = new XPRegistry.ItemXPRegistry("crafting", Items.CRAFTING_TABLE);
    public static final XPRegistry.ItemXPRegistry SMELTING_ITEMS_XP = new XPRegistry.ItemXPRegistry("smelting", Items.FURNACE);
    public static final XPRegistry.BlockXPRegistry BREAKING_BLOCKS_XP = new XPRegistry.BlockXPRegistry("breaking", Items.DIAMOND_PICKAXE);
    public static final XPRegistry.BlockXPRegistry HARVESTING_CROPS_XP = new XPRegistry.BlockXPRegistry("harvesting", Items.IRON_HOE);
    public static final XPRegistry.EntityXPRegistry KILLING_ENTITY_XP = new XPRegistry.EntityXPRegistry("killing", Items.NETHERITE_SWORD);
    public static final XPRegistry.EntityXPRegistry BREEDING_ENTITY_XP = new XPRegistry.EntityXPRegistry("breeding", Items.WHEAT);
    public static final XPRegistry.ItemXPRegistry FISHING_ITEMS_XP = new XPRegistry.ItemXPRegistry("fishing", Items.FISHING_ROD);

    public static final Map<String, byte[]> JOBS_ICONS = new HashMap<>();

    public static final RewardsData REWARDS = new RewardsData();
    public static final LevelData JOBS_LEVELS = new LevelData();
    public static final TranslationData TRANSLATIONS = new TranslationData();
    public static final BlockedCraftsData BLOCKED_CRAFTS = new BlockedCraftsData();
    public static final BlockedBlocksData BLOCKED_BLOCKS = new BlockedBlocksData();
    private static final Set<XPRegistry<? extends XPData>> XP_REGISTRIES = new HashSet<>();


    /**
     * Registers the XP Registry
     * @param registry the registry to register
     */
    public static void registerXPRegistry(XPRegistry<? extends XPData> registry){
        XP_REGISTRIES.add(registry);
    }

    /**
     * Registers the common registries used by the mod
     */
    public static void registerCommonXPRegistries(){
        registerXPRegistry(CRAFTING_ITEMS_XP);
        registerXPRegistry(SMELTING_ITEMS_XP);
        registerXPRegistry(BREAKING_BLOCKS_XP);
        registerXPRegistry(HARVESTING_CROPS_XP);
        registerXPRegistry(KILLING_ENTITY_XP);
        registerXPRegistry(BREEDING_ENTITY_XP);
        registerXPRegistry(FISHING_ITEMS_XP);
    }


    /**
     * Sends all the data about the jobs to the client
     * @param player the client who will receive the data
     */
    public static void sendDataToClient(ServerPlayer player) {
        PacketUpdateClientJobsData packet1 = new PacketUpdateClientJobsData(XP_REGISTRIES,
                JOBS_LEVELS,
                BLOCKED_CRAFTS,
                BLOCKED_BLOCKS,
                JOBS_ICONS,
                TRANSLATIONS);
        PacketHandler.INSTANCE.sendTo(packet1,
                player.connection.getConnection(),
                NetworkDirection.PLAY_TO_CLIENT);
    }
}
