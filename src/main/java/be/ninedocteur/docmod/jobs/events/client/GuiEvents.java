package be.ninedocteur.docmod.jobs.events.client;

import be.ninedocteur.docmod.jobs.data.ClientJobsData;
import be.ninedocteur.docmod.jobs.gui.GuiGainXP;
import be.ninedocteur.docmod.jobs.gui.containers.ContainerCraft;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import org.jetbrains.annotations.Nullable;

@EventBusSubscriber
public class GuiEvents {

    /**
     * Render the Gain XP interface if needed. Is executed at every frame.
     * @param e the Render Event
     */
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void clientTick(RenderGuiOverlayEvent e) {
        if(Minecraft.getInstance().player == null)
            return;
            ClientJobsData.addXPInfos.update();
            if(!ClientJobsData.addXPInfos.shouldShow())
                return;
            Pair<String, Long> toShow = ClientJobsData.addXPInfos.showJobAtTime();
            if(ClientJobsData.playerJobs.isMax(toShow.getFirst()))
                return;
            GuiGainXP gui = new GuiGainXP(toShow.getFirst(), toShow.getSecond());
            gui.render(new PoseStack(), 0.0f);
    }

    /**
     * Cancels the opening of the regular crafting interface and opens the custom one from the Jobs mod.
     * @param e the Click Event
     */
    @SubscribeEvent
    public void onOpenCraftingTable(RightClickBlock e) {
        if(e.getLevel().getBlockState(e.getPos()).getBlock() == Blocks.CRAFTING_TABLE) {
            e.setCanceled(true);
            if(!e.getLevel().isClientSide) {
                openUpdatedCraftingTable(e.getEntity(), e.getLevel(), e.getPos());
            }
        }
    }


    /**
     * Opens the custom crafting interface of the Jobs mod.
     * @param player the player opening the interface
     * @param world the current world
     * @param pos the position of the crafting table
     */
    private void openUpdatedCraftingTable(Player player, Level world, BlockPos pos){
        player.openMenu(new MenuProvider() {

            @Nullable
            @Override
            public AbstractContainerMenu createMenu(int p_39954_, Inventory p_39955_, Player p_39956_) {
                return new ContainerCraft(p_39954_, p_39955_, (ContainerLevelAccess) pos);
            }

            @Override
            public Component getDisplayName() {
                return Component.literal("container.crafting");
            }
        });
    }
}
