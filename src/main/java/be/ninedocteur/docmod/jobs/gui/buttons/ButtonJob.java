package be.ninedocteur.docmod.jobs.gui.buttons;

import java.awt.Color;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.jobs.data.ClientInfos;
import be.ninedocteur.docmod.jobs.gui.screens.GuiJobInfos;
import be.ninedocteur.docmod.jobs.util.Constants;
import be.ninedocteur.docmod.jobs.util.GuiUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import org.lwjgl.opengl.GL11;

@OnlyIn(Dist.CLIENT)
public class ButtonJob extends Button {

    private final ResourceLocation texture = new ResourceLocation(DocMod.MOD_ID + ":textures/gui/jobs_icons.png");
    private final int xTexStart;
    private final int yTexStart;
    private final String title;
    private final Constants.Job job;
    private int buttonX = this.getX();
    private int buttonY = this.getY();

    protected static final net.minecraft.client.gui.components.Button.CreateNarration DEFAULT_NARRATION = (p_253298_) -> {
        return p_253298_.get();
    };

    public ButtonJob(int posX, int posY, Constants.Job j)
    {
        super(posX, posY, 200, 40, Component.literal(""),new OnPressed(),DEFAULT_NARRATION);
        this.xTexStart = 40 * j.index;
        this.yTexStart = 216;
        this.title = I18n.get("jobs." + j.name);
        this.job = j;
    }

    public void setPosition(int x, int y)
    {
        this.buttonX = x;
        this.buttonY = y;
    }


    @Override
    public void render(PoseStack mStack, int mouseX, int mouseY, float partialTicks)
    {
    	if (this.visible)
        {
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, this.texture);
            drawIcon(mStack);
            drawGradient(mStack);
            drawName(mStack);

        }
    }

    private void drawIcon(PoseStack mStack)
    {
        int i = this.xTexStart;
        int j = this.yTexStart;
        GuiUtil.drawScaledTexture(mStack, this.buttonX, this.buttonY, i, j, 40, 40, 30, 30);
    }

    private void drawGradient(PoseStack mStack)
    {
        long xp = ClientInfos.job.getXPByJob(this.job);
        int lvl = ClientInfos.job.getLevelByJob(this.job);
        if(lvl < 25)
        {
            long total = Constants.XP_BY_LEVEL[lvl+1];
            int size = (int)(150*((double)xp / (double)total));
            GuiUtil.drawTexture(mStack, this, this.buttonX + 30, this.buttonY + 15, 0, 80, 150, 12); //background
            GuiUtil.drawTexture(mStack, this, this.buttonX + 30, this.buttonY + 15, 0, 92, size, 12);

            String info = xp + "/" + total;
            int widthInfo = Minecraft.getInstance().font.width(info);
            Minecraft.getInstance().font.draw(mStack, info, this.buttonX + 105 - widthInfo/2, this.buttonY + 18,
                    Color.white.getRGB());
        }
        else
        {
            int size = 150;
            this.blit(mStack, this.buttonX + 30, this.buttonY + 15, 0, 80, 150, 12); //background
            this.blit(mStack, this.buttonX + 30, this.buttonY + 15, 0, 104, size, 12);

            String info = I18n.get("text.level.max");
            int widthInfo = Minecraft.getInstance().font.width(info);
            Minecraft.getInstance().font.draw(mStack, info, this.buttonX + 95 - widthInfo/2, this.buttonY + 18,
                    Color.white.getRGB());
        }
    }

    private void drawName(PoseStack mStack)
    {
        int lvl = ClientInfos.job.getLevelByJob(this.job);
        String name = this.title + " (" + I18n.get("text.level") + " " + lvl + ")";
        int x = 105 - Minecraft.getInstance().font.width(name)/2;
        int y = 2;
        Minecraft.getInstance().font.draw(mStack, name, this.buttonX + x, this.buttonY + y, Color.black.getRGB());
    }
    
    public static class OnPressed implements OnPress{

		@Override
		public void onPress(Button btn) 
		{
			if(!(btn instanceof ButtonJob)) return;
			Minecraft.getInstance().setScreen(new GuiJobInfos(((ButtonJob)btn).job));
		}
    	
    }
}
