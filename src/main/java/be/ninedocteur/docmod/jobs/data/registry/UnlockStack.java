package be.ninedocteur.docmod.jobs.data.registry;

import net.minecraft.world.item.ItemStack;

public class UnlockStack implements Comparable<UnlockStack>{

    private final int level;
    private final ItemStack stack;
    private final BlockedData.Type type;

    /**
     * Constructs an Unlock Stack to be shown in the GUI
     * @param level the level at which it is unlocked
     * @param stack the stack rendered in the GUI
     * @param type the type of UnlockStack it is
     */
    public UnlockStack(int level, ItemStack stack, BlockedData.Type type) {
        this.level = level;
        this.stack = stack;
        this.type = type;
    }

    /**
     * Comparator used to sort them in the GUI
     * @param o the object to be compared.
     * @return this.level - o.level
     */
    @Override
    public int compareTo(UnlockStack o) {
        return Integer.compare(this.level, o.level);
    }

    public int getLevel() {
        return level;
    }

    public ItemStack getStack() {
        return stack;
    }

    public String getType() {
        return type.name().toLowerCase();
    }
}
