package be.ninedocteur.docmod.jobs.data;

import be.ninedocteur.docmod.jobs.network.PacketUpdateClientInfos;
import be.ninedocteur.docmod.jobs.util.Constants;
import be.ninedocteur.docmod.network.PacketHandler;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.network.NetworkDirection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GainXPUtil {

    public static Map<Block, long[]> BREAK_BLOCK_XP = new HashMap<>();
    public static Map<Block, Constants.Job> BREAK_BLOCK_JOB = new HashMap<>();

    public static Map<Item, long[]> HARVEST_CROP_XP = new HashMap<>();
    public static Map<Item, Constants.Job> HARVEST_CROP_JOB = new HashMap<>();

    public static Map<Item, long[]> CRAFT_ITEM_XP = new HashMap<>();
    public static Map<Item, Constants.Job> CRAFT_ITEM_JOB = new HashMap<>();

    public static Map<Item, long[]> SMELT_ITEM_XP = new HashMap<>();
    public static Map<Item, Constants.Job> SMELT_ITEM_JOB = new HashMap<>();

    public static Map<String, long[]> KILL_ENTITY_XP = new HashMap<>();
    public static Map<String, Constants.Job> KILL_ENTITY_JOB = new HashMap<>();


    public static Map<Item, Integer> CRAFT_UNLOCK_LVL = new HashMap<>();
    public static Map<Item, Constants.Job> CRAFT_UNLOCK_JOB = new HashMap<>();

    public static Map<Integer, List<ItemStack>> REWARDS_WARRIOR = new HashMap<>();
    public static Map<Integer, List<ItemStack>> REWARDS_WIZARD = new HashMap<>();
    public static Map<Integer, List<ItemStack>> REWARDS_HERBALIST = new HashMap<>();
    public static Map<Integer, List<ItemStack>> REWARDS_MINER = new HashMap<>();




    public static void sendDataToClient(ServerPlayer player)
    {
        PacketUpdateClientInfos packet1 = new PacketUpdateClientInfos(BREAK_BLOCK_XP, BREAK_BLOCK_JOB,
                                                                     HARVEST_CROP_XP, HARVEST_CROP_JOB,
                                                                     CRAFT_ITEM_XP, CRAFT_ITEM_JOB,
                                                                     SMELT_ITEM_XP, SMELT_ITEM_JOB,
                                                                     KILL_ENTITY_XP, KILL_ENTITY_JOB,
                                                                     CRAFT_UNLOCK_LVL, CRAFT_UNLOCK_JOB);
        PacketHandler.INSTANCE.sendTo(packet1, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
    }









}
