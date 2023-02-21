package be.ninedocteur.docmod.jobs.gui.screens;

import java.awt.Color;
import java.io.IOException;


import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.jobs.JobFactory;
import be.ninedocteur.docmod.jobs.data.ClientInfos;
import be.ninedocteur.docmod.jobs.gui.buttons.ButtonJob;
import be.ninedocteur.docmod.jobs.util.Constants;
import be.ninedocteur.docmod.jobs.util.save.LoadUtil;
import be.ninedocteur.docmod.jobs.util.save.LoadXPValues;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.server.level.ServerPlayer;

public class MainJobsMenu extends Screen {
    Button job1, job2, job3, job4;

    public MainJobsMenu() 
    {
		super(Component.translatable("text.jobs.title"));
	}

	public static final ResourceLocation BACKGROUND = new ResourceLocation(DocMod.MOD_ID, "textures/gui/background.png");
    public static final ResourceLocation ICONS = new ResourceLocation(DocMod.MOD_ID, "textures/gui/jobs_icons.png");
    
    @Override
    protected void init() 
    {
//        if(Minecraft.getInstance().player.getServer() == null){
//            MinecraftServer server = Minecraft.getInstance().getSingleplayerServer();
//            LoadUtil.loadData(server);
//        } else {
//            LoadUtil.loadData(Minecraft.getInstance().player.getServer());
//        }
    	job1 = new ButtonJob(this.width/2 - 90, this.height/2 - 70, Constants.Job.byIndex(0));
        job2 = new ButtonJob(this.width/2 - 90, this.height/2 - 30, Constants.Job.byIndex(1));
        job3 = new ButtonJob(this.width/2 - 90, this.height/2 + 10, Constants.Job.byIndex(2));
        job4 = new ButtonJob(this.width/2 - 90, this.height/2 + 50, Constants.Job.byIndex(3));
        this.addRenderableWidget(job1);
        this.addRenderableWidget(job2);
        this.addRenderableWidget(job3);
        this.addRenderableWidget(job4);
    	super.init();
    }
    
    @Override
    public boolean isPauseScreen() 
    {
    	return false;
    }
    
    @Override
    public void render(PoseStack mStack, int mouseX, int mouseY, float partialTicks)
    {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, BACKGROUND);
        this.blit(mStack, this.width/2 - 128, this.height/2 - 110, 0, 0, 256, 220);
        drawTitle(mStack);
    	super.render(mStack, mouseX, mouseY, partialTicks);
    }

    private void drawTitle(PoseStack mStack)
    {
        mStack.pushPose();
        mStack.scale(2.0F, 2.0F, 2.0F);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        String title = I18n.get("text.jobs.title");
        int x = this.width/4 - this.font.width(title)/4 - 5;
        int y = this.height/2 - 114;
        this.font.draw(mStack, title, x, y, Color.black.getRGB());
        mStack.popPose();
    }
}
