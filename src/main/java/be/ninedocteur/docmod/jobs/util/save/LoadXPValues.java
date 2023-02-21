package be.ninedocteur.docmod.jobs.util.save;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map.Entry;

import be.ninedocteur.docmod.jobs.data.GainXPUtil;
import be.ninedocteur.docmod.jobs.util.Constants;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.ChatFormatting;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.registries.ForgeRegistries;

public class LoadXPValues {




    public static void loadCraftItemXP(MinecraftServer server)
    {
        try
        {
            File file = new File(LoadUtil.getDataFolder(server) + "/xp_craftitem.json");
            if(!file.exists())
            {
                LoadUtil.createEmptyJSON(new File(LoadUtil.getDataFolder(server)), "xp_craftitem.json");
                return;
            }

            JsonObject json = (JsonObject) new JsonParser().parse(new FileReader(file));
            for(Entry<String, JsonElement> entry : json.entrySet())
            {
                String name = entry.getKey().replace('-', ':');
                long[] xps = new long[25];
                JsonObject property = entry.getValue().getAsJsonObject();
                JsonArray array = property.get("xp").getAsJsonArray();
                for(int i = 0; i < 25; i++)
                {
                    xps[i] = array.get(i).getAsLong();
                }

                GainXPUtil.CRAFT_ITEM_JOB.put(ForgeRegistries.ITEMS.getValue(new ResourceLocation(name)), Constants.Job.valueOf(property.get("job").getAsString()));
                GainXPUtil.CRAFT_ITEM_XP.put(ForgeRegistries.ITEMS.getValue(new ResourceLocation(name)), xps);
            }
        }
        catch(IOException e)
        {
            System.out.println(ChatFormatting.RED + "[Jobs] Failed to load CraftItem XP !");
        }

    }

    public static void loadSmeltItemXP(MinecraftServer server)
    {
        try
        {
            File file = new File(LoadUtil.getDataFolder(server) + "/xp_smeltitem.json");
            if(!file.exists())
            {
                LoadUtil.createEmptyJSON(new File(LoadUtil.getDataFolder(server)), "xp_smeltitem.json");
                return;
            }

            JsonObject json = (JsonObject) new JsonParser().parse(new FileReader(file));
            for(Entry<String, JsonElement> entry : json.entrySet())
            {
                String name = entry.getKey().replace('-', ':');
                long[] xps = new long[25];
                JsonObject property = entry.getValue().getAsJsonObject();
                JsonArray array = property.get("xp").getAsJsonArray();
                for(int i = 0; i < 25; i++)
                {
                    xps[i] = array.get(i).getAsLong();
                }

                GainXPUtil.SMELT_ITEM_JOB.put(ForgeRegistries.ITEMS.getValue(new ResourceLocation(name)), Constants.Job.valueOf(property.get("job").getAsString()));
                GainXPUtil.SMELT_ITEM_XP.put(ForgeRegistries.ITEMS.getValue(new ResourceLocation(name)), xps);
            }
        }
        catch(IOException e)
        {
            System.out.println(ChatFormatting.RED + "[Jobs] Failed to load SmeltItem XP !");
        }

    }


    public static void loadBreakBlockXP(MinecraftServer server)
    {
        try
        {
            File file = new File(LoadUtil.getDataFolder(server) + "/xp_breakblock.json");
            if(!file.exists())
            {
                LoadUtil.createEmptyJSON(new File(LoadUtil.getDataFolder(server)), "xp_breakblock.json");
                return;
            }

            JsonObject json = (JsonObject) new JsonParser().parse(new FileReader(file));
            for(Entry<String, JsonElement> entry : json.entrySet())
            {
                String name = entry.getKey().replace('-', ':');
                long[] xps = new long[25];
                JsonObject property = entry.getValue().getAsJsonObject();
                JsonArray array = property.get("xp").getAsJsonArray();
                for(int i = 0; i < 25; i++)
                {
                    xps[i] = array.get(i).getAsLong();
                }

                GainXPUtil.BREAK_BLOCK_JOB.put(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(name)), Constants.Job.valueOf(property.get("job").getAsString()));
                GainXPUtil.BREAK_BLOCK_XP.put(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(name)), xps);
            }
        }
        catch(IOException e)
        {
            System.out.println(ChatFormatting.RED + "[Jobs] Failed to load BlockBreak XP !");
        }

    }

    public static void loadHarvestCropXP(MinecraftServer server)
    {
        try
        {
            File file = new File(LoadUtil.getDataFolder(server) + "/xp_harvestcrop.json");
            if(!file.exists())
            {
                LoadUtil.createEmptyJSON(new File(LoadUtil.getDataFolder(server)), "xp_harvestcrop.json");
                return;
            }

            JsonObject json = (JsonObject) new JsonParser().parse(new FileReader(file));
            for(Entry<String, JsonElement> entry : json.entrySet())
            {
                String name = entry.getKey().replace('-', ':');
                long[] xps = new long[25];
                JsonObject property = entry.getValue().getAsJsonObject();
                JsonArray array = property.get("xp").getAsJsonArray();
                for(int i = 0; i < 25; i++)
                {
                    xps[i] = array.get(i).getAsLong();
                }

                GainXPUtil.HARVEST_CROP_JOB.put(ForgeRegistries.ITEMS.getValue(new ResourceLocation(name)), Constants.Job.valueOf(property.get("job").getAsString()));
                GainXPUtil.HARVEST_CROP_XP.put(ForgeRegistries.ITEMS.getValue(new ResourceLocation(name)), xps);
            }
        }
        catch(IOException e)
        {
            System.out.println(ChatFormatting.RED + "[Jobs] Failed to load HarvestCrop XP !");
        }

    }

    public static void loadKillEntityXP(MinecraftServer server)
    {
        try
        {
            File file = new File(LoadUtil.getDataFolder(server) + "/xp_killentity.json");
            if(!file.exists())
            {
                LoadUtil.createEmptyKillEntityJSON(new File(LoadUtil.getDataFolder(server)), "xp_killentity.json");
                return;
            }

            JsonObject json = (JsonObject) new JsonParser().parse(new FileReader(file));
            for(Entry<String, JsonElement> entry : json.entrySet())
            {
                String name = entry.getKey().replace("-", " ");
                long[] xps = new long[25];
                JsonObject property = entry.getValue().getAsJsonObject();
                JsonArray array = property.get("xp").getAsJsonArray();
                for(int i = 0; i < 25; i++)
                {
                    xps[i] = array.get(i).getAsLong();
                }

                GainXPUtil.KILL_ENTITY_JOB.put(name, Constants.Job.valueOf(property.get("job").getAsString()));
                GainXPUtil.KILL_ENTITY_XP.put(name, xps);
            }
        }
        catch(IOException e)
        {
            System.out.println(ChatFormatting.RED + "[Jobs] Failed to load KillEntity XP !");
        }

    }


}
