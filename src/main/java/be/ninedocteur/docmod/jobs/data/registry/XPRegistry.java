package be.ninedocteur.docmod.jobs.data.registry;

import be.ninedocteur.docmod.jobs.util.JobsUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;
import java.util.stream.Collectors;

public abstract class XPRegistry <T extends XPData> {

    private final Map<String, List<T>> data = new HashMap<>();
    private final String name;
    private final Item icon;
    private final Type type;

    /**
     * Creates a new XP Registry of a specific type
     * @param type Item/Block/Entity
     * @param name the registry name
     * @param icon the icon that should be displayed in the How XP GUI
     */
    public XPRegistry(Type type, String name, Item icon) {
        this.name = name;
        this.icon = icon;
        this.type = type;
    }

    /**
     * Reads a XP Registry from a buffer
     * @param buf the buffer where to read
     * @param type the type of the XP Registry
     */
    private XPRegistry(FriendlyByteBuf buf, Type type) {
        this.type = type;
        this.name = JobsUtil.readString(buf);
        this.icon = Item.byId(buf.readInt());
    }

    /**
     * Gets a list of XPData associated to a job
     * @param job the job associated
     * @return the list of XPData
     */
    public List<T> getXPDataByJob(String job){
        if(!data.containsKey(job))
            return new ArrayList<>();
        return data.get(job);
    }

    /**
     * @return the set of jobs present in the Registry
     */
    public Set<String> getJobs(){
        return data.keySet();
    }

    /**
     * @return the config file name of the XP Registry
     */
    public String getFileName(){
        return "xp_" + name.toLowerCase() + ".json";
    }

    /**
     * Adds an XPData for a specific job
     * @param job the job for which the data is added
     * @param xpdata the data added
     */
    public void addDataForJob(String job, T xpdata){
        if(!data.containsKey(job))
            data.put(job, new ArrayList<>());
        data.get(job).add(xpdata);
    }

    /**
     * Writes the XP Registry to a byte buffer
     * @param buf the buffer where to write
     */
    public void writeToBytes(FriendlyByteBuf buf){
        buf.writeInt(type.id);
        JobsUtil.writeString(name, buf);
        buf.writeInt(Item.getId(icon));
        buf.writeInt(data.size());
        for(Map.Entry<String, List<T>> entry : data.entrySet()){
            JobsUtil.writeString(entry.getKey(), buf);
            buf.writeInt(entry.getValue().size());
            for(T xpdata : entry.getValue()){
                xpdata.writeToBytes(buf);
            }
        }
    }

    public String getName() {
        return name;
    }

    public Item getIcon() {
        return icon;
    }

    /**
     * Clears all data from the registry
     */
    public void clear(){
        this.data.clear();
    }

    /**
     * Reads a byte buffer and creates an XP Registry based on it
     * @param buf the buffer where to read
     * @return the created XP Registry
     */
    public static XPRegistry<? extends XPData> fromBytes(FriendlyByteBuf buf){
        Type type = Type.byID(buf.readInt());
        if(type == null)
            return null;
        switch (type){
            case ITEM:
                return new ItemXPRegistry(buf);
            case BLOCK:
                return new BlockXPRegistry(buf);
            case ENTITY:
                return new EntityXPRegistry(buf);
        }
        return null;
    }

    /**
     * @return a string representation of the XP Registry
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(name).append(" XP Registry (icon = ")
                                                 .append(ForgeRegistries.ITEMS.getKey(icon).toString())
                                                 .append(") :\n");
        for(Map.Entry<String, List<T>> e : data.entrySet()){
            s.append(" * ").append(e.getKey()).append(" ->\n");
            for(T xp : e.getValue()){
                s.append("    - ").append(xp.toString());
            }
        }
        return s.toString();
    }

    public static final class ItemXPRegistry extends XPRegistry<XPData.ItemXPData>{

        /**
         * Creates an Item XP Registry
         * @param name the name of the registry
         * @param icon the registry icon
         */
        public ItemXPRegistry(String name, Item icon) {
            super(Type.ITEM, name, icon);
        }

