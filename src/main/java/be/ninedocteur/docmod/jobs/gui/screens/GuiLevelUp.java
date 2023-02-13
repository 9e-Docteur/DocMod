package be.ninedocteur.docmod.jobs.gui.screens;


import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.jobs.data.ClientJobsData;
import be.ninedocteur.docmod.jobs.data.registry.UnlockStack;
import be.ninedocteur.docmod.jobs.util.GuiUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.ItemStack;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class GuiLevelUp extends Screen {

    public static final ResourceLocation TEXTURES = new ResourceLocation(DocMod.MOD_ID, "textures/gui/gui_level_up.png");
    private final String job;

    /**
     * Creates a Level Up GUI for a job
     * @param job the job that leveled up
     */
    public GuiLevelUp(String job) {
    	super(Component.empty());
        this.job = job;
    }

    /**
     * @return false, this GUI doesn't pause the game
     */
    @Override
    public boolean isPauseScreen() {
    	return false;
    }

    /**
     * Renders the GUI on the screen
     * @param mStack
     * @param mouseX
     * @param mouseY
     * @param partialTicks
     */
    @Override
    public void render(PoseStack mStack, int mouseX, int mouseY, float partialTicks) {
        if(this.font == null)
            return;
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, TEXTURES);
        this.blit(mStack, this.width/2 - 88, this.height/2 - 75, 0, 0, 176, 150); //background


        
        GuiUtil.drawJobIcon(mStack, this, job, this.width/2, this.height/2-47, 40);
        String lvl = I18n.get("text.level") + " " + ClientJobsData.playerJobs.getLevelByJob(job);
        GuiUtil.renderProgressBarWithText(mStack, this, this.width/2 - 75, this.height/2 - 25, 150, 12, 1, 1, lvl);

        String unlock = I18n.get("text.unlocked");
        GuiUtil.renderCenteredString(mStack, unlock, Color.BLACK.getRGB(), this.width/2, this.height/2, 1.0f);
        this.drawUnlockedStacks(mStack, mouseX, mouseY);
        String reward = I18n.get("text.rewards");
        GuiUtil.renderCenteredString(mStack, reward, Color.BLACK.getRGB(), this.width/2, this.height/2 + 36, 1.0f);
        this.drawRewardStacks(mStack, mouseX, mouseY);
        super.render(mStack, mouseX, mouseY, partialTicks);
    }


    /**
     * Renders the Unlocked stacks of the screen
     * @param mStack
     * @param mouseX the x coordinate of the mouse, used to find the tooltip to render
     * @param mouseY the y coordinate of the mouse, used to find the tooltip to render
     */
    private void drawUnlockedStacks(PoseStack mStack, int mouseX, int mouseY) {
        //RenderHelper.setupForFlatItems();
        List<UnlockStack> stacks = ClientJobsData.getUnlockedStacksSorted(this.job);
        stacks.removeIf(x -> x.getLevel() != ClientJobsData.playerJobs.getLevelByJob(job));

        if(stacks.isEmpty()){
            String text = I18n.get("text.no_unlock");
            GuiUtil.renderCenteredString(mStack, text, Color.RED.getRGB(), this.width/2, this.height/2 + 19, 0.75f);
            return;
        }

        int hovered = -1;
        int y = this.height/2 + 11;
        int sizeX = stacks.size()*16 + (stacks.size()-1)*6;
        int x = this.width/2 - sizeX/2;
        for(int i = 0; i < stacks.size(); i++) {
            this.itemRenderer.renderGuiItem(stacks.get(i).getStack(), x + i*22, y);
            if(mouseX >= x + i*22 && mouseX < x + i*22 + 16 && mouseY >= y && mouseY < y + 16)
                hovered = i;
        }
        if(hovered != -1)
            this.renderUnlockedCraftToolTip(mStack, stacks.get(hovered), mouseX, mouseY);
        //RenderHelper.setupFor3DItems();
    }

    /**
     * Renders the rewards stacks on the screen
     * @param mStack
     * @param mouseX the x coordinate of the mouse, used to find the tooltip to render
     * @param mouseY the y coordinate of the mouse, used to find the tooltip to render
     */
    private void drawRewardStacks(PoseStack mStack, int mouseX, int mouseY) {
       // RenderHelper.setupForFlatItems();
        List<ItemStack> stacks = ClientJobsData.CURRENT_REWARDS;

        if(stacks.isEmpty()){
            String text = I18n.get("text.no_reward");
            GuiUtil.renderCenteredString(mStack, text, Color.RED.getRGB(), this.width/2, this.height/2 + 56, 0.75f);
            return;
        }

        int hovered = -1;
        int y = this.height/2 + 48;
        int sizeX = stacks.size()*16 + (stacks.size()-1)*6;
        int x = this.width/2 - sizeX/2;
        for(int i = 0; i < stacks.size(); i++) {
            this.itemRenderer.renderGuiItem(stacks.get(i).copy(), x + i*22, y);
            if(mouseX >= x + i*22 && mouseX < x + i*22 + 16 && mouseY >= y && mouseY < y + 16)
                hovered = i;
        }
        if(hovered != -1)
            this.renderToolTipAndCount(mStack, stacks.get(hovered), mouseX, mouseY);
        //RenderHelper.setupFor3DItems();
    }


    /**
     * Renders the tooltip for the hovered UnlockStack
     * @param mStack
     * @param stack the hovered UnlockStack
     * @param x the x coordinate of the mouse
     * @param y the y coordinate of the mouse
     */
    private void renderUnlockedCraftToolTip(PoseStack mStack, UnlockStack stack, int x, int y) {
        List<FormattedCharSequence> tooltips = new ArrayList<>();
        tooltips.add(Component.literal(stack.getStack().getDisplayName().getString().replace("[", "").replace("]", "")).getVisualOrderText());
        tooltips.add(Component.literal(ChatFormatting.GREEN + I18n.get("text.unlock_" + stack.getType())).getVisualOrderText());

        this.renderTooltip(mStack, tooltips, x, y, Minecraft.getInstance().font);
    }

    /**
     * Renders the tooltip and the count for the hovered reward
     * @param mStack
     * @param stack the hovered stack
     * @param x the x coordinate of the mouse
     * @param y the y coordinate of the mouse
     */
    protected void renderToolTipAndCount(PoseStack mStack, ItemStack stack, int x, int y) {
        List<FormattedCharSequence> tooltips = new ArrayList<>();

        tooltips.add(Component.literal(stack.getDisplayName().getString().replace("[", "").replace("]", "")).getVisualOrderText());
        tooltips.add(Component.literal(ChatFormatting.GREEN + "" + stack.getCount()).getVisualOrderText());

        this.renderTooltip(mStack, tooltips, x, y, Minecraft.getInstance().font);
    }
}
