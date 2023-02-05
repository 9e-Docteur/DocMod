package be.ninedocteur.docmod.jobs.data;

import be.ninedocteur.docmod.jobs.data.capabilities.PlayerJobs;
import be.ninedocteur.docmod.jobs.data.registry.*;
import be.ninedocteur.docmod.jobs.gui.GuiGainXP;
import be.ninedocteur.docmod.jobs.gui.screens.GuiLevelUp;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.world.item.ItemStack;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClientJobsData {

    public static LevelData JOBS_LEVELS = new LevelData();
    public static BlockedCraftsData BLOCKED_CRAFTS = new BlockedCraftsData();
    public static BlockedBlocksData BLOCKED_BLOCKS = new BlockedBlocksData();

    public static Set<XPRegistry<? extends XPData>> XP_REGISTRIES = new HashSet<>();

    public static Map<String, DynamicTexture> JOBS_ICONS = new HashMap<>();

    public static TranslationData TRANSLATIONS = null;

    public static GuiGainXP.GuiAddXpInfos addXPInfos = new GuiGainXP.GuiAddXpInfos();
    public static final List<ItemStack> CURRENT_REWARDS = new ArrayList<>();
    public static PlayerJobs playerJobs = null;

    /**
     * Shows the Gain XP GUI
     * @param job the job for which the player received xp
     * @param xpAdded the amount of xp received
     */
    public static void showAddGui(String job, long xpAdded) {
        addXPInfos.addXP(job, xpAdded);
    }

    /**
     * Shows the Level Up GUI
     * @param job the job for which the player has received xp
     */
    public static void showLevelUpGui(String job) {
        Minecraft.getInstance().setScreen(new GuiLevelUp(job));
    }


    /**
     * Returns the XPData of the registry sorted by the amount of xp they can give to the player
     * @param job the job for which the XPData give xp
     * @param level the level of the player
     * @param registry the registry to sort
     * @return a sorted list of XPData, ready to be rendered in the GUI
     */
    public static List<XPData> getOrderedXPFromRegistry(String job, int level, XPRegistry<? extends XPData> registry){
        List<XPData> unordered = registry.getXPDataByJob(job)
                .stream()
                .map(x -> (XPData)x)
                .filter(x -> !x.createStack().isEmpty())
                .collect(Collectors.toList());
        List<XPData> ordered = new ArrayList<>();
        for(XPData x : unordered){
            boolean flag = true;
            for(XPData y : ordered){
                if(x.getXPByLevel(level) > y.getXPByLevel(level)){
                    ordered.add(ordered.indexOf(y), x);
                    flag = false;
                    break;
                }
            }
            if(flag)
                ordered.add(x);
        }
        return ordered;
    }

    /**
     * Returns all the Blocked Blocks and Crafts sorted by the level at which they are unlocked
     * @param job the job for which the Blocks and Crafts are blocked
     * @return a sorted list of UnlockStacks, ready to be rendered in the GUI
     */
    public static List<UnlockStack> getUnlockedStacksSorted(String job){
        return Stream.concat(
                BLOCKED_CRAFTS.getBlockedCrafts(job).stream().map(BlockedCraftsData.BlockedCraft::getUnlockStack),
                BLOCKED_BLOCKS.getBlockedBlocks(job).stream().map(BlockedBlocksData.BlockedBlock::getUnlockStack))
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Gets the locale translated name of a Job
     * @param job the job to translate
     * @return the locale name of the job
     */
    public static String getJobName(String job){
        return TRANSLATIONS.getTranslation(job,
                Minecraft.getInstance()
                        .getLanguageManager()
                        .getSelected()
                        .getCode());
    }
}
