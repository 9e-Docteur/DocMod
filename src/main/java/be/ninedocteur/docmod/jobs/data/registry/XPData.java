package be.ninedocteur.docmod.jobs.data.registry;

import be.ninedocteur.docmod.jobs.util.JobsUtil;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;

public abstract class  XPData {

    private final long[] xp_values;

    /**
     * Creates the XPData based on the xp values stored as an array
     * @param xp_values the xp values
     */
    public XPData(long[] xp_values) {
        this.xp_values = xp_values;
    }

    /**
     * Reads an XPData from a byte buffer
     * @param buf the buffer where to read
     */
    public XPData(FriendlyByteBuf buf) {
        this.xp_values = JobsUtil.fromBytes(JobsUtil.readByteArray(buf));
    }

    /**
     * @return the xp values
     */
    public long[] getXP_values() {
        return xp_values;
    }

    /**
     * Gets the amount of xp a player would get based on its level
     * @param level the level of the player
     * @return the amount of xp the player would receive
     */
    public long getXPByLevel(int level){
        if(level < 0 || level >= this.xp_values.length)
            return 0;
        return this.xp_values[level];
    }

    /**
     * Returns the first level higher than the given level where the player receives more than 0xp
     * @param level the level the player is currently at
     * @return Returns the level where the player receives xp, or -1 if none was found
     */
    public int unlockingLevel(int level){
        for(int i = level+1; i < this.xp_values.length; i++)
            if(this.getXPByLevel(i) > 0)
                return i;
        return -1;
    }

    /**
     * Writes the XPData to a byte buffer
     * @param buf the buffer where to write
     */
    public void writeToBytes(FriendlyByteBuf buf){
        JobsUtil.writeByteArray(JobsUtil.toBytes(xp_values), buf);
    }

    /**
     * Creates a stack representing the XPData
     * @return an Item Stack representing the XPData
     */
    public abstract ItemStack createStack();

    /**
     * @return a string representation of the XPData
     */
    @Override
    public String toString() {
        return Arrays.toString(this.xp_values);
    }

    public static class ItemXPData extends XPData{
        private final Item item;
        private final int metadata;

        /**
         * Constructs an XPData for an Item
         * @param xp_values
         * @param item
         * @param metadata
         */
        public ItemXPData(long[] xp_values, Item item, int metadata) {
            super(xp_values);
            this.item = item;
            this.metadata = metadata;
        }

        /**
         * Reads an Item XPData from a byte buffer
         * @param buf the buffer where to read
         */
        public ItemXPData(FriendlyByteBuf buf){
            super(buf);
            this.item = Item.byId(buf.readInt());
            this.metadata = buf.readInt();
        }

        public Item getItem() {
            return item;
        }

        public int getMetadata() {
            return metadata;
        }

        /**
         * Checks if a stack is representing the item
         * @param stack the stack to check
         * @return true if the stack represent the Item XPData
         */
        public boolean matches(ItemStack stack){
            if(item != stack.getItem())
                return false;
            return metadata < 0 || metadata == stack.getDamageValue();
        }

        /**
         * Writes the Item XPData to a byte buffer
         * @param buf the buffer where to write
         */
        @Override
        public void writeToBytes(FriendlyByteBuf buf) {
            super.writeToBytes(buf);
            buf.writeInt(Item.getId(item));
            buf.writeInt(metadata);
        }

        /**
         * @return a stack representing the Item XP Data
         */
        @Override
        public ItemStack createStack() {
            return JobsUtil.itemStack(item, 1, metadata);
        }

        /**
         * @return a string representation of the Item XPData
         */
        @Override
        public String toString() {
            String s = ForgeRegistries.ITEMS.getKey(item).toString();
            if(metadata >= 0)
                s += " (" + metadata + ")";
            return  s + " : " + super.toString();
        }
    }


    public static class BlockXPData extends XPData{
        private final Block block;
        private final boolean isCrop;

