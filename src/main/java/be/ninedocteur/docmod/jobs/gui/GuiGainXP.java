package be.ninedocteur.docmod.jobs.gui;

import java.awt.Color;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.gui.screens.compnent.DocModServerList;
import be.ninedocteur.docmod.jobs.data.ClientInfos;
import be.ninedocteur.docmod.jobs.util.Constants;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;

@OnlyIn(Dist.CLIENT)
public class GuiGainXP extends GuiComponent {

    public static final ResourceLocation TEXTURE = new ResourceLocation(DocMod.MOD_ID, "textures/gui/gui_gain_xp.png");

    private final Constants.Job job;
    private final long xp;
    public GuiGainXP(Constants.Job job, long xpAdded)
    {
        this.job = job;
        this.xp = xpAdded;
    }
    
    public void render(PoseStack mStack, float partialTicks)
    {
        mStack.pushPose();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int render_width = Minecraft.getInstance().getWindow().getGuiScaledWidth();

        long xp_progression = ClientInfos.job.getXPByJob(job);
        long total = Constants.XP_BY_LEVEL[ClientInfos.job.getLevelByJob(job)+1];
        int width = (int)(150 * ((double)xp_progression /(double)total));
        String title = ChatFormatting.WHITE + I18n.get("jobs." + job.name) + " (lvl " + ClientInfos.job.getLevelByJob(job) + ") : " +
                ChatFormatting.AQUA + "+" + xp + ChatFormatting.WHITE + " xp";
        String xpTotal = xp_progression + "/" + total;
        int titleWidth = Minecraft.getInstance().font.width(title);
        int xpTotalWidth = Minecraft.getInstance().font.width(xpTotal);

        this.blit(mStack, render_width/2 - 90, 5, 0, 0, 180, 50);//background
        this.blit(mStack, render_width/2 - 75, 35, 0, 50, 150, 12);//progressbackground
        this.blit(mStack, render_width/2 - 75, 35, 0, 62, width, 12);//progressbar
        Minecraft.getInstance().font.draw(mStack, title, render_width/2 - titleWidth/2, 15, Color.white.getRGB());
        Minecraft.getInstance().font.draw(mStack, xpTotal, render_width/2 - xpTotalWidth/2, 38, Color.black.getRGB());
        mStack.popPose();
    }


    public static class GuiAddXpInfos{
        public long xpAdded;
        public Constants.Job job;
        public long ticks;

        public GuiAddXpInfos(Constants.Job j, long xp)
        {
            this.job = j;
            this.xpAdded = xp;
            ticks = System.currentTimeMillis() + 5000;
        }
    }

}