        /**
         * Reads an Item XP Registry from a byte buffer
         * @param buf the buffer where to read
         */
        public ItemXPRegistry(FriendlyByteBuf buf){
            super(buf, Type.ITEM);
            int size = buf.readInt();
            for(int i = 0; i < size; i++){
                String job = JobsUtil.readString(buf);
                int amount = buf.readInt();
                for(int j = 0; j < amount; j++)
                    this.addDataForJob(job, new XPData.ItemXPData(buf));
            }
        }

        /**
         * Gets the XP the player receives for that Item Stack for a specific job and level
         * @param stack the stack to check
         * @param level the level of the player
         * @param job the job to check
         * @return the amount of XP the player will receive
         */
        public long getXPByLevelAndJob(ItemStack stack, int level, String job){
            return getXPDataByJob(job).stream()
                                      .filter(x -> x.matches(stack))
                                      .findFirst()
                                      .map(x -> x.getXPByLevel(level))
                                      .orElse(0L);
        }
    }

    public static final class BlockXPRegistry extends XPRegistry<XPData.BlockXPData>{

        /**
         * Creates a Block XP Registry
         * @param name the name of the registry
         * @param icon the registry icon
         */
        public BlockXPRegistry(String name, Item icon) {
            super(Type.BLOCK, name, icon);
        }

        /**
         * Reads a Block XP Registry from a byte buffer
         * @param buf the buffer where to read
         */
        public BlockXPRegistry(FriendlyByteBuf buf){
            super(buf, Type.BLOCK);
            int size = buf.readInt();
            for(int i = 0; i < size; i++){
                String job = JobsUtil.readString(buf);
                int amount = buf.readInt();
                for(int j = 0; j < amount; j++)
                    this.addDataForJob(job, new XPData.BlockXPData(buf));
            }
        }

        /**
         * Gets the XP the player receives for that Block state for a specific job and level
         * @param state the state to check
         * @param level the level of the player
         * @param job the job to check
         * @return the amount of XP the player will receive
         */
        public long getXPByLevelAndJob(BlockState state, int level, String job){
            return getXPDataByJob(job).stream()
                    .filter(x -> x.matches(state))
                    .findFirst()
                    .map(x -> x.getXPByLevel(level))
                    .orElse(0L);
        }
    }

    public static final class EntityXPRegistry extends XPRegistry<XPData.EntityXPData>{

        /**
         * Creates an Entity XP Registry
         * @param name the name of the registry
         * @param icon the registry icon
         */
        public EntityXPRegistry(String name, Item icon) {
            super(Type.ENTITY, name, icon);
        }

        /**
         * Reads an Entity XP Registry from a byte buffer
         * @param buf the buffer where to read
         */
        public EntityXPRegistry(FriendlyByteBuf buf){
            super(buf, Type.ENTITY);
            int size = buf.readInt();
            for(int i = 0; i < size; i++){
                String job = JobsUtil.readString(buf);
                int amount = buf.readInt();
                for(int j = 0; j < amount; j++)
                    this.addDataForJob(job, new XPData.EntityXPData(buf));
            }
        }

        /**
         * Gets the XP the player receives for that entity for a specific job and level
         * @param entity the entity to check
         * @param level the level of the player
         * @param job the job to check
         * @return the amount of XP the player will receive
         */
        public long getXPByLevelAndJob(EntityType<? extends Entity> entity, int level, String job){
            return getXPDataByJob(job).stream()
                    .filter(x -> x.matches(entity))
                    .findFirst()
                    .map(x -> x.getXPByLevel(level))
                    .orElse(0L);
        }
    }

    private enum Type{
        ITEM(0),
        BLOCK(1),
        ENTITY(2);

        public final int id;
        Type(int id){
            this.id = id;
        }

        public static Type byID(int id){
            for(Type t : Type.values())
                if(t.id == id)
                    return t;
            return null;
        }
    }
}