        /**
         * Constructs an XPData for a Block
         * @param xp_values
         * @param block
         */
        public BlockXPData(long[] xp_values, Block block) {
            super(xp_values);
            this.block = block;
            this.isCrop = false;
        }

        /**
         * Constructs an XPData for a Block giving the option to register a Crops Block
         * @param xp_values
         * @param block
         * @param isCrop
         */
        public BlockXPData(long[] xp_values, Block block, boolean isCrop) {
            super(xp_values);
            this.block = block;
            this.isCrop = isCrop;
        }

        /**
         * Reads a Block XPData from a byte buffer
         * @param buf the buffer where to read
         */
        public BlockXPData(FriendlyByteBuf buf){
            super(buf);
            this.block = Block.stateById(buf.readInt()).getBlock();
            this.isCrop = buf.readBoolean();
        }

        public Block getBlock() {
            return block;
        }

        /**
         * Checks if a block state is representing the block
         * @param state the block state to check
         * @return true if the state represent the Block XPData
         */
        public boolean matches(BlockState state){
            if(this.block != state.getBlock())
                return false;
            if(!(this.block instanceof CropBlock) || !isCrop)
                return true;
            else{
                CropBlock crop = (CropBlock) this.block;
                return crop.isMaxAge(state);
            }
        }

        /**
         * Writes the Block XPData to a byte buffer
         * @param buf the buffer where to write
         */
        @Override
        public void writeToBytes(FriendlyByteBuf buf) {
            super.writeToBytes(buf);
            buf.writeInt(Block.getId(block.defaultBlockState()));
            buf.writeBoolean(isCrop);
        }

        /**
         * @return a stack representing the Block XP Data
         */
        @Override
        public ItemStack createStack() {
            return JobsUtil.itemStack(Item.BY_BLOCK.getOrDefault(block, Items.AIR), 1, -1);
        }

        /**
         * @return a string representation of the Block XPData
         */
        @Override
        public String toString() {
            String s = ForgeRegistries.BLOCKS.getKey(block).toString();
            return  s + " : " + super.toString();
        }
    }


    public static class EntityXPData extends XPData{
        private final EntityType<? extends Entity> entity;

        /**
         * Constructs an XPData for an Entity
         * @param xp_values
         * @param type
         */
        public EntityXPData(long[] xp_values, EntityType<? extends Entity> type) {
            super(xp_values);
            this.entity = type;
        }

        /**
         * Reads an Entity XPData from a byte buffer
         * @param buf the buffer where to read
         */
        public EntityXPData(FriendlyByteBuf buf){
            super(buf);
            entity = EntityType.byString(JobsUtil.readString(buf)).orElse(null);
        }

        public EntityType<? extends Entity> getEntity() {
            return entity;
        }

        /**
         * @return the locale translated name of the Entity
         */
        @OnlyIn(Dist.CLIENT)
        public String getEntityName(){
            return I18n.get("entity.minecraft." + EntityType.getKey(entity).getPath());
        }

        /**
         * Checks if an entity type is representing the entity
         * @param entity the type to check
         * @return true if the type represent the Entity XPData
         */
        public boolean matches(EntityType<? extends Entity> entity){
            return this.entity.equals(entity);
        }

        /**
         * Writes Entity the XPData to a byte buffer
         * @param buf the buffer where to write
         */
        @Override
        public void writeToBytes(FriendlyByteBuf buf) {
            super.writeToBytes(buf);
            String name = EntityType.getKey(entity).toString();
            JobsUtil.writeString(name, buf);
        }

        /**
         * @return a stack representing the Entity XP Data
         */
        @Override
        public ItemStack createStack() {
            return JobsUtil.createEntityItemStack(entity);
        }

        /**
         * @return a string representation of the Entity XPData
         */
        @Override
        public String toString() {
            String s = EntityType.getKey(entity).toString();
            return  s + " : " + super.toString();
        }
    }
}
