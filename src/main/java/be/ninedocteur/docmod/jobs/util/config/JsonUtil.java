package be.ninedocteur.docmod.jobs.util.config;

import be.ninedocteur.docmod.jobs.data.registry.BlockedBlocksData;
import be.ninedocteur.docmod.jobs.data.registry.BlockedCraftsData;
import be.ninedocteur.docmod.jobs.data.registry.RewardsData;
import be.ninedocteur.docmod.jobs.data.registry.XPData;
import com.google.gson.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import be.ninedocteur.docmod.jobs.util.JobsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JsonUtil {

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Converts a long array to a JSON array
     * @param array the array to convert
     * @return the json version of the array
     */
    public static JsonArray longArrayToJSON(long[] array){
        JsonArray jsonArray = new JsonArray();
        for(long l : array)
            jsonArray.add(l);
        return jsonArray;
    }

    /**
     * load a long array from a JSON array
     * @param jsonArray the array to convert
     * @return the long array
     */
    public static long[] longArrayFromJSON(JsonArray jsonArray){
        long[] array = new long[jsonArray.size()];
        for(int i = 0; i < array.length; i++)
            array[i] = jsonArray.get(i).getAsLong();
        return array;
    }

    /**
     * Creates a JSON object representing an Item Stack
     * @param stack the stack to represent
     * @return the JSON object representing the stack
     */
    public static JsonObject itemStackToJSON(ItemStack stack){
        JsonObject object = new JsonObject();
        object.addProperty("item", ForgeRegistries.ITEMS.getKey(stack.getItem()).toString());
        object.addProperty("count", stack.getCount());
        object.addProperty("metadata", stack.getDamageValue());
        return object;
    }

    /**
     * Creates the Item Stack represented in the JSON object
     * @param object the JSON object representing the Item Stack
     * @return the created Item Stack
     */
    public static Optional<ItemStack> itemStackFromJSON(JsonObject object){
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(object.get("item").getAsString()));
        if(item == null)
            return Optional.empty();
        int count = object.get("count").getAsInt();
        int metadata = -1;
        if(object.has("metadata"))
            metadata = object.get("metadata").getAsInt();
        return Optional.of(JobsUtil.itemStack(item, count, metadata));
    }

    /**
     * Creates a JSON object representing the XPData
     * @param data the XPData to represent
     * @return the JSON object representing the data
     */
    public static JsonObject itemXPDataToJSON(XPData.ItemXPData data){
        JsonObject object = new JsonObject();
        object.addProperty("item", ForgeRegistries.ITEMS.getKey(data.getItem()).toString());
        if(data.getMetadata() >= 0)
            object.addProperty("metadata", data.getMetadata());
        JsonArray array = longArrayToJSON(data.getXP_values());
        object.add("xp", array);
        return object;
    }

    /**
     * Creates the Item XPData represented in the JSON object
     * @param object the JSON object representing the Item XPData
     * @return the created Item XPData
     */
    public static Optional<XPData.ItemXPData> itemXPDataFromJSON(JsonObject object){
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(object.get("item").getAsString()));
        if(item == null)
            return Optional.empty();
        int metadata = -1;
        if(object.has("metadata"))
            metadata = object.get("metadata").getAsInt();
        JsonArray array = object.getAsJsonArray("xp");
        long[] xp = longArrayFromJSON(array);
        return Optional.of(new XPData.ItemXPData(xp, item, metadata));
    }

    /**
     * Creates a JSON object representing the XPData
     * @param data the XPData to represent
     * @return the JSON object representing the data
     */
    public static JsonObject blockXPDataToJSON(XPData.BlockXPData data){
        JsonObject object = new JsonObject();
        object.addProperty("block", ForgeRegistries.BLOCKS.getKey(data.getBlock()).toString());
        JsonArray array = longArrayToJSON(data.getXP_values());
        object.add("xp", array);
        return object;
    }

    /**
     * Creates the Block XPData represented in the JSON object
     * @param object the JSON object representing the Block XPData
     * @return the created Block XPData
     */
    public static Optional<XPData.BlockXPData> blockXPDataFromJSON(JsonObject object){
        Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(object.get("block").getAsString()));
        if(block == null)
            return Optional.empty();
        JsonArray array = object.getAsJsonArray("xp");
        long[] xp = longArrayFromJSON(array);
        return Optional.of(new XPData.BlockXPData(xp, block));
    }

    /**
     * Creates a JSON object representing the XPData
     * @param data the XPData to represent
     * @return the JSON object representing the data
     */
    public static JsonObject entityXPDataToJSON(XPData.EntityXPData data){
        JsonObject object = new JsonObject();
        object.addProperty("entity", EntityType.getKey(data.getEntity()).toString());
        JsonArray array = longArrayToJSON(data.getXP_values());
        object.add("xp", array);
        return object;
    }

    /**
     * Creates the Entity XPData represented in the JSON object
     * @param object the JSON object representing the entity XPData
     * @return the created Entity XPData
     */
    public static Optional<XPData.EntityXPData> entityXPDataFromJSON(JsonObject object){
        EntityType<? extends Entity> entity = EntityType.byString(object.get("entity").getAsString()).orElse(null);
        if(entity == null)
            return Optional.empty();
        JsonArray array = object.getAsJsonArray("xp");
        long[] xp = longArrayFromJSON(array);
        return Optional.of(new XPData.EntityXPData(xp, entity));
    }

    /**
     * Creates a JSON array representing the Reward
     * @param reward the Reward to represent
     * @return the JSON array representing the Reward
     */
    public static JsonArray rewardToJSON(RewardsData.Reward reward){
        JsonArray stacks = new JsonArray();
        for(ItemStack s : reward.getRewards())
            stacks.add(itemStackToJSON(s));
        return stacks;
    }

    /**
     * Creates the Reward represented in the JSON array
     * @param array the JSON object representing the Reward
     * @param level the level of the Reward
     * @return the created Reward
     */
    public static Optional<RewardsData.Reward> rewardFromJSON(JsonArray array, int level){
        List<ItemStack> stacks = new ArrayList<>();
        for(JsonElement e : array)
            itemStackFromJSON(e.getAsJsonObject()).ifPresent(stacks::add);
        if(stacks.isEmpty())
            return Optional.empty();
        return Optional.of(new RewardsData.Reward(level, stacks));
    }

    /**
     * Creates a JSON object representing the Blocked Craft data
     * @param craft the Blocked Craft data to represent
     * @return the JSON object representing the Blocked Craft data
     */
    public static JsonObject blockedCraftToJSON(BlockedCraftsData.BlockedCraft craft){
        JsonObject object = new JsonObject();
        object.addProperty("item", ForgeRegistries.ITEMS.getKey(craft.getCraft()).toString());
        if(craft.getMetadata() >= 0)
            object.addProperty("metadata", craft.getMetadata());
        object.addProperty("level", craft.getLevel());
        return object;
    }

    /**
     * Creates the Blocked Craft data represented in the JSON object
     * @param object the JSON object representing the Blocked Craft XPData
     * @return the created Block Craft XPData
     */
    public static Optional<BlockedCraftsData.BlockedCraft> blockedCraftFromJSON(JsonObject object){
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(object.get("item").getAsString()));
        if(item == null)
            return Optional.empty();
        int metadata = -1;
        if(object.has("metadata"))
            metadata = object.get("metadata").getAsInt();
        int level = object.get("level").getAsInt();
        return Optional.of(new BlockedCraftsData.BlockedCraft(level, item, metadata));
    }

    /**
     * Creates a JSON object representing the Blocked Block data
     * @param block the Blocked Block data to represent
     * @return the JSON object representing the Blocked Block data
     */
    public static JsonObject blockedBlockToJSON(BlockedBlocksData.BlockedBlock block){
        JsonObject object = new JsonObject();
        object.addProperty("block", ForgeRegistries.BLOCKS.getKey(block.getBlock()).toString());
        object.addProperty("level", block.getLevel());
        return object;
    }

    /**
     * Creates the Blocked Block data represented in the JSON object
     * @param object the JSON object representing the Blocked Block data
     * @return the created Blocked Block data
     */
    public static Optional<BlockedBlocksData.BlockedBlock> blockedBlockFromJSON(JsonObject object){
        Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(object.get("block").getAsString()));
        if(block == null)
            return Optional.empty();
        int level = object.get("level").getAsInt();
        return Optional.of(new BlockedBlocksData.BlockedBlock(level, block));
    }
}
