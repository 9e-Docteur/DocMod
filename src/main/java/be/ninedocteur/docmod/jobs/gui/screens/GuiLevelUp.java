package be.ninedocteur.docmod.jobs.gui.screens;



import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.jobs.data.ClientInfos;
import be.ninedocteur.docmod.jobs.util.Constants;
import be.ninedocteur.docmod.jobs.util.GuiUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class GuiLevelUp extends Screen {

    public static final ResourceLocation TEXTURES = new ResourceLocation(DocMod.MOD_ID, "textures/gui/gui_level_up.png");
    public static final ResourceLocation ICONS = new ResourceLocation(DocMod.MOD_ID, "textures/gui/jobs_icons.png");
    private final Constants.Job job;

    public GuiLevelUp(Constants.Job job)
    {
    	super(Component.literal(""));
        this.job = job;
    }
    
    @Override
    public boolean isPauseScreen() 
    {
    	return false;
    }

    @Override
    public void render(PoseStack mStack, int mouseX, int mouseY, float partialTicks)
    {
        if(this.font == null) return;
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, TEXTURES);
        this.blit(mStack, this.width/2 - 88, this.height/2 - 75, 0, 0, 176, 150); //background

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, ICONS);
        
        GuiUtil.drawJobIcon(mStack, this, job, this.width/2, this.height/2-47);
        int textY = ClientInfos.job.getLevelByJob(this.job) >= 25 ? 12 : 0;
        this.blit(mStack, this.width/2 - 75, this.height/2 - 25, 0, 92 + textY, 150, 12); //gradient

        String lvl = I18n.get("text.level") + " " + ClientInfos.job.getLevelByJob(job);
        this.font.draw(mStack, lvl, this.width/2 - this.font.width(lvl)/2, this.height/2 - 22, Color.white.getRGB());
        String unlock = I18n.get("text.unlocked");
        this.font.draw(mStack, unlock, this.width/2 - this.font.width(unlock)/2, this.height/2, Color.black.getRGB());
        this.drawUnlockedStacks(mStack, mouseX, mouseY);
        String reward = I18n.get("text.rewards");
        this.font.draw(mStack, reward, this.width/2 - this.font.width(reward)/2, this.height/2 + 34, Color.black.getRGB());
        this.drawRewardStacks(mStack, mouseX, mouseY);
        super.render(mStack, mouseX, mouseY, partialTicks);
    }


    private void drawUnlockedStacks(PoseStack mStack, int mouseX, int mouseY)
    {
        //RenderHelper.setupForFlatItems();
        List<ItemStack> stacks = new ArrayList<>();
        int hovered = -1;
        for(Item i : ClientInfos.CRAFT_UNLOCK_LVL.keySet())
        {
            if(ClientInfos.CRAFT_UNLOCK_JOB.get(i) == this.job && ClientInfos.CRAFT_UNLOCK_LVL.get(i) == ClientInfos.job.getLevelByJob(this.job))
            {
                ItemStack s = new ItemStack(i);
                stacks.add(s);
            }
        }
        int y = this.height/2 + 11;
        int sizeX = stacks.size()*16 + (stacks.size()-1)*6;
        int x = this.width/2 - sizeX/2;
        for(int i = 0; i < stacks.size(); i++)
        {
            this.itemRenderer.renderGuiItem(stacks.get(i), x + i*22, y);
            if(mouseX >= x + i*22 && mouseX < x + i*22 + 16 && mouseY >= y && mouseY < y + 16)
                hovered = i;
        }
        if(hovered != -1)
            this.renderToolTip(mStack, stacks.get(hovered), mouseX, mouseY);
        //RenderHelper.setupFor3DItems();
    }

    private void drawRewardStacks(PoseStack mStack, int mouseX, int mouseY)
    {
        //RenderHelper.setupForFlatItems();
        List<ItemStack> stacks = ClientInfos.CURRENT_REWARDS;
        int hovered = -1;

        int y = this.height/2 + 48;
        int sizeX = stacks.size()*16 + (stacks.size()-1)*6;
        int x = this.width/2 - sizeX/2;
        for(int i = 0; i < stacks.size(); i++)
        {
            this.itemRenderer.renderGuiItem(stacks.get(i).copy(), x + i*22, y);
            if(mouseX >= x + i*22 && mouseX < x + i*22 + 16 && mouseY >= y && mouseY < y + 16)
                hovered = i;
        }
        if(hovered != -1)
            this.renderToolTipAndCount(mStack, stacks.get(hovered), mouseX, mouseY);
        //RenderHelper.setupFor3DItems();
    }


    private void renderToolTip(PoseStack mStack, ItemStack stack, int x, int y)
    {
        List<Component> tooltips = new ArrayList<>();

        tooltips.add(Component.literal(stack.getDisplayName().getString().replace("[", "").replace("]", "")));

        if(ClientInfos.CRAFT_UNLOCK_JOB.containsKey(stack.getItem()))
            tooltips.add(Component.literal(ChatFormatting.GREEN + I18n.get("text.unlock_craft")));

        this.renderComponentTooltip(mStack, tooltips, x, y);
    }

    protected void renderToolTipAndCount(PoseStack mStack, ItemStack stack, int x, int y)
    {
        List<Component> tooltips = new ArrayList<>();

        tooltips.add(Component.literal(stack.getDisplayName().getString().replace("[", "").replace("]", "")));
        tooltips.add(Component.literal(ChatFormatting.GREEN + "" + stack.getCount()));

        this.renderComponentTooltip(mStack, tooltips, x, y);
    }
}
